package work.doestheinternetstill.mousemover;

import java.awt.AWTException;
import java.awt.Point;
import java.awt.Robot;

import work.doestheinternetstill.mousemover.gui.Options;
import work.doestheinternetstill.mousemover.gui.Options.MovementSpeed;

public class MovementManager {

	public static boolean runToggle = false;
	private static int sleepValue;
	private static Movement movement;
	static Robot robot;

	// FIXME: Why do I have these constants?
	public static final int ITERATION_AMOUNT = 1200;

	public static void toggleMovement(Options.MovementType movementType, Options.MovementSpeed movementSpeed,
			int duration) {
		runToggle = !runToggle;
		if (runToggle) {
			// run toggle weas previously false, now it is set to true, therefore we must
			// run
			setMovementSpeed(movementSpeed);
			setMovementType(movementType);
			startMovement(duration);
		}
	}

	private static void setMovementSpeed(MovementSpeed movementSpeed) {
		sleepValue = movementSpeed.getInverseSpeed();
	}

	private static void setMovementType(Options.MovementType inputMovementType) {
		movement = inputMovementType.getM0vementTypeObject();
	}

	private static void startMovement(int duration) {
		try {
			robot = new Robot();
		} catch (AWTException e) {
			;
		} // TODO Properly handle these exceptions

		new Runnable() {

			@Override
			public void run() {
				int iterationCount = 0;

				while (iterationCount < ITERATION_AMOUNT && runToggle) {
					// TODO Move into own method - call method from action handler
					// requires moving the nextlocation to a new scope?
					// requires moving nextMobve to a new location too I think.
					{
						Point nextLocation = movement.generateNextMouseLocation();
						robot.mouseMove((int) nextLocation.getX(), (int) nextLocation.getY());
					}
					try {
						Thread.sleep(sleepValue);
					} catch (InterruptedException e) {
						;
					} // TODO Properly handle these exceptions
					iterationCount++;
				}
			}
		}.run();
		;
	}

}
