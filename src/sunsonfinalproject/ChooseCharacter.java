package sunsonfinalproject;

import java.util.Random;

import processing.core.PApplet;

public class ChooseCharacter {
	private PApplet parent;
	private int[] rectColor;
	private int selectIndex;
	private float[] selectRect;
	private boolean select=false;
	
	public ChooseCharacter(PApplet parent, float x, float y){
		Random ran = new Random();
		this.parent = parent;
		
		//color initial
		this.rectColor = new int[3];
		this.rectColor[0] = ran.nextInt(255);	//R
		this.rectColor[1] = ran.nextInt(255);	//G
		this.rectColor[2] = ran.nextInt(255);	//B
		
		//position initial
		this.selectRect = new float[2];
		this.selectRect[0] = x;
		this.selectRect[1] = y;		
	}
	
	public void choose(String direction){
		if(this.select == false){
			if (direction.equals("left")) {
				int temp = selectIndex - 1;
				temp = temp >= 0 ? temp : temp + 15;
				setSelectIndex(temp);
			}
			else if (direction.equals("up")) {
				int temp = selectIndex - 5;
				temp = temp >= 0 ? temp : temp + 15;
				setSelectIndex(temp);
			}
			else if (direction.equals("right")) {
				int temp = selectIndex + 1;
				temp = temp < 15 ? temp : temp - 15;
				setSelectIndex(temp);
			}
			else if (direction.equals("down")) {
				int temp = selectIndex + 5;
				temp = temp < 15 ? temp : temp - 15;
				setSelectIndex(temp);
			}
			else if (direction.equals("select")){
				this.select = true;
			}
		}
	}
	
	public void display(){
		this.parent.fill(rectColor[0], rectColor[1], rectColor[2]);
		this.parent.stroke(rectColor[0], rectColor[1], rectColor[2]);
		this.parent.rect(selectRect[0],selectRect[1],140,140);
	}
	
	public void setSelectIndex(int selectIndex){
		this.selectIndex = selectIndex;
		selectRect[0] = 40 + 250 * (selectIndex % 5) - 10;
		selectRect[1] = 25 + 250 * (selectIndex / 5) - 10;
	}
	
	public boolean getSelect(){
		return this.select;
	}
	
	public int getSelectIndex(){
		return this.selectIndex;
	}
}
