package eg.edu.alexu.csd.ds.maze.cs05;

import java.awt.Point;
import java.util.Random;

public class randomMap implements IMaze {
	Integer row , column , mazeLength , restMaze;
	Point [][] TheDoors = new Point[12][2]; 
	
	public randomMap(Integer length , Integer width){
		row = length;
		column = width;
	}
	
	public randomMap(){
		Random rand = new Random();
		row = rand.nextInt(35)+5; 
		column = rand.nextInt(35)+5;
	}
	
	public String[] randomMazeGenerator(){
		MazeSolver solver = new bfs();
		String [] map;
		do{
			Random rand = new Random();
			Integer startR =0 , startC = 0;
			String[][] maze = new String[row][column];
			map = new String[row];
			for(int i=0;i<row;i++){
				for(int j=0;j<column;j++){
					maze[i][j] = "#";
				}
			}
			startR = rand.nextInt(row);
			startC = rand.nextInt(column);
			maze[startR][startC] = "S";
			mazeLength = rand.nextInt(2*(row*column)/10)+2*(row*column)/10;
			restMaze = rand.nextInt(4 * (row * column) / 10) + 4 * (row * column) / 10 - mazeLength;
			maze = makeMaze(maze,startR,startC);
			maze = completeMap(maze);
			map = convertInto1D(map,maze);
		}while(solver.solve(map, row, column, this.getDoors()) == null);
		return map;
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
		int maxRock = 7;
		int numRock;
		int choice;
		if(15 * row * column / 100 < 7){
			maxRock = 15 * row * column;
		}
		numRock = rand.nextInt(maxRock);
		MyLinkedList List = new SList();
		List.add(0);
		List.add(1);
		List.add(2);
		List.add(3);
		while(mazeLength > 0){
			int k=0,j=0;
			try{
				k = rand.nextInt(List.size());
				j = (int) List.get(k);
				List.remove(k);
			}catch(Exception e){
				
			}
			
			if(j==0){
				left = true;
				if(startC-1 >=0 && mazeLength > 0){
					if(startC-2 >=0 && mazeLength > 0){
						if(maze[startR][startC-1].equals("#") && maze[startR][startC-2].equals("#")){
							if(mazeLength > 1){
								choice = rand.nextInt(5);
								if(choice == 2 && numRock > 0){
									maze[startR][startC-1] = "X";
									maze[startR][startC-2] = "G";
									numRock --;
								}
								else{
									maze[startR][startC-1] = ".";
								}
							}
							else{
								maze[startR][startC-1] = "E";
							}
							mazeLength--;
							makeMaze(maze,startR,startC-1);
						}
					}
					else{
						if(maze[startR][startC-1].equals("#")){
							if(mazeLength > 1){
								maze[startR][startC-1] = ".";
							}
							else{
								maze[startR][startC-1] = "E";
							}
							mazeLength--;
							makeMaze(maze,startR,startC-1);
						}
					}
				}
			}
			if(j==1){
				right = true;
				if(startC+1 <column && mazeLength > 0){
					if(startC+2 <column && mazeLength > 0){
						if(maze[startR][startC+1].equals("#") && maze[startR][startC+2].equals("#")){
							if(mazeLength > 1){
								choice = rand.nextInt(5);
								if(choice == 2 && numRock > 0){
									maze[startR][startC+1] = "X";
									maze[startR][startC+2] = "G";
									numRock --;
								}
								else{
									maze[startR][startC+1] = ".";
								}
							}
							else{
								maze[startR][startC+1] = "E";
							}
							mazeLength--;
							makeMaze(maze,startR,startC+1);
						}
					}
					else{
						if(maze[startR][startC+1].equals("#")){
							if(mazeLength > 1){
								maze[startR][startC+1] = ".";
							}
							else{
								maze[startR][startC+1] = "E";
							}
							mazeLength--;
							makeMaze(maze,startR,startC+1);
						}
					}
				}
			}
			if(j==2){
				up = true;
				if(startR-1 >=0 && mazeLength > 0){
					if(startR-2 >=0 && mazeLength > 0){
						if(maze[startR-1][startC].equals("#") && maze[startR-2][startC].equals("#")){
							if(mazeLength > 1){
								choice = rand.nextInt(5);
								if(choice == 2 && numRock > 0){
									maze[startR-1][startC] = "X";
									maze[startR-2][startC] = "G";
									numRock --;
								}
								else{
									maze[startR-1][startC] = ".";
								}
							}
							else{
								maze[startR][startC] = "E";
							}
							mazeLength--;
							makeMaze(maze,startR-1,startC);
						}
					}
					else{
						if(maze[startR-1][startC].equals("#")){
							if(mazeLength > 1){
								maze[startR-1][startC] = ".";
							}
							else{
								maze[startR-1][startC] = "E";
							}
							mazeLength--;
							makeMaze(maze,startR-1,startC);
						}
					}
				}
			}
			if(j==3){
				down = true;
				if(startR+1 <row && mazeLength > 0){
					if(startR+2 <row && mazeLength > 0){
						if(maze[startR+1][startC].equals("#") && maze[startR+2][startC].equals("#")){
							if(mazeLength > 1){
								choice = rand.nextInt(5);
								if(choice == 2 && numRock > 0){
									maze[startR+1][startC] = "X";
									maze[startR+2][startC] = "G";
									numRock --;
								}
								else{
									maze[startR+1][startC] = ".";
								}
							}
							else{
								maze[startR+1][startC] = "E";
							}
							mazeLength--;
							makeMaze(maze,startR+1,startC);
						}
					}
					else{
						if(maze[startR+1][startC].equals("#")){
							if(mazeLength > 1){
								maze[startR+1][startC] = ".";
							}
							else{
								maze[startR+1][startC] = "E";
							}
							mazeLength--;
							makeMaze(maze,startR+1,startC);
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
	
	private String[][] completeMap(String[][] maze){
		Random rand = new Random();
		int maxRock = 12;
		int maxDoor = 12;
		if(row * column / 10 < 12){
			maxRock = row * column / 10;
			maxDoor = row * column / 10;
		}
		int numDoor = rand.nextInt(maxDoor);
		int numRock = rand.nextInt(maxRock);
		int Doors = numDoor / 4;
		int Rocks = numRock / 4;
		int changes = restMaze / 4;
		int cell;
		int numD = 0;
		for(int counter = 0; counter < row / 2 && changes > 0; counter ++){
			for(int counter2 = 0; counter2 < column / 2 && changes > 0; counter2 ++){
				if(maze[counter][counter2] != "#"){
					continue;
				}
				cell = rand.nextInt(10);
				if(cell == 4 && Doors > 0){
					int xDes , yDes;
					maze[counter][counter2] = "O";
					do{
						xDes = rand.nextInt(column);
						yDes = rand.nextInt(row);
					}while(maze[yDes][xDes] == "O" || maze[yDes][xDes] == "X" || maze[yDes][xDes] == "G");
					if(maze[yDes][xDes] == "#"){
						maze[yDes][xDes] = ".";
					}
					TheDoors[numD][0] = new Point(counter2 , counter);
					TheDoors[numD][1] = new Point(xDes , yDes);
					numD ++;
					Doors --;
					changes --;
				}
				else if(cell == 7 && Rocks > 0){
					maze[counter][counter2] = "X";
					Rocks --;
					changes --;
				}
				else if(cell % 2 == 0){
					maze[counter][counter2] = ".";
					changes --;
				}
			}
		}
		Doors += numDoor / 4;
		Rocks += numRock / 4;
		changes += restMaze / 4;
		for(int counter = row / 2; counter < row - 1 && changes > 0; counter ++){
			for(int counter2 = 0; counter2 < column / 2 && changes > 0; counter2 ++){
				if(maze[counter][counter2] != "#"){
					continue;
				}
				cell = rand.nextInt(10);
				if(cell == 4 && Doors > 0){
					int xDes , yDes;
					maze[counter][counter2] = "O";
					do{
						xDes = rand.nextInt(column);
						yDes = rand.nextInt(row);
					}while(maze[yDes][xDes] == "O" || maze[yDes][xDes] == "X" || maze[yDes][xDes] == "G");
					if(maze[yDes][xDes] == "#"){
						maze[yDes][xDes] = ".";
					}
					TheDoors[numD][0] = new Point(counter2 , counter);
					TheDoors[numD][1] = new Point(xDes , yDes);
					numD ++;
					Doors --;
					changes --;
				}
				else if(cell == 7 && Rocks > 0){
					maze[counter][counter2] = "X";
					Rocks --;
					changes --;
				}
				else if(cell % 2 == 0){
					maze[counter][counter2] = ".";
					changes --;
				}
			}
		}
		Doors += numDoor / 4;
		Rocks += numRock / 4;
		changes += restMaze / 4;
		for(int counter = row / 2; counter < row - 1 && changes > 0; counter ++){
			for(int counter2 = column / 2; counter2 < column - 1 && changes > 0; counter2 ++){
				if(maze[counter][counter2] != "#"){
					continue;
				}
				cell = rand.nextInt(10);
				if(cell == 4 && Doors > 0){
					int xDes , yDes;
					maze[counter][counter2] = "O";
					do{
						xDes = rand.nextInt(column);
						yDes = rand.nextInt(row);
					}while(maze[yDes][xDes] == "O" || maze[yDes][xDes] == "X" || maze[yDes][xDes] == "G");
					if(maze[yDes][xDes] == "#"){
						maze[yDes][xDes] = ".";
					}
					TheDoors[numD][0] = new Point(counter2 , counter);
					TheDoors[numD][1] = new Point(xDes , yDes);
					numD ++;
					Doors --;
					changes --;
				}
				else if(cell == 7 && Rocks > 0){
					maze[counter][counter2] = "X";
					Rocks --;
					changes --;
				}
				else if(cell % 2 == 0){
					maze[counter][counter2] = ".";
					changes --;
				}
			}
		}
		Doors += numDoor / 4;
		Rocks += numRock / 4;
		changes += restMaze / 4;
		for(int counter = 0; counter < row /2 && changes > 0; counter ++){
			for(int counter2 = column / 2; counter2 < column - 1 && changes > 0; counter2 ++){
				if(maze[counter][counter2] != "#"){
					continue;
				}
				cell = rand.nextInt(10);
				if(cell == 4 && Doors > 0){
					int xDes , yDes;
					maze[counter][counter2] = "O";
					do{
						xDes = rand.nextInt(column);
						yDes = rand.nextInt(row);
					}while(maze[yDes][xDes] == "O" || maze[yDes][xDes] == "X" || maze[yDes][xDes] == "G");
					if(maze[yDes][xDes] == "#"){
						maze[yDes][xDes] = ".";
					}
					TheDoors[numD][0] = new Point(counter2 , counter);
					TheDoors[numD][1] = new Point(xDes , yDes);
					numD ++;
					Doors --;
					changes --;
				}
				else if(cell == 7 && Rocks > 0){
					maze[counter][counter2] = "X";
					Rocks --;
					changes --;
				}
				else if(cell % 2 == 0){
					maze[counter][counter2] = ".";
					changes --;
				}
			}
		}
		return maze;
	}
	
	public Point[][] getDoors(){
		int size = 0;
		while(TheDoors[size][0] != null){
			size ++;
		}
		if(size == 0){
			return null;
		}
		Point [][] Doors = new Point[size][2];
		for(int counter = 0; counter < size; counter ++){
			Doors[counter][0] = TheDoors[counter][0];
			Doors[counter][1] = TheDoors[counter][1];
		}
		return Doors;
	}
}
