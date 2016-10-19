package com.quartz.x.player;

import com.quartz.x.Global;
import com.quartz.x.State;
import com.quartz.x.State.STATE;
import com.quartz.x.combat.Weapon;
import com.quartz.x.map.Map;
import com.quartz.x.map.MapFour;
import com.quartz.x.map.MapOne;
import com.quartz.x.map.MapThree;
import com.quartz.x.map.MapTwo;
import com.quartz.x.map.MapZero;

public class Player
{
	private int gold;
	private int rations;
	private int ammunition;
	private int currentMap;
	private int currentMapPoint;
	private int hull,
				accuracy,
				evasion;
	private Map map;
	
	public Weapon[] weapons; 
	
	public Player()
	{
		weapons = new Weapon[] {new Weapon("Catapult"), new Weapon("Cannon"), new Weapon(),  new Weapon()};
		//weapons = new Weapon[] {new Weapon("Catapult", 10), new Weapon("Catapult", 10), new Weapon("Cannon", 10),  new Weapon("Cannon", 10)};
		
		gold = 100;
		rations = 10;
		ammunition = 15;
		currentMap = 0;
		currentMapPoint = 0;
		hull = 	Global.MAX_HULL;
		accuracy = 10;
		evasion = 10;
		map = new MapZero();
	}
	
	public int getHull() {
		return hull;
	}
		
	public Map getMap()
	{
		return map;
	}
	
	public int getMapCount()
	{
		return currentMap;
	}

	public void switchMap()
	{
		switch (currentMap) {
		case 0:
			map = new MapOne();
			break;
		case 1:
			map = new MapTwo();
			break;
		case 2:
			map = new MapThree();
			break;
		case 3:
			map = new MapFour();
			break;
		default:
			break;
		}
		currentMap++;
		currentMapPoint = 0;
	}

	public int getMapPoint()
	{
		return currentMapPoint;
	}
	
	public void setMapPoint(int nextMapPoint)
	{
		this.currentMapPoint = nextMapPoint;
	}
	
	public int getGold()
	{
		return gold;
	}
	
	public int getRations()
	{
		return rations;
	}
	
	public int getAmmunition()
	{
		return ammunition;
	}
	

	public int getEvasion() 
	{
		return evasion;
	}
	
	public int getAccuracy() 
	{
		return accuracy;
	}
	
	public void modGold(int amount)
	{
		gold += amount;
	}
	
	public void modRations(int amount)
	{
		rations += amount;
		if (rations <= 0) {
			rations = 0;
			State.setState(STATE.GAME_OVER);
		}
	}

	public void modAmmunition(int amount)
	{
		ammunition += amount;
	}	

	public void modHull(int amount)
	{
		hull += amount;
		if (hull <= 0) {
			hull = 0;
			State.setState(STATE.GAME_OVER);
		}
	}

	public void modAccuracy(int amount)
	{
		accuracy += amount;
	}

	public void modEvasion(int amount)
	{
		evasion += amount;
	}

	public Boolean isHullFull()
	{
		return hull == Global.MAX_HULL;
	}
	
	public void modResources(int[] resourceMods)
	{
		modGold(resourceMods[0]);
		modHull(resourceMods[1]);
		modRations(resourceMods[2]);
		modAmmunition(resourceMods[3]);
		modAccuracy(resourceMods[4]);
		modEvasion(resourceMods[5]);
	}
	
//	public void addWeapon(int weaponCode)
//	{
//		int emptySlot = Global.nextEmptyWeaponSlot(weapons);
//		
//		if(emptySlot > -1)
//		{
//			weapons[emptySlot] = Global.ALL_WEAPONS[weaponCode];
//		}
////		else
////			weaponsFull(newWeapon);
//	}
//	
//	public void weaponsFull(Weapon newWeapon)
//	{
//		System.out.println("You do not have any more weapon storage space. Select a weapon to throw overboard.");
//		
//		for(int i = 0; i < weapons.length; i++)
//		{
//			System.out.println((i + 1) + ". Weapon " + (i + 1));
//			weapons[i].print();
//		}
//		
//		System.out.println((weapons.length + 1) + ". New Weapon:");
//		newWeapon.print();
//		
//		int choice = Const.SCAN.nextInt();
//		
//		if(choice > 0 && choice < weapons.length + 1)
//		{
//			weapons[choice - 1] = newWeapon;			
//		}
//		else if(choice == weapons.length + 1)
//			System.out.print("The new weapon was thrown overboard");
//		else
//		{
//			System.out.println("Invalid choice. Try again");
//			weaponsFull(newWeapon);
//		}
//	}
	
}