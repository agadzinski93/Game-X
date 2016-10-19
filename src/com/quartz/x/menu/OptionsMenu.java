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

public class OptionsMenu extends MainMenu
{
	private BufferedImage subMenu;
	private int subMenuWidth;
	private int subMenuHeight;
	private int xMargin;
	private int yMargin;
	
	private MainButton up;
	private MainButton down;
	
	private MenuLabel titleOptions;
	private MenuLabel volumeText;
	private MenuLabel exitClose;
	
	private BufferedImage soundSwitch;
	private BufferedImage soundSwitchOn;
	private BufferedImage soundSwitchOff;
	private int soundSwitchWidth;
	private int soundSwitchHeight;
	
	public OptionsMenu(Player player)
	{
		super(player);
		try
		{
			subMenu = ImageIO.read(new File("res/textures/options.png"));
			soundSwitchOn = ImageIO.read(new File("res/textures/musicOn.png"));
			soundSwitchOff = ImageIO.read(new File("res/textures/musicOff.png"));
			
			up = new MainButton(new Rectangle(425, 440, 170, 74), "UP");
			down = new MainButton(new Rectangle(675, 440, 170, 74), "DOWN");
			
			titleOptions = new MenuLabel("MUSIC", 580, 230);
			volumeText = new MenuLabel("VOLUME: ", 540, 410);
			exitClose = new MenuLabel("X", 912, 190);
			
			if (!mute)
				soundSwitch = soundSwitchOn;
			else
				soundSwitch = soundSwitchOff;
			
			subMenuWidth = subMenu.getWidth();
			subMenuHeight = subMenu.getHeight();
			xMargin = (width - subMenuWidth) / 2;
			yMargin = (height - subMenuHeight) / 2;
			
			soundSwitchWidth = soundSwitch.getWidth();
			soundSwitchHeight = soundSwitch.getHeight();
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
			if (pointPressed.getX() >= 1205 && pointPressed.getX() <= 1225 && pointPressed.getY() >= 45 && pointPressed.getY() <= 75)
			{
			}
			else if (pointPressed.getX() >= 350 && pointPressed.getX() <= 420 && pointPressed.getY() >= 205 && pointPressed.getY() <= 245)
			{
			}
			else if (pointPressed.getX() >= 540 && pointPressed.getX() <= 635 && pointPressed.getY() >= 205 && pointPressed.getY() <= 245)
			{
			}
			else if (up.contains(pointPressed))
			{
				up.highlight(true);
			}
			else if (down.contains(pointPressed))
			{
				down.highlight(true);
			}
		}
		
		if (released)
		{
			if (pointReleased.getX() >= 912 && pointReleased.getX() <= 932 && pointReleased.getY() >= 170 && pointReleased.getY() <= 190)
			{
				State.setState(STATE.MENU);
				Sound.click();
			}
			else if (pointReleased.getX() >= 515 && pointReleased.getX() <= 585 && pointReleased.getY() >= 305 && pointReleased.getY() <= 345)
			{
				if (isMute())
				{
					Menu.setMute(false);
					soundSwitch = soundSwitchOn;
					Sound.themeStart();
				}
				Sound.click();
			}
			else if (pointReleased.getX() >= 705 && pointReleased.getX() <= 800 && pointReleased.getY() >= 305 && pointReleased.getY() <= 345)
			{
				if (!isMute())
				{
					Menu.setMute(true);
					soundSwitch = soundSwitchOff;
					Sound.themeStop();
				}
				Sound.click();
			}
			else if (up.contains(pointPressed, pointReleased))
			{
				Sound.volumeUp();
				up.highlight(false);
			}
			else if (down.contains(pointPressed, pointReleased))
			{
				Sound.volumeDown();
				down.highlight(false);
			}
			
			up.highlight(false);
			down.highlight(false);
			
			released = false;
		}
	}
	
	@Override
	public void render(Graphics g)
	{
		super.render(g);
		g.drawImage(subMenu, xMargin, yMargin, subMenuWidth, subMenuHeight, null);
		g.drawImage(soundSwitch, 450, 300, soundSwitchWidth, soundSwitchHeight, null);
		up.render(g);
		down.render(g);
		titleOptions.render(g);
		volumeText.render(g, Sound.getVolume());
		exitClose.render(g);
	}
}
