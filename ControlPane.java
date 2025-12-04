package de.hsrm.mi.enia.mp3player.presentation.uicomponents;

import javafx.scene.control.Button;
import javafx.scene.control.ToggleButton;
import javafx.scene.layout.HBox;
//import javafx.geometry.Insets;
//import javafx.geometry.Pos;

public class ControlPane extends HBox {
	public Button playButton;
	public Button skipButton;
	public Button skipBackButton;
	public ToggleButton shuffleButton;
	
	public ControlPane() {
		playButton = new Button("");
		playButton.getStyleClass().add("icon-button");
		playButton.setId("play-button");
		// wenn Button pause-Icon anzeigen soll (ab naechter Woche dann im Controller)
		playButton.getStyleClass().remove("play");
		playButton.getStyleClass().add("pause");
		
		// wenn Button play-Icon anzeigen soll (ab naechter Woche dann im Controller)
		playButton.getStyleClass().remove("pause");
		playButton.getStyleClass().add("play");
		
		skipButton = new Button("");
		skipButton.getStyleClass().add("icon-button");
		skipButton.setId("skip-button");
		
		skipBackButton = new Button("");
		skipBackButton.getStyleClass().add("icon-button");
		skipBackButton.setId("skipback-button");
		
		shuffleButton = new ToggleButton("");
		shuffleButton.getStyleClass().add("icon-button");
		shuffleButton.setId("shuffel-button");
		
		// wenn Button schuffle-disabled-Icon anzeigen soll (ab naechter Woche dann im Controller)
		shuffleButton.getStyleClass().remove("shuffle");
		shuffleButton.getStyleClass().add("shuffle-disabled");
				
		// wenn Button play-Icon anzeigen soll (ab naechter Woche dann im Controller)
		shuffleButton.getStyleClass().remove("shuffle-disabled");
		shuffleButton.getStyleClass().add("shuffle");
		
		/* Layout-Eigenschaften der HBox
		this.setAlignment(Pos.CENTER);  // Buttons zentrieren
		this.setSpacing(15);  // Abstand zwischen Buttons
		this.setPadding(new Insets(10, 20, 10, 20));  // Außenabstand*/
		
		
		this.getChildren().addAll(
				skipBackButton,
				playButton,
				skipButton,
				shuffleButton);
		
		this.setId("control-view");
	}

}
