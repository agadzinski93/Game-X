package com.quartz.x.button;

import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import com.quartz.x.label.Label;

public abstract class Button
{
	protected BufferedImage background;
	protected BufferedImage backgroundNormal;
	protected BufferedImage backgroundClicked;
	protected BufferedImage backgroundDisabled;
	protected Label label;
	protected int x;
	protected int y;
	protected int width;
	protected int height;
	protected Rectangle area;
	protected boolean disabled;
	
	/**
	 * The default constructor that has yet to apply dimensions and location.
	 */
	public Button()
	{
		disabled = false;
	}
	
	/**
	 * Will construct a new button's dimensions and location.
	 * @param area - the rectangle that represents the area for the button.
	 */
	public Button(Rectangle area)
	{
		disabled = false;
		this.area = area;
		
		x = (int) area.getX();
		y = (int) area.getY();
		width = (int) area.getWidth();
		height = (int) area.getHeight();
	}
	
	/**
	 * Checks whether a mouse pressed within a rectangle 
	 * @param p - the point where the mouse was pressed
	 * @return true if the point lies in rectangle; false otherwise
	 */
	public boolean contains(Point p)
	{
		return area.contains(p);
	}
	
	/**
	 * Checks whether a mouse pressed and released within a rectangle 
	 * @param p1 - the point where the mouse was pressed
	 * @param p2 - the point where the mouse was released
	 * @return true if both points lie in rectangle; false otherwise
	 */
	public boolean contains(Point p1, Point p2)
	{
		return area.contains(p1) && area.contains(p2);
	}
	
	
	/**
	 * Renders the button onto a component.
	 * @param g - The Graphics object.
	 */
	public abstract void render(Graphics g);
	
	public boolean isDisabled()
	{
		return disabled;
	}
	
	public void fillArea(Graphics g)
	{
		g.drawRect(x, y, width, height);
	}
	
	public void setDisabled(boolean value)
	{
		disabled = value;
	}
}
