package eg.edu.alexu.csd.ds.maze.cs05;

import java.awt.Point;

public class bfs implements MazeSolver{
	private Point findStart(String [] map , Integer length , Integer width){
		for(int counter1 = 0; counter1 < length; counter1 ++){
			for(int counter2 = 0; counter2 < width; counter2 ++){
				if(map[counter1].charAt(counter2) == 'S' || map[counter1].charAt(counter2) == 's'){
					return new Point(counter2 , counter1);
				}
			}
		}
		return null;
	}
	
	private  Point findDestination(Point source , Point[][] Doors){
		int index = 0;
		while(true){
			if(Doors[index][0].x == source.x && Doors[index][0].y == source.y){
				return Doors[index][1];
			}
			index ++;
		}
	}
	
	private boolean isAncestor(Point child , Point currentParent , Point Anc , Point [][] parent , int direction){
		if(child == null || Anc == null || currentParent == null){
			return false;
		}
		//left
		if(child.x == Anc.x && child.y == Anc.y && direction == 1 && (currentParent.x != child.x - 1 || currentParent.y != child.y)){
			return false;
		}
		//right
		if(child.x == Anc.x && child.y == Anc.y && direction == 2 && (currentParent.x != child.x + 1 || currentParent.y != child.y)){
			return false;
		}
		//up
		if(child.x == Anc.x && child.y == Anc.y && direction == 3 && (currentParent.x != child.x || currentParent.y != child.y - 1)){
			return false;
		}
		//down
		if(child.x == Anc.x && child.y == Anc.y && direction == 4 && (currentParent.x != child.x || currentParent.y != child.y + 1)){
			return false;
		}
		if(child.x == Anc.x && child.y == Anc.y){
			return true;
		}
		if(currentParent.x == Anc.x && currentParent.y == Anc.y){
			return true;
		}
		Point now = new Point();
		now.x = currentParent.x;
		now.y = currentParent.y;
		while(parent[now.y][now.x] != null){
			if(parent[now.y][now.x].x == Anc.x && parent[now.y][now.x].y == Anc.y){
				return true;
			}
			Point swap = new Point();
			swap.x = now.x;
			swap.y = now.y;
			now.x = parent[swap.y][swap.x].x;
			now.y = parent[swap.y][swap.x].y;
		}
		return false;
	}
	
