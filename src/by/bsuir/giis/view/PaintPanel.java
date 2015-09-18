package by.bsuir.giis.view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JPanel;

import by.bsuir.giis.controller.IGraphicController;
import by.bsuir.giis.controller.IGraphicObject;
import by.bsuir.giis.controller.IPointMoveable;
import by.bsuir.giis.controller.impl.CellController;
import by.bsuir.giis.controller.impl.MovePointController;
import by.bsuir.giis.model.Cell;
import by.bsuir.giis.model.Coordinates;
import by.bsuir.giis.util.algorithm.AlgorithmType;

public class PaintPanel extends JPanel {

	private static final long serialVersionUID = 8409819777725124351L;

	MainFrame mainFrame;

	private final Color gridColor = Color.LIGHT_GRAY;

	private int width;
	private int height;
	private int step = 1;

	private boolean showGrid = false;

	private Coordinates coordinates;

	private IGraphicObject currentShape;

	private List<IGraphicObject> shapeList;

	private IGraphicController graphicController;

	public int getStep() {
		return step;
	}

	private List<Cell> clickedCells = new ArrayList<Cell>();
	private List<Cell> cells = null;
	private CellController cellControl = null;

	PaintPanel paintPanel = this;

	private boolean showControlPoints = false;

	private int shapesCount = 0;

	public PaintPanel(final MainFrame mainFrame) {

		cells = new ArrayList<Cell>();

		shapeList = new ArrayList<IGraphicObject>();

		cellControl = new CellController(mainFrame, this);
		cellControl.setStep(step);
		coordinates = new Coordinates();

		this.mainFrame = mainFrame;

		setBackground(Color.WHITE);

		addMouseWheelListener(new MouseWheelListener() {

			@Override
			public void mouseWheelMoved(MouseWheelEvent e) {
				if (e.isControlDown()) {
					if (e.getWheelRotation() == 1 && (step + 1) >= 1
							&& (step + 1) <= 20) {
						updateGrid(step + 1);
						paintPanel.setPreferredSize(new Dimension(
								(int) width + 1, (int) height + 1));
						paintPanel.revalidate();
					}
					if (e.getWheelRotation() == -1 && (step - 1) >= 1
							&& (step - 1) <= 20) {
						updateGrid(step - 1);
						paintPanel.setPreferredSize(new Dimension(
								(int) width - 1, (int) height - 1));
						paintPanel.revalidate();
					}
				}
			}
		});

		addMouseListener();
	}

	@Override
	public void paint(Graphics g) {
		super.paint(g);

		width = getWidth();
		height = getHeight();

		if (showGrid && (step > 2))
			drawGrid(g);

		if (!clickedCells.isEmpty()) {
			for (Cell cell : clickedCells) {
				g.fillRect(cell.getX() * step, cell.getY() * step, step, step);
			}
		}

		if (shapeList != null)
			for (IGraphicObject object : shapeList)
				object.draw(g, step);

		if (showControlPoints && currentShape != null)
			if (graphicController != null)
				graphicController.draw((Graphics2D) g, step);

		if (shapeList.size() != shapesCount) {
			mainFrame.setSaveEnabled(true);
			shapesCount = shapeList.size();
		} else
			mainFrame.setSaveEnabled(false);
	}

	private void drawGrid(Graphics g) {
		g.setColor(gridColor);

		int progress = step;
		for (int count = 0; progress < width; count++) {
			g.drawLine(progress, 0, progress, height);
			progress = count * step;
		}

		progress = step;
		for (int count = 0; progress < height; count++) {
			g.drawLine(0, progress, width, progress);
			progress = count * step;
		}
	}

	public void updateGrid(final int step) {
		this.step = step;
		cells.clear();
		cellControl.setStep(step);
		repaint();
	}

	public void addMouseListener() {

		this.addMouseMotionListener(new MouseMotionListener() {

			@Override
			public void mouseMoved(MouseEvent e) {
			}

			@Override
			public void mouseDragged(MouseEvent e) {
				if (graphicController != null) {
					graphicController.processMouseMove(e.getX(), e.getY(), step);
				}
				repaint();
			}
		});

		this.addMouseListener(new MouseAdapter() {
			private boolean endClicked = false;

			@Override
			public void mousePressed(MouseEvent e) {

				if (graphicController != null) {
					boolean res = graphicController.processMousePress(e.getX(),
							e.getY(), step);
					if (!res) {
					}
				}

			}

			@Override
			public void mouseReleased(MouseEvent e) {
				if (graphicController != null) {
					graphicController.processMouseRelease(e.getX(), e.getY(),
							step);
				}
				repaint();
			}

			@Override
			public void mouseClicked(MouseEvent e) {

				if (e.getButton() == MouseEvent.BUTTON3) {
					try {
						currentShape = getShapeByCoord(e.getX(), e.getY());
					} catch (NullPointerException xzException) {
						System.out.println("Что то тут не так");
					}
					if (currentShape != null)
						setGraphicsObjectConntrol(new MovePointController(
								(IPointMoveable) currentShape));
					clickedCells.clear();
					return;
				}

				switch (mainFrame.getAlgorithmType()) {
				case BREZ_LINE:
				case CDA_LINE:
				case WU_LINE:
				default:
					break;
				}

				if (endClicked) {
					cellControl.setLineAlgorithm(mainFrame.getAlgorithmType());
					cellControl.setCootdinatesForAlgorithm(coordinates);
					coordinates.clear();
					clickedCells.clear();
					endClicked = false;
				}
			}

		});
		repaint();
	}

	public void paint() {
		repaint();
	}

	public void nextStep() {
		cellControl.nextStep();
		repaint();
	}

	public void setLineAlgorithm(AlgorithmType algorithmType) {
		this.cellControl.setLineAlgorithm(algorithmType);
		System.out.println(algorithmType);
	}

	public void clearAll() {
		this.cellControl.clear();
		clickedCells.clear();
		repaint();

	}

	public void showGrid(boolean showGrid) {
		this.showGrid = showGrid;
		repaint();
	}

	public void showControlPoints(boolean showControlPoints) {
		this.showControlPoints = showControlPoints;

		if (showControlPoints && currentShape != null)
			setGraphicsObjectConntrol(new MovePointController(
					(IPointMoveable) currentShape));
		else
			graphicController = null;

		repaint();
	}

	public void setGraphicsObjectConntrol(IGraphicController pintsMoveControl) {
		graphicController = pintsMoveControl;
		repaint();
	}

	public void clearShapeList() {
		shapeList.clear();
		graphicController = null;
		repaint();
	}

	public void setCurrentShape(IGraphicObject object) {

		shapeList.add(object);
		currentShape = object;
	}

	public IGraphicObject getShapeByCoord(int x, int y) {

		for (IGraphicObject object : shapeList) {

			List<Cell> cells = (ArrayList<Cell>) object.getPoints();
			for (Cell cell : cells)
				if (cell.getX() == x / step && cell.getY() == y / step)
					return object;

		}
		return currentShape;
	}

	public void deleteLastShape() {
		if (shapeList.size() != 0) {
			shapeList.remove(shapeList.size() - 1);
			if (shapeList.size() >= 1) {
				currentShape = shapeList.get(shapeList.size() - 1);
				setGraphicsObjectConntrol(new MovePointController(
						(IPointMoveable) currentShape));
			} else {
				graphicController = null;
				currentShape = null;
			}
		}
		repaint();
	}

}
