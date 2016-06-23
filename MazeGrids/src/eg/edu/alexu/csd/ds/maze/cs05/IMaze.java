package eg.edu.alexu.csd.ds.maze.cs05;

import java.awt.Point;

public interface IMaze {
	/*
	 * generates and returns a random maze
	 */
	public String[] randomMazeGenerator();
	
	/*
	 * returns array containing doors sources and its destinations
	 */
	public Point[][] getDoors();
}
