package Game;

import People.Person;
import Rooms.Room;
import Rooms.WinningRoom;

import java.util.Scanner;

public class Runner {

	private static int r = 5;
	private static int c = 5;

	private static boolean gameOn = true;

	public static void main(String[] args)
	{
		Room[][] building = new Room[r][c];

		//Fill the building with normal rooms
		for (int x = 0; x<building.length; x++)
		{
			for (int y = 0; y < building[x].length; y++)
			{
				building[x][y] = new Room(x,y);
			}
		}

		//Create a random winning room.
		int x = (int)(Math.random()*building.length);
		int y = (int)(Math.random()*building.length);
		building[x][y] = new WinningRoom(x, y);

		//Setup player 1 and the input scanner
		Scanner in = new Scanner(System.in);
		System.out.println("What is your first name?");
		String firstName = in.nextLine();
		System.out.println("What is your last name?");
		String familyName = in.nextLine();
		Person player1 = new Person(firstName,familyName,0,0);
		System.out.println(player1.toString() + ", we believe there is a criminal on the loose. Can you help us catch him?");
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
				printMap(player1.getxLoc(),player1.getyLoc());
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

	public static void printMap(int x, int y){
		String[][] map = new String[2*r+1][2*c+1];
		String finMap = "";
		for(int i =0; i < map.length; i++){
			if(i%2==0){
				for(int j= 0;j <map[i].length;j++){
					map[i][j] = "-";
				}
			}
			else{
				for(int j =0; j<map[i].length;j++){
					if(j%2==0){
						map[i][j] = "|";
					}
					else{
						map[i][j] = " ";
					}
				}
			}
		}
		map[2*x+1][2*y+1] = "x";
		for(String[] row : map){
			for(String column: row){
				finMap += column;
			}
			finMap += "\n";
		}
		System.out.println(finMap);
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