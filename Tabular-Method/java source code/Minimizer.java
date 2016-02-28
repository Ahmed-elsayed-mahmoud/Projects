import java.util.Vector;


public class Minimizer implements OutPutsInterface {
	
	//to store all possible minimized function returned from petrick method
	public String[] allMini = new String[1];
	
	public String[] minimized(int inputs, Vector<Integer> minterms, Vector<Integer> dontCares,
			String[] varsName) {
		
		// handle the case of zero (no minterms)
		if (minterms.size() == 0){
				return ((new String[] {"0"}));
		}
		
		// first find all primes
		FindPrimes finder = new FindPrimes(inputs, minterms, dontCares, varsName);
		finder.findPrimes();
		
		// Second find all possible essentials
		FindEssentials f = new FindEssentials(finder.primes, minterms, inputs);
		Vector<PrimeImplicant> es = f.minimize();
		
		// minimum expression string array
		String[] ans = new String[es.size()];
		for (int i=0; i<es.size(); i++){
			PrimeImplicant p = es.get(i);
			ans[i] = p.stringTerm;
		}
		
		// all possible solution string array
		allMini = new String[f.allEssentials.size()];
		for (int i=0; i<f.allEssentials.size(); i++){
			StringBuilder sb = new StringBuilder(f.eString);
			for (PrimeImplicant p : f.allEssentials.get(i)){
				if (f.eString.length()>0)
					sb.append(" + ");
				sb.append(p.stringTerm);
			}
			allMini[i] = sb.toString();
		}
		
		return ans;
	}

}
