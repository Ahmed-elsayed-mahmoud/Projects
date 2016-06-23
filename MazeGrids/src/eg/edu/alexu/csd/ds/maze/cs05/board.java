package eg.edu.alexu.csd.ds.maze.cs05;

import java.awt.*;
import java.awt.event.*;
import java.util.*;

import javax.swing.*;
import javax.swing.Timer;

public class board extends JPanel{
	
	private map m;
	
	public board(MyLinkedList path, String[] map, boolean b){
		m = new map(path,map,b);
	}
	
	public void paint (Graphics g){
		super.paint(g);
		int l = m.getlength() , w = m.getwidth() ,red = 0 ,blue = 0 ,green = 0,counter =0;
		boolean LastIsDoor = false;
		Random rand = new Random();
		for(int i=0;i<l;i++){
			for(int j=0;j<w;j++){
				if(m.getMap(i, j).equals("#")){
					g.setColor(Color.BLACK);
					g.fillRect(j*16, i*16, 16, 16);
				}
				else if(m.getMap(i, j).equals(".")){
					g.setColor(Color.WHITE);
					g.fillRect(j*16, i*16, 16, 16);
				}
				else if(m.getMap(i, j).equals("S")){
					g.setColor(Color.GREEN);
					g.fillRect(j*16, i*16, 16, 16);
				}
				else if(m.getMap(i, j).equals("E")){
					g.setColor(Color.BLUE);
					g.fillRect(j*16, i*16, 16, 16);
				}
				else if(m.getMap(i, j).equals("+")){
					g.setColor(Color.YELLOW);
					g.fillOval(j*16, i*16, 16, 16);
				}
				else if(m.getMap(i, j).equals("X")){
					g.setColor(Color.CYAN);
					g.fillOval(j*16, i*16, 16, 16);
				}
				else if(m.getMap(i, j).equals("G")){
					g.setColor(Color.ORANGE);
					g.fillOval(j*16, i*16, 16, 16);
				}
				else if(m.getMap(i, j).equals("O")){
					red = rand.nextInt(254)+1;
					blue = rand.nextInt(254)+1;
					green = rand.nextInt(254)+1;
					g.setColor(new Color(red,green,blue));
					g.fillOval(j*16, i*16, 16, 16);
				}
			}
		}
		Point[][] doors = new Point[50][2];
		int max = m.max();
		doors = m.theDoors();
		for(int i=0;i<max; i++){
			Point p1 =new Point();
			red = rand.nextInt(254)+1;
			blue = rand.nextInt(254)+1;
			green = rand.nextInt(254)+1;
			p1=doors[i][0];
			g.setColor(new Color(red,green,blue));
			g.fillOval(p1.y*16, p1.x*16, 16, 16);
			p1=doors[i][1];
			g.setColor(new Color(red,green,blue));
			g.fillOval(p1.y*16, p1.x*16, 16, 16);
		}
	}
	
}
