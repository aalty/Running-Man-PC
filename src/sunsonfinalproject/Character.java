package sunsonfinalproject;

import java.util.ArrayList;

import processing.core.PApplet;


public class Character {

	public float x, y, radius;
	public int location=0, diff=0;
	private String name;
	private PApplet parent;
	private ArrayList<Character> targets = new ArrayList<Character>();
	private int borderOffset = 100;
	private int rightX = parent.width - this.borderOffset;
	private int leftX = this.borderOffset;
	private int upY = this.borderOffset;
	private int downY = parent.height - this.borderOffset;
	
	/*
	 * Store these variables when instance created.
	 */
	public Character(PApplet parent, float x, float y){
		this.parent = parent;
		this.x = x;
		this.y = y;
	}
	
	public void forward(){
		//下半部

		if(this.x > this.leftX && this.y < this.downY){
			this.x -= 20;
			System.out.println("run1:" +this.x +", " + this.y);
		}
		//左彎下半
		else if(this.x <= this.leftX && this.y > this.upY){
//			this.x -= 5;
			this.y -= 5;
			System.out.println("run2:" +this.x +", " + this.y);
		}
		//上半部
		else if(this.x < this.rightX && this.y <= this.upY){
			this.x += 20;
			System.out.println("run:" +this.x +", " + this.y);
		}
		//右彎下半
		else if(this.x >= this.rightX && this.y < this.downY){
//			this.x -= 5;
			this.y += 5;
			System.out.println("run:" +this.x +", " + this.y);
		}
		//win
		else{
				
		}
	}
	
	/*
	 * Use display() to draw the character on the sketch.
	 */
	public void display(){	
		this.parent.fill(0,255,204);
		this.parent.rect(x, y, 50, 50);
//		this.parent.fill(0);
//		this.parent.text(this.name,x+10,y+25);
		while(diff > 0){
			forward();
			diff--;
		}
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
