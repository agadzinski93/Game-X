package com.quartz.x.combat;

import com.quartz.x.Global;

public class Weapon 
{
	private final int BASE_ACCURACY = 40;
	private final int BASE_DAMAGE = 1;
	private final int BASE_COOLDOWN = 10;
		
	public String weaponName; //cannon or catapult
	public int weaponLevel; //upgrade level

	private int accuracy, //the weapons specific chance to hit
	cooldown, //the amount of time before the weapon is usable again
	damage; //the amount of damage each weapon shot does

	//mutator functions

	public Weapon()
	{
		weaponName = "EMPTY";
		weaponLevel = 0;
		accuracy = 0;
		damage = 0;
		cooldown = 0;
	}
	
	public Weapon(String newWeaponName, int newWeaponLevel)
	{
		weaponName = newWeaponName;
		weaponLevel = newWeaponLevel;
		accuracy = BASE_ACCURACY + (5 * (weaponLevel - 1));
		damage = BASE_DAMAGE + ((weaponLevel - 1) / 3);
		cooldown = BASE_COOLDOWN - ((weaponLevel - 1) / 2);
		
		if(newWeaponName == "Cannon")
		{
			accuracy += 15;
			damage += 2;
			cooldown -= 2;	
		}
	}
	
	public Weapon(String newWeaponName)
	{
		weaponName = newWeaponName;
		weaponLevel = 1;
		accuracy = BASE_ACCURACY;
		damage = BASE_DAMAGE;
		cooldown = BASE_COOLDOWN;
		
		if(newWeaponName == "Cannon")
		{
			accuracy += 15;
			damage += 2;
			cooldown -= 2;	
		}
	}
	
	public Weapon(String newWeaponName, int newWeaponLevel, int newAccuracy,
			int newShots, int newCooldown, int newDamage)
	{
		weaponName = newWeaponName;
		weaponLevel = newWeaponLevel;
		accuracy = newAccuracy;
		cooldown = newCooldown;
		damage = newDamage;
	}
	
	public void upgradeWeapon()
	{
		weaponLevel++;
		accuracy += 5;
		if(weaponLevel % 3 == 1)
			damage++;
		if(weaponLevel % 2 == 1)
			cooldown--;
	}
	
	//accessor functions
	public int getAccuracy()
	{
		return accuracy;
	}

	public int getCoolDown()
	{
		return cooldown;
	}

	public int getDamage()
	{
		return damage;
	}	
	
	public int getLevel()
	{
		return weaponLevel;
	}
	
	public String getWeaponName()
	{
		return weaponName;
	}
	
	public Boolean isEmpty()
	{
		return weaponName == "EMPTY";
	}
	
	public int getCost()
	{
		if(weaponName == "Cannon")
			return Global.CANNON_UPGRADE_BASE + (Global.CANNON_UPGRADE_MODIFIER * weaponLevel);
		else if(weaponName == "Catapult")
			return Global.CATAPULT_UPGRADE_BASE + (Global.CATAPULT_UPGRADE_MODIFIER * weaponLevel);
		else
			return 0;
	}
	
	public void print()
	{
		System.out.println(weaponName + " " + weaponLevel);
		System.out.println("Accuracy: " + accuracy);
		System.out.println("Damage: " + damage);
		System.out.println("Cooldown: " + cooldown);
		
	}
}
