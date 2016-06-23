package eg.edu.alexu.csd.ds.maze.cs05;

public class toDoStack implements toDoList {
	private MyStack S = new Stack();

	@Override
	public void add(Object element) {
		S.push(element);	
	}

	@Override
	public Object remove() {
		Object t = S.pop();
		return t;
	}
	
	@Override
	public boolean isEmpty(){
		return S.isEmpty();
	}
}
