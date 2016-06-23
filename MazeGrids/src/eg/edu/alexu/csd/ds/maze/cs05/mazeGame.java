package eg.edu.alexu.csd.ds.maze.cs05;

import java.awt.*;
import java.awt.event.*;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.Scanner;

import javax.swing.*;

public class mazeGame extends JFrame{
	private JLabel  welcome1 , welcome2, row, column, error1, error2, error3 ,done1 ;
	private JTextField row1 , column1 , tsolve;
	private JButton wideMaze , narrowMaze , display , solve , exit;
	private int length=0 , width=0;
	private String[] mazeFromFile;
	private Point[][] doors;
	
	public mazeGame(){
		setLayout(new GridLayout(8,2));
		welcome1 = new JLabel("Welcome to ",SwingConstants.RIGHT);
		welcome2 = new JLabel("the maze game");
		add(welcome1);
		add(welcome2);
		row = new JLabel("Enter Length (Less than 40)");
		column = new JLabel("Enter Width (Less than 40)");
		row1 = new JTextField("0",15);
		column1 = new JTextField("0",15);
		add(row);
		add(row1);
		add(column);
		add(column1);
		makeWide a = new makeWide();
		wideMaze = new JButton("Make wide maze");
		error1 = new JLabel("");
		add(wideMaze);
		add(error1);
		wideMaze.addActionListener(a);
		narrowMaze = new JButton("Make narrow maze");
		error2 = new JLabel("");
		makeNarrow b = new makeNarrow();
		narrowMaze.addActionListener(b);
		add(narrowMaze);
		add(error2);
		display = new JButton("Display the map");
		error3 = new JLabel(" ");
		add(display);
		add(error3);
		Display c = new Display();
		display.addActionListener(c);
		solve = new JButton("Solve the map");
		tsolve = new JTextField("");
		add(solve);
		add(tsolve);
		Solve d = new Solve();
		done1 = new JLabel("",SwingConstants.RIGHT);
		
		solve.addActionListener(d);
		exit = new JButton("Exit");
		add(exit);
		add(done1);
		quit q = new quit();
		exit.addActionListener(q);
	}
	
