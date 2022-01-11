package unsw.dungeon.Entities;

import java.util.Timer;
import java.util.TimerTask;

import unsw.dungeon.Entity;
import unsw.dungeon.Movement;
import unsw.dungeon.Application.Dungeon;

public class FireTrap extends Entity {
	
	private Dungeon dungeon;
	private Player player;
	private Fire fire; // each fireTrap has a fire assigned to it.
	private Timer t;
	private int timeGap; // fireTrap must have a timeGap
	
	public FireTrap(Dungeon dungeon, int x, int y, int timeGap, Movement movement) {
		super(x, y, movement);
		this.dungeon = dungeon;
		this.timeGap = timeGap;
		this.setEntityName("fireTrap");
		t = new Timer();
	}
	
	/**
	 * Starts the timer for the fireTrap to create fires.
	 */
	public void start() {
		// TODO Auto-generated method stub
		this.player = dungeon.getPlayer();
		fire.setPlayer(player);
		fire.deactivate();
		// System.out.println("Player X "+ player.getX());
		
		t.schedule(new TimerTask() {

            @Override
            public void run() {
            	createFire();
            	

            }
        }, timeGap, timeGap);
	}
	
	/**
	 * Stops the timer for the fireTrap
	 */
	public void stop() {
		fire.deactivate();
		t.cancel();
	}
	
	/**
	 * Makes a fire on the fireTrap.
	 */
	public void createFire() {
		// create fire
		// wait a little bit
		// destroy the fire.
		fire.activate();
	}

	public Fire getFire() {
		return fire;
	}

	public void setFire(Fire fire) {
		this.fire = fire;
	}
}
