package de.hsrm.mi.enia.mp3player.business;

import de.hsrm.mi.prog.util.StaticScanner;

public class KeyboardController {
	
	private static final String HELP = String.join("\n",
	        "Befehle:",
	        "  play                 -> aktuellen Track/Playlist abspielen",
	        "  play <datei.mp3>     -> spezifische Datei abspielen",
	        "  pause                -> pausieren",
	        "  stop                 -> stoppen (Player freigeben)",
	        "  skip                 -> nächster Titel",
	        "  back                 -> vorheriger Titel",
	        "  shuffle on|off       -> Shuffle an/aus",
	        "  repeat on|off        -> Repeat an/aus",
	        "  volume <dB>          -> Lautstärke (Gain) setzen, z.B. -10 oder 5",
	        "  quit                 -> Programm beenden"
	    );

	    private static final String PLAY    = "play";
	    private static final String PAUSE   = "pause";
	    private static final String STOP    = "stop";
	    private static final String VOLUME  = "volume";
	    private static final String SKIP    = "skip";
	    private static final String BACK    = "back";
	    private static final String SHUFFLE = "shuffle";
	    private static final String REPEAT  = "repeat";
	    private static final String QUIT    = "quit";
	    private static final String HELP_CMD = "help";

	    public static void main(String[] args) {
	    	
	    MP3Player mp3Player = new MP3Player(); // passt zum vorhandenen Konstruktor
	    System.out.println("Einfacher MP3-Controller. Tippe 'help' für Befehle.");
	    System.out.println(HELP);

	    while (true) {
	        System.out.print("> ");
	        String line = StaticScanner.nextString();
	        if (line == null) {
	            // Eingabestrom beendet (z.B. Ctrl+D) -> sauber beenden
	            mp3Player.stop();
	            return;
	        }

	        line = line.trim();
	        if (line.isEmpty()) {
	            continue;
	        }

	        String[] parts = line.split("\\s+", 2);
	        String command = parts[0].toLowerCase();
	        String arg = (parts.length > 1) ? parts[1].trim() : null;

	        try {
	            switch (command) {
	                case HELP_CMD:
	                    System.out.println(HELP);
	                    break;

	                case PLAY:
	                    if (arg != null && !arg.isEmpty()) {
	                        // play <datei.mp3>
	                        mp3Player.play(arg);
	                    } else {
	                        // play (aktuellen Track/Playlist)
	                        mp3Player.play();
	                    }
	                    break;

	                case PAUSE:
	                    mp3Player.pause();
	                    break;

	                case STOP:
	                    mp3Player.stop();
	                    break;

	                case SKIP:
	                    mp3Player.skip();
	                    break;

	                case BACK:
	                    mp3Player.skipBack();
	                    break;

	                case SHUFFLE:
	                    if (arg == null) {
	                        System.out.println("Bitte 'shuffle on' oder 'shuffle off' verwenden.");
	                        break;
	                    }
	                    mp3Player.shuffle(parseOnOff(arg));
	                    break;

	                case REPEAT:
	                    if (arg == null) {
	                        System.out.println("Bitte 'repeat on' oder 'repeat off' verwenden.");
	                        break;
	                    }
	                    mp3Player.repeat(parseOnOff(arg));
	                    break;

	                case VOLUME:
	                    if (arg == null) {
	                        System.out.println("Nutzung: volume <dB>, z.B. -10 oder 5");
	                        break;
	                    }
	                    try {
	                        float gain = Float.parseFloat(arg.replace(',', '.'));
	                        mp3Player.volume(gain);
	                    } catch (NumberFormatException nfe) {
	                        System.out.println("Ungültiger Zahlenwert für Lautstärke: " + arg);
	                    }
	                    break;

	                case QUIT:
	                    mp3Player.stop(); // Player freigeben
	                    System.out.println("Tschüss!");
	                    return;

	                default:
	                    System.out.println("Unbekannter Befehl: " + command);
	                    System.out.println("Tippe 'help' für die verfügbaren Befehle.");
	            }
	        } catch (Exception ex) {
	            // Fängt unerwartete Laufzeitfehler ab, damit die Schleife weiterlebt
	            System.out.println("Fehler beim Ausführen des Befehls: " + ex.getMessage());
	        }
	    }
	}

	    private static boolean parseOnOff(String s) {
	        String v = s.toLowerCase();
	        if (v.equals("on") || v.equals("an") || v.equals("true") || v.equals("1")) return true;
	        if (v.equals("off") || v.equals("aus") || v.equals("false") || v.equals("0")) return false;
	        // Default: interpretieren als aus
	        System.out.println("Unklarer Wert '" + s + "'. Erwarte on/off.");
	        return false;
	    }

}
