package eg.edu.alexu.csd.oop.calculator;

import java.util.ArrayList;

public class History {

	private static ArrayList<String> expr;
	public static String path;

	public History() {
		expr = new ArrayList<String>();
	}
	public void deleteAtIndex(int index) {
		expr.remove(index);
	}
	public void addAtIndex(int index, String input) {
		expr.add(index, input);
	}
	public void add(String input) {
		expr.add(input);
	}
	public static int size() {
		return expr.size();
	}
	public static String getAtIndex(int index) {
		return expr.get(index);
	}
	public void clearAllHistory() {
		expr.clear();
	}
	public String getPath() {
		return path;
	}
}
