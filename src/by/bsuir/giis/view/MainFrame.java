package by.bsuir.giis.view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.File;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JSlider;
import javax.swing.JTextArea;
import javax.swing.KeyStroke;
import javax.swing.UIManager;

import by.bsuir.giis.util.algorithm.AlgorithmType;

import com.alee.laf.WebLookAndFeel;

public class MainFrame extends JFrame {

	private static final long serialVersionUID = -1292116759702514832L;

	public static String outputText = "";
	public static JTextArea output = new JTextArea(50, 50);
	private ShapesToolBar toolBar;
	PaintPanel paintPanel;
	JLabel zoomLabel;
	private int zoom = 1;
	private File file = null;
	private JSlider zoomSlider;
	JRadioButton automaticallyRadioButton;
	JRadioButton byStepsRadioButton;
	JRadioButton moveRadioButton;
	JButton nextStepButton;
	private AlgorithmType algorithmType = AlgorithmType.CDA_LINE;
	private JCheckBox checkBoxForGrid;
	private JLabel firstPoint;
	private JLabel secondPoint;
	private JMenuItem saveMenuItem;

	final Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

	public MainFrame() {
		try {
			UIManager.setLookAndFeel(WebLookAndFeel.class.getCanonicalName());
			for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager
					.getInstalledLookAndFeels()) {
				if ("Windows".equals(info.getName())) {
					javax.swing.UIManager.setLookAndFeel(info.getClassName());
					break;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		setFocusable(true);
		setTitle("GIIS");

		paintPanel = new PaintPanel(this);
		this.setZoom(paintPanel.getStep());
		JScrollPane scroller = new JScrollPane(paintPanel);
		scroller.setPreferredSize(screenSize);
		scroller.setWheelScrollingEnabled(false);

		PixelStatusBar statusBar = new PixelStatusBar(this);

		addKeyListener(new KeyAdapter() {

			@Override
			public void keyPressed(KeyEvent e) {
				if (e.isControlDown() && e.getKeyCode() == KeyEvent.VK_Z) {
					paintPanel.deleteLastShape();
				}
			}
		});

		toolBar = new ShapesToolBar(this, paintPanel);
		JScrollPane outScroll = new JScrollPane(output);
		output.setLineWrap(true);

		add(scroller, BorderLayout.CENTER);
		add(outScroll, BorderLayout.EAST);
		setJMenuBar(createMenu());
		add(statusBar, BorderLayout.SOUTH);
		add(toolBar, BorderLayout.WEST);

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		screenSize.setSize(screenSize.getWidth() - 40,
				screenSize.getHeight() - 40);
		setSize(screenSize);
		setVisible(true);
	}

	public void changeZoom(int zoomValue) {

		paintPanel.updateGrid(zoomValue);
		paintPanel.setPreferredSize(new Dimension((int) screenSize.getWidth()
				* zoomValue, (int) screenSize.getHeight() * zoomValue));
		paintPanel.revalidate();
	}

	private JMenuBar createMenu() {
		JMenuBar menuBar = new JMenuBar();

		JMenu draw = new JMenu("Ğèñîâàíèå");

		JMenuItem clearMenuItem = new JMenuItem("Î÷èñòèòü", new ImageIcon(
				getImage("images/clear.png")));
		clearMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_DELETE,
				KeyEvent.SHIFT_MASK));
		clearMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				paintPanel.clearShapeList();
			}
		});

		draw.add(clearMenuItem);

		menuBar.add(draw);

		return menuBar;
	}

	public void setFirstPointCord(String text) {
		this.firstPoint.setText(text);
	}

	public void setSecondPoint(String text) {
		this.secondPoint.setText(text);
	}

	private Image getImage(String path) {
		ImageIcon icon = new ImageIcon(path);
		return icon.getImage().getScaledInstance(20, 20,
				java.awt.Image.SCALE_SMOOTH);
	}

	public boolean checkBoxIsSelected() {
		return checkBoxForGrid.isSelected();
	}

	public void setZoom(int zoom) {
		this.zoom = zoom;
	}

	public int getZoom() {
		return zoom;
	}

	public void setLineAlgorithm(AlgorithmType algorithmType) {
		this.paintPanel.setLineAlgorithm(algorithmType);
		System.out.println(algorithmType);
	}

	public AlgorithmType getLineAlgorithm() {
		return algorithmType;
	}

	public void setSliderOptions(int step) {
		if (step >= 1 && step <= 20) {
			zoomSlider.setValue(step);
			zoomLabel.setText("Pixel size: " + zoomSlider.getValue());
		}

	}

	public AlgorithmType getAlgorithmType() {
		return algorithmType;
	}

	public void setAlgorithmType(AlgorithmType algorithmType) {
		this.algorithmType = algorithmType;
	}

	public void setSaveEnabled(boolean saveEnabled) {
		if (file != null)
			this.saveMenuItem.setEnabled(saveEnabled);
	}

}
