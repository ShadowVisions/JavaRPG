package main;

import java.util.ArrayList;

/**
 * Event class for battle and shop interactions
 * @author Tyler Deaton
 *
 */
public class Event {
	/** Name of event time */
	String name;
	String message;
	ArrayList<Enemy> enemy;
	Treasure t;
	PostEvent p;
	/**
	 * Default constructor
	 * @param name event name
	 * @param level event level
	 */
	public Event(String name, Enemy e) {
		this.name = name;
		this.message = "";
		
		
		this.enemy = new ArrayList<Enemy>();
		enemy.add(e);
		this.t = null;
	}
	public Event(String name, String message) {
		this.name = name;
		this.message = message;
		this.enemy = null;
		this.t = null;
	}
	public Event(String name, ArrayList<Enemy> e) {
		this.name = name;
		this.message = "";
		this.enemy = e;
		this.t = null;
	}
	public Event(String name, Treasure t) {
		this.name = name;
		this.message = "";
		this.enemy = null;
		this.t = t;
	}
	public void setPostEvent(PostEvent p) {
		this.p = p;
	}
	

}
