import java.util.LinkedList;
import java.util.Queue;

public class FIFOStrategy implements ReplacementStrategy{

	private Queue<Integer> q = new LinkedList<Integer>();
	@Override
	public void addNewBlock(int block) {
		q.add(block);
	}

	@Override
	public void update(int block) {
		
	}

	@Override
	public int replace(int newBlock, int[] set) {
		Queue<Integer> q2 = new LinkedList<Integer>();
		int target = 0;
		boolean found = false;
		while(!q.isEmpty() && !found){
			int removed = q.poll();
			for(int i=0;i<set.length;i++){
				if(set[i] == removed){
					target = i;
					found = true;
					break;
				}
			}
			if(!found){
				q2.add(removed);
			}
		}
		while(!q.isEmpty()){
			q2.add(q.poll());
		}
		while(!q2.isEmpty()){
			q.add(q2.poll());
		}
		q.add(newBlock);
		return target;
	}

	
}
