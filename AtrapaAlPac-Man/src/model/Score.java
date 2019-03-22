package model;

import java.io.Serializable;

public class Score implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 4L;
	private String name;
	private int level;
	private int rebounds;
	
	public Score(String name, int level, int rebounds) {
		this.name = name;
		this.level = level;
		this.rebounds = rebounds;
	}
	
	public String getName() {
		return name;
	}
	
	public int getLevel() {
		return level;
	}
	
	public int getRebounds() {
		return rebounds;
	}
}
