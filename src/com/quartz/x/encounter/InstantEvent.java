package com.quartz.x.encounter;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Rectangle;

import com.quartz.x.Global;
import com.quartz.x.State;
import com.quartz.x.State.STATE;
import com.quartz.x.button.EventButton;
import com.quartz.x.label.EventLabel;

public class InstantEvent extends Encounter{

	public EventButton eventButton;
	
	private final int EFFECT_DRAW_X = 550;
	private final int EFFECT_DRAW_Y = 250;
	private final int EFFECT_BUFFER_Y = 25;
	
	private EventLabel eventEffectLabels[] = new EventLabel[6];
	
	public InstantEvent()
	{
		this.eventText = "Instant Event";
		this.eventEffect = new int[] {0,0,0,0,0,0};
		
		for (int iter = 0; iter < eventEffectLabels.length; ++iter)
		{
			eventEffectLabels[iter] = new EventLabel(EFFECT_DRAW_X, 0, "");
		}

		eventButton = new EventButton(new Rectangle(700, 425, 400, 14), "Continue...");
	}
	
	public InstantEvent(String filler)
	{
		this.eventText = "BATTLE";
		this.eventEffect = new int[] {0, 0, 0, 0, 0, 0};
		
		eventEffectLabels[0] = new EventLabel(EFFECT_DRAW_X, EFFECT_DRAW_Y, "filler");
		eventEffectLabels[1] = new EventLabel(EFFECT_DRAW_X, 0, "");
		eventEffectLabels[2] = new EventLabel(EFFECT_DRAW_X, 0, "");
		eventEffectLabels[3] = new EventLabel(EFFECT_DRAW_X, 0, "");
		eventEffectLabels[4] = new EventLabel(EFFECT_DRAW_X, 0, "");
		eventEffectLabels[5] = new EventLabel(EFFECT_DRAW_X, 0, "");

		eventButton = new EventButton(new Rectangle(700, 425, 400, 14), "Continue...");
	}
	
	public InstantEvent(int event)
	{
		this.eventText = Global.NORMAL_INSTANT_EVENTS[event].eventText;
		this.eventEffect = Global.NORMAL_INSTANT_EVENTS[event].eventEffect;
		
		for (int iter = 0; iter < eventEffectLabels.length; ++iter)
		{
			eventEffectLabels[iter] = new EventLabel(EFFECT_DRAW_X, 0, "");
		}
		
		applyEffects();

		eventButton = new EventButton(new Rectangle(700, 425, 400, 14), "Continue...");
	}
	
	public InstantEvent(String newEventText, int[] newEventEffects)
	{
		
		this.eventText = newEventText;
		this.eventEffect = newEventEffects;
		
		for (int iter = 0; iter < eventEffectLabels.length; ++iter)
		{
			eventEffectLabels[iter] = new EventLabel(EFFECT_DRAW_X, 0, "");
		}
		
		applyEffects();

		eventButton = new EventButton(new Rectangle(700, 425, 400, 14), "Continue...");
	}
	
	public InstantEvent(InstantEvent newEvent)
	{
		
		this.eventText = newEvent.eventText;
		this.eventEffect = newEvent.eventEffect;
		
		for (int iter = 0; iter < eventEffectLabels.length; ++iter)
		{
			eventEffectLabels[iter] = new EventLabel(EFFECT_DRAW_X, 0, "");
		}
		
		applyEffects();

		eventButton = new EventButton(new Rectangle(700, 425, 400, 14), "Continue...");
	}
	
	
	public void applyEffects()
	{
	
		String effect = "";
		int yBufferMultiplier = 0;
		
		for (int effectsIter = 0; effectsIter < eventEffect.length; ++effectsIter)
		{
			if (eventEffect[effectsIter] > 0)
			{
				effect = ("Gain " + eventEffect[effectsIter] + " " 
				+ resourceType[effectsIter]);
				eventEffectLabels[effectsIter].setColor(Global.DARK_GREEN);
				eventEffectLabels[effectsIter].setYPos(EFFECT_DRAW_Y +
						(yBufferMultiplier * EFFECT_BUFFER_Y));
				++yBufferMultiplier;
			}
			else if (eventEffect[effectsIter] < 0)
			{
				effect = ("Lose " + eventEffect[effectsIter] + " " 
				+ resourceType[effectsIter]);
				eventEffectLabels[effectsIter].setColor(Color.RED);
				eventEffectLabels[effectsIter].setYPos(EFFECT_DRAW_Y +
						(yBufferMultiplier * EFFECT_BUFFER_Y));
				++yBufferMultiplier;
			}
			else
			{
				effect = "";
			}
			
			eventEffectLabels[effectsIter].update(effect);
		}
	}
	
	@Override
	public void update() {

		State.setState(STATE.SHIP);
	}
	
	public void render(Graphics g) {
		g.setColor(Color.BLACK);
		g.setFont(new Font("Serif", Font.BOLD, 18));
		
		if(eventText.length() > Global.CHARACTER_LIMIT)
		{
			Global.drawStringWrapped(g, eventText, Global.PARCHMENT_START_Y);			
		}
		else
		{
			g.drawString(eventText, Global.PARCHMENT_START_X, Global.PARCHMENT_START_Y);
		}
		
		for (int iter = 0; iter < eventEffectLabels.length; ++iter)
		{
			eventEffectLabels[iter].render(g);
		}	

		eventButton.render(g);
	}
	
	public void print()
	{
		System.out.println(eventText);
		for(int i = 0; i < resourceType.length; i ++)
			System.out.println(resourceType[i] + ": " + eventEffect[i]);
	}

}
