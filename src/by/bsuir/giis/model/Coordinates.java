package by.bsuir.giis.model;

import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

public class Coordinates {

	private List<Point> points;

	public Coordinates() {
		points = new ArrayList<Point>();
	}

	public void add(Point point) {
		points.add(point);
	}

	public void remove(int index) {
		points.remove(index);
	}

	public void add(int index, Point element) {
		points.add(index, element);
	}

	public Point get(int index) {
		return points.get(index);
	}

	public void clear() {
		points.clear();
	}

	public boolean isEmpty() {
		return points.isEmpty();
	}

	public int size() {
		return points.size();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((points == null) ? 0 : points.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Coordinates other = (Coordinates) obj;
		if (points == null) {
			if (other.points != null)
				return false;
		} else if (!points.equals(other.points))
			return false;
		return true;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Coordinates [points=");
		builder.append(points);
		builder.append("]");
		return builder.toString();
	}

}
