package unsw.dungeon.Application;

import java.io.IOException;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class TutorialScreen {
	
	private Stage stage;
	private Scene scene;
	private TutorialController controller;
	
	public TutorialScreen(Stage primaryStage) throws IOException {
		this.stage = primaryStage;
	    //stage.setTitle("Game Menu");
	    
	    controller = new TutorialController();
	    FXMLLoader loader = new FXMLLoader(getClass().getResource("Tutorial.fxml"));
	    loader.setController(controller);

	    Parent root = loader.load();
	    this.scene = new Scene(root);

	    //stage.setScene(scene);
	    //stage.show();
	}
	
	public void start() {
		stage.setTitle("Tutorial");
		stage.setScene(scene);
		
		stage.show();
		
	}
	
	public TutorialController getController() {
		return this.controller;
	}
	
	/**
	 * Sets the MenuScreen for the Controller
	 * @param menuScreen
	 */
	public void setMenuScreen(MenuScreen menuScreen) {
		controller.setMenuScreen(menuScreen);
	}
}
