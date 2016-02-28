import java.util.Vector;

public interface OutPutsInterface {

	/*
	 * int inputs
	 * number of inputs (1, 2, 3, 4 ...)
	 * it's prefered not to accept a big number 
	 * say 15 is the maximum number of inputs
	 */
	
	/*
	 * int[] minterms
	 * array of integers represents the minterms numbers e.g {1, 3, 5, 10} 
	 */
	
	/*
	 * int[] dontCare
	 * array of integers represents the don't cares minterms numbers e.g {1, 3, 5, 10}
	 * if there is no don't cares return an array of size zero {} 
	 */
	
	/*
	 * String varsName
	 * optional to change the names of the variables as the user wishes 
	 * if returned an empty array, the program would follow the default
	 * manner i.e (A, B, C, D .....) 
	 */
	
	/*
	 * returns array of Strings each String represents essential implicant e.g {"AB",
	 * "A'BC", "C'D'"}
	 */
	public String[] minimized(int inputs, Vector<Integer> minterms, Vector<Integer> dontCares,
			String[] varsName);
}
