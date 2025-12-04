package de.hsrm.mi.enia.mp3player.presentation.views;

import de.hsrm.mi.enia.mp3player.presentation.uicomponents.ControlPane;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
//import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class PlayerView extends BorderPane {
	
	Label titleLabel;
	ControlPane controlPane;
	
	public PlayerView() {
	
		VBox header = new VBox();
		header.setId("header");
		
	// Layout-Eigenschaften für Header setzen
		header.setAlignment(Pos.CENTER);  // Zentriert den Inhalt
		header.setPadding(new Insets(20, 10, 20, 10));  // Abstand: oben, rechts, unten, links
		header.setSpacing(10);  // Abstand zwischen Kinder-Elementen
		
		titleLabel = new Label("Player View");
		titleLabel.getStyleClass().add("main-text");
		
		header.getChildren().addAll(titleLabel);
				
		controlPane = new ControlPane();
		
	// Layout-Eigenschaften für ControlPane
		controlPane.setPadding(new Insets(10, 10, 20, 10));
		
		this.setTop(header);
		this.setBottom(controlPane);
		
	// Hintergrundfarbe kann später per CSS überschrieben werden
		this.setPadding(new Insets(0));
	}

}
