package choosechar;

import javax.swing.JFrame;


public class Main extends JFrame{
	
	private final static int windowWidth = 1200, windowHeight = 670;
	
	public static void main(String [] args){
		
		MainApplet applet = new MainApplet();
		applet.init();
		applet.start();
		applet.setFocusable(true);
		
		JFrame window = new JFrame("Let's play a game");
		//window.setContentPane(applet);
		window.add(applet);
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setSize(windowWidth, windowHeight);
		window.setVisible(true);
	}
}