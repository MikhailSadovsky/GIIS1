package by.bsuir.giis.controller;

import java.awt.Point;
import java.util.List;

public interface IPointMoveable {

	/**
	 * @return список опорных точек
	 */
	List<Point> getControlPoints();

	/**
	 * @param points
	 *            список опорных точек
	 */
	void setControlPoints(List<Point> points);
}
