package com.quartz.x;

import java.awt.Canvas;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.image.BufferStrategy;
import java.io.IOException;

import javax.swing.JFrame;

import com.quartz.x.State.STATE;
import com.quartz.x.encounter.InstantEvent;
import com.quartz.x.input.Mouse;
import com.quartz.x.menu.BattleEventMenu;
import com.quartz.x.menu.BattleMenu;
import com.quartz.x.menu.CustomizeMenu;
import com.quartz.x.menu.ExitMenu;
import com.quartz.x.menu.GameOverMenu;
import com.quartz.x.menu.InstantEventMenu;
import com.quartz.x.menu.MainMenu;
import com.quartz.x.menu.MapMenu;
import com.quartz.x.menu.Menu;
import com.quartz.x.menu.MusicGameMenu;
import com.quartz.x.menu.NeutralEventMenu;
import com.quartz.x.menu.OptionsGameMenu;
import com.quartz.x.menu.OptionsMenu;
import com.quartz.x.menu.PortMenu;
import com.quartz.x.menu.RewardMenu;
import com.quartz.x.menu.ShipMenu;
import com.quartz.x.menu.StatsMenu;
import com.quartz.x.player.Enemy;
import com.quartz.x.player.Player;

public class Game extends Canvas implements Runnable
{
	private static final long serialVersionUID = 1L;
	
	public static int height = 240;
	public static int width = height * 16 / 9;
	public static int scale = 3;
	public static String title = "X";
	
	private Thread thread;
	private JFrame frame;
	private boolean running = false;
	private boolean printStatsMenu = false;
	private Mouse mouse;
	private Cursor cursor;
	public static Player player;
	
	private static Menu menu;
	private static Menu statsMenu;
	
