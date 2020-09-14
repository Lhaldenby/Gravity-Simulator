import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

import javax.media.j3d.Appearance;
import javax.media.j3d.ColoringAttributes;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.JTextField;
import javax.swing.ListCellRenderer;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.vecmath.Color3f;

/**
 * @author ldh13/ Lewis Haldenby
 * C03015 - Computer Science Project
 * Gravity Simulator in 3D
 */

public class Options extends JFrame {
	
	
	
	public Options() {
		
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Container cp = getContentPane();
        cp.setLayout(new BorderLayout());
        
        ImagePanel optionsPanel = new ImagePanel(
        		new ImageIcon(getClass().getClassLoader().getResource("SimBackground.jpg")).getImage());
        optionsPanel.setBackground(Color.black);
        optionsPanel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.insets = new Insets(10, 00, 10, 0);
        
        //create options menu
        gbc.gridy++;
        gbc.gridx=1;
        JLabel title = new JLabel("Options");
        title.setFont(new java.awt.Font("Arial", Font.BOLD, 32));
        title.setForeground(Color.white);
        optionsPanel.add(title,gbc);
        
        
        gbc.gridx=0;
        gbc.gridy++;
        JLabel sound = new JLabel("Music     ");
        sound.setFont(new java.awt.Font("Arial", Font.BOLD, 18));
        sound.setForeground(Color.white);
        optionsPanel.add(sound,gbc);
        
        gbc.gridx++;
        JSlider volumeSlider = new JSlider(0,100,100); 
        volumeSlider.setValue((int) ((double) Context.getVolume() * 100));
        volumeSlider.setMajorTickSpacing(25);
        volumeSlider.setMinorTickSpacing(5);
        volumeSlider.setPaintTicks(true);
        volumeSlider.setPaintLabels(true);
        volumeSlider.setBackground(Color.black);
        volumeSlider.setForeground(Color.white);
        
        volumeSlider.addChangeListener(new ChangeListener() {
        	
        	//change volume on slider change
        	@Override
        	public void stateChanged(ChangeEvent e) {
        		
        		Context.setVolume((float) ((JSlider)e.getSource()).getValue()/100);
        	}

        });
        
        
        optionsPanel.add(volumeSlider,gbc);
        
        gbc.gridx=0;
        gbc.gridy++;
        JLabel safety = new JLabel("Safety    ");
        safety.setFont(new java.awt.Font("Arial", Font.BOLD, 18));
        safety.setForeground(Color.white);
        optionsPanel.add(safety,gbc);
        
        gbc.gridx++;
        JCheckBox safetyBox = new JCheckBox("Safety Warnings ON");
        if (Context.getSafety())
        	safetyBox.setSelected(true);
        else {
        	safetyBox.setSelected(false);
        	safetyBox.setText("Safety Warnings OFF");
        }
        safetyBox.setForeground(Color.white);
        safetyBox.setBackground(Color.black);
        
        safetyBox.addChangeListener(new ChangeListener() {

        	//change safety warnings settings
			@Override
			public void stateChanged(ChangeEvent e) {
				if (((JCheckBox)e.getSource()).isSelected() ) {
					safetyBox.setText("Safety Warnings ON");
					Context.setSafety(true);
				} else {
					safetyBox.setText("Safety Warnings OFF");
					Context.setSafety(false);
					Context.setConfirmCreate(true);
					Context.setConfirmDelete(true);
				}
				
			}
        	
        });
        
        optionsPanel.add(safetyBox,gbc);
        
        gbc.gridx=0;
        gbc.gridy++;
        JLabel autoStart = new JLabel("Autostart ");
        autoStart.setFont(new java.awt.Font("Arial", Font.BOLD, 18));
        autoStart.setForeground(Color.white);
        optionsPanel.add(autoStart,gbc);
        
        gbc.gridx++;
        JCheckBox autoStartBox = new JCheckBox("Start Simulations ON");
        if (Context.getAutoStart())
        	autoStartBox.setSelected(true);
        else {
        	autoStartBox.setSelected(false);
        	autoStartBox.setText("Start Simulations OFF");
        }
        autoStartBox.setForeground(Color.white);
        autoStartBox.setBackground(Color.black);
        
        autoStartBox.addChangeListener(new ChangeListener() {

        	//change autostart settings
			@Override
			public void stateChanged(ChangeEvent e) {
				if (((JCheckBox)e.getSource()).isSelected() ) {
					autoStartBox.setText("Start Simulations ON");
					Context.setAutoStart(true);
				} else {
					autoStartBox.setText("Start Simulations OFF");
					Context.setAutoStart(false);
				}
				
			}
        	
        });
        
        optionsPanel.add(autoStartBox,gbc);
        
        gbc.gridx = 0;
        gbc.gridy++;
        Button back = new Button("Back");
        back.setFont(new java.awt.Font("Arial", Font.BOLD, 14));
        back.setForeground(Color.white);
        optionsPanel.add(back,gbc);
        
        back.addActionListener(new ActionListener() {
        	
        	@Override
        	public void actionPerformed(ActionEvent e) {
        		
        		new Main();
        		dispose();
        	}
        	
        });

        gbc.gridx++;
        Button exit = new Button("Exit");
        exit.setFont(new java.awt.Font("Arial", Font.BOLD, 14));
        exit.setForeground(Color.white);
        optionsPanel.add(exit,gbc);
        
        exit.addActionListener(new ActionListener() {
        	
        	@Override
        	public void actionPerformed(ActionEvent e) {
        		System.exit(0);
        	}
        	
        });
        
        
        cp.add(optionsPanel, BorderLayout.CENTER);
        
        Dimension screenDim = Toolkit.getDefaultToolkit().getScreenSize();
        setLocation(screenDim.width/4, screenDim.height/4);
        setTitle("Gavity Simulation");
        setSize(1000,740);
        setVisible(true);
        setResizable(false);
        
	}
	
}
