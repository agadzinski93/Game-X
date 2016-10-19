package com.quartz.x.menu;

import java.awt.Graphics;
import java.awt.Point;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import com.quartz.x.Global;
import com.quartz.x.Sound.Sound;
import com.quartz.x.encounter.NeutralEvent;
import com.quartz.x.player.Player;

public class NeutralEventMenu extends Menu
{
	BufferedImage eventBox;
	int eventBoxWidth, eventBoxHeight;
	NeutralEvent event;
	
	public NeutralEventMenu(Player player) 
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
		
		event = new NeutralEvent(Global.RANDOM_RANGE(0, Global.ALL_NEUTRAL_EVENTS.length - 1));
	}

	public void update(Point pointPressed, Point pointReleased) {
		
		
		for(int i = 0; i < event.optionButtons.length; i++)
		{
			if (clicked)
			{
				if (event.optionButtons[i].contains(pointPressed))
				{
					//event.eventButton.highlight(true);
				}
			}
			
			if (released)
			{
				if (event.optionButtons[i].contains(pointReleased))
				{
					//event.eventButton.highlight(false);
					Sound.click();
					event.update(i);
				}
			}
		}
	}
	

	public void render(Graphics g) {
		g.drawImage(background, STATS_WIDTH, 0, width, height, null);
		g.drawImage(eventBox, (STATS_WIDTH + ((width - eventBoxWidth) / 2)),
				(height - eventBoxHeight) / 2, eventBoxWidth, eventBoxHeight, null);
				
		event.render(g);
	}

}
