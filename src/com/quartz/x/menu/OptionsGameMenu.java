package com.quartz.x.menu;

import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import com.quartz.x.State;
import com.quartz.x.Sound.Sound;
import com.quartz.x.button.MainButton;
import com.quartz.x.label.MenuLabel;
import com.quartz.x.player.Player;
import com.quartz.x.State.STATE;

public class OptionsGameMenu extends ShipMenu
{
	private BufferedImage subMenu;
	private int subMenuWidth;
	private int subMenuHeight;
	private int xMargin;
	private int yMargin;
	
	private MainButton options;
	private MainButton exit;
	
	private MenuLabel titleMenu;
	
	public OptionsGameMenu(Player player)
	{
		super(player);
		try
		{
			subMenu = ImageIO.read(new File("res/textures/exit.png"));
			subMenuWidth = subMenu.getWidth();
			subMenuHeight = subMenu.getHeight();
			xMargin = STATS_WIDTH + (width - subMenuWidth) / 2;
			yMargin = (height - subMenuHeight) / 2;
			
			titleMenu = new MenuLabel("Menu", xMargin + 150, 225);
		}
		catch(IOException e)
		{
			e.printStackTrace();
		}
		
		options = new MainButton(new Rectangle(710, 300, 170, 74), "Options");
		exit = new MainButton(new Rectangle(710, 400, 170, 74), "Exit");
	}
	
	@Override
	public void update(Point pointPressed, Point pointReleased)
	{
		if (clicked)
		{
			if (options.contains(pointPressed))
			{
				options.highlight(true);
			}
			else if (exit.contains(pointPressed))
			{
				exit.highlight(true);
			}
		}
		
		if (released)
		{
			if (pointReleased.getX() >= 961 && pointReleased.getX() <= 981 && pointReleased.getY() >= 200 && pointReleased.getY() <= 220)
			{
				Sound.click();
				released = false;
				State.setState(STATE.SHIP);
			}
			else if (options.contains(pointPressed, pointReleased))
			{
				options.highlight(false);
				Sound.click();
				released = false;
				State.setState(STATE.MUSIC_GAME);
			}
			else if (exit.contains(pointPressed, pointReleased))
			{
				exit.highlight(false);
				Sound.click();
				released = false;
				State.setState(STATE.MENU);
			}
			
			options.highlight(false);
			exit.highlight(false);
		}
	}
	
	@Override
	public void render(Graphics g)
	{
		g.drawImage(background, STATS_WIDTH, 0, width, height, null);
		g.drawImage(subMenu, xMargin, yMargin, subMenuWidth, subMenuHeight, null);
		
		options.render(g);
		exit.render(g);
		titleMenu.render(g);
	}
}
