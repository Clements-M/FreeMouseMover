package work.doestheinternetstill.mousemover;

import java.awt.AWTException;
import java.awt.Point;
import java.awt.Robot;

import work.doestheinternetstill.mousemover.gui.Options;
import work.doestheinternetstill.mousemover.gui.Options.MovementSpeed;

public class MovementManager {
	private static boolean runToggle = false;
	private static int sleepValue;
	private static Movement movement;
	static Robot robot;

	public static void toggleMovement(Options.MovementType movementType, Options.MovementSpeed movementSpeed,
			double duration) {
		setMovementSpeed(movementSpeed);
		setMovementType(movementType);
		startMovement(duration);
	}

	private static void setMovementSpeed(MovementSpeed movementSpeed) {
		sleepValue = movementSpeed.getInverseSpeed();
	}

	private static void setMovementType(Options.MovementType inputMovementType) {
		movement = inputMovementType.getM0vementTypeObject();
	}

	private static void loopMovementAction() {
		{
			Point nextLocation = movement.generateNextMouseLocation();
			robot.mouseMove((int) nextLocation.getX(), (int) nextLocation.getY());
		}
		try {
			Thread.sleep(sleepValue); // TODO make thread safe - causing slight pause after stopping
		} catch (InterruptedException e) {
			;
		} // TODO Properly handle these exceptions
	}

	private static void startMovement(double duration) {
		try {
			robot = new Robot();
		} catch (AWTException e) {
			;
		} // TODO Properly handle these exceptions

		new Runnable() {

			@Override
			public void run() {
				long startTime = System.currentTimeMillis();
				long currentTime = startTime;
				long endTime = (long) ((long) startTime + (duration * 60000));

				runToggle = true;

				while (currentTime < endTime && runToggle) {
					loopMovementAction();
					currentTime = System.currentTimeMillis();
				}

				runToggle = false;
			}
		}.run();
		;
	}

	public static void stopRun() {
		runToggle = false;
	}
}
