package unsw.dungeon.Application;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import unsw.dungeon.*;
import unsw.dungeon.Entities.*;

/**
 * A DungeonLoader that also creates the necessary ImageViews for the UI,
 * connects them via listeners to the model, and creates a controller.
 * @author Robert Clifton-Everest
 *
 */
public class DungeonControllerLoader extends DungeonLoader {

    private List<ImageView> entities;

    //Images
    private Image playerImage;
    private Image wallImage;
    private Image enemy1Image; // new addition, adds enemy.
    private Image boulderImage;
    private Image switchImage;
    private Image portalImage;
    private Image invincibilityImage;
    private Image exitImage;
    private Image swordImage;
    private Image treasureImage;
    private Image doorImage;
    private Image keyImage;
    private Image fireTrapImage;
    private Image fireImage;
    private Image houndImage;
    private Image longSwordImage;
    private Image maceImage;

    public DungeonControllerLoader(String filename)
            throws FileNotFoundException {
        super(filename);
        entities = new ArrayList<>();
        playerImage = new Image("/human_new.png");
        wallImage = new Image("/brick_brown_0.png");
        enemy1Image = new Image("/deep_elf_master_archer.png");
        boulderImage = new Image("/boulder.png");
        switchImage = new Image("/pressure_plate.png");
        portalImage = new Image("/portal.png");
        invincibilityImage = new Image("/brilliant_blue_new.png");
        exitImage = new Image("/exit.png");
        swordImage = new Image("/greatsword_1_new.png");
        treasureImage = new Image("/gold_pile.png");
        doorImage = new Image("/closed_door.png");
        keyImage = new Image("/key.png");
        fireTrapImage = new Image("/fire_trap.png");
        fireImage = new Image("/fire.png");
        houndImage = new Image("/hound.png");
        longSwordImage = new Image("/longsword.png");
        maceImage = new Image("/mace.png");
    }

    @Override
    public void onLoad(Entity player) {
        ImageView view = new ImageView(playerImage);
        addEntity(player, view);
    }

    @Override
    public void onLoad(Wall wall) {
        ImageView view = new ImageView(wallImage);
        addEntity(wall, view);
    }
    
    @Override
    public void onLoad(Henchman henchman) {
    	ImageView view = new ImageView(enemy1Image);
        addEntity(henchman, view);
    }
    
    @Override
    public void onLoad(Boulder boulder) {
    	ImageView view = new ImageView(boulderImage);
    	addEntity(boulder, view);
    }
    
    @Override
    public void onLoad(FloorSwitch fSwitch) {
    	ImageView view = new ImageView(switchImage);
    	addEntity(fSwitch, view);
    }
    
    @Override
    public void onLoad(Portal portal) {
        ImageView view = new ImageView(portalImage);
        addEntity(portal, view);
    }
    @Override
    public void onLoad(Invincibility invincibility) {
        ImageView view = new ImageView(invincibilityImage);
        addEntity(invincibility, view);
    }
    
    @Override
    public void onLoad(Exit exit) {
        ImageView view = new ImageView(exitImage);
        addEntity(exit, view);
    }
    
    @Override
    public void onLoad(Sword sword) {
        ImageView view = new ImageView(swordImage);
        addEntity(sword, view);
    }
    
    @Override
    public void onLoad(LongSword longSword) {
        ImageView view = new ImageView(longSwordImage);
        addEntity(longSword, view);
    }
    
    @Override
    public void onLoad(Mace mace) {
        ImageView view = new ImageView(maceImage);
        addEntity(mace, view);
    }
    
    @Override
    public void onLoad(Treasure treasure) {
        ImageView view = new ImageView(treasureImage);
        addEntity(treasure, view);
    }
    
    @Override
    public void onLoad(Door door) {
        ImageView view = new ImageView(doorImage);
        addEntity(door, view);
    }
    
    @Override
    public void onLoad(Key key) {
        ImageView view = new ImageView(keyImage);
        addEntity(key, view);
    }
    
    @Override
    public void onLoad(FireTrap fireTrap) {
    	ImageView view = new ImageView(fireTrapImage);
    	addEntity(fireTrap, view);
    }
    
    @Override
    public void onLoad(Fire fire) {
    	ImageView view = new ImageView(fireImage);
    	addEntity(fire, view);
    }
    
    @Override
    public void onLoad(Hound hound) {
    	ImageView view = new ImageView(houndImage);
    	addEntity(hound, view);
    }

    private void addEntity(Entity entity, ImageView view) {
        trackPosition(entity, view);
        entity.setEntityView(view);
        entities.add(view);
    }
    
    

    /**
     * Set a node in a GridPane to have its position track the position of an
     * entity in the dungeon.
     *
     * By connecting the model with the view in this way, the model requires no
     * knowledge of the view and changes to the position of entities in the
     * model will automatically be reflected in the view.
     * @param entity
     * @param node
     */
    private void trackPosition(Entity entity, Node node) {
        GridPane.setColumnIndex(node, entity.getX());
        GridPane.setRowIndex(node, entity.getY());
        entity.x().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable,
                    Number oldValue, Number newValue) {
                GridPane.setColumnIndex(node, newValue.intValue());
            }
        });
        entity.y().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable,
                    Number oldValue, Number newValue) {
                GridPane.setRowIndex(node, newValue.intValue());
            }
        });
    }

    /**
     * Create a controller that can be attached to the DungeonView with all the
     * loaded entities.
     * @return
     * @throws FileNotFoundException
     */
    public DungeonController loadController() throws FileNotFoundException {
        return new DungeonController(load(), entities);
    }
    



}
