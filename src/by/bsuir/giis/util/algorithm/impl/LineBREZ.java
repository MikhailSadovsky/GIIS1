package by.bsuir.giis.util.algorithm.impl;

import java.awt.Color;
import java.awt.Point;
import java.util.ArrayList;

import by.bsuir.giis.model.Cell;
import by.bsuir.giis.model.Coordinates;
import by.bsuir.giis.util.algorithm.AbstractLine;
import by.bsuir.giis.view.MainFrame;

public class LineBREZ extends AbstractLine {

	private int newX;
	private int newY;

	private Color color = Color.BLACK;

	private int dX;
	private int dY;

	private int incx;
	private int incy;

	int es = 0;
	int el = 0;

	private int e;

	public LineBREZ(Coordinates coordinates, int step) {

		this.beginPoint = coordinates.get(0);
		this.endPoint = coordinates.get(1);

		this.cells = new ArrayList<Cell>();

		prepare();

	}

	public LineBREZ(Point point1, Point point2) {
		// TODO Auto-generated constructor stub
		this.beginPoint = point1;
		this.endPoint = point2;

		this.cells = new ArrayList<Cell>();

		prepare();
	}

	public void prepare() {

		this.newX = beginPoint.x;
		this.newY = beginPoint.y;

		this.dX = (endPoint.x - beginPoint.x); // �������� �� ��� x
		this.dY = (endPoint.y - beginPoint.y); // �������� �� ��� y

		incx = sign(dX);
		/*
		 * ����������, � ����� ������� ����� ����� ����������. ���� dx < 0, �.�.
		 * ������� ��� ������ ������ �� ����, �� incx ����� ����� -1. ��� �����
		 * �������������� � ����� �����������.
		 */
		incy = sign(dY);
		/*
		 * ����������. ���� ������ ������� ����� ����� - ��� ����� �������������
		 * ����� ��� y (����� - �������������).
		 */

		if (dX < 0)
			dX = -dX;// ����� �� ����� ����������: "if (dx < dy)"
		if (dY < 0)
			dY = -dY;// ������� ���������� ������� dx = |dx|; dy = |dy|
		// ��� ��� ������� ����� �������� � ���: dx = Math.abs(dx); dy =
		// Math.abs(dy);

		if (dX > dY) {
			/*
			 * ���� dx > dy, �� ������ ������� "�������" ����� ��� ���, �.�. ��
			 * ������ �������, ��� �������. ������ � ����� ����� ����� ���� ��
			 * ��� (������� el = dx;), ������ "�����������" ������ �� ���� ����
			 * � ������������ � ���, ����� ������� � ������ ������ ��� ��� (pdx
			 * = incx;), ��� ���� �� y ����� ����� �����������.
			 */
			dX = incx;
			dY = 0;
			es = Math.abs(endPoint.y - beginPoint.y);
			el = Math.abs(endPoint.x - beginPoint.x);
		} else {
			// ������, ����� ������ ������ "�������", ��� �������, �.�. ��������
			// �� ��� y
			dX = 0;
			dY = incy;
			es = Math.abs(endPoint.x - beginPoint.x);
			el = Math.abs(endPoint.y - beginPoint.y);
		}

		e = el;

		cells.clear();

		cells.add(new Cell((int) newX, (int) newY, color));
	}

	public void execution() {

		for (int t = 1; t <= el; t++) {
			e -= 2 * es;
			if (e < 0) {
				e += 2 * el;
				newX += incx;
				newY += incy;
			} else {
				newX += dX;
				newY += dY;
			}
			MainFrame.outputText = MainFrame.outputText + "��� " + t + " ";
			MainFrame.outputText = MainFrame.outputText
					+ "�������� �� ��� � ����� " + this.newX;
			MainFrame.outputText = MainFrame.outputText
					+ " �������� �� ��� Y ����� " + this.newY + "\n";
			MainFrame.outputText = MainFrame.outputText
					+ " �������� ������ � ����� " + e + "\n";
			cells.add(new Cell((int) newX, (int) newY, color));
		}
		MainFrame.output.setText(MainFrame.outputText);

	}

}
