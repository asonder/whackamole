
import java.util.*;


public class WhackAMole {
    int score;
    int molesLeft;
    int attemptsLeft;
    char[][] moleGrid;
    
    /*
     * Constructor for the game. Specifies
     * total number of whacks allowed 
     * and the grid dimension. 
     *
     * Initializes the moleGrid with the 
     * appropriate character.
     */
    
   public WhackAMole(int numAttempts, int gridDimension) {
       this.score = 0;
       this.molesLeft = 0;
       this.attemptsLeft = numAttempts;
	      
       
       this.moleGrid = new char[gridDimension][gridDimension];
       
       //Fill each row with a star
       for(int i = 0; i < moleGrid.length; i++) {
	   for(int j = 0; j < moleGrid[i].length; j++) {
	       moleGrid[i][j] = '*';
	   }
	   
       }
       
   }
	
   
   /*
    * Given a location, place a mole at that location. 
    * Update number of moles left.
    * Return whether it is possible to place
   */
   
   
   public boolean place(int x, int y)  {
       if(moleGrid[x][y] == 'M') return false;
     
       moleGrid[x][y] = 'M';
       molesLeft += 1;
	       return true;
	
    }
   
   /*
    * Given a location, take a whack at that location. 
    * If that location contains a mole, 
    * update the score, 
    * number of attemptsLeft, 
    * and molesLeft. 
    * If that location does not contain a mole, 
    * only update attemptsLeft.
    */

    public void whack(int x, int y) {
	if(moleGrid[x][y] == 'M') {
	    score ++;
	    attemptsLeft --;
	    molesLeft --;
	    moleGrid[x][y] = 'W';
	    
	} else {
	    attemptsLeft --;
	}
	
	
    }
    /*
     * Print the grid without showing where the moles are. 
     * For every spot that has recorded a “whacked mole,” 
     * print out a W, or * otherwise.
     */
    
    public void printGridToUser() {
	
	for(int i = 0; i < moleGrid.length; i++) {
	    for(int j = 0; j < moleGrid[i].length; j++) {
		if(moleGrid[i][j] == 'W') {
		    System.out.print('W');
		   } 
	    	else {
		       System.out.print('*');
		   }       
	   } System.out.println();	
	}
    }
    
    /*
     * Print the grid completely. 
     * This is effectively dumping 
     * the 2d array on the screen. 
     * The places where the moles are 
     * should be printed as an ‘M’. 
     * The places where the moles 
     * have been whacked should be printed 
     * as a ‘W’. The places that don’t have 
     * a mole should be printed as *.
     */
    
    
    public void printGrid() {
	
	for(int i = 0; i < moleGrid.length; i++) {
	    for(int j = 0; j < moleGrid[i].length; j++) {
		if(moleGrid[i][j] == 'W') {
		    System.out.print('W');
		   } 
		else if(moleGrid[i][j]=='M') {
		       System.out.print('M');
		       
		   } 
		else {
		       System.out.print('*');
		   }   
	    } System.out.println();
	
	}
    }
/*
 *  
Create a 10 by 10 grid with the moles randomly placed.
Place a total of 10 moles.
Now allow the user (remember how to use Scanner?) 
to enter the x and y coordinates of where they 
would like to take a whack. 
Tell them they have a maximum of 50 attempts to get all the moles. 
At any point in the game, 
they can input coordinates of -1, -1 
in order to indicate that they are giving up. If the user 
gives up they get to see the entire grid.  
The game ends if the user is able to uncover 
all the moles or if the user runs out of attempts. 
 */
    
   public static void main (String[] args)  {
       WhackAMole game = new WhackAMole(50, 10);
       Random random = new Random();
       
       for(int i=0; i < 10; i++) {
	   game.place(random.nextInt(10), random.nextInt(10));
       }
       
       Scanner scanner = new Scanner(System.in);
       
       System.out.println("Welcome to Whack-A-Mole");
       
       while(game.attemptsLeft > 0 && game.molesLeft > 0) {
	  
	   System.out.println("Your score: " + game.score);
	   System.out.println("Attempts remaining: " + game.attemptsLeft);
	   System.out.println("Please enter the x and y coordinates "
	   	+ "for your next whack!");
	   
	   int x_coordinate = scanner.nextInt();
	   int y_coordinate = scanner.nextInt();
	   
	   if(x_coordinate != -1 && y_coordinate != -1) {
	       game.whack(x_coordinate, y_coordinate);
	       
	   } else {
	       
	       game.printGrid();
	       System.out.println("Sorry to see you leave!");
	       break;
       }
       
   }
       if(game.attemptsLeft == 0) {
       game.printGrid();
       System.out.println("Thanks for playing. Try again any time!");
   
       }
    }
}
