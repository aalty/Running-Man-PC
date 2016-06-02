package sunsonfinalproject;

import java.util.ArrayList;

import processing.core.PApplet;
import processing.core.PConstants;
import processing.core.PImage;


public class Character {

	public float x, y, radius;
	public int location=0, diff=0;
	private PApplet parent;
	private PImage img;
	private ArrayList<Character> targets = new ArrayList<Character>();
	private int borderOffset = 0;
	public int winFlag=0;
	public int bomb=0;
	public int set_score=0;
	private float angle1=PConstants.PI/2;
	private float angle2=PConstants.PI/2;
	
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
		int rightX = parent.width - this.borderOffset - 200;
		int leftX = this.borderOffset + 200;
		int upY = this.borderOffset;
		int downY = parent.height - this.borderOffset;
		int middleY = 300;
		int startY = 500;
		int startX = 50;
		int r = 120;
		int cx = rightX;
		int cy = (middleY+startY)/2;
		
		
		//win
		if(winFlag == 2){
			//don`t run
		}
		
		else if(this.x>=995&&this.y==65)
			winFlag = 2;

		else if(rightX > this.x && this.x > leftX && this.y == startY){
			//finish a round
			/*if(winFlag == 1){
				winFlag = 2;
			}*/
			this.x += 20;
			System.out.println("下："+this.x + " " + this.y + " " + rightX);
		}
		else if(this.x > leftX && this.y > middleY){
			cx = rightX;
			cy = (middleY+startY)/2;
//			this.y -= 15;
			this.x = (int)(cx + r*PApplet.cos(this.angle1));
			this.y = (int)(cy + r*PApplet.sin(this.angle1));
			this.angle1-=PConstants.PI/16;
//			winFlag = 1;
			System.out.println("下中右："+this.x + " " + this.y + " " + r*PApplet.cos(this.angle1) + " " + r*PApplet.sin(this.angle1));
		}
		else if(this.x > leftX && this.y == middleY){
			this.x -= 20;
			System.out.println("中："+this.x + " " + this.y);
		}
		else if(this.x < rightX && this.y > 80){
			cx = leftX;
			cy = (middleY+upY)/2;
//			this.y -= 15;
			this.x = (int)(cx + r*PApplet.cos(this.angle2));
			this.y = (int)(cy + r*PApplet.sin(this.angle2));
			this.angle2+=PConstants.PI/16;
//			winFlag = 1;
			System.out.println("中中上："+this.x + " " + this.y + " " + r*PApplet.cos(this.angle1) + " " + r*PApplet.sin(this.angle2));
		}
		else if(this.x < rightX && this.y > upY){
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
