package com.quartz.x;

import java.awt.Color;
import java.awt.Graphics;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.lang.Math;

import com.quartz.x.combat.Weapon;
import com.quartz.x.encounter.BattleEvent;
import com.quartz.x.encounter.InstantEvent;
import com.quartz.x.encounter.NeutralEvent;

public class Global 
{
	public static NeutralEvent[] ALL_NEUTRAL_EVENTS = new NeutralEvent[3];
	public static InstantEvent[] NORMAL_INSTANT_EVENTS = new InstantEvent[17];
	public static BattleEvent[] NORMAL_BATTLE_EVENTS = new BattleEvent[9];

	public static BattleEvent[] BATTLE_OUTCOMES = new BattleEvent[3];
	public static InstantEvent[] INSTANT_OUTCOMES = new InstantEvent[3];
	
	//public static Weapon[] ALL_WEAPONS = new Weapon[6];
	
	public static BattleEvent tempBattleEvent = new BattleEvent();
	public static InstantEvent tempInstantEvent = new InstantEvent();
	
	public static Color DARK_GREEN = new Color(0,153,0);
	
	public static String musicType = "";
	
	public static final int CHARACTER_LIMIT = 75,
							PARCHMENT_START_X = 510,
							PARCHMENT_START_Y = 90;

	public static final int	CANNON_UPGRADE_MODIFIER = 10, // BASE + MOD * LEVEL = upgrade price
			CATAPULT_UPGRADE_MODIFIER = 5,
			CANNON_UPGRADE_BASE= 50, 
			CATAPULT_UPGRADE_BASE = 30, 
			NEW_CANNON_PRICE = 200, 
			NEW_CATAPULT_PRICE = 150,
			MAX_WEAPONS = 4, //maximum amount of weapons for each set: equipped & stored.
			MAX_HULL = 40, //maximum possible amount of hull hit points
			HULL_COST = 3, //cost per restore 1 hull hit point
			ACCURACY_COST = 10, //cost per increase of 1 accuracy
			EVASION_COST = 30, // cost per increase of 1 evasion
			RATIONS_COST = 5, // cost of 1 ration
			AMMUNITION_COST = 10, //cost of 1 ammunition
			SELLING_WEIGHT = 2, //price / SELLING_WEIGHT = selling price
			MAX_STOCK = 10, //maximum resource stock of ports
			MIN_STOCK = 3, //minimum resource stock of ports
			BASE_GAINED_GOLD = 30, //base gold earned from destroying a ship
			BASE_GAINED_RATIONS = 2, //base rations earned from destroying a ship
			BASE_GAINED_AMMUNITION = 3; //base ammunition earned from destroying a ship
	

	public static void fillLists() throws IOException
	{
		//fillWeaponsList(ALL_WEAPONS);
		
		fillInstantEventsList(NORMAL_INSTANT_EVENTS, new BufferedReader(new FileReader("res/databases/NORMAL_INSTANT_EVENTS_LIST.txt")));
		fillBattleEventsList(NORMAL_BATTLE_EVENTS, new BufferedReader(new FileReader("res/databases/BATTLE_EVENTS_LIST.txt")));
		
		fillNeutralEventsList(ALL_NEUTRAL_EVENTS, new BufferedReader(new FileReader("res/databases/NEUTRAL_EVENTS_LIST.txt")));
		
		fillBattleEventsList(BATTLE_OUTCOMES, new BufferedReader(new FileReader("res/databases/BATTLE_OUTCOMES.txt")));
		
		fillInstantEventsList(INSTANT_OUTCOMES, new BufferedReader(new FileReader("res/databases/INSTANT_OUTCOMES.txt")));
		
	}	

	public static void drawStringWrapped(Graphics g, String text, int yPos)
	{
		String[] parts = text.split(" ");
		String[] eventTextLines = new String[(text.length() / CHARACTER_LIMIT) + 2];
		
		for(int k = 0; k < eventTextLines.length; k++)
			eventTextLines[k] = "";
			
		int c = 0,
			i = 0;
			
		while(c < parts.length)
		{
			if(eventTextLines[i].length() + parts[c].length() < CHARACTER_LIMIT)
			{
				eventTextLines[i] += (parts[c] + " ");
				c++;
			}
			else
				i++;
		}
		
		for(int x = 0; x < eventTextLines.length; x++)
		{
			yPos += 25;
			g.drawString(eventTextLines[x], PARCHMENT_START_X, yPos);
		}
		
		
			
	}
	
