package sunsonfinalproject;

import java.util.ArrayList;

import processing.core.PApplet;


public class Character {

	public float x, y, radius;
	private String name;
	private PApplet parent;
	private ArrayList<Character> targets = new ArrayList<Character>();
	
	/*
	 * Store these variables when instance created.
	 */
	public Character(PApplet parent, String name, float x, float y){
		this.parent = parent;
		this.name = name;
		this.x = x;
		this.y = y;
	}
	
	public void forward(){
		if(this.y == 500 && this.x > 250){
			this.x -= 10;
		}
		else if(this.y >= 280 && this.x <= 250 && this.x > 45){
			this.x -= 5;
			this.y -= 5;
			System.out.print(this.x + " " + this.y + "\n");
		}
		else if(this.y <= 300 && this.x >= 40){
			this.x += 5;
			this.y -= 5;
			
		}
	}
	
	/*
	 * Use display() to draw the character on the sketch.
	 */
	public void display(){	
		this.parent.fill(0,255,204);
		this.parent.rect(x, y, 50, 50);
		this.parent.fill(0);
		this.parent.text(this.name,x+10,y+25);
	}
	
	/*
	 * Add the target to the array list when loading file.
	 */
	public void addTarget(Character target){
		targets.add(target);
	}
	
	public ArrayList<Character> getTargets(){
		return this.targets;
	}
}
