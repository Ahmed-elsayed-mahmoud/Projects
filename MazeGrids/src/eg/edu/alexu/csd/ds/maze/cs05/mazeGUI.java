package eg.edu.alexu.csd.ds.maze.cs05;

import javax.swing.*;

import java.awt.*;
import java.awt.event.*;

public class mazeGUI extends JFrame {
	MyLinkedList path = new SList();
	String [] map;
	public mazeGUI(MyLinkedList path2, String[] ran, boolean b){
		path=path2;
		map=ran;
		int N = ran.length , M =ran[0].length();
		JFrame f = new JFrame();
		f.setBackground(Color.BLACK);
		f.add(new board(path,map,b));
		f.setDefaultCloseOperation(HIDE_ON_CLOSE);
		f.setSize(M*16+16,N*16+40);
		f.setVisible(true);
		f.setTitle("Maze");
	}
}
