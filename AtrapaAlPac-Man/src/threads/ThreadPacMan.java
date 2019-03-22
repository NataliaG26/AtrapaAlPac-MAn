package threads;

import model.PacMan;

public class ThreadPacMan extends Thread{
	
	/**
	 * 
	 */
	private PacMan pacMan;
	
	/**
	 * 
	 */
	public ThreadPacMan(PacMan pacMan) {
		this.pacMan = pacMan;
	}
	
	/**
	 * 
	 */
	public void run() {
		while(!pacMan.getStatus()) {
			pacMan.moved();
			try {
				sleep(pacMan.getDelay());
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
