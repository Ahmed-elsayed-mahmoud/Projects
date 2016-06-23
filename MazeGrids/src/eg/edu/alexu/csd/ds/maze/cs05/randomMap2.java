package eg.edu.alexu.csd.ds.maze.cs05;

import java.awt.Point;
import java.util.Random;

public class randomMap2 implements IMaze{
	Integer row=0 , column=0 , SX , SY;
	Point [][] doors = new Point[4][2];
	
	public randomMap2(Integer length , Integer width){
		row = length;
		column = width;
	}
	
	public randomMap2(){
		Random rand = new Random();
		row = rand.nextInt(35)+5; 
		column = rand.nextInt(35)+5;
	}
	
	public String[] randomMazeGenerator(){
		Random rand = new Random();
		Integer startR =0 , startC = 0;
		row = rand.nextInt(35)+5;
		column = rand.nextInt(35)+5;
		String[][] maze = new String[row][column];
		String[] map = new String[row];
		for(int i=0;i<row;i++){
			for(int j=0;j<column;j++){
				maze[i][j] = "#";
			}
		}
		startR = rand.nextInt(row);
		startC = rand.nextInt(column);
		SX = startR;
		SY = startC;
		maze[startR][startC] = "S";
		maze = makeMaze(maze,startR,startC);
		maze = putEnd(maze);
		maze = addWay(maze);
		map = convertInto1D(map,maze);
		
		return map;
	}
	private String[][] addWay(String[][] maze) {
		Random rand = new Random();
		int i , j , counter;
		int maxDoors = 4;
		int choice = 0;
		counter = rand.nextInt(8)+1;
		while(counter>0){
			i = rand.nextInt(row-2)+1;
			j = rand.nextInt(column-2)+1;
			if(maze[i][j].equals("#")){
				choice = 0;
				if(maxDoors > 0){
					choice = rand.nextInt(2);
				}
				if(choice == 0){
					maze[i][j] = ".";
				}
				else{
					maze[i][j] = "O";
					doors[4 - maxDoors][0] = new Point(j , i);
					int dy , dx;
					do{
						 dx = rand.nextInt(column);
						 dy = rand.nextInt(row);
					}while(maze[dy][dx] == "O");
					if(maze[dy][dx] == "#"){
						maze[dy][dx] = ".";
					}
					doors[4 - maxDoors][1] = new Point(dx , dy);
					maxDoors --;
				}
				counter--;
			}
		}
		return maze;
	}
	private String[][] putEnd(String[][] maze) {
		Integer EX,EY;
		boolean endPutted = false;
		Random rand = new Random();
		while(!endPutted){
			EX = rand.nextInt(row-1);
			EY = rand.nextInt(column-1);
			if(maze[EX][EY] == "."){
				maze[EX][EY] = "E";
				endPutted = true;
			}
		}
		return maze;
	}
	private String[] convertInto1D(String[] map, String[][] maze) {
		for(int i=0;i<row;i++){
			String s ="";
			for(int j=0;j<column;j++){
				s += maze[i][j];
			}
			map[i]=s;
		}
		return map;
	}

	private String[][] makeMaze(String[][] maze, Integer startR, Integer startC) {
		Random rand = new Random();
		boolean right = false, left = false , up = false , down = false;
		MyLinkedList List = new SList();
		List.add(0);
		List.add(1);
		List.add(2);
		List.add(3);
		while(!List.isEmpty()){
			int k=0,j=0;
			try{
				k = rand.nextInt(List.size());
				j = (int) List.get(k);
				List.remove(k);
			}catch(Exception e){
				return maze;
			}
			
			if(j==0){
				left = true;
				if(startC-1 >=0 ){
					if(startC-2 >=0){
						if(maze[startR][startC-1].equals("#") && maze[startR][startC-2].equals("#")){
							maze[startR][startC-1] = ".";
							maze[startR][startC-2] = ".";
							makeMaze(maze,startR,startC-2);
						}
					}
				}
			}
			if(j==1){
				right = true;
				if(startC+1 <column ){
					if(startC+2 <column ){
						if(maze[startR][startC+1].equals("#") && maze[startR][startC+2].equals("#")){
							maze[startR][startC+1] = ".";
							maze[startR][startC+2] = ".";
							makeMaze(maze,startR,startC+2);
						}
					}
				}
			}
			if(j==2){
				up = true;
				if(startR-1 >=0 ){
					if(startR-2 >=0 ){
						if(maze[startR-1][startC].equals("#") && maze[startR-2][startC].equals("#")){
							maze[startR-1][startC] = ".";
							maze[startR-2][startC] = ".";
							makeMaze(maze,startR-2,startC);
						}
					}
				}
			}
			if(j==3){
				down = true;
				if(startR+1 <row){
					if(startR+2 <row){
						if(maze[startR+1][startC].equals("#") && maze[startR+2][startC].equals("#")){
							maze[startR+1][startC] = ".";
							maze[startR+2][startC] = ".";
							makeMaze(maze,startR+2,startC);
						}
					}
				}
			}
			if(right && left && up && down){
				return maze;
			}
		}
		return maze;
	}
	
	public Point[][] getDoors(){
		int size = 0;
		while(doors[size][0] != null){
			size ++;
		}
		if(size == 0){
			return null;
		}
		Point [][] TheDoors = new Point[size][2];
		for(int counter = 0; counter < size; counter ++){
			TheDoors[counter][0] = doors[counter][0];
			TheDoors[counter][1] = doors[counter][1];
		}
		return TheDoors;
	}
}
