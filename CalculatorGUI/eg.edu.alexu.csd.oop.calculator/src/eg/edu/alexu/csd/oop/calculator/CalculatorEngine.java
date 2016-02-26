package eg.edu.alexu.csd.oop.calculator;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.FileNotFoundException;
import java.io.IOException;

import javax.swing.JDialog;
import javax.swing.JFrame;

public class CalculatorEngine implements ActionListener {

	private static CalculatorEngine calci;
	private static MyCalculator clac;
	private GUI gui = new GUI();

	// Variables
	private final int MAX_INPUT_LENGTH = 50;
	private final int INPUT_MODE = 0;
	private final int RESULT_MODE = 1;
	private final int ERROR_MODE = 2;
	private int displayMode;

	private boolean clearOnNextDigit = false, newInput = false;
	private String lastOperator = "0";

	public static CalculatorEngine create() {
		clac = MyCalculator.create();
		if (calci == null)
			calci = new CalculatorEngine();
		return calci;
	}
	public static void destoryInstance(){
		calci = null;
   }
	private CalculatorEngine() {
		// activate ActionListener
		for (int i = 0; i < gui.jbnButtons.length; i++) {
			gui.jbnButtons[i].addActionListener(this);
		}
		gui.jmenuitemLoad.addActionListener(this);
		gui.jmenuitemSave.addActionListener(this);
		gui.jmenuitemAbout.addActionListener(this);
		gui.jmenuitemHistory.addActionListener(this);
		gui.jmenuitemExit.addActionListener(this);
		clearAll();
		// add WindowListener for closing frame and ending program
		gui.addWindowListener(new WindowAdapter() {
			public void windowClosed(WindowEvent e) {
				System.exit(0);
			}
		});
		gui.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	} // End of Contructor Calculator

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub

