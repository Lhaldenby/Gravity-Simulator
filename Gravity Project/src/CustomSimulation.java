import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import javax.media.j3d.Alpha;
import javax.media.j3d.AmbientLight;
import javax.media.j3d.Appearance;
import javax.media.j3d.Background;
import javax.media.j3d.BoundingSphere;
import javax.media.j3d.BranchGroup;
import javax.media.j3d.Canvas3D;
import javax.media.j3d.ColoringAttributes;
import javax.media.j3d.DirectionalLight;
import javax.media.j3d.ImageComponent2D;
import javax.media.j3d.Material;
import javax.media.j3d.RotationInterpolator;
import javax.media.j3d.Shape3D;
import javax.media.j3d.Texture;
import javax.media.j3d.TextureAttributes;
import javax.media.j3d.Transform3D;
import javax.media.j3d.TransformGroup;
import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;
import javax.swing.border.Border;
import javax.vecmath.Color3f;
import javax.vecmath.Color4f;
import javax.vecmath.Point3d;
import javax.vecmath.Vector3d;
import javax.vecmath.Vector3f;

import com.sun.j3d.utils.behaviors.mouse.MouseRotate;
import com.sun.j3d.utils.behaviors.mouse.MouseTranslate;
import com.sun.j3d.utils.behaviors.mouse.MouseZoom;
import com.sun.j3d.utils.geometry.Primitive;
import com.sun.j3d.utils.geometry.Sphere;
import com.sun.j3d.utils.image.TextureLoader;
import com.sun.j3d.utils.picking.PickCanvas;
import com.sun.j3d.utils.picking.PickResult;
import com.sun.j3d.utils.universe.SimpleUniverse;

/**
 * @author ldh13/ Lewis Haldenby
 * C03015 - Computer Science Project
 * Gravity Simulator in 3D
 */

public class CustomSimulation extends JFrame implements ActionListener, MouseListener, KeyListener{
	
	private BoundingSphere bounds = new BoundingSphere(new Point3d(0.0,0.0,0.0), 100.0);
	
	private PickCanvas pickCanvas;
	
	private JLabel label1, label2, label3;
	
	private Button timeButton = new Button(String.valueOf("\u25b7"));
	private Button fastButton = new Button(String.valueOf("x1.5"));
	private Button slowButton = new Button(String.valueOf("x0.5"));
	
	private Timer timer;
	
	private List<TransformGroup> objTransList = new ArrayList<TransformGroup>();;
	
	private TransformGroup lightTrans = new TransformGroup();
	
	private Transform3D trans = new Transform3D();
	
	private List<Float> posX = new ArrayList<Float>(); 

	private List<Float> posY = new ArrayList<Float>();
	
	private List<Float> posZ = new ArrayList<Float>();

	private List<Vector3f> velocityList = new ArrayList<Vector3f>();
	
	private List<Vector3f> accelList = new ArrayList<Vector3f>();
	
	private List<Vector3f> momentumList = new ArrayList<Vector3f>();
	
	private double dt = 0.001;
	
	private Planet sunPlanet = new Planet("Sun1392684",1392684,0,1989, "Yellow");
	
	private List<Planet> planetList = new ArrayList<Planet>();
	
	private int displayCount = 0;
	
	private double G = 6.67e-11;
	
