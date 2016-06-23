public class FullyAssociative implements Mapping {

	private ICache cache;
	private ReplacementStrategy strategy;
	
	public FullyAssociative(ICache cache) {
		this.cache = cache;
	}

	@Override
	public ICache doMapping(int[] blocks) {
		// TODO Auto-generated method stub
		int[][] cacheData = cache.getCacheData();
	    boolean inserted = false;
	    
	    for(int block : blocks){
	    	inserted = false;
		    for(int i=0; i<cacheData[0].length; i++){
		    	if(cacheData[0][i] == block){
		    		strategy.update(block);
		    		inserted = true;
		    		break;
		    	}
		    	else if(cacheData[0][i] == -1){
		    		cacheData[0][i] = block;
		    		strategy.addNewBlock(block);
		    		inserted = true;
		    		break;
		    	}
		    }
		    if(! inserted){
		    	int index = replace(block, cacheData);
		    	cacheData[0][index] = block;
		    }
	    }
	    cache.setCacheData(cacheData);
	 
		return cache;
	}

	private int replace(int block, int[][] cacheData) {
		return strategy.replace(block, cacheData[0]);
	}

	@Override
	public void setReplacementType(ReplacementStrategy type) {
		// TODO Auto-generated method stub
		strategy = type;
	}
}
