import java.util.Arrays;
import java.util.Vector;

public class Table {
	
	Vector<PrimeImplicant> primes;
	public Vector<Integer> minterms;
	int INPUTS;
	boolean[] coverd;
	boolean[] availbleTerm;
	boolean[][] table;

	Table(Vector<PrimeImplicant> primes, Vector<Integer> minterms, int inputs) {
		this.primes = primes;
		this.minterms = minterms;
		INPUTS = inputs;
		coverd = new boolean[1 << INPUTS];
		availbleTerm = new boolean[1 << INPUTS];
		table = new boolean[primes.size()][1 << INPUTS];

		Arrays.fill(coverd, false);
		Arrays.fill(availbleTerm, false);
		for (int m : minterms) {
			availbleTerm[m] = true;
		}

		for (int i = 0; i < primes.size(); i++) {
			Arrays.fill(table[i], false);
		}

		for (int i = 0; i < primes.size(); i++) {
			PrimeImplicant p = primes.get(i);
			for (Integer m : p.covers) {
				//System.out.println(m + " covered by " + i );
				table[i][m] = true;
			}
		}

	}

	public boolean isAllCoverd() {
		for (Integer i : minterms) {
			if (!coverd[i] && availbleTerm[i])
				return false;
		}
		return true;
	}
}
