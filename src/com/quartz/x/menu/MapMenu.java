package com.quartz.x.menu;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import com.quartz.x.Sound.Sound;
import com.quartz.x.label.MainLabel;
import com.quartz.x.map.Map;
import com.quartz.x.map.MapPoint;
import com.quartz.x.player.Player;

public class MapMenu extends Menu
{
	private Map map;
	private MapPoint[] mapPoints;
	private int playerPoint;
	private int xLoc;
	private int yLoc;
	private MainLabel exitLabel;
	
	public MapMenu(Player player)
	{
		super(player);
		try
		{
			background = ImageIO.read(new File("res/textures/map.png"));
			width = background.getWidth();
			height = background.getHeight();
		}
		catch(IOException e)
		{
			e.printStackTrace();
		}

		this.player = player;
		this.map = player.getMap();
		mapPoints = map.getMapPoints();
		
		for (int i = 0; i < mapPoints.length; i++) {
			if (mapPoints[i].getExitPoint()) {
				exitLabel = new MainLabel("<- Exit", mapPoints[i].getX() + 75, mapPoints[i].getY() + 7);
			}
		}
	}

	@Override
	public void update(Point pointPressed, Point pointReleased)
	{
		playerPoint = player.getMapPoint();
		map.update(pointPressed, pointReleased, player);
	}
	
	@Override
	public void render(Graphics g)
	{
		g.drawImage(background, STATS_WIDTH, 0, width, height, null);
		
		for (int i = 0; i < mapPoints.length; ++i)
			mapPoints[i].render(g);
		
		g.setColor(Color.BLACK);
		
		xLoc = mapPoints[playerPoint].getX();
		yLoc = mapPoints[playerPoint].getY();
		
		for (int i = 0; i < mapPoints[playerPoint].getTravelable().length; i++)
			g.drawLine(xLoc + 7, yLoc + 7, 
					mapPoints[mapPoints[playerPoint].getTravelable()[i]].getX() + 7, 
					mapPoints[mapPoints[playerPoint].getTravelable()[i]].getY() + 7);
		
		exitLabel.render(g);
	
		MainLabel arrow = new MainLabel("V", xLoc + 7, yLoc - 8);
		arrow.render(g);
		
		MainLabel areaLabel = new MainLabel("Area: " + (player.getMapCount() + 1), 800, 130);
		areaLabel.render(g);
		
		/* Uncomment Following Code For MapPoint Numbers
		for (Integer i = new Integer(0); i < mapPoints.length; i++)
		{
			MainLabel n = new MainLabel(i.toString(), mapPoints[i].getX(), mapPoints[i].getY());
			n.render(g);
		}
		*/
	}
}
