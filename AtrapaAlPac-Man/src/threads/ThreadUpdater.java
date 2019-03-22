package threads;
import controller.Controller;
import javafx.application.Platform;

public class ThreadUpdater extends Thread{

	private Controller controller;
	public ThreadUpdater(Controller p) {
		controller = p;
	}
	
	public void run() {
		
		while(!controller.stop()) {
			
			Platform.runLater(new Runnable(){
				@Override
				public void run() {
					controller.actualize();
				}
			});
			
			try {
				sleep(70);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
}
