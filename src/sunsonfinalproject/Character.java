package sunsonfinalproject;

import java.util.ArrayList;

import processing.core.PApplet;
import processing.core.PConstants;
import processing.core.PImage;


public class Character {

	private MainApplet parent;
	private PImage img;
	private ArrayList<Character> targets = new ArrayList<Character>();
	private int enterRightCircle, enterLeftCircle;
	private int borderOffset = 0;
	public int location=0, diff=0;
	public float rightRadius, leftRadius, midX, midY;
	public int winFlag = 0;
	public int bomb = 0;
	public int set_score=0;
	private int playerIndex;
	private float angle1=PConstants.PI * 3/2;
	private float angle2=PConstants.PI * 3/2;
	private int imgIndex;
	
	/*
	 * Store these variables when instance created.
	 */
	public Character(MainApplet parent, PImage image, int playerIndex,int imgIndex){
		this.parent = parent;
		this.img = image;
		this.img.resize(100, 100);
		this.playerIndex=playerIndex;
		this.imgIndex = imgIndex;
		this.enterRightCircle = this.enterLeftCircle = 0;
		this.midX = this.parent.appletLeftX - img.width;
		this.midY = (float) (this.parent.appletFirstPathStartY + this.playerIndex * 40 - 0.5 * this.img.height);
	}
	
	public void playAgain(){
		
		this.winFlag=0;
		this.bomb=0;
		this.set_score=0;
		this.img.resize(100, 100);
		this.angle1=PConstants.PI*3/2;
		this.angle2=PConstants.PI*3/2;
		this.midX = this.parent.appletLeftX - img.width;
		this.midY = (float) (this.parent.appletFirstPathStartY + playerIndex * 40 - 0.5 * this.img.height);
		this.enterRightCircle = this.enterLeftCircle = 0;
	}
	
	public void forward(){
		if(winFlag == 2){} //win, don't run
		else if(this.midX < parent.appletRightX && this.midY > parent.appletRightCircleCenterY && enterLeftCircle == 0){
			//character in the below run path
			this.midX += 20;
			if(this.midX >= parent.appletRightX) this.midX = parent.appletRightX;
			System.out.println("下："+ this.midX + " " + this.midY);
		}
		else if(this.midX >= parent.appletRightX && this.enterRightCircle < 2){
			if(enterRightCircle == 0){
				this.rightRadius = (float) (this.midY - parent.appletRightCircleCenterY);
				enterRightCircle ++;
			}
			
			midX = parent.appletRightX + this.rightRadius * PApplet.cos(this.angle1);
			midY = parent.appletRightCircleCenterY - this.rightRadius * PApplet.sin(this.angle1);
			this.angle1 += PConstants.PI/16;
		
			
			if(midX <= parent.appletRightX && midY < parent.appletRightCircleCenterY && enterRightCircle > 0){
				this.midX = parent.appletRightX;
				System.out.println("End right circle: R " + this.rightRadius + " R' " + (parent.appletRightCircleCenterY - this.midY));
				System.out.println("End right circle: A " + (this.angle1/PConstants.PI*180));
				enterRightCircle ++;
			}
			System.out.println("下右中："+ (this.midX - img.width/2)  + " " + (this.midY - img.height/2));
		}
		else if(this.midX > parent.appletLeftX && this.midY < parent.appletRightCircleCenterY && this.midY > parent.appletLeftCircleCenterY){
			this.midX -= 20;
			if(this.midX <= parent.appletLeftX) this.midX = parent.appletLeftX;
			System.out.println("中："+ (this.midX - img.width/2)  + " " + (this.midY - img.height/2));
		}
		else if(this.midX <= parent.appletLeftX && this.enterLeftCircle < 2){
			if(enterLeftCircle == 0) {
				this.leftRadius = this.midY - parent.appletLeftCircleCenterY;
				enterLeftCircle ++;
			}
			
//			if(this.angle2 > )
			
			
			
			int cx = parent.appletLeftX;
			int cy = parent.appletLeftCircleCenterY;
			
			this.midX = cx + this.leftRadius * PApplet.cos(this.angle2);
			this.midY = cy - this.leftRadius * PApplet.sin(this.angle2);
			this.angle2 -=PConstants.PI/16;
			
			if(midX >= cx && midY < cy && enterLeftCircle > 0){
				this.midX = parent.appletLeftX;
				enterLeftCircle ++;
			}
			System.out.println("中左上：" + (this.midX - img.width/2)  + " " + (this.midY - img.height/2));
		}
		else if(this.midX < parent.appletRightX && this.midY < parent.appletLeftCircleCenterY){
			this.midX += 20;
			System.out.println("上：" + (this.midX - img.width/2)  + " " + (this.midY - img.height/2));
		}
		else if(enterLeftCircle == 2 && this.midX >= parent.appletRightX){
			winFlag = 2;
		}
		else{
			System.out.println("沒跑道：" + (this.midX - img.width/2)  + " " + (this.midY - img.height/2));
		}
	}
	
	public void end_play(){
		this.img= MainApplet.endheros[this.imgIndex];
		//this.img.resize(250, 250);
		
		//this.parent.image(this.img, midX - img.width/4, midY - img.height/4);
		this.parent.image(this.img, midX, midY);
	}
	
	

	/*
	 * Use display() to draw the character on the sketch.
	 */
	public void display(){	
		this.img.resize(100, 100);
		if(bomb == 1){
			this.parent.tint(255,0,0);//red
		}
		//System.out.println("Display Char: " + x + " " + headY);
		//this.parent.image(this.img, midX, midY);
		this.parent.image(this.img, midX - img.width/2, midY - img.height/2);
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
