package com.quartz.x.button;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

import com.quartz.x.combat.Weapon;
import com.quartz.x.label.WeaponStatsLabel;

public class WeaponUpgradeButton extends Button{
	private WeaponStatsLabel weaponLabel;
	
	Color originalColor;
	
	public WeaponUpgradeButton() {
		super();
		weaponLabel = new WeaponStatsLabel();
	}
	
	public WeaponUpgradeButton(Rectangle area) {
		super(area);
		weaponLabel = new WeaponStatsLabel();
	}
	
	public WeaponUpgradeButton(Rectangle area, Weapon weapon) {
		super(area);
		weaponLabel = new WeaponStatsLabel(weapon, (int)area.getX(), (int)area.getY());
	}
	
	public void setColor(Color newColor)
	{
		weaponLabel.setColor(newColor);
	}
	
	public void updateText(String newString)
	{
		weaponLabel.update(newString);
	}
	
	@Override
	public void render(Graphics g) {
		weaponLabel.render(g);
	}
	
	
	public void highlight(boolean clicked)
	{
		if (clicked)
		{
			originalColor = weaponLabel.getColor();
			weaponLabel.setColor(Color.BLUE);
		}
		else
		{
			weaponLabel.setColor(originalColor);
		}
	}
}
