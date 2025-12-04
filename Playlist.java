package de.hsrm.mi.enia.mp3player.business;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Playlist {
	private String name;
    private List<Track> playlist = new ArrayList<>();

    public Playlist(String name) { 
    	this.name = name;
    }
    public void addTrack(Track t) { 
    	if (t != null) playlist.add(t); 
    }
    public void removeTrack(Track t) { 
    	playlist.remove(t); 
    }
    public List<Track> getTracks() { 
    	return Collections.unmodifiableList(playlist);
    }
    public String getName() {
    	return name; 
    }
    public int size() {
    	return playlist.size(); 
    }

}
