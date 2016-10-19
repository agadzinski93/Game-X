package com.quartz.x.healthbar;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class HealthBar {
	private final int X_BUFFER = 9;
	private final int Y_BUFFER = 10;
	
	private BufferedImage	healthBarFirst,
							healthBarMiddle,
							healthBarLast;
	
	private BufferedImage healthPoint;
	
	private int healthBarWidth;
	private int healthPointWidth, healthPointHeight;
	private int pointsToDraw;
	private int xBarDrawLoc, yBarDrawLoc;

	public HealthBar(int xBarDrawLoc, int yBarDrawLoc, int maxHealth) {
		try {
			healthBarFirst = ImageIO.read(new File("res/textures/healthBarFirst.png"));
			healthBarMiddle = ImageIO.read(new File("res/textures/healthBarMiddle.png"));
			healthBarLast = ImageIO.read(new File("res/textures/healthBarLast.png"));
			healthBarWidth = healthBarFirst.getWidth() + (healthBarMiddle.getWidth() * maxHealth) + healthBarLast.getWidth();

			healthPoint = ImageIO.read(new File("res/textures/healthPoint.png"));
			healthPointWidth = healthPoint.getWidth();
			healthPointHeight = healthPoint.getHeight();
		} catch(IOException e) {
			e.printStackTrace();
		}
		
		this.xBarDrawLoc = xBarDrawLoc;
		this.yBarDrawLoc = yBarDrawLoc;
	}
	
	public void update(int currentHealth) {

		pointsToDraw = currentHealth;
//		percentRemaining = currentHealth * 100 / totalHealth;
//		
//		if (percentRemaining == 100) {
//			pointsToDraw = 10;
//		} else if (percentRemaining > 0) {
//			pointsToDraw = (percentRemaining / 10) + 1;
//		} else {
//			pointsToDraw = 0;
//		}
	}
	
	public void render(Graphics g) {
		
		g.drawImage(healthBarFirst, xBarDrawLoc, yBarDrawLoc, healthBarFirst.getWidth(), healthBarFirst.getHeight(), null);
		for(int i = 0; i < healthBarWidth - (healthBarFirst.getWidth() + healthBarLast.getWidth()); i +=  healthBarMiddle.getWidth())
			g.drawImage(healthBarMiddle, xBarDrawLoc + healthBarFirst.getWidth() + i, yBarDrawLoc, healthBarMiddle.getWidth(), healthBarMiddle.getHeight(), null);
		g.drawImage(healthBarLast, xBarDrawLoc + healthBarWidth - healthBarLast.getWidth(), yBarDrawLoc, healthBarLast.getWidth(), healthBarLast.getHeight(), null);
		
		int iter = pointsToDraw;
		while (iter > 0) {
			g.drawImage(healthPoint, (xBarDrawLoc + X_BUFFER) + (healthPointWidth * (iter - 1)),
					(yBarDrawLoc + Y_BUFFER),
					healthPointWidth, healthPointHeight, null);
			iter--;
		}
	}
}
