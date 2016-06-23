package eg.edu.alexu.csd.ds.maze.cs05;

import java.awt.Point;

public class dfs implements MazeSolver{

	@Override
	public MyLinkedList solve(String[] map, Integer length, Integer width , Point [][] Doors) {
		toDoList path = new toDoStack();
		boolean endFound = false;
		int[][] visited = new int[length][width];
		Point [][] parent = new Point [length][width];
		Point S = new Point();
		S = searchforS(map,length,width);
		if(S==null){
			return null;
		}
		path.add(S);
		Point p = new Point();
		while(!path.isEmpty()){
			p = (Point) path.remove();
			if(map[p.x].charAt(p.y) == 'O'){
				visited[p.x][p.y] = 1;
				Point destination = findDestination(p, Doors);
				parent[destination.x][destination.y] = new Point(p.x , p.y);
				p = destination;
			}
			if(map[p.x].charAt(p.y) == 'E' || map[p.x].charAt(p.y) == 'e'){
				path.add(p);
				endFound = true;
				break;
			}
			else{
				if(visited[p.x][p.y] == 0){
					visited[p.x][p.y] = 1;
					if(p.y-1 >= 0 && map[p.x].charAt(p.y-1) != '#' && visited[p.x][p.y-1]  ==0){
						path.add(p);
						Point s = new Point();
						s.x = p.x;
						s.y = p.y-1;
						path.add(s);
						parent[s.x][s.y] = p;
						continue;
					}
				}
				if(visited[p.x][p.y] == 1){
					visited[p.x][p.y] = 2;
					if(p.y+1 < width && map[p.x].charAt(p.y+1) != '#' && visited[p.x][p.y+1]  ==0){
						path.add(p);
						Point s = new Point();
						s.x = p.x;
						s.y = p.y+1;
						path.add(s);
						parent[s.x][s.y] = p;
						continue;
					}
				}
				if(visited[p.x][p.y] == 2){
					visited[p.x][p.y] = 3;
					if(p.x+1 < length && map[p.x+1].charAt(p.y) != '#' && visited[p.x+1][p.y]  ==0){
						path.add(p);
						Point s = new Point();
						s.x = p.x+1;
						s.y = p.y;
						path.add(s);
						parent[s.x][s.y] = p;
						continue;
					}
				}
				if(visited[p.x][p.y] == 3){
					visited[p.x][p.y] = 4;
					if(p.x-1 >= 0 && map[p.x-1].charAt(p.y) != '#' && visited[p.x-1][p.y]  ==0){
						path.add(p);
						Point s = new Point();
						s.x = p.x-1;
						s.y = p.y;
						path.add(s);
						parent[s.x][s.y] = p;
						continue;
					}
				}
			}
		}
		if(endFound){
			MyLinkedList List = new SList();
			List = reverse(path,parent);
			return List;
		}
		return null;
	}

	private MyLinkedList reverse(toDoList path, Point[][] parent) {
		MyLinkedList List = new SList();
		Point a =new Point();
		a = (Point) path.remove();
		while(parent[a.x][a.y] != null){
			List.add(0, new Point(a.y,a.x));
			a = parent[a.x][a.y];
		}
		List.add(0, new Point (a.y,a.x));
		return List;
	}

	private Point searchforS(String[] map, Integer length, Integer width) {
		Point S = new Point();
		for(int i=0;i<length;i++){
			for(int j=0;j<width;j++){
				if(map[i].charAt(j) == 's' || map[i].charAt(j) == 'S'){
					S.x=i;
					S.y=j;
					return S;
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
}
