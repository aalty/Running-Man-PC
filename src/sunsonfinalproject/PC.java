package sunsonfinalproject;

import javax.swing.JFrame;
<<<<<<< HEAD:src/sunsonfinalproject/Main.java
public class Main {
	private static int gameWindowWidth = 1200, gameWindowHeight = 820;
	
	public static void main(String [] args){
=======
public class PC {
	public PC(){
>>>>>>> 0e154b2d2f83d0643f8aa6ae0421ae1e217c0470:src/sunsonfinalproject/PC.java
		MainApplet applet = new MainApplet();
		applet.init();
		applet.start();
		applet.setSize(gameWindowWidth, gameWindowHeight);
		applet.setFocusable(true);
		
		JFrame window = new JFrame("Running man");
		window.setContentPane(applet);
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setSize(gameWindowWidth, gameWindowHeight);      
		window.setVisible(true);
	}
}
