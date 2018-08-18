package work.doestheinternetstill.mousemover;

import java.awt.Point;
import java.awt.Robot;

public class FreeMouseMover {

    public static final int SLEEP_INTERVAL = 1000;
    public static final int ITERATION_AMOUNT = 1200;

    public static final int MIN_Y = 100;
    public static final int MIN_X = 100;

    public static final int MAX_Y = 400;
    public static final int MAX_X = 400;

    public static void main(String... args) throws Exception {
        Robot robot = new Robot();
        Movement nextMove;
        
        {
        	//TODO Determine the movement type based on the radio button that is selected
        	// or command line option?
        	//nextMove = new RandomMovement(MIN_X, MIN_Y, MAX_X, MAX_Y);
        	nextMove = new JitterMovement();
        }
        
        int iterationCount = 0;
        
        while (iterationCount < ITERATION_AMOUNT) {
        	Point nextLocation = nextMove.generateNextMouseLocation();
        	
            robot.mouseMove((int) nextLocation.getX(), (int) nextLocation.getY());
            
            Thread.sleep(SLEEP_INTERVAL);
            
            iterationCount++;
        }
    }
}