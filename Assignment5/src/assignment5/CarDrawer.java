/*
 CarDrawer.java
 Solves EE422C programming assignment #5
 @author Kevin Yee (kjy252)
 @version 1.01 2016-04-05
 */


/** the purpose of this example applet it to draw a boxy car out of
 basic graphical shapes (rectangles, circles and lines) on the applet
 window. It's position and size has been predetermined by sketching
 it out on graph paper first. The specific values of the coordinates
 of the shapes have been arbitrarily chosen.
*/

package assignment5;
import java.awt.*;
import java.applet.Applet;
import java.awt.Button;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;
import java.applet.*;
import java.awt.*;
import java.awt.event.*;


public class CarDrawer extends Applet implements Runnable, ActionListener

{ 
	private Thread threadAnimate;
	private JavaCars[] cars;
	private int winner = -1;
	private boolean running;
	private boolean finishline;
	public static Checkbox checkBox1;
	private Image dbImage;
	private Graphics dbg;
	AudioClip audioClip;
	StopWatch timefinish = new StopWatch();
	
	
	/*Function: init
	 * ----------------------------
	 * @setups "one-time" necessities (Buttons and Music)
	 * @param none
	 */
	
	public void init() {
		System.out.println("Init");
		Button beep = new Button("Reset");
		beep.setBackground(Color.green);
	    this.add(beep);
	    beep.addActionListener(this);
		
		checkBox1 = new Checkbox("Skittles Car");
		checkBox1.setBackground(Color.green);
		add(checkBox1);
		//audioClip = getAudioClip(getCodeBase(),"nyancat.wav");
		gameSetup();
	}
	
	/*Function: start
	 * ----------------------------
	 * @setup Create thread
	 * @param none
	 */

	public void start() {
		System.out.println("Start");
		if(!running){
		running = true;
		this.threadAnimate = new Thread(this,"New Thread");
		this.threadAnimate.start();
		}
	}
	
	/*Function: start
	 * ----------------------------
	 * @setup Stops thread
	 * @param none
	 */

	public void stop() {
		System.out.println("Stop");
	        if(running) {
	            running = false;
	            
	            try {
	               this.threadAnimate.interrupt();
	               this.threadAnimate.join();
	                
	            } catch(Exception e) {
	            }
	        }
		  }
	

	/*Function: gameSetup()
	 * ----------------------------
	 * @Creates car objects
	 * @param none
	 */

	public void gameSetup(){
		StopWatch timer = new StopWatch();
		timer.start();
		timefinish.start();
		cars = new JavaCars[5];
		for (int i = 0; i < cars.length; i++) {
		cars[i] = new JavaCars(i*150,i);
		}
		
		timer.stop();
		
		 System.out.println("Elapsed time to create car objects: " + timer.getElapsedTime() + " milliseconds");
	}
	
	
	/*Function: run()
	 * ----------------------------
	 * @Implements and overrides runnable to repaint
	 * @param none
	 */
	public void run() {
		
		long time = System.currentTimeMillis();

		while (!threadAnimate.isInterrupted()) {
			move();
			if(!finishline){
			repaint(); 
			}
			try {
				time += 10;
				Thread.sleep(Math.max(0, time - System.currentTimeMillis()));
			} catch (InterruptedException e) {
				break;
			}
		}

	}
	
	/*Function: move()
	 * ----------------------------
	 * @moves cars
	 * @param none
	 */
	public void move() {
		StopWatch timer = new StopWatch();
		timer.start();
		for (int i = 0; i < cars.length; i++) {
			try{
			cars[i].race();
			// if skittle cars options picked, then loop endlessly
			if (cars[i].victory() && !checkBox1.getState()) {
				winner = i+1;
				break;
			}
			}
			catch(Exception e){
				
			}
		}
		timer.stop();
		//System.out.println("Elapsed time to move car objects: " + timer.getElapsedTime() + " milliseconds");
	}

	/*Function: paint()
	 * ----------------------------
	 * @redraws the entire image 
	 * @param none
	 */

	public void paint(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
		
		setBackground(Color.DARK_GRAY);
		  g.setColor(Color.BLACK);
		  //Draw the finish line using 1 filled rectangles using black color.
		  g.fillRect(1000,0,40,750);
		  //Draw the yellow colored lines.
		  g.setColor(Color.yellow);
		  
		  for(int j=0;j<35;j++)
		  {
		   
		    g.drawLine(1020,j*20,1020,j*20+10);
		  }
		  
		  g.setColor(Color.cyan);
		  g.drawString("Finish!", 1000, 800);
		  
		  //Draw the bottom of the lines; to divide racers
		  g.setColor(Color.cyan);
		  g.drawLine(0, 150, 1000, 150);
		  g.setColor(Color.green);
		  g.drawLine(0, 300, 1000, 300);
		  g.setColor(Color.MAGENTA);
		  g.drawLine(0, 450, 1000, 450);
		  g.setColor(Color.PINK);
		  g.drawLine(0, 600, 1000, 600);
		  g.setColor(Color.ORANGE);
		  g.drawLine(0, 750, 1000, 750);
		  
		  //if Skittles State, Draw the colored boxes
		  if(checkBox1.getState()){
			  g.setColor(Color.pink);
			  g.drawRect(0, 10, 1000, 140);
			  g.fillRect(0, 10, 1000, 140);
			  g.setColor(Color.orange);
			  g.drawRect(0, 140, 1000, 140);
			  g.fillRect(0, 140, 1000, 140);
			  g.setColor(Color.green);
			  g.drawRect(0, 280, 1000, 140);
			  g.fillRect(0, 280, 1000, 140);
			  g.setColor(Color.cyan);
			  g.drawRect(0, 420, 1000, 140);
			  g.fillRect(0, 420, 1000, 140);
			  g.setColor(Color.black);
			  g.drawRect(0, 560, 1000, 140);
			  g.fillRect(0, 560, 1000, 140);
			  String msg = "Taste the Rainbow!";
				g2.setColor(Color.green);
	            g2.drawString(msg, 500, 800);
		  }
		  
		  //Draw the cars 
		for (JavaCars car : cars) {
			car.draw(g2);
		}
		
		//If winner is lfound, exit and print winner
		if (winner != -1) {
			String msg = String.format("Racer #%d wins!", winner);
			g2.setColor(Color.cyan);
            g2.drawString(msg, 500, 800);
            finishline = true;
            running = false;
			//stop();
            timefinish.stop();
            msg = "Elapsed time to finish line: " + timefinish.getElapsedTime() + " milliseconds";
            g2.setColor(Color.cyan);
            g2.drawString(msg, 20, 800);
		}
	}

	

	@Override
	public void actionPerformed(ActionEvent arg0) {
		System.out.println("Reset");
		if(checkBox1.getState()){
			//audioClip.play(); //play music
		}
		if(!running){
			winner = -1;
			running = true;
			finishline = false;
			gameSetup(); //reset button to reset game
		}
		
		gameSetup();
		
	}
	
	
	





} // end of CarDrawer 