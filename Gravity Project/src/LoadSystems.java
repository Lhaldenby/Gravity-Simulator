import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

/**
 * @author ldh13/ Lewis Haldenby
 * C03015 - Computer Science Project
 * Gravity Simulator in 3D
 */

public class LoadSystems extends JFrame {
	

	private JPanel footer = new JPanel();;
	
	public LoadSystems() {
		
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Container cp = getContentPane();
        cp.setLayout(new BorderLayout());
        
        JPanel header = new JPanel();
        
        header.setBackground(Color.black);
        
        
        JLabel title = new JLabel("Load System");
        title.setFont(new java.awt.Font("Arial", Font.BOLD, 36));
        title.setForeground(Color.white);
        header.add(title);
        
        Button exit = new Button("Exit");
        exit.setFont(new java.awt.Font("Arial", Font.BOLD, 14));
        exit.setForeground(Color.white);
        
        exit.addActionListener(new ActionListener() {
        	
        	@Override
        	public void actionPerformed(ActionEvent e) {
        		System.exit(0);
        	}
        	
        });
        
        Button mainMenu = new Button("Back");
        mainMenu.setFont(new java.awt.Font("Arial", Font.BOLD, 14));
        mainMenu.setForeground(Color.white);
        
        mainMenu.addActionListener(new ActionListener() {
        	
        	@Override
        	public void actionPerformed(ActionEvent e) {
        		Context.setConfirmDelete(false);
        		new Main();
        		dispose();
        	}
        	
        });
        
        
        footer.setBackground(Color.black);
        footer.add(mainMenu);
        footer.add(exit);
        
        ImagePanel content = new ImagePanel(
        		new ImageIcon(getClass().getClassLoader().getResource("SimBackground.jpg")).getImage());
        content.setPreferredSize(new Dimension(100,200));
        
        content.setBackground(Color.black);
        
        
        content.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.insets = new Insets(10, 0, 10, 0);
        
        File planetFolder = new File("./resources/PlanetFiles");
		File[] listOfFiles = planetFolder.listFiles();
		
		//sort list of files into date modified order
		Arrays.sort(listOfFiles, new Comparator<File>() {
			@Override
			public int compare(File o1, File o2) {
				return Long.valueOf(o2.lastModified()).compareTo(o1.lastModified());
		}});
		
		//for all filescreate button for the file and then also the delete button
		for (int i = 0; i < listOfFiles.length; i++) {
			gbc.gridy++;
			gbc.gridx = 0;
			String systemName = listOfFiles[i].getName().substring(0, listOfFiles[i].getName().length()-4);
			
			List<Planet> planetList = new ArrayList<Planet>();
			
			try {
				FileInputStream inFileStream = new FileInputStream("./resources/PlanetFiles/"+listOfFiles[i].getName());
				ObjectInputStream inReader = new ObjectInputStream(inFileStream);
				planetList = (List<Planet>) inReader.readObject();
				inReader.close();
			} catch (FileNotFoundException e2) {
				// TODO Auto-generated catch block
				e2.printStackTrace();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (ClassNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
			String numPlanets = "Number of Planets: "+planetList.size();
			
			File modFile = new File("./resources/PlanetFiles/"+listOfFiles[i].getName());
			
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");
			
			String dateMod = sdf.format(modFile.lastModified());
			
			Button button = new Button(systemName + "   |   "+numPlanets+"   |   Created: "+dateMod);
			
			button.setFont(new java.awt.Font("Arial", Font.BOLD, 20));
	        button.setForeground(Color.white);
	        button.setPreferredSize(new Dimension(800,40));
	        
	        button.addActionListener(new ActionListener() {
	        	
	        	@Override
	        	public void actionPerformed(ActionEvent e) {
	        		
	        		List<Planet> planetList = new ArrayList<Planet>();
	        		try {
    					
	        			File userFile = new File("./resources/PlanetFiles/"+ button.getLabel().substring(0,button.getLabel().indexOf("|")-3) +".csv");
	        			FileInputStream inFileStream = new FileInputStream(userFile);
	        			ObjectInputStream inReader = new ObjectInputStream(inFileStream);
	        			planetList = (List<Planet>) inReader.readObject();
	        			inReader.close();
	        			
					} catch (IOException | ClassNotFoundException e1) {
						e1.printStackTrace();
					}
	        		
    				new CustomSimulation(planetList);
    				dispose();
	        	}
	        	
	        });
	        
			content.add(button,gbc);
			
			gbc.gridx++;
			Button delButton = new Button("Delete");
			delButton.setFont(new java.awt.Font("Arial", Font.PLAIN, 18));
	        delButton.setForeground(Color.white);
	        
	        delButton.addActionListener(new ActionListener() {
	        	
	        	@Override
	        	public void actionPerformed(ActionEvent e) {
	        		
	        		if (Context.getSafety() && !Context.getConfirmDelete()) {
	        			new Confirm(1);
	        		}
	        		
	        		if (Context.getConfirmDelete()) {
	        			Context.setConfirmDelete(false);
	        			
	        			File userFile = new File("./resources/PlanetFiles/"+ button.getLabel().substring(0,button.getLabel().indexOf("|")-3) +".csv");
	        			userFile.delete();
	        			
	        			new LoadSystems();
	        			dispose();
	        		}
	        	}
	        	
	        });
	        
	        content.add(delButton,gbc);
		}
        
        
		JPanel container = new JPanel();
		container.setLayout(new BoxLayout(container, BoxLayout.Y_AXIS));
		container.add(content);
		
		JScrollPane systemContent = new JScrollPane(container);
		
        cp.add(systemContent, BorderLayout.CENTER);
        cp.add(header, BorderLayout.PAGE_START);
        cp.add(footer, BorderLayout.PAGE_END);
        
        Dimension screenDim = Toolkit.getDefaultToolkit().getScreenSize();
        setLocation(screenDim.width/4, screenDim.height/4);
        setTitle("Gavity Simulation");
        setSize(1000,740);
        setVisible(true);
        setResizable(false);
	}
}