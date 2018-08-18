package work.doestheinternetstill.mousemover.gui;

import javax.swing.JFrame;
import javax.swing.JLabel;

public class MouseMoverGui {
	public void showGui() {
		JFrame frame = new JFrame("HelloWorldSwing");
	    final JLabel label = new JLabel("Hello World");
	    frame.getContentPane().add(label);

	    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    frame.pack();
	    frame.setVisible(true);
	}
	public void getOptions() {
		
	}
}
