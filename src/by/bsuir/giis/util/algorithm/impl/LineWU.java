package by.bsuir.giis.util.algorithm.impl;

import java.awt.Color;
import java.util.ArrayList;

import by.bsuir.giis.model.Cell;
import by.bsuir.giis.model.Coordinates;
import by.bsuir.giis.util.algorithm.AbstractLine;
import by.bsuir.giis.view.MainFrame;

public class LineWU extends AbstractLine {

	private int dX;
	private int dY;

	private Color color = Color.BLACK;

	int incx = 0;
	int incy = 0;

	int es = 0;
	int el = 0;

	double intensity;
	double iter = 0;

	boolean steep;

	int temp;

	public LineWU(Coordinates coordinates) {

		this.beginPoint = coordinates.get(0);
		this.endPoint = coordinates.get(1);
		;

		this.cells = new ArrayList<Cell>();

		prepare();
	}

	public void prepare() {

		// Вычисление изменения координат
		dX = endPoint.x - beginPoint.x;
		dY = endPoint.y - beginPoint.y;

		cells.clear();
	}

	public void execution() {

		// Для Х-линии (коэффициент наклона < 1)
		if (Math.abs(dY) < Math.abs(dX)) {
			// Первая точка должна иметь меньшую координату Х
			if (endPoint.x < beginPoint.x) {
				endPoint.x += beginPoint.x;
				beginPoint.x = endPoint.x - beginPoint.x;
				endPoint.x -= beginPoint.x;
				endPoint.y += beginPoint.y;
				beginPoint.y = endPoint.y - beginPoint.y;
				endPoint.y -= beginPoint.y;
			}
			// Относительное изменение координаты Y
			float grad = (float) dY / dX;
			// Промежуточная переменная для Y
			float intery = beginPoint.y + grad;
			// Первая точка
			cells.add(new Cell(beginPoint.x, beginPoint.y, color));

			MainFrame.outputText = MainFrame.outputText
					+ "Проекция на ось Х равна " + this.beginPoint.x;
			MainFrame.outputText = MainFrame.outputText
					+ " Проекция на ось Y равна " + this.beginPoint.y + "\n";
			MainFrame.outputText = MainFrame.outputText
					+ "Интенсивность пикселя: " + color.getAlpha() + "\n";

			for (int x = beginPoint.x + 1; x < endPoint.x; x++) {
				// Верхняя точка
				Color color = new Color(this.color.getRed(),
						this.color.getGreen(), this.color.getBlue(),
						(int) (255 - FPart(intery) * 255));
				cells.add(new Cell(x, IPart(intery), color));
				MainFrame.outputText = MainFrame.outputText
						+ "Проекция на ось Х равна " + x;
				MainFrame.outputText = MainFrame.outputText
						+ " Проекция на ось Y равна " + IPart(intery) + "\n";
				MainFrame.outputText = MainFrame.outputText
						+ " Интенсивность пикселя: " + color.getAlpha() + "\n";
				// Нижняя точка

				// Нижняя точка
				color = new Color(this.color.getRed(), this.color.getGreen(),
						this.color.getBlue(), (int) (FPart(intery) * 255));
				cells.add(new Cell(x, IPart(intery) + 1, color));
				int temp = IPart(intery) + 1;
				MainFrame.outputText = MainFrame.outputText
						+ "Проекция на ось Х равна " + x;
				MainFrame.outputText = MainFrame.outputText
						+ " Проекция на ось Y равна " + temp + "\n";
				MainFrame.outputText = MainFrame.outputText
						+ " Интенсивность пикселя: " + color.getAlpha() + "\n";

				// Изменение координаты Y
				intery += grad;
			}
			// Последняя точка
			cells.add(new Cell(endPoint.x, endPoint.y, color));
		}
		// Для Y-линии (коэффициент наклона > 1)
		else {
			// Первая точка должна иметь меньшую координату Y
			if (endPoint.y < beginPoint.y) {
				endPoint.x += beginPoint.x;
				beginPoint.x = endPoint.x - beginPoint.x;
				endPoint.x -= beginPoint.x;
				endPoint.y += beginPoint.y;
				beginPoint.y = endPoint.y - beginPoint.y;
				endPoint.y -= beginPoint.y;
			}
			// Относительное изменение координаты X
			float grad = (float) dX / dY;
			// Промежуточная переменная для X
			float interx = beginPoint.x + grad;
			// Первая точка
			cells.add(new Cell(beginPoint.x, beginPoint.y, color));

			MainFrame.outputText = MainFrame.outputText
					+ "Проекция на ось Х равна " + this.beginPoint.x;
			MainFrame.outputText = MainFrame.outputText
					+ " Проекция на ось Y равна " + this.beginPoint.y + "\n";
			MainFrame.outputText = MainFrame.outputText
					+ "Интенсивность пикселя: " + color.getAlpha() + "\n";

			for (int y = beginPoint.y + 1; y < endPoint.y; y++) {
				// Верхняя точка
				Color color = new Color(this.color.getRed(),
						this.color.getGreen(), this.color.getBlue(),
						255 - (int) (FPart(interx) * 255));
				cells.add(new Cell(IPart(interx), y, color));

				MainFrame.outputText = MainFrame.outputText
						+ "Проекция на ось Х равна " + IPart(interx);
				MainFrame.outputText = MainFrame.outputText
						+ " Проекция на ось Y равна " + y + "\n";
				MainFrame.outputText = MainFrame.outputText
						+ " Интенсивность пикселя: " + color.getAlpha() + "\n";
				// Нижняя точка
				color = new Color(this.color.getRed(), this.color.getGreen(),
						this.color.getBlue(), (int) (FPart(interx) * 255));
				cells.add(new Cell(IPart(interx) + 1, y, color));
				int tmp = IPart(interx) + 1;
				MainFrame.outputText = MainFrame.outputText
						+ "Проекция на ось Х равна " + tmp;
				MainFrame.outputText = MainFrame.outputText
						+ " Проекция на ось Y равна " + y + "\n";
				MainFrame.outputText = MainFrame.outputText
						+ " Интенсивность пикселя: " + color.getAlpha() + "\n";

				// Изменение координаты X
				interx += grad;
			}
			// Последняя точка
			cells.add(new Cell(endPoint.x, endPoint.y, color));

			MainFrame.outputText = MainFrame.outputText
					+ "Проекция на ось Х равна " + endPoint.x;
			MainFrame.outputText = MainFrame.outputText
					+ " Проекция на ось Y равна " + endPoint.y + "\n";
			MainFrame.outputText = MainFrame.outputText
					+ " Интенсивность пикселя: " + color.getAlpha() + "\n";

		}

		MainFrame.output.setText(MainFrame.outputText);

	}

}
