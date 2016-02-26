package eg.edu.alexu.csd.oop.calculator;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class MyCalculator implements Calculator {
	//objects
	private static MyCalculator myCal;
	private History history = new History();
	//variables
	private int index = -1;
	private double result = 0;
	private String input = "", filePath = "caculator.txt";
	private BufferedReader br;

	public static MyCalculator create() {
		if (myCal == null)
			myCal = new MyCalculator();
		return myCal;
	}
	public static void destoryInstance(){
		myCal = null;
   }
	private MyCalculator() {

	}

	@Override
	public void input(String input) {
		// TODO Auto-generated method stub
		if(index == 4){
			history.deleteAtIndex(0);
			index--;
		}
		history.addAtIndex(++index, input);
		for (int i = History.size() - 1; i > index; i--) {
			history.deleteAtIndex(i);
		}
	}

	@Override
	public String getResult(){
		input = History.getAtIndex(index);
		// TODO Auto-generated method stub
		Operations oper = new Operations();
		/* Create stacks for operators and operands */
		Stack<Integer> op = new Stack<Integer>();
		Stack<Double> val = new Stack<Double>();
		/* Create temporary stacks for operators and operands */
		Stack<Integer> optmp = new Stack<Integer>();
		Stack<Double> valtmp = new Stack<Double>();

		input = "0" + input;
		input = input.replaceAll("-", "+-");
		/* Store operands and operators in respective stacks */
		String temp = "";
		for (int i = 0; i < input.length(); i++) {
			char ch = input.charAt(i);
			if (ch == '-')
				temp = "-" + temp;
			else if (ch != '+' && ch != '*' && ch != '/' && ch != '%')
				temp = temp + ch;
			else {
				val.push(Double.parseDouble(temp));
				op.push((int) ch);
				temp = "";
			}
		}
		val.push(Double.parseDouble(temp));
		/* Create char array of operators as per precedence */
		/* -ve sign is already taken care of while storing */
		char operators[] = { '/', '%', '*', '+' };
		/* Evaluation of expression */
		for (int i = 0; i < 4; i++) {
			boolean it = false;
			while (!op.isEmpty()) {
				int optr = op.pop();
				double v1 = val.pop();
				double v2 = val.pop();
				if (optr == operators[i]) {
					/* if operator matches evaluate and store in temporary stack */
					if (i == 0){
						try {
							{
								valtmp.push(oper.divide(v2, v1));
								it = true;
								break;
							}
						} catch (Exception e) {
							throw new RuntimeException();
						}
					} else if (i == 1) {
						valtmp.push(oper.modd(v2, v1));
						it = true;
						break;
					} else if (i == 2) {
						valtmp.push(oper.multiply(v2, v1));
						it = true;
						break;
					} else if (i == 3) {
						valtmp.push(oper.add(v2, v1));
						it = true;
						break;
					}
				} else {
					valtmp.push(v1);
					val.push(v2);
					optmp.push(optr);
				}
			}
			/* Push back all elements from temporary stacks to main stacks */
			while (!valtmp.isEmpty())
				val.push(valtmp.pop());
			while (!optmp.isEmpty())
				op.push(optmp.pop());
			/* Iterate again for same operator */
			if (it)
				i--;
		}
		result = val.pop();
		try {
			return Double.toString(this.result);
		} catch (Exception e) {
			throw new RuntimeException("Last result wasn't found");
		}
	}

	@Override
	public String current() {
		// TODO Auto-generated method stub
		if (index < 0)
			return null;
		return History.getAtIndex(this.index);
	}

	@Override
	public String prev() {
		// TODO Auto-generated method stub
		if (index <= 0)
			return null;
		this.index--;
		return History.getAtIndex(this.index);
	}

	@Override
	public String next() {
		// TODO Auto-generated method stub
		if (index == History.size() - 1)
			return null;
		this.index++;
		return History.getAtIndex(this.index);
	}

	@Override
	public void save() {
		// TODO Auto-generated method stub
		try {
			if(history.getPath() != null)
				filePath = history.getPath();
			else
				filePath = "caculator.txt";
		} catch (Exception t) {
			filePath = "caculator.txt";
		}
		BufferedWriter output = null;
		try {
			File file = new File(filePath);
			output = new BufferedWriter(new FileWriter(file));
			for (int i = index >= 5 ? index - 4 : 0; i <= index; i++) {
				output.write(History.getAtIndex(i));
				output.newLine();
			}
			output.close();
		} catch (IOException e) {
			throw new RuntimeException("invalid path or list is empty");
		}
	}

	@Override
	public void load() {
		// TODO Auto-generated method stub
		try {
			if(history.getPath() != null)
				filePath = history.getPath();
			else
				filePath = "caculator.txt";
		} catch (Exception t) {
			filePath = "caculator.txt";
		}
		FileInputStream fstream = null;
		try {
			fstream = new FileInputStream(filePath);
			br = new BufferedReader(new InputStreamReader(fstream));
			String strLine = "";
			history.clearAllHistory();
			index = -1;
			while ((strLine = br.readLine()) != null) {
				history.add(strLine);
				index++;
			}
			
		
		} catch (IOException e) {
			throw new RuntimeException("File Not Found!!");
		}
	
	}

}
