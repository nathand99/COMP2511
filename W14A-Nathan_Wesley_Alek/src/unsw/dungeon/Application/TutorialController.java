package unsw.dungeon.Application;

import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class TutorialController {
	@FXML
	private Button backButton;
	
	private MenuScreen menuScreen;
	
	public void setMenuScreen(MenuScreen menuScreen) {
		this.menuScreen = menuScreen;
	}
	
	@FXML
	public void goToMenu() {
		menuScreen.start();
	}
}
