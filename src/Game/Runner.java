package Game;

import People.Criminal;
import People.Person;
import Rooms.Room;

import java.util.Scanner;

/**
 * got x and y mixed up, but code still works
 */

public class Runner {

	private static boolean gameOn = true;

	public static void main(String[] args)
	{
		//setup building and criminal location
		Board board = new Board(5,5);
		Room[][] building = board.setBuilding();
		Criminal enemy1 = board.placeEnemy();

		//Setup player 1 and the input scanner
		Scanner in = new Scanner(System.in);
		System.out.println("What is your first name?");
		String firstName = in.nextLine();
		System.out.println("What is your last name?");
		String familyName = in.nextLine();
		Person player1 = new Person(firstName,familyName,0,0,"player");
		System.out.println(player1.toString() + ", we believe there is a criminal on the loose. Can you help us get to the hostage without any caasualties?");
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
				board.printMap(player1,enemy1,init);
				System.out.println("You are the " + init.toUpperCase());
				System.out.println("We have spotted " + enemy1.toString() + "! Sending his location to the map now. We marked him with a \uD83D\uDC80");
			}
			if(validMove(move, player1, building))
			{
				System.out.println("Your coordinates: row = " + player1.getxLoc() + " col = " + player1.getyLoc());

			}
			else {
				System.out.println("Please choose a valid move.");
			}
			if(player1.getxLoc()==enemy1.getxLoc() && player1.getyLoc()== enemy1.getyLoc()){
				System.out.println("You have encountered the criminal!");
				System.out.println("He reaches into his pocket, and pulls out a ...");
				System.out.println("Quick, do something!");
				System.out.println("\033You can sidestep, backpedal, or reach for the kevlar on the table.\033");
				board.combat();
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