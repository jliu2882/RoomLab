package Game;

import Rooms.Room;
import Rooms.WinningRoom;

public class Board {
    private static int r;
    private static int c;

    public Board(int r, int c) {
        this.r = r;
        this.c = c;
    }

    public static Room[][] getBuilding(){
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

        return building;
    }
    public static void printMap(int x, int y, String init){
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
        map[2*x+1][2*y+1] = init.toUpperCase();
        for(String[] row : map){
            for(String column: row){
                finMap += column;
            }
            finMap += "\n";
        }
        System.out.println(finMap);
    }
}
