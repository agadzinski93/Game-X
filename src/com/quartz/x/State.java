package com.quartz.x;

public abstract class State
{	
	public enum STATE
	{
		MENU,
		EXIT,
		OPTIONS,
		OPTIONS_GAME,
		MUSIC_GAME,
		SHIP,
		MAP,
		CUSTOMIZE,
		EVENT,
		PORT,
		NEUTRAL_EVENT,
		INSTANT_EVENT,
		BATTLE_EVENT,
		NEUTRAL_BATTLE,
		NEUTRAL_INSTANT,
		BATTLE,
		NEW_GAME,
		CONTINUE_GAME,
		VISITED_EVENT,
		REWARD,
		GAME_OVER,
		RESET;
	};
	
	/** STATE - Holds the state of the game. */
	private static STATE state = STATE.MENU;
	
	/**
	 * Returns the state of the game.
	 * @return STATE - the state of the game.
	 */
	public static STATE getState()
	{
		return state;
	}
	
	/**
	 * Sets the state of the game to the state passed in.
	 * @param s - the new state
	 */
	public static void setState(STATE s)
	{
		state = s;
	}
	
}