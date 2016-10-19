package com.quartz.x.encounter;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Rectangle;

import com.quartz.x.Global;
import com.quartz.x.State;
import com.quartz.x.State.STATE;
import com.quartz.x.button.EventButton;
import com.quartz.x.combat.Weapon;
import com.quartz.x.label.EventLabel;
import com.quartz.x.label.WeaponStatsLabel;
import com.quartz.x.player.Player;

public class Port extends Encounter
{
	private static int	rationsStock,
				ammunitionStock;
	
	public EventButton	buyRations,
						buyAmmunition,
						buyAccuracy,
						buyEvasion,
						repairHull,
						repairHullFull;

	public WeaponStatsLabel[] weaponUpgrades;
	public EventButton[][] upgradeButtons;
	public EventLabel[][] upgradePrices;
	
	private Player player;
	//Weapons[] weaponsStock;
	
	
	public Port(Player player)
	{
		this.player = player;
		this.eventText = "You are at a port, where you can buy or sell goods.";
		rationsStock = Global.RANDOM_RANGE(5, 15);
		ammunitionStock = Global.RANDOM_RANGE(5, 15);	
		
		int buttonStartingY = Global.PARCHMENT_START_Y + 30;
		
		repairHull = 		new EventButton(new Rectangle(Global.PARCHMENT_START_X + 25, buttonStartingY, 400, 20), "Repair 1 Hull Durability: " + Global.HULL_COST + " gold.");
		repairHullFull = 	new EventButton(new Rectangle(Global.PARCHMENT_START_X + 25, buttonStartingY + 25, 400, 20), "Repair All Hull Durability: " + ((Global.MAX_HULL - player.getHull()) *  Global.HULL_COST) + " gold. ");
		buyRations = 		new EventButton(new Rectangle(Global.PARCHMENT_START_X + 25, buttonStartingY + 50, 400, 20), "Buy Rations: " + Global.RATIONS_COST + " gold. " + rationsStock + " in stock.");
		buyAmmunition = 	new EventButton(new Rectangle(Global.PARCHMENT_START_X + 25, buttonStartingY + 75, 400, 20), "Buy Ammunition: " + Global.AMMUNITION_COST + " gold. " + ammunitionStock + " in stock.");
		buyAccuracy = 		new EventButton(new Rectangle(Global.PARCHMENT_START_X + 25, buttonStartingY + 100, 400, 20), "Buy Accuracy: " + Global.ACCURACY_COST + " gold.");
		buyEvasion = 		new EventButton(new Rectangle(Global.PARCHMENT_START_X + 25, buttonStartingY + 125, 400, 20), "Buy Evasion: " + Global.EVASION_COST + " gold.");
		

		weaponUpgrades = new WeaponStatsLabel[player.weapons.length];
		upgradeButtons = new EventButton[player.weapons.length][2];
		upgradePrices = new EventLabel[player.weapons.length][2];
					
		for(int i = 0; i < weaponUpgrades.length; i++)
		{
			 generateWeaponOptions(i);
		}
		
		eventButton = 		new EventButton(new Rectangle(1000, buttonStartingY + 450, 400, 20), "1. Exit.");
	}
	
	public void upgradeWeapon(int weaponIndex)
	{
		player.weapons[weaponIndex].upgradeWeapon();
		generateWeaponOptions(weaponIndex);
	}
	
	public void buyCannon(int weaponIndex)
	{
		player.weapons[weaponIndex] = new Weapon("Cannon");
		generateWeaponOptions(weaponIndex);
	}
	
	public void buyCatapult(int weaponIndex)
	{
		player.weapons[weaponIndex] = new Weapon("Catapult");
		generateWeaponOptions(weaponIndex);
	}
	
	public void buyRations()
	{
		if(rationsStock > 1)
		{
			rationsStock--;
			buyRations.updateText("Buy Rations: " + Global.RATIONS_COST + " gold. " + rationsStock + " in stock.");
			
			player.modRations(1);
			player.modGold(-1 * Global.RATIONS_COST);
		}
		else
		{
			rationsStock = 0;
			buyRations.updateText("Rations out of Stock.");
			buyRations.setColor(Color.RED);
		}
	}	
	
	public void buyAmmunition()
	{
		if(ammunitionStock > 1)
		{
			ammunitionStock--;
			buyAmmunition.updateText("Buy Ammunition: " + Global.AMMUNITION_COST + " gold. " + ammunitionStock + " in stock.");
			
			player.modAmmunition(1);
			player.modGold(-1 * Global.AMMUNITION_COST);
		}
		else
		{
			ammunitionStock = 0;
			buyAmmunition.updateText("Ammunition out of Stock.");
			buyAmmunition.setColor(Color.RED);
		}
	}
	
