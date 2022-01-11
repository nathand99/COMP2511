package unsw.dungeon.Application;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.sound.sampled.AudioSystem;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ToolBar;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import unsw.dungeon.Entity;
import unsw.dungeon.GoalReader;
import unsw.dungeon.PickupItem;
import unsw.dungeon.PlayerGoal;
import unsw.dungeon.Weapons;
import unsw.dungeon.Entities.*;
import unsw.dungeon.SoundEffects;

/**
 * A JavaFX controller for the dungeon.
 * @author Robert Clifton-Everest
 *
 */
public class DungeonController {

	
	// this stuff comes first in the loading sequence.
    @FXML
    private GridPane squares;
    
    @FXML
    private Pane pane;

    private List<ImageView> initialEntities;

    private Player player;

    private Dungeon dungeon;
    
    private int mapNumber;
    
    @FXML
    private Button menuButton;
    @FXML
    private Button restartButton;   
    @FXML 
    private Pane header;
    @FXML 
    private Pane goals_pane;
    @FXML
    private ImageView treasureImage;
    @FXML
    private ImageView keyImage;
    @FXML
    private ImageView weaponImage;
    @FXML
    private Label treasureCount;
    @FXML
    private Label keyID;
    @FXML
    private Label weaponDurability;
    @FXML
    private Label goalID;
    
    private MenuScreen menuScreen;
    private DungeonScreen dungeonScreen;
    private boolean registered = false;

    public DungeonController(Dungeon dungeon, List<ImageView> initialEntities) {
        this.dungeon = dungeon;
        this.player = dungeon.getPlayer();
        this.initialEntities = new ArrayList<>(initialEntities);
        player.setController(this);
        
    }
    
    /**
     * Takes user back to the main menu
     */
    public void goToMenu() {
		dungeon.deregisterAll();
		menuScreen.start();
	}
    
    /**
     * Restarts the current level
     * @throws IOException
     */
    public void restart() throws IOException {
    	dungeon.deregisterAll();
		dungeonScreen.start(mapNumber);
	}
    
    /**
     * Goes to the next level.
     * @throws IOException
     */
    public void nextLevel() throws IOException {
    	dungeon.deregisterAll();
    	if (mapNumber == 20) {
    		goToMenu();
    		return;
    	}
		dungeonScreen.start(mapNumber + 1);
	}

    @FXML
    public void initialize() {
        Image ground = new Image("/dirt_0_new.png");
        
        
        // Add the ground first so it is below all other entities
        for (int x = 0; x < dungeon.getWidth(); x++) {
            for (int y = 0; y < dungeon.getHeight(); y++) {
                squares.add(new ImageView(ground), x, y);
            }
        }
        
        // set the header, goal_pane and pane width as the width of the dungeon everytime it updates
        // move the quit button 63 px from the right edge of the header (which has width = dungeon width)
        squares.widthProperty().addListener((obs, oldVal, newVal) -> {
        	header.setPrefWidth(newVal.doubleValue());
            header.autosize();
            goals_pane.setPrefWidth(newVal.doubleValue());
            goals_pane.autosize();
            pane.setPrefSize(newVal.doubleValue(), squares.getMinHeight());
            pane.autosize();
            // tweaked some of the values.
            menuButton.setLayoutX(newVal.doubleValue() - 63);
            
            goalID.setMinWidth(newVal.doubleValue());
        });
        
        // move restartButton 75 px to the left of where quit button ends up
        // restart button is 65px
        menuButton.layoutXProperty().addListener((obs, oldVal, newVal) -> {
        	restartButton.setLayoutX(newVal.doubleValue() - 75);
        });  
        
        // sets the visibility of weapon, treasure and key to false in the inventory bar.
        treasureImage.setVisible(false);
        keyImage.setVisible(false);
        weaponImage.setVisible(false);
        
        for (ImageView entity : initialEntities) {
            squares.getChildren().add(entity);
        }
        player.toFront();
    }
    
    /**
     * readGoal - runs the GoalReader
     * @param map
     * @return
     * @throws FileNotFoundException
     */
    // move to dungeonApplication maybe.
    public GoalReader readGoal(String map) throws FileNotFoundException {
    	return new GoalReader(new PlayerGoal(), dungeon.getPlayer(), map, dungeon.getEntities());
    } 

    @FXML
    public void handleKeyPress(KeyEvent event) {
        switch (event.getCode()) {
        case UP:
            player.moveUp();
            register();
            break;
        case DOWN:
            player.moveDown();
            register();
            break;
        case LEFT:
            player.moveLeft();
            register();
            break;
        case RIGHT:
            player.moveRight();
            register();
            break;
        case W:
        	player.attackW();
        case A:
        	player.attackA();
        case S:
        	player.attackS();
        case D:
        	player.attackD();        	
        default:
            break;
        }
        
        if(!player.isNormalState()) {
        	DropShadow ds= new DropShadow();
			ds.setRadius(17.0);
			ds.setColor(Color.color(0, 0.3, 1));
			player.getEntityView().setEffect(ds);
        }else {
        	player.getEntityView().setEffect(null);
        }
        
    }
    
    /**
     * Registers the dungeon entities if it has not been registered.
     */
    public void register() {
    	if (registered == false) {
    		dungeon.registerAll();
    		registered = true;
    	}
    }
    
    public void setMenuScreen(MenuScreen menuScreen) {
    	this.menuScreen = menuScreen;
    }
    
    public void setDungeonScreen(DungeonScreen dungeonScreen) {
    	this.dungeonScreen = dungeonScreen;
    }
    
    /**
     * Adds an item to inventory, and frontend matches this.
     * @param item
     */
    public void invAdd(PickupItem item) {
    	// must update for weapon if extra weapons come out.
    	if (((Entity)item).getEntityName().equals("key")) {
    		keyImage.setVisible(true);
    		keyID.setText(String.valueOf(player.getKeyID()));
    	} else if (((Entity)item).getEntityName().equals("treasure")) {
    		treasureImage.setVisible(true);
    		treasureCount.setText(String.valueOf(player.getTreasure()));
    	} else if (((Entity)item).getEntityName().equals("weapon")){
    		// add new code for different weapons here.
    		weaponImage.setImage(player.getWeaponView().getImage());
    		weaponImage.setVisible(true);
    		weaponDurability.setText(String.valueOf(player.getWeaponDurability()));
    	}
    }
    
    /**
     * Sets the weapon durability again, to update. Removes image if durability hits 0.
     */
    public void setWeaponDurability() {
    	int durability = player.getWeaponDurability();
    	if (durability <= 0) {
    		weaponImage.setVisible(false);
    	}
    	weaponDurability.setText(String.valueOf(durability));
    }
    
    /**
     * removes the Key from inventory to match backend.
     */
    public void removeKey() {
    	keyImage.setVisible(false);
    	keyID.setText("0");
    }
    
    /**
     * Updates the current goal.
     */
    public void updateGoal() {
    	PlayerGoal goal = player.getGoals();
    	goalID.setText(goal.returnStringGoals());
    }

	public int getMapNumber() {
		return mapNumber;
	}

	public void setMapNumber(int mapNumber) {
		this.mapNumber = mapNumber;
	}
}

