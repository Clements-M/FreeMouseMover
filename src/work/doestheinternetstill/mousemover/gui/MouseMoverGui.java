package work.doestheinternetstill.mousemover.gui;

import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.concurrent.Executors;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JSpinner;
import javax.swing.SpinnerModel;
import javax.swing.SpinnerNumberModel;
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
		frame.setResizable(false);
		frame.pack();
		frame.setVisible(true);
	}
}

class GridBagWindow extends JFrame implements ActionListener, KeyListener {
	private static final long serialVersionUID = 1L;

	private JButton startBtn;
	private JButton stopBtn;

	private JComboBox<MovementType> modeCombo;
	private JComboBox<MovementSpeed> speedCombo;

	private JSpinner duration;
	private SpinnerModel durationModel;

	private JLabel speedLbl;
	private JLabel modeLbl;
	private JLabel previewLbl;
	private JLabel durationLbl;

	private boolean startEnabledStopDisabled = true;

	public GridBagWindow() {
		Container contentPane = getContentPane();
		GridBagLayout gridbag = new GridBagLayout();
		contentPane.setLayout(gridbag);
		contentPane.setFocusable(true);
		contentPane.addKeyListener(this);

		GridBagConstraints constraints = new GridBagConstraints();
		constraints.insets = new Insets(5, 5, 5, 5);
		constraints.gridwidth = GridBagConstraints.REMAINDER;
		constraints.fill = GridBagConstraints.BOTH;

		modeLbl = new JLabel("Movement Mode");
		modeLbl.setFocusable(true);
		modeLbl.addKeyListener(this);
		constraints.gridx = 0;
		constraints.gridy = 0;
		gridbag.setConstraints(modeLbl, constraints);
		contentPane.add(modeLbl);

		MovementType[] modeOptions = { MovementType.Random, MovementType.Jitter };
		modeCombo = new JComboBox<MovementType>(modeOptions);
		modeCombo.setFocusable(true);
		modeCombo.addKeyListener(this);
		constraints.gridx = 1;
		constraints.gridy = 0;
		constraints.gridwidth = 1;
		gridbag.setConstraints(modeCombo, constraints);
		contentPane.add(modeCombo);

		speedLbl = new JLabel("Speed");
		speedLbl.setFocusable(true);
		speedLbl.addKeyListener(this);
		constraints.gridx = 0; // x grid position
		constraints.gridy = 1; // y grid position
		gridbag.setConstraints(speedLbl, constraints); // associate the label with a constraint object
		contentPane.add(speedLbl); // add it to content pane

		MovementSpeed[] speedOptions = { MovementSpeed.High, MovementSpeed.Medium, MovementSpeed.Low };
		speedCombo = new JComboBox<MovementSpeed>(speedOptions);
		speedCombo.setFocusable(true);
		speedCombo.addKeyListener(this);
		constraints.gridx = 1;
		constraints.gridy = 1;
		constraints.gridwidth = 1;
		gridbag.setConstraints(speedCombo, constraints);
		contentPane.add(speedCombo);

		durationLbl = new JLabel("Duration (Seconds)");
		durationLbl.setFocusable(true);
		durationLbl.addKeyListener(this);
		constraints.gridx = 0;
		constraints.gridy = 2;
		gridbag.setConstraints(durationLbl, constraints);
		contentPane.add(durationLbl);

		durationModel = new SpinnerNumberModel(10, 1, 10000, 1.0);
		duration = new JSpinner(durationModel);
		duration.setFocusable(false);
		duration.addKeyListener(this);
		constraints.gridx = 1;
		constraints.gridy = 2;
		constraints.gridwidth = 1;
		gridbag.setConstraints(duration, constraints);
		contentPane.add(duration);

		stopBtn = new JButton("Stop");
		constraints.gridx = 1;
		constraints.gridy = 3;
		gridbag.setConstraints(stopBtn, constraints);
		contentPane.add(stopBtn);
		stopBtn.setEnabled(!startEnabledStopDisabled);
		stopBtn.addActionListener(this);
		stopBtn.setFocusable(true);
		stopBtn.addKeyListener(this);

		startBtn = new JButton("Start");
		constraints.gridx = 0;
		constraints.gridy = 3;
		gridbag.setConstraints(startBtn, constraints);
		contentPane.add(startBtn);
		startBtn.setEnabled(startEnabledStopDisabled);
		startBtn.addActionListener(this);
		startBtn.setFocusable(true);
		startBtn.addKeyListener(this);

		previewLbl = new JLabel("Stopped - F6 To Start");
		previewLbl.setMaximumSize(new Dimension(116, 17));
		previewLbl.setMinimumSize(new Dimension(116, 17));
		previewLbl.setPreferredSize(new Dimension(116, 17));
		previewLbl.setFocusable(true);
		previewLbl.addKeyListener(this);
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
		if (!startEnabledStopDisabled)
			previewLbl.setText("Started - F6 To Stop");
		if (startEnabledStopDisabled)
			previewLbl.setText("Stopped - F6 To Start");
	}

	private void stopRun() {
		MovementManager.stopRun();
		toggleStartStopButtons();
	}

	private void startRun() {
		Executors.newSingleThreadExecutor().submit(new Runnable() {
			@Override
			public void run() {
				MovementManager.toggleMovement((Options.MovementType) modeCombo.getSelectedItem(),
						(Options.MovementSpeed) speedCombo.getSelectedItem(), (double) duration.getValue());

				toggleStartStopButtons();
			}
		});
	}

	public void actionPerformed(ActionEvent e) {
		if (startEnabledStopDisabled)
			startRun();
		if (!startEnabledStopDisabled)
			stopRun();
		toggleStartStopButtons();
	}

	@Override
	public void keyPressed(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_F6) {
			if (startEnabledStopDisabled)
				startRun();
			if (!startEnabledStopDisabled)
				stopRun();
			toggleStartStopButtons();
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		; // Do nothing
	}

	@Override
	public void keyTyped(KeyEvent e) {
		;
	}

}