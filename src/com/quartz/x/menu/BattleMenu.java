package com.quartz.x.menu;

import java.awt.Font;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import com.quartz.x.Global;
import com.quartz.x.State;
import com.quartz.x.State.STATE;
import com.quartz.x.Sound.Sound;
import com.quartz.x.button.WeaponButton;
import com.quartz.x.combat.Shot;
import com.quartz.x.healthbar.HealthBar;
import com.quartz.x.label.CombatLabel;
import com.quartz.x.label.EventLabel;
import com.quartz.x.player.Enemy;
import com.quartz.x.player.Player;

public class BattleMenu extends Menu{
	private HealthBar enemyHealthBar;
	private Enemy enemy;
	
	private BufferedImage playerShip, enemyShip;
	
	private int playerShipX, playerShipY;
	private int playerShipWidth, playerShipHeight;
	
	private int enemyShipX, enemyShipY;
	private int enemyShipWidth, enemyShipHeight;
	
	private WeaponButton weaponButtons[];
	private EventLabel chancesToHit[];
	
	public BattleMenu(Player player, Enemy enemy) {
		super(player);
		
		this.enemy = enemy;
		
		CombatLabel.combatLabels = new CombatLabel[30];
		Shot.shots = new Shot[40];
		
		playerShipX = 400;
		playerShipY = 75;
		
		enemyShipX = 900;
		enemyShipY = 75;
		
		weaponButtons = new WeaponButton[4];
		for (int i = 0; i < weaponButtons.length; ++i) {
			weaponButtons[i] = new WeaponButton(
					new Rectangle(40, 240 + (i * 50), 250, 20),
					player.weapons[i]);
			weaponButtons[i].setFont(new Font("Serif", Font.BOLD, 18));
		}
		chancesToHit = new EventLabel[4];
		for (int i = 0; i < chancesToHit.length; i++ )
		{
			int chance = (player.getAccuracy() + player.weapons[i].getAccuracy()) - enemy.evasion;
			if(chance <= 100)
				chancesToHit[i] = new EventLabel(45, 280 + (i*50), chance + "% chance to hit");
			else

				chancesToHit[i] = new EventLabel(45, 280 + (i*50), "100% chance to hit");
			
			chancesToHit[i].setFont(new Font("Serif", Font.BOLD, 14));
		}
		
		try
		{
			background = ImageIO.read(new File("res/textures/sea.png"));
			width = background.getWidth();
			height = background.getHeight();
			
			playerShip = ImageIO.read(new File("res/textures/shipPlayer.png"));
			playerShipWidth = playerShip.getWidth();
			playerShipHeight = playerShip.getHeight();
			
			enemyShip = ImageIO.read(new File("res/textures/shipEnemy.png"));
			enemyShipWidth = enemyShip.getWidth();
			enemyShipHeight = enemyShip.getHeight();
			
			playingTheme = false;
			Sound.themeStop();
		}
		catch(IOException e)
		{
			e.printStackTrace();
		}
		
		enemyHealthBar = new HealthBar(800, 15, enemy.getMaxHealth());
		
		if (Global.musicType != "battle" && !mute)
		{
			Sound.battleStart();
			Global.musicType = "battle";
			playingTheme = true;
		}
		
	}
	
	// Update gets called 60 times a second, use this!!!
	public void update(Point pointPressed, Point pointReleased) {
		enemyHealthBar.update(enemy.getCurrentHealth());
		if (enemy.dead()) {
			State.setState(STATE.REWARD);
		}
		//**************FLOATING TEXT UPDATE************************
		for (int labelsIter = 0; labelsIter < CombatLabel.combatLabels.length; ++labelsIter) {
			if (CombatLabel.combatLabels[labelsIter] instanceof CombatLabel &&
					!CombatLabel.combatLabels[labelsIter].empty){
				CombatLabel.combatLabels[labelsIter].update();
			}
		}
		
		//*************SHOT ANIMATION UPDATE************************
		for (int shotsIter = 0; shotsIter < Shot.shots.length; ++shotsIter) {
			if (Shot.shots[shotsIter] instanceof Shot &&
					!Shot.shots[shotsIter].getEmpty()){
				Shot.shots[shotsIter].update();
			}
		}
		
		for (int i = 0; i < weaponButtons.length; ++i) {
			weaponButtons[i].update();
		}
		//*****************ENEMY UPDATE*****************************
		enemy.update(player);
		
		//**************PLAYER UPDATE*******************************
		if (clicked) {
		}

		if (released) {
			for (int i = 0; i < weaponButtons.length; ++i) {
				if (weaponButtons[i].contains(pointPressed, pointReleased) && weaponButtons[i].loaded) {
					switch (player.weapons[i].getWeaponName()) {
					case "Catapult":
						Sound.catapultLaunch();
						shoot(i);						
						break;
					case "Cannon":
						if(player.getAmmunition() > 0)
						{
							Sound.cannonExplosion();
							shoot(i);
							player.modAmmunition(-1);
						}
						break;
					}
				}
			}
		}
	}
	
	private void shoot(int index)
	{		
		//****MISS/HIT*****
		int shotChance = (player.getAccuracy() + player.weapons[index].getAccuracy()) - enemy.evasion;
		int shotRoll = Global.RANDOM_RANGE(0, 100);
		boolean misses;
		if (shotRoll <= shotChance)
			misses = false;
		else
			misses = true;
		
		//**********CREATE SHOT*************
		Integer damage = 0 - player.weapons[index].getDamage();
		Shot.shots[Shot.nextIndex] =
				new Shot(true, misses, damage, player, enemy);
		weaponButtons[index].fired();
	}

	@Override
	public void render(Graphics g) {
		g.drawImage(background, STATS_WIDTH, 0, width, height, null);
		enemyHealthBar.render(g);
		//*****************RENDER SHOTS**************************************
		for (int i = 0; i < Shot.shots.length; ++i) {
			if (Shot.shots[i] instanceof Shot &&
					!Shot.shots[i].getEmpty()){
				Shot.shots[i].render(g);
			}
		}
		
		//*******************RENDER SHIPS**************************************
		g.drawImage(playerShip, playerShipX, playerShipY, playerShipWidth, playerShipHeight, null);
		g.drawImage(enemyShip, enemyShipX, enemyShipY, enemyShipWidth, enemyShipHeight, null);
		
		//*****************RENDER FLOATING TEXT******************************
		for (int i = 0; i < CombatLabel.combatLabels.length; ++i) {
			if (CombatLabel.combatLabels[i] instanceof CombatLabel &&
					!CombatLabel.combatLabels[i].empty){
				CombatLabel.combatLabels[i].render(g);
			}
		}
		
		//*********************RENDER WEAPONBUTTONS****************************
		for (int i = 0; i < weaponButtons.length; ++i) {
			weaponButtons[i].render(g);
		}
		
		//*********************RENDER CHANCE TO HIT LABELS****************************
		for (int i = 0; i < chancesToHit.length; ++i) {
			if(player.weapons[i].getWeaponName() != "EMPTY")
				chancesToHit[i].render(g);
		}
	}
}
