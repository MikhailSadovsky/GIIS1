package by.bsuir.giis.controller.impl;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import by.bsuir.giis.model.Cell;
import by.bsuir.giis.model.Coordinates;
import by.bsuir.giis.util.algorithm.AlgorithmType;
import by.bsuir.giis.util.algorithm.conic.AbstractConic;
import by.bsuir.giis.util.algorithm.conic.impl.Circle;
import by.bsuir.giis.util.algorithm.line.AbstractLine;
import by.bsuir.giis.util.algorithm.line.impl.LineBREZ;
import by.bsuir.giis.util.algorithm.line.impl.LineCDA;
import by.bsuir.giis.util.algorithm.line.impl.LineWU;
import by.bsuir.giis.view.MainFrame;
import by.bsuir.giis.view.PaintPanel;

public class CellController {

	List<Cell> cells = null;
	List<Cell> cellsForStep = null;
	MainFrame mainFrame;
	PaintPanel paintPanel;
	private int step;

	Graphics gr;

	private AbstractLine lineAlgorithm;
	private AbstractConic conicAlgorithm;

	private AlgorithmType algorithmType;

	public CellController(MainFrame mainFrame, PaintPanel paintPanel) {
		cells = new ArrayList<Cell>();
		cellsForStep = new ArrayList<Cell>();
		this.mainFrame = mainFrame;
		this.paintPanel = paintPanel;
	}

	public void draw(Graphics g) {

		g.setColor(Color.DARK_GRAY);

		for (int index = 0; index < cells.size(); index++) {
			g.setColor(cells.get(index).getColor());
			g.fillRect(cells.get(index).getX() * getStep(), cells.get(index)
					.getY() * getStep(), getStep(), getStep());
			g.setColor(null);
		}

	}

	public void nextStep() {

		if (!cellsForStep.isEmpty()) {
			cells.add(cellsForStep.get(0));
			cellsForStep.remove(cellsForStep.get(0));
		} else
			JOptionPane.showMessageDialog(null, "Drawing complete!");
	}

	public Cell get(int i) {
		if (i < cells.size()) {
			return cells.get(i);
		}
		return null;
	}

	public void addCell(Cell cell) {
		cells.add(cell);
		System.out.println("AddCell: " + cell.getDescription());
	}

	public void addAll(List<Cell> cells) {
		this.cells.addAll(cells);
	}

	public boolean isEmpty() {
		return cells.isEmpty();
	}

	public void clear() {
		cells.clear();
	}

	public void setStep(int step) {
		this.step = step;
	}

	public int getStep() {
		return step;
	}

	public void setLineAlgorithm(AlgorithmType algorithmType) {
		this.algorithmType = algorithmType;
	}

	public void setCoordinatesForAlgorithm(Coordinates coordinates) {
		switch (algorithmType) {
		case CDA_LINE:
			lineAlgorithm = new LineCDA(coordinates);
			lineAlgorithm.execution();
			paintPanel.setCurrentShape(lineAlgorithm);
			break;
		case BREZ_LINE:
			lineAlgorithm = new LineBREZ(coordinates, step);
			lineAlgorithm.execution();
			paintPanel.setCurrentShape(lineAlgorithm);
			break;
		case WU_LINE:
			lineAlgorithm = new LineWU(coordinates);
			lineAlgorithm.execution();
			paintPanel.setCurrentShape(lineAlgorithm);
			break;
		case CIRCLE:
			conicAlgorithm = new Circle(coordinates);
			conicAlgorithm.execution();
			paintPanel.setCurrentShape(conicAlgorithm);
			break;
		default:
			break;
		}
	}
}
