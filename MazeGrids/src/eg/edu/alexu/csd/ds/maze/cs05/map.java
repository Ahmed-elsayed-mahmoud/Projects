package eg.edu.alexu.csd.ds.maze.cs05;

import java.awt.Point;

public class map {
	private Integer N ,M , counter = 0;	
	private String[][] maze;
	private MyLinkedList path = new SList();
	private Point[][] doors = new Point[50][2];
	

	public int getlength(){
		return N;
	}
	public int getwidth(){
		return M;
	}
	public String getMap(int x, int y){
		return maze[x][y];
	}
	public Point[][] theDoors(){
		return doors;
	}
	public int max(){
		return counter;
	}
	public void convertInto2D(String[] map){
		maze = new String[N][M];
		for(int i=0;i<N;i++){
			for(int j =0 ;j<M;j++){
				maze[i][j] = map[i].substring(j, j+1);
			}
		}
	}
	
	public void addanswer(){
		int k = getAnserLength();
		boolean lastIsDoor = false;
		for(int i=1;i<k-1;i++){
			Point a = new Point();
			a=(Point) path.get(i);
			if(lastIsDoor){
				lastIsDoor = false;
				maze[a.y][a.x] ="O";
				doors[counter][1] = new Point(a.x,a.y);
				counter++;
			}
			else if(maze[a.y][a.x].equals("O")){
				maze[a.y][a.x] ="O";
				doors[counter][0] = new Point(a.x,a.y);
				lastIsDoor = true;
			}
			else if(maze[a.y][a.x].equals("X")){
				Point p = new Point();
				p=(Point) path.get(i-1);
				if(p.y-a.y > 0 && p.x-a.x==0 && a.y-1 >=0){
					maze[a.y-1][a.x]= "X";
					maze[a.y][a.x]= "+";
				}
				else if(p.y-a.y < 0 && p.x-a.x==0 && a.y+1 < N){
					maze[a.y+1][a.x]= "X";
					maze[a.y][a.x]= "+";
				}
				else if(p.y-a.y == 0 && p.x-a.x>0 && a.x-1 >= 0){
					maze[a.y][a.x-1]= "X";
					maze[a.y][a.x]= "+";
				}
				else if(p.y-a.y == 0 && p.x-a.x <0 && a.x+1 < M){
					maze[a.y][a.x+1]= "X";
					maze[a.y][a.x]= "+";
				}
			}
			else{
				maze[a.y][a.x]= "+";
			}
			
		}
		
	}
	public int getAnserLength(){
		return path.size();
	}
	
	public map(MyLinkedList path2, String[] map, boolean b){
		path=path2;
		N=map.length;
		M=map[0].length();
		convertInto2D(map);
		if(b){
			addanswer();
		}
		
	}
}
