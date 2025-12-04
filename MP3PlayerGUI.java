package de.hsrm.mi.enia.mp3player.presentation;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.HashMap;
import java.util.Map;


import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javazoom.jl.player.Player;
import de.hsrm.mi.enia.mp3player.business.MP3Player;
import de.hsrm.mi.enia.mp3player.presentation.views.BaseController;
import de.hsrm.mi.enia.mp3player.presentation.views.PlayerView;
import de.hsrm.mi.enia.mp3player.presentation.views.PlayerViewController;
import de.hsrm.mi.enia.mp3player.presentation.views.PlaylistView;

/**
 * Hauptfenster für PlayerGUI, bettet die PlayerView
 * als erste Hauptsicht zur Steuerung des Players ein.
 */
public class MP3PlayerGUI extends Application { // extends Application heißt die Klasse ist eine JavaFX Anwendung 
	private static Stage stage;
	
	private static Map<String, Pane> views;
	
	private MP3Player player;
	
	@Override
	public void init() {
		// Initialisierung der Businessklassen
		views = new HashMap<>();
		
		player = new MP3Player();

	}
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		stage = primaryStage;
		
		BaseController playerViewController = new PlayerViewController(player);
		Pane playerView = playerViewController.getRoot();
		views.put("playerView", playerView);
		
		Scene scene = new Scene(playerView, 500, 400);
		scene.getStylesheets().addAll(getClass().getResource("style.css").toExternalForm());
		primaryStage.setScene(scene);
		primaryStage.setTitle("MP3Player");
		primaryStage.show();
	}
	
	@Override
	public void stop() {
		// Beeneden moeglicher Prozesse - z.B. das Abspielen von Songs
		
	}
	
	public static void switchRoot(String view) {
		Scene scene = stage.getScene();
		
		Pane nextRoot = views.get(view);
		
		if (nextRoot != null) {
			scene.setRoot(nextRoot);
		}
	}

}
