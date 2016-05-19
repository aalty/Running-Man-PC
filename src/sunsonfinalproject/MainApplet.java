package sunsonfinalproject;

import java.util.ArrayList;
import java.util.Random;

import processing.core.PApplet;
import processing.core.PImage;
import processing.data.JSONArray;
import processing.data.JSONObject;

@SuppressWarnings("serial")
public class MainApplet extends PApplet{

	PImage field;
	private ArrayList<Character> characters; 
	private int startX = 900, startY = 500;
	
	public void setup(){
		size(1000, 820);
		characters = new ArrayList<Character>();	
		smooth();
	}
	
	public void drawField(){
		field = loadImage("pic/field.jpg");
		image(field, 0, 0, width, height);
	}
	
	/*
	 * Draw the network here.
	 */
	public void draw(){
		background(255);
		drawField();
		for(Character character : characters){
			character.display();
		}
	}
	
	public Character newCharacter(){
		Character tmp = new Character(this, startX, startY);
		characters.add(tmp);
		return tmp;
	}
	
	/*
	 * Change the structure randomly when key pressed.
	 */
	public void keyPressed(){
	}
	
}
