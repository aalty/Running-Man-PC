package sunsonfinalproject;

import java.util.ArrayList;

import processing.core.PApplet;
import processing.core.PImage;


public class Character {

	public float x, y, radius;
	public int location=0, diff=0;
	private PApplet parent;
	private PImage img;
	private ArrayList<Character> targets = new ArrayList<Character>();
	private int borderOffset = 40;
	private int winFlag=0;
	public int bomb=0;
	
	/*
	 * Store these variables when instance created.
	 */
	public Character(PApplet parent, float x, float y, PImage image){
		this.parent = parent;
		this.x = x;
		this.y = y;
		this.img = image;
	}
	
	public void forward(){
		int rightX = parent.width - this.borderOffset;
		int leftX = this.borderOffset;
		int upY = this.borderOffset;
		int downY = parent.height - this.borderOffset;
		int middleY = 300;
		int startY = 500;
//		int startX = 40;
		System.out.println("out " + this.x + " " + this.y);
		//win
		if(winFlag == 2){
			//don`t run
		}
		//下
		else if(this.x < 1000 && this.y == startY){
			//finish a round
			this.x += 20;
			System.out.println("down " + this.x + " " + this.y);
		}
		//右
		else if(this.x == 1000 && middleY < this.y && this.y <= startY){
			//finish a round
			this.y -= 20;
			System.out.println("right " + this.x + " " + this.y+ " " +middleY);
		}
		//中
		else if(leftX < this.x && this.y == middleY){
			this.x -= 20;
			System.out.println("middle " + this.x + " " + this.y);
		}
		//左
		else if(this.x == leftX && this.y == middleY){
			this.y -= 20;
			System.out.println("left " + this.x + " " + this.y);
		}
		//右
		else if(this.x >= rightX && this.y < downY){
			this.y += 15;
			System.out.println("down " + this.x + " " + this.y);
		}
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
