package com.quartz.x.menu;

import java.awt.Graphics;
import java.awt.Point;
import java.awt.image.BufferedImage;

import com.quartz.x.player.Player;

public abstract class Menu
{
	protected Player player;
	protected BufferedImage background;
	protected int width;
	protected int height;
	protected final int STATS_WIDTH = 321;
	
	protected static boolean playingTheme = false;
	protected static boolean mute = false;
	protected static boolean clicked = false;
	protected static boolean released = false;
	
	public Menu(Player player)
	{
		this.player = player;
	}
	
	public abstract void update(Point pointPressed, Point pointReleased);
	
	public abstract void render(Graphics g);
	
	public static boolean isMute()
	{
		return mute;
	}
	
	public static void setMute(boolean value)
	{
		mute = value;
		playingTheme = !value;
	}
	
	public static void setClicked(boolean value)
	{
		clicked = value;
	}
	
	public static void setReleased(boolean value)
	{
		released = value;
	}
}
