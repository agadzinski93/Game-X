package com.quartz.x.menu;

import java.awt.Graphics;
import java.awt.Point;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import com.quartz.x.Global;
import com.quartz.x.State;
import com.quartz.x.State.STATE;
import com.quartz.x.Sound.Sound;
import com.quartz.x.encounter.Port;
import com.quartz.x.player.Player;


public class PortMenu extends Menu
{
	BufferedImage eventBox;
	int eventBoxWidth, eventBoxHeight;
	Port event;
	
	public PortMenu(Player player) 
	{
		super(player);
		try
		{
			background = ImageIO.read(new File("res/textures/sea.png"));
			width = background.getWidth();
			height = background.getHeight();
			
			eventBox = ImageIO.read(new File("res/textures/parchmentEvent.png"));
			eventBoxWidth = eventBox.getWidth();
			eventBoxHeight = eventBox.getHeight();
			
			
		} catch(IOException e) {
			e.printStackTrace();
		}
		
		event = new Port(player); 
	}

	public void update(Point pointPressed, Point pointReleased) {
		//EXIT
		event.eventButton.highlight(false);
		
		if (clicked)
		{
			if (event.eventButton.contains(pointPressed))
			{
				event.eventButton.highlight(true);
			}
		}
		
		if (released)
		{
			if (event.eventButton.contains(pointReleased))
			{
				event.eventButton.highlight(false);
				Sound.click();
				event.update();
			}
		}
		
		//BUY RATIONS
		//event.buyRations.highlight(false);
		
		if (clicked)
		{
			if (event.buyRations.contains(pointPressed))
			{
				//event.buyRations.highlight(true);
			}
		}
		
		if (released)
		{
			if (event.buyRations.contains(pointReleased))
			{
				//event.buyRations.highlight(false);
				if(player.getGold() > Global.RATIONS_COST && Port.getRationsStock() > 0)
				{
					Sound.click();
					event.buyRations();
					State.setState(STATE.PORT);
				}
			}
		}
		
		//BUY AMMUNITION
		//event.buyAmmunition.highlight(false);
		
		if (clicked)
		{
			if (event.buyAmmunition.contains(pointPressed))
			{
				//event.buyAmmunition.highlight(true);
			}
		}
		
		if (released)
		{
			if (event.buyAmmunition.contains(pointReleased))
			{
				//event.buyAmmunition.highlight(false);
				if(player.getGold() > Global.AMMUNITION_COST && Port.getAmmunitionStock() > 0)
				{
					Sound.click();
					event.buyAmmunition();
					State.setState(STATE.PORT);
				}
			}
		}
		
		//REPAIR 1
		//event.repairHull.highlight(false);
		
		if (clicked)
		{
			if (event.repairHull.contains(pointPressed))
			{
				//event.repairHull.highlight(true);
			}
		}
		
		if (released)
		{
			if (event.repairHull.contains(pointReleased))
			{
				//event.repairHull.highlight(false);
				if(player.getGold() > Global.HULL_COST && !player.isHullFull())
				{
					Sound.click();
					event.repairHull();
					State.setState(STATE.PORT);
				}
			}
		}
		
		//REPAIR ALL
	//	event.repairHullFull.highlight(false);
		
		if (clicked)
		{
			if (event.repairHullFull.contains(pointPressed))
			{
				//event.repairHullFull.highlight(true);
			}
		}
		
		if (released)
		{
			if (event.repairHullFull.contains(pointReleased))
			{
				//event.repairHullFull.highlight(false);
				if(player.getGold() > (Global.MAX_HULL - player.getHull()) * Global.HULL_COST  && !player.isHullFull())
				{
					Sound.click();
					event.repairHullFull();
					State.setState(STATE.PORT);
				}
				
			}
		}
		
		//BUY EVASION
		//event.buyEvasion.highlight(false);
		
		if (clicked)
		{
			if (event.buyEvasion.contains(pointPressed))
			{
				//event.buyEvasion.highlight(true);
			}
		}
		
		if (released)
		{
			if (event.buyEvasion.contains(pointReleased))
			{
				//event.buyEvasion.highlight(false);
				if(player.getGold() > Global.EVASION_COST)
				{
					Sound.click();
					event.buyEvasion();
					State.setState(STATE.PORT);
				}
			}
		}
		
		//BUY ACCURACY
		//event.buyAccuracy.highlight(false);
		
		if (clicked)
		{
			if (event.buyAccuracy.contains(pointPressed))
			{
				//event.buyAccuracy.highlight(true);
			}
		}
		
		if (released)
		{
			if (event.buyAccuracy.contains(pointReleased))
			{
				//event.buyAccuracy.highlight(false);
				if(player.getGold() > Global.ACCURACY_COST)
				{
					Sound.click();
					event.buyAccuracy();
					State.setState(STATE.PORT);
				}
			}
		}
		
		for(int j = 0; j < event.upgradeButtons.length; j++)
		{
			for(int k = 0; k < event.upgradeButtons[j].length; k++)
			{
				if (clicked)
				{
					if (event.upgradeButtons[j][k].contains(pointPressed))
					{
						//event.buyAccuracy.highlight(true);
					}
				}
				
				if (released)
				{
					if (event.upgradeButtons[j][k].contains(pointReleased) && !event.upgradeButtons[j][k].isGray())
					{
						switch(event.upgradeButtons[j][k].getText())
						{
						case "Upgrade?":
							player.modGold(-1 * player.weapons[j].getCost());
							event.upgradeWeapon(j);
							Sound.click();
							break;
						case "Purchase Cannon I?":
							player.modGold(-1 * Global.NEW_CANNON_PRICE);
							event.buyCannon(j);
							Sound.click();
							break;
						case "Purchase Catapult I?":
							player.modGold(-1 * Global.NEW_CATAPULT_PRICE);
							event.buyCatapult(j);
							Sound.click();
							break;
						default:
								break;
						}		
						
						for(int i = 0; i < event.weaponUpgrades.length; i++)
						{
							 event.generateWeaponOptions(i);
						}
						
						State.setState(STATE.PORT);						
					}
				}
			}
		}
	}

	public void render(Graphics g) {
		g.drawImage(background, STATS_WIDTH, 0, width, height, null);
		g.drawImage(eventBox, (STATS_WIDTH + ((width - eventBoxWidth) / 2)),
				(height - eventBoxHeight) / 2, eventBoxWidth, eventBoxHeight, null);
		
		event.render(g);
	}

}

