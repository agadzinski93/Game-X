package com.quartz.x.combat;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Random;

import javax.imageio.ImageIO;

import com.quartz.x.Sound.Sound;
import com.quartz.x.label.CombatLabel;
import com.quartz.x.player.Enemy;
import com.quartz.x.player.Player;

public class Shot {
	private BufferedImage shotImageOne;
	private BufferedImage shotImageTwo;
	private BufferedImage shotImageThree;
	private BufferedImage shotImageFour;
	private BufferedImage currentShotImage;
	private int shotImageIter;
	
	private int imageWidth, imageHeight;
	
	private int timer;
	private boolean empty;
	
	private int xLoc;
	private int yLoc;
	private double xStart;
	private double xDestination;
	private double xMovementIterator;
	
	private final int X_MISS_OFFSET = 125;
	
	private final Rectangle shotArea = new Rectangle(616, 135, 330, 390);
	
	public static Shot[] shots;
	public static int nextIndex = 0;
	
	private Integer damage;
	private Boolean shotFromPlayer, misses;
	private Player player;
	private Enemy enemy;
	
	public Shot(Boolean shotFromPlayer, Boolean misses, int damage, Player player, Enemy enemy) {
		this.player = player;
		this.enemy = enemy;
		this.damage = damage;
		this.shotFromPlayer = shotFromPlayer;
		this.misses = misses;
		
		try {
			shotImageOne = ImageIO.read(new File("res/textures/cannon_fly1.png"));
			shotImageTwo = ImageIO.read(new File("res/textures/cannon_fly2.png"));
			shotImageThree = ImageIO.read(new File("res/textures/cannon_fly3.png"));
			shotImageFour = ImageIO.read(new File("res/textures/cannon_fly4.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		imageWidth = shotImageOne.getWidth();
		imageHeight = shotImageOne.getHeight();
		
		currentShotImage = shotImageOne;
		shotImageIter = 1;
		timer = 0;
		
		if (shotFromPlayer) {
			xStart = shotArea.x;
			xDestination = (shotArea.x + shotArea.width);
			if (misses) 
				xDestination -= X_MISS_OFFSET;
		}
		else {
			xStart = shotArea.x + shotArea.width;
			xDestination = shotArea.x;
			if (misses)
				xDestination += X_MISS_OFFSET;
		}
		
		xMovementIterator = (xDestination - xStart) / 60;
		
		xLoc = (int) xStart;
		Random rand = new Random();
		yLoc = rand.nextInt((390 - 135)) + 135;
		
		shots[nextIndex] = this;
		
		if (nextIndex == (shots.length - 1)) 
			nextIndex = 0;
		else 
			nextIndex++;
	}
	
	public boolean getEmpty() {
		return empty;
	}
	
	//Called 1/60th of a second
	public void update() {
		timer++;
		xLoc += xMovementIterator;
		
		if (timer % 10 == 0) {
			switch (shotImageIter) {
			case 1:
				shotImageIter++;
				currentShotImage = shotImageTwo;
				break;
			case 2:
				shotImageIter++;
				currentShotImage = shotImageThree;
				break;
			case 3:
				shotImageIter++;
				currentShotImage = shotImageFour;
				break;
			case 4:
				shotImageIter = 1;
				currentShotImage = shotImageOne;
				break;
			}
		}

		if (timer == 60) {
			empty = true;
			if (shotFromPlayer) {
				if (!misses) {
					Sound.damageSound();
					CombatLabel.combatLabels[CombatLabel.nextEmpty] =
							new CombatLabel(damage.toString(), 900, 200);
					enemy.modHull(damage);
				}
				else {
					Sound.splash();
					int colorSwapper = CombatLabel.nextEmpty;
					CombatLabel.combatLabels[CombatLabel.nextEmpty] =
							new CombatLabel("MISS", 900, 200);
					CombatLabel.combatLabels[colorSwapper].setColor(Color.GRAY);
				}
			}
			else {
				if (!misses) {
					Sound.damageSound();
					CombatLabel.combatLabels[CombatLabel.nextEmpty] =
							new CombatLabel(damage.toString(), 400, 200);
					player.modHull(damage);
				}
				else {
					Sound.splash();
					int colorSwapper = CombatLabel.nextEmpty;
					CombatLabel.combatLabels[CombatLabel.nextEmpty] =
							new CombatLabel("MISS", 400, 200);
					CombatLabel.combatLabels[colorSwapper].setColor(Color.GRAY);
				}
			}
		}
	}

	public void render(Graphics g) {
		g.drawImage(currentShotImage, xLoc, yLoc, imageWidth, imageHeight, null);
	}
}
