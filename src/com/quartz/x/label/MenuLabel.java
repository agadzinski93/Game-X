package com.quartz.x.label;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

public class MenuLabel extends Label
{
	public MenuLabel()
	{
		super();
		
		font = new Font("Serif", Font.BOLD, 32);
		color = Color.BLACK;
	}
	
	public MenuLabel(String text, int xLoc, int yLoc)
	{
		super(text, xLoc, yLoc);
		
		font = new Font("Serif", Font.BOLD, 32);
		color = Color.BLACK;
	}

	@Override
	public void render(Graphics g)
	{
		g.setColor(color);
		g.setFont(font);
		g.drawString(text, xLoc, yLoc);
	}

	public void render(Graphics g, int volume)
	{
		g.setColor(color);
		g.setFont(font);
		g.drawString(text + volume, xLoc, yLoc);
	}
}
