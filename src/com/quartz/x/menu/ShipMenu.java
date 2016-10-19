package com.quartz.x.menu;

import java.awt.Graphics;
import java.awt.Point;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import com.quartz.x.Global;
import com.quartz.x.Sound.Sound;
import com.quartz.x.player.Player;

public class ShipMenu extends Menu 
{
	private BufferedImage playerShip;
	
	private int playerShipX, playerShipY;
	private int playerShipWidth, playerShipHeight;
	
	public ShipMenu(Player player)
	{
		super(player);
		
		playerShipX = 400;
		playerShipY = 75;
		
		try
		{
			background = ImageIO.read(new File("res/textures/sea.png"));
			width = background.getWidth();
			height = background.getHeight();
			
			playerShip = ImageIO.read(new File("res/textures/shipPlayer.png"));
			playerShipWidth = playerShip.getWidth();
			playerShipHeight = playerShip.getHeight();
			
			if(Global.musicType != "sailing")
			{
				playingTheme = false;
				Sound.themeStop();
			}
		}
		catch(IOException e)
		{
			e.printStackTrace();
		}
		
		if (Global.musicType != "sailing" && !mute)
		{
			Sound.sailingStart();
			Global.musicType = "sailing";
			playingTheme = true;
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
		g.drawImage(playerShip, playerShipX, playerShipY, playerShipWidth, playerShipHeight, null);
	}
}
