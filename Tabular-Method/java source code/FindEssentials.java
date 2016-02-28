import java.util.Vector;

public class FindEssentials {

	private Table t;
	private Vector<PrimeImplicant> essentials = new Vector<PrimeImplicant>();
	
	public String eString = new String();
	public Vector<Vector<PrimeImplicant>> allEssentials = new Vector<Vector<PrimeImplicant>>();

	public FindEssentials(Vector<PrimeImplicant> primes,
			Vector<Integer> minterms, int inputs) {
		t = new Table(primes, minterms, inputs);
	}

	public Vector<PrimeImplicant> minimize() {

		while (!t.isAllCoverd()) {
			if (findEssentials())
				continue;
			// if (rowDominans())
			// continue;
			// if (colDominans())
			// continue;
			Petrick pet = new Petrick();
			StringBuilder sb = new StringBuilder();
			for (PrimeImplicant p : essentials) {
				sb.append(p.stringTerm + " + ");
			}
			sb.delete(sb.length() - 3, sb.length());
			eString = sb.toString();
			essentials = pet.getEssentials(essentials, t);
			allEssentials = pet.allEssentials;
			return essentials;
		}
		return essentials;
	}

	private boolean findEssentials() {
		boolean flag = false; // return false if there is no essentials found

		/* loop on all minterms */
		for (Integer i : t.minterms) {
			int primeIndex = -1;

			if (!t.coverd[i] && t.availbleTerm[i]) {
				int counter = 0;
				// System.out.print(">> " + i + "covered by ");
				for (int j = 0; j < t.primes.size(); j++) {
					if (t.table[j][i]) {
						// System.out.print(" " + j);
						counter++;
						primeIndex = j;
					}
				}
				// System.out.print("\n");

				/*
				 * if it only covered by one primeImplicant this prime is
				 * essential
				 */
				if (counter == 1) {
					// System.out.println("essential" + primeIndex);
					flag = true;
					PrimeImplicant temp = t.primes.elementAt(primeIndex);
					temp.essential = true;
					t.primes.set(primeIndex, temp);
					essentials.addElement(temp);
					t.coverd[i] = true;
					t.availbleTerm[i] = false;

					/*
					 * mark all minterms that this essential primeImplicant
					 * covers
					 */
					for (Integer m : temp.covers) {
						t.coverd[m] = true;
						t.availbleTerm[m] = false;
						for (int j = 0; j < t.primes.size(); j++) {
							t.table[j][m] = false;
						}
					}
				}
			}
		}

		/*
		 * if there is a prime which is all his minterms are covered we can mark
		 * it as unavailable as it is not useful any more
		 */
		if (!t.isAllCoverd()) {
			for (int j = 0; j < t.primes.size(); j++) {
				PrimeImplicant p = t.primes.elementAt(j);
				if (!p.essential && p.availble) {
					p.availble = false;
					for (int k : t.minterms) {
						if (t.table[j][k]) {
							p.availble = true;
							break;
						}
					}
				}
			}
		}

		return flag;
	}

	private boolean rowDominans() {
		// TODO Auto-generated method stub
		return false;
	}

	private boolean colDominans() {
		// TODO Auto-generated method stub
		return false;
	}
}
