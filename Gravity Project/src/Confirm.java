import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.Border;

/**
 * @author ldh13/ Lewis Haldenby
 * C03015 - Computer Science Project
 * Gravity Simulator in 3D
 */

public class Confirm extends JFrame {
	
	public Confirm (int confirmScreen){
		
        Container cp = getContentPane();
        cp.setLayout(new BorderLayout());
        
        JPanel confirmPanel = new JPanel();
        confirmPanel.setBackground(Color.black);
        Border whiteLine = BorderFactory.createLineBorder(Color.white, 1);
        confirmPanel.setBorder(whiteLine);
        
        JPanel footer = new JPanel();
        footer.setLayout(new BorderLayout());
        footer.setBackground(Color.black);
        
        if (confirmScreen == 0) {
        	//create confirm screen for creating a solar system
        	
        	JLabel text1 = new JLabel("By clicking \"Accept\" you accept responsibility for creating the solar");
        	JLabel text2 = new JLabel("system with only the planets that you have clicked \"Add Planets\" for. This");
        	JLabel text3 = new JLabel("means anything still in the planet creation will be lost. To continue click");
        	JLabel text4 = new JLabel("\"Create\" and continue to the simulation otherwise keep changing planets.");
        	text1.setFont(new java.awt.Font("Arial", Font.PLAIN, 16));
        	text1.setForeground(Color.white);
        	confirmPanel.add(text1);
        	text2.setFont(new java.awt.Font("Arial", Font.PLAIN, 16));
        	text2.setForeground(Color.white);
        	confirmPanel.add(text2);
        	text3.setFont(new java.awt.Font("Arial", Font.PLAIN, 16));
        	text3.setForeground(Color.white);
        	confirmPanel.add(text3);
        	text4.setFont(new java.awt.Font("Arial", Font.PLAIN, 16));
        	text4.setForeground(Color.white);
        	confirmPanel.add(text4);
        	
        	setTitle("Confirm Create");
        	
        	Button yes = new Button("Accept");
        	yes.setFont(new java.awt.Font("Arial", Font.BOLD, 18));
            yes.setForeground(Color.white);
            yes.setPreferredSize(new Dimension(530,30));
            
            yes.addActionListener(new ActionListener() {
            	
            	@Override
            	public void actionPerformed(ActionEvent e) {
            		Context.setConfirmCreate(true);
            		dispose();
            		
            	}
            	
            });
 
            footer.add(yes, BorderLayout.LINE_START);
            
            
        } else if (confirmScreen == 1) {
        	//create confirm screen for deleting a solar system
        	
        	JLabel text1 = new JLabel("By clicking \"Accept\" you accept responsibility for deleting the solar");
        	JLabel text2 = new JLabel("system next to the \"Delete\" button you just clicked. This means anything");
        	JLabel text3 = new JLabel("in that solar system file will be  lost. To continue click \"Delete\" otherwise");
        	JLabel text4 = new JLabel("click on a solar system to load it or go back to the main menu.");
        	text1.setFont(new java.awt.Font("Arial", Font.PLAIN, 16));
        	text1.setForeground(Color.white);
        	confirmPanel.add(text1);
        	text2.setFont(new java.awt.Font("Arial", Font.PLAIN, 16));
        	text2.setForeground(Color.white);
        	confirmPanel.add(text2);
        	text3.setFont(new java.awt.Font("Arial", Font.PLAIN, 16));
        	text3.setForeground(Color.white);
        	confirmPanel.add(text3);
        	text4.setFont(new java.awt.Font("Arial", Font.PLAIN, 16));
        	text4.setForeground(Color.white);
        	confirmPanel.add(text4);
        	
        	setTitle("Confirm Delete");
        	
        	Button yes = new Button("Accept");
        	yes.setFont(new java.awt.Font("Arial", Font.BOLD, 18));
            yes.setForeground(Color.white);
            yes.setPreferredSize(new Dimension(530,30));
            
            yes.addActionListener(new ActionListener() {
            	
            	@Override
            	public void actionPerformed(ActionEvent e) {
            		Context.setConfirmDelete(true);
            		dispose();
            		
            	}
            	
            });
            footer.add(yes, BorderLayout.LINE_START);
            
        } else {
        	
        }
        
        cp.add(footer, BorderLayout.PAGE_END);
        cp.add(confirmPanel, BorderLayout.CENTER);
        
        
        
        Dimension screenDim = Toolkit.getDefaultToolkit().getScreenSize();
        setLocation(screenDim.width/3, screenDim.height/3);
        
        setSize(530,200);
        setVisible(true);
        setResizable(false);
	}
}