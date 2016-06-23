package eg.edu.alexu.csd.ds.maze.cs05;

public class MySingleLinkedList implements MyLinkedList{

	private singleListNode head=null;
	private singleListNode tail=null;
	int size=0;

	@Override
	public void add(int index, Object element) {
		singleListNode newnode = new singleListNode(element);
		if(index > size || index <0){
			if(size == 0){
				throw new RuntimeException("The list is empty!");
			}
			else
				throw new RuntimeException("The value is invalid!");
		}
		if(index == 0){
			if(size==0){
				newnode.next=tail;
				head = newnode;
				tail=newnode;
			}
			else{
				newnode.next=head;
				head = newnode;
				head.next = newnode.next;
			}
		}
		else{
			singleListNode pointer = head;
			for(int i=0; i<index-1; i++){
				pointer=pointer.next;
			}
			newnode.next=pointer.next;
			pointer.next=newnode;
			if(size==index){
				tail=newnode;
			}
		}
		size++;
	}

	@Override
	public void add(Object element) {
		singleListNode newnode = new singleListNode(element);
		if(size==0){
			newnode.next=tail;
			head = newnode;
			tail=newnode;
		}
		else{
			singleListNode pointer = head;
			for(int i=0;i<size-1;i++){
				pointer=pointer.next;
			}
			pointer.next = newnode;
			newnode.next = null;
			tail = newnode;
		}
		size++;		
	}

	@Override
	public Object get(int index) {
		if(index > size || index <0){
			if(size == 0){
				throw new RuntimeException("The list is empty!");
			}
			else
				throw new RuntimeException("The value is invalid!");
		}
		singleListNode pointer = head;
			for(int i=0;i<index;i++){
				pointer=pointer.next;
		}
		return pointer.value;
				
			
	}

	@Override
	public void set(int index, Object element) {
		if(index > size || index <0){
			if(size == 0){
				throw new RuntimeException("The list is empty!");
			}
			else
				throw new RuntimeException("The value is invalid!");
		}
		singleListNode pointer = head;
		for(int i=0;i<index;i++){
			pointer=pointer.next;
		}
		pointer.value = element;	
	}

	@Override
	public void clear() {
		head=null;
		tail=null;
		size=0;
	}

	@Override
	public boolean isEmpty() {
		if(size == 0)
			return true;
		else
			return false;
	}

	@Override
	public void remove(int index) {
		if(index > size || index <0){
			if(size == 0){
				throw new RuntimeException("The list is empty!");
			}
			else
				throw new RuntimeException("The value is invalid!");
		}
		if(size>0){
			singleListNode pointer = head;
			if(index==0){
				if(size==1){
					head.next=null;
					head=null;
					tail = null;
				}
				
				if(size>1){
					pointer=pointer.next;
					head.next = null;
					head=pointer;
					head.next=pointer.next;
				}
				size--;
				
			}
			else{
				for(int i=0; i<index-1; i++){
					pointer=pointer.next;
				}
				singleListNode indexnode ;
				indexnode = pointer.next;
				pointer.next = indexnode.next;
				indexnode.next = null;
				size--;
			}
		}	
	}

	@Override
	public int size() {
		return size;
	}

	@Override
	public MyLinkedList sublist(int fromIndex, int toIndex) {
		if(size == 0){
			throw new RuntimeException("The list is empty!");
		}
		if(fromIndex > toIndex){
				throw new RuntimeException("FromIndex is greater than to index!");
		}
		MySingleLinkedList sublist = new MySingleLinkedList();
			if(fromIndex <= toIndex){
				singleListNode pointermain = head;
				for(int i=0; i<toIndex+1;i++){
					if(i>=fromIndex){
						singleListNode pointerSub = new singleListNode(null);
						pointerSub.value = pointermain.value;
						sublist.add(pointerSub.value);
					}
					pointermain=pointermain.next;
				}
			}
		return sublist;
	}

	@Override
	public boolean contains(Object o) {
		if(o == null){
			throw new NullPointerException();
		}
		singleListNode pointer = head;
		for(int i=0; i<size;i++){
			if(pointer.value.equals(o)){
				return true;
			}
			pointer=pointer.next;
		}
		return false;
	}

}
