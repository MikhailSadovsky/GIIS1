package by.bsuir.giis.util.algorithm.conic;

import java.awt.Graphics;
import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

import by.bsuir.giis.controller.IGraphicObject;
import by.bsuir.giis.controller.IPointMoveable;
import by.bsuir.giis.model.Cell;

public abstract class AbstractConic implements IGraphicObject, IPointMoveable {

	protected List<Cell> cells;
	protected Point centerPoint;

	/**
	 * ���������� ��������� � ������
	 */
	public abstract void prepare();

	/**
	 * ���������� ���������
	 * 
	 * @return ������ �����
	 */
	public abstract List<Cell> execution();

	@Override
	public List<Point> getControlPoints() {
		List<Point> points = new ArrayList<Point>();
		points.add(centerPoint);

		return points;
	}

	@Override
	public void setControlPoints(List<Point> points) {
		centerPoint = points.get(0);

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
		return null;
	}

}
