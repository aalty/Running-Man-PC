package choosechar;

import processing.core.PApplet;
import processing.core.PImage;

public class CharacterApplet extends PApplet {

	private PImage[] heros = new PImage[9];
	private float[] selectRect = new float[2];
	private int selectIndex = 0;
	
	public void setup(){
		setSize(1200,670);
		selectRect[0] = 30;
		selectRect[1] = 15;
		smooth();
		background(0);
		heros[0] = loadImage("pic/Amumu.png");
		heros[1] = loadImage("pic/draven.png");
		heros[2] = loadImage("pic/garen.png");
		heros[3] = loadImage("pic/jinx.png");
		heros[4] = loadImage("pic/nami.png");
		heros[5] = loadImage("pic/shen.png");
		heros[6] = loadImage("pic/soraka.png");
		heros[7] = loadImage("pic/Talon.png");
		heros[8] = loadImage("pic/vladimir.png");
	}
	
	public void draw(){
		background(255);
		fill(75);
		rect(selectRect[0],selectRect[1],140,140);
		for(int i = 0; i < 9; i++){
			image(heros[i],40+500*(i%3),25+ 250*(i/3));
		}
	}
	
	public int getSelectIndex(){
		return this.selectIndex;
	}
	public void setSelectIndex(int selectIndex){
		this.selectIndex = selectIndex;
		selectRect[0] = 40 + 500 * (selectIndex % 3) - 10;
		selectRect[1] = 25 + 250 * (selectIndex / 3) - 10;
	}
	
	public void keyPressed(){
		if (keyCode == 37) {
			int temp = selectIndex - 1;
			temp = temp >= 0 ? temp : temp + 9;
			setSelectIndex(temp);
		}
		else if (keyCode == 38) {
			int temp = selectIndex - 3;
			temp = temp >= 0 ? temp : temp + 9;
			setSelectIndex(temp);
		}
		else if (keyCode == 39) {
			int temp = selectIndex + 1;
			temp = temp < 9 ? temp : temp - 9;
			setSelectIndex(temp);
		}
		else if (keyCode == 40) {
			int temp = selectIndex + 3;
			temp = temp < 9 ? temp : temp - 9;
			setSelectIndex(temp);
		}
	}
}
