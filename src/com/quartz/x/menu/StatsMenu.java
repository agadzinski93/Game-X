package com.quartz.x.menu;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import com.quartz.x.Global;
import com.quartz.x.State;
import com.quartz.x.Sound.Sound;
import com.quartz.x.State.STATE;
import com.quartz.x.button.MainButton;
import com.quartz.x.healthbar.HealthBar;
import com.quartz.x.label.StatsLabel;
import com.quartz.x.label.WeaponStatsLabel;
import com.quartz.x.player.Player;

public class StatsMenu extends Menu
{
	private StatsLabel hull;
	private StatsLabel gold;
	private StatsLabel ammunition;
	private StatsLabel rations,
						accuracy,
						evasion;
	
	private WeaponStatsLabel[] weaponLabels = new WeaponStatsLabel[player.weapons.length];
	
	private MainButton map;
	private MainButton customize;
	private MainButton options;
	
	private HealthBar healthBar;
	private final int HEALTHBAR_XLOC = 10;
	private final int HEALTHBAR_YLOC = 45;
	
	private boolean lockedState;
	
	public StatsMenu(Player player)
	{
		super(player);
		
		lockedState = false;
		
		try
		{
			background = ImageIO.read(new File("res/textures/statsBar.png"));
			height = background.getHeight();
		}
		catch(IOException e)
		{
			e.printStackTrace();
		}
		
		hull = 	new StatsLabel("ERROR", 40, 35);
		hull.setColor(Color.BLUE);
		gold = 	new StatsLabel("ERROR", 40, 130);
		gold.setColor(Color.BLUE);
		ammunition = new StatsLabel("ERROR", 40, 155);
		ammunition.setColor(Color.BLUE);
		rations = new StatsLabel("ERROR", 40, 180);
		rations.setColor(Color.BLUE);
		accuracy = new StatsLabel("ERROR", 40, 205);
		accuracy.setColor(Color.BLUE);
		evasion = new StatsLabel("ERROR", 40, 230);
		evasion.setColor(Color.BLUE);
		
		for(int i = 0; i < player.weapons.length; i++)
		{
			weaponLabels[i] = new WeaponStatsLabel(player.weapons[i], 40, 260 + (i * 50));
		}
		
		
		map = new MainButton(new Rectangle(70, 470, 170, 74), "Map");
		customize = new MainButton(new Rectangle(70, 555, 170, 74), "Customize");
		options = new MainButton(new Rectangle(70, 640, 170, 74), "Menu");
		
		healthBar = new HealthBar(HEALTHBAR_XLOC, HEALTHBAR_YLOC, Global.MAX_HULL);
	}
	
	@Override
	public void update(Point pointPressed, Point pointReleased) {
		if (player.getHull() <= 0)
			hull.setColor(Color.RED);
		else
			hull.setColor(Color.BLUE);
		hull.update("Hull: " + player.getHull() + "/" + Global.MAX_HULL);
		
		if (player.getGold() <= 0)
			gold.setColor(Color.RED);
		else
			gold.setColor(Color.BLUE);
		gold.update("Gold : " + player.getGold());
		
		if (player.getAmmunition() <= 0)
			ammunition.setColor(Color.RED);
		else
			ammunition.setColor(Color.BLUE);
		ammunition.update("Ammunition : " + player.getAmmunition());
		
		if (player.getRations() <= 0)
			rations.setColor(Color.RED);
		else
			rations.setColor(Color.BLUE);
		rations.update("Rations : " + player.getRations());
		
		accuracy.update("Accuracy : " + player.getAccuracy());
		evasion.update("Evasion : " + player.getEvasion());
		
		healthBar.update(player.getHull());
		
		for(int i = 0; i < player.weapons.length; i++)
		{
			weaponLabels[i] = new WeaponStatsLabel(player.weapons[i], 40, 260 + (i * 50));
		}

		if (!(State.getState() == STATE.SHIP))
			lockedState = true;
		else
			lockedState = false;

		if (lockedState)
		{
			map.setDisabled(true);
			customize.setDisabled(true);
			options.setDisabled(true);
		}
		else
		{
			map.setDisabled(false);
			customize.setDisabled(false);
			options.setDisabled(false);
		}

		if (clicked)
		{
			if (!map.isDisabled() && map.contains(pointPressed))
			{
				map.highlight(true);
			}
			else if (!customize.isDisabled() && customize.contains(pointPressed))
			{
				customize.highlight(true);
			}
			else if (!options.isDisabled() && options.contains(pointPressed))
			{
				options.highlight(true);
			}
		}
		
		if (released)
		{
			if (!map.isDisabled() && map.contains(pointPressed, pointReleased))
			{
				map.highlight(false);
				Sound.click();
				State.setState(STATE.MAP);
			}
			else if (!customize.isDisabled() && customize.contains(pointPressed, pointReleased))
			{
				customize.highlight(false);
				Sound.click();
				State.setState(STATE.CUSTOMIZE);
			}
			else if (!options.isDisabled() && options.contains(pointPressed, pointReleased))
			{
				options.highlight(false);
				Sound.click();
				State.setState(STATE.OPTIONS_GAME);
			}
			
			map.highlight(false);
			customize.highlight(false);
			options.highlight(false);
			
			released = false;
		}
	}
	
	@Override
	public void render(Graphics g)
	{
		g.drawImage(background, 0, 0, STATS_WIDTH, height, null);
		
		healthBar.render(g);
		
		hull.render(g);
		gold.render(g);
		ammunition.render(g);
		rations.render(g);
		evasion.render(g);
		accuracy.render(g);
		
		
		if(State.getState() != STATE.BATTLE)
		{
			for(int i = 0; i < weaponLabels.length; i++)
			{
				weaponLabels[i].render(g);					
			}
		}
		
		map.render(g);
		customize.render(g);
		options.render(g);
	}
}
