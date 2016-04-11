/*
 JavaCars.java
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

import java.awt.image.BufferedImage;
import java.applet.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.Color;
import java.awt.Rectangle;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;
import java.util.Random;
import java.awt.*;
import java.applet.Applet;
import java.awt.Graphics;
import java.awt.* ;
import java.applet.Applet ;
import java.util.*;

public class JavaCars{
	
	public ArrayList<Color> colorList = new ArrayList<Color>();        
	private static final int FINISH_LINE = 1020;
	//Imports
	private Random rand;
	private BufferedImage car;
	//Positions
	private double x, y;
	private double velocity;
	private double acceleration;
	
	
	/*Function: JavaCars(int y, int number)
	 * ----------------------------
	 * @Car Object to be drawn onto a Buffered Image
	 * @param int y coordinate of each Buffered Image 
	 * @param int number corresponding with the racer number
	 */
	

	public JavaCars(int y, int number) {
		// color definitions (red, green, blue)
	    Color REDBROWN = new Color ( 182, 100, 110 ) ;
	    Color DARKBROWN = new Color ( 150, 70, 80 ) ;
	    Color LTBLUE = new Color ( 35, 206, 255 ) ;
	    Color DARKGOLD = new Color ( 240, 220, 0 ) ;
	    
		//Place the following colors into the global colorList loop
	    colorList.add(Color.cyan);
		colorList.add(Color.green);
		colorList.add(Color.magenta);
		colorList.add(Color.pink);
		colorList.add(Color.orange);
        
		//create Buffered Image of type RGB of each car to be overlayed on top of
        this.car = new BufferedImage(200, 130, BufferedImage.TYPE_INT_ARGB);
		this.rand = new Random();
		
		
	
		
		//control the speed of the car
		this.velocity = rand.nextDouble()%1000;
		if(CarDrawer.checkBox1.getState()){
			this.velocity = rand.nextDouble()*15; //if Skittles Car speed up the car
		}
		this.acceleration = rand.nextDouble()/500.0;
		this.x = 0;
		this.y = y;
		
		
		Graphics2D g = car.createGraphics();

		  
		// front tire
         g.setColor ( Color.black ) ;
	g.fillOval ( 10, 85, 50, 50 ) ;
         g.setColor ( Color.white ) ;
	g.fillOval ( 15, 90, 40, 40 ) ;
         g.setColor ( Color.black ) ;
	g.drawOval ( 20, 95, 30, 30 ) ;

		// back tire
         g.setColor ( Color.black ) ;
	g.fillOval ( 132, 85, 50, 50 ) ;
         g.setColor ( Color.white ) ;
	g.fillOval ( 140, 90, 40, 40 ) ;
         g.setColor ( Color.black ) ;
	g.drawOval ( 145, 95, 30, 30 ) ;

		// car hood
         g.setColor ( DARKBROWN ) ;
	g.fillRect ( 5, 56, 61, 12/2 ) ;
         g.setColor ( REDBROWN ) ;
	g.fillRect ( 5, 61, 61, 41 ) ;

		// car hood ornament
         g.setColor ( DARKGOLD ) ;
	g.fillOval ( 5, 52, 5, 5 ) ;

		//  car window
         g.setColor (colorList.get(number)) ;
         g.fillRect ( 90, 7, 65, 50 ) ; 

		// car door
         g.setColor ( REDBROWN ) ;
	g.fillRect ( 65, 56, 65, 46 ) ;

		// car backseat
         g.setColor ( REDBROWN ) ;
	g.fillRect ( 258/2, 56, 61, 46 ) ;

		// car running board
         g.setColor ( DARKBROWN ) ;
	g.fillRect ( 59, 102, 77, 5) ;

	
		// door handle
         g.setColor ( Color.black ) ;
	g.drawLine ( 92, 62, 25, 62) ;
	g.drawLine ( 92, 61, 25, 61) ;
	
	//set Color depending on the racer number
	g.setColor(colorList.get(number));
	number+=1;
	 g.drawString("Racer " +number + ".0", 92, 83);
			
	}
	
	/*Function: race()
	 * ----------------------------
	 * @Move racer objects
	 */
	
	public void race() {
		if(CarDrawer.checkBox1.getState()){
			this.x += this.velocity;
			this.velocity += this.acceleration;
			this.x = this.x%1020;
		}
		if (!victory()) {
			this.x += this.velocity;
			this.velocity += this.acceleration;
		}
	}

	public boolean victory() {
		return this.x + this.car.getWidth() >= FINISH_LINE;
	}

	public void draw(Graphics2D g) {
		g.drawImage(car, new AffineTransform(1f,0f,0f,1f,x,y), null);
	}

}