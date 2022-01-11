package unsw.automata;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.layout.GridPane;
import javafx.util.Duration;

public class GameOfLifeController {
	
	private GameOfLife game;
	private Timeline timeline;
	
	@FXML
    private GridPane gridpane;

    @FXML
    private Button play_button;

    @FXML
    private Button tick_button;

    @FXML
    void play(ActionEvent event) {
    	if (play_button.getText().equals("Play")) {
    		play_button.setText("Stop");
    		
    		timeline = new Timeline();
    		timeline.setCycleCount(Animation.INDEFINITE);
    		timeline.getKeyFrames().add(new KeyFrame(Duration.seconds(0.5), new EventHandler<ActionEvent>() {
    			public void handle(ActionEvent event) {
    				tick();
    			}
    		}));
    		timeline.play();
    		
    	} else {
    		play_button.setText("Play");
    		timeline.stop();
    	}
    }

    @FXML
    void tick() {
    	game.tick();
    }
    
    @FXML
    public void initialize() {
    	game = new GameOfLife();
    	for (int i = 0; i < 10; i++) {
      	     for (int j = 0; j < 10; j++) {
      	    	CheckBox checkbox1 = new CheckBox();
      	    	gridpane.add(checkbox1, i, j);
      	    	checkbox1.selectedProperty().bindBidirectional(game.cellProperty(i, j));
      	     }
      	}
        
    }


}
