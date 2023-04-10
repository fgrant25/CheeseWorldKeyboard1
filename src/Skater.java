

import java.awt.*;

	public class Skater {

		//VARIABLE DECLARATION SECTION
		//Here's where you state which variables you are going to use.

		public int xpos;                //the x position
		public int ypos;                //the y position
		public int width;
		public int height;
		public boolean isAlive;            //a boolean to denote if the hero is alive or dead.
		public int dx;                    //the speed of the hero in the x direction
		public int dy;                    //the speed of the hero in the y direction
		public Rectangle rec;
		public Image pic;
		public int hits;
		public boolean right;
		public boolean left;
		public boolean down;
		public boolean up;

		// METHOD DEFINITION SECTION

		//This is a constructor that takes 3 parameters.  This allows us to specify the object's name and position when we build it.
		// if you put in a String, an int and an int the program will use this constructor instead of the one above.
		public Skater(int pXpos, int pYpos) {

			xpos = pXpos;
			ypos = pYpos;
			width = 50;
			height = 100;
			dx = 10;
			dy = 10;
			isAlive = true;
			hits = 0;
			rec = new Rectangle(xpos, ypos, width, height);


		} // constructor


		public Skater(int pXpos, int pYpos, int dxParameter, int dyParameter, Image picParameter) {

			xpos = pXpos;
			ypos = pYpos;
			width = 100;
			height = 150;
			dx = dxParameter;
			dy = dyParameter;
			pic = picParameter;
			isAlive = true;
			hits = 0;
			rec = new Rectangle(xpos, ypos, width, height);


		} // constructor


		//The move method.  Everytime this is run (or "called") the hero's x position and y position change by dx and dy
		public void move() {
			xpos = xpos + dx;
			ypos = ypos + dy;

			if(right == true){
				dx = 10;
			} else if (left == true) {
				dx = -10;
			} else { // (right == false && left == false)
				dx = 0;
			}

			if(down == true){
				dy = 10;
			} else if (up == true) {
				dy = -10;
			} else {
				dy = 0;
			}

			if(xpos>1000-width){ // right
				xpos = 1000-width;
			}
			if(xpos < 0) { // left
				xpos = 0;
			}
			if(ypos>650-height){ // down
				ypos = 650-height;
			}
			if(ypos < 0) { // up
				ypos = 0;
			}


			//always put this after you've done all the changing of the xpos and ypos values
			rec = new Rectangle(xpos, ypos, width, height);

		}

		public void move2() {
			xpos = xpos + dx;
			ypos = ypos + dy;

			if (ypos < 250) {
				dy = dy + 1;
			}
			if (ypos > 250) {
				ypos = 250;
			}

			if (xpos > 1000 - width) { // right
				xpos = 1000 - width;
			}
			if (xpos < 0) { // left
				xpos = 0;
			}
			if (ypos > 650 - height) { // down
				ypos = 650 - height;
			}
			if (ypos < 0) { // up
				ypos = 0;
			}
		}


	} //end of the Mouse object class  definition
