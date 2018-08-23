package work.doestheinternetstill.mousemover.gui;

import java.awt.Container;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class MouseMoverGui {
	public void showGui() {
		GridBagWindow frame = new GridBagWindow();

		frame.setTitle("Free Mouse Mover");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.pack();
		frame.setVisible(true);
	}

	public void getOptions() {

	}
}

class GridBagWindow extends JFrame {
	private static final long serialVersionUID = 1L;

	private JButton startBtn;
	private JButton stopBtn;

	private JComboBox<String> modeCombo;
	private JComboBox<String> speedCombo;

	private JLabel tagLbl;
	private JLabel tagModeLbl;
	private JLabel previewLbl;

	public GridBagWindow() {
		Container contentPane = getContentPane();
		GridBagLayout gridbag = new GridBagLayout();
		contentPane.setLayout(gridbag);

		GridBagConstraints constraints = new GridBagConstraints();
		constraints.insets = new Insets(5, 5, 5, 5);
		constraints.gridwidth = GridBagConstraints.REMAINDER;
		constraints.fill = GridBagConstraints.BOTH;

		tagModeLbl = new JLabel("Movement Mode");
		constraints.gridx = 0;
		constraints.gridy = 0;
		gridbag.setConstraints(tagModeLbl, constraints);
		contentPane.add(tagModeLbl);

		String[] modeOptions = { "Random", "Jitter", "Circular" };
		modeCombo = new JComboBox<String>(modeOptions);
		constraints.gridx = 1;
		constraints.gridy = 0;
		constraints.gridwidth = 1;
		gridbag.setConstraints(modeCombo, constraints);
		contentPane.add(modeCombo);

		tagLbl = new JLabel("Speed");
		constraints.gridx = 0; // x grid position
		constraints.gridy = 1; // y grid position
		gridbag.setConstraints(tagLbl, constraints); // associate the label with a constraint object
		contentPane.add(tagLbl); // add it to content pane

		String[] speedOptions = { "High", "Medium", "Low" };
		speedCombo = new JComboBox<String>(speedOptions);
		constraints.gridx = 1;
		constraints.gridy = 1;
		constraints.gridwidth = 1;
		gridbag.setConstraints(speedCombo, constraints);
		contentPane.add(speedCombo);

		stopBtn = new JButton("Stop");
		constraints.gridx = 1;
		constraints.gridy = 2;
		gridbag.setConstraints(stopBtn, constraints);
		contentPane.add(stopBtn);

		startBtn = new JButton("Start");
		constraints.gridx = 0;
		constraints.gridy = 2;
		gridbag.setConstraints(startBtn, constraints);
		contentPane.add(startBtn);

		previewLbl = new JLabel("Stopped - F6 To Start");
		constraints.gridx = 0;
		constraints.gridy = 3;
		gridbag.setConstraints(previewLbl, constraints);
		contentPane.add(previewLbl);

		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});
	}
}