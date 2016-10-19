package com.quartz.x.button;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Rectangle;

import com.quartz.x.Game;
import com.quartz.x.Global;
import com.quartz.x.combat.Weapon;
import com.quartz.x.label.EventLabel;

public class WeaponButton extends Button {
	Weapon weapon;
	public boolean loaded,
					haveAmmo;
	double timer;
	double reloadTimer;
	
	public WeaponButton(Rectangle area, Weapon weapon) {
		super(area);
		this.weapon = weapon;
		
		label = new EventLabel((int)area.getX(), (int)area.getY() + (int)area.getHeight(), "No Weapon");
		
		reloadTimer = weapon.getCoolDown();
	}
	
	public void update() {
		timer++;
		if (timer > 60) {
			timer -= 60;
			if (!loaded) {
				reloadTimer--;
				if (reloadTimer == 0) {
					loaded = true;
				}
			}
		}
		
		haveAmmo = Game.player.getAmmunition() > 0;

		if (!loaded && (haveAmmo || !label.getText().contains("Cannon")))
		{
			label.setColor(Color.DARK_GRAY);
			label.update(weapon.weaponName + " " + Global.toRomanNumeral("",weapon.weaponLevel) + " | " + reloadTimer);
		}
		else if (!haveAmmo && label.getText().contains("Cannon"))
		{
			label.setColor(Color.DARK_GRAY);
			label.update(weapon.weaponName + " " + Global.toRomanNumeral("",weapon.weaponLevel) + " | Out of Ammo");
		}
		else
		{
			label.setColor(Global.DARK_GREEN);
			label.update(weapon.weaponName + " " + Global.toRomanNumeral("",weapon.weaponLevel) + " | Ready!");
		}
	}

	public void fired() {
		reloadTimer = weapon.getCoolDown();
		loaded = false;
	}
	
	public void render(Graphics g) {
		if(!weapon.isEmpty())
			label.render(g);
	}

	public void setFont(Font newFont) 
	{
		label.setFont(newFont);		
	}

}
