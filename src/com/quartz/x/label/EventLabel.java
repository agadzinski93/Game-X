package com.quartz.x.label;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

public class EventLabel extends Label{
	
	public EventLabel(int xLoc, int yLoc, String text) {
		super(text, xLoc, yLoc);
		
		font = new Font("Serif", Font.BOLD, 22);
		color = Color.BLACK;
	}
	
	
	@Override
	public void render(Graphics g) {
		g.setColor(color);
		g.setFont(font);
		g.drawString(text, xLoc, yLoc);
	}

}
