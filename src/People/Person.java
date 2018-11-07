package People;

import Game.Runner;
import Game.Board;

/**
 * Person represents the player as they move through the game.
 */
public class Person {
	String firstName;
	String familyName;
	int xLoc, yLoc;
	String team;
	int hp;


	public int getxLoc() {
		return xLoc;
	}

	public void setxLoc(int xLoc) {
		this.xLoc = xLoc;
	}

	public int getyLoc() {
		return yLoc;
	}

	public void setyLoc(int yLoc) {
		this.yLoc = yLoc;
	}

	public Person (String firstName, String familyName, int xLoc, int yLoc, String id,int hp)
	{
		this.firstName = firstName;
		this.familyName = familyName;
		this.xLoc = xLoc;
		this.yLoc = yLoc;
		this.team = id;
		this.hp = hp;
	}

	public String toString(){
		return this.firstName + " " + this.familyName;
	}

	public void moveRandom(){
		if(((int)Math.random()*2)==1){
			this.setxLoc(this.getxLoc()+(int)Math.random()*3-1);
		}
		else{
			this.setyLoc(this.getyLoc()+(int)Math.random()*3-1);
		}
	}

	public void loseHP(Person p,Boolean tf){
		this.hp--;
		if(this.team.equals("Player") && this.hp<=0){
			Runner.gameOff();
		}
		if(this.team.equals("Criminal") && this.hp<=0 && !tf){
			Criminal enemy1 = Board.placeEnemy();
		}
		if(this.team.equals("Criminal") && this.hp<=0){
			this.setxLoc(9999);
		}
		if(tf){
			System.out.println(this.team + " has " + this.hp + "hp left.");
		}
	}
}
