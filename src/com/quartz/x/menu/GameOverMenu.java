package com.quartz.x.menu;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

import com.quartz.x.State;
import com.quartz.x.Sound.Sound;
import com.quartz.x.State.STATE;
import com.quartz.x.button.MainButton;
import com.quartz.x.label.MenuLabel;
import com.quartz.x.player.Player;

public class GameOverMenu extends Menu{

	private BufferedImage playerShip;

	private int playerShipX, playerShipY;
	private int playerShipWidth, playerShipHeight;

	private BufferedImage subMenu;
	private int subMenuWidth;
	private int subMenuHeight;
	private int xMargin;
	private int yMargin;

	private MenuLabel title;
	private MenuLabel results;
	
	private MainButton goToMainMenu;
	
	public GameOverMenu (Player player) {
		super(player);

		playerShipX = 400;
		playerShipY = 75;

		try
		{
			title = new MenuLabel("GAME OVER", 690, 230);
			results = new MenuLabel("ERROR", 600, 350);
			goToMainMenu = new MainButton(new Rectangle(710, 400, 170, 74), "Main Menu");
			
			background = ImageIO.read(new File("res/textures/sea.png"));
			width = background.getWidth();
			height = background.getHeight();
			
			subMenu = ImageIO.read(new File("res/textures/options.png"));
			subMenuWidth = subMenu.getWidth();
			subMenuHeight = subMenu.getHeight();
			xMargin = (width - subMenuWidth) / 2;
			yMargin = (height - subMenuHeight) / 2;
			
			playerShip = ImageIO.read(new File("res/textures/shipPlayer.png"));
			playerShipWidth = playerShip.getWidth();
			playerShipHeight = playerShip.getHeight();
			
			playingTheme = false;
			Sound.themeStop();
		}
		catch(IOException e)
		{
			e.printStackTrace();
		}
		
		results.update("You have been lost at sea...");
		results.setColor(Color.RED);
	}

	@Override
	public void update(Point pointPressed, Point pointReleased) {

		if (clicked)
		{
			if (goToMainMenu.contains(pointPressed))
			{
				goToMainMenu.highlight(true);
			}
		}
		
		if (released)
		{
			if (goToMainMenu.contains(pointPressed, pointReleased))
			{
				State.setState(STATE.RESET);
				goToMainMenu.highlight(false);
			}
			
			goToMainMenu.highlight(false);
			
			released = false;
		}
	}

	@Override
	public void render(Graphics g) {
		g.drawImage(background, STATS_WIDTH, 0, width, height, null);
		g.drawImage(playerShip, playerShipX, playerShipY, playerShipWidth, playerShipHeight, null);
		g.drawImage(subMenu, xMargin + STATS_WIDTH, yMargin, subMenuWidth, subMenuHeight, null);
		title.render(g);
		results.render(g);
		goToMainMenu.render(g);
	}

}