package de.hsrm.mi.enia.mp3player.presentation.views;

import javafx.scene.layout.Pane;

// Hallo Leute
//hhhjj
// Hallooooo

public abstract class BaseController<T extends Pane> {
	T root;

	public abstract void initialize();
	
	public T getRoot() {
		return root;
	}
	

}