	public class quit implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent arg0) {
			System.exit(0);
		}
		
	}
	
	public class Solve implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent arg0) {
			String S = tsolve.getText();
			String P = "eg.edu.alexu.csd.ds.maze.cs05."+S;
			error1.setText("");
			error2.setText("");
			error3.setText("");
			done1.setText("");
			Class<?> c = null;
			try {
				c = Class.forName(P);
			} catch (ClassNotFoundException e1) {
			}
			MazeSolver solver = null;
			try {
				solver = (MazeSolver) c.newInstance();
			} catch (InstantiationException | IllegalAccessException e) {
			}
			File file = new File("theMaze.txt"); 
			if (!file.exists()) {
				done1.setText("There is no file!!");
			}
			else{
				try {
					Scanner fscan = new Scanner(file);
					length = fscan.nextInt();
					width = fscan.nextInt();
					mazeFromFile = new String[length];
					String s = fscan.nextLine();
					for(int i=0;i<length;i++){
						mazeFromFile[i] = fscan.nextLine();
					}
					if(fscan.hasNextInt()){
						int doorlength = fscan.nextInt() , doorwidth = fscan.nextInt();
						doors = new Point[doorlength][doorwidth];
						for(int i=0;i<doorlength; i++){
							for(int j=0;j<doorwidth; j++){
								Point p = new Point();
								p.y = fscan.nextInt();
								p.x = fscan.nextInt();
								doors[i][j] = p;
							}
						}
					}
					
				} catch (Exception e) {
					done1.setText("Something wrong in the file recreate the maze!!");
				}

			}
			MyLinkedList path = solver.solve(mazeFromFile,length , width , doors);
			done1.setText("Solved!!");
			new mazeGUI(path,mazeFromFile,true);
		}
		
	}
	
	public class Display implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent c) {
			error1.setText("");
			error2.setText("");
			error3.setText("");
			done1.setText("");
			File file = new File("theMaze.txt"); 
			if (!file.exists()) {
				error3.setText("There is no file!!");
			}
			else{
				try {
					Scanner fscan = new Scanner(file);
					length = fscan.nextInt();
					width = fscan.nextInt();
					mazeFromFile = new String[length];
					String s = fscan.nextLine();
					for(int i=0;i<length;i++){
						mazeFromFile[i] = fscan.nextLine();
					}
					if(fscan.hasNextInt()){
						int doorlength = fscan.nextInt() , doorwidth = fscan.nextInt();
						doors = new Point[doorlength][doorwidth];
						for(int i=0;i<doorlength; i++){
							for(int j=0;j<doorwidth; j++){
								Point p = new Point();
								p.y = fscan.nextInt();
								p.x = fscan.nextInt();
								doors[i][j] = p;
							}
						}
					}
					error3.setText("Loaded!!");
					mazeGUI gui = new mazeGUI(null, mazeFromFile, false);
				} catch (Exception e) {
					error3.setText("Something wrong in the file recreate the maze!!");
				}

			}
		}
		
	}
	
	public class makeNarrow implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent b) {
			try{
				randomMap2 m = null;
				String [] maze = null;
				error1.setText("");
				error2.setText("");
				error3.setText("");
				done1.setText("");
				length = (int)(Double.parseDouble(row1.getText()));
				width = (int)(Double.parseDouble(column1.getText()));
				if(length == 0 && width == 0){
					m = new randomMap2();
					maze = m.randomMazeGenerator();
				}
				else if(length < 0 || width < 0){
					error2.setText("Enter postive numbers only");
				}
				else if((length < 5 || width < 5)|| (length > 40 || width > 40)){
					error2.setText("Enter numbers between 5 - 40");
				}
				else{
					m = new randomMap2(length , width);
					maze = new String[length];
					maze = m.randomMazeGenerator();
				}
				length = maze.length;
				width = maze[0].length();
				Point[][] doors = m.getDoors();
				File file = new File("theMaze.txt"); 
				if (!file.exists()) {
					file.createNewFile();
				}
				FileWriter writer = new FileWriter(file);
				writer.write(length + " " + width); 
			    writer.flush();
			    writer.write(System.getProperty( "line.separator" ));
			    writer.flush();
				for(int i=0;i<length;i++){
					writer.write(maze[i]); 
				    writer.flush();
				    writer.write(System.getProperty( "line.separator" ));
				    writer.flush();
				}
				if(doors != null){
					int doorlength = doors.length , doorwidth = doors[0].length;
					writer.write(doorlength + " " + doorwidth); 
				    writer.flush();
				    writer.write(System.getProperty( "line.separator" ));
				    writer.flush();
					for(int i=0;i<doors.length;i++){
						for(int j=0;j<doors[0].length;j++){
							Point p = new Point();
							p = doors[i][j];
							writer.write(p.x + " " + p.y + " "); 
						    writer.flush();
						}
						 writer.write(System.getProperty( "line.separator" ));
						 writer.flush();
					}
				}
				writer.close();
				error2.setText("Completed!!");
			}catch(Exception e){
					error2.setText("Enter numbers only");
			}			
		}
		
	}
	
	public class makeWide implements ActionListener{
		public void actionPerformed(ActionEvent a) {
			try{
				randomMap m = null;
				String [] maze = null;
				error1.setText("");
				error2.setText("");
				error3.setText("");
				done1.setText("");
				length = (int)(Double.parseDouble(row1.getText()));
				width = (int)(Double.parseDouble(column1.getText()));
				if(length == 0 && width == 0){
					m = new randomMap();
					maze = m.randomMazeGenerator();
				}
				else if(length < 0 || width < 0){
					error1.setText("Enter postive numbers only");
				}
				else if((length < 5 || width < 5)|| (length > 40 || width > 40)){
					error1.setText("Enter numbers between 5 - 40");
				}
				else{
					m = new randomMap(length , width);
					maze = new String[length];
					maze = m.randomMazeGenerator();
				}
				length = maze.length;
				width = maze[0].length();
				Point[][] doors = m.getDoors();
				File file = new File("theMaze.txt"); 
				if (!file.exists()) {
					file.createNewFile();
				}
				FileWriter writer = new FileWriter(file);
				writer.write(length + " " + width); 
			    writer.flush();
			    writer.write(System.getProperty( "line.separator" ));
			    writer.flush();
				for(int i=0;i<length;i++){
					writer.write(maze[i]); 
				    writer.flush();
				    writer.write(System.getProperty( "line.separator" ));
				    writer.flush();
				}
				if(doors != null){
					int doorlength = doors.length , doorwidth = doors[0].length;
					writer.write(doorlength + " " + doorwidth); 
				    writer.flush();
				    writer.write(System.getProperty( "line.separator" ));
				    writer.flush();
					for(int i=0;i<doors.length;i++){
						for(int j=0;j<doors[0].length;j++){
							Point p = new Point();
							p = doors[i][j];
							writer.write(p.x + " " + p.y + " "); 
						    writer.flush();
						}
						 writer.write(System.getProperty( "line.separator" ));
						 writer.flush();
					}
				}
				writer.close();
				error1.setText("Completed!!");
			}catch(Exception e){
					error1.setText("Enter numbers only");
			}
			
		}
	}
	
	public static void main(String[] args){
		mazeGame game = new mazeGame();
		game.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		game.setVisible(true);
		game.pack();
		game.setTitle("Maze");
	}
}