	public MyLinkedList solve(String [] map , Integer length , Integer width , Point [][] Doors){
		Point start = findStart(map , length , width);
		if(start == null){
			return null;
		}
		boolean [][] visited = new boolean [length][width];
		boolean [][] rightB = new boolean [length][width];
		boolean [][] leftB = new boolean [length][width];
		boolean [][] upB = new boolean [length][width];
		boolean [][] downB = new boolean [length][width];
		Point [][] parent = new Point [length][width];
		int [][] children = new int [length][width];
		toDoList next = new toDoQueue();
		toDoList Allparents = new toDoQueue();
		next.add(start);
		Allparents.add(null);
		visited[start.y][start.x] = true;
		Point currentPoint = new Point();
		Point currentParent = new Point();
		boolean solved = false;
		while(!next.isEmpty()){
			int counter = 0;
			boolean isDoor = false;
			currentPoint = (Point) next.remove();
			if(map[currentPoint.y].charAt(currentPoint.x) == 'O'){
				visited[currentPoint.y][currentPoint.x] = true;
				Point destination = findDestination(currentPoint, Doors);
				currentParent = new Point(currentPoint.x , currentPoint.y);
				currentPoint = destination;
				isDoor = true;
			}
			if(!isDoor){
				currentParent = (Point) Allparents.remove();
			}
			if(map[currentPoint.y].charAt(currentPoint.x) == 'E' || map[currentPoint.y].charAt(currentPoint.x) == 'e'){
				solved = true;
				break;
			}
			visited[currentPoint.y][currentPoint.x] = true;
			if(currentPoint.x + 1 < width && !visited[currentPoint.y][currentPoint.x + 1] && map[currentPoint.y].charAt(currentPoint.x + 1) != '#' && !(map[currentPoint.y].charAt(currentPoint.x + 1) == 'X' && (currentPoint.x + 2 >= width || (map[currentPoint.y].charAt(currentPoint.x + 2) != '.' && map[currentPoint.y].charAt(currentPoint.x + 2) != 'G')))){
				if(map[currentPoint.y].charAt(currentPoint.x + 1) == 'X'){
					if((rightB[currentPoint.y][currentPoint.x + 2] == false || !isAncestor(currentPoint, currentParent ,new Point(currentPoint.x + 3 , currentPoint.y), parent , 2)) && (leftB[currentPoint.y][currentPoint.x + 2] == false  || !isAncestor(currentPoint, currentParent , new Point(currentPoint.x + 1 , currentPoint.y), parent , 1)) && (upB[currentPoint.y][currentPoint.x + 2] == false || !isAncestor(currentPoint, currentParent ,new Point(currentPoint.x + 2 , currentPoint.y - 1), parent , 3)) && (downB[currentPoint.y][currentPoint.x + 2] == false || !isAncestor(currentPoint, currentParent ,new Point(currentPoint.x + 2 , currentPoint.y + 1), parent , 4))){
						parent[currentPoint.y][currentPoint.x + 1] = new Point(currentPoint.x , currentPoint.y);
						Allparents.add(new Point(currentPoint.x , currentPoint.y));
						next.add(new Point(currentPoint.x + 1 , currentPoint.y));
						leftB[currentPoint.y][currentPoint.x + 2] = true;
						counter ++;
					}
				}
				if(map[currentPoint.y].charAt(currentPoint.x + 1) == '.' || map[currentPoint.y].charAt(currentPoint.x + 1) == 'G'){
					if((rightB[currentPoint.y][currentPoint.x + 1] == false || !isAncestor(currentPoint,  currentParent ,new Point(currentPoint.x + 2 , currentPoint.y), parent , 2))&& (leftB[currentPoint.y][currentPoint.x + 1] == false || !isAncestor(currentPoint, currentParent ,new Point(currentPoint.x , currentPoint.y), parent , 1))&& (upB[currentPoint.y][currentPoint.x + 1] == false || !isAncestor(currentPoint, currentParent , new Point(currentPoint.x + 1 , currentPoint.y - 1), parent , 3))&& (downB[currentPoint.y][currentPoint.x + 1] == false || !isAncestor(currentPoint, currentParent , new Point(currentPoint.x + 1 , currentPoint.y + 1), parent , 4))){
						parent[currentPoint.y][currentPoint.x + 1] = new Point(currentPoint.x , currentPoint.y);
						Allparents.add(new Point(currentPoint.x , currentPoint.y));
						next.add(new Point(currentPoint.x + 1 , currentPoint.y));
						counter ++;
					}
					if((rightB[currentPoint.y][currentPoint.x + 1] == true && isAncestor(currentPoint, currentParent , new Point(currentPoint.x + 2 , currentPoint.y), parent , 2)) || (leftB[currentPoint.y][currentPoint.x + 1] == true  && isAncestor(currentPoint, currentParent , new Point(currentPoint.x , currentPoint.y), parent , 1)) || (upB[currentPoint.y][currentPoint.x + 1] == true && isAncestor(currentPoint, currentParent , new Point(currentPoint.x + 1 , currentPoint.y - 1), parent , 3)) || (downB[currentPoint.y][currentPoint.x + 1] == true && isAncestor(currentPoint, currentParent , new Point(currentPoint.x + 1 , currentPoint.y + 1), parent , 4))){
						if((currentPoint.x + 2 < width && (map[currentPoint.y].charAt(currentPoint.x + 2) == '.' || map[currentPoint.y].charAt(currentPoint.x + 2) == 'G')) &&(rightB[currentPoint.y][currentPoint.x + 2] == false || !isAncestor(currentPoint, currentParent , new Point(currentPoint.x + 3 , currentPoint.y), parent , 2)) && (leftB[currentPoint.y][currentPoint.x + 2] == false  || !isAncestor(currentPoint, currentParent ,new Point(currentPoint.x + 1 , currentPoint.y), parent , 1)) && (upB[currentPoint.y][currentPoint.x + 2] == false || !isAncestor(currentPoint, currentParent , new Point(currentPoint.x + 2 , currentPoint.y - 1), parent , 3)) && (downB[currentPoint.y][currentPoint.x + 2] == false || !isAncestor(currentPoint, currentParent , new Point(currentPoint.x + 2 , currentPoint.y + 1), parent , 4))){
							parent[currentPoint.y][currentPoint.x + 1] = new Point(currentPoint.x , currentPoint.y);
							Allparents.add(new Point(currentPoint.x , currentPoint.y));
							next.add(new Point(currentPoint.x + 1 , currentPoint.y));
							leftB[currentPoint.y][currentPoint.x + 2] = true;
							counter ++;
						}
					}
				}
				if(map[currentPoint.y].charAt(currentPoint.x + 1) == 'E' || map[currentPoint.y].charAt(currentPoint.x + 1) == 'e'){
					parent[currentPoint.y][currentPoint.x + 1] = new Point(currentPoint.x , currentPoint.y);
					Allparents.add(new Point(currentPoint.x , currentPoint.y));
					next.add(new Point(currentPoint.x + 1 , currentPoint.y));
					counter ++;
				}
				if(map[currentPoint.y].charAt(currentPoint.x + 1) == 'O' || map[currentPoint.y].charAt(currentPoint.x + 1) == 'o'){
					parent[currentPoint.y][currentPoint.x + 1] = new Point(currentPoint.x , currentPoint.y);
					next.add(new Point(currentPoint.x + 1 , currentPoint.y));
					counter ++;
				}
			}
			if(currentPoint.x - 1 >= 0 && !visited[currentPoint.y][currentPoint.x - 1] && map[currentPoint.y].charAt(currentPoint.x - 1) != '#' && !(map[currentPoint.y].charAt(currentPoint.x - 1) == 'X' && (currentPoint.x - 2 < 0 || (map[currentPoint.y].charAt(currentPoint.x - 2) != '.' && map[currentPoint.y].charAt(currentPoint.x - 2) != 'G')))){
				if(map[currentPoint.y].charAt(currentPoint.x - 1) == 'X'){
					if((rightB[currentPoint.y][currentPoint.x - 2] == false || !isAncestor(currentPoint, currentParent ,new Point(currentPoint.x - 1 , currentPoint.y), parent , 2)) && (leftB[currentPoint.y][currentPoint.x - 2] == false  || !isAncestor(currentPoint, currentParent , new Point(currentPoint.x - 3 , currentPoint.y), parent , 1)) && (upB[currentPoint.y][currentPoint.x - 2] == false || !isAncestor(currentPoint, currentParent , new Point(currentPoint.x - 2 , currentPoint.y - 1), parent , 3)) && (downB[currentPoint.y][currentPoint.x - 2] == false || !isAncestor(currentPoint, currentParent , new Point(currentPoint.x - 2 , currentPoint.y + 1), parent , 4))){
						parent[currentPoint.y][currentPoint.x - 1] = new Point(currentPoint.x , currentPoint.y);
						Allparents.add(new Point(currentPoint.x , currentPoint.y));
						next.add(new Point(currentPoint.x - 1 , currentPoint.y));
						rightB[currentPoint.y][currentPoint.x - 2] = true;
						counter ++;
					}
				}
				if(map[currentPoint.y].charAt(currentPoint.x - 1) == '.' || map[currentPoint.y].charAt(currentPoint.x - 1) == 'G'){
					if((rightB[currentPoint.y][currentPoint.x - 1] == false || !isAncestor(currentPoint, currentParent , new Point(currentPoint.x , currentPoint.y), parent , 2))&& (leftB[currentPoint.y][currentPoint.x - 1] == false || !isAncestor(currentPoint, currentParent , new Point(currentPoint.x - 2 , currentPoint.y), parent , 1))&& (upB[currentPoint.y][currentPoint.x - 1] == false || !isAncestor(currentPoint, currentParent , new Point(currentPoint.x - 1 , currentPoint.y - 1), parent , 3))&& (downB[currentPoint.y][currentPoint.x - 1] == false || !isAncestor(currentPoint, currentParent , new Point(currentPoint.x - 1 , currentPoint.y + 1), parent , 4))){
						parent[currentPoint.y][currentPoint.x - 1] = new Point(currentPoint.x , currentPoint.y);
						Allparents.add(new Point(currentPoint.x , currentPoint.y));
						next.add(new Point(currentPoint.x - 1 , currentPoint.y));
						counter ++;
					}
					if((rightB[currentPoint.y][currentPoint.x - 1] == true && isAncestor(currentPoint, currentParent , new Point(currentPoint.x , currentPoint.y), parent , 2)) || (leftB[currentPoint.y][currentPoint.x - 1] == true  && isAncestor(currentPoint, currentParent , new Point(currentPoint.x - 2 , currentPoint.y), parent , 1)) || (upB[currentPoint.y][currentPoint.x - 1] == true && isAncestor(currentPoint, currentParent , new Point(currentPoint.x - 1 , currentPoint.y - 1), parent , 3)) || (downB[currentPoint.y][currentPoint.x - 1] == true && isAncestor(currentPoint, currentParent , new Point(currentPoint.x - 1 , currentPoint.y + 1), parent , 4))){
						if((currentPoint.x - 2 >= 0 && (map[currentPoint.y].charAt(currentPoint.x - 2) == '.' || map[currentPoint.y].charAt(currentPoint.x - 2) == 'G')) && (rightB[currentPoint.y][currentPoint.x - 2] == false || !isAncestor(currentPoint, currentParent , new Point(currentPoint.x - 1 , currentPoint.y), parent , 2)) && (leftB[currentPoint.y][currentPoint.x - 2] == false  || !isAncestor(currentPoint, currentParent , new Point(currentPoint.x - 3 , currentPoint.y), parent , 1)) && (upB[currentPoint.y][currentPoint.x - 2] == false || !isAncestor(currentPoint, currentParent , new Point(currentPoint.x - 2 , currentPoint.y - 1), parent , 3)) && (downB[currentPoint.y][currentPoint.x - 2] == false || !isAncestor(currentPoint, currentParent , new Point(currentPoint.x - 2 , currentPoint.y + 1), parent , 4))){
							parent[currentPoint.y][currentPoint.x - 1] = new Point(currentPoint.x , currentPoint.y);
							Allparents.add(new Point(currentPoint.x , currentPoint.y));
							next.add(new Point(currentPoint.x - 1 , currentPoint.y));
							rightB[currentPoint.y][currentPoint.x - 2] = true;
							counter ++;
						}
					}
				}
				if(map[currentPoint.y].charAt(currentPoint.x - 1) == 'E' || map[currentPoint.y].charAt(currentPoint.x - 1) == 'e'){
					parent[currentPoint.y][currentPoint.x - 1] = new Point(currentPoint.x , currentPoint.y);
					Allparents.add(new Point(currentPoint.x , currentPoint.y));
					next.add(new Point(currentPoint.x - 1 , currentPoint.y));
					counter ++;
				}
				if(map[currentPoint.y].charAt(currentPoint.x - 1) == 'O' || map[currentPoint.y].charAt(currentPoint.x - 1) == 'o'){
					parent[currentPoint.y][currentPoint.x - 1] = new Point(currentPoint.x , currentPoint.y);
					next.add(new Point(currentPoint.x - 1 , currentPoint.y));
					counter ++;
				}
			}
			if(currentPoint.y + 1 < length && !visited[currentPoint.y + 1][currentPoint.x] && map[currentPoint.y + 1].charAt(currentPoint.x) != '#' &&  !(map[currentPoint.y + 1].charAt(currentPoint.x) == 'X' && (currentPoint.y + 2 >= length || (map[currentPoint.y + 2].charAt(currentPoint.x) != '.' && map[currentPoint.y + 2].charAt(currentPoint.x) != 'G')))){
				if(map[currentPoint.y + 1].charAt(currentPoint.x) == 'X'){
					if((rightB[currentPoint.y + 2][currentPoint.x] == false || !isAncestor(currentPoint, currentParent , new Point(currentPoint.x + 1 , currentPoint.y + 2), parent , 2)) && (leftB[currentPoint.y + 2][currentPoint.x] == false  || !isAncestor(currentPoint, currentParent , new Point(currentPoint.x - 1 , currentPoint.y + 2), parent , 1)) && (upB[currentPoint.y + 2][currentPoint.x] == false || !isAncestor(currentPoint, currentParent , new Point(currentPoint.x , currentPoint.y + 1), parent , 3)) && (downB[currentPoint.y + 2][currentPoint.x] == false || !isAncestor(currentPoint, currentParent , new Point(currentPoint.x , currentPoint.y + 3), parent , 4))){
						parent[currentPoint.y + 1][currentPoint.x] = new Point(currentPoint.x , currentPoint.y);
						Allparents.add(new Point(currentPoint.x , currentPoint.y));
						next.add(new Point(currentPoint.x , currentPoint.y + 1));
						upB[currentPoint.y + 2][currentPoint.x] = true;
						counter ++;
					}
				}
				if(map[currentPoint.y + 1].charAt(currentPoint.x) == '.' || map[currentPoint.y + 1].charAt(currentPoint.x) == 'G'){
					if((rightB[currentPoint.y + 1][currentPoint.x] == false || !isAncestor(currentPoint, currentParent , new Point(currentPoint.x + 1 , currentPoint.y + 1), parent , 2))&& (leftB[currentPoint.y + 1][currentPoint.x] == false || !isAncestor(currentPoint, currentParent , new Point(currentPoint.x - 1 , currentPoint.y + 1), parent , 1))&& (upB[currentPoint.y + 1][currentPoint.x] == false || !isAncestor(currentPoint, currentParent , new Point(currentPoint.x , currentPoint.y), parent , 3))&& (downB[currentPoint.y + 1][currentPoint.x] == false || !isAncestor(currentPoint, currentParent , new Point(currentPoint.x , currentPoint.y + 2), parent , 4))){
						parent[currentPoint.y + 1][currentPoint.x] = new Point(currentPoint.x , currentPoint.y);
						Allparents.add(new Point(currentPoint.x , currentPoint.y));
						next.add(new Point(currentPoint.x , currentPoint.y + 1));
						counter ++;
					}
					if((rightB[currentPoint.y + 1][currentPoint.x] == true && isAncestor(currentPoint, currentParent , new Point(currentPoint.x + 1 , currentPoint.y + 1), parent , 2)) || (leftB[currentPoint.y + 1][currentPoint.x] == true  && isAncestor(currentPoint, currentParent , new Point(currentPoint.x - 1 , currentPoint.y + 1), parent , 1)) || (upB[currentPoint.y + 1][currentPoint.x] == true && isAncestor(currentPoint, currentParent , new Point(currentPoint.x , currentPoint.y), parent , 3)) || (downB[currentPoint.y + 1][currentPoint.x] == true && isAncestor(currentPoint, currentParent , new Point(currentPoint.x , currentPoint.y + 2), parent , 4))){
						if((currentPoint.y + 2 < length && (map[currentPoint.y + 2].charAt(currentPoint.x) == '.' || map[currentPoint.y + 2].charAt(currentPoint.x) == 'G')) && (rightB[currentPoint.y + 2][currentPoint.x] == false || !isAncestor(currentPoint, currentParent , new Point(currentPoint.x + 1 , currentPoint.y + 2), parent , 2)) && (leftB[currentPoint.y + 2][currentPoint.x] == false  || !isAncestor(currentPoint, currentParent , new Point(currentPoint.x - 1 , currentPoint.y + 2), parent , 1)) && (upB[currentPoint.y + 2][currentPoint.x] == false || !isAncestor(currentPoint, currentParent , new Point(currentPoint.x , currentPoint.y + 1), parent , 3)) && (downB[currentPoint.y + 2][currentPoint.x] == false || !isAncestor(currentPoint, currentParent , new Point(currentPoint.x , currentPoint.y + 3), parent , 4))){
							parent[currentPoint.y + 1][currentPoint.x] = new Point(currentPoint.x , currentPoint.y);
							Allparents.add(new Point(currentPoint.x , currentPoint.y));
							next.add(new Point(currentPoint.x , currentPoint.y + 1));
							upB[currentPoint.y + 2][currentPoint.x] = true;
							counter ++;
						}
					}
				}
				if(map[currentPoint.y + 1].charAt(currentPoint.x) == 'E' || map[currentPoint.y + 1].charAt(currentPoint.x) == 'e'){
					parent[currentPoint.y + 1][currentPoint.x] = new Point(currentPoint.x , currentPoint.y);
					Allparents.add(new Point(currentPoint.x , currentPoint.y));
					next.add(new Point(currentPoint.x , currentPoint.y + 1));
					counter ++;
				}
				if(map[currentPoint.y + 1].charAt(currentPoint.x) == 'O' || map[currentPoint.y + 1].charAt(currentPoint.x) == 'o'){
					parent[currentPoint.y + 1][currentPoint.x] = new Point(currentPoint.x , currentPoint.y);
					next.add(new Point(currentPoint.x , currentPoint.y + 1));
					counter ++;
				}
			}
			if(currentPoint.y - 1 >= 0 && !visited[currentPoint.y - 1][currentPoint.x] && map[currentPoint.y - 1].charAt(currentPoint.x) != '#' &&  !(map[currentPoint.y - 1].charAt(currentPoint.x) == 'X' && (currentPoint.y - 2 < 0 || (map[currentPoint.y - 2].charAt(currentPoint.x) != '.' && map[currentPoint.y - 2].charAt(currentPoint.x) != 'G')))){
				if(map[currentPoint.y - 1].charAt(currentPoint.x) == 'X'){
					if((rightB[currentPoint.y - 2][currentPoint.x] == false || !isAncestor(currentPoint, currentParent , new Point(currentPoint.x + 1 , currentPoint.y - 2), parent , 2)) && (leftB[currentPoint.y - 2][currentPoint.x] == false  || !isAncestor(currentPoint, currentParent , new Point(currentPoint.x - 1 , currentPoint.y - 2), parent , 1)) && (upB[currentPoint.y - 2][currentPoint.x] == false || !isAncestor(currentPoint, currentParent , new Point(currentPoint.x , currentPoint.y - 3), parent , 3)) && (downB[currentPoint.y - 2][currentPoint.x] == false || !isAncestor(currentPoint, currentParent , new Point(currentPoint.x , currentPoint.y - 1), parent , 4))){
						parent[currentPoint.y - 1][currentPoint.x] = new Point(currentPoint.x , currentPoint.y);
						Allparents.add(new Point(currentPoint.x , currentPoint.y));
						next.add(new Point(currentPoint.x , currentPoint.y - 1));
						downB[currentPoint.y - 2][currentPoint.x] = true;
						counter ++; 
					}
				}
				if(map[currentPoint.y - 1].charAt(currentPoint.x) == '.' || map[currentPoint.y - 1].charAt(currentPoint.x) == 'G'){
					if((rightB[currentPoint.y - 1][currentPoint.x] == false || !isAncestor(currentPoint, currentParent , new Point(currentPoint.x + 1 , currentPoint.y - 1), parent , 2))&& (leftB[currentPoint.y - 1][currentPoint.x] == false || !isAncestor(currentPoint, currentParent ,  new Point(currentPoint.x - 1 , currentPoint.y - 1), parent , 1))&& (upB[currentPoint.y - 1][currentPoint.x] == false || !isAncestor(currentPoint, currentParent , new Point(currentPoint.x , currentPoint.y - 2), parent , 3))&& (downB[currentPoint.y - 1][currentPoint.x] == false || !isAncestor(currentPoint, currentParent , new Point(currentPoint.x , currentPoint.y), parent , 4))){
						parent[currentPoint.y - 1][currentPoint.x] = new Point(currentPoint.x , currentPoint.y);
						Allparents.add(new Point(currentPoint.x , currentPoint.y));
						next.add(new Point(currentPoint.x , currentPoint.y - 1));
						counter ++;
					}
					if((rightB[currentPoint.y - 1][currentPoint.x] == true && isAncestor(currentPoint, currentParent , new Point(currentPoint.x + 1 , currentPoint.y - 1), parent , 2)) || (leftB[currentPoint.y - 1][currentPoint.x] == true  && isAncestor(currentPoint, currentParent , new Point(currentPoint.x - 1 , currentPoint.y - 1), parent , 1)) || (upB[currentPoint.y - 1][currentPoint.x] == true && isAncestor(currentPoint, currentParent , new Point(currentPoint.x , currentPoint.y - 2), parent , 3)) || (downB[currentPoint.y - 1][currentPoint.x] == true && isAncestor(currentPoint, currentParent , new Point(currentPoint.x , currentPoint.y), parent , 4))){
						if((currentPoint.y - 2 >= 0 && (map[currentPoint.y - 2].charAt(currentPoint.x) == '.' || map[currentPoint.y - 2].charAt(currentPoint.x) == 'G')) && (rightB[currentPoint.y - 2][currentPoint.x] == false || !isAncestor(currentPoint, currentParent , new Point(currentPoint.x + 1 , currentPoint.y - 2), parent , 2)) && (leftB[currentPoint.y - 2][currentPoint.x] == false  || !isAncestor(currentPoint, currentParent , new Point(currentPoint.x - 1 , currentPoint.y - 2), parent , 1)) && (upB[currentPoint.y - 2][currentPoint.x] == false || !isAncestor(currentPoint, currentParent , new Point(currentPoint.x , currentPoint.y - 3), parent , 3)) && (downB[currentPoint.y - 2][currentPoint.x] == false || !isAncestor(currentPoint, currentParent , new Point(currentPoint.x , currentPoint.y - 1), parent , 4))){
							parent[currentPoint.y - 1][currentPoint.x] = new Point(currentPoint.x , currentPoint.y);
							Allparents.add(new Point(currentPoint.x , currentPoint.y));
							next.add(new Point(currentPoint.x , currentPoint.y - 1));
							downB[currentPoint.y - 2][currentPoint.x] = true;
							counter ++;
						}
					}
				}
				if(map[currentPoint.y - 1].charAt(currentPoint.x) == 'E' || map[currentPoint.y - 1].charAt(currentPoint.x) == 'e'){
					parent[currentPoint.y - 1][currentPoint.x] = new Point(currentPoint.x , currentPoint.y);
					Allparents.add(new Point(currentPoint.x , currentPoint.y));
					next.add(new Point(currentPoint.x , currentPoint.y - 1));
					counter ++;
				}
				if(map[currentPoint.y - 1].charAt(currentPoint.x) == 'O' || map[currentPoint.y - 1].charAt(currentPoint.x) == 'o'){
					parent[currentPoint.y - 1][currentPoint.x] = new Point(currentPoint.x , currentPoint.y);
					next.add(new Point(currentPoint.x , currentPoint.y - 1));
					counter ++;
				}
			}
			if(counter > children[currentPoint.y][currentPoint.x] && currentParent != null){
				parent[currentPoint.y][currentPoint.x] = new Point(currentParent.x , currentParent.y);
				children[currentPoint.y][currentPoint.x] = counter;
			}
		}
		if(!solved){
			return null;
		}
		MyLinkedList path = new SList();
		while(parent [currentPoint.y][currentPoint.x] != null){
			path.add(0 , new Point(currentPoint.x , currentPoint.y));
			currentPoint = parent [currentPoint.y][currentPoint.x];
		}
		path.add(0 , new Point(currentPoint.x , currentPoint.y));
		return path;
	}
}
