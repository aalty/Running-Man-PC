package sunsonfinalproject;
import java.awt.Container; 
import java.awt.Font;
import javax.swing.Timer;
import javax.swing.border.EmptyBorder;

import javax.swing.JButton; 
import javax.swing.JFrame; 
import javax.swing.JPanel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener; 
import javax.swing.JTextField; 

public class CountdownTimer extends javax.swing.JFrame implements ActionListener {
	
	private Container container; 
	private JButton btn; 
	private JTextField jtfTime; 
	private Timer tmr; 
    
	public  CountdownTimer(){ 
		
		initComponents(); 
		Timer tmr; 
		tmr = new Timer(1000,this); 
		this.tmr = tmr; 
	 
		 
		} 
	private void initComponents(){ 
		
	   
		jtfTime = new JTextField("3"); 
		jtfTime.setHorizontalAlignment(JTextField.CENTER);
		btn = new JButton("­Ë¼Æ"); 
		Font font = new Font("3",100,150);
		jtfTime.setFont(font);
		setSize(1200, 820); 
		
		this.setResizable(false); 
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
		container = getContentPane(); 
		JPanel panel = new JPanel();
		jtfTime.setBorder(new EmptyBorder(0,0,0,0));
		panel.add(btn); 
		panel.add(jtfTime); 
	
		panel.setLayout(null); 
		jtfTime.setBounds(370,230,400,200);
		btn.setSize(50,50);
		
		this.add(panel); 
		btn.addActionListener(this); 
		setVisible(true);
		} 


	@Override
	public void actionPerformed(ActionEvent ae) {
		int t;
		// TODO Auto-generated method stub
		if(ae.getSource() == btn){ 
			
			jtfTime.setText("3"); 
			tmr.start(); 
			
			}else { 
				 
				
				t = Integer.parseInt(jtfTime.getText()); 
				
				
				t--; 
			
				jtfTime.setText(""+t); 
				
				 if(t<=0){
					 tmr.stop();
					 jtfTime.setVisible(false); 
				 }
			}
		}
	 public static void main(String[] args){
		new CountdownTimer(); 
	 }
	
}


