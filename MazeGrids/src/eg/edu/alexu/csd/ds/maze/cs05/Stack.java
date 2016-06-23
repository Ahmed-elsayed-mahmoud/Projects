package eg.edu.alexu.csd.ds.maze.cs05;

public class Stack implements MyStack{
	private MyLinkedList stack = new MySingleLinkedList();

	@Override
	public void add(int index, Object element) {
		stack.add(index, element);
	}

	@Override
	public Object pop() {
		Object element;
		element = stack.get(0);
		stack.remove(0);
		return element;
	}

	@Override
	public Object peek() {
		Object element;
		element = stack.get(0);
		return element;
	}

	@Override
	public void push(Object element) {
		stack.add(0, element);
	}

	@Override
	public boolean isEmpty() {
		return stack.isEmpty();
	}

	@Override
	public int size() {
		return stack.size();
	}

}
