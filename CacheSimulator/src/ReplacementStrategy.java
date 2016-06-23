
public interface ReplacementStrategy {
	
	/**
	 * add the blocks to cache according to type of replacement
	 * @param block
	 */
	public void addNewBlock(int block);
	
	/**
	 * update the information of the blocks in cash(used only in LRU)
	 * if the block is already in cache
	 * @param block
	 */
	public void update(int block);
	
	/**
	 * replace old block with new block according to type of replacement
	 * @param newBlock
	 * @param set
	 * @return index were to replace in that set
	 */
	public int replace(int newBlock,int[] set);
}
