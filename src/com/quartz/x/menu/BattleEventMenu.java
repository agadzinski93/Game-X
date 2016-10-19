package com.quartz.x.menu;

import java.awt.Graphics;
import java.awt.Point;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import com.quartz.x.Global;
import com.quartz.x.Sound.Sound;
import com.quartz.x.encounter.BattleEvent;
import com.quartz.x.player.Player;

public class BattleEventMenu extends Menu
{
	BufferedImage eventBox;
	int eventBoxWidth, eventBoxHeight;
	BattleEvent event;
	
	public BattleEventMenu(Player player) 
	{	
		super(player);
		try
		{
			background = ImageIO.read(new File("res/textures/sea.png"));
			width = background.getWidth();
			height = background.getHeight();

			eventBox = ImageIO.read(new File("res/textures/parchmentEvent.png"));
			eventBoxWidth = eventBox.getWidth();
			eventBoxHeight = eventBox.getHeight();
		} catch(IOException e) {
			e.printStackTrace();
		}
		
		event = new BattleEvent(Global.RANDOM_RANGE(0, Global.NORMAL_BATTLE_EVENTS.length - 1));

	}
	
	public BattleEventMenu(Player player, BattleEvent newEvent) 
	{	
		super(player);
		try
		{
			background = ImageIO.read(new File("res/textures/sea.png"));
			width = background.getWidth();
			height = background.getHeight();

			eventBox = ImageIO.read(new File("res/textures/parchmentEvent.png"));
			eventBoxWidth = eventBox.getWidth();
			eventBoxHeight = eventBox.getHeight();
		} catch(IOException e) {
			e.printStackTrace();
		}

		event = newEvent;

	}
	
	public BattleEventMenu(Player player, String battleText) 
	{	
		super(player);
		try
		{
			background = ImageIO.read(new File("res/textures/sea.png"));
			width = background.getWidth();
			height = background.getHeight();

			eventBox = ImageIO.read(new File("res/textures/parchmentEvent.png"));
			eventBoxWidth = eventBox.getWidth();
			eventBoxHeight = eventBox.getHeight();
		} catch(IOException e) {
			e.printStackTrace();
		}

		event = new BattleEvent(battleText);

	}


	public void update(Point pointPressed, Point pointReleased) {
		event.eventButton.highlight(false);
		
		if (clicked)
		{
			if (event.eventButton.contains(pointPressed))
			{
				event.eventButton.highlight(true);
			}
		}

		if (released)
		{
			if (event.eventButton.contains(pointReleased))
			{
				event.eventButton.highlight(false);
				Sound.click();
				event.update();
			}
			
			released = false;
		}
	}

	public void render(Graphics g) {
		g.drawImage(background, STATS_WIDTH, 0, width, height, null);
		g.drawImage(eventBox, (STATS_WIDTH + ((width - eventBoxWidth) / 2)),
				(height - eventBoxHeight) / 2, eventBoxWidth, eventBoxHeight, null);
		
		event.render(g);
	}

}
