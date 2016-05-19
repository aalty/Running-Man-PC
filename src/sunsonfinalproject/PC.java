package sunsonfinalproject;

import javax.swing.JFrame;
public class PC {
	public PC(){
		MainApplet applet = new MainApplet();
		applet.init();
		applet.start();
		applet.setFocusable(true);
		
		JFrame window = new JFrame("Running man");
		window.setContentPane(applet);
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setSize(1000, 820);      
		window.setVisible(true);
	}
}
