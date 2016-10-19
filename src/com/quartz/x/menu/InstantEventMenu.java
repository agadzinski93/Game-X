package com.quartz.x.menu;

import java.awt.Graphics;
import java.awt.Point;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import com.quartz.x.Global;
import com.quartz.x.Sound.Sound;
import com.quartz.x.encounter.InstantEvent;
import com.quartz.x.player.Player;

public class InstantEventMenu extends Menu
{
	BufferedImage eventBox;
	int eventBoxWidth, eventBoxHeight;
	InstantEvent event;
	
	public InstantEventMenu(Player player) 
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
		
		this.event = new InstantEvent(Global.RANDOM_RANGE(0, Global.NORMAL_INSTANT_EVENTS.length - 1));
		player.modResources(event.eventEffect);
	}
	
	public InstantEventMenu(Player player, InstantEvent event) 
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
		
		this.event = event;
		player.modResources(event.eventEffect);
	}
	
	public InstantEventMenu(String filler, Player player) 
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

		event = new InstantEvent("meh");
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
