package sunsonfinalproject;

import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import com.sun.prism.Image;

import processing.core.PApplet;
import processing.core.PImage;

enum gameState{
	START, WAITCONNECT, CHOOSECHAR, PLAY
}

@SuppressWarnings("serial")
public class MainApplet extends PApplet{
	PImage field;
	private ArrayList<Character> characters; 
	private int startX = 900, startY = 500;
	private gameState currentGameState = gameState.CHOOSECHAR;
	private PImage[] heros = new PImage[9];
	private float[] selectRect = new float[2];
	private int selectIndex = 0;
	private GameMusicPlayer gameMusicPlayer = new GameMusicPlayer();

	public void setup(){
		if(currentGameState == gameState.CHOOSECHAR) setupChooseChar();
		else{
			characters = new ArrayList<Character>();
		}
		System.out.println(width + " " + height);
		
		smooth();
	}
	
	public void setupChooseChar(){
		selectRect[0] = 30;
		selectRect[1] = 15;
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
	
	public void setupPlayChar(){
		characters = new ArrayList<Character>();
	}
	
	public void draw(){
		background(255);
		if(currentGameState == gameState.CHOOSECHAR) drawChooseChar();
		else if(currentGameState == gameState.PLAY){
			drawField();
			for(Character character : characters){
				character.display();
			}
		}
	}

	public void drawField(){
		field = loadImage("pic/field.jpg");
		image(field, 0, 0, width, height);
	}
	
	public void drawChooseChar(){
		background(255);
		fill(0, 191, 255);
		stroke(255);
		rect(selectRect[0],selectRect[1],140,140);
		for(int i = 0; i < 9; i++){
			image(heros[i],40+500*(i%3),25+ 250*(i/3));
			
		}
	}
	
	public Character newCharacter(){
		Character tmp = new Character(this, startX, startY, heros[this.selectIndex]);
		characters.add(tmp);
		return tmp;
	}
	
	public void keyPressed(KeyEvent e){
		if(currentGameState == gameState.CHOOSECHAR){
			if (e.getKeyCode() == KeyEvent.VK_LEFT) {
				int temp = selectIndex - 1;
				temp = temp >= 0 ? temp : temp + 9;
				setSelectIndex(temp);
			}
			else if (e.getKeyCode() == KeyEvent.VK_UP) {
				int temp = selectIndex - 3;
				temp = temp >= 0 ? temp : temp + 9;
				setSelectIndex(temp);
			}
			else if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
				int temp = selectIndex + 1;
				temp = temp < 9 ? temp : temp - 9;
				setSelectIndex(temp);
			}
			else if (e.getKeyCode() == KeyEvent.VK_DOWN) {
				int temp = selectIndex + 3;
				temp = temp < 9 ? temp : temp - 9;
				setSelectIndex(temp);
			}
			else if (e.getKeyCode() == KeyEvent.VK_ENTER){
				setupPlayChar();
				currentGameState = gameState.PLAY;
				gameMusicPlayer.gameMusicPlay();
			}
		}
		else{
			for(int i=0; i<characters.size(); i++){
				if(key == ' '){
					characters.get(i).forward();
				}
			}
		}
	}
	public void setSelectIndex(int selectIndex){
		this.selectIndex = selectIndex;
		selectRect[0] = 40 + 500 * (selectIndex % 3) - 10;
		selectRect[1] = 25 + 250 * (selectIndex / 3) - 10;
	}
	
}