	public void repairHull()
	{
		player.modHull(1);
		player.modGold(-1 * Global.HULL_COST);
		
		repairHullFull.updateText("Repair All Hull Durability: " + ((Global.MAX_HULL - player.getHull()) *  Global.HULL_COST) + " gold. ");
	}
	
	public void repairHullFull()
	{
		int missingHull = Global.MAX_HULL - player.getHull();
		
		player.modHull(missingHull);
		player.modGold(-1 * missingHull * Global.HULL_COST);

		repairHull.updateText("Your Hull is completely repaired.");
		repairHullFull.updateText("");
	}
	
	public void buyAccuracy()
	{
		player.modAccuracy(1);
		player.modGold(-1 * Global.ACCURACY_COST);
	}

	public void buyEvasion()
	{
		player.modEvasion(1);
		player.modGold(-1 * Global.EVASION_COST);
		
	}
	
	public static int getRationsStock()
	{
		return rationsStock;
	}
	
	public static int getAmmunitionStock()
	{
		return ammunitionStock;
	}
	
	public void update() {
		State.setState(STATE.SHIP);
	}
	
	public void generateWeaponOptions(int index)
	{
		int x,
		y;
	
		if(index % 2 == 0)
			x = Global.PARCHMENT_START_X + 50;
		else
			x = Global.PARCHMENT_START_X + 350;
	
		if(index / 2 < 1)
			y = Global.PARCHMENT_START_Y + 230;
		else
			y = Global.PARCHMENT_START_Y + 330;
		
		if(player.weapons[index].getLevel() < 10)
		{
			weaponUpgrades[index] = new WeaponStatsLabel(new Weapon(player.weapons[index].getWeaponName(), player.weapons[index].getLevel() + 1), x, y);
			
			
			if(!player.weapons[index].isEmpty() && player.weapons[index].getWeaponName() != "Cannon")
			{
				upgradeButtons[index][0] = new EventButton(new Rectangle(x, y + 35, 200, 14), "Upgrade?");
				upgradePrices[index][0] = new EventLabel(x + 150, y + 50, player.weapons[index].getCost() + " gold.");
				
				upgradeButtons[index][1] = new EventButton(new Rectangle(x, y + 55, 200, 14), "Purchase Cannon I?");
				upgradePrices[index][1] = new EventLabel(x + 150, y + 70, Global.NEW_CANNON_PRICE + " gold.");
			}
			else if (!player.weapons[index].isEmpty() )
			{
				upgradeButtons[index][0] = new EventButton(new Rectangle(x, y + 35, 200, 14), "Upgrade?");
				upgradePrices[index][0] = new EventLabel(x + 150, y + 50, player.weapons[index].getCost() + " gold.");
				
				upgradeButtons[index][1] = new EventButton(new Rectangle(x, y + 55, 200, 14), "Purchase Catapult I?");
				upgradePrices[index][1] = new EventLabel(x + 150, y + 70, Global.NEW_CATAPULT_PRICE + " gold.");
			}
			else
			{
				upgradeButtons[index][0] = new EventButton(new Rectangle(x, y + 35, 200, 14), "Purchase Catapult I?");
				upgradePrices[index][0] = new EventLabel(x + 150, y + 50, Global.NEW_CATAPULT_PRICE + " gold.");
				
				upgradeButtons[index][1] = new EventButton(new Rectangle(x, y + 55, 200, 14), "Purchase Cannon I?");
				upgradePrices[index][1] = new EventLabel(x + 150, y + 70, Global.NEW_CANNON_PRICE + " gold.");
			}
		}
		else
		{
			weaponUpgrades[index] = new WeaponStatsLabel(new Weapon(player.weapons[index].getWeaponName(), player.weapons[index].getLevel()), x, y);
			
			if(!player.weapons[index].isEmpty() && player.weapons[index].getWeaponName() != "Cannon")
			{
				upgradeButtons[index][0] = new EventButton(new Rectangle(x, y + 35, 200, 14), "This weapon is at the maximum level.");
				upgradePrices[index][0] = new EventLabel(x + 150, y + 50, "");
				
				upgradeButtons[index][1] = new EventButton(new Rectangle(x, y + 55, 200, 14), "Purchase Cannon I?");
				upgradePrices[index][1] = new EventLabel(x + 150, y + 70, Global.NEW_CANNON_PRICE + " gold.");
			}
			else if (!player.weapons[index].isEmpty() )
			{
				upgradeButtons[index][0] = new EventButton(new Rectangle(x, y + 35, 200, 14), "This weapon is at the maximum level.");
				upgradePrices[index][0] = new EventLabel(x + 150, y + 50, "");
				
				upgradeButtons[index][1] = new EventButton(new Rectangle(x, y + 55, 200, 14), "Purchase Catapult I?");
				upgradePrices[index][1] = new EventLabel(x + 150, y + 70, Global.NEW_CATAPULT_PRICE + " gold.");
			}
			else
			{
				upgradeButtons[index][0] = new EventButton(new Rectangle(x, y + 35, 200, 14), "Purchase Catapult I?");
				upgradePrices[index][0] = new EventLabel(x + 150, y + 50, Global.NEW_CATAPULT_PRICE + " gold.");				
				
				upgradeButtons[index][1] = new EventButton(new Rectangle(x, y + 55, 200, 14), "Purchase Cannon I?");
				upgradePrices[index][1] = new EventLabel(x + 150, y + 70, Global.NEW_CANNON_PRICE + " gold.");
			}
		}
		
		for(int j = 0; j < upgradeButtons[index].length; j++)
		{
			upgradeButtons[index][j].setFont(new Font("Serif", Font.BOLD, 14));
			upgradePrices[index][j].setFont(new Font("Serif", Font.BOLD, 14));
			
			switch(upgradeButtons[index][j].getText())
			{
			case "Upgrade?":
				if(player.weapons[index].getCost() <= player.getGold())
				{
					upgradeButtons[index][j].setColor(Color.RED);
					upgradePrices[index][j].setColor(Color.RED);
				}
				else
				{
					upgradeButtons[index][j].setColor(Color.GRAY);
					upgradePrices[index][j].setColor(Color.GRAY);
				}
				break;
			case "Purchase Cannon I?":
				if(Global.NEW_CANNON_PRICE <= player.getGold())
				{
					upgradeButtons[index][j].setColor(Color.RED);
					upgradePrices[index][j].setColor(Color.RED);
				}
				else
				{
					upgradeButtons[index][j].setColor(Color.GRAY);
					upgradePrices[index][j].setColor(Color.GRAY);
				}
				break;
			case "Purchase Catapult I?":
				if(Global.NEW_CATAPULT_PRICE <= player.getGold())
				{
					upgradeButtons[index][j].setColor(Color.RED);
					upgradePrices[index][j].setColor(Color.RED);
				}
				else
				{
					upgradeButtons[index][j].setColor(Color.GRAY);
					upgradePrices[index][j].setColor(Color.GRAY);
				}
				break;
			default:
				break;
			}
		}
	}
	
