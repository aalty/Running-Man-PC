package sunsonfinalproject;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.net.ConnectException;

import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;


public class ChatClient extends JFrame {

	private String destinationIPAddr;
	private int destinationPortNum;
	private Socket socket;
	private PrintWriter writer;
	private ClientThread connection;
	private BufferedReader reader;
	
	private String nickname;
	private JTextArea textArea;
	private JTextField textField;
	
	public ChatClient() {
		
		this.setLayout(new BoxLayout(this.getContentPane(), BoxLayout.PAGE_AXIS));
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		// Initialize textArea
		this.textArea = new JTextArea();
		this.textArea.setEditable(false);
		this.textArea.setPreferredSize(new Dimension(500,550));
		JScrollPane scrollPane = new JScrollPane(this.textArea);
	    this.add(scrollPane);
	    
	    // Initialize textField
	    this.textField = new JTextField();
	    this.textField.setPreferredSize(new Dimension(500,40));
	    this.textField.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				ChatClient.this.sendMessage(ChatClient.this.textField.getText());
			}
	    	
	    });
	    this.add(this.textField);
	    
	    this.pack();
	    
	    // Ask for nickname before showing client window
	    this.nickname = JOptionPane.showInputDialog("Nickname", "Unknown");
		this.welcome(this.nickname);
		
		this.setVisible(true);
		
//		System.out.println(SwingUtilities.isEventDispatchThread());
	}
	
	public ChatClient(String IPAddress, int portNum) {
		this();
		
		this.destinationIPAddr = IPAddress;
		this.destinationPortNum = portNum;
	}
	
	private void welcome(final String nickname) {

		SwingUtilities.invokeLater(new Runnable(){

			@Override
			public void run() {
//				System.out.println(SwingUtilities.isEventDispatchThread());
				StringBuilder sBuilder = new StringBuilder("**[ Welcome ");
				sBuilder.append(nickname).append("! ]\n");
				ChatClient.this.textArea.append(sBuilder.toString());
			}
			
		});
	}
	
	private void sendMessage(String message) {
//		System.out.println(SwingUtilities.isEventDispatchThread());
		StringBuilder sBuilder = new StringBuilder(this.nickname);
		sBuilder.append(": ").append(message);
		this.writer.println(sBuilder.toString());
		this.writer.flush();
		this.textField.setText("");
	}

	public ChatClient setIPAddress(String IPAddress) {
		this.destinationIPAddr = IPAddress;
		return this;
	}
	
	public ChatClient setPort(int portNum) {
		this.destinationPortNum = portNum;
		return this;
	}
	
	public void connect() {
		// Create socket & thread, remember to start your thread
		try{
			this.socket = new Socket(this.destinationIPAddr, this.destinationPortNum);
			this.writer = new PrintWriter(new OutputStreamWriter(this.socket.getOutputStream()));
			this.reader = new BufferedReader(new InputStreamReader(this.socket.getInputStream()));
			this.connection = new ClientThread(reader);
			this.connection.start();
		}
		catch(java.rmi.UnknownHostException e){
			e.printStackTrace();
		}
		catch(ConnectException e){
			e.printStackTrace();
		}
		catch(IOException e){
			e.printStackTrace();
		}
	}
	
	private void addLine(final String message) {
		SwingUtilities.invokeLater(new Runnable(){

			@Override
			public void run() {
				ChatClient.this.textArea.append(message+"\n"); 
			}
			
		});
	}
	
	// Define an inner class for handling message reading
	class ClientThread extends Thread{
		private BufferedReader reader;
		public ClientThread(BufferedReader reader){
			this.reader = reader;
		}
		public void run(){
			while(true){
				try{
					String line = this.reader.readLine();
					System.out.println("client:"+line);
					ChatClient.this.addLine(line);
				}
				catch(IOException e){
					e.printStackTrace();
				}
			}
		}
	}

	
	public static void main(String[] args) {
		
		ChatClient client = new ChatClient();
		client.setIPAddress("127.0.0.1").setPort(8000).connect();
		
		/* Equivalent of the above*/
		// ChatClient client = new ChatClient("127.0.0.1", 8000);
		// client.connect();
	}

}
