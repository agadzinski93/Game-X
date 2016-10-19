package com.quartz.x.menu;

import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import com.quartz.x.Global;
import com.quartz.x.State;
import com.quartz.x.Sound.Sound;
import com.quartz.x.State.STATE;
import com.quartz.x.button.MainButton;
import com.quartz.x.label.MenuLabel;
import com.quartz.x.player.Player;

public class RewardMenu extends Menu{

	private BufferedImage playerShip;

	private int playerShipX, playerShipY;
	private int playerShipWidth, playerShipHeight;

	private BufferedImage subMenu;
	private int subMenuWidth;
	private int subMenuHeight;
	private int xMargin;
	private int yMargin;

	private MenuLabel title;
	private MenuLabel results[];
	
	private String[] resourceGain = new String[] {"gold", "rations", "ammunition"};
	
	private MainButton ok;
	
	public RewardMenu(Player player) {
		super(player);

		playerShipX = 400;
		playerShipY = 75;

		try
		{
			title = new MenuLabel("You have prevailed!", 660, 230);
			ok = new MainButton(new Rectangle(710, 400, 170, 74), "Ok");
			
			results = new MenuLabel[resourceGain.length];
			for(int i = 0; i < results.length; i++)
			{
				results[i] = new MenuLabel("", 660, 305 + (40 * i));
			}
			
			
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
		
		
		
		int gainedGoldMedian = Global.BASE_GAINED_GOLD + (player.getMapCount()*20),
			gainedRationsMedian = Global.BASE_GAINED_RATIONS + (player.getMapCount()*1),
			gainedAmmunitionMedian = Global.BASE_GAINED_AMMUNITION + (player.getMapCount()*2),
			
			gainedGold = Global.RANDOM_RANGE(gainedGoldMedian - 20, gainedGoldMedian + 20),
			gainedRations = Global.RANDOM_RANGE(gainedRationsMedian - 1, gainedRationsMedian + 1),
			gainedAmmunition = Global.RANDOM_RANGE(gainedAmmunitionMedian - 2, gainedAmmunitionMedian + 2);
		
		int[] gains = new int[] {gainedGold, gainedRations, gainedAmmunition};
		
		for(int i = 0; i < results.length; i++)
		{
			results[i].update("You gain " + gains[i] + " " + resourceGain[i] + ".");
		}
		
		//gold, hull, rations, ammunition, evasion, accuracy;
		
		
		
		player.modResources(new int[] {gains[0], 0, gains[1], gains[2], 0, 0});
		
		
	}

	@Override
	public void update(Point pointPressed, Point pointReleased) {

		if (clicked)
		{
			if (ok.contains(pointPressed))
			{
				ok.highlight(true);
			}
		}
		
		if (released)
		{
			if (ok.contains(pointPressed, pointReleased))
			{
				State.setState(STATE.SHIP);
				ok.highlight(false);
			}
			
			ok.highlight(false);
			
			released = false;
		}
	}

	@Override
	public void render(Graphics g) {
		g.drawImage(background, STATS_WIDTH, 0, width, height, null);
		g.drawImage(playerShip, playerShipX, playerShipY, playerShipWidth, playerShipHeight, null);
		g.drawImage(subMenu, xMargin + STATS_WIDTH, yMargin, subMenuWidth, subMenuHeight, null);
		title.render(g);
		for(int i = 0; i < results.length; i++)
			results[i].render(g);
		ok.render(g);
	}

}
