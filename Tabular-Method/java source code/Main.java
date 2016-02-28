import java.util.Arrays;
import java.util.Vector;


public class Main {

	public static void main(String[] args) {
		int inputs = 4;
		Vector<Integer> minterms = new Vector<Integer>();
		minterms.add(0);
		minterms.add(1);
		minterms.add(2);
		minterms.add(5);
		minterms.add(6);
		minterms.add(7);
		minterms.add(8);
		minterms.add(9);
		minterms.add(10);
		minterms.add(14);
		
		//int[] minterms = {};
		Vector<Integer> dontCare = new Vector<Integer>();
		OutPutsInterface solver = new Minimizer();
		String[] ans = solver.minimized(inputs, minterms, dontCare, new String[] {});
		System.out.println(Arrays.toString(ans));

	}

}
