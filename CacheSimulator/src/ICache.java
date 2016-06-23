
public interface ICache {

	/**
	 * This method sets the size of the cache
	 * i.e total number of blocks in cache
	 * @param size
	 */
	public void setCacheSize(int size);
	/**
	 * This method sets the number of sets n the cache
	 * In case of Fully associative mapping it will be 1
	 * @param sets
	 */
	public void setNumOfSets(int sets);
	/**
	 * returns the number of sets
	 * @return numOfSets
	 */
	public int getNumOfSets();
	/**
	 * returns the number of blocks in the cache
	 * @return cachSize
	 */
	public int getCacheSize();
	
	/**
	 * you can set the cache 2D array
	 * 
	 */
	public void createCache();
	/**
	 * returns a 2D array with the data
	 * @return Array
	 */
	public int[][] getCacheData();
	/**
	 * Here you can print the cache
	 */
	public void printCache();
	
	public void setCacheData(int[][] data);
}