	public static String toRomanNumeral(String roman, int number) //converts integers from 1 to 50 to Roman Numerals
	{
		switch(number)
		{
			case 0:
				break;
			case 1:
				roman += "I";
				break;
			case 2:
				roman += "II";
				break;
			case 3:
				roman += "III";
				break;
			case 4:
				roman += "IV";
				break;
			case 5:
				roman += "V";
				break;
			case 6:
				roman += "VI";
				break;
			case 7:
				roman += "VII";
				break;
			case 8:
				roman += "VIII";
				break;
			case 9:
				roman += "IX";
				break;
			case 10:
				roman += "X";
				break;
			default:
				toRomanNumeral(roman, number % 10);
		}
		
		return roman;		
	}
	
//	static void fillWeaponsList(Weapon[] list) throws IOException
//	{
//		BufferedReader reader = new BufferedReader(new FileReader("res/databases/WEAPONS_LIST.txt"));
//		
//		String 	line,
//				newWeaponName,
//				newWeaponClass;
//		
//		int newAccuracy,
//			newShots,
//			newCooldown,
//			newDamage,
//			i = 0;
//				
//		while((line = reader.readLine()) != null)
//		{
//			String[] parts = line.split("_");
//				
//			newWeaponName = parts[0];
//			newWeaponClass = parts[1];
//			newAccuracy = Integer.valueOf(parts[2]);
//			newShots = Integer.valueOf(parts[3]);
//			newCooldown = Integer.valueOf(parts[4]);
//			newDamage = Integer.valueOf(parts[5]);
//		
//	//		list[i] = new Weapon(newWeaponName, newWeaponClass, newAccuracy, newShots, newCooldown, newDamage);
//			i++;	
//		}
//	
//		reader.close();
//	}
	
	static void fillInstantEventsList(InstantEvent[] list, BufferedReader reader ) throws IOException
	{
		String 	line,
				newEventText;
		
		int effects[] = new int[] {0,0,0,0,0,0};

		int i = 0;
					
		while((line = reader.readLine()) != null)
		{
			String[] parts = line.split("_");	
							
			newEventText = parts[0];
			effects = new int[] {Integer.valueOf(parts[1]), Integer.valueOf(parts[2]), Integer.valueOf(parts[3]),
								Integer.valueOf(parts[4]), Integer.valueOf(parts[5]), Integer.valueOf(parts[6])};
			
			list[i] = new InstantEvent(newEventText, effects);
			i++;	
		}
		
		reader.close();
	}
	
	static void fillBattleEventsList(BattleEvent[] list, BufferedReader reader ) throws IOException
	{
		String 	line,
				newEventText;
		
		int effects[];

		int i = 0;
					
		while((line = reader.readLine()) != null)
		{
			String[] parts = line.split("_");	
			
						
			newEventText = parts[0];
			effects = new int[] {Integer.valueOf(parts[1]), Integer.valueOf(parts[2]), Integer.valueOf(parts[3]),
								Integer.valueOf(parts[4]), Integer.valueOf(parts[5]), Integer.valueOf(parts[6])};
			
			list[i] = new BattleEvent(newEventText, effects);
			i++;	
		}
		
		reader.close();
	}
	
	static void fillNeutralEventsList(NeutralEvent[] list, BufferedReader reader ) throws IOException
	{
		//Neutral event._Battle_b_0_Instant_i_0
		//		[0]		  [1]  [2][3]	[4]	[5][6]
		
		String 	line,
				newEventText;
		
		String[] options;
		char[] outcomeType;
		int[] outcomes;

		int i = 0;
					
		while((line = reader.readLine()) != null)
		{
			String[] parts = line.split("_");
			
			newEventText = parts[0];
			options = new String[] {parts[1], parts[4]};
			outcomeType = new char[] {parts[2].charAt(0), parts[5].charAt(0)};
			outcomes = new int[] {Integer.valueOf(parts[3]), Integer.valueOf(parts[6])};			
			
			list[i] = new NeutralEvent(newEventText, options, outcomes, outcomeType);
			i++;	
		}
		
		reader.close();
	}
	
	
	public static final int nextEmptyWeaponSlot(Weapon[] list)
	{
		for(int i = 0; i < list.length; i++)
		{
			if(list[i].weaponName.equals("NULL"))
				return i;
		}
		
		return -1;
	}
	
	
	public static int RANDOM_RANGE(int min, int max)
	//returns a random integer between min and max
	{
		return (int)(Math.random() * ((max - min) + 1)) + min;
	}
}