		// About
		if (e.getSource() == gui.jmenuitemAbout) {
			JDialog dlgAbout = new AboutDialog(gui, "About My Calculator", true);
			dlgAbout.setVisible(true);
		}// Exit
		else if (e.getSource() == gui.jmenuitemExit) {
			System.exit(0);
		}// history
		else if (e.getSource() == gui.jmenuitemHistory) {
			JDialog dlgHistory = new HistoryDialog(gui, "History", true);
			dlgHistory.setVisible(true);
		}// Save
		else if (e.getSource() == gui.jmenuitemSave) {
			SaveDialog save = new SaveDialog();
			String filePath = save.getPath();
			System.out.println("Save as file: " + filePath);
			try {
				clac.save();
			} catch (RuntimeException e1) {
				displayError("Error while Saving !!");
			}
		}// Load
		else if (e.getSource() == gui.jmenuitemLoad) {
			LoadDialog load = new LoadDialog();
			String filePath = load.getPath();
			System.out.println("Selected file: " + filePath);
			try {
				clac.load();
			} catch (RuntimeException e1) {
				displayError("File Not Found!!");
			} 
			clearOnNextDigit = false;
			String s = clac.current();
			if (s == null)
				displayError("no Current ( File Empty ) !!");
			else {
				gui.jlbHistory.setText(s);
				setDisplayString(s);
			}
			lastOperator = "1";
		}
		// Search for the button pressed until end of array or key found
		for (int i = 0; i < gui.jbnButtons.length; i++) {
			if (e.getSource() == gui.jbnButtons[i]) {
				switch (i) {
				case 0:
				case 1:
				case 2:
				case 3:
				case 4:
				case 5:
				case 6:
				case 7:
				case 8:
				case 9:
					addDigitToDisplay(i);
					break;
				case 10: // +/-
					signChange();
					break;
				case 11: // decimal point
					addDecimalPoint();
					break;
				case 12: // =
					processEquals();
					break;
				case 13: // divide
					addOperation("/");
					break;
				case 14: // *
					addOperation("*");
					break;
				case 15: // -
					addOperation("-");
					break;
				case 16: // +
					addOperation("+");
					break;
				case 17: // sqrt
					squareRoot();
					break;
				case 18: // 1/x
					div();
					break;
				case 19: // %
					addOperation("%");
					break;
				case 20: // backspace
					backSpace();
					break;
				case 21: // CE
					clearAll();
					break;
				case 22: // previous
					getPrevious();
					break;
				case 23: // current
					getCurrent();
					break;
				case 24: // next
					getNext();
					break;
				}// End switch
			}// End if
		}// End for

	}

	private void getNext() {
		
		displayMode = INPUT_MODE;
		clearOnNextDigit = false;
		String s = clac.next();
		if (s == null)
			displayError("There is no Next to display !!");
		else {
			gui.jlbHistory.setText(s);
			gui.jlbOutput.setText(s);
		}
		lastOperator = "1";
		newInput = false;
	}

	private void getPrevious() {

		displayMode = INPUT_MODE;
		clearOnNextDigit = false;
		String s = clac.prev();
		if (s == null)
			displayError("There is no Previous to display !!");
		else {
			gui.jlbHistory.setText(s);
			gui.jlbOutput.setText(s);
		}
		lastOperator = "1";
		newInput = false;
	}

	private void getCurrent() {

		displayMode = INPUT_MODE;
		clearOnNextDigit = false;
		String s = clac.current();
		if (s == null)
			displayError("There is no Current to display !!");
		else {
			gui.jlbHistory.setText(s);
			gui.jlbOutput.setText(s);
		}
		lastOperator = "1";
		newInput = false;
	}

	private void backSpace() {
		if (displayMode != ERROR_MODE) {
			setDisplayString(getDisplayString().substring(0,
					getDisplayString().length() - 1));

			if (getDisplayString().length() < 1)
				setDisplayString("0");
		}
	}

	private void div() {
		if (displayMode != ERROR_MODE) {
			try {
				if (Double.parseDouble(gui.jlbOutput.getText()) == 0) {
					displayError("Cannot divide by zero!");
				} else {
					double result = 1 / Double.parseDouble(gui.jlbOutput
							.getText());
					displayResult(result);
				}
			} catch (Exception ex) {
				displayError("Invalid input for function!");
				displayMode = ERROR_MODE;
			}
		}
	}

	private void squareRoot() {
		if (displayMode != ERROR_MODE) {
			try {
				if (getDisplayString().indexOf("-") == 0)
					displayError("Invalid input for function!");
				double result = Math.sqrt(Double.parseDouble(gui.jlbOutput
						.getText()));
				displayResult(result);
			} catch (Exception ex) {
				displayError("Invalid input for function!");
			}
		}
	}

	void setDisplayString(String s) {
		gui.jlbOutput.setText(s);
	}

	String getDisplayString() {
		return gui.jlbOutput.getText();
	}

	void displayResult(double result) {
		setDisplayString(Double.toString(result));
		displayMode = RESULT_MODE;
		clearOnNextDigit = true;
	}

	void displayError(String errorMessage) {
		setDisplayString(errorMessage);
		displayMode = ERROR_MODE;
		clearOnNextDigit = true;
	}

	void clearAll() {
		setDisplayString("0");
		clearOnNextDigit = true;
		displayMode = INPUT_MODE;
	}

	void addDigitToDisplay(int digit) {
		if (clearOnNextDigit)
			setDisplayString("");
		String inputString = getDisplayString();
		if (inputString.indexOf("0") == 0 && inputString.indexOf(".") != 1) {
			inputString = inputString.substring(1);
		}
		if ((!inputString.equals("0") || digit > 0)
				&& inputString.length() < MAX_INPUT_LENGTH) {
			setDisplayString(inputString + digit);
		}
		displayMode = INPUT_MODE;
		clearOnNextDigit = false;
		newInput = true;
	}

	void signChange() {
		try {
			if (displayMode == INPUT_MODE && !clearOnNextDigit
					|| displayMode == RESULT_MODE) {
				double numberInDisplay = Double.parseDouble(gui.jlbOutput
						.getText());

				if (numberInDisplay != 0)
					displayResult(-numberInDisplay);
			}
		} catch (Exception e) {
			displayMode = ERROR_MODE;
			displayError("Cannot change the sign for the expression!");
		}
	}

	void addDecimalPoint() {
		if (displayMode != ERROR_MODE) {
			displayMode = INPUT_MODE;
			clearOnNextDigit = false;
			String s = getDisplayString();
			boolean exist = false;
			for (int i = s.length() - 1; i >= 0; i--) {
				if (!Character.isDigit(s.charAt(i)) && s.charAt(i) == '.') {
					exist = true;
					break;
				}
				if (!Character.isDigit(s.charAt(i)))
					break;
			}
			if (!exist) {
				setDisplayString(new String(s + "."));
			}
		}
	}

	void addOperation(String op) {
		if (displayMode != ERROR_MODE) {
			String s = getDisplayString();
			boolean exist = false;
			for (int i = s.length() - 1; i >= 0; i--) {
				if (!Character.isDigit(s.charAt(i)) && s.charAt(i) != '.') {
					exist = true;
					break;
				}
				if (Character.isDigit(s.charAt(i)))
					break;
			}
			if (!exist) {
				setDisplayString(gui.jlbOutput.getText() + " " + op + " ");
				clearOnNextDigit = false;
				lastOperator = op;
			}
		}
	}

	void processEquals() {
		if (displayMode != ERROR_MODE && !lastOperator.equals("0")) {
			try {
				String expr = getDisplayString().replace(" ", "");
				if (newInput)
					clac.input(expr);

				String res = clac.getResult();
				setDisplayString(res);
				gui.jlbHistory.setText(clac.current() + " = " + res);
			}catch (Exception e) {
				displayError("Cannot divide by zero!");
			}
			displayMode = RESULT_MODE;
			clearOnNextDigit = true;
			lastOperator = "0";
		}
	}

	public static void main(String[] args) {
		CalculatorEngine.create();
		calci.gui.setTitle("My Calculator");
		calci.gui.setSize(241, 217);
		calci.gui.pack();
		calci.gui.setLocation(400, 100);
		calci.gui.setVisible(true);
		calci.gui.setResizable(false);
	}
}
