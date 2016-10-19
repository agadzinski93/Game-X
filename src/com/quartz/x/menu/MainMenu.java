package com.quartz.x.menu;

import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import com.quartz.x.Global;
import com.quartz.x.State;
import com.quartz.x.State.STATE;
import com.quartz.x.Sound.Sound;
import com.quartz.x.button.MainButton;
import com.quartz.x.player.Player;

public class MainMenu extends Menu
{
	private MainButton newGame;
	private MainButton continueGame;
	private MainButton options;
	private MainButton exit;
	
	public MainMenu(Player player)
	{
		super(player);
		try
		{
			background = ImageIO.read(new File("res/textures/titleScreen.png"));
			width = background.getWidth();
			height = background.getHeight();
			
			newGame = new MainButton(new Rectangle(325, 525, 170, 74), "New Game");
			continueGame = new MainButton(new Rectangle(725, 525, 170, 74), "Continue Game");
			options = new MainButton(new Rectangle(50, 600, 170, 74), "Options");
			exit = new MainButton(new Rectangle(1005, 600, 170, 74), "Exit");
			
			
			if(Global.musicType != "main" && playingTheme)
			{
				playingTheme = false;
				Sound.themeStop();
			}
			
			if (Global.musicType != "main" && !mute)
			{
				Sound.themeStart();
				playingTheme = true;
				Global.musicType = "main";
			}
			
		}
		catch(IOException e)
		{
			e.printStackTrace();
		}
	}
	
	@Override
	public void update(Point pointPressed, Point pointReleased)
	{
		if (!(player instanceof Player))
			continueGame.setDisabled(true);
		
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
			else if (newGame.contains(pointPressed))
			{
				newGame.highlight(true);
			}
			else if ((player instanceof Player) && continueGame.contains(pointPressed))
			{
				continueGame.highlight(true);
			}
		}

		if (released)
		{
			if (options.contains(pointPressed, pointReleased))
			{
				options.highlight(false);
				Sound.click();
				State.setState(STATE.OPTIONS);
			}
			else if (exit.contains(pointPressed, pointReleased))
			{
				exit.highlight(false);
				Sound.click();
				State.setState(STATE.EXIT);
			}
			else if (newGame.contains(pointPressed, pointReleased))
			{
				newGame.highlight(false);
				Sound.click();
				State.setState(STATE.NEW_GAME);
			}
			else if ((player instanceof Player) && continueGame.contains(pointPressed, pointReleased))
			{
				continueGame.highlight(false);
				Sound.click();
				State.setState(STATE.CONTINUE_GAME);
			}
			
			options.highlight(false);
			exit.highlight(false);
			newGame.highlight(false);
			continueGame.highlight(false);
			
			released = false;
		}
		
	}
	
	@Override
	public void render(Graphics g)
	{
		g.drawImage(background, 0, 0, width, height, null);
		
		newGame.render(g);
		continueGame.render(g);
		options.render(g);
		exit.render(g);
	}
}
