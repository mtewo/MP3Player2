package de.hsrm.mi.enia.mp3player.business;

import de.hsrm.mi.eibo.simpleplayer.SimpleAudioPlayer;


import de.hsrm.mi.eibo.simpleplayer.SimpleMinim;

// Hallo Leute

public class MP3Player {
	private SimpleMinim minim;
    private SimpleAudioPlayer audioPlayer;
    private Playlist currentPlaylist;
    private int currentIndex = 0;
    private boolean shuffle = false;
    private boolean repeat = false;

    /*
     * Bei der Instanziierung der Klasse MP3Player
     * soll jedes Objekt immer ein Minim (true) haben.
     
    public MP3Player() {
        minim = new SimpleMinim(true);
    }*/
    
 // NEU: Thread-Variable und Flag
    private PlayThread playThread;
    private volatile boolean wasStopped = false;  // volatile wichtig für Thread-Kommunikation!
    
    /**
     * GEÄNDERT: SimpleMinim nun mit FALSE (blockierend)
     * Wir steuern das Threading selbst!
     */
    public MP3Player() {
        minim = new SimpleMinim(false);  // WICHTIG: false statt true!
    }
    
    
    private void releasePlayer() {
        if (audioPlayer != null) {
            try {
                audioPlayer.pause();   // Wiedergabe anhalten
            } catch (Exception ignored) {}
            audioPlayer = null;        // Referenz freigeben
        }
    }
	
	/*
	 * bei der Instanziierung der Klasse MP3Player soll jedes Objekt 
	 * immer ein minim (immer auf true) und audioplayer haben
	 */
	
    public void setPlaylist(Playlist playlist) {
        this.currentPlaylist = playlist;
        this.currentIndex = 0;
        System.out.println("Playlist gesetzt: " + playlist.getName());
    }

    /** Aktuellen Song abspielen */
    public void play() {
        if (currentPlaylist == null || currentPlaylist.size() == 0) {
            System.out.println("Keine Playlist oder leer!");
            return;
        }

        Track track = currentPlaylist.getTracks().get(currentIndex);
        System.out.println("Spiele: " + track);

        // alten Player schließen, falls einer läuft
        if (audioPlayer != null) {
            releasePlayer();
        }

        // neue Datei laden
        audioPlayer = minim.loadMP3File(track.getFilename());
        //audioPlayer.play();
        
     // NEU: Thread starten statt direkt play()
        wasStopped = false;  // Flag zurücksetzen
        playThread = new PlayThread();
        playThread.start();
    }

    /** Einen bestimmten Song direkt abspielen */
    public void play(String filename) {
        if (audioPlayer != null) {
        	releasePlayer();
        }
        audioPlayer = minim.loadMP3File(filename);
        //audioPlayer.play();
     // Auch hier Thread nutzen
        wasStopped = false;
        playThread = new PlayThread();
        playThread.start();
    }

    /** Pause */
    public void pause() {
  
        if (audioPlayer != null) {
            audioPlayer.pause();
        }
    }

    /** Stoppen */
    public void stop() {
    	wasStopped = true;  // NEU: Flag setzen
        if (audioPlayer != null) {
        	releasePlayer();
        }
    }

    /** Nächster Song */
    public void skip() {
        if (currentPlaylist == null) return;

        if (shuffle) {
            currentIndex = (int) (Math.random() * currentPlaylist.size());
        } else {
            currentIndex++;
            if (currentIndex >= currentPlaylist.size()) {
                if (repeat) currentIndex = 0;
                else {
                    System.out.println("Ende der Playlist erreicht.");
                    return;
                }
            }
        }
        play();
    }

    /** Vorheriger Song */
    public void skipBack() {
        if (currentPlaylist == null) return;

        currentIndex--;
        if (currentIndex < 0) {
            if (repeat) currentIndex = currentPlaylist.size() - 1;
            else currentIndex = 0;
        }
        play();
    }

    /** Shuffle-Modus an/aus */
    public void shuffle(boolean on) {
        this.shuffle = on;
        System.out.println("Shuffle: " + (on ? "aktiv" : "aus"));
    }

    /** Repeat-Modus an/aus */
    public void repeat(boolean on) {
        this.repeat = on;
        System.out.println("Repeat: " + (on ? "aktiv" : "aus"));
    }

    /** Lautstärke (Gain) einstellen */
    public void volume(float value) {
        if (audioPlayer != null) {
            audioPlayer.setGain(value); // z. B. -10 = leiser, +5 = lauter
        }
    }
    
    private class PlayThread extends Thread {
        @Override
        public void run() {
            if (audioPlayer != null) {
                // BLOCKIERENDES Abspielen!
                audioPlayer.play();
                
                System.out.println("Song zu Ende.");
                
                // NEU Aufgabe 2: Hier würde Auto-Play kommen
                // if (!wasStopped) {
                //     skip(); // Nächsten Song abspielen
                // }
            }
        }
    }

}
