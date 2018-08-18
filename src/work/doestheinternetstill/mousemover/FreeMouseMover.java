package work.doestheinternetstill.mousemover;

import java.awt.Point;
import java.awt.Robot;

import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import work.doestheinternetstill.mousemover.gui.Options;

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

		/*********************************
		 * IF CMD, got0 cmdUI() cmdUI -> verify run with cmd arguments IF GUI, goto
		 * grapicalUI call code below run with selected options
		 * 
		 *********************************/
		{
			try {
				// Set System L&F
				UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
			} catch (UnsupportedLookAndFeelException e) {
				// handle exception
			} catch (ClassNotFoundException e) {
				// handle exception
			} catch (InstantiationException e) {
				// handle exception
			} catch (IllegalAccessException e) {
				// handle exception
			}

			new work.doestheinternetstill.mousemover.gui.MouseMoverGui().showGui();
		}

		/******************
		 * 888 Code for
		 */
		{
			{
				final Options.MovementType inputMovementType = Options.MovementType.JITTER;
				switch (inputMovementType) {
				case RANDOM:
					nextMove = new RandomMovement(MIN_X, MIN_Y, MAX_X, MAX_Y);
					break;
				case CIRCULAR:
				case PATH:
				case JITTER:
				default:
					nextMove = new JitterMovement();
					break;
				}
			}

			int iterationCount = 0;

			while (iterationCount < ITERATION_AMOUNT) {
				Point nextLocation = nextMove.generateNextMouseLocation();

				// robot.mouseMove((int) nextLocation.getX(), (int) nextLocation.getY());

				Thread.sleep(SLEEP_INTERVAL);

				iterationCount++;
			}
		}
	}
}