package de.hsrm.mi.enia.mp3player.business;

public class Track {
	
	private final String filename;   // relativer Pfad wie "songs/02 Drei Worte.mp3"
    private final String title;
    private final String artist;
    private final String album;
    private final int lengthSec;     // Dauer in Sekunden (falls vorhanden)

    public Track(String filename, String title, String artist, String album, int lengthSec) {
        this.filename = filename;
        this.title = title;
        this.artist = artist;
        this.album = album;
        this.lengthSec = lengthSec;
    }

    public String getFilename() { return filename; }
    public String getTitle()    { return title; }
    public String getArtist()   { return artist; }
    public String getAlbum()    { return album; }
    public int getLengthSec()   { return lengthSec; }

    @Override public String toString() {
        return (title != null && !title.isBlank() ? title : filename) +
               (artist != null && !artist.isBlank() ? " – " + artist : "");
    }

}
