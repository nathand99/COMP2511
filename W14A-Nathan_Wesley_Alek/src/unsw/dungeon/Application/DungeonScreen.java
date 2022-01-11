package unsw.dungeon.Application;

import java.io.FileNotFoundException;
import java.io.IOException;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import unsw.dungeon.GoalReader;

public class DungeonScreen {
	private Stage stage;
	private Scene scene;
	private DungeonControllerLoader loader;
	private DungeonController controller;
	private MenuScreen menuScreen;

    public DungeonScreen(Stage primaryStage) throws IOException {
    	this.stage = primaryStage;
        //stage.setTitle("Dungeon");
       
        //stage.setScene(scene);
        //stage.show();

    }
    
    /**
     * Starts the dungeon
     * @throws IOException
     */
    public void start(int mapNo) throws IOException {
    	String mapNumber = String.valueOf(mapNo);
    	stage.setTitle("Dungeon");
    	String map = "dungeons/" + mapNumber + ".json"; // need to add file string and the .json
        loader = new DungeonControllerLoader(map);
        controller = loader.loadController();
        controller.setMenuScreen(menuScreen);
        controller.setDungeonScreen(this);
        controller.setMapNumber(mapNo);
        // new
        GoalReader goal = controller.readGoal(map);
        goal.loadGoal();

        FXMLLoader loader = new FXMLLoader(getClass().getResource("DungeonView2.fxml"));
        loader.setController(controller);
        Parent root = loader.load();
        controller.updateGoal();
        scene = new Scene(root);
        root.requestFocus();
    	stage.setScene(scene);
    	stage.show();
    }
    
    // most likely will need a stop method, so that you can't lose while you're in the menu.
    // something like unload?
    
    public DungeonControllerLoader getLoader() {
    	return loader;
    }
    
    public DungeonController getController() {
    	return controller;
    }
    
    /**
     * Sets the MenuScreen
     * @param menuScreen
     */
    public void setMenuScreen(MenuScreen menuScreen) {
    	this.menuScreen = menuScreen;
    }
}
