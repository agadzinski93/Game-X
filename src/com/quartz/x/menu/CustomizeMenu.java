package com.quartz.x.menu;

import java.awt.Graphics;
import java.awt.Point;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import com.quartz.x.player.Player;

public class CustomizeMenu extends Menu
{
	public CustomizeMenu(Player player)
	{
		super(player);
		try
		{
			background = ImageIO.read(new File("res/textures/customize.png"));
			width = background.getWidth();
			height = background.getHeight();
		}
		catch(IOException e)
		{
			e.printStackTrace();
		}
	}
	
	@Override
	public void update(Point pointPressed, Point pointReleased)
	{
		
	}
	
	@Override
	public void render(Graphics g)
	{
		g.drawImage(background, STATS_WIDTH, 0, width, height, null);
	}
}
