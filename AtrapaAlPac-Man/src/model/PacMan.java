package model;

import java.io.Serializable;

public class PacMan implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * 
	 */
	private double radio;
	/**
	 * 
	 */
	private double posX;
	/**
	 * 
	 */
	private double posY;
	/**
	 * 
	 */
	private int delay;
	/**
	 * 
	 */
	private char direction;
	/**
	 * 
	 */
	private int rebounds;
	/**
	 * 
	 */
	private boolean status;
	/**
	 * 
	 */
	public final static char RIGHT = 'R';
	/**
	 * 
	 */
	public final static char LEFT = 'L';
	/**
	 * 
	 */
	public final static char UPWARD = 'U';
	/**
	 * 
	 */
	public final static char DOWNWARD = 'D';
	
	public final static int MOVEMENT = 3;
	/**
	 * 
	 */
	public PacMan(double radio, double posX, double posY, int delay, 
			char direction, int rebounds, boolean status) {
		this.radio = radio;
		this.posX = posX;
		this.posY = posY;
		this.delay = delay;
		this.direction = direction;
		this.rebounds = rebounds;
		this.status = status;
		
	}
	
	/**
	 * 
	 */
	public void moved() {
		if(direction == RIGHT) {
			posX += MOVEMENT;
		}else if(direction == LEFT) {
			posX -= MOVEMENT;
		}else if(direction == UPWARD) {
			posY -= MOVEMENT;
		}else if(direction == DOWNWARD) {
			posY += MOVEMENT;
		}
	}
	
	/**
	 * 
	 */
	public String toString() {
		return radio+"\t"+posX+"\t"+posY+"\t"+delay+"\t"+direction+"\t"+rebounds+"\t"+status;
	}
	
	//GET SET
	
	/**
	 * 
	 */
	public double getRadio() {
		return radio;
	}
	/**
	 * 
	 */
	public double getPosX() {
		return posX;
	}
	
	/**
	 * 
	 */
	public void setPosX(double posX) {
		this.posX = posX;
	}
	
	/**
	 * 
	 */
	public double getPosY() {
		return posY;
	}
	/**
	 * 
	 */
	public void setPosY(double posY) {
		this.posY = posY;
	}
	
	/**
	 * 
	 */
	public int getDelay() {
		return delay;
	}
	
	/**
	 * 
	 */
	public char getDirection() {
		return direction;
	}
	/**
	 * 
	 */
	public void setDirection() {
		if(direction == RIGHT) {
			direction = LEFT;
		}else if(direction == LEFT) {
			direction = RIGHT;
		}else if(direction == UPWARD) {
			direction = DOWNWARD;
		}else if(direction == DOWNWARD) {
			direction = UPWARD;
		}
		rebounds++;
	}
	
	/**
	 * 
	 */
	public int getRebounds() {
		return rebounds;
	}
	/**
	 * 
	 */
	public void setRebounds(int rebounds) {
		this.rebounds = rebounds;
	}
	
	/**
	 * 
	 */
	public boolean getStatus() {
		return status;
	}
	/**
	 * 
	 */
	public void setStatus(boolean status) {
		this.status = status;
	}
	

}
