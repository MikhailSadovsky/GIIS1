package by.bsuir.giis.util.algorithm.impl;

import java.awt.Color;
import java.util.ArrayList;

import by.bsuir.giis.model.Cell;
import by.bsuir.giis.model.Coordinates;
import by.bsuir.giis.util.algorithm.AbstractLine;
import by.bsuir.giis.view.MainFrame;

public class LineCDA extends AbstractLine {

	private float length;
	private float dX;
	private float dY;
	private float newX;
	private float newY;
	private Color color = Color.BLACK;
	int count = 0;

	public LineCDA(Coordinates coordinates) {

		this.beginPoint = coordinates.get(0);
		this.endPoint = coordinates.get(1);
		;

		cells = new ArrayList<Cell>();

		prepare();
	}

	@Override
	public void prepare() {

		length = Math.max(Math.abs(endPoint.x - beginPoint.x),
				Math.abs(endPoint.y - beginPoint.y));

		dX = (endPoint.x - beginPoint.x) / length;
		dY = (endPoint.y - beginPoint.y) / length;

		newX = (float) (beginPoint.x + 0.5 * sign(dX));
		newY = (float) (beginPoint.y + 0.5 * sign(dY));

		cells.clear();

		cells.add(new Cell((int) newX, (int) newY, color));
	}

	@Override
	public void execution() {

		for (count = 1; count <= length; count++) {

			newX = newX + dX;
			newY = newY + dY;

			cells.add(new Cell((int) newX, (int) newY, color));
			MainFrame.outputText = MainFrame.outputText + "Шаг " + count + " ";
			MainFrame.outputText = MainFrame.outputText + " Х: " + this.newX;
			MainFrame.outputText = MainFrame.outputText + " Y: " + this.newY
					+ "\n";
			MainFrame.outputText = MainFrame.outputText + " Координаты: ( "
					+ (int) this.newX + ", " + (int) this.newY + ") \n";

		}

		MainFrame.output.setText(MainFrame.outputText);
	}

}
