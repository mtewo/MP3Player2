package de.hsrm.mi.enia.mp3player.presentation.views;

import de.hsrm.mi.enia.mp3player.presentation.uicomponents.ControlPane;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class PlaylistView extends BorderPane {
	
	Label titleLabel;
	Button switchButton;
	
	ControlPane controlPane;
	
	public PlaylistView() {
	
		HBox header = new HBox();
		header.setId("header");

		
		switchButton = new Button("Switch");
		
		VBox headerText = new VBox();
		headerText.setId("header-text");
		titleLabel = new Label("Playlist View");
		titleLabel.getStyleClass().add("main-text");
		headerText.getChildren().addAll(titleLabel);
		
		
		
		header.getChildren().addAll(headerText, switchButton);
				
		controlPane = new ControlPane();
		
		this.setTop(header);
		this.setBottom(controlPane);
	}

}
