package by.bsuir.giis.util.algorithm.line.impl;

import java.awt.Color;
import java.util.ArrayList;

import by.bsuir.giis.model.Cell;
import by.bsuir.giis.model.Coordinates;
import by.bsuir.giis.util.algorithm.line.AbstractLine;
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

		// ���������� ��������� ���������
		dX = endPoint.x - beginPoint.x;
		dY = endPoint.y - beginPoint.y;

		cells.clear();
	}

	public void execution() {

		// ��� �-����� (����������� ������� < 1)
		if (Math.abs(dY) < Math.abs(dX)) {
			// ������ ����� ������ ����� ������� ���������� �
			if (endPoint.x < beginPoint.x) {
				endPoint.x += beginPoint.x;
				beginPoint.x = endPoint.x - beginPoint.x;
				endPoint.x -= beginPoint.x;
				endPoint.y += beginPoint.y;
				beginPoint.y = endPoint.y - beginPoint.y;
				endPoint.y -= beginPoint.y;
			}
			// ������������� ��������� ���������� Y
			float grad = (float) dY / dX;
			// ������������� ���������� ��� Y
			float intery = beginPoint.y + grad;
			// ������ �����
			cells.add(new Cell(beginPoint.x, beginPoint.y, color));

			MainFrame.outputText = MainFrame.outputText
					+ " X: " + this.beginPoint.x;
			MainFrame.outputText = MainFrame.outputText
					+ " Y: " + this.beginPoint.y + "\n";
			MainFrame.outputText = MainFrame.outputText
					+ " Color alpha: " + color.getAlpha() + "\n";

			for (int x = beginPoint.x + 1; x < endPoint.x; x++) {
				// ������� �����
				Color color = new Color(this.color.getRed(),
						this.color.getGreen(), this.color.getBlue(),
						(int) (255 - FPart(intery) * 255));
				cells.add(new Cell(x, IPart(intery), color));
				MainFrame.outputText = MainFrame.outputText
						+ " X: " + x;
				MainFrame.outputText = MainFrame.outputText
						+ " Y: " + IPart(intery) + "\n";
				MainFrame.outputText = MainFrame.outputText
						+ " Color alpha: " + color.getAlpha() + "\n";
				// ������ �����

				// ������ �����
				color = new Color(this.color.getRed(), this.color.getGreen(),
						this.color.getBlue(), (int) (FPart(intery) * 255));
				cells.add(new Cell(x, IPart(intery) + 1, color));
				int temp = IPart(intery) + 1;
				MainFrame.outputText = MainFrame.outputText
						+ " X: " + x;
				MainFrame.outputText = MainFrame.outputText
						+ " Y: " + temp + "\n";
				MainFrame.outputText = MainFrame.outputText
						+ " Color alpha: " + color.getAlpha() + "\n";

				// ��������� ���������� Y
				intery += grad;
			}
			// ��������� �����
			cells.add(new Cell(endPoint.x, endPoint.y, color));
		}
		// ��� Y-����� (����������� ������� > 1)
		else {
			// ������ ����� ������ ����� ������� ���������� Y
			if (endPoint.y < beginPoint.y) {
				endPoint.x += beginPoint.x;
				beginPoint.x = endPoint.x - beginPoint.x;
				endPoint.x -= beginPoint.x;
				endPoint.y += beginPoint.y;
				beginPoint.y = endPoint.y - beginPoint.y;
				endPoint.y -= beginPoint.y;
			}
			// ������������� ��������� ���������� X
			float grad = (float) dX / dY;
			// ������������� ���������� ��� X
			float interx = beginPoint.x + grad;
			// ������ �����
			cells.add(new Cell(beginPoint.x, beginPoint.y, color));

			MainFrame.outputText = MainFrame.outputText
					+ " X: " + this.beginPoint.x;
			MainFrame.outputText = MainFrame.outputText
					+ " Y: " + this.beginPoint.y + "\n";
			MainFrame.outputText = MainFrame.outputText
					+ " Color alpha: " + color.getAlpha() + "\n";

			for (int y = beginPoint.y + 1; y < endPoint.y; y++) {
				// ������� �����
				Color color = new Color(this.color.getRed(),
						this.color.getGreen(), this.color.getBlue(),
						255 - (int) (FPart(interx) * 255));
				cells.add(new Cell(IPart(interx), y, color));

				MainFrame.outputText = MainFrame.outputText
						+ " X: " + IPart(interx);
				MainFrame.outputText = MainFrame.outputText
						+ " Y: " + y + "\n";
				MainFrame.outputText = MainFrame.outputText
						+ " Color alpha: " + color.getAlpha() + "\n";
				// ������ �����
				color = new Color(this.color.getRed(), this.color.getGreen(),
						this.color.getBlue(), (int) (FPart(interx) * 255));
				cells.add(new Cell(IPart(interx) + 1, y, color));
				int tmp = IPart(interx) + 1;
				MainFrame.outputText = MainFrame.outputText
						+ " X: " + tmp;
				MainFrame.outputText = MainFrame.outputText
						+ " Y: " + y + "\n";
				MainFrame.outputText = MainFrame.outputText
						+ " Color alpha: " + color.getAlpha() + "\n";

				// ��������� ���������� X
				interx += grad;
			}
			// ��������� �����
			cells.add(new Cell(endPoint.x, endPoint.y, color));

			MainFrame.outputText = MainFrame.outputText
					+ " X: " + endPoint.x;
			MainFrame.outputText = MainFrame.outputText
					+ " Y: " + endPoint.y + "\n";
			MainFrame.outputText = MainFrame.outputText
					+ " Color alpha: " + color.getAlpha() + "\n";

		}

		MainFrame.output.setText(MainFrame.outputText);

	}

}
