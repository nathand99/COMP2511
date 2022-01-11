package unsw.dungeon.Application;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import unsw.dungeon.GoalReader;

public class DungeonApplication extends Application {
	
	@Override
    public void start(Stage primaryStage) throws IOException {
        MenuScreen menuScreen = new MenuScreen(primaryStage);
        DungeonScreen dungeonScreen = new DungeonScreen(primaryStage);
        TutorialScreen tutorialScreen = new TutorialScreen(primaryStage);
        
        menuScreen.setDungeonScreen(dungeonScreen);
        menuScreen.setTutorialScreen(tutorialScreen);
        
        tutorialScreen.setMenuScreen(menuScreen);
        
        dungeonScreen.setMenuScreen(menuScreen);
        
        
        menuScreen.start();
    }


    public static void main(String[] args) {
        launch(args);
    }

}
