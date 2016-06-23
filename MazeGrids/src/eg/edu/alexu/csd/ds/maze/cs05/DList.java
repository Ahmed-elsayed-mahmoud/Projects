package eg.edu.alexu.csd.ds.maze.cs05;


public class DList implements MyLinkedList {
	private DNode header;
	private DNode trailer;
	private int size;
	public DList(){
		header = new DNode();
		trailer = new DNode();
		header.setNext(trailer);
		trailer.setPrev(header);
		size = 0;
	}
	
	public void add(int index, Object element){
		if(index > size || index < 0){
			throw new ListException("List Out Of Bounds!");
		}
		DNode assist = header;
		for(int counter = 0; counter < index; counter ++){
			assist = assist.getNext();
		}
		DNode newNode = new DNode();
		newNode.setElement(element);
		newNode.setNext(assist.getNext());
		newNode.setPrev(assist);
		assist.getNext().setPrev(newNode);
		assist.setNext(newNode);
		size ++;
	}
	
	public void add(Object element){
		DNode newNode = new DNode();
		newNode.setElement(element);
		newNode.setNext(trailer);
		newNode.setPrev(trailer.getPrev());
		trailer.getPrev().setNext(newNode);
		trailer.setPrev(newNode);
		size ++;
	}
	
	public Object get(int index){
		if(index >= size || index < 0){
			throw new ListException("List Out Of Bounds!");
		}
		DNode assist = header;
		for(int counter = 0; counter <= index; counter ++){
			assist = assist.getNext();
		}
		return assist.getElement();
	}
	
	public void set(int index, Object element){
		if(index >= size || index < 0){
			throw new ListException("List Out Of Bounds!");
		}
		DNode assist = header;
		for(int counter = 0; counter <= index; counter ++){
			assist = assist.getNext();
		}
		assist.setElement(element);
	}
	
	public void clear(){
		header.setNext(trailer);
		trailer.setPrev(header);
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
		DNode assist = header;
		for(int counter = 0; counter <= index; counter ++){
			assist = assist.getNext();
		}
		assist.getNext().setPrev(assist.getPrev());
		assist.getPrev().setNext(assist.getNext());
		assist.setNext(null);
		assist.setPrev(null);
		size --;
	}
	
	public int size(){
		return size;
	}
	
	public DList sublist(int fromIndex, int toIndex){
		if(fromIndex > toIndex){
			throw new ListException("From Index Can't Be More Than To Index!");
		}
		if(fromIndex < 0 || toIndex >= size){
			throw new ListException("List Out Of Bounds!");
		}
		DNode assist = header;
		for(int counter = 0; counter < fromIndex; counter ++){
			assist = assist.getNext();
		}
		DList part = new DList();
		for(int counter = fromIndex; counter <= toIndex; counter ++){
			assist = assist.getNext();
			Object newNode = new Object();
			newNode = assist.getElement();
			part.add(newNode);
		}
		return part;
	}
	
	public boolean contains(Object o){
		DNode assist = header.getNext();
		while(assist != trailer){
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
