package by.bsuir.giis.controller;

import java.awt.Point;
import java.util.List;

public interface IPointMoveable {

	/**
	 * @return ������ ������� �����
	 */
	List<Point> getControlPoints();

	/**
	 * @param points
	 *            ������ ������� �����
	 */
	void setControlPoints(List<Point> points);
}
