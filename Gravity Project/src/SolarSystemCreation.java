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
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

import javax.media.j3d.Appearance;
import javax.media.j3d.ColoringAttributes;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.ListCellRenderer;
import javax.vecmath.Color3f;

/**
 * @author ldh13/ Lewis Haldenby
 * C03015 - Computer Science Project
 * Gravity Simulator in 3D
 */

public class SolarSystemCreation extends JFrame {
	
	private int planetIntCount = 0;
	private String labelPlanetContent;
	private List<Planet> planetList = new ArrayList<Planet>();
	
	//for combo box colours
	static Color[] colors = {Color.blue, Color.white, Color.gray, Color.red, Color.green,
			Color.yellow, Color.orange, Color.magenta, Color.pink};
	static String[] strings = {"Blue","White","Grey","Red","Green",
			"Yellow","Orange","Pink","Peach"};
	
	
	public SolarSystemCreation() {
		
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Container cp = getContentPane();
        cp.setLayout(new BorderLayout());
        
        ImagePanel planetCreation = new ImagePanel(
        		new ImageIcon(getClass().getClassLoader().getResource("SimBackground.jpg")).getImage());
        planetCreation.setBackground(Color.black);
        planetCreation.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.insets = new Insets(10, 00, 10, 0);
        
        gbc.gridx = 2;
        Button help = new Button("Help");
        help.setForeground(Color.white);
        planetCreation.add(help,gbc);
        
        //set up creation fields
        help.addActionListener(new ActionListener() {
        	
        	@Override
        	public void actionPerformed(ActionEvent e) {
        		new HelpScreen(0);
        	}
        	
        });
        
        gbc.gridy++;
        gbc.gridx = 0;
        JLabel solarNameText = new JLabel("Solar System Name: ");
        solarNameText.setFont(new java.awt.Font("Arial", Font.BOLD, 14));
        solarNameText.setForeground(Color.white);
        planetCreation.add(solarNameText,gbc);
        
        gbc.gridx++;
        JTextField solarName = new JTextField(15);
        planetCreation.add(solarName,gbc);
        
        gbc.gridx++;
        JLabel solarNameError = new JLabel("");
        solarNameError.setFont(new java.awt.Font("Arial", Font.BOLD, 14));
        solarNameError.setForeground(Color.red);
        planetCreation.add(solarNameError,gbc);
        
        gbc.gridx = 0;
        gbc.gridy++;
        JLabel nameText = new JLabel("Planet Name: ");
        nameText.setFont(new java.awt.Font("Arial", Font.BOLD, 14));
        nameText.setForeground(Color.white);
        planetCreation.add(nameText,gbc);
        
        gbc.gridx++;
        JTextField name = new JTextField(15);
        planetCreation.add(name,gbc);
        
        gbc.gridx++;
        JLabel nameError = new JLabel("");
        nameError.setFont(new java.awt.Font("Arial", Font.BOLD, 14));
        nameError.setForeground(Color.red);
        planetCreation.add(nameError,gbc);
        
        gbc.gridx = 0;
        gbc.gridy++;
        JLabel sizeText = new JLabel("Planet Diameter (km): ");
        sizeText.setFont(new java.awt.Font("Arial", Font.BOLD, 14));
        sizeText.setForeground(Color.white);
        planetCreation.add(sizeText,gbc);
        
        gbc.gridx++;
        JTextField diameter = new JTextField(15);
        planetCreation.add(diameter,gbc);
        
        gbc.gridx++;
        JLabel diameterError = new JLabel("");
        diameterError.setFont(new java.awt.Font("Arial", Font.BOLD, 14));
        diameterError.setForeground(Color.red);
        planetCreation.add(diameterError,gbc);
        
        gbc.gridx = 0;
        gbc.gridy++;
        JLabel distanceText = new JLabel("Distance from sun (e6km): ");
        distanceText.setFont(new java.awt.Font("Arial", Font.BOLD, 14));
        distanceText.setForeground(Color.white);
        planetCreation.add(distanceText,gbc);
        
        gbc.gridx++;
        JTextField distance = new JTextField(15);
        planetCreation.add(distance,gbc);
        
        gbc.gridx++;
        JLabel distanceError = new JLabel("");
        distanceError.setFont(new java.awt.Font("Arial", Font.BOLD, 14));
        distanceError.setForeground(Color.red);
        planetCreation.add(distanceError,gbc);
        
        gbc.gridx = 0;
        gbc.gridy++;
        JLabel massText = new JLabel("Mass of Planet (e24kg): ");
        massText.setFont(new java.awt.Font("Arial", Font.BOLD, 14));
        massText.setForeground(Color.white);
        planetCreation.add(massText,gbc);
        
        gbc.gridx++;
        JTextField mass = new JTextField(15);
        planetCreation.add(mass,gbc);
        
        gbc.gridx++;
        JLabel massError = new JLabel("");
        massError.setFont(new java.awt.Font("Arial", Font.BOLD, 14));
        massError.setForeground(Color.red);
        planetCreation.add(massError,gbc);
        
        gbc.gridx=0;
        gbc.gridy++;
        JLabel appearanceText = new JLabel("Planet Appearance");
        appearanceText.setFont(new java.awt.Font("Arial", Font.BOLD, 14));
        appearanceText.setForeground(Color.white);
        planetCreation.add(appearanceText,gbc);
        
		
        gbc.gridx++;
        JComboBox cmb = new JComboBox(strings);
        ComboBoxRenderer renderer = new ComboBoxRenderer(cmb);
        
        renderer.setColors(colors);
        renderer.setStrings(strings);
        
        cmb.setRenderer(renderer);
        cmb.setLightWeightPopupEnabled(false);
        
        planetCreation.add(cmb,gbc);
        
        gbc.gridx = 0;
        gbc.gridy++;
        JLabel noPlanetError = new JLabel("");
        noPlanetError.setFont(new java.awt.Font("Arial", Font.BOLD, 14));
        noPlanetError.setForeground(Color.red);
        planetCreation.add(noPlanetError,gbc);
        
        gbc.gridx++;
        Button add = new Button("Add Planet");
        add.setForeground(Color.white);
        planetCreation.add(add,gbc);
        
        gbc.gridx = 0;
        gbc.gridy++;
        labelPlanetContent = "Planet Count : " + Integer.toString(planetIntCount);
        JLabel planetCount = new JLabel(labelPlanetContent);
        planetCount.setFont(new java.awt.Font("Arial", Font.BOLD, 14));
        planetCount.setForeground(Color.white);
        planetCreation.add(planetCount,gbc);
        
        gbc.gridx++;
        Button create = new Button("Create");
        create.setFont(new java.awt.Font("Arial", Font.BOLD, 14));
        create.setForeground(Color.white);
        planetCreation.add(create,gbc);
        
        gbc.gridx = 0;
        gbc.gridy++;
        Button back = new Button("Back");
        back.setFont(new java.awt.Font("Arial", Font.BOLD, 14));
        back.setForeground(Color.white);
        planetCreation.add(back,gbc);
        
        back.addActionListener(new ActionListener() {
        	
        	@Override
        	public void actionPerformed(ActionEvent e) {
        		Context.setConfirmCreate(false);
        		new Main();
        		dispose();
        	}
        	
        });

        gbc.gridx++;
        Button exit = new Button("Exit");
        exit.setFont(new java.awt.Font("Arial", Font.BOLD, 14));
        exit.setForeground(Color.white);
        planetCreation.add(exit,gbc);
        
        exit.addActionListener(new ActionListener() {
        	
        	@Override
        	public void actionPerformed(ActionEvent e) {
        		System.exit(0);
        	}
        	
        });
        
        //check add planet code, so everything is filled in and a valid answer
        add.addActionListener(new ActionListener() {
        	
        	@Override
        	public void actionPerformed(ActionEvent e) {
        		boolean noEmpty = true;
        		
        		if (name.getText().equals("")) {
        			nameError.setText("Can't be Empty");
        			noEmpty = false;
        		} else {
        			nameError.setText("");
        		}
        		
        		if (diameter.getText().equals("")) {
        			diameterError.setText("Can't be Empty");
        			noEmpty = false;
        		} else {
        			
        			try {
        				Integer.parseInt(diameter.getText());
        				diameterError.setText("");
        			} catch(NumberFormatException ex) {
        				diameterError.setText("Enter Integer");
        				noEmpty = false;
        			}
        			
        		}
        		
        		
        		if (distance.getText().equals("")) {
        			distanceError.setText("Can't be Empty");
        			noEmpty = false;
        		} else {
        			
        			try {
        				Double.parseDouble(distance.getText());
        				distanceError.setText("");
        			} catch(NumberFormatException ex) {
        				distanceError.setText("Enter Double/Integer");
        				noEmpty = false;
        			}
        			
        		}
        		
        		if (mass.getText().equals("")) {
        			massError.setText("Can't be Empty");
        			noEmpty = false;
        		} else {
        			
        			try {
        				Double.parseDouble(mass.getText());
        				massError.setText("");
        			} catch(NumberFormatException ex) {
        				massError.setText("Enter Double/Integer");
        				noEmpty = false;
        			}
        			
        		}
        		
        		if (noEmpty) {
        			String item = (String) cmb.getSelectedItem();
        			String App = "";
        			switch (item) {
        			case "Blue":
        				App = "Blue";
        				break;
        			case "White":
        				App = "White";
        				break;
        			case "Grey":
        				App = "Grey";
        				break;
        			case "Red":
        				App = "Red";
        				break;
        			case "Green":
        				App = "Green";
        				break;
        			case "Yellow":
        				App = "Yellow";
        				break;
        			case "Orange":
        				App = "Orange";
        				break;
        			case "Pink":
        				App = "Pink";
        				break;
        			case "Peach":
        				App = "Peach";
        				break;	
        			}
        			
        			//create valid planet and add it to the solar system file
        			Planet planet = new Planet(name.getText(), Integer.parseInt(diameter.getText()), Double.parseDouble(distance.getText()), Double.parseDouble(mass.getText()), App);
        			
        			planetList.add(planet);
        			
        			noPlanetError.setForeground(Color.green);
        			noPlanetError.setText("Planet Added");
        			planetIntCount++;
        			labelPlanetContent = "Planet Count : " + Integer.toString(planetIntCount);
        			planetCount.setText(labelPlanetContent);
        			
        			name.setText("");
        			diameter.setText("");
        			distance.setText("");
        			mass.setText("");
        		}
        	}
        });
        
        //create new simulation with the planet list from the planet added
        create.addActionListener(new ActionListener() {
        	
        	@Override
        	public void actionPerformed(ActionEvent e) {
        		
        			boolean notEmpty = true;
        			boolean taken = false;
        		
        			ArrayList<File> listOfFiles = new ArrayList<File>();
        			
        			String fileName = solarName.getText() + ".csv";
    		
        			InputStream fileStream = getClass().getClassLoader().getResourceAsStream("PlanetFiles");
        			BufferedReader rdr = new BufferedReader(new InputStreamReader(fileStream));
        	        String line;
        			
        	        try {
						while ((line = rdr.readLine()) != null) {
						    listOfFiles.add(new File(line));
						}
						rdr.close();
					} catch (IOException e2) {
						// TODO Auto-generated catch block
						e2.printStackTrace();
					}
        	        
    			
        			if (solarName.getText().equals("")) {
        				solarNameError.setText("Can't be Empty");
        				notEmpty = false;
        			} 
    			
        			if (!taken && notEmpty) {
        				solarNameError.setText("");
        			}
    			
        			if (planetIntCount == 0 && notEmpty) {
        				noPlanetError.setForeground(Color.red);
        				noPlanetError.setText("Must Add Planets");
        				notEmpty = false;
        			} 
    			
        			if (notEmpty) {
    				
        				if (Context.getSafety() && !Context.getConfirmCreate()) {
                			new Confirm(0);
                		}
        				
                		if (Context.getConfirmCreate()) {
                			Context.setConfirmCreate(false);
        				try {

        					FileOutputStream outFileStream = new FileOutputStream("./resources/PlanetFiles/"+solarName.getText()+".csv");
        					ObjectOutputStream outWriter = new ObjectOutputStream(outFileStream);
        					outWriter.writeObject(planetList);
        					outWriter.close();
	        			
        				} catch (IOException e1) {
        					e1.printStackTrace();
					}
    				
    				
    				
    				
        				new CustomSimulation(planetList);
        				dispose();
        			}
                		
        			}
        		}
        	
        });
        
        cp.add(planetCreation, BorderLayout.CENTER);
        
        Dimension screenDim = Toolkit.getDefaultToolkit().getScreenSize();
        setLocation(screenDim.width/4, screenDim.height/4);
        setTitle("Gavity Simulation");
        setSize(1000,740);
        setVisible(true);
        setResizable(false);
        
	}
	
}

//color combo box
class ComboBoxRenderer extends JPanel implements ListCellRenderer {

	private static final long serialVersionUID = -1L;
    private Color[] colors;
    private String[] strings;

    JPanel textPanel;
    JLabel text;

    public ComboBoxRenderer(JComboBox combo) {

        textPanel = new JPanel();
        textPanel.add(this);
        text = new JLabel();
        text.setOpaque(true);
        text.setFont(combo.getFont());
        textPanel.add(text);
    }

    public void setColors(Color[] col)
    {
        colors = col;
    }

    public void setStrings(String[] str)
    {
        strings = str;
    }

    public Color[] getColors()
    {
        return colors;
    }

    public String[] getStrings()
    {
        return strings;
    }

    @Override
    public Component getListCellRendererComponent(JList list, Object value,
            int index, boolean isSelected, boolean cellHasFocus) {

    	
        if (isSelected)
        {
            setBackground(list.getSelectionBackground());
        }
        else
        {
            setBackground(Color.black);
        }

        text.setBackground(getBackground());

        text.setText(value.toString());
        if (index>-1) {
            text.setForeground(colors[index]);
        }
        return text;
    }
	
}