package com.quartz.x.label;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

public class CombatLabel extends Label {
	public static CombatLabel combatLabels[];
	public static int nextEmpty = 0;
	
	double timer;
	public boolean empty;
	
	public CombatLabel(String text, int xLoc, int yLoc) {
		super (text, xLoc, yLoc);
		
		color = Color.red;
		font = new Font("Serif", Font.PLAIN, 40);
		empty = false;
		timer = 0;
		
		combatLabels[nextEmpty] = this;

		if (nextEmpty == (combatLabels.length - 1))
			nextEmpty = 0;
		else
			nextEmpty++;
	}
	
	public void update() {
		timer++;
		if (timer % 3 == 0) {
			yLoc -= 2;
			xLoc -= 1;
		}
		
		if (timer > (60 * 3)) {
			empty = true;
		}
	}
	
	public void render(Graphics g) {
		g.setFont(font);
		g.setColor(color);
		g.drawString(text, xLoc, yLoc);
	}
}
