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

	public int set_score=0;
	private int borderOffset = 0;
	public int winFlag=0;
	
	
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
		int middleY = this.parent.height/2;
		int startY = 500;
//		int startX = 50;
		
		//win
		if(winFlag == 2){
			//don`t run
		}
		//下
		else if(rightX > this.x && this.x > leftX && this.y == startY){
		//else if(this.x > leftX && this.y == startY){
			//finish a round
			if(winFlag == 1){
				winFlag = 2;
			}
			this.x += 20;
			
			System.out.println("下："+this.x + " " + this.y + " " + rightX);
		}
		//右
		else if(rightX > this.x && this.x > leftX && this.y > middleY){
			this.y -= 15;
			winFlag = 1;
			System.out.println("右："+this.x + " " + this.y);
		}
		//中
		else if(this.x <= leftX && this.y < downY && this.y >= middleY){
			this.y -= 15;
			winFlag = 1;
			System.out.println("中："+this.x + " " + this.y);
		}
		//撌�
		else if(this.x <= leftX && this.y > upY){
			this.y -= 15;
			System.out.println("左："+this.x + " " + this.y);
		}
		//銝�
		else if(this.x < rightX && this.y <= upY){
			this.x += 20;
			System.out.println("上："+this.x + " " + this.y);
		}
		//�
		//else if(this.x >= rightX && this.y < downY){
		if(this.x >= 600){
			this.y += 15;
			winFlag = 2;
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
		this.parent.image(this.img, x, y);
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
