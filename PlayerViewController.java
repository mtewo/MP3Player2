package de.hsrm.mi.enia.mp3player.presentation.views;

import de.hsrm.mi.enia.mp3player.business.MP3Player;


import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.ToggleButton;

public class PlayerViewController extends BaseController<PlayerView>{
	
	Button playButton;
	Button skipButton;
	Button skipBackButton;
	Button pauseButton;
	ToggleButton shuffleButton;
	
	private MP3Player player;
	
	
	
	public class PlayHandler implements EventHandler<ActionEvent> {

		@Override
		public void handle(ActionEvent event) {
			player.play();

		}

	}
	
	public class SkipHandler implements EventHandler<ActionEvent> {

		@Override
		public void handle(ActionEvent event) {
			player.skip();

		}

	}
	


	
	/**
	 * PlayThread lagert den langlaufenden Aufruf 
	 *    player.play() 
	 * aus. 
	 * Wohin? In einem separaten Thread, damit die GUI nicht blockiert 
	 */
	/*public class PlayThread extends Thread {        auskommentiert da PlayThread im Player ausgelagert 
	 

		public void run() {
			player.play();
			
			/* Nachem der Song abgespielt wurde,
			 * soll sich der Button wieder aendern
			 * Da der Aufruf aus einem Thread erfolgt,
			 * darf dieser nicht direkt die Aenderung durchfuehren,
			 * sondern muss die Aufgabe dem Application Thread 
			 * uebergeben
			 */
			/*Platform.runLater(
				() -> {
					
					
					//playButton.setText("play");
					playButton.getStyleClass().remove("pause");
					playButton.getStyleClass().add("play");
					
				}
			);
		}
	}*/
	
	
	public PlayerViewController(MP3Player player) {
		root = new PlayerView();
		
		playButton = root.controlPane.playButton;
		skipButton = root.controlPane.skipButton;
		skipBackButton = root.controlPane.skipBackButton;
		shuffleButton = root.controlPane.shuffleButton;
		
		
		this.player = player;
		initialize();
	}
	
	public void initialize() {
		
		// Nutzung einer Member-Klasse, falls man mehrmals einen gleichen Handler erzeugen will
		skipButton.addEventHandler(ActionEvent.ACTION, new SkipHandler());

		// Anonyme Klasse, falls man auch Attribute oder auch weitere Methoden definieren will
		// z.B. eine Zustand, um zwischen play/pause zu unterscheiden
		playButton.addEventHandler(ActionEvent.ACTION, new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				
				// NEU: Prüfen, welches Icon gerade angezeigt wird
				if (playButton.getStyleClass().contains("play")) {
					// Zustand: Play-Icon angezeigt → Song starten
					
					// NEU: Direkter Aufruf, kein Thread hier!
                    player.play();  // Player kümmert sich intern um Threading
                    
                    // Icon zu Pause wechseln
                    playButton.getStyleClass().remove("play");
                    playButton.getStyleClass().add("pause");
                    
                    System.out.println("Play wurde gestartet");
				
				} else {
					// Zustand: Pause-Icon angezeigt → Song pausieren
					
					player.pause();
					
					// Icon zu Play wechseln
					playButton.getStyleClass().remove("pause");
					playButton.getStyleClass().add("play");
					
					System.out.println("Pause wurde aufgerufen");
				}

			}

		});
		
		// Lamda-Ausdruck - kurze kompakte Loesung, die in den meisten Faellen die
		// passende Variante ist
		skipBackButton.addEventHandler(ActionEvent.ACTION, 

			event -> {
				player.skipBack();
				System.out.println("SkipBack wurde aufgerufen");
				
			}

		);
		
		shuffleButton.selectedProperty().addListener(new ChangeListener<Boolean>() {

			@Override
			public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
				player.shuffle(newValue.booleanValue());
				System.out.println("Shuffle: " + (newValue ? "AN" : "AUS"));
			}
		});
		
	}

}
