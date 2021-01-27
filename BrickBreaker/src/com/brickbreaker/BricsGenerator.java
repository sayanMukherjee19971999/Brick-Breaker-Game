package com.brickbreaker;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;

public class BricsGenerator {
	public int map[][];
	public int brickWidth;
	public int brickHeight;
	public BricsGenerator(int row,int column) {
		map=new int[row][column];
		for(int i=0;i<map.length;i++) {
			for(int j=0;j<map[0].length;j++) {
				map[i][j]=1; //1 denotes this particular brick is not broken. It will be shown in the screen
			}
		}
		
		brickWidth=540/column;
		brickHeight=150/row;
	}
	
	public void draw(Graphics2D g) {
		for(int i=0;i<map.length;i++) {
			for(int j=0;j<map[0].length;j++) {
				if(map[i][j]>0) {
					g.setColor(Color.white);
					g.fillRect(j*brickWidth+80, i*brickHeight+50, brickWidth, brickHeight);
					g.setStroke(new BasicStroke(3));
					g.setColor(Color.black);
					g.drawRect(j*brickWidth+80, i*brickHeight+50, brickWidth, brickHeight); //drawing border around bricks
				}
			}
		}
	}
	
	public void setBrickValue(int value,int row,int column) {
		map[row][column]=value;
	}
}
