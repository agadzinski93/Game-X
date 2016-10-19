package com.quartz.x.label;


import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import com.quartz.x.Global;
import com.quartz.x.combat.Weapon;

public class WeaponStatsLabel extends Label
{
	int weaponAccuracy,
		weaponDamage,
		weaponCooldown;
	
	public WeaponStatsLabel()
	{
		super();
		
		font = new Font("Serif", Font.BOLD, 14);
		color = Color.BLACK;
		weaponAccuracy = 0;
		weaponDamage = 0;
		weaponCooldown = 0;
		
	}
	
	public WeaponStatsLabel(String newWeaponName, int newWeaponAccuracy, int newWeaponDamage, int newWeaponCoolDown, int xLoc, int yLoc)
	{
		super(newWeaponName, xLoc, yLoc);	

		weaponAccuracy = newWeaponAccuracy;
		weaponDamage = newWeaponDamage;
		weaponCooldown = newWeaponCoolDown;
		
		font = new Font("Serif", Font.BOLD, 14);
		color = Color.BLACK;
	}
	
	public WeaponStatsLabel(Weapon weapon, int xLoc, int yLoc)
	{
		super("", xLoc, yLoc);
		
		if(!weapon.isEmpty())
			this.update(weapon.getWeaponName() + " " + Global.toRomanNumeral("", weapon.getLevel()));
		else
			this.update(weapon.getWeaponName());			

		weaponAccuracy = weapon.getAccuracy();
		weaponDamage = weapon.getDamage();
		weaponCooldown = weapon.getCoolDown();
		
		font = new Font("Serif", Font.BOLD, 14);
		color = Color.BLACK;
	}
	
	public void update(String newWeaponName, int newWeaponLevel)
	{
		if(newWeaponName != "EMPTY")
			this.update(newWeaponName + " " + Global.toRomanNumeral("", newWeaponLevel));
		else
			this.update(newWeaponName);	
	}
		
	@Override
	public void render(Graphics g)
	{
		g.setColor(color);
		g.setFont(new Font("Serif", Font.BOLD, 18));
		g.drawString(text, xLoc, yLoc);
		if(text != "EMPTY")
		{
			g.setFont(new Font("Serif", Font.BOLD, 14));
			g.drawString("Accuracy: " + weaponAccuracy + "  Damage: " + weaponDamage, xLoc, yLoc + 15);
			g.drawString("Cooldown: " + weaponCooldown, xLoc, yLoc + 30);
		}
	}
	
}