package com.quartz.x.label;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

public abstract class Label
{
	protected int xLoc;
	protected int yLoc;
	
	protected String text;
	protected Font font;
	protected Color color;
	
	public Label()
	{
		text = "";
		xLoc = 0;
		yLoc = 0;
	}
	
	public Label(String text, int xLoc, int yLoc)
	{
		this.text = text;
		this.xLoc = xLoc;
		this.yLoc = yLoc;
	}
	
	public void update(String string)
	{
		text = string;
	}
	
	public void setColor(Color newColor)
	{
		color = newColor;
	}
	
	public void setFont(Font newFont)
	{
		font = newFont;
	}
	
	public Color getColor()
	{
		return color;
	}
	
	public void setYPos(int newYPos)
	{
		yLoc = newYPos;
	}
	
	public String getText()
	{
		return text;
	}
	
	public int getX()
	{
		return xLoc;
	}
	
	public int getY()
	{
		return yLoc;
	}
	
	public abstract void render(Graphics g);
}
