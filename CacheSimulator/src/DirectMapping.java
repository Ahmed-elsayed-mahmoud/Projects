
public class DirectMapping implements Mapping {

	private ICache cache;

	public DirectMapping(ICache cache) {
		this.cache = cache;
		cache.setCacheData(new int[cache.getCacheSize()][1]);
	}

	@Override
	public ICache doMapping(int[] blocks) {
		// TODO Auto-generated method stub
		int cacheSize = cache.getCacheSize();
		int [][] cacheData = cache.getCacheData();
		for(int i = 0 ; i < blocks.length; i++){
			cacheData[blocks[i]%cacheSize][0] = blocks[i];
		}
		cache.setCacheData(cacheData);
		return cache;
	}

	@Override
	public void setReplacementType(ReplacementStrategy type) {
		// TODO Auto-generated method stub
	}

}
