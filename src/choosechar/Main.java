package choosechar;

import javax.swing.JFrame;


@SuppressWarnings("serial")
public class Main extends JFrame{
	
	private final static int windowWidth = 1200, windowHeight = 670;
	
	public static void main(String [] args){
		
		CharacterApplet applet = new CharacterApplet();
		applet.init();
		applet.start();
		applet.setFocusable(true);
		
		JFrame window = new JFrame("Let's play a game");
		window.setContentPane(applet);
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setSize(windowWidth, windowHeight);
		window.setVisible(true);
	}
}