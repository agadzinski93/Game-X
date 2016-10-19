package com.quartz.x.map;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

import com.quartz.x.button.Button;

public class MapPoint extends Button
{
	private boolean reachable;
	private boolean visited;
	int[] travelable;
	private boolean exitPoint;
	
	public MapPoint()
	{
	}
	
	public MapPoint(int xLoc, int yLoc, int travelable[], boolean exitPoint)
	{
		x = xLoc;
		y = yLoc;
		width = 15;
		height = 15;
		area = new Rectangle(x, y, width, height);
		this.travelable = travelable;
		visited = false;
		this.exitPoint = exitPoint;
		
		//pointEvent = Const.BattleEvents[random int]
		
	}
	
	public int getX()
	{
		return x;
	}
	
	public int getY()
	{
		return y;
	}
	
	public void setReachable(boolean value)
	{
		reachable = value;
	}
	
	public boolean getReachable()
	{
		return reachable;
	}
	
	public void setVisited(boolean value)
	{
		visited = value;
	}
	
	public boolean getVisited()
	{
		return visited;
	}
	
	public int[] getTravelable()
	{
		return travelable;
	}
	
	public boolean getExitPoint()
	{
		return exitPoint;
	}

	@Override
	public void render(Graphics g)
	{
		if (!visited) 
			g.setColor(Color.GREEN);
		else
			g.setColor(Color.GRAY);
		
		g.fillRect(x, y, width, height);
	}
}
