import java.util.Arrays;
import java.util.Vector;

public class PrimeImplicant {
	private int mainTerm;
	public Vector<Integer> diff = new Vector<Integer>();
	public Vector<Integer> covers = new Vector<Integer>();
	public int INPUTS;
	public int cost;
	public String[] varsName;
	public boolean prime = true;
	public boolean essential = false;
	public boolean availble = true;
	String stringTerm;

	public PrimeImplicant(String[] varsName) {
		this.varsName = varsName;
	}

	public int MainTerm() {
		return mainTerm;
	}

	public void setMainTerm(int mainTerm) {
		this.mainTerm = mainTerm;
	}

	/*
	 * insert in diff Vector sortef to make it easy when check for equality of
	 * dublicates
	 */
	public void insertsort(Integer d) {
		int i = 0;
		while (i < diff.size() && diff.elementAt(i) < d) {
			i++;
		}
		diff.add(i, d);
	}

	/*
	 * calculate cost, find minterms this prime covers and the string
	 * representation of it
	 */
	public void finit() {

		findTermsCovers();

		// string representation
		String[] term = new String[INPUTS];
		for (int i = 0; i < INPUTS; i++) {
			String s;
			if (varsName.length <= i) {
				Character c = (char) ('A' + i);
				s = c.toString();
			} else {
				if (varsName[i].length() > 1) {
					s = "(" + varsName[i] + ")";
				} else {
					s = varsName[i];
				}
			}
			term[i] = (((mainTerm >> (INPUTS - i - 1)) & 1)) == 1 ? s
					: (s + "'");
		}
		cost = INPUTS + 1;
		for (Integer i : diff) {
			term[INPUTS - log2(i) - 1] = "-";
			cost--;
		}

		StringBuilder builder = new StringBuilder();
		for (String s : term) {
			builder.append(s);
		}
		stringTerm = builder.toString();
		stringTerm = stringTerm.replace("-", "");
		if (stringTerm.length() == 0)
			stringTerm = "1";
		// System.out.println(stringTerm);
	}

	public boolean equals(PrimeImplicant p) {
		if (this.MainTerm() == p.MainTerm() && this.diff.equals(p.diff))
			return true;
		return false;
	}

	public void findTermsCovers() {
		// by find all combines form diff vector
		for (int i = 0; i <= diff.size(); i++) {
			combin(0, i, 0);
		}
	}

	private void combin(int indx, int k, int sum) {
		if (k == 0) {
			covers.add(sum + this.mainTerm);
			return;
		}
		if (indx >= diff.size())
			return;
		combin(indx + 1, k - 1, sum + diff.elementAt(indx));
		combin(indx + 1, k, sum);
	}

	// used in debug
	// intended to use to print minimizations steps
	public String print() {
		StringBuilder s = new StringBuilder();
		s.append(this.MainTerm() + " (");
		System.out.print(this.MainTerm() + " (");
		for (Integer d : this.diff) {
			System.out.print("," + d);
			s.append("," + d);
		}
		System.out.print(") -- ");
		for (Integer m : this.covers) {
			System.out.print(m + " ");
		}
		System.out.println();
		s.append("\n");
		return s.toString();
	}

	public static int log2(int x) {
		if (x < 0)
			throw new RuntimeException("negative number");
		int log = 0;
		while ((x >> log++) > 0)
			;
		return log - 2;
	}

	public static int countOnes(int x) {
		int counter = 0;
		while (x > 0) {
			counter += (x & 1);
			x = x >> 1;
		}
		return counter;
	}
}
