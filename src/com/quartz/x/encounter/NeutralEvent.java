package com.quartz.x.encounter;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Rectangle;

import com.quartz.x.Global;
import com.quartz.x.State;
import com.quartz.x.State.STATE;
import com.quartz.x.button.EventButton;

public class NeutralEvent extends Encounter{

	public String[] options;
	public char[] outcomeType;
	public int[] outcomes;
	public EventButton[] optionButtons;
	
	public NeutralEvent()
	{		
		//Neutral event._Battle_b_0_Instant_i_0
		this.eventText = "Neutral event.";
		options = new String[] {"Battle", "Instant"};
		outcomeType = new char[] {'b', 'i'};
		outcomes = new int[] {0, 0};
		
		optionButtons = new EventButton[options.length];
		
		for(int i = 0; i < options.length; i++)
		{
			optionButtons[i] = new EventButton(new Rectangle(730, 360 + (50 * i), 400, 14), options[i]);
		}
	}
	
	public NeutralEvent(int index)
	{
		this.eventText = Global.ALL_NEUTRAL_EVENTS[index].eventText;
		options = Global.ALL_NEUTRAL_EVENTS[index].options;
		outcomeType = Global.ALL_NEUTRAL_EVENTS[index].outcomeType;
		outcomes = Global.ALL_NEUTRAL_EVENTS[index].outcomes;
		
		optionButtons = new EventButton[options.length];
		
		for(int i = 0; i < options.length; i++)
		{
			optionButtons[i] = new EventButton(new Rectangle(730, 360 + (50 * i), 400, 14), options[i]);
		}
	}
	
	public NeutralEvent(String newEventText, String[] newOptions, int[] newOutcomes, char[] newOutcomeType)
	{
		this.eventText = newEventText;
		options = newOptions;
		outcomeType = newOutcomeType;
		outcomes = newOutcomes;
		
		optionButtons = new EventButton[options.length];
		
		for(int i = 0; i < options.length; i++)
		{
			optionButtons[i] = new EventButton(new Rectangle(730, 360 + (50 * i), 400, 14), options[i]);
		}
	}
	
	
	public NeutralEvent(NeutralEvent newEvent)
	{
		this.eventText = newEvent.eventText;
		options = newEvent.options;
		outcomes = newEvent.outcomes;
		outcomeType = newEvent.outcomeType;
		
		optionButtons = new EventButton[options.length];
		
		for(int i = 0; i < options.length; i++)
		{
			optionButtons[i] = new EventButton(new Rectangle(730, 360 + (50 * i), 400, 14), options[i]);
		}
	}
	
	public void update(int index)
	{
		switch(outcomeType[index])
		{
		case 'b':
		{
			Global.tempBattleEvent = Global.BATTLE_OUTCOMES[outcomes[index]];
			State.setState(STATE.NEUTRAL_BATTLE);
			break;
			
		}
		case 'i':
		{
			Global.tempInstantEvent = Global.INSTANT_OUTCOMES[outcomes[index]];
			State.setState(STATE.NEUTRAL_INSTANT);
			break;
		}
		default:
		{
			State.setState(STATE.MAP);
			break;
		}
		}
	}
	
	public void print()
	{
		System.out.println(eventText);
		
		for(int i = 0; i < options.length; i++)
		{
			System.out.println(options[i]);
			System.out.println(outcomes[i]);
		}
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
		

		for(int k = 0; k < optionButtons.length; k++)
		{
			optionButtons[k].render(g);
		}	
		
	}
	
}
