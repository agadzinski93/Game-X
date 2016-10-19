package com.quartz.x.label;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

public class StatsLabel extends Label
{
	public StatsLabel()
	{
		super();
		
		font = new Font("Serif", Font.BOLD, 24);
		color = Color.red;
	}
	
	public StatsLabel(String text, int xLoc, int yLoc)
	{
		super(text, xLoc, yLoc);
		
		font = new Font("Serif", Font.BOLD, 24);
		color = Color.red;
	}
	
	@Override
	public void render(Graphics g)
	{
		g.setColor(color);
		g.setFont(font);
		g.drawString(text, xLoc, yLoc);
	}
}
