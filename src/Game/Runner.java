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
		Person player1 = new Person(firstName,familyName,0,0,"Player",5);
		System.out.println(player1.toString() + ", we believe there is a criminal on the loose. Can you help us get to the hostage without any caasualties?");
		if(!in.nextLine().toLowerCase().contains("ye")){
			System.out.println("Well, too bad!");
		}
		board.printMap(player1,enemy1,player1.toString().substring(0,1));
		System.out.println("If you need another map, be sure to call for a map!");
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
			if(board.validMove(move, player1, building) && !move.toLowerCase().equals("map"))
			{
				System.out.println("Your coordinates: row = " + player1.getxLoc() + " col = " + player1.getyLoc());
				enemy1.moveEnemy(player1);
			}
			else {
				System.out.println("Please choose a valid move.");
			}
			if(player1.getxLoc()==enemy1.getxLoc() && player1.getyLoc()== enemy1.getyLoc()){
				System.out.println("You have encountered the criminal! \n He reaches around, and pulls out a ...");
				System.out.println("\033Quick, do something! You can sidestep, backpedal, or reach for the armor on the table.\033");
				board.combat(player1,enemy1);
			}
		}
		in.close();
	}
	public static void gameOff()
	{
		gameOn = false;
	}



}