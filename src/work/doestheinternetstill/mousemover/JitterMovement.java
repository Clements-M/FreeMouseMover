package work.doestheinternetstill.mousemover;

import java.awt.MouseInfo;
import java.awt.Point;
import java.util.Random;

public class JitterMovement implements Movement{
	private static final int MAX_JITTER_SPACING = 15;

	public Point generateNextMouseLocation() {
		Random random = new Random();
		
        Point currPoint = MouseInfo.getPointerInfo().getLocation();
        
        int x = (int) currPoint.getX() + (random.nextInt(MAX_JITTER_SPACING * 2) - (MAX_JITTER_SPACING));
        int y = (int) currPoint.getY() + (random.nextInt(MAX_JITTER_SPACING * 2) - (MAX_JITTER_SPACING));
   
        System.out.println(random.nextInt(49));
   
		return new Point(x, y);
	}

}
