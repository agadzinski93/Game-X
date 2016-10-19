package com.quartz.x.button;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Rectangle;

import com.quartz.x.label.EventLabel;

public class EventButton extends Button{
	private EventLabel eventLabel;
	
	Color originalColor;
	
	public EventButton(Rectangle area, String text) {
		super(area);
		eventLabel = new EventLabel((int)area.getX(), (int)area.getY() + (int)area.getHeight(),
				text);
	}
	
	public void setColor(Color newColor)
	{
		eventLabel.setColor(newColor);
	}
	
	public void setFont(Font newFont)
	{
		eventLabel.setFont(newFont);
	}
	
	public void updateText(String newString)
	{
		eventLabel.update(newString);
	}
	
	public String getText()
	{
		return eventLabel.getText();
	}
	
	public Boolean isGray()
	{
		return eventLabel.getColor() == Color.GRAY;
	}
	
	@Override
	public void render(Graphics g) {
		eventLabel.render(g);
	}
	
	public void highlight(boolean clicked)
	{
		if (clicked)
		{
			originalColor = eventLabel.getColor();
			eventLabel.setColor(Color.BLUE);
		}
		else
		{
			eventLabel.setColor(originalColor);
		}
	}
}
