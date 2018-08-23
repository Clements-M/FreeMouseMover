package work.doestheinternetstill.mousemover;

import java.awt.Dimension;
import java.awt.Point;
import java.util.Random;

public class RandomMovement implements Movement {

	private int topLeftX, topLeftY;
	private int botRightX, botRightY;
	Random random;

	public RandomMovement(int topLeftX, int topLeftY, int botRightX, int botRightY) {
		this.topLeftX = topLeftX;
		this.topLeftY = topLeftY;
		this.botRightX = botRightX;
		this.botRightY = botRightY;
		random = new Random();
	}

	public RandomMovement(Point topLeftPoint, Point bottomRightPoint) {
		topLeftX = (int) topLeftPoint.getX();
		topLeftY = (int) topLeftPoint.getY();
		botRightX = (int) bottomRightPoint.getX();
		botRightY = (int) bottomRightPoint.getY();
		random = new Random();
	}

	public Point generateNextMouseLocation() {
		int pointX = topLeftX + random.nextInt((botRightX - topLeftX));
		int pointY = topLeftY + random.nextInt((botRightY - topLeftY));
		return new Point(pointX, pointY);
	}

	public static RandomMovement generateRandomScreenMovement() {
		Dimension mainScreenSize = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
		final Point topLeft = new Point(0, 0);
		final Point bottomRight = new Point((int) mainScreenSize.getWidth(), (int) mainScreenSize.getHeight());
		return new RandomMovement(topLeft, bottomRight);
	}

}
