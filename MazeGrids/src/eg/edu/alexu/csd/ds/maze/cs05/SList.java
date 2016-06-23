package eg.edu.alexu.csd.ds.maze.cs05;

public class SList implements MyLinkedList{
	private SNode head;
	private int size;
	public void add(int index, Object element){
		if(index > size || index < 0){
			throw new ListException("List Out Of Bounds!");
		}
		SNode newNode = new SNode();
		newNode.setElement(element);
		if(index == 0){
			newNode.setNext(head);
			head = newNode;
		}
		else{
			SNode assist = head;
			for(int counter = 1; counter < index; counter ++){
				assist = assist.getNext();
			}
			newNode.setNext(assist.getNext());
			assist.setNext(newNode);
		}
		size ++;
	}
	
	public void add(Object element){
		if(head == null){
			head = new SNode();
			head.setElement(element);
		}
		else{
			SNode assist = head;
			while(assist.getNext() != null){
				assist = assist.getNext();
			}
			assist.setNext(new SNode());
			assist.getNext().setElement(element);
		}
		size ++;
	}
	
	public Object get(int index){
		if(index >= size || index < 0){
			throw new ListException("List Out Of Bounds!");
		}
		SNode assist = head;
		for(int counter = 1; counter <= index; counter ++){
			assist = assist.getNext();
		}
		return assist.getElement();
	}
	
	public void set(int index, Object element){
		if(index >= size || index < 0){
			throw new ListException("List Out Of Bounds!");
		}
		SNode assist = head;
		for(int counter = 1; counter <= index; counter ++){
			assist = assist.getNext();
		}
		assist.setElement(element);
	}
	
	public void clear(){
		head = null;
		size = 0;
	}
	
	public boolean isEmpty(){
		if(size == 0){
			return true;
		}
		return false;
	}
	
	public void remove(int index){
		if(index >= size || index < 0){
			throw new ListException("List Out Of Bounds!");
		}
		if(index == 0){
			SNode assist = head;
			head = head.getNext();
			assist.setNext(null);
		}
		else{
			SNode assist = head;
			for(int counter = 1; counter < index; counter ++){
				assist = assist.getNext();
			}
			SNode assist2 = assist.getNext();
			assist.setNext(assist2.getNext());
			assist2.setNext(null);
		}
		size --;
	}
	
	public int size(){
		return size;
	}
	
	public SList sublist(int fromIndex, int toIndex){
		if(fromIndex > toIndex){
			throw new ListException("From Index Can't Be More Than To Index!");
		}
		if(fromIndex < 0 || toIndex >= size){
			throw new ListException("List Out Of Bounds!");
		}
		SNode assist = head;
		for(int counter = 1; counter <= fromIndex; counter ++){
			assist = assist.getNext();
		}
		SList part = new SList();
		for(int counter = fromIndex; counter <= toIndex; counter ++){
			Object newNode = new Object();
			newNode = assist.getElement();
			part.add(newNode);
			assist = assist.getNext();
		}
		return part;
	}
	
	public boolean contains(Object o){
		SNode assist = head;
		while(assist != null){
			if(o == null && assist.getElement() == null){
				return true;
			}
			else{
				try{
					if(assist.getElement().equals(o)){
						return true;
					}
				}catch(NullPointerException e){
					
				}
			}
			assist = assist.getNext();
		}
		return false;
	}
}
