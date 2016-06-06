package sunsonfinalproject;
import java.util.ArrayList;

import processing.core.PApplet;
import processing.core.PImage;

enum gameState{
	START, WAITCONNECT, CHOOSECHAR, WAITOTHERS ,PLAY, END
}

@SuppressWarnings("serial")
public class MainApplet extends PApplet {
	PImage field;
	private ArrayList<Character> characters; 
	private ArrayList<ChooseCharacter> selectRects;
	public static PImage[] heros = new PImage[17];
	public static PImage[] endheros = new PImage[17];
	private GameMusicPlayer gameMusicPlayer;

	private String IP, port;
	private int rectCnt=0;
	public static boolean countdown=false;
	public static gameState currentGameState;
	public WaitConnect waitConnectPage;
	public int end_num=0;
	public int player_num=0;
	public int appletUpY, appletMidY, appletDownY, appletLeftCircleCenterY, appletRightCircleCenterY, 
			   appletCircleR, appletLeftX, appletRightX, appletFirstPathStartY;
	public static int  wait = 10000, tick = 0;
	private int time = millis();
	
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
		appletLeftX = 280;
		appletRightX = 904;
		appletLeftCircleCenterY = 234;
		appletRightCircleCenterY = 517;
		appletFirstPathStartY = 650;
		appletUpY = 0;
		appletDownY = this.height;
		appletMidY = 300;
		appletCircleR = 120;
		
		time = millis();
		loadCharacters();
		loadendheros();
		smooth();
	}
	
	//load characters picture
	public void loadCharacters(){
		for(int i=0; i<17; i++){
			heros[i] = loadImage("pic/characters" + i + ".png");
			heros[i].resize(120, 120);
		}
	}
	public void loadendheros(){
		for(int i=0; i<17; i++){
			endheros[i] = loadImage("pic/characters" + i + ".png");
			endheros[i].resize(250, 250);
		}
	}	
	
	public void playAgain(){
		end_num=0;
		for(Character character : characters)
			character.playAgain();
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
		
		else if(currentGameState == gameState.END){
			endPage();
		}
		
		
		
//		textSize(45);
//		text("BULLSHIT", 550, 410);
//		fill(0);
	}
	
	public void endPage(){
		field = loadImage("pic/win.jpg");
		image(field, 0, 0, width, height);
		for(Character character : characters){
			
			if(character.set_score==1){character.midX=440; character.midY=200;}
			else if(character.set_score==2){character.midX=160; character.midY=255;}
			else if(character.set_score==3){character.midX=700; character.midY=300;}
			else if(character.set_score==4){character.midX=950; character.midY=440;}
			character.end_play();
		}
	}
	
	public void playPage(){
		//Background
		field = loadImage("pic/bg.jpeg");
		
		image(field, 0, 0, width, height);
		System.out.println("player num: "+player_num);
		//Character move
		for(Character character : characters){
			character.display();
			if(end_num==player_num-1&&player_num!=1&&character.set_score==0){
				character.set_score=player_num;
				end_num++;
				break;
			}
			else if(character.winFlag==2&&character.set_score==0){
				end_num++;
				character.set_score=end_num;
				//System.out.println(end_num "+end_num);
			}
		}
		
		if(millis() - time >= wait){
				tick ++;
			time = millis();
		}
		
		if(tick > 0){
			System.out.println("TICK "+ tick);
			if(tick<5)
				countdown=true;
			textSize(300);
			fill(255);
			if(tick == 1){
				text("3", 550, 400);
				wait=1000;
			}
			else if(tick == 2) {
				text("2", 550, 400);
				wait=1000;
			}
			else if(tick == 3) {
				text("1", 550, 400);
				wait=1000;
			}
			else if(tick == 4) {
				text("start!",250,400);
				wait=1000;
			}
			else
				countdown=false;
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
		tmp = new ChooseCharacter(this, 680, 60, rectCnt);
		this.rectCnt++;
		selectRects.add(tmp);
		return tmp;
	}
	
	public Character newCharacter(int player){
		Character tmp = new Character(this, heros[this.selectRects.get(player).getSelectIndex()], player,this.selectRects.get(player).getSelectIndex());
		characters.add(tmp);
		return tmp;
	}
	
}
