package eg.edu.alexu.csd.ds.maze.cs05;

import java.awt.Point;

public class mazeUI {
	public static void main(String[] args) {
		randomMap map = new randomMap();
		String [] ran = map.randomMazeGenerator();
		bfs solver = new bfs();
		/*
		String [] ran = {"..#.#..E.#"
						,"X..OG.##X#"
						,".###XXG.##"
						,"#.#.XG#.##"
						,"G...#..X##"
						,"X...#.O##."
						,"..#....###"
						,"GX..S.#..#"
						,"..##O.#X.#"
						,"##########"};
						
						
						
						...XG.X.....X.....#.#.X##
						XS#.#..###....##...##..X#
						G#..#########.G.XG#######
						#.....#G..##.EXXG.#.##..#
						##.XXGXX.###.....###...##
						##XG.X#.....#...#..######
						####.....XG...###########

		*/
		for(int counter = 0; counter < ran.length; counter ++){
			System.out.println(ran[counter]);
		}
		MyLinkedList path = solver.solve(ran, ran.length,ran[0].length() , map.getDoors());
		while(true){
			Point P = new Point();
			try{
				P = (Point) path.get(0);
				path.remove(0);
				System.out.print(P);
				System.out.println();
			}catch(Exception e){
				break;
			}
		}
	}
}
