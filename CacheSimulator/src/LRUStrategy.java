import java.util.ArrayList;
import java.util.List;

public class LRUStrategy implements ReplacementStrategy{

	private List<Block> pq = new ArrayList<Block>();
	
	@Override
	public void addNewBlock(int block) {
		for(Block block2 : pq){
			block2.setlastUsage(block2.getlastUsage()+1);
		}
		pq.add(new Block(block));
	}

	@Override
	public void update(int block) {
		for(Block block2 : pq){
			if(block2.getBlockNumber() == block){
				block2.setlastUsage(1);
			}
			else{
				block2.setlastUsage(block2.getlastUsage()+1);
			}
		}
	}

	@Override
	public int replace(int newBlock, int[] set) {
		int target = 0;
		int max = 0;
		int index = 0;
		for(int i=0;i<set.length;i++){
			for(Block block : pq){
				if(block.getBlockNumber() == set[i]){
					if(max<block.getlastUsage()){
						max = block.getlastUsage();
						target = i;
						index = pq.indexOf(block);
						break;
					}
				}
			}
		}
		pq.remove(index);
		this.addNewBlock(newBlock);
		return target;
	}

	private class Block{
		
		private int lastUsage = 0 , blockNumber = 0;
		
		public Block(int blockNumber){
			this.blockNumber = blockNumber;
			lastUsage = 1;
		}
		
		public int getBlockNumber(){
			return this.blockNumber;
		}
		public int getlastUsage(){
			return this.lastUsage;
		}
		
		public void setlastUsage(int num){
			this.lastUsage = num;
		}
	}
	
}
