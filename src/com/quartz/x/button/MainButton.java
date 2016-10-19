package com.quartz.x.button;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import com.quartz.x.label.MainLabel;

public class MainButton extends Button
{
	/**
	 * Will construct a new button's dimensions and location.
	 * @param area - the rectangle that represents the area for the button.
	 * @param text - the string that will be printed on the button.
	 */
	public MainButton(Rectangle area, String text)
	{
		super(area);
		
		label = new MainLabel(text, x, y, width, height);
		
		try
		{
			backgroundNormal = ImageIO.read(new File("res/textures/buttonTemplate.png"));
			backgroundClicked = ImageIO.read(new File("res/textures/buttonClicked.png"));
			backgroundDisabled = ImageIO.read(new File("res/textures/buttonDisabled.png"));
			background = backgroundNormal;
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
	}

	@Override
	public void render(Graphics g)
	{
		if (!disabled)
		{
			g.drawImage(background, x, y, width, height, null);
		}
		else
		{
			g.drawImage(backgroundDisabled, x, y, width, height, null);
		}
		label.render(g);
	}

	/**
	 * If a button was clicked, highlight the image. Remove the highlight after release.
	 * @param clicked - True if the button was clicked; False otherwise.
	 */
	public void highlight(boolean clicked)
	{
		if (clicked)
			background = backgroundClicked;
		else
			background = backgroundNormal;
	}
}
