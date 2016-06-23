package eg.edu.alexu.csd.ds.maze.cs05;

public interface toDoList {
	/*
	 * add to the list 
	 */
	public void add(Object element);
	
	/*
	 * return the object and remove it from the list
	 */
	public Object remove();
	
	/*
	 * tests whether the list is empty or not
	 */
	public boolean isEmpty();
}
