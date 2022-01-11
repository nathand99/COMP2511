package unsw.dungeon.Old_Tests;

import unsw.dungeon.*;
import unsw.dungeon.Application.Dungeon;
import unsw.dungeon.Entities.*;

public class TestDoor {
	public static void main(String[] args) {
		Dungeon dungeon = new Dungeon(10,10);
		Player player = new Player(dungeon, 0, 0, new Moveable());
		
		// test with key and door with same ID - door should unlock
		System.out.println("--------------test 1----------------");
		System.out.println("player starting at: " + player.getX() + "," + player.getY());
		Key key = new Key(dungeon, 0, 1, 1, new Collectable());
		dungeon.addEntity(key);
		Door door = new Door(dungeon, 0, 2, 1, new Interactable());
		dungeon.addEntity(door);

		player.moveDown();
		System.out.println("move down");
		if (player.getKey() == null) {
			System.out.println("i have no key");
		}
		if (player.getKey()  != null) {
			System.out.println("got key with ID:" + player.getKey().getkeyID());
		}
		
		try
		{
		    Thread.sleep(800);
		}
		catch(InterruptedException ex)
		{
		    Thread.currentThread().interrupt();
		}
		player.moveDown();
		System.out.println("move down");
		
		// player should have unlocked the door and be standing on the square of the door
		System.out.println("door is at: " + door.getX() + "," + door.getY());
		System.out.println("player is at: " + player.getX() + "," + player.getY());
		
		System.out.println("player is standing on unlocked door");
		
		// players key has been used by the door
		if (player.getKey() == null) {
			System.out.println("my key is gone");
		}
		
		//test 2 with key and door having DIFFERENT id (key has id 2, door has id 1)
		System.out.println("--------------test 2----------------");
		Key key2 = new Key(dungeon, 0, 3, 2, new Collectable());
		dungeon.addEntity(key2);
		Door door2 = new Door(dungeon, 0, 4, 1, new Interactable());
		dungeon.addEntity(door2);
		
		System.out.println("player starting at: " + player.getX() + "," + player.getY());
		
		try
		{
		    Thread.sleep(800);
		}
		catch(InterruptedException ex)
		{
		    Thread.currentThread().interrupt();
		}
		player.moveDown();
		System.out.println("move down");
		if (player.getKey()  == null) {
			System.out.println("i have no key");
		}
		if (player.getKey()  != null) {
			System.out.println("got key with ID:" + player.getKey().getkeyID());
		} else {
			System.out.println("i still have no key");
		}
		
		try
		{
		    Thread.sleep(800);
		}
		catch(InterruptedException ex)
		{
		    Thread.currentThread().interrupt();
		}
		player.moveDown();
		System.out.println("move down");
		
		// player should have unlocked the door and be standing on the square of the door
		System.out.println("player is at: " + player.getX() + "," + player.getY());
		System.out.println("door is at: " + door2.getX() + "," + door2.getY());		
		
		System.out.println("player is standing above locked door since they have been blocked");
		
		// players key has been used by the door
		if (player.getKey() != null) {
			System.out.println("i still have my key");
		}
		
		try
		{
		    Thread.sleep(800);
		}
		catch(InterruptedException ex)
		{
		    Thread.currentThread().interrupt();
		}
		player.moveUp();
		
		System.out.println("door is at: " + door.getX() + "," + door.getY());
		System.out.println("player is at: " + player.getX() + "," + player.getY());
		
		System.out.println("door above is still unlocked");
	}
}
