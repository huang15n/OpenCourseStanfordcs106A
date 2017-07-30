
/*
 * File: Breakout.java
 * -------------------
 * Author:Eddie Huang
 * Date: July 30 2017 
 * This file will eventually implement the game of Breakout.
 * this game is designed to duplicate the model that steve wozinac made in early 1970s 
 * the game itself is simple but it runs on event-driven programming 
 * 
 */
import acm.graphics.*;
import acm.program.*;
import acm.util.*;
import java.util.ArrayList;
import java.applet.*;
import java.awt.*;
import java.awt.event.*;
public class Breakout extends GraphicsProgram {
/** Width and height of application window in pixels */
   public static final int APPLICATION_WIDTH = 600;
   public static final int APPLICATION_HEIGHT = 600;
/** Dimensions of game board (usually the same) */
   private static final int WIDTH = APPLICATION_WIDTH;
   private static final int HEIGHT = APPLICATION_HEIGHT;
/** Dimensions of the paddle */
   private static final int PADDLE_WIDTH = 60;
   private static final int PADDLE_HEIGHT = 10;
/** Offset of the paddle up from the bottom */
   private static final int PADDLE_Y_OFFSET = 30;
/** Number of bricks per row */
   private static final int NBRICKS_PER_ROW = 10;
/** Number of rows of bricks */
   private static final int NBRICK_ROWS = 10;
/** Separation between bricks */
   private static final int BRICK_SEP = 4;
/** Width of a brick */
   private static final int BRICK_WIDTH =
     (WIDTH - (NBRICKS_PER_ROW - 1) * BRICK_SEP) / NBRICKS_PER_ROW;
/** Height of a brick */
   private static final int BRICK_HEIGHT = 8;
/** Radius of the ball in pixels */
   private static final int BALL_RADIUS = 10;
/** Offset of the top brick row from the top */
   private static final int BRICK_Y_OFFSET = 70;
/** Number of turns */
   private static final int NTURNS = 3;
   /** the array list here is intended to be a container for the bricks */
   private ArrayList<GRect> brickStanding = new ArrayList<GRect>();
   /** the individual bricks will be created as objects  */
   private GRect bricks;
   /** this is the paddle for the game ,it is a bar shape which can pick up the ball and make it bounce back */
   private GRect paddle; 
   /** the ball itself has to be in the certain shape */
   private GOval ball;
   /** this random generator is made to make the unpredicatable behaviour of the ball motion slightly */
   private RandomGenerator rgen = new RandomGenerator().getInstance();
   /** the x velocity and y velocity is declare and vy is a constant value */
   private double vx, vy = 3.0;
   /** the collider is designed to detect the ball hit the wall or the paddler */
   private GObject collider;
   /** the score is the marker for how many bricks that user hits */
   private int score = 0;
   /** the turn is the counter to decide how many turns that the user has involved */
   private int turn = 0;
   /** the marker is created with the glabel with the score that player has reached so far*/
   GLabel marker = new GLabel("Score "+score,50,50);
   /** the turn counter is also as a label  */
   GLabel turnCounter = new GLabel("Turn "+turn,50,20);
   /** the loss marker is occurred when user lose all three turns  */
   GLabel loss = new GLabel("You Lose", WIDTH / 2, 20);
   
   public void run() {
   
	   /* to define the window size */
   setSize(APPLICATION_WIDTH, APPLICATION_HEIGHT);

   /*set the score marker font color */
	 marker.setColor(Color.BLUE);
	add(marker);
	
	 /*set the turn marker font color */
	turnCounter.setColor(Color.RED);
	add(turnCounter);
	  
	/*set the font the the game loss sign*/
	loss.setFont("Monospace-bold-20");
	 
	 /* the whole game play methods have been contain inside to be invoked */
    gamePlay();
 
   
   
   } 
   
   /**
    * this method is able to initialize the original world status 
    * the game has 10 rows of bricks with various colors 
    * switch statement is to define the color in every two rows 
    * the arrayList is used here to store the bricks 
    * precondition: the world is empty 
    * postcondition: all the bricks have been added on the screen within the help of array list 
    * 
    */
   public void intialGame(){
	  
	   for(int i = 1; i <= NBRICKS_PER_ROW ; i++){
		   for(int j = 1; j<= NBRICK_ROWS; j++){
			 
			   bricks = new GRect(((BRICK_WIDTH  + BRICK_SEP) * i)  , ((BRICK_HEIGHT + BRICK_SEP) * j ) + BRICK_Y_OFFSET , BRICK_WIDTH, BRICK_HEIGHT);
			   bricks.setColor(Color.white);
			   bricks.setFilled(true);  // it has to be set as  filled true otherwise it cannot be filled 
			   
			  
			   switch(j){
			   case 1:
			   case 2:
				   bricks.setFillColor(Color.RED); // set color red 
				 
				   break;
			   case 3:
			   case 4:
				   bricks.setFillColor(Color.ORANGE);
			 
				   break;
			   case 5:
			   case 6:
				   bricks.setFillColor(Color.YELLOW);
				 
				   break;
			   case 7:
			   case 8:
				   bricks.setFillColor(Color.GREEN);
				   
				   break;
			   case 9:
			   case 10:
				   bricks.setFillColor(Color.CYAN);
				   
				   break;
			   }
			   
			   brickStanding.add(bricks);   // the array list add the bricks 
			   
			  
		   }
	   }
	   
	   for(int i = 0; i < brickStanding.size(); i++){
	   add(brickStanding.get(i));    //loop through the array list and add the elements on the screen 
	   }
   }
   
