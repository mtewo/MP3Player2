package de.hsrm.mi.enia.mp3player;

import de.hsrm.mi.enia.mp3player.presentation.MP3PlayerGUI;
import javafx.application.Application;

public class StarterApp {
	public static void main(String[] args) {
		Application.launch(MP3PlayerGUI.class); 
		// erstellt eine Instanz von MP3PlayerGUI, 
		//ruft automatisch start(stage primaryStage)
		//und startet JavaFx Application Thread
	}
}
