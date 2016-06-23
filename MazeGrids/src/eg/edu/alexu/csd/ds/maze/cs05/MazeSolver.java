package eg.edu.alexu.csd.ds.maze.cs05;

import java.awt.Point;

public interface MazeSolver {
	/*
	 * parameter1 : maze grid
	 * parameter2 : maze length
	 * parameter3 : maze width
	 */
	/*
	 * returns a linked list of points that describes a bath from S to E starting from
	 * S at index 0 and E at the end of the list 
	 */
	public MyLinkedList solve(String[] map , Integer length , Integer Width , Point [][] Doors);
}
