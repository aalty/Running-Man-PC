package sunsonfinalproject;

import java.util.Random;

import processing.core.PApplet;

public class ChooseCharacter {
	private PApplet parent;
	private int[] rectColor;
	private int selectIndex;
	private float[] selectRect;
	private boolean select=false;
	private Random ran;
	
	public ChooseCharacter(PApplet parent, float x, float y, int cnt){
		this.parent = parent;
		
		//color initial
		this.rectColor = new int[3];
		if(cnt == 0){ //Red
			this.rectColor[0] = 255;	//R
			this.rectColor[1] = 0;		//G
			this.rectColor[2] = 0;		//B
		}
		else if(cnt == 1){ //Green
			this.rectColor[0] = 0;
			this.rectColor[1] = 255;
			this.rectColor[2] = 0;
		}
		else if(cnt == 2){ //Blue
			this.rectColor[0] = 0;
			this.rectColor[1] = 0;
			this.rectColor[2] = 255;
		}
		else if(cnt == 3){ //Cyan
			this.rectColor[0] = 0;
			this.rectColor[1] = 255;
			this.rectColor[2] = 255;
		}
		else{//random
			this.rectColor[0] = ran.nextInt(255);
			this.rectColor[1] = ran.nextInt(255);
			this.rectColor[2] = ran.nextInt(255);
		}
		
		//position initial
		this.selectRect = new float[2];
		this.selectRect[0] = x;
		this.selectRect[1] = y;		
	}
	
	public void choose(String direction){
		if(this.select == false){
			if (direction.equals("left")) {
				int temp = selectIndex - 1;
				temp = temp >= 0 ? temp : temp + 17;
				setSelectIndex(temp);
			}
			else if (direction.equals("up")) {
				int temp;
				if(selectIndex >= 2 && selectIndex <= 4)
					temp = selectIndex - 7;
				else if(selectIndex >= 0 && selectIndex <= 1)
					temp= selectIndex - 2;
				else
					temp= selectIndex - 5;
				temp = temp >= 0 ? temp : temp + 17;
				setSelectIndex(temp);
			}
			else if (direction.equals("right")) {
				int temp = selectIndex + 1;
				temp = temp < 17 ? temp : temp - 17;
				setSelectIndex(temp);
			}
			else if (direction.equals("down")) {
				int temp;
				if(selectIndex >= 12 && selectIndex <= 14)
					temp = selectIndex + 7;
				else if(selectIndex >= 15 && selectIndex <= 16)
					temp= selectIndex + 2;
				else
					temp= selectIndex + 5;
				temp = temp < 17 ? temp : temp - 17;
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
		selectRect[0] = 120 + 190 * ((selectIndex+3) % 5) - 10;
		selectRect[1] = 70 + 190 * ((selectIndex+3) / 5) - 10;
	}
	
	public boolean getSelect(){
		return this.select;
	}
	
	public int getSelectIndex(){
		return this.selectIndex;
	}
	
	public String getColor(){
//		if(player == 0){
//			//red
//			return "#FF0000";
//		}
//		else if(player == 1){
//			//green
//			return "#00FF00";
//		}
//		else if(player == 2){
//			//blue
//			return "#0000FF";
//		}
//		else if(player == 3){
//			//cyan
//			return "#00FFFF";
//		}
		return String.format("#%02X%02X%02X", this.rectColor[0], this.rectColor[1], this.rectColor[2]);
	}
}
