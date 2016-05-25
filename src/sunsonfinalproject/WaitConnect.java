package sunsonfinalproject;

import processing.core.PApplet;
import gifAnimation.*;

public class WaitConnect {
	private PApplet parent;
	private String IP, port;
	private Gif waitImg;
	
	public WaitConnect(PApplet parent, String IP, String port){
		this.parent = parent;
		this.IP = IP;
		this.port = port;
		loadPic();
	}
	
	public void loadPic(){
		waitImg = new Gif(this.parent, "pic/wait.gif");
		waitImg.play();
	}
	
	public void display(){
		int space = 100;
		int x = this.parent.width/2;
		this.parent.background(255);
		
		//Image
		this.parent.image(waitImg, x-waitImg.width, this.parent.height/2-space);
		
		//Word part
		this.parent.textSize(26);
		this.parent.fill(0);
		this.parent.text("Waiting for players...", x-130, this.parent.height/2);
		this.parent.text("Server IP:     "+ this.IP + "\nServer port: " + this.port, x-160, this.parent.height/2+space);
	}
}