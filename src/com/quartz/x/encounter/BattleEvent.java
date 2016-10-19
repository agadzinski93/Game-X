package com.quartz.x.encounter;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Rectangle;

import com.quartz.x.Global;
import com.quartz.x.State;
import com.quartz.x.State.STATE;
import com.quartz.x.button.EventButton;

public class BattleEvent extends Encounter {
	
	public EventButton eventButton;
	
	public BattleEvent() {
		
		this.eventText = "Battle Event.";
		this.eventEffect = new int[] {0,0,0,0,0,0};
		
		eventButton = new EventButton(new Rectangle(700, 425, 400, 14), "Get ready to fight!");
	}
	
	public BattleEvent(String eventText) {
		this.eventText = eventText;
		
		eventButton = new EventButton(new Rectangle(700, 425, 400, 14), "Get ready to fight!");
	}
	
	public BattleEvent(int event) {
		this.eventText = Global.NORMAL_BATTLE_EVENTS[event].eventText;
		
		eventButton = new EventButton(new Rectangle(700, 425, 400, 14), "Get ready to fight!");
	}
	
	public BattleEvent(String newEventText, int[] newEventEffects)
	{
		this.eventText = newEventText;
		this.eventEffect = newEventEffects;

		eventButton = new EventButton(new Rectangle(700, 425, 400, 14), "Get ready to fight!");
	}
	
	public BattleEvent(BattleEvent newEvent)
	{
		this.eventText = newEvent.eventText;		
	}
	
	public void update() {
		State.setState(STATE.BATTLE);
	}
	
	public void render(Graphics g) {
		g.setColor(Color.BLACK);
		g.setFont(new Font("Serif", Font.BOLD, 18));
		
		if(eventText.length() > 30)
		{
			Global.drawStringWrapped(g, eventText, Global.PARCHMENT_START_Y);			
		}
		else
		{
			g.drawString(eventText, Global.PARCHMENT_START_X, Global.PARCHMENT_START_Y);
		}
		
		eventButton.render(g);
	}
}
