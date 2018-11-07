package Rooms;

import People.Person;
import Game.Board;
import People.Criminal;

public class Room {
	Person occupant;
	int xLoc,yLoc;
	String msg="";

	public Room(int x, int y)
	{
		xLoc = x;
		yLoc = y;
	}
	public Room(int x, int y, String str)
	{
		xLoc = x;
		yLoc = y;
		msg = str;
	}

	/**
	 * Method controls the results when a person enters this room.
	 * @param x the Person entering
	 */
	public void enterRoom(Person x)
	{
		System.out.println("You enter a plain old room");
		occupant = x;
		x.setxLoc(this.xLoc);
		x.setyLoc(this.yLoc);
	}

	/**
	 * Removes the player from the room.
	 * @param x
	 */
	public void leaveRoom(Person x)
	{
		occupant = x;
		occupant = null;
	}

}
