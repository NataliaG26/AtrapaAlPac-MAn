package controller;

import threads.ThreadUpdater;

import java.io.File;
import java.io.FileNotFoundException;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Arc;
import javafx.scene.shape.ArcType;
import javafx.stage.FileChooser;
import model.SceneGame;

public class Controller{

	@FXML
	private BorderPane borderPane;
	
	@FXML
    private Pane pane;
	
	private SceneGame sceneGame;
	
	@FXML
    private Label counterTxt;
	
	@FXML
    private TextField nameTxF;
	
	/**
	 * 
	 */
	void paint() {
		for (int i = 0; i < sceneGame.getPacMans().size(); i++) {
			double radio = sceneGame.getPacMans().get(i).getRadio();
			double posX = sceneGame.getPacMans().get(i).getPosX();
			double posY = sceneGame.getPacMans().get(i).getPosY();
			Arc arc = new Arc();
			arc.setCenterX(posX);
			arc.setCenterY(posY);
			arc.setRadiusX(radio);
			arc.setRadiusY(radio);
			arc.setStartAngle(45);
			arc.setLength(290);
			arc.setType(ArcType.ROUND);
			arc.setFill(Color.YELLOW);
			pane.getChildren().add(arc);
		}
		
	}
	
	public void actualize() {
		for (int i = 0; i < sceneGame.getPacMans().size(); i++) {
			double posX = sceneGame.getPacMans().get(i).getPosX();
			double posY = sceneGame.getPacMans().get(i).getPosY();
			((Arc) pane.getChildren().get(i)).setCenterX(posX);
			((Arc) pane.getChildren().get(i)).setCenterY(posY);
		}
		counterTxt.setText(sceneGame.getCounter()+"");
		sceneGame.rebound();
	}
	
	@FXML
    void atrapaAlPacMan(MouseEvent event) {
		for (int i = 0; i < sceneGame.getPacMans().size(); i++) {
			double radio = sceneGame.getPacMans().get(i).getRadio();
			double posX = sceneGame.getPacMans().get(i).getPosX();
			double posY = sceneGame.getPacMans().get(i).getPosY();
			if(event.getX() < posX+radio && event.getX() > posX-radio && 
					event.getY() < posY+radio && event.getY() > posY-radio) {
				sceneGame.getPacMans().get(i).setStatus(true);
			}
		}
    }
	
	@FXML
	/**
	 * 
	 */
    void aboutGame(ActionEvent event) {
		Alert al = new Alert(Alert.AlertType.INFORMATION);
		al.setContentText("ATRAPA AL PAC-MAN\n\nEs un juego que consiste en detenere los Pac-Man que aparecen en la pantalla haciendo clic sobre ellos\n\nCreador: Natalia Gonzalez");
		al.show();
    }

    @FXML
    /**
	 * 
	 */
    void bestScores(ActionEvent event) {
    	sceneGame.loadHallDeLaFama();
    	Alert al = new Alert(Alert.AlertType.INFORMATION);
		al.setContentText(sceneGame.toStringHallDeLaFama());
		al.show();
    }

    @FXML
    /**
	 * 
	 */
    void exit(ActionEvent event) {
    	pane.getChildren().clear();
    	sceneGame.getPacMans().clear();
    	sceneGame.stopPacMans();
    	sceneGame.saveHallDeLaFama();
    	Platform.exit();
    }

    @FXML
    /**
	 * 
	 */
    void startGame(ActionEvent event){
    	pane.getChildren().clear();
    	FileChooser fileCh = new FileChooser();
    	fileCh.setInitialDirectory(new File("./resources"));
    	File f = fileCh.showOpenDialog(null);
    	ThreadUpdater hilo = new ThreadUpdater(this);
    	try{
    		sceneGame.loadGame(f);
			paint();
			sceneGame.movement();
			hilo.start();
			
			//
		}catch(Exception ioE){
			Alert al = new Alert(Alert.AlertType.INFORMATION);
			al.setTitle("problema");
			al.setContentText("Problemas leyendo el archivo\nEs probable que el formato no sea válido.");
			al.show();
		}
    	
    }
    
    public boolean stop() {
    	boolean stop = false;
    	if(pane.getChildren().isEmpty() || !sceneGame.pacMansStop()) {
    		stop = true;
    		sceneGame.newScore(nameTxF.getText());
    		sceneGame.loadHallDeLaFama();
    	}
    	return stop;
    }

    @FXML
    /**
	 * 
	 */
    void saveGame(ActionEvent event) throws FileNotFoundException {
    	String name = sceneGame.saveGame();
    	Alert al = new Alert(Alert.AlertType.INFORMATION);
		al.setContentText("Se ha guardado la el juego con este nombre: "+name);
		al.show();
    }
	
	
	/**
	 * 
	 */
    @FXML
	void initialize() {
		sceneGame = new SceneGame();
		pane.setMaxSize(sceneGame.getHeight(), sceneGame.getWidth());
		pane.setStyle("-fx-background-color: black;");
	
	}
}
