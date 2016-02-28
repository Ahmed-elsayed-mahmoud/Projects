import java.util.Arrays;
import java.util.Vector;

/*
 * This is a modified version of Petrick method:
 * ============================================ 
 * instead of evaluate a long boolean expression and take care of boolean algebra,
 * we just calculate all possible combinations of primes implicants then check
 * if the combination covers all uncovered minterms we add it to possible solution
 * then evaluate its cost to compare it with other valid combinations
 * return the minimum cost one  
 */

public class Petrick {

	Vector<PrimeImplicant> primes = new Vector<PrimeImplicant>();
	Vector<Integer> notCovered = new Vector<Integer>();
	Table T;
	int minCost = Integer.MAX_VALUE;
	Vector<Integer> minCostPrimes = new Vector<Integer>();
	Vector<Vector<PrimeImplicant>> allEssentials = new Vector<Vector<PrimeImplicant>>();

	public Vector<PrimeImplicant> getEssentials(
			Vector<PrimeImplicant> essentials, Table t) {
		T = t;

		// primes that are availble and not marked as essentials
		for (PrimeImplicant p : t.primes) {
			if (!p.essential && p.availble)
				primes.add(p);
		}
		// minterms that not coverd yet
		for (int i = 0; i < T.coverd.length; i++) {
			if (!T.coverd[i] && T.availbleTerm[i]) {
				// System.out.print(i + " ");
				notCovered.addElement(i);
			}
		}

		for (int i = 1; i <= primes.size(); i++) {
			combin(0, i, new Vector<Integer>());
		}
		
		for (Integer i : minCostPrimes)
			essentials.add(primes.get(i));
		return essentials;
	}

	private void combin(int indx, int k, Vector<Integer> covers) {
		if (k == 0) {
			if (coversAll(covers)) {
				int cost = 0;
				for (Integer ind : covers) {
					cost += primes.get(ind).cost;
				}
				if (minCost > cost) {
					minCost = cost;
					minCostPrimes = covers;
				}
				Vector<PrimeImplicant> v = new Vector<PrimeImplicant>();
				for (Integer i : covers)
					v.add(primes.get(i));
				allEssentials.addElement(v);
			}
			return;
		}
		if (indx >= primes.size())
			return;
		combin(indx + 1, k, (Vector<Integer>) covers.clone());
		covers.add(indx);
		combin(indx + 1, k - 1, (Vector<Integer>) covers.clone());
	}

	private boolean coversAll(Vector<Integer> covers) {
		boolean[] covered = T.coverd.clone();
		for (Integer i : covers) {
			PrimeImplicant p = primes.get(i);
			for (Integer j : p.covers) {
				covered[j] = true;
			}
		}
		int counter = 0;
		for (Integer i : notCovered) {
			if (covered[i])
				counter++;
		}
		return (counter == notCovered.size());
	}

}