	public void render(Graphics g) {
		g.setColor(Color.BLACK);
		g.setFont(new Font("Serif", Font.BOLD, 18));


		if(eventText.length() > Global.CHARACTER_LIMIT)
		{
			Global.drawStringWrapped(g, eventText, Global.PARCHMENT_START_Y);			
		}
		else
		{
			g.drawString(eventText, Global.PARCHMENT_START_X, Global.PARCHMENT_START_Y);
		}
		
		//REPAIR 1
		if(player.getGold() > Global.HULL_COST && !player.isHullFull())
		{
			repairHull.render(g);
		}
		else
		{
			repairHull.setColor(Color.GRAY);
			repairHull.render(g);
		}
		
		
		//REPAIR ALL
		if(player.getGold() > Global.RATIONS_COST * (Global.MAX_HULL - player.getHull()) && !player.isHullFull())
		{
			repairHullFull.render(g);
		}
		else
		{
			repairHullFull.setColor(Color.GRAY);
			repairHullFull.render(g);
		}
				
		//BUY RATIONS
		if(player.getGold() > Global.RATIONS_COST)
		{
			buyRations.render(g);
		}
		else
		{
			buyRations.setColor(Color.GRAY);
			buyRations.render(g);
		}
		
		//BUY AMMUNITION
		if(player.getGold() > Global.AMMUNITION_COST)
		{
			buyAmmunition.render(g);
		}
		else
		{
			buyAmmunition.setColor(Color.GRAY);
			buyAmmunition.render(g);
		}
		
		//BUY ACCURACY
		if(player.getGold() > Global.ACCURACY_COST)
		{
			buyAccuracy.render(g);
		}
		else
		{
			buyAccuracy.setColor(Color.GRAY);
			buyAccuracy.render(g);
		}
		
		//BUY EVASION
		if(player.getGold() > Global.EVASION_COST)
		{
			buyEvasion.render(g);
		}
		else
		{
			buyEvasion.setColor(Color.GRAY);
			buyEvasion.render(g);
		}
		eventButton.setColor(Color.black);
		eventButton.render(g);
		
		for(int i = 0; i < weaponUpgrades.length; i++)
			weaponUpgrades[i].render(g);
		
		for(int k = 0; k < upgradeButtons.length; k++)
		{
			for(int j = 0; j < upgradeButtons[k].length; j++)
			{
				upgradeButtons[k][j].render(g);
				upgradePrices[k][j].render(g);
			}
		}
		
	}
		
}