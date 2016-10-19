package com.quartz.x.input;

import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import com.quartz.x.map.Map;
import com.quartz.x.menu.Menu;

public class Mouse implements MouseListener
{
	private Point pointPressed;
	private Point pointReleased;
	
	public Mouse()
	{
		pointPressed = new Point(0,0);
		pointReleased = new Point(0,0);
	}

	@Override
	public void mouseClicked(MouseEvent e)
	{
	}

	@Override
	public void mouseEntered(MouseEvent e)
	{
	}

	@Override
	public void mouseExited(MouseEvent e)
	{
	}

	@Override
	public void mousePressed(MouseEvent e)
	{
		pointPressed = e.getPoint();
		Menu.setClicked(true);
		Menu.setReleased(false);
	}

	@Override
	public void mouseReleased(MouseEvent e)
	{
		pointReleased = e.getPoint();
		Menu.setClicked(false);
		Menu.setReleased(true);
		Map.setClicked(false);
		Map.setReleased(true);
	}
	
	public Point getPointPressed()
	{
		return pointPressed;
	}
	
	public Point getPointReleased()
	{
		return pointReleased;
	}
}
