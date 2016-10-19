package com.quartz.x.menu;

import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import com.quartz.x.State;
import com.quartz.x.State.STATE;
import com.quartz.x.Sound.Sound;
import com.quartz.x.button.MainButton;
import com.quartz.x.label.MenuLabel;
import com.quartz.x.player.Player;

public class ExitMenu extends MainMenu 
{
	private BufferedImage subMenu;
	private int subMenuWidth;
	private int subMenuHeight;
	private int xMargin;
	private int yMargin;
	
	private MainButton yes;
	private MainButton no;
	
	private MenuLabel titleExit;
	
	public ExitMenu(Player player)
	{
		super(player);
		try
		{
			subMenu = ImageIO.read(new File("res/textures/exit.png"));
			subMenuWidth = subMenu.getWidth();
			subMenuHeight = subMenu.getHeight();
			xMargin = (width - subMenuWidth) / 2;
			yMargin = (height - subMenuHeight) / 2;
			
			yes = new MainButton(new Rectangle(555, 310, 170, 74), "Yes");
			no = new MainButton(new Rectangle(555, 410, 170, 74), "No");
			
			titleExit = new MenuLabel("Exit?", xMargin + 160, 230);
		}
		catch(IOException e)
		{
			e.printStackTrace();
		}
	}
	
	@Override
	public void update(Point pointPressed, Point pointReleased)
	{
		if (clicked)
		{
			if (yes.contains(pointPressed))
			{
				yes.highlight(true);
			}
			else if (no.contains(pointPressed))
			{
				no.highlight(true);
			}
		}
		
		if (released)
		{
			if (yes.contains(pointPressed, pointReleased))
			{
				System.exit(1);
			}
			else if (no.contains(pointPressed, pointReleased))
			{
				Sound.click();
				no.highlight(false);
				State.setState(STATE.MENU);
			}
			
			yes.highlight(false);
			no.highlight(false);
			
			released = false;
		}
	}
	
	@Override
	public void render(Graphics g)
	{
		super.render(g);
		g.drawImage(subMenu, xMargin, yMargin, subMenuWidth, subMenuHeight, null);
		yes.render(g);
		no.render(g);
		titleExit.render(g);
	}
}
