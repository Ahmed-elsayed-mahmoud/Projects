
public class Cache implements ICache{

	private int cacheSize, numOfSets;
	private int[][] cacheData;
	@Override
	public void setCacheSize(int size) {
		this.cacheSize = size;
	}

	@Override
	public void setNumOfSets(int sets) {
		this.numOfSets = sets;
	}

	@Override
	public int getNumOfSets() {
		return numOfSets;
	}


	@Override
	public int[][] getCacheData() {
		return cacheData;
	}

	@Override
	public void createCache() {
		cacheData = new int[numOfSets][cacheSize / numOfSets];
		for(int i=0; i<cacheData.length; i++){
			for(int j=0; j<cacheData[0].length; j++){
				cacheData[i][j] = -1;
			}
		}
	}
	
	@Override
	public void printCache() {
		System.out.println("The cache contents:");
		for(int i=0; i<cacheData.length; i++){
//			for(int j=0; j<cacheData[0].length; j++){
//				System.out.print("---");
//			}
			System.out.println();
			if(numOfSets > 0) System.out.print("Set " + i );
			for(int j=0; j<cacheData[0].length; j++){
				if(j == 0)System.out.print("|");
				if(cacheData[i][j] == -1) System.out.print("_|");
				else System.out.print(cacheData[i][j] + "|");
			}
		}
		System.out.println();
	}

	@Override
	public int getCacheSize() {
		return cacheSize;
	}

	@Override
	public void setCacheData(int[][] data) {
		// TODO Auto-generated method stub
		cacheData = data;
	}

}
