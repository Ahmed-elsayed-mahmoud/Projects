public class SetAssociative implements Mapping {

	private ICache cache;
	private ReplacementStrategy strategy ; 
	private int numOfSets;
	private int numOfBloks;

	public SetAssociative(ICache cache) {
		this.cache = cache;
	}

	@Override
	public ICache doMapping(int[] blocks) {
		// TODO Auto-generated method stub
		int [][] temp = cache.getCacheData();
		numOfSets = cache.getNumOfSets();
		numOfBloks = cache.getCacheSize()/numOfSets;
		for(int i = 0 ; i < blocks.length ;i++){
			int setNum = blocks[i]%numOfSets;
			int t = setNum;
			int [][] tr = new int[1][temp[0].length];
			tr[0]=temp[t];
			ICache newCache = new Cache();
			newCache.setCacheData(tr);
			Mapping fully = new FullyAssociative(newCache);
			fully.setReplacementType(strategy);
			int[] input = new int[1];
			input[0]=blocks[i];
			ICache retCache = fully.doMapping(input);
			temp[t]=retCache.getCacheData()[0];
			cache.setCacheData(temp);
		}
		return cache;
	}

	@Override
	public void setReplacementType(ReplacementStrategy type) {
		// TODO Auto-generated method stub
		strategy = type;
	}

}
