package eg.edu.alexu.csd.ds.maze.cs05;

public class SNode {
	private Object element;
	private SNode next;
	public void setElement(Object element){
		this.element = element;
	}
	public void setNext(SNode next){
		this.next = next;
	}
	public Object getElement(){
		return element;
	}
	public SNode getNext(){
		return next;
	}
}
