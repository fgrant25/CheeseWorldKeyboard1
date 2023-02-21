//K. Chun 8/2018

//*******************************************************************************
//Import Section
//Add Java libraries needed for the game
//import java.awt.Canvas;

//Graphics Libraries

import java.awt.Graphics2D;
import java.awt.image.BufferStrategy;
import java.awt.*;
import javax.swing.*;

/***
 * Step 0 for keyboard control - Import
 */
import java.awt.event.*;

/***
 * Step 1 for keyboard control - implements KeyListener
 */
public class SkateFacts implements Runnable, KeyListener {

    //Variable Definition Section

    //Sets the width and height of the program window
    final int WIDTH = 1000;
    final int HEIGHT = 650;

    //Declare the variables needed for the graphics
    public JFrame frame;
    public Canvas canvas;
    public JPanel panel;
    public BufferStrategy bufferStrategy;

    //Declare the variables needed for images

    public Image rinkPic;
    public Image jumpPic;
    public Image skaterPic;
    public Image tomPic;

    public Image bearPic;

    //Declare the character objects
    public Skater skater1;
    public Jump theJump;
    public Player user;

    public Bear bear1;



    // Main method definition
    // This is the code that runs first and automatically
    public static void main(String[] args) {
        SkateFacts myApp = new SkateFacts();   //creates a new instance of the game
        new Thread(myApp).start();               //creates a threads & starts up the code in the run( ) method
    }

    // Constructor Method - setup portion of the program
    // Initialize your variables and construct your program objects here.
    public SkateFacts() {

        setUpGraphics();

        /***
         * Step 2 for keyboard control - addKeyListener(this) to the canvas
         */
        canvas.addKeyListener(this);

        //load images
        rinkPic = Toolkit.getDefaultToolkit().getImage("RinkPic.png");
        jumpPic = Toolkit.getDefaultToolkit().getImage("cheese.gif");
        skaterPic = Toolkit.getDefaultToolkit().getImage("RaccoonSkater.png");
        tomPic = Toolkit.getDefaultToolkit().getImage("tomCat.png");
        bearPic = Toolkit.getDefaultToolkit().getImage("Bear.png");



        //create (construct) the objects needed for the game
        skater1 = new Skater(200, 300, 4, 4, skaterPic);
        theJump = new Jump(400, 300, 3, -4, jumpPic);
        user = new Player(250, 250, 0, 0, tomPic);
        bear1 = new Bear(800,300,3,-4, bearPic);

    } // CheeseWorld()


//*******************************************************************************
//User Method Section

    // main thread
    // this is the code that plays the game after you set things up
    public void moveThings() {
        skater1.move();
        theJump.move();
        user.move();
        bear1.move();
    }

    public void checkIntersections() {

    }

    public void run() {
        while (true) {
            moveThings();           //move all the game objects
            checkIntersections();   // check character crashes
            render();               // paint the graphics
            pause(20);         // sleep for 20 ms
        }
    }

    //paints things on the screen using bufferStrategy
    public void render() {
        Graphics2D g = (Graphics2D) bufferStrategy.getDrawGraphics();
        g.clearRect(0, 0, WIDTH, HEIGHT);

        //draw characters to the screen
        g.drawImage(rinkPic, 0, 0, 1000, 600, null);
        g.drawImage(skater1.pic, skater1.xpos, skater1.ypos, skater1.width, skater1.height, null);
        g.drawImage(theJump.pic, theJump.xpos, theJump.ypos, theJump.width, theJump.height, null);
        g.drawImage(skater1.pic, skater1.xpos, skater1.ypos, skater1.width, skater1.height, null);
        g.drawImage(bear1.pic, bear1.xpos, bear1.ypos, bear1.width, bear1.height, null);


        g.dispose();
        bufferStrategy.show();
    }

    /***
     * Step 3 for keyboard control - add required methods
     * You need to have all 3 even if you aren't going to use them all
     */
    public void keyPressed(KeyEvent event) {
        //This method will do something whenever any key is pressed down.
        //Put if( ) statements here
        char key = event.getKeyChar();     //gets the character of the key pressed
        int keyCode = event.getKeyCode();  //gets the keyCode (an integer) of the key pressed
        System.out.println("Key Pressed: " + key + "  Code: " + keyCode);

        if (keyCode == 68) { // d
            skater1.right = true;
        }
        if (keyCode == 65) { // a
            skater1.left = true;
        }

        if (keyCode == 83) { // s
            skater1.down = true;
        }
        if (keyCode == 87) { // w
            skater1.up = true;
        }
    }//keyPressed()

    public void keyReleased(KeyEvent event) {
        char key = event.getKeyChar();
        int keyCode = event.getKeyCode();
        //This method will do something when a key is released
        if (keyCode == 68) { // d
            skater1.right = false;
        }
        if (keyCode == 65) { // a
            skater1.left = false;
        }
        if (keyCode == 83) { // s
            skater1.down = false;
        }
        if (keyCode == 87) { // w
            skater1.up = false;
        }

    }//keyReleased()

    public void keyTyped(KeyEvent event) {
        // handles a press of a character key (any key that can be printed but not keys like SHIFT)
        // we won't be using this method, but it still needs to be in your program
    }//keyTyped()


    //Graphics setup method
    public void setUpGraphics() {
        frame = new JFrame("SkateFacts");   //Create the program window or frame.  Names it.

        panel = (JPanel) frame.getContentPane();  //sets up a JPanel which is what goes in the frame
        panel.setPreferredSize(new Dimension(WIDTH, HEIGHT));  //sizes the JPanel
        panel.setLayout(null);   //set the layout

        // creates a canvas which is a blank rectangular area of the screen onto which the application can draw
        // and trap input events (Mouse and Keyboard events)
        canvas = new Canvas();
        canvas.setBounds(0, 0, WIDTH, HEIGHT);
        canvas.setIgnoreRepaint(true);

        panel.add(canvas);  // adds the canvas to the panel.

        // frame operations
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  //makes the frame close and exit nicely
        frame.pack();  //adjusts the frame and its contents so the sizes are at their default or larger
        frame.setResizable(false);   //makes it so the frame cannot be resized
        frame.setVisible(true);      //IMPORTANT!!!  if the frame is not set to visible it will not appear on the screen!

        // sets up things so the screen displays images nicely.
        canvas.createBufferStrategy(2);
        bufferStrategy = canvas.getBufferStrategy();
        canvas.requestFocus();
        System.out.println("DONE graphic setup");

    }

    //Pauses or sleeps the computer for the amount specified in milliseconds
    public void pause(int time) {
        //sleep
        try {
            Thread.sleep(time);
        } catch (InterruptedException e) {

        }
    }

}//class
