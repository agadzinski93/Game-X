package com.quartz.x.map;

import java.awt.Point;

import com.quartz.x.Global;
import com.quartz.x.State;
import com.quartz.x.Sound.Sound;
import com.quartz.x.State.STATE;
import com.quartz.x.player.Player;

public abstract class Map
{
	protected final int NUM_OF_POINTS = 12;
	
	protected MapPoint[] mapPoints;
	protected int[] travelable;
	
	protected static boolean clicked = false;
	protected static boolean released = false;
	
	public Map()
	{
		mapPoints = new MapPoint[NUM_OF_POINTS];
	}
	
	public void update(Point pointPressed, Point pointReleased, Player player)
	{
		if (clicked)
		{
			/* DO NOTHING FOR NOW

			travelable = mapPoints[player.getMapPoint()].getTravelable();

			for (int i = 0; i < travelable.length; i++)
				if (mapPoints[travelable[i]].contains(point))
				{

				}
			 */
		}

		if (released)
		{
			travelable = mapPoints[player.getMapPoint()].getTravelable();

			for (int i = 0; i < travelable.length; i++)
				if (mapPoints[travelable[i]].contains(pointPressed, pointReleased))
				{
					Sound.click();
					player.setMapPoint(travelable[i]);
					player.modRations(-1);

					if (!mapPoints[travelable[i]].getVisited())
					{
						mapPoints[travelable[i]].setVisited(true);
						startEncounter();
					}
					else
					{
						startVisited();
					}

					if (mapPoints[travelable[i]].getExitPoint())
					{
						player.switchMap();
					}
				}

			released = false;
		}
	}
	
	public static void setClicked(boolean value)
	{
		clicked = value;
	}
	
	public static void setReleased(boolean value)
	{
		released = value;
	}
	
	public MapPoint[] getMapPoints()
	{
		return mapPoints;
	}
	
	public int[] getTravelable()
	{
		return travelable;
	}
	
	public void startEncounter()
	{
		/*
		 * Port event chance is 25%
		 * Instant event chance is 30%
		 * Battle event Chance is 25%
		 * Neutral event chance is 20%
		 */
		int chance = Global.RANDOM_RANGE(1,100);

		if(chance < 25)
			State.setState(STATE.PORT);
		else if(chance < 55)
			State.setState(STATE.INSTANT_EVENT);
		else if (chance < 80)
			State.setState(STATE.BATTLE_EVENT);
		else
			State.setState(STATE.NEUTRAL_EVENT);
	}

	public void startVisited()
	{
		State.setState(STATE.VISITED_EVENT);
	}
}
