package com.brickbreaker;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;


import javax.swing.JPanel;
import javax.swing.Timer;

public class GamePlay extends JPanel implements KeyListener,ActionListener {
	
	private boolean play=false;
	private int score=0;
	private int totalBricks=21;
	private Timer timer;
	
	private int delay=10;
	
	private int playerX=320;
	private int ballposX=120;
	private int ballposY=350;
	private int ball2posX=200;
	private int ball2posY=350;
	private int ball3posX=250;
	private int ball3posY=350;
	private int ball4posX=400;
	private int ball4posY=350;
	private int ballXdir=-1;
	private int ballYdir=-2;
	private int ball2Xdir=3;
	private int ball2Ydir=-2;
	private int ball3Xdir=5;
	private int ball3Ydir=-3;
	private int ball4Xdir=2;
	private int ball4Ydir=-2;
	private BricsGenerator map;
	
	public GamePlay() {
		map=new BricsGenerator(3,7);
		addKeyListener(this);
		setFocusable(true); // get the power of getting focussed
		setFocusTraversalKeysEnabled(false); //decides whether or not focus traversal keys
		timer=new Timer(delay,this);
		
		timer.start();
		
	}
	
	public void paint(Graphics g) {
		//background coloring
		g.setColor(Color.black);
		g.fillRect(1, 1, 692, 592);
		
		//drawing bricks
		map.draw((Graphics2D)g);
		
		//adding borders to three sides
		//bottom is kept open because the ball will touch the frame and Game Over will occur
		g.setColor(Color.yellow);
		g.fillRect(0, 0, 3, 592); //adding border to left side
		g.fillRect(0, 0, 692, 3); //adding border to top
		g.fillRect(681, 0, 3, 592); //adding border to right side
		
		//adding score to screen
		g.setColor(Color.white);
		g.setFont(new Font("consolas",Font.BOLD,25));
		g.drawString(""+score, 590, 30);
		
		//creating the slider
		g.setColor(Color.green);
		g.fillRect(playerX, 550, 100, 8);
		
		//creating the first ball
		g.setColor(Color.yellow);
		g.fillOval(ballposX, ballposY, 20, 20);
		
		//creating the second ball
		g.setColor(Color.green);
		g.fillOval(ball2posX, ball2posY, 20, 20);
		
		//creating the third ball
		g.setColor(Color.blue);
		g.fillOval(ball3posX, ball3posY, 20, 20);
				
		//creating the fourth ball
		g.setColor(Color.red);
		g.fillOval(ball4posX, ball4posY, 20, 20);
		
		//If all the bricks are broken
		if(totalBricks<=0) {
			play=false;
			ballXdir=0;
			ballYdir=0;
			ball2Xdir=0;
			ball2Ydir=0;
			ball3Xdir=0;
			ball3Ydir=0;
			ball4Xdir=0;
			ball4Ydir=0;
			g.setColor(Color.red);
			g.setFont(new Font("serif",Font.BOLD,30));
			g.drawString("You Won", 260, 300);
			
			//Restart the game
			g.setFont(new Font("consolas",Font.BOLD,25));
			g.drawString("Press Enter to Restart", 190, 350);
		}
		
		//If ball touches the ground
		if(ballposY>570) {
			play=false;
			ballXdir=0;
			ballYdir=0;
			ball2Xdir=0;
			ball2Ydir=0;
			ball3Xdir=0;
			ball3Ydir=0;
			ball4Xdir=0;
			ball4Ydir=0;
			g.setColor(Color.red);
			g.setFont(new Font("serif",Font.BOLD,30));
			g.drawString("Game Over, Score: ", 190, 300);
			
			//Restart the game
			g.setFont(new Font("consolas",Font.BOLD,25));
			g.drawString("Press Enter to Restart", 190, 350);
		}
		
		g.dispose(); //make the close operation of frame window ineffective
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		timer.start();
		
		if(play) {
			ballposX+=ballXdir;
			ballposY+=ballYdir;
			
			ball2posX+=ball2Xdir;
			ball2posY+=ball2Ydir;
			
			ball3posX+=ball3Xdir;
			ball3posY+=ball3Ydir;
			
			ball4posX+=ball4Xdir;
			ball4posY+=ball4Ydir;
			
			//when first ball collides with the slider
			if(new Rectangle(ballposX,ballposY,20,20).intersects(new Rectangle(playerX,550,100,8))) {
				ballYdir= -ballYdir;
			}
			
			//when second ball collides with the slider
			if(new Rectangle(ball2posX,ball2posY,20,20).intersects(new Rectangle(playerX,550,100,8))) {
				ball2Ydir= -ball2Ydir;
			}
			
			//when third ball collides with the slider
			if(new Rectangle(ball3posX,ball3posY,20,20).intersects(new Rectangle(playerX,550,100,8))) {
				ball3Ydir= -ball3Ydir;
			}
			
			//when fourth ball collides with the slider
			if(new Rectangle(ball4posX,ball4posY,20,20).intersects(new Rectangle(playerX,550,100,8))) {
				ball4Ydir= -ball4Ydir;
			}
			
			//when first ball collide with second ball
			if(new Rectangle(ballposX,ballposY,20,20).intersects(new Rectangle(ball2posX,ball2posY,20,20))) {
				ball2Ydir= -ball2Ydir;
				ballYdir=-ballYdir;
				ball2Xdir=-ball2Xdir;
				ballXdir=-ballXdir;
			}
			
			//when first ball collide with third ball
			if(new Rectangle(ballposX,ballposY,20,20).intersects(new Rectangle(ball3posX,ball3posY,20,20))) {
				ball3Ydir= -ball3Ydir;
				ballYdir=-ballYdir;
				ball3Xdir=-ball3Xdir;
				ballXdir=-ballXdir;
			}
			
			//when first ball collide with fourth ball
			if(new Rectangle(ballposX,ballposY,20,20).intersects(new Rectangle(ball4posX,ball4posY,20,20))) {
				ball4Ydir= -ball4Ydir;
				ballYdir=-ballYdir;
				ball4Xdir=-ball4Xdir;
				ballXdir=-ballXdir;
			}
			
			//when second ball collide with first ball
			if(new Rectangle(ball2posX,ball2posY,20,20).intersects(new Rectangle(ballposX,ballposY,20,20))) {
				ball2Ydir= -ball2Ydir;
				ballYdir=-ballYdir;
				ball2Xdir=-ball2Xdir;
				ballXdir=-ballXdir;
			}
			
			//when second ball collide with third ball
			if(new Rectangle(ball2posX,ball2posY,20,20).intersects(new Rectangle(ball3posX,ball3posY,20,20))) {
				ball2Ydir= -ball2Ydir;
				ball3Ydir=-ball3Ydir;
				ball2Xdir=-ball2Xdir;
				ball3Xdir=-ball3Xdir;
			}
			
			//when second ball collide with fourth ball
			if(new Rectangle(ball2posX,ball2posY,20,20).intersects(new Rectangle(ball4posX,ball4posY,20,20))) {
				ball2Ydir= -ball2Ydir;
				ball4Ydir=-ball4Ydir;
				ball2Xdir=-ball2Xdir;
				ball4Xdir=-ball4Xdir;
			}
			
			//when third ball collide with first ball
			if(new Rectangle(ball3posX,ball3posY,20,20).intersects(new Rectangle(ballposX,ballposY,20,20))) {
				ball3Ydir= -ball3Ydir;
				ballYdir=-ballYdir;
				ball3Xdir=-ball3Xdir;
				ballXdir=-ballXdir;
			}
			
			//when third ball collide with second ball
			if(new Rectangle(ball3posX,ball3posY,20,20).intersects(new Rectangle(ball2posX,ball2posY,20,20))) {
				ball3Ydir= -ball3Ydir;
				ball2Ydir=-ball2Ydir;
				ball3Xdir=-ball3Xdir;
				ball2Xdir=-ball2Xdir;
			}
			
			//when third ball collide with fourth ball
			if(new Rectangle(ball3posX,ball3posY,20,20).intersects(new Rectangle(ball4posX,ball4posY,20,20))) {
				ball3Ydir= -ball3Ydir;
				ball4Ydir=-ball4Ydir;
				ball3Xdir=-ball3Xdir;
				ball4Xdir=-ball4Xdir;
			}
			
			A: for(int i=0;i<map.map.length;i++) {
				for(int j=0;j<map.map[0].length;j++) {
					if(map.map[i][j]>0) {
						int brickX=j*map.brickWidth+80;
						int brickY=i*map.brickHeight+50;
						int brickWidth=map.brickWidth;
						int brickHeight=map.brickHeight;
						
						Rectangle brickRect=new Rectangle(brickX,brickY,brickWidth,brickHeight);
						Rectangle ballRect=new Rectangle(ballposX,ballposY,20,20);
						Rectangle ballRect1=new Rectangle(ball2posX,ball2posY,20,20);
						Rectangle ballRect2=new Rectangle(ball3posX,ball3posY,20,20);
						Rectangle ballRect3=new Rectangle(ball4posX,ball4posY,20,20);
						
						if(ballRect1.intersects(brickRect)) {
							ball2Xdir=-ball2Xdir;
							ball2Ydir=-ball2Ydir;
						}
						
						if(ballRect2.intersects(brickRect)) {
							ball3Xdir=-ball3Xdir;
							ball3Ydir=-ball3Ydir;
						}
						
						if(ballRect3.intersects(brickRect)) {
							ball4Xdir=-ball4Xdir;
							ball4Ydir=-ball4Ydir;
						}
						
						if(ballRect.intersects(brickRect)) {
							map.setBrickValue(0, i, j);
							totalBricks--;
							score+=10;
							
							if(ballposX+19<=brickRect.x || ballposY+1>=brickRect.x+brickRect.width) {
								ballXdir=-ballXdir;
								
							}
							else {
								ballYdir=-ballYdir;
								
							}
							break A;
						}
					}
				}
			}
			
			if(ballposX<0) {
				ballXdir=-ballXdir;
			}
			if(ball2posX<0) {
				ball2Xdir=-ball2Xdir;
			}
			if(ballposY<0) {
				ballYdir=-ballYdir;
			}
			if(ball2posY<0) {
				ball2Ydir=-ball2Ydir;
			}
			if(ballposX>670) {
				ballXdir=-ballXdir;
			}
			if(ball2posX>670) {
				ball2Xdir=-ball2Xdir;
			}
			if(ball2posY>550) {
				ball2Ydir=-ball2Ydir;
			}
			if(ball3posX<0) {
				ball3Xdir=-ball3Xdir;
			}
			if(ball3posX>670) {
				ball3Xdir=-ball3Xdir;
			}
			if(ball3posY<0) {
				ball3Ydir=-ball3Ydir;
			}
			if(ball3posY>550) {
				ball3Ydir=-ball3Ydir;
			}
			if(ball4posX<0) {
				ball4Xdir=-ball4Xdir;
			}
			if(ball4posX>670) {
				ball4Xdir=-ball4Xdir;
			}
			if(ball4posY<0) {
				ball4Ydir=-ball4Ydir;
			}
			if(ball4posY>550) {
				ball4Ydir=-ball4Ydir;
			}
		}
		repaint(); //calling the paint method again to make the slider seems to move
		
	}
	
