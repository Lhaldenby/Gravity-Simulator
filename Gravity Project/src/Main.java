import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * @author ldh13/ Lewis Haldenby
 * C03015 - Computer Science Project
 * Gravity Simulator in 3D
 */

public class Main extends JFrame {
	
	public Main() {
		
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Container cp = getContentPane();
        cp.setLayout(new BorderLayout());
        
        ImagePanel titles = new ImagePanel(
                new ImageIcon(getClass().getClassLoader().getResource("SpaceBackground.jpg")).getImage());
        titles.setBackground(Color.black);
        titles.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.insets = new Insets(10, 00, 10, 0);
        
        //create main menu
        JLabel name = new JLabel("Gravity Simulator");
        name.setFont(new java.awt.Font("Arial", Font.BOLD, 45));
        name.setForeground(Color.white);
        titles.add(name,gbc);
        
        gbc.gridy++;
        Button start = new Button("BUILD");
        start.setFont(new java.awt.Font("Arial", Font.BOLD, 16));
        start.setForeground(Color.white);
        titles.add(start,gbc);
     
        start.addActionListener(new ActionListener() {
        	
        	@Override
        	public void actionPerformed(ActionEvent e) {
        		
        		new SolarSystemCreation();
        		dispose();
        	}
        });
        
        gbc.gridy++;
        Button load = new Button("LOAD");
        load.setFont(new java.awt.Font("Arial", Font.BOLD, 16));
        load.setForeground(Color.white);
        titles.add(load,gbc);
        
        load.addActionListener(new ActionListener() {
        	
        	@Override
        	public void actionPerformed(ActionEvent e) {
        		
        		new LoadSystems();
        		dispose();
        	}
        });

        gbc.gridy++;
        Button education = new Button("EDUCATION");
        education.setFont(new java.awt.Font("Arial", Font.BOLD, 16));
        education.setForeground(Color.white);
        titles.add(education,gbc);
        
        education.addActionListener(new ActionListener() {
        	
        	@Override
        	public void actionPerformed(ActionEvent e) {
        		
        		new EducationSimulation();
        		dispose();
        	}
        });
        
        gbc.gridy++;
        Button options = new Button("OPTIONS");
        options.setFont(new java.awt.Font("Arial", Font.BOLD, 16));
        options.setForeground(Color.white);
        titles.add(options,gbc);
        
        options.addActionListener(new ActionListener() {
        	
        	@Override
        	public void actionPerformed(ActionEvent e) {
        		
        		new Options();
        		dispose();
          	}
        });
        
        gbc.gridy++;
        Button exit = new Button("EXIT");
        exit.setFont(new java.awt.Font("Arial", Font.BOLD, 16));
        exit.setForeground(Color.white);
        
        exit.addActionListener(new ActionListener() {
        	
        	@Override
        	public void actionPerformed(ActionEvent e) {
        		System.exit(0);
        	}
        });

        titles.add(exit,gbc);
        
        cp.add(titles, BorderLayout.CENTER);
        
        Dimension screenDim = Toolkit.getDefaultToolkit().getScreenSize();
        setLocation(screenDim.width/4, screenDim.height/4);
        setTitle("Gavity Simulation");
        setSize(1000,740);
        setVisible(true);
        setResizable(false);
        
	}
	
	public static void main(String...args){
		//start music
		Context context = new Context();
		
		new Main();
		
	}
}