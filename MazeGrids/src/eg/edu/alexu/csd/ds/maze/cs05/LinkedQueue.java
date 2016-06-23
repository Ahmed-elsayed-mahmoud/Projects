package eg.edu.alexu.csd.ds.maze.cs05;

public class LinkedQueue implements MyQueue{
	private DList queue = new DList();
	
	public void enqueue(Object item){
		queue.add(item);
	}
	
	public Object dequeue(){
		Object item;
		try{
			item = queue.get(0);
			queue.remove(0);
		}catch(ListException e){
			throw new RuntimeException("Empty Queue Exception!");
		}
		return item;
	}
	
	public boolean isEmpty(){
		return queue.isEmpty();
	}
	
	public int size(){
		return queue.size();
	}
}
