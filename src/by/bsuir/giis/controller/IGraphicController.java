package by.bsuir.giis.controller;

import java.awt.Graphics2D;

public interface IGraphicController {

	boolean processMousePress(int x, int y, int cellSize);

	boolean processMouseRelease(int x, int y, int cellSize);

	boolean processMouseMove(int x, int y, int cellSize);

	boolean processMouseDoubleClick(int x, int y, int cellSize);

	String getName();

	void draw(Graphics2D g, int cellSize);
}
