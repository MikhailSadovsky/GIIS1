package by.bsuir.giis.util.algorithm;

import java.awt.Graphics;
import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

import by.bsuir.giis.controller.IGraphicObject;
import by.bsuir.giis.controller.IPointMoveable;
import by.bsuir.giis.controller.impl.CellController;
import by.bsuir.giis.model.Cell;

public abstract class AbstractLine implements IPointMoveable, IGraphicObject {

	protected Point beginPoint;
	protected Point endPoint;

	protected CellController cControl;

	protected List<Cell> cells;

	/**
	 * ���������� ��������� � ������
	 */
	public abstract void prepare();

	/**
	 * ���������� ���������
	 * 
	 * @return ������ �����
	 */
	public abstract void execution();

	/**
	 * 
	 * @param num
	 *            ����� � ��������� ������
	 * @return -1, 0, 1 ��� ��������������, ��������, � �������������� ���������
	 *         ��������������
	 */
	public int sign(float num) {
		return (num > 0) ? 1 : (num < 0) ? -1 : 0;
	}

	/**
	 * 
	 * @param num
	 *            ����� �����
	 * @return -1, 0, 1 ��� ��������������, ��������, � �������������� ���������
	 *         ��������������
	 */
	public int sign(int num) {
		return (num > 0) ? 1 : (num < 0) ? -1 : 0;
	}

	/**
	 * 
	 * @param num
	 *            ����� � ��������� ������
	 * @return ����� ����� ���������
	 */
	public int IPart(double num) {
		return (int) num;
	}

	/**
	 * 
	 * @param num
	 *            ����� � ��������� ������
	 * @return ������� ����� �����
	 */
	public double FPart(double num) {
		return (((double) (num)) - (double) IPart(num));
	}

	@Override
	public List<Point> getControlPoints() {
		List<Point> points = new ArrayList<Point>();
		points.add(beginPoint);
		points.add(endPoint);
		return points;
	}

	@Override
	public void setControlPoints(List<Point> points) {
		beginPoint = points.get(0);
		endPoint = points.get(1);
		prepare();
		execution();
	}

	@Override
	public boolean processMousePress(int x, int y) {
		// TODO Auto-generated method stub

		return false;
	}

	@Override
	public boolean processMouseRelease(int x, int y) {
		// TODO Auto-generated method stub

		return false;
	}

	@Override
	public boolean processMouseMove(int x, int y) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean processMouseDoubleClick(int x, int y) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void draw(Graphics g, int step) {
		for (Cell cell : cells) {
			g.setColor(cell.getColor());
			g.fillRect(cell.getX() * step, cell.getY() * step, step, step);
			g.setColor(null);
		}
	}

	@Override
	public boolean isComplete() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public List<Cell> getPoints() {
		// TODO Auto-generated method stub
		return cells;
	}
}
