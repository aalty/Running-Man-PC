package sunsonfinalproject;

import java.util.ArrayList;

import processing.core.PApplet;
import processing.core.PImage;


public class Character {

	private MainApplet parent;
	private PImage img;
	private ArrayList<Character> targets = new ArrayList<Character>();
	private int rightRadius, leftRadius, enterRightCircle = 0, enterLeftCircle = 0;
	private int borderOffset = 0;
	public int location=0, diff=0;
	public float x, y;
	public int winFlag=0;
	public int bomb=0;
	public int set_score=0;
	
	/*
	 * Store these variables when instance created.
	 */
	public Character(MainApplet parent, PImage image, int playerIndex){
		this.parent = parent;
		this.x = this.parent.appletLeftX;
		this.y = this.parent.appletStartY + playerIndex * 50;
		this.rightRadius = this.parent.appletCircleR + playerIndex * 50;
		this.leftRadius = this.parent.appletCircleR + (3 - playerIndex) * 50;
		this.img = image;
	}
	
	public void forward(){
		if(winFlag == 2){} //win, don't run
		else if(this.x < parent.appletRightX && this.y > parent.appletRightCircleCenterY){
			//character in the below run path
			this.x += 20;
			System.out.println("下："+this.x + " " + this.y + " " + parent.appletRightX);
		}
		else if(this.x >= parent.appletRightX && this.enterRightCircle < 2){
			this.y -= 15;
			winFlag = 1;
			System.out.println("下右中："+this.x + " " + this.y);
		}
		else if(this.x > parent.appletLeftX && this.y < parent.appletRightCircleCenterY && this.y > parent.appletLeftCircleCenterY){
			this.x -= 20;
			System.out.println("中："+this.x + " " + this.y);
		}
		else if(this.x <= parent.appletLeftX && this.enterLeftCircle < 2){
			this.y -= 15;
			System.out.println("中左上："+this.x + " " + this.y);
		}
		else if(this.x < parent.appletRightX && this.y < parent.appletLeftCircleCenterY){
			this.x += 20;
			System.out.println("上："+this.x + " " + this.y);
		}
	}
	
	public void end_play(){
		this.img.resize(250, 250);
		this.parent.image(this.img, x, y);
	}
	
	/*
	 * Use display() to draw the character on the sketch.
	 */
	public void display(){	
		this.img.resize(100, 100);
		if(bomb == 1){
			this.parent.tint(255,0,0);//red
		}
		this.parent.image(this.img, x, y);
		this.parent.noTint();
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
