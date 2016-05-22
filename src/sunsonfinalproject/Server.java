package sunsonfinalproject;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.BindException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

import javafx.embed.swing.JFXPanel;

public class Server {

	private ServerSocket serverSocket;
	private ConnectionThread connection;
	private List<ConnectionThread> connections = new ArrayList<ConnectionThread>();
	private MainApplet applet;
	private int appletWidth = 1200, appletHeight = 820;
	
	public Server(int portNum) {
		final CountDownLatch latch = new CountDownLatch(1);
		SwingUtilities.invokeLater(new Runnable() {
		    public void run() {
		        new JFXPanel(); // initializes JavaFX environment
		        latch.countDown();
		    }
		});
		try {
			latch.await();
		} catch (InterruptedException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		GUI();
		
		try {
			this.serverSocket = new ServerSocket(portNum);
			System.out.printf("Server starts listening on port %d.\n", portNum);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void GUI(){
		applet = new MainApplet();
		applet.init();
		applet.setSize(appletWidth, appletHeight);
		applet.start();
		applet.setFocusable(true);
		
		JFrame window = new JFrame("Running man");
		window.setContentPane(applet);
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setSize(appletWidth, appletHeight);      
		window.setVisible(true);
	}
	
	public void runForever() {
		System.out.println("Server starts waiting for client.");
		while(true){
			try{
				Socket ToClient = this.serverSocket.accept();
				System.out.println("Get connection from client"
									+ ToClient.getInetAddress()+":"
									+ ToClient.getPort());
				
				connection = new ConnectionThread(ToClient);
				connection.start();
				this.connections.add(connection);
				
			}catch(BindException e){
				e.printStackTrace();
			}catch(IOException e){
				e.printStackTrace();
			}
		}
	}
	
	private void broadcast(String message) {
		for (ConnectionThread connection: connections) {
			connection.sendMessage(message);
		}
	}
	
	// Define an inner class (class name should be ConnectionThread)
	class ConnectionThread extends Thread{
		private PrintWriter writer;
		private BufferedReader reader;
		private Socket socket;
		private Character character;
		private int lastShake=0;
		
		public ConnectionThread(Socket socket){
			this.socket = socket;
			character = applet.newCharacter();
			try{
				this.reader = new BufferedReader(new InputStreamReader(this.socket.getInputStream()));
				this.writer = new PrintWriter(new OutputStreamWriter(this.socket.getOutputStream()));
			}catch(IOException e){
				e.printStackTrace();
			}
		}
		
		public void run(){
			while(true){
				try{
					String line = this.reader.readLine();
					character.diff = Integer.parseInt(line) - lastShake;
					lastShake = Integer.parseInt(line);
					System.out.println("server:"+ line + " " + character.diff);
					Server.this.broadcast(line);
				}
				catch(IOException e){
					
					e.printStackTrace();
				}
			}
		}
		
		public void sendMessage(String msg){
			this.writer.println(msg);
			this.writer.flush();
		}
	}
	
	public static void main(String[] args) {
		
		Server server = new Server(8888);
		server.runForever();
	}

}