   /**
    * this method intends to create the paddler and return an unique paddle to the gameplay 
    * precondition: with the defined length of paddle 
    * postcondition: paddle been created 
    */
   
    public GRect paddleCreation(){
    	paddle = new GRect(getWidth() / 2, getHeight() / 1.2, PADDLE_WIDTH, PADDLE_HEIGHT );
    	paddle.setFilled(true);
    	paddle.setFillColor(Color.BLACK);
    	add(paddle);
    	
    	return paddle;
    }
    
    /**
     * (non-Javadoc)
     * @see acm.program.Program#mouseMoved(java.awt.event.MouseEvent)
     * this is totally the event driven programming ,once the mouseMoved the paddle will just move 
     * 
     * 
     */
    
    public void mouseMoved(MouseEvent event){
    	int xCo = event.getX() -  PADDLE_WIDTH / 2; // here to define the motion of the paddle ,it moves with the coordinates thorough the mouse x coordinates + the middle point of the paddle 
    	 
    	  
    		if(xCo < getWidth() - PADDLE_WIDTH / 2) {
    			
    		paddle.setLocation(xCo, getHeight() / 1.2); // the paddle moves to x coordianates but y coordinates is fixed 
    		
    		}
    		 
    		pause(200);   // set the pause time for animation 
     
    	
    }
    
    /**
     * this method focuses on the ball creation the radius has been defined and the position starts from the left corner 
     * 
     */
    public void ballCreation(){
    	ball = new GOval(getWidth() / 3, getHeight() / 2,BALL_RADIUS ,BALL_RADIUS );
    	ball.setFilled(true);
    	ball.setFillColor(Color.BLACK);
    	
    	 add(ball);
    	
    }
    
    /**
     * the ball motion is varied from its velocity ,which is swinged by random range from 1 to 3 
     * here are some additional motions 
     * if the ball bounce on the paddler ,the x velocity will be negated to bounce off 
     * at the same condition of collision ,the will bounce off from the wall with negated x velocity 
     * at the same time ,if the ball hits the bricks ,the brick will be removed from the array list 
     * to check the termination of the game ,it  comes with the condition if the ball hits the end of the window 
     */
    public void ballMotion(){
    	 
    	vx = rgen.nextDouble(1.0, 3.0);
    	if(rgen.nextBoolean(0.5)){
    		vx = -vx;
    		
    	}
    	
    	while(ball.getY() <= getHeight()){
    		ball.move(vx, vy);
    		 
    		collider = getColldingObject();
    		if(collider == paddle){   // if the ball hits the paddle 
    			vy = -vy;
    		}
    		if(ball.getX() <= BALL_RADIUS || ball.getX() >= getWidth() - BALL_RADIUS){   // to check the wall on right and left side 
    			vx = -vx;
    		}
    		if(ball.getY() <= BALL_RADIUS){   // of the ball hits the top 
    			vy = -vy;
    		}
    	    for(int i = 0; i < brickStanding.size(); i++){   // if the ball hits the brick 
    	    	if(collider == brickStanding.get(i)){
    	    		remove(brickStanding.get(i));
    	    		vy = -vy;
    	    		score++;
    	    		marker.setLabel("Score :"+score);
    	    	}
    	    }
    	    if(ball.getY() >= getHeight() - BALL_RADIUS){   // if the ball fall at the bottom ,the player lose one turn 
    	    	turn++;
    	    	turnCounter.setLabel("Turn :"+turn);
    	    	
    	    	remove(ball);
    	    	remove(paddle);
    	    	remove(marker);
    	    	score = 0;
    	    	add(marker);
    	    	gamePlay();
    	    }
    		 
        	pause(50);
    	}	
    		 
    	 
    	
    }
    
    /**
     * this method is to define the ball's collision 
     * if the ball has the element container ,it trigger the collision and all the 
     * reaction or event handling will go through the motion method 
     * which is the condition of the bricks , wall and paddler hittings 
     */
  
    private GObject getColldingObject(){
    	GObject object = null;
    	double x = ball.getX();
    	double y = ball.getY();
    	
        if(getElementAt(x, y) != null){
        	object = getElementAt(x, y);
        } else if(getElementAt(x + BALL_RADIUS, y) != null){
        	object = getElementAt(x + BALL_RADIUS, y);
        }else if (getElementAt(x, y  + BALL_RADIUS) != null){
        	object = getElementAt(x, y  + BALL_RADIUS) ;
        }
        else if(getElementAt(x+ BALL_RADIUS, y  + BALL_RADIUS)!= null){
        	object = getElementAt(x + BALL_RADIUS , y  + BALL_RADIUS) ;
        }
        
        
        return object;
    	
    	
    }
    
    /**
     * this is for the game play
     * if the turn is less than 3 ,even the player lose one which means the ball falls into the bottom 
     * it will show the turn and initial the game 
     * if the player loses more than 3 turns ,game is over 
     *  
     */
    
    public void gamePlay(){
    	if(turn < 3){
    	   intialGame();
    	   
    	   paddle = paddleCreation();
    	   ballCreation();
    	 
    	   addMouseListeners();
    	    
    	   ballMotion();
    	}
    	else {
    		remove(marker);
    		remove(turnCounter);
    		 
    		add(loss);
    	}
    	   
    	   
    }
    
   
   




}