	public Game()
	{
		Dimension size = new Dimension(width * scale, height * scale);
		setPreferredSize(size);
		
		try {
			Global.fillLists();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		frame = new JFrame();
		mouse = new Mouse();
		cursor = Toolkit.getDefaultToolkit().createCustomCursor(Toolkit.getDefaultToolkit().getImage("res/textures/cursorHook.png"), new Point(0, 0), "Cursor");
		this.setCursor(cursor);
		this.addMouseListener(mouse);
	}
	
	public synchronized void start()
	{
		running = true;
		thread = new Thread(this, "Display");
		thread.start();
	}
	
	public synchronized void stop()
	{
		running = false;
		
		try
		{
			thread.join();
		}
		catch(InterruptedException e)
		{
			e.printStackTrace();
		}
	}
	
	public void run()
	{
		long lastTime = System.nanoTime();
		long timer = System.currentTimeMillis();
		final double ns = 1000000000.0 / 60;
		double delta = 0;
		
		int frames = 0,
			updates = 0;
		
		update();
		
		while (running)
		{
			long now = System.nanoTime();
			delta += (now - lastTime) / ns;
			lastTime = now;
			
			while (delta >= 1)
			{
				update();
				updates++;
				delta--;
			}
	
			render();
			frames++;
			
			if (System.currentTimeMillis() - timer > 1000)
			{
				timer += 1000;
				frame.setTitle(title + "  |  " + updates + " ups, " + frames + " fps");
				updates = 0;
				frames = 0;
			}
		}
		
		stop();
	}
	
	public void update()
	{	
		switch(State.getState())
		{
		case RESET:
			State.setState(STATE.MENU);
			player = null;
			
			break;
			
		case MENU:
			if (!(menu instanceof MainMenu) || (menu instanceof OptionsMenu) || (menu instanceof ExitMenu))
			{
				menu = new MainMenu(player);
				printStatsMenu = false;
			}
			
			break;
			
		case EXIT:
			if (!(menu instanceof ExitMenu))
			{
				menu = new ExitMenu(player);
				printStatsMenu = false;
			}
			
			break;
		
		case OPTIONS:
			if (!(menu instanceof OptionsMenu))
			{
				menu = new OptionsMenu(player);
				printStatsMenu = false;
			}
			
			break;
			
		case NEW_GAME:
			player = new Player();
			statsMenu = new StatsMenu(player);
			State.setState(STATE.SHIP);
			break;
			
		case CONTINUE_GAME:
			if (player instanceof Player)
			{
				State.setState(STATE.SHIP);
			}
			else
			{
				State.setState(STATE.MENU);
			}
			break;
			
		case SHIP:
			if (!(menu instanceof ShipMenu) || (menu instanceof OptionsGameMenu))
			{
				menu = new ShipMenu(player);
				printStatsMenu = true;
			}
			
			break;
			
		case MAP:
			if (!(menu instanceof MapMenu))
			{
				menu = new MapMenu(player);
				printStatsMenu = true;
			}
			
			break;
			
		case CUSTOMIZE:
			if (!(menu instanceof CustomizeMenu))
			{
				menu = new CustomizeMenu(player);
				printStatsMenu = true;
			}
			
			break;
			
		case OPTIONS_GAME:
			if (!(menu instanceof OptionsGameMenu))
			{
				menu = new OptionsGameMenu(player);
				printStatsMenu = true;
			}
			
			break;
			
		case MUSIC_GAME:
			if (!(menu instanceof MusicGameMenu))
			{
				menu = new MusicGameMenu(player);
				printStatsMenu = true;
			}
			
			break;
			
		case INSTANT_EVENT:
			if (!(menu instanceof InstantEventMenu))
			{
				menu = new InstantEventMenu(player);
				printStatsMenu = true;
			}
			
			break;
			
		case VISITED_EVENT:
			if (!(menu instanceof InstantEventMenu))
			{
				menu = new InstantEventMenu(player, new InstantEvent("Returning to somewhere you've been before," +
			" you realize that there is nothing new to see here besides calm waters.",
						new int[] {0, 0, 0, 0, 0, 0}));
				printStatsMenu = true;
			}
			
			break;
			
		case PORT:
			if (!(menu instanceof PortMenu))
			{
				menu = new PortMenu(player);
				printStatsMenu = true;
			}
			
			break;
			
		case NEUTRAL_EVENT:
			if (!(menu instanceof NeutralEventMenu))
			{
				menu = new NeutralEventMenu(player);
				printStatsMenu = true;
			}
			
			break;
			
		case NEUTRAL_BATTLE:
			if (!(menu instanceof BattleEventMenu))
			{
				menu = new BattleEventMenu(player, Global.tempBattleEvent);
				printStatsMenu = true;
			}
			
			break;
			
		case NEUTRAL_INSTANT:
			if (!(menu instanceof InstantEventMenu))
			{
				menu = new InstantEventMenu(player, Global.tempInstantEvent);
				printStatsMenu = true;
			}
			
			break;
			
		case BATTLE_EVENT:
			if (!(menu instanceof BattleEventMenu))
			{
				menu = new BattleEventMenu(player);
				printStatsMenu = true;
			}
			
			break;
			
		case BATTLE:
			if (!(menu instanceof BattleMenu))
			{
				menu = new BattleMenu(player, new Enemy(player.getMapCount() + 1));
				printStatsMenu = true;
			}
			
			break;
			
		case REWARD:
			if (!(menu instanceof RewardMenu))
			{
				menu = new RewardMenu(player);
				printStatsMenu = true;
			}
			
			break;
			
		case GAME_OVER:
			if (!(menu instanceof GameOverMenu))
			{
				menu = new GameOverMenu(player);
				printStatsMenu = true;
			}
			
			break;
			
		default:
			break;
		}

		menu.update(mouse.getPointPressed(), mouse.getPointReleased());
		
		if (printStatsMenu)
			statsMenu.update(mouse.getPointPressed(), mouse.getPointReleased());
	}
	
	public void render()
	{
		BufferStrategy bs = getBufferStrategy();
		
		if (bs == null)
		{
			createBufferStrategy(3);
			return;
		}
		
		Graphics g = bs.getDrawGraphics();

		if (printStatsMenu)
			statsMenu.render(g);
		
		menu.render(g);
		
		g.dispose();
		bs.show();
	}
	
	public static void main(String[] args)
	{
		Game game = new Game();

					
		game.frame.setResizable(false);
		game.frame.setTitle(title);
		game.frame.add(game);
		game.frame.pack();
		game.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		game.frame.setLocationRelativeTo(null);
		game.frame.setVisible(true);
		
		game.start();
	}
	
}
