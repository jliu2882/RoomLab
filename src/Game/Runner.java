package Game;

import People.Person;
import Rooms.Room;
import Rooms.WinningRoom;

import java.util.Scanner;

public class Runner {

	private static boolean gameOn = true;

	public static void main(String[] args)
	{
		Board board = new Board(5,5);
		Room[][] building = board.getBuilding();


		//Setup player 1 and the input scanner
		Scanner in = new Scanner(System.in);
		System.out.println("What is your first name?");
		String firstName = in.nextLine();
		System.out.println("What is your last name?");
		String familyName = in.nextLine();
		Person player1 = new Person(firstName,familyName,0,0);
		System.out.println(player1.toString() + ", we believe there is a hostage on the loose. Can you help us get the hostage without getting caught?");
		if(!in.nextLine().toLowerCase().contains("ye")){
			System.out.println("Well, too bad!");
		}
		System.out.println("If you need a map, be sure to call for one!");
		building[0][0].enterRoom(player1);
		while(gameOn)
		{
			System.out.println("Where would you like to move? (Choose N, S, E, W)");
			String move = in.nextLine();
			if(move.toLowerCase().equals("map")){
				String init = player1.toString().substring(0,1);
				board.printMap(player1.getxLoc(),player1.getyLoc(),init);
				System.out.println("You are the " + init.toUpperCase());
			}
			if(validMove(move, player1, building))
			{
				System.out.println("Your coordinates: row = " + player1.getxLoc() + " col = " + player1.getyLoc());

			}
			else {
				System.out.println("Please choose a valid move.");
			}


		}
		in.close();
	}
	/**
	 * Checks that the movement chosen is within the valid game map.
	 * @param move the move chosen
	 * @param p person moving
	 * @param map the 2D array of rooms
	 * @return
	 */
	public static boolean validMove(String move, Person p, Room[][] map)
	{
		move = move.toLowerCase().trim();
		switch (move) {
			case "n":
				if (p.getxLoc() > 0)
				{
					map[p.getxLoc()][p.getyLoc()].leaveRoom(p);
					map[p.getxLoc()-1][p.getyLoc()].enterRoom(p);
					return true;
				}
				else
				{
					return false;
				}
			case "e":
				if (p.getyLoc()< map[p.getyLoc()].length -1)
				{
					map[p.getxLoc()][p.getyLoc()].leaveRoom(p);
					map[p.getxLoc()][p.getyLoc() + 1].enterRoom(p);
					return true;
				}
				else
				{
					return false;
				}

			case "s":
				if (p.getxLoc() < map.length - 1)
				{
					map[p.getxLoc()][p.getyLoc()].leaveRoom(p);
					map[p.getxLoc()+1][p.getyLoc()].enterRoom(p);
					return true;
				}
				else
				{
					return false;
				}

			case "w":
				if (p.getyLoc() > 0)
				{
					map[p.getxLoc()][p.getyLoc()].leaveRoom(p);
					map[p.getxLoc()][p.getyLoc()-1].enterRoom(p);
					return true;
				}
				else
				{
					return false;
				}


			default:
				break;

		}
		return true;
	}
	public static void gameOff()
	{
		gameOn = false;
	}



}