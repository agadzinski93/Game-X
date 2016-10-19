package com.quartz.x.label;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;

public class MainLabel extends Label
{
	private FontMetrics fm;
	private int x;
	private int y;
	
	private int buttonWidth;
	private int buttonHeight;
	private boolean calculatedPosition;
	
	public MainLabel()
	{
		super();
		
		font = new Font("Serif", Font.BOLD, 18);
		color = Color.red;
		calculatedPosition = false;
	}
	
	public MainLabel(String text, int xLoc, int yLoc)
	{
		super(text, xLoc, yLoc);
		
		font = new Font("Serif", Font.BOLD, 18);
		color = Color.red;
		calculatedPosition = false;
	}
	
	public MainLabel(String text, int xLoc, int yLoc, int buttonWidth, int buttonHeight)
	{
		super(text, xLoc, yLoc);
		this.buttonWidth = buttonWidth;
		this.buttonHeight = buttonHeight;
		
		font = new Font("Serif", Font.BOLD, 18);
		color = Color.red;
		calculatedPosition = false;
	}
	
	@Override
	public void render(Graphics g)
	{
		if (!calculatedPosition)
		{
			fm = g.getFontMetrics(font);
			x = xLoc + (buttonWidth - fm.stringWidth(text)) / 2;
			y = yLoc + (buttonHeight / 2 + fm.getHeight() / 4);
			calculatedPosition = true;
		}
		
		g.setColor(color);
		g.setFont(font);
		g.drawString(text, x, y);
	}
}
