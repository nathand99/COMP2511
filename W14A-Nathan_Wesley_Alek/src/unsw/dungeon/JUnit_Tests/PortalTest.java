package unsw.dungeon.JUnit_Tests;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import unsw.dungeon.*;
import unsw.dungeon.Application.Dungeon;
import unsw.dungeon.Entities.*;

public class PortalTest {
	@Test
	// test player goes through portal 
	void testGoIntoPortal() {
		Dungeon dungeon = new Dungeon(10,10);								// initialise dungeon
		Player player = new Player(dungeon, 0, 0, new Moveable());
		dungeon.setPlayer(player);                                          // initialise player in the dungeon (at 0,0)
		Portal portal1a = new Portal(dungeon, 1, 0, 1, new Interactable());
		Portal portal1b = new Portal(dungeon, 9, 0, 1, new Interactable());
		dungeon.addEntity(portal1a);
		dungeon.addEntity(portal1b);
		
		player.moveRight();						// player moves down 1 space
		try {
		    Thread.sleep(400);
		} catch(InterruptedException ex) {
		    Thread.currentThread().interrupt();
		}
		assertEquals(player.getX(), portal1b.getX());
		assertEquals(player.getY(), portal1b.getY());
		
	}
	
	@Test
	// test go through portal and come back 
	void testGoIntoPortalAndComeBack() {
		Dungeon dungeon = new Dungeon(10,10);								// initialise dungeon
		Player player = new Player(dungeon, 0, 0, new Moveable());
		dungeon.setPlayer(player);                                          // initialise player in the dungeon (at 0,0)
		Portal portal1a = new Portal(dungeon, 1, 0, 1, new Interactable());
		Portal portal1b = new Portal(dungeon, 9, 0, 1, new Interactable());
		dungeon.addEntity(portal1a);
		dungeon.addEntity(portal1b);
		
		player.moveRight();						// player moves down 1 space
		try {
		    Thread.sleep(400);
		} catch(InterruptedException ex) {
		    Thread.currentThread().interrupt();
		}
		assertEquals(player.getX(), portal1b.getX());
		assertEquals(player.getY(), portal1b.getY());
		
		player.moveLeft();						// player moves down 1 space
		try {
		    Thread.sleep(400);
		} catch(InterruptedException ex) {
		    Thread.currentThread().interrupt();
		}
		
		assertEquals(player.getX(), 8);
		assertEquals(player.getY(), 0);
		
		player.moveRight();						// player moves down 1 space
		try {
		    Thread.sleep(400);
		} catch(InterruptedException ex) {
		    Thread.currentThread().interrupt();
		}
		assertEquals(player.getX(), portal1a.getX());
		assertEquals(player.getY(), portal1a.getY());
	}
	@Test
	// test player goes through right portal and comes back right portal 
	void testCorrectPortal() {
		Dungeon dungeon = new Dungeon(10,10);								// initialise dungeon
		Player player = new Player(dungeon, 0, 0, new Moveable());
		dungeon.setPlayer(player);                                          // initialise player in the dungeon (at 0,0)
		Portal portal1a = new Portal(dungeon, 1, 0, 1, new Interactable());
		Portal portal1b = new Portal(dungeon, 9, 0, 1, new Interactable());
		Portal portal2a = new Portal(dungeon, 1, 1, 2, new Interactable());
		Portal portal2b = new Portal(dungeon, 9, 1, 2, new Interactable());
		dungeon.addEntity(portal1a);
		dungeon.addEntity(portal1b);
		dungeon.addEntity(portal2a);
		dungeon.addEntity(portal2b);
		
		player.moveRight();						// player moves right 1 space
		try {
		    Thread.sleep(400);
		} catch(InterruptedException ex) {
		    Thread.currentThread().interrupt();
		}
		assertEquals(player.getX(), portal1b.getX());
		assertEquals(player.getY(), portal1b.getY());
		
		player.moveLeft();						// player moves left 1 space
		try {
		    Thread.sleep(400);
		} catch(InterruptedException ex) {
		    Thread.currentThread().interrupt();
		}
		
		assertEquals(player.getX(), 8);
		assertEquals(player.getY(), 0);
		
		player.moveRight();						// player moves right 1 space
		try {
		    Thread.sleep(400);
		} catch(InterruptedException ex) {
		    Thread.currentThread().interrupt();
		}
		assertEquals(player.getX(), portal1a.getX());
		assertEquals(player.getY(), portal1a.getY());
		
		player.moveDown();						// player moves down 1 space
		try {
		    Thread.sleep(400);
		} catch(InterruptedException ex) {
		    Thread.currentThread().interrupt();
		}
		assertEquals(player.getX(), portal2b.getX());
		assertEquals(player.getY(), portal2b.getY());
		
		player.moveDown();						// player moves down 1 space
		try {
		    Thread.sleep(400);
		} catch(InterruptedException ex) {
		    Thread.currentThread().interrupt();
		}
		player.moveUp();						// player moves down 1 space
		try {
		    Thread.sleep(400);
		} catch(InterruptedException ex) {
		    Thread.currentThread().interrupt();
		}
		assertEquals(player.getX(), portal2a.getX());
		assertEquals(player.getY(), portal2a.getY());
	
	}
	
	@Test
	//can't push movable into portal
	void testPushEntity() {
		Dungeon dungeon = new Dungeon(10,10);								// initialise dungeon
		Player player = new Player(dungeon, 0, 0, new Moveable());
		dungeon.setPlayer(player);                                          // initialise player in the dungeon (at 0,0)
		Portal portal1a = new Portal(dungeon, 2, 0, 1, new Interactable());
		Portal portal1b = new Portal(dungeon, 9, 0, 1, new Interactable());
		Boulder boulder = new Boulder(dungeon, 1, 0, new Interactable());
		dungeon.addEntity(portal1a);
		dungeon.addEntity(portal1b);
		dungeon.addEntity(boulder);	
		
		player.moveRight();						// player moves down 1 space
		try {
		    Thread.sleep(400);
		} catch(InterruptedException ex) {
		    Thread.currentThread().interrupt();
		}
		assertEquals(boulder.getX(),1);
		assertEquals(boulder.getY(),0);
		
	}
}
