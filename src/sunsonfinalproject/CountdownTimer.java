package sunsonfinalproject;
import java.awt.Color;
import java.awt.Container; 
import java.awt.FlowLayout;
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
    
	public CountdownTimer() { 
		initComponents(); 
		Timer tmr; 
		tmr = new Timer(1000,this); 
		this.tmr = tmr; 
		setVisible(true); 
		} 
	private void initComponents(){ 
		
	   
		
		jtfTime = new JTextField("3"); 
		jtfTime.setHorizontalAlignment(JTextField.CENTER);
		btn = new JButton("倒數"); 
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
		btn.setSize(30,30);
		
		this.add(panel); 
		btn.addActionListener(this); 
		} 


	@Override
	public void actionPerformed(ActionEvent ae) {
		// TODO Auto-generated method stub
	
		if(ae.getSource() == btn){ 
			jtfTime.setText("3");
			
			tmr.start(); 
			}
		else { 
			int t; 

			t = Integer.parseInt(jtfTime.getText()); 
			t--; 
			jtfTime.setText(""+t); 
			if (t<1){
				 
				jtfTime.setText("start!");
				System.out.println("時間到");
				tmr.stop(); 
				
				
				jtfTime.setVisible(false); 
				
			}
		
		}
			
		
	}
	public static void main(String[] args) { 
		// TODO 自動產生方法 Stub 
		new CountdownTimer(); 

		} 

}
