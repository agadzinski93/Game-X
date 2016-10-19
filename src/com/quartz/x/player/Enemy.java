package com.quartz.x.player;

import java.util.Random;

import com.quartz.x.Global;
import com.quartz.x.Sound.Sound;
import com.quartz.x.combat.Shot;
import com.quartz.x.combat.Weapon;

public class Enemy {
	private int currentHealth;
	private int maxHealth;

	public Weapon[] weapons;
	public Integer[] cooldowns;
	
	public int evasion, accuracy;
	
	public int timer;
	private final int AI_FIRE_BUFFER = 3;
	
	public Enemy(int mapNumber) {
		maxHealth = 10 + (5 * mapNumber);
		currentHealth = maxHealth;
		evasion = 0 + (5 * mapNumber);
		accuracy = 10 + (5 * mapNumber);
		
		weapons = new Weapon[Global.MAX_WEAPONS];
		cooldowns = new Integer[Global.MAX_WEAPONS];

		switch (mapNumber) {
		case 1:
			weapons = new Weapon[2];
			cooldowns = new Integer[2];
			
			weapons[0] = generateEnemyWeapon(mapNumber);
			cooldowns[0] = weapons[0].getCoolDown() + 3;
			weapons[1] = generateEnemyWeapon(mapNumber);
			cooldowns[1] = weapons[0].getCoolDown() + 4;
			break;
			
		case 2:
			weapons = new Weapon[2];
			cooldowns = new Integer[2];
			
			weapons[0] = generateEnemyWeapon(mapNumber);
			cooldowns[0] = weapons[0].getCoolDown() + 2;
			weapons[1] = generateEnemyWeapon(mapNumber);
			cooldowns[1] = weapons[1].getCoolDown() + 3;
			break;
			
		case 3:
			weapons = new Weapon[3];
			cooldowns = new Integer[3];
			
			weapons[0] = generateEnemyWeapon(mapNumber); 
			cooldowns[0] = weapons[0].getCoolDown() + 1;
			weapons[1] = generateEnemyWeapon(mapNumber);
			cooldowns[1] = weapons[1].getCoolDown() + 2;
			weapons[2] = generateEnemyWeapon(mapNumber); 
			cooldowns[2] = weapons[2].getCoolDown() + 3;
			break;
			
		case 4:
			weapons = new Weapon[3];
			cooldowns = new Integer[3];
			
			weapons[0] = generateEnemyWeapon(mapNumber);
			cooldowns[0] = weapons[0].getCoolDown();
			weapons[1] = generateEnemyWeapon(mapNumber);
			cooldowns[1] = weapons[1].getCoolDown() + 1;
			weapons[2] = generateEnemyWeapon(mapNumber);
			cooldowns[2] = weapons[2].getCoolDown() + 2;
			break;
			
		case 5:
			weapons = new Weapon[3];
			cooldowns = new Integer[3];
			
			weapons[0] = generateEnemyWeapon(mapNumber);
			cooldowns[0] = weapons[0].getCoolDown();
			weapons[1] = generateEnemyWeapon(mapNumber); 
			cooldowns[1] = weapons[1].getCoolDown();
			weapons[2] = generateEnemyWeapon(mapNumber); 
			cooldowns[2] = weapons[2].getCoolDown() + 1;
			break;
		}

		timer = 0;
	}
	
	public boolean dead() {
		return (currentHealth <= 0);
	}

	public void update(Player player) {
		timer++;
		if (timer % 60 == 0) {
			for (int i = 0; i < cooldowns.length; ++i) {
				if (cooldowns[i] > 0) {
					cooldowns[i]--;
				}
			}
		}
		
		for (int i = 0; i < weapons.length; ++i) {
			if (cooldowns[i] <= 0) {
				int shotChance = (this.accuracy + weapons[i].getAccuracy()) - player.getEvasion();
				Random rand = new Random();
				int shotRoll = rand.nextInt(101);
				boolean misses;
				if (shotRoll <= shotChance)
					misses = false;
				else
					misses = true;

				Integer damage = 0 - this.weapons[i].getDamage();
				Shot.shots[Shot.nextIndex] =
						new Shot(false, misses, damage, player, this);

				switch (weapons[i].getWeaponName()) {
				case "Catapult":
					Sound.catapultLaunch();
					break;
				case "Cannon":
					Sound.cannonExplosion();
					break;
				}
				cooldowns[i] = weapons[i].getCoolDown() + AI_FIRE_BUFFER;
			}
		}
	}

	public void modHull(int n) {
		currentHealth += n;
	}

	public int getMaxHealth() {
		return maxHealth;
	}

	public int getCurrentHealth() {
		return currentHealth;
	}
	
	public Weapon generateEnemyWeapon(int mapNumber)
	{
		String weaponClass;
		int level,
			chance;
		
		chance = Global.RANDOM_RANGE(0,100);
		if(chance < (100 - (mapNumber * 5)))
			weaponClass = "Catapult";
		else
			weaponClass = "Cannon";
		
		chance = Global.RANDOM_RANGE(0,100);
		if(chance < 30 && mapNumber - 1 != 0)
			level = mapNumber - 1;
		else if(chance < 90)
			level = mapNumber;
		else
			level = mapNumber + 1;
		
		
		return new Weapon(weaponClass, level);
	}
}