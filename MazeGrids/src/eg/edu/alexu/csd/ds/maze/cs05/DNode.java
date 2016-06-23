package eg.edu.alexu.csd.ds.maze.cs05;

public class DNode {
	private Object element;
	private DNode next;
	private DNode prev;
	public void setElement(Object element){
		this.element = element;
	}
	public void setNext(DNode next){
		this.next = next;
	}
	public void setPrev(DNode prev){
		this.prev = prev;
	}
	public Object getElement(){
		return element;
	}
	public DNode getNext(){
		return next;
	}
	public DNode getPrev(){
		return prev;
	}

}
