package work.doestheinternetstill.mousemover.gui;

import java.awt.Container;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.concurrent.Executors;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import work.doestheinternetstill.mousemover.MovementManager;
import work.doestheinternetstill.mousemover.gui.Options.MovementSpeed;
import work.doestheinternetstill.mousemover.gui.Options.MovementType;

public class MouseMoverGui {

	public MouseMoverGui() {
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException
				| UnsupportedLookAndFeelException e) {
			;
		} // TODO Properly handle these exceptions
	}

	public void showGui() {
		GridBagWindow frame = new GridBagWindow();

		frame.setTitle("Free Mouse Mover");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.pack();
		frame.setVisible(true);
	}
}

class GridBagWindow extends JFrame implements ActionListener {
	private static final long serialVersionUID = 1L;

	private JButton startBtn;
	private JButton stopBtn;

	private JComboBox<MovementType> modeCombo;
	private JComboBox<MovementSpeed> speedCombo;
	private JComboBox<String> durationCombo; // TODO change to number field with + / - symbol

	private JLabel tagLbl;
	private JLabel tagModeLbl;
	private JLabel previewLbl;
	private JLabel durationLbl;

	private boolean startEnabledStopDisabled = true;

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

		MovementType[] modeOptions = { MovementType.Random, MovementType.Jitter };
		modeCombo = new JComboBox<MovementType>(modeOptions);
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

		MovementSpeed[] speedOptions = { MovementSpeed.High, MovementSpeed.Medium, MovementSpeed.Low };
		speedCombo = new JComboBox<MovementSpeed>(speedOptions);
		constraints.gridx = 1;
		constraints.gridy = 1;
		constraints.gridwidth = 1;
		gridbag.setConstraints(speedCombo, constraints);
		contentPane.add(speedCombo);

		durationLbl = new JLabel("Duration");
		constraints.gridx = 0;
		constraints.gridy = 2;
		gridbag.setConstraints(durationLbl, constraints);
		contentPane.add(durationLbl);

		String[] durationOptions = { "1", "2", "3" }; // ETC
		durationCombo = new JComboBox<String>(durationOptions);
		constraints.gridx = 1;
		constraints.gridy = 2;
		constraints.gridwidth = 1;
		gridbag.setConstraints(durationCombo, constraints);
		contentPane.add(durationCombo);

		stopBtn = new JButton("Stop");
		constraints.gridx = 1;
		constraints.gridy = 3;
		gridbag.setConstraints(stopBtn, constraints);
		contentPane.add(stopBtn);
		stopBtn.setEnabled(!startEnabledStopDisabled);
		stopBtn.addActionListener(this);

		startBtn = new JButton("Start");
		constraints.gridx = 0;
		constraints.gridy = 3;
		gridbag.setConstraints(startBtn, constraints);
		contentPane.add(startBtn);
		startBtn.setEnabled(startEnabledStopDisabled);
		startBtn.addActionListener(this);

		previewLbl = new JLabel("Stopped - F6 To Start");
		constraints.gridx = 0;
		constraints.gridy = 4;
		gridbag.setConstraints(previewLbl, constraints);
		contentPane.add(previewLbl);

		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});

	}

	private void toggleStartStopButtons() {
		startEnabledStopDisabled = !startEnabledStopDisabled;
		stopBtn.setEnabled(!startEnabledStopDisabled);
		startBtn.setEnabled(startEnabledStopDisabled);
	}

	public void actionPerformed(ActionEvent e) {
		toggleStartStopButtons();

		int duration = Integer.valueOf(durationCombo.getSelectedItem().toString());

		Executors.newSingleThreadExecutor().submit(new Runnable() {
			@Override
			public void run() {
				MovementManager.toggleMovement((Options.MovementType) modeCombo.getSelectedItem(),
						(Options.MovementSpeed) speedCombo.getSelectedItem(), duration);
			}
		});
	}
}