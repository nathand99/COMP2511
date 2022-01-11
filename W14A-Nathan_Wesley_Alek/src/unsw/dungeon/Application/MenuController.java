package unsw.dungeon.Application;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

/**
 * A controller for the Menu.
 * 
 *
 */
public class MenuController {
	@FXML
	private GridPane levelGrid;
	@FXML
	private Button helpButton;
	@FXML
	private Button quitButton;
	
	private DungeonScreen dungeonScreen;
	private TutorialScreen tutorialScreen;
	
	/**
	 * Will create buttons for the GridPane to go to specific levels.
	 */
	@FXML
	public void initalize() {
		int level;
		for (int i = 0; i < levelGrid.getRowCount(); i++) {
			for (int j = 0; j < levelGrid.getColumnCount(); j++) {
				String levelName = new String();
				level = j + i*5 + 1;
				levelName = nameLevel(level);
				Button levelButton = new Button(levelName);
				final int levelF = level;
				levelButton.setOnAction(new EventHandler<ActionEvent>() {
					@Override public void handle(ActionEvent e) {
				        try {
							dungeonScreen.start(levelF);
						} catch (IOException error) {
							error.printStackTrace();
						}
				    }
				});
				levelGrid.add(levelButton, j, i);
			}
		}
	}
	
	/**
	 * Names the levels
	 * @param level
	 * @return
	 */
	private String nameLevel(int level) {
		String name;
		if (level <= 3) {
			name = "Exit " + String.valueOf(level);
		} else if (level <= 6) {
			name = "Treasure " + String.valueOf(level - 3);
		} else if (level <= 9) {
			name = "Boulder " + String.valueOf(level - 6);
		} else if (level <= 12) {
			name = "Enemy " + String.valueOf(level - 9);
		} else if (level <= 15) {
			name = "And Goal " + String.valueOf(level - 12);
		} else if (level <= 18) {
			name = "Or Goal " + String.valueOf(level - 15);
		} else {
			name = "Wildcard " + String.valueOf(level - 18);
		}
		return name;
	}

	public void setDungeonScreen(DungeonScreen dungeonScreen) {
		this.dungeonScreen = dungeonScreen;
	}
	
	public void setTutorialScreen(TutorialScreen tutorialScreen) {
		this.tutorialScreen = tutorialScreen;
	}
	
	/**
	 * Goes to the tutorialPage.
	 * @throws IOException
	 */
	@FXML
	public void tutorialPage() throws IOException {
		tutorialScreen.start();
	}
	
	/**
	 * Quits the application.
	 */
	public void quitGame() {
		Stage stage = (Stage) quitButton.getScene().getWindow();
	    stage.close();
	}
}
