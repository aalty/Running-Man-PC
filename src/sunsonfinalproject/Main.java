package sunsonfinalproject;

import javax.swing.JFrame;
public class Main {
	private static int gameWindowWidth = 1200, gameWindowHeight = 820;
	
	public static void main(String [] args){
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
