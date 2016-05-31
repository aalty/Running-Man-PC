package sunsonfinalproject;
import java.util.ArrayList;

import processing.core.PApplet;
import processing.core.PImage;

enum gameState{
	START, WAITCONNECT, CHOOSECHAR, WAITOTHERS ,PLAY
}

@SuppressWarnings("serial")
public class MainApplet extends PApplet{
	PImage field;
	private ArrayList<Character> characters; 
	private ArrayList<ChooseCharacter> selectRects;
	private int startX = 900, startY = 500;
	public gameState currentGameState;
	private PImage[] heros = new PImage[17];
	private GameMusicPlayer gameMusicPlayer;
	public WaitConnect waitConnectPage;
	private String IP, port;
	
	public MainApplet(String IP, String port){
		this.IP= IP;
		this.port = port;
	}

	public void setup(){
		//initialize
		waitConnectPage = new WaitConnect(this, this.IP, this.port);
		gameMusicPlayer = new GameMusicPlayer();
		currentGameState = gameState.WAITCONNECT;
		characters = new ArrayList<Character>();
		selectRects = new ArrayList<ChooseCharacter>();
		
		loadCharacters();
		smooth();
	}
	
	//load characters picture
	public void loadCharacters(){
		for(int i=0; i<17; i++){
			heros[i] = loadImage("pic/characters" + i + ".png");
			heros[i].resize(120, 120);
		}
	}
	
	public void draw(){
		background(255);
		//Wait page
		if(currentGameState == gameState.WAITCONNECT){
			waitConnectPage.display();
		}
		//Select character page
		else if(currentGameState == gameState.CHOOSECHAR){
			gameMusicPlayer.gameMusicPlay();
			chooseCharactersPage();
		}
		//Play page
		else if(currentGameState == gameState.PLAY){
			playPage();
		}
	}
	
	
	public void playPage(){
		//Background
		field = loadImage("pic/bg.jpeg");
		image(field, 0, 0, width, height);
		
		//Character move
		for(Character character : characters){
			character.display();
		}
	}
	
	public void chooseCharactersPage(){
		image(loadImage("pic/select.jpg"),0,0); 
		//background(255, 255, 148);
		//draw select rectangle
		for(ChooseCharacter cc : selectRects){
			cc.display();
		}
		
		//draw characters for selecting
		for(int i = 0; i < 17; i++){
				image(heros[i],120+190*((i+3)%5),70+190*((i+3)/5));
		}
	}
	
	public ChooseCharacter newRect(){
		ChooseCharacter tmp;
		tmp = new ChooseCharacter(this, 680, 60);
		selectRects.add(tmp);
		return tmp;
	}
	
	public Character newCharacter(int player){
		Character tmp = new Character(this, startX, startY, heros[this.selectRects.get(player).getSelectIndex()]);
		characters.add(tmp);
		return tmp;
	}
}