	@Override
	public void keyReleased(KeyEvent e) {}
	@Override
	public void keyTyped(KeyEvent e) {}
	
	@Override
	public void keyPressed(KeyEvent e) {
		
		//When right key is pressed
		if(e.getKeyCode()==KeyEvent.VK_RIGHT) {
			if(playerX>=580) {
				playerX=580;
			}
			else {
				moveRight();
			}
		}
		
		//When left key is pressed
		if(e.getKeyCode()==KeyEvent.VK_LEFT) {
			if(playerX<=7) {
				playerX=7;
			}
			else {
				moveLeft();
			}	
		}
		
		//When enter key is pressed
		if(e.getKeyCode()==KeyEvent.VK_ENTER) {
			if(!play) {
				play=true;
				ballposX=120;
				ballposY=350;
				ballXdir=-1;
				ballYdir=-2;
				ball2Xdir=1;
				ball2Ydir=-2;
				ball2posX=200;
				ball2posY=350;
				ball3posX=250;
				ball3posY=350;
				ball4posX=100;
				ball4posY=350;
				ball3Xdir=5;
				ball3Ydir=-3;
				ball4Xdir=2;
				ball4Ydir=-2;
				playerX=310;
				score=0;
				totalBricks=21;
				map=new BricsGenerator(3,7);
				repaint();
			}
		}
	}
	
	public void moveRight() {
		play=true;
		playerX+=40;
	}

	public void moveLeft() {
		play=true;
		playerX-=40;
	}
	
}
