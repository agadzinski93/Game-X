package com.quartz.x.Sound;

import java.io.File;
import java.io.IOException;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

public abstract class Sound
{
	private static AudioInputStream sound;
	private static Clip theme;
	private static FloatControl control;
	private static float volume = -9;
	
	/**
	 * Plays the click sound effect.
	 */
	public static void click()
	{
		try
		{
			sound = AudioSystem.getAudioInputStream(new File("res/sounds/click.wav"));
			Clip clip = AudioSystem.getClip();
			clip.open(sound);
			clip.start();
			control = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
			control.setValue(volume - 3);
		}
		catch(UnsupportedAudioFileException e)
		{
			e.printStackTrace();
		}
		catch(IOException e)
		{
			e.printStackTrace();
		}
		catch(LineUnavailableException e)
		{
			e.printStackTrace();
		}
	}
	
	/**
	 * Plays the splash sound effect.
	 */
	public static void splash()
	{
		try
		{
			sound = AudioSystem.getAudioInputStream(new File("res/sounds/splash.wav"));
			Clip clip = AudioSystem.getClip();
			clip.open(sound);
			clip.start();
			control = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
			control.setValue(volume - 6);
		}
		catch(UnsupportedAudioFileException e)
		{
			e.printStackTrace();
		}
		catch(IOException e)
		{
			e.printStackTrace();
		}
		catch(LineUnavailableException e)
		{
			e.printStackTrace();
		}
	}
	
	/**
	 * Plays the cannon explosion sound effect.
	 */
	public static void damageSound()
	{
		try
		{
			sound = AudioSystem.getAudioInputStream(new File("res/sounds/damageSound.wav"));
			Clip clip = AudioSystem.getClip();
			clip.open(sound);
			clip.start();
			control = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
			control.setValue(volume - 3);
		}
		catch(UnsupportedAudioFileException e)
		{
			e.printStackTrace();
		}
		catch(IOException e)
		{
			e.printStackTrace();
		}
		catch(LineUnavailableException e)
		{
			e.printStackTrace();
		}
	}
	
	/**
	 * Plays the cannon explosion sound effect.
	 */
	public static void cannonExplosion()
	{
		try
		{
			sound = AudioSystem.getAudioInputStream(new File("res/sounds/cannonExplosion.wav"));
			Clip clip = AudioSystem.getClip();
			clip.open(sound);
			clip.start();
			control = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
			control.setValue(volume - 3);
		}
		catch(UnsupportedAudioFileException e)
		{
			e.printStackTrace();
		}
		catch(IOException e)
		{
			e.printStackTrace();
		}
		catch(LineUnavailableException e)
		{
			e.printStackTrace();
		}
	}
	
	/**
	 * Plays the catapult launch sound effect.
	 */
	public static void catapultLaunch()
	{
		try
		{
			sound = AudioSystem.getAudioInputStream(new File("res/sounds/catapultLaunch.wav"));
			Clip clip = AudioSystem.getClip();
			clip.open(sound);
			clip.start();
			control = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
			control.setValue(volume - 5);
		}
		catch(UnsupportedAudioFileException e)
		{
			e.printStackTrace();
		}
		catch(IOException e)
		{
			e.printStackTrace();
		}
		catch(LineUnavailableException e)
		{
			e.printStackTrace();
		}
	}
	
	/**
	 * Starts the main theme song.
	 */
	public static void themeStart()
	{
		try
		{
			sound = AudioSystem.getAudioInputStream(new File("res/sounds/themeMain.wav"));
			theme = AudioSystem.getClip();
			theme.open(sound);
			control = (FloatControl) theme.getControl(FloatControl.Type.MASTER_GAIN);
			control.setValue(volume);
			theme.loop(Clip.LOOP_CONTINUOUSLY);
		}
		catch(UnsupportedAudioFileException e)
		{
			e.printStackTrace();
		}
		catch(IOException e)
		{
			e.printStackTrace();
		}
		catch(LineUnavailableException e)
		{
			e.printStackTrace();
		}
	}
	
	/**
	 * Starts the sailing theme song.
	 */
	public static void sailingStart()
	{
		try
		{
			sound = AudioSystem.getAudioInputStream(new File("res/sounds/themeSailing.wav"));
			theme = AudioSystem.getClip();
			theme.open(sound);
			control = (FloatControl) theme.getControl(FloatControl.Type.MASTER_GAIN);
			control.setValue(volume);
			theme.loop(Clip.LOOP_CONTINUOUSLY);
		}
		catch(UnsupportedAudioFileException e)
		{
			e.printStackTrace();
		}
		catch(IOException e)
		{
			e.printStackTrace();
		}
		catch(LineUnavailableException e)
		{
			e.printStackTrace();
		}
	}
	
	/**
	 * Starts the battle theme song.
	 */
	public static void battleStart()
	{
		try
		{
			sound = AudioSystem.getAudioInputStream(new File("res/sounds/themeBattle.wav"));
			theme = AudioSystem.getClip();
			theme.open(sound);
			control = (FloatControl) theme.getControl(FloatControl.Type.MASTER_GAIN);
			control.setValue(volume);
			theme.loop(Clip.LOOP_CONTINUOUSLY);
		}
		catch(UnsupportedAudioFileException e)
		{
			e.printStackTrace();
		}
		catch(IOException e)
		{
			e.printStackTrace();
		}
		catch(LineUnavailableException e)
		{
			e.printStackTrace();
		}
	}
	
	/**
	 * Stops the current background music.
	 */
	public static void themeStop()
	{
		theme.stop();
	}
	
	/**
	 * Increases the volume of the background music by 1dB.
	 */
	public static void volumeUp()
	{
		if (volume < 6)
		{
			control = (FloatControl) theme.getControl(FloatControl.Type.MASTER_GAIN);
			control.setValue(++volume);
			Sound.click();
		}
	}
	
	/**
	 * Decreases the volume of the background music by 1dB.
	 */
	public static void volumeDown()
	{
		if (volume > -18)
		{
			control = (FloatControl) theme.getControl(FloatControl.Type.MASTER_GAIN);
			control.setValue(--volume);
			Sound.click();
		}
	}
	
	/**
	 * Returns the volume of the game from a range of 0 to 15.
	 * @return The current volume of the game.
	 */
	public static int getVolume()
	{
		return (int) volume + 19;
	}
}