	public CustomSimulation(List<Planet> planetList) {
		 
		//get planets and adds sun to the list
		this.planetList = planetList;
		this.planetList.add(sunPlanet);
		
		//loop through planet list and set position and velocity and initial momentums
		for (int i = 0; i < planetList.size(); i++) {
			
			posX.add((float)planetList.get(i).getDistance()/35);
			posY.add(0.0f);
			posZ.add(0.0f);
			velocityList.add(new Vector3f(0f,0f,0f));
			accelList.add(new Vector3f(0f,0f,0f));
			
			if (!planetList.get(i).equals(sunPlanet)) {
				momentumList.add(new Vector3f(00.0f,0.0f,150f));
			} else {
				momentumList.add(new Vector3f(0.0f,0.0f,0f));
			}
			objTransList.add(new TransformGroup());
			
		}
		
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Container cp = getContentPane();
        cp.setLayout(new BorderLayout());
        
        JPanel universePanel = new JPanel();
        universePanel.setBackground(Color.black);
        
        JPanel detailsPanel = new JPanel();
        detailsPanel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 0;
        gbc.gridy = 0;		
        gbc.insets = new Insets(10, 0, 10, 0);

        Border greyLine = BorderFactory.createLineBorder(Color.DARK_GRAY , 1);
        Border raisedbevel = BorderFactory.createRaisedBevelBorder();
        Border loweredbevel = BorderFactory.createLoweredBevelBorder();
        Border compound = BorderFactory.createCompoundBorder(
                raisedbevel, loweredbevel);
        compound = BorderFactory.createCompoundBorder(greyLine, compound);
        
        Button closeDetails = new Button(">");
        closeDetails.setFont(new java.awt.Font("Arial", Font.BOLD, 14));
        closeDetails.setForeground(Color.white);
        closeDetails.setPreferredSize(new Dimension(190,20));
        detailsPanel.add(closeDetails, gbc);
        
        gbc.gridy++;
        Button help = new Button("?");
        help.setFont(new java.awt.Font("Arial", Font.BOLD, 14));
        help.setForeground(Color.white);
        help.setMaximumSize(new Dimension(25,25));
        detailsPanel.add(help, gbc);
        
        help.addActionListener(new ActionListener() {
        	
        	@Override
        	public void actionPerformed(ActionEvent e) {
        		new HelpScreen(1);
        	}
        	
        });
        
        gbc.gridy++;
        label1 = new JLabel("Name: Space");
        label1.setForeground(Color.white);
        detailsPanel.add(label1, gbc);
        
        gbc.gridy++;
        label2 = new JLabel("Diameter: Null");
        label2.setForeground(Color.white);
        detailsPanel.add(label2, gbc);
        
        gbc.gridy++;
        label3 = new JLabel("Mass: Null");
        label3.setForeground(Color.white);
        detailsPanel.add(label3, gbc);
        
        gbc.gridy++;
        JPanel timeControls = new JPanel();
        timeControls.setLayout(new GridLayout(1,3));
        timeControls.setBackground(Color.darkGray);
        timeControls.setForeground(Color.white);
        detailsPanel.add(timeControls, gbc);
        
        timeControls.add(timeButton);
        timeControls.add(slowButton);
        timeControls.add(fastButton);
        
        timeButton.addActionListener(this);
        
        fastButton.addActionListener(this);
        
        slowButton.addActionListener(this);
        
        gbc.gridy++;
        Button menuButton = new Button("Main Menu");
        menuButton.setFont(new java.awt.Font("Arial", Font.BOLD, 14));
        menuButton.setForeground(Color.white);
        detailsPanel.add(menuButton, gbc);
        
        gbc.gridy++;
        Button exitButton = new Button("Exit");
        exitButton.setFont(new java.awt.Font("Arial", Font.BOLD, 14));
        exitButton.setForeground(Color.white);
        detailsPanel.add(exitButton, gbc);
        
        detailsPanel.setBorder(compound);
        detailsPanel.setPreferredSize(new Dimension(200,0));
        detailsPanel.setBackground(Color.darkGray);
        
        JPanel expandDetails = new JPanel();		
        Button expandButton = new Button("<");
        expandDetails.add(expandButton);
        expandDetails.setVisible(false);
        expandDetails.setBackground(Color.darkGray);
        expandDetails.setBorder(compound);
        
        expandButton.setFont(new java.awt.Font("Arial", Font.BOLD, 14));
        expandButton.setForeground(Color.white);
        expandButton.addActionListener(new ActionListener() {
        	
        	@Override
        	public void actionPerformed(ActionEvent e) {
        		expandDetails.setVisible(false);
        		detailsPanel.setVisible(true);
        		cp.add(detailsPanel,BorderLayout.LINE_END);
        	}
        });
        
        closeDetails.addActionListener(new ActionListener() {
        	
        	@Override
        	public void actionPerformed(ActionEvent e) {
        		detailsPanel.setVisible(false);
        		
        		expandDetails.setVisible(true);
        		cp.add(expandDetails,BorderLayout.LINE_END);
        	}
        	
        });
        
        exitButton.addActionListener(new ActionListener() {
        	
        	@Override
        	public void actionPerformed(ActionEvent e) {
        		System.exit(0);
        	}
        	
        });
        
        menuButton.addActionListener(new ActionListener() {
        	
        	@Override
        	public void actionPerformed(ActionEvent e) {
        		timer.stop();
        		new Main();
        		dispose();
        	}
        	
        });
        
        Canvas3D c = new Canvas3D(SimpleUniverse.getPreferredConfiguration() );
        SimpleUniverse u = new SimpleUniverse(c);
        cp.add(c, BorderLayout.CENTER);
        
        BranchGroup scene = new BranchGroup();
        
        try {
			createSceneGraph(scene);
		} catch (URISyntaxException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
        
        u.addBranchGraph(scene);
        
        pickCanvas = new PickCanvas(c, scene);
        pickCanvas.setMode(PickCanvas.BOUNDS);
        
        //sets up event listeners
        c.addMouseListener(this);
        c.addKeyListener(this);
        
        //sets up timer for later calculations
        timer = new Timer(10, this);
        
        //if auto start is on then start the timer
        if (Context.getAutoStart()) {
        	timeButton.setLabel(String.valueOf("||"));
			timer.start();
        }
        
        u.getViewingPlatform().setNominalViewingTransform();
        
    	// *** create a viewing platform
        TransformGroup cameraTG = u.getViewingPlatform().getViewPlatformTransform();
        
        //starting position of the viewing platform
        Vector3f translate = new Vector3f(); 
      	Transform3D T3D = new Transform3D();
      	
      	// move along z axis by 15.0f ("move away from the screen") 
      	translate.set( 0.0f, 0.0f, 30.0f);
        T3D.setTranslation(translate);
        cameraTG.setTransform(T3D);
        
        Dimension screenDim = Toolkit.getDefaultToolkit().getScreenSize();
        setLocation(screenDim.width/4, screenDim.height/4);
        
        cp.add(detailsPanel, BorderLayout.LINE_END);
        
        setTitle("Gavity Simulation");
        setSize(1000,750);
        setVisible(true);
        setResizable(false);
        
        
	}
	
	//get texture for each planet
	public Appearance getAppearance(URL url) {
		
		Color3f black = new Color3f(0.0f, 0.0f, 0.0f);
	    Color3f white = new Color3f(1.0f, 1.0f, 1.0f);
	    Color3f red = new Color3f(0.7f, .15f, .15f);
		
		TextureLoader loader = new TextureLoader(url, 
				"RGB", new Container());
		Texture texture = loader.getTexture();
		texture.setBoundaryModeS(Texture.WRAP);
		texture.setBoundaryModeT(Texture.WRAP);
		texture.setBoundaryColor(new Color4f(0.0f, 1.0f, 0.0f, 0.0f));

		TextureAttributes texAttr = new TextureAttributes();
		texAttr.setTextureMode(TextureAttributes.DECAL);
		Appearance ap = new Appearance();
		ap.setTexture(texture);
		ap.setTextureAttributes(texAttr);

		
		ap.setMaterial(new Material(red, black, red, black, 1.0f));
		
		return ap;
	}

	//creates solar system
	public void createSceneGraph(BranchGroup objRoot) throws URISyntaxException {
	
		TransformGroup mainTG = new TransformGroup();		
		mainTG.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
		mainTG.setCapability(TransformGroup.ALLOW_TRANSFORM_READ);
		
		objRoot.addChild(mainTG);
		   
		int primflags = Primitive.GENERATE_NORMALS
		        + Primitive.GENERATE_TEXTURE_COORDS;
        
        //for each planet in the list create the object and add to the system
        for (int i = 0; i < planetList.size(); i++) {
        	objTransList.get(i).setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
        	
        	Sphere planet = new Sphere();
        	
        	switch (planetList.get(i).getName()) {
        	case "Sun1392684":
        		planet = new Sphere((float)sunPlanet.getSize()/750000, primflags, 100, getAppearance(getClass().getClassLoader().getResource("2k_sun.jpg")));
                planet.setUserData(sunPlanet);
                
                SunCollisionDetector scd = new SunCollisionDetector(planet);
                scd.setSchedulingBounds(bounds);
                
                objTransList.get(i).addChild(scd);
        		break;
        	case "Mercury":
        		planet = new Sphere((float)planetList.get(i).getSize()/100000, primflags, 100, getAppearance(getClass().getClassLoader().getResource("2k_mercury.jpg")));
                planet.setUserData(planetList.get(i));
        		break;
        	case "Venus":
        		planet = new Sphere((float)planetList.get(i).getSize()/100000, primflags, 100, getAppearance(getClass().getClassLoader().getResource("2k_venus.jpg")));
                planet.setUserData(planetList.get(i));
        		break;
        	case "Earth":
        		planet = new Sphere((float)planetList.get(i).getSize()/100000, primflags, 100, getAppearance(getClass().getClassLoader().getResource("2k_earth.jpg")));
                planet.setUserData(planetList.get(i));
        		break;
        	case "Mars":
        		planet = new Sphere((float)planetList.get(i).getSize()/100000, primflags, 100, getAppearance(getClass().getClassLoader().getResource("2k_mars.jpg")));
                planet.setUserData(planetList.get(i));
        		break;
        	case "Jupiter":
        		planet = new Sphere((float)planetList.get(i).getSize()/100000, primflags, 100, getAppearance(getClass().getClassLoader().getResource("2k_jupiter.jpg")));
                planet.setUserData(planetList.get(i));
        		break;
        	case "Saturn":
        		planet = new Sphere((float)planetList.get(i).getSize()/100000, primflags, 100, getAppearance(getClass().getClassLoader().getResource("2k_saturn.jpg")));
                planet.setUserData(planetList.get(i));
        		break;
        	case "Uranus":
        		planet = new Sphere((float)planetList.get(i).getSize()/100000, primflags, 100, getAppearance(getClass().getClassLoader().getResource("2k_uranus.jpg")));
                planet.setUserData(planetList.get(i));
        		break;
        	case "Neptune":
        		planet = new Sphere((float)planetList.get(i).getSize()/100000, primflags, 100, getAppearance(getClass().getClassLoader().getResource("2k_neptune.jpg")));
                planet.setUserData(planetList.get(i));
        		break;
        	case "Pluto":
        		planet = new Sphere((float)planetList.get(i).getSize()/100000, primflags, 100, getAppearance(getClass().getClassLoader().getResource("2k_moon.jpg")));
                planet.setUserData(planetList.get(i));
        		break;
        	default:
        		switch (planetList.get(i).getAppearance()) {
        		case "Blue":
        			planet = new Sphere((float)planetList.get(i).getSize()/100000, primflags, 100, getAppearance(getClass().getClassLoader().getResource("2k_custom_blue.jpg")));
        			break;
        		case "White":
        			planet = new Sphere((float)planetList.get(i).getSize()/100000, primflags, 100, getAppearance(getClass().getClassLoader().getResource("2k_custom_white.jpg")));
        			break;
        		case "Grey":
        			planet = new Sphere((float)planetList.get(i).getSize()/100000, primflags, 100, getAppearance(getClass().getClassLoader().getResource("2k_custom_grey.jpg")));
        			break;
        		case "Red":
        			planet = new Sphere((float)planetList.get(i).getSize()/100000, primflags, 100, getAppearance(getClass().getClassLoader().getResource("2k_custom_red.jpg")));
        			break;
        		case "Green":
        			planet = new Sphere((float)planetList.get(i).getSize()/100000, primflags, 100, getAppearance(getClass().getClassLoader().getResource("2k_custom_green.jpg")));
        			break;
        		case "Yellow":
        			planet = new Sphere((float)planetList.get(i).getSize()/100000, primflags, 100, getAppearance(getClass().getClassLoader().getResource("2k_custom_yellow.jpg")));
        			break;
        		case "Orange":
        			planet = new Sphere((float)planetList.get(i).getSize()/100000, primflags, 100, getAppearance(getClass().getClassLoader().getResource("2k_custom_orange.jpg")));
        			break;
        		case "Pink":
        			planet = new Sphere((float)planetList.get(i).getSize()/100000, primflags, 100, getAppearance(getClass().getClassLoader().getResource("2k_custom_pink.jpg")));
        			break;
        		case "Peach":
        			planet = new Sphere((float)planetList.get(i).getSize()/100000, primflags, 100, getAppearance(getClass().getClassLoader().getResource("2k_custom_peach.jpg")));
        			break;
        		default:
        			planet = new Sphere((float)planetList.get(i).getSize()/100000, primflags, 100, getAppearance(getClass().getClassLoader().getResource("2k_custom_white.jpg")));
        			break;
        			
        		}
        		planet.setUserData(planetList.get(i));
        		break;
        	}
        	
        	
        	Transform3D pos = new Transform3D();
        	pos.setTranslation(new Vector3f((float)planetList.get(i).getDistance()/35,0.0f,0.0f));
        	
        	objTransList.get(i).setTransform(pos);
        	objTransList.get(i).addChild(planet);
        	
        	mainTG.addChild(objTransList.get(i));
        }
        
		
		/*-- Start of functionality of the 3D universe--*/
		
		// *** Create the rotate behaviour node
		MouseRotate behavior = new MouseRotate();
		behavior.setTransformGroup(mainTG);
		objRoot.addChild(behavior);
		behavior.setSchedulingBounds(bounds);
		
		// *** Create the zoom behaviour node
		MouseZoom behavior2 = new MouseZoom();
		behavior2.setTransformGroup(mainTG);
		objRoot.addChild(behavior2);
		behavior2.setSchedulingBounds(bounds);
		
		// *** Create the translate behaviour node
		MouseTranslate behavior3 = new MouseTranslate();
		behavior3.setTransformGroup(mainTG);
		objRoot.addChild(behavior3);
		behavior3.setSchedulingBounds(bounds);
		
		/*-- End of functionality of the 3D universe--*/
		   
		AmbientLight ambientLight = new AmbientLight(new Color3f(3.0f, 3.0f, 3.0f));
	    ambientLight.setInfluencingBounds(bounds);
	    objRoot.addChild(ambientLight);
	   
	    
		//To set a Background image:
		ImageComponent2D image = new TextureLoader(
				getClass().getClassLoader().getResource("SimBackground.jpg"), this).getImage();
		Background back = new Background( image );
		back.setApplicationBounds( bounds );

		objRoot.addChild(back);
				
	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		
		//if e or q is pressed swap to next planets information
		if (e.getKeyChar() == 'e') {
			if (displayCount == planetList.size()-1) {
				displayCount = 0;
			}
			else {
				displayCount++;
			}
			
		}
		if (e.getKeyChar() == 'q') {
			if (displayCount == 0) {
				displayCount = planetList.size()-1;
			}
			else {
				displayCount--;
			}
			
		}
		
		label1.setText("Name: "+planetList.get(displayCount).getName());
		label2.setText("Diameter: "+planetList.get(displayCount).getSize()+" km");
		label3.setText("Mass: "+planetList.get(displayCount).getMass()+"e24kg");
		
		if (planetList.get(displayCount).getName().equals("Sun1392684")) {
			label1.setText("Name: Sun");
		}
	}



	//for each mouse click get the object you clicked on and display its information
	@Override
	public void mouseClicked(MouseEvent e) {
		pickCanvas.setShapeLocation(e);
		
		PickResult result = pickCanvas.pickClosest();
		
		
		if (result == null) {
			label1.setText("Name: Space");
			label2.setText("Diameter: Null");
			label3.setText("Mass: Null");
		} else {
			Planet planet = (Planet)result.getNode(PickResult.PRIMITIVE).getUserData();
			
			Shape3D s = (Shape3D)result.getNode(PickResult.SHAPE3D); 
			
			
			label1.setText("Name: "+planet.getName());
			label2.setText("Diameter: "+planet.getSize()+" km");
			label3.setText("Mass: "+planet.getMass()+"e24kg");
			
			if (planet.getName().equals("Sun1392684")) {
				label1.setText("Name: Sun");
			}
			
			
		}
	}
	
	public void actionPerformed(ActionEvent e ) {
		
		//if timer hasn't started, start it
		if (e.getSource()==timeButton) {
			if (!timer.isRunning()) {
				timeButton.setLabel(String.valueOf("||"));
				timer.start();
			} else {
				timeButton.setLabel(String.valueOf("\u25b7"));
				timer.stop();
			}
		}
		//decrease or increase timer speed
		else if (e.getSource()==fastButton) {
			if (!(timer.getDelay() <= 4)) 
				timer.setDelay((int)(timer.getDelay()*0.5));
		}
		else if (e.getSource()==slowButton) {
			if (!(timer.getDelay() >= 74))
				timer.setDelay((int)(timer.getDelay()*1.5)); 
			
		}
		//for each increment of timer run this code
		else {

			List<Vector3f> forceMathList = new ArrayList<Vector3f>();

			//loop through each planet
			for (int i=0; i < planetList.size(); i++) {
				
				forceMathList.add(new Vector3f());
				
				//loop through each planet making sure to not calculate momentum for each one but not itself
				for (int j=0; j <planetList.size(); j++) {
					
					Vector3f force = new Vector3f();
					
					if (!planetList.get(j).equals(planetList.get(i))) {
						
						double planetIMass = planetList.get(i).getMass();
						double planetJMass = planetList.get(j).getMass();
						
						double posIX = posX.get(i);
						double posIY = posY.get(i);
						double posIZ = posZ.get(i);
						
						double posJX = posX.get(j);
						double posJY = posY.get(j);
						double posJZ = posZ.get(j);
						
						Vector3f vector = new Vector3f( (float)(posIX-posJX), (float)(posIY-posJY), (float)(posIZ-posJZ) );
						
						double distance = Math.sqrt(Math.pow((posIX - posJX), 2) + 
									Math.pow((posIY - posJY), 2) + 
									Math.pow((posIZ - posJZ), 2));
						
						
						Vector3f unitVector = new Vector3f( (float)(vector.getX()/distance), 
													(float)(vector.getY()/distance), (float)(vector.getZ()/distance) );
						
						force = new Vector3f( 
								(float)(( -1 * (planetIMass * planetJMass) * unitVector.getX() ) / Math.pow(distance, 2)),
								(float)(( -1 * (planetIMass * planetJMass) * unitVector.getY() ) / Math.pow(distance, 2)),
								(float)(( -1 * (planetIMass * planetJMass) * unitVector.getZ() ) / Math.pow(distance, 2))
								);
						
						
					}
					
					forceMathList.set(i, new Vector3f(
							(float) ( forceMathList.get(i).getX() + force.getX()),
							(float) ( forceMathList.get(i).getY() + force.getY()),
							(float) ( forceMathList.get(i).getZ() + force.getZ())
							));
					
				}
				
				//update momentum
				momentumList.set(i, new Vector3f(
						(float) (momentumList.get(i).getX() + (forceMathList.get(i).getX() * 0.001)),
						(float) (momentumList.get(i).getY() + (forceMathList.get(i).getY() * 0.001)),
						(float) (momentumList.get(i).getZ() + (forceMathList.get(i).getZ() * 0.001))
						));
				
			}


			for (int k = 0; k < planetList.size(); k++) {
				
				//update position
				posX.set(k, (float) (( (posX.get(k) ) + momentumList.get(k).getX() * 0.001 / (planetList.get(k).getMass()) ) ));
				posY.set(k, (float) (( (posY.get(k) ) + momentumList.get(k).getY() * 0.001 / (planetList.get(k).getMass()) )));
				posZ.set(k, (float) (( (posZ.get(k) ) + momentumList.get(k).getZ() * 0.001 / (planetList.get(k).getMass()) ) ));
				
				trans.setTranslation(new Vector3f( posX.get(k), posY.get(k), posZ.get(k)));
				
				objTransList.get(k).setTransform(trans);
			}
				
		}
		
	}
	
	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	
}