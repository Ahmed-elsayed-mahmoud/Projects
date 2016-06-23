
public interface Mapping {
 
	/**
	 * Apply Mapping on the cache 
	 * @param cache
	 * @param blocks
	 * @param replacementType
	 * @return ICache
	 */
	public ICache doMapping( int[] blocks);
	/**
	 * set the type of replacement
	 * @param type
	 */
	public void setReplacementType(ReplacementStrategy type);
	
}
