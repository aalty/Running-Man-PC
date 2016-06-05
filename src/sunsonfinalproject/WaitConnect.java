package sunsonfinalproject;

import java.util.ArrayList;

import processing.core.PApplet;
import processing.core.PFont;
import gifAnimation.*;

public class WaitConnect {
	private PApplet parent;
	private String IP, port;
	private Gif waitImg;
	private ArrayList<String> clientIP;
	private PFont font;
	
	public WaitConnect(PApplet parent, String IP, String port){
		this.parent = parent;
		this.IP = IP;
		this.port = port;
		clientIP = new ArrayList<String>();
		// load font
		font = this.parent.createFont("font/a.ttf", 50);
		loadPic();
	}
	
	public void loadPic(){
		waitImg = new Gif(this.parent, "pic/waiting.gif");
		//waitImg.resize(1200, 500);
		waitImg.play();
		
	}
	
	public void sendClientIP(String IP){
		clientIP.add(IP);
	}
	
	public void display(){
		this.parent.image(waitImg, 0, 0);
		/* old version wait
		int space = 100;
		int x = this.parent.width/2;
		int i=0;
		this.parent.background(255);
		
		//Image
		this.parent.image(waitImg, x-waitImg.width, this.parent.height/2-space);
		*/
		
		//Word part
		int space = 50;
		int x = this.parent.width/2;
		int i=0;
		this.parent.textFont(font, 45);
		this.parent.fill(0);
		//this.parent.text("Waiting for players...", x-130, this.parent.height/2);
		this.parent.text("Server IP:     "+ this.IP + "\nServer port: " + this.port, x-220, this.parent.height/2+space);
		//print client statement
		this.parent.textSize(25);
		for(String client : clientIP){
			this.parent.text("Get connection from client: " + client + "\n", 50, 50+i*50);
			i++;
		}
		
	}
}