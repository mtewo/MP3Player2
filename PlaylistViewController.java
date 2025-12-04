package de.hsrm.mi.enia.mp3player.presentation.views;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;

public class PlaylistViewController {

	PlaylistView root;
	
	
	public PlaylistViewController() {
		root = new PlaylistView();
		
		
		inititialize();
	}
	
	public void inititialize() {
		
		
	}
	
	public Pane getRoot() {
		return root;
	}
}
