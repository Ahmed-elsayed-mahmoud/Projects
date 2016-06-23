package eg.edu.alexu.csd.ds.maze.cs05;

public class toDoQueue implements toDoList{
	MyQueue queue = new LinkedQueue();
	
	public void add(Object element){
		queue.enqueue(element);
	}
	
	public Object remove(){
		return queue.dequeue();
	}
	
	public boolean isEmpty(){
		return queue.isEmpty();
	}
}
