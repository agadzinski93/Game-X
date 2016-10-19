package com.quartz.x.encounter;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Rectangle;

import com.quartz.x.State;
import com.quartz.x.State.STATE;
import com.quartz.x.button.EventButton;

public class Encounter {
	char eventType;
	public String eventText;
	public int[] eventEffect = new int[6];
	public String[] resourceType = new String[] {"gold", "hull", "rations",
			"ammunition", "accuracy", "evasion"};
	
	public EventButton eventButton;
	
	public Encounter() {
		this.eventType = 'd';
		this.eventText = "Nothing has changed since you were last here.";
		
		eventButton = new EventButton(new Rectangle(730, 360, 400, 14), "Ok");
	}
	
	public Encounter(String eventText) {
		this.eventType = 'p';
		this.eventText = eventText;
		
		eventButton = new EventButton(new Rectangle(730, 360, 400, 14), "Ok");
	}
	
	public Encounter(Encounter newEvent)
	{
		this.eventType = newEvent.eventType;
		this.eventText = newEvent.eventText;
		
	}
	
	public void update() {
		State.setState(STATE.SHIP);
	}
	
	public void render(Graphics g) {
		g.setColor(Color.BLACK);
		g.setFont(new Font("Serif", Font.BOLD, 18));
		g.drawString(eventText, 650, 200);
		eventButton.render(g);
	}
}
