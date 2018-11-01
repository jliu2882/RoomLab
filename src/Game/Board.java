package Game;

import People.Criminal;
import Rooms.Room;
import Rooms.WinningRoom;
import People.Person;
import java.util.Scanner;

public class Board {
    private static int r;
    private static int c;
    private static int mem1;
    private static int mem2;

    public Board(int r, int c) {
        this.r = r;
        this.c = c;
    }

    public static Room[][] setBuilding(){
        Room[][] building = new Room[r][c];

        //Fill the building with rooms
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
        mem1 = x;
        mem2 = y;
        return building;
    }
    public static Criminal placeEnemy(){
        int x = mem1 + fiftyFifty();
        int y = mem2 + fiftyFifty();
        if(x>r){
            x=r;
        } else if(x<0){
            x=0;
        }
        if(y>c){
            y=c;
        } else if(y<0){
            y=0;
        }
        Criminal enemy1 = new Criminal("Inal","Crimson",x,y);
        return enemy1;
    }
    public static int fiftyFifty(){
        int r = (int)(Math.random()*2);
        if(r==0){
            return -1;
        }
        return 1;
    }
    public static void printMap(Person p, Person p2, String init){
        int x = p.getxLoc();
        int y = p.getyLoc();
        int x2 = p2.getxLoc();
        int y2 = p2.getyLoc();
        String[][] map = new String[2*r+1][4*c+1];
        String finMap = "";
        for(int i =0; i < map.length; i++){
            if(i%2==0){
                for(int j= 0;j <map[i].length;j++){
                    map[i][j] = "-";
                }
            }
            else{
                for(int j =0; j<map[i].length;j++){
                    if(j%4==0){
                        map[i][j] = "|";
                    }
                    else{
                        map[i][j] = " ";
                    }
                }
            }
        }
        map[2*x+1][4*y+2] = init.toUpperCase();
        map[2*x2+1][4*y2+2] = "\uD83D\uDC80";
        for(String[] row : map){
            for(String column: row){
                finMap += column;
            }
            finMap += "\n";
        }
        System.out.println(finMap);
    }
    public static void combat(){
        Scanner in = new Scanner(System.in);
    }
}
