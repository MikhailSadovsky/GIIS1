package by.bsuir.giis.model;

import java.awt.Color;
import java.awt.Point;

public class Cell {

	private int x;
	private int y;

	private Color color;

	public Cell(int x, int y, Color color) {
		this.x = x;
		this.y = y;
		this.setColor(color);
	}

	public Cell(Point point, Color color) {
		this.x = point.x;
		this.y = point.y;
		this.setColor(color);
	}

	public String getDescription() {
		return "x: " + x + " y: " + y;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public void setColor(Color color) {
		this.color = color;
	}

	public Color getColor() {
		return color;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((color == null) ? 0 : color.hashCode());
		result = prime * result + x;
		result = prime * result + y;
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
		Cell other = (Cell) obj;
		if (color == null) {
			if (other.color != null)
				return false;
		} else if (!color.equals(other.color))
			return false;
		if (x != other.x)
			return false;
		if (y != other.y)
			return false;
		return true;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Cell [x=");
		builder.append(x);
		builder.append(", y=");
		builder.append(y);
		builder.append(", color=");
		builder.append(color);
		builder.append("]");
		return builder.toString();
	}

}
