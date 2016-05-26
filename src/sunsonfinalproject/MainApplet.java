package sunsonfinalproject;

import java.awt.event.KeyEvent;
import java.util.ArrayList;

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
	private gameState currentGameState = gameState.WAITCONNECT;
	private PImage[] heros = new PImage[16];
	private float[] selectRect = new float[2];
	private int selectIndex = 0;
	private GameMusicPlayer gameMusicPlayer = new GameMusicPlayer();
	public WaitConnect waitConnectPage;
	private String IP, port;
	
	public MainApplet(String IP, String port){
		this.IP= IP;
		this.port = port;
	}

	public void setup(){
		waitConnectPage = new WaitConnect(this, this.IP, this.port);
		setupChooseChar();
		setupPlayChar();
		
		smooth();
	}
	
	public void setGameState(gameState state){
		this.currentGameState = state;
	}
	
	public void setupChooseChar(){
		selectRect[0] = 30;
		selectRect[1] = 15;
		for(int i=0; i<16; i++){
			heros[i] = loadImage("pic/characters" + i + ".png");
			heros[i].resize(120, 120);
		}
	}
	
	public void setupPlayChar(){
		characters = new ArrayList<Character>();
	}
	
	public void draw(){
		background(255);
		if(currentGameState == gameState.WAITCONNECT){
			waitConnectPage.display();
		}
		else if(currentGameState == gameState.CHOOSECHAR){
			drawChooseChar();
		}
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
		background(255, 255, 148);
		fill(0, 191, 255);
		stroke(0, 191, 255);
		rect(selectRect[0],selectRect[1],140,140);
		for(int i = 0; i < 16; i++){
			
			image(heros[i],40+250*(i%5),25+ 250*(i/5));
			
		}
	}
	
	public Character newCharacter(){
		Character tmp = new Character(this, startX, startY, heros[this.selectIndex]);
		characters.add(tmp);
		return tmp;
	}
	
	public void chooseCharacter(String direction){
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
			setupPlayChar();
			currentGameState = gameState.PLAY;
			gameMusicPlayer.gameMusicPlay();
		}
	}
	
	public void keyPressed(KeyEvent e){
		if(currentGameState == gameState.WAITCONNECT){
			if (key == ' '){
				currentGameState = gameState.CHOOSECHAR;
				gameMusicPlayer.gameMusicPlay();
			}
		}
		else if(currentGameState == gameState.CHOOSECHAR){
			if (e.getKeyCode() == KeyEvent.VK_LEFT) {
				int temp = selectIndex - 1;
				temp = temp >= 0 ? temp : temp + 15;
				setSelectIndex(temp);
			}
			else if (e.getKeyCode() == KeyEvent.VK_UP) {
				int temp = selectIndex - 5;
				temp = temp >= 0 ? temp : temp + 15;
				setSelectIndex(temp);
			}
			else if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
				int temp = selectIndex + 1;
				temp = temp < 15 ? temp : temp - 15;
				setSelectIndex(temp);
			}
			else if (e.getKeyCode() == KeyEvent.VK_DOWN) {
				int temp = selectIndex + 5;
				temp = temp < 15 ? temp : temp - 15;
				setSelectIndex(temp);
			}
			else if (e.getKeyCode() == KeyEvent.VK_ENTER){
				setupPlayChar();
				currentGameState = gameState.PLAY;
				gameMusicPlayer.gameMusicPlay();
			}
		}
		else if(currentGameState == gameState.PLAY){
			for(int i=0; i<characters.size(); i++){
				if(key == ' '){
					characters.get(i).forward();
				}
			}
		}
	}
	public void setSelectIndex(int selectIndex){
		this.selectIndex = selectIndex;
		selectRect[0] = 40 + 250 * (selectIndex % 5) - 10;
		selectRect[1] = 25 + 250 * (selectIndex / 5) - 10;
	}
	
}
