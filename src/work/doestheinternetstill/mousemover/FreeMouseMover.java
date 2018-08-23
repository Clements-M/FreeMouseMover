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

		int sleepAmount;

		{

			final Options.MovementSpeed inputMovementSpeed = Options.MovementSpeed.HIGH;
			switch (inputMovementSpeed) {
			case HIGH:
				sleepAmount = 250;
				break;
			case MEDIUM:
				sleepAmount = 1000;
				break;
			case LOW:
			default:
				sleepAmount = 2500;
				break;
			}
		}

		{
			{
				final Options.MovementType inputMovementType = Options.MovementType.JITTER;
				switch (inputMovementType) {
				case RANDOM:
					nextMove = RandomMovement.generateRandomScreenMovement();
					break;
				case CIRCULAR:
					// TODO nextMove = new CircularMovement();
				case PATH:
				case JITTER:
				default:
					nextMove = new JitterMovement();
					break;
				}
			}

			int iterationCount = 0;

			/*
			 * while(TimerThread - > getTime < waitTime){ Point nextLocation =
			 * nextMove.generateNextMouseLocation(); robot.mouseMove((int)
			 * nextLocation.getX(), (int) nextLocation.getY());
			 * Thread.sleep(SLEEP_INTERVAL); TimerThread.updateTime(); }
			 */
			while (iterationCount < ITERATION_AMOUNT) {
				Point nextLocation = nextMove.generateNextMouseLocation();

				robot.mouseMove((int) nextLocation.getX(), (int) nextLocation.getY());

				Thread.sleep(sleepAmount);

				iterationCount++;
			}
		}
	}
}