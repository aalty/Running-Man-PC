package sunsonfinalproject;

import java.util.ArrayList;
import java.util.Random;

import processing.core.PApplet;
import processing.core.PImage;
import processing.data.JSONArray;
import processing.data.JSONObject;

@SuppressWarnings("serial")
public class MainApplet extends PApplet{

	private String file = "main/resources/data.json";
	JSONObject data;
	JSONArray nodes, links;
	PImage field;
	private ArrayList<Character> characters; 
	private int startX = 900, startY = 500;
	private Character sherry = new Character(this, "Sherry", startX, startY);
	
	public void setup(){
		size(1000, 820);
		characters = new ArrayList<Character>();	
		characters.add(sherry);
		smooth();
	}
	
	public void drawField(){
		field = loadImage("res/pic/field.jpg");
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
	
	/*
	 * Change the structure randomly when key pressed.
	 */
	public void keyPressed(){
		for(int i=0; i<characters.size(); i++){
			if(key == ' '){
				characters.get(i).forward();
			}
		}
	}
	
	/*
	 * Load the data here.
	 */
	private void loadData(){
		int i;
		Random ran = new Random();
		JSONObject tmp;
		
		data = loadJSONObject(file);
		nodes = data.getJSONArray("nodes");
		links = data.getJSONArray("links");
		for(i=0; i<nodes.size(); i++){
			tmp = nodes.getJSONObject(i);
			
			String n = tmp.getString("name");
			float x = ran.nextFloat()*500;
			float y = ran.nextFloat()*500;
			
			characters.add(new Character(this, n, x, y));
		}
		for(i=0; i<links.size(); i++){
			tmp = links.getJSONObject(i);
			int s = tmp.getInt("source");
			int t = tmp.getInt("target");
			
			characters.get(s).addTarget(characters.get(t));
		}
	}
}
