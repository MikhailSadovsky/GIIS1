package by.bsuir.giis.view;

import java.awt.Dimension;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JToolBar;

import by.bsuir.giis.util.algorithm.AlgorithmType;

import com.alee.laf.button.WebToggleButton;

public class ShapesToolBar extends JToolBar implements ActionListener {

	private static final long serialVersionUID = 1L;

	private final String GRID_ICON = "images/grid_2.png";
	private final String CDA_ICON = "images/cda_line.png";
	private final String BREZ_ICON = "images/brez_line.png";
	private final String WU_ICON = "images/wu_line.png";

	private final String GRID_ACTION = "grid";
	private final String CDA_ACTION = "cda";
	private final String BREZ_ACTION = "brez";
	private final String WU_ACTION = "wu";

	private WebToggleButton gridToggleButton = null;

	private MainFrame mainFrame = null;
	private PaintPanel paintPanel = null;

	public ShapesToolBar(MainFrame mainFrame, PaintPanel paintPanel) {
		super(JToolBar.VERTICAL);
		this.paintPanel = paintPanel;
		this.mainFrame = mainFrame;
		this.paintPanel = paintPanel;
		initComponents();
	}

	private void initComponents() {

		gridToggleButton = createToggleButton(GRID_ICON, "Сетка", GRID_ACTION);
		WebToggleButton cdaToggleButton = createToggleButton(CDA_ICON,
				"Алгоритм ЦДА", CDA_ACTION);
		WebToggleButton brezToggleButton = createToggleButton(BREZ_ICON,
				"Алгоритм Брезентхема", BREZ_ACTION);
		WebToggleButton wuToggleButton = createToggleButton(WU_ICON,
				"Алгоритм Ву", WU_ACTION);

		cdaToggleButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

			}
		});

		cdaToggleButton.setSelected(true);

		ButtonGroup buttonGroup = new ButtonGroup();
		buttonGroup.add(cdaToggleButton);
		buttonGroup.add(brezToggleButton);
		buttonGroup.add(wuToggleButton);

		add(cdaToggleButton);
		add(brezToggleButton);
		add(wuToggleButton);
		addSeparator();
		addSeparator();
		addSeparator();
		add(Box.createVerticalGlue());
		addSeparator();
		add(gridToggleButton);
	}

	private Image getImage(String path) {
		ImageIcon icon = new ImageIcon(path);
		return icon.getImage().getScaledInstance(30, 30,
				java.awt.Image.SCALE_SMOOTH);
	}

	private WebToggleButton createToggleButton(String iconPath,
			String toolTipText, String actionCommand) {

		WebToggleButton button = new WebToggleButton();
		button.setShadeToggleIcon(true);
		button.setIcon(new ImageIcon(getImage(iconPath)));
		button.setSelected(false);
		button.setMinimumSize(new Dimension(37, 37));
		button.setPreferredSize(new Dimension(37, 37));
		button.setMaximumSize(new Dimension(37, 37));
		button.addActionListener(this);
		button.setActionCommand(actionCommand);
		button.setToolTipText(toolTipText);
		button.setFocusable(false);

		return button;
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {

		String actionCommand = arg0.getActionCommand();

		if (actionCommand.equals(GRID_ACTION)) {
			paintPanel.showGrid(gridToggleButton.isSelected());
		} else if (actionCommand.equals(CDA_ACTION)) {
			mainFrame.setAlgorithmType(AlgorithmType.CDA_LINE);
		} else if (actionCommand.equals(BREZ_ACTION)) {
			mainFrame.setAlgorithmType(AlgorithmType.BREZ_LINE);
		} else if (actionCommand.equals(WU_ACTION)) {
			mainFrame.setAlgorithmType(AlgorithmType.WU_LINE);
		}
	}

}
