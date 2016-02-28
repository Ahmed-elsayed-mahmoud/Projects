import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Vector;

public class FindPrimes {
	public Vector<PrimeImplicant> primes;
	int INPUTS;
	public String[] varsName;
	Vector<PrimeImplicant>[] groups;

	// StringBuilder sb = new StringBuilder();

	public FindPrimes(int numberOfInputs, Vector<Integer> minTerms,
			Vector<Integer> dontCare, String[] varsName) {

		// initialize
		this.varsName = varsName;
		INPUTS = numberOfInputs;
		groups = new Vector[INPUTS + 2];
		primes = new Vector<PrimeImplicant>();

		/*
		 * divide minterms into groups relative to number of ones in the binary
		 * representation
		 */
		for (int m : minTerms) {
			int ones = PrimeImplicant.countOnes(m);
			PrimeImplicant temp = new PrimeImplicant(varsName);
			temp.INPUTS = INPUTS;
			temp.setMainTerm(m);
			if (groups[ones] == null) {
				groups[ones] = new Vector<PrimeImplicant>();
			}
			groups[ones].add(temp);
		}
		// the same with don't cares
		for (int m : dontCare) {
			int ones = PrimeImplicant.countOnes(m);
			PrimeImplicant temp = new PrimeImplicant(varsName);
			temp.setMainTerm(m);
			if (groups[ones] == null) {
				groups[ones] = new Vector<PrimeImplicant>();
			}
			groups[ones].addElement(temp);
		}

	}

	public void findPrimes() {
		boolean flag = true;
		int c = 0; // counter of levels

		/*
		 * maximum number of levels are INPUTS if no new group formed no need to
		 * continue loop (flag = false)
		 */
		while (c++ <= INPUTS && flag) {
			Vector<PrimeImplicant>[] newGroups = new Vector[INPUTS + 2];
			flag = false;

			// loop on all groups
			for (int i = 0; i <= INPUTS; i++) {
				if (groups[i] != null) {
					for (PrimeImplicant p1 : groups[i]) {

						// to make sure not to go out of the array bounds
						if (i < INPUTS && groups[i + 1] != null) {
							for (PrimeImplicant p2 : groups[i + 1]) {
								if (p1.MainTerm() < p2.MainTerm()
										&& PrimeImplicant.countOnes(p2
												.MainTerm() - p1.MainTerm()) == 1
										&& p1.diff.equals(p2.diff)) {

									p1.prime = false;
									p2.prime = false;
									PrimeImplicant newP = new PrimeImplicant(
											varsName);
									newP.setMainTerm(p1.MainTerm());
									newP.diff = (Vector<Integer>) p1.diff
											.clone();
									newP.insertsort(p2.MainTerm()
											- p1.MainTerm());
									newP.INPUTS = INPUTS;
									if (newGroups[i] == null) {
										newGroups[i] = new Vector<PrimeImplicant>();
									}
									// to prevent duplicates
									if (!vectorContains(newGroups[i], newP)) {
										newGroups[i].add(newP);
										// newP.print();
										flag = true;
									}
								}
							}
						}
						if (p1.prime) {
							p1.finit();
							// System.out.print("prime ");
							// p1.print();
							primes.addElement(p1);
						}
					}
				}
			}
			groups = newGroups;
		}
	}

	public static boolean vectorContains(Vector<PrimeImplicant> v,
			PrimeImplicant p) {
		for (PrimeImplicant p1 : v) {
			if (p.equals(p1))
				return true;
		}
		return false;
	}

}
