import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;

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

public class HelpScreen extends JFrame {
	
	public HelpScreen (int helpScreen){
		
        Container cp = getContentPane();
        cp.setLayout(new BorderLayout());
        
        JPanel hintPanel = new JPanel();
        hintPanel.setBackground(Color.black);
        Border whiteLine = BorderFactory.createLineBorder(Color.white, 1);
        hintPanel.setBorder(whiteLine);
        
        //display help screen for creation
        if (helpScreen == 0) {
        	
        	JLabel text1 = new JLabel("To create a solar system, you must make sure that the name of it hasn't");
        	JLabel text2 = new JLabel("already been used. There must also be at least one planet in the solar");
        	JLabel text3 = new JLabel("system already added to the planet count. Below are some sample values:");
        	JLabel text4 = new JLabel("  -Earth Size: 12755.66km               ");
        	JLabel text5 = new JLabel("-Earth Distance from Sun: 150e6km     ");
        	JLabel text6 = new JLabel("-Earth Mass: 5.972 e24kg             ");
        	text1.setFont(new java.awt.Font("Arial", Font.PLAIN, 16));
        	text1.setForeground(Color.white);
        	hintPanel.add(text1);
        	text2.setFont(new java.awt.Font("Arial", Font.PLAIN, 16));
        	text2.setForeground(Color.white);
        	hintPanel.add(text2);
        	text3.setFont(new java.awt.Font("Arial", Font.PLAIN, 16));
        	text3.setForeground(Color.white);
        	hintPanel.add(text3);
        	text4.setFont(new java.awt.Font("Arial", Font.BOLD, 16));
        	text4.setForeground(Color.white);
        	hintPanel.add(text4);
        	text5.setFont(new java.awt.Font("Arial", Font.BOLD, 16));
        	text5.setForeground(Color.white);
        	hintPanel.add(text5);
        	text6.setFont(new java.awt.Font("Arial", Font.BOLD, 16));
        	text6.setForeground(Color.white);
        	hintPanel.add(text6);
        
        	//display help for simulation
        } else if (helpScreen == 1) {
        	JLabel text1 = new JLabel("-To move the view of the universe: (Right Click and Drag)");
        	JLabel text2 = new JLabel("-To zoom in and out of the universe: (Alt-Left Click and Drag)");
        	JLabel text3 = new JLabel("-To zoom in and out of the universe: (Third-Mouse Click and Drag)");
        	JLabel text4 = new JLabel("-To rotate the universe: (Left Click and Drag)");
        	JLabel text5 = new JLabel("-To get details of the planets click on them");
        	JLabel text6 = new JLabel("-To swap between planet information (Press 'q' and 'e')");
        	text1.setFont(new java.awt.Font("Arial", Font.BOLD, 16));
        	text1.setForeground(Color.white);
        	hintPanel.add(text1);
        	text2.setFont(new java.awt.Font("Arial", Font.BOLD, 16));
        	text2.setForeground(Color.white);
        	hintPanel.add(text2);
        	text3.setFont(new java.awt.Font("Arial", Font.BOLD, 16));
        	text3.setForeground(Color.white);
        	hintPanel.add(text3);
        	text4.setFont(new java.awt.Font("Arial", Font.BOLD, 16));
        	text4.setForeground(Color.white);
        	hintPanel.add(text4);
        	text5.setFont(new java.awt.Font("Arial", Font.BOLD, 16));
        	text5.setForeground(Color.white);
        	hintPanel.add(text5);
        	text6.setFont(new java.awt.Font("Arial", Font.BOLD, 16));
        	text6.setForeground(Color.white);
        	hintPanel.add(text6);
        	
        } else {
        	
        }
        
        cp.add(hintPanel, BorderLayout.CENTER);
        
        
        
        Dimension screenDim = Toolkit.getDefaultToolkit().getScreenSize();
        setLocation(screenDim.width/4, screenDim.height/4);
        setTitle("Planet Creation Help");
        setSize(525,200);
        setVisible(true);
        setResizable(false);
	}
}