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
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
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
import javax.media.j3d.ImageComponent2D;
import javax.media.j3d.Link;
import javax.media.j3d.Material;
import javax.media.j3d.RotationInterpolator;
import javax.media.j3d.Shape3D;
import javax.media.j3d.SharedGroup;
import javax.media.j3d.Texture;
import javax.media.j3d.Texture2D;
import javax.media.j3d.TextureAttributes;
import javax.media.j3d.Transform3D;
import javax.media.j3d.TransformGroup;
import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.Border;
import javax.vecmath.AxisAngle4f;
import javax.vecmath.Color3f;
import javax.vecmath.Color4f;
import javax.vecmath.Point3d;
import javax.vecmath.Vector3d;
import javax.vecmath.Vector3f;

import com.sun.j3d.utils.behaviors.mouse.MouseRotate;
import com.sun.j3d.utils.behaviors.mouse.MouseTranslate;
import com.sun.j3d.utils.behaviors.mouse.MouseZoom;
import com.sun.j3d.utils.geometry.Cylinder;
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

public class EducationSimulation extends JFrame implements MouseListener, KeyListener{

	private BoundingSphere bounds = new BoundingSphere(new Point3d(0.0,0.0,0.0), 10000.0);
	
	private PickCanvas pickCanvas;
	
	private JLabel label1, label2, label3, label4, label5, label6, label7, label8, label9, label10, label11, label12, label13;
	
	private Sphere object;
	
	private List<Planet> planetList = new ArrayList<Planet>();
	
	private int displayCount = 0;
	
	public EducationSimulation() {
		
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
        
        Button closeDetails = new Button(">");
        closeDetails.setFont(new java.awt.Font("Arial", Font.BOLD, 14));
        closeDetails.setForeground(Color.white);
        closeDetails.setPreferredSize(new Dimension(195,20));
        detailsPanel.add(closeDetails, gbc);
        
        gbc.gridy++;
        Button help = new Button("?");
        help.setFont(new java.awt.Font("Arial", Font.BOLD, 14));
        help.setForeground(Color.white);
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
        label4 = new JLabel(" ");
        label4.setForeground(Color.white);
        detailsPanel.add(label4, gbc);

        gbc.gridy++;
        label5 = new JLabel(" ");
        label5.setForeground(Color.white);
        detailsPanel.add(label5, gbc);
        
        gbc.gridy++;
        label6 = new JLabel(" ");
        label6.setForeground(Color.white);
        detailsPanel.add(label6, gbc);
        
        gbc.gridy++;
        label7 = new JLabel(" ");
        label7.setForeground(Color.white);
        detailsPanel.add(label7, gbc);
        
        gbc.gridy++;
        label8 = new JLabel(" ");
        label8.setForeground(Color.white);
        detailsPanel.add(label8, gbc);
        
        gbc.gridy++;
        label9 = new JLabel(" ");
        label9.setForeground(Color.white);
        detailsPanel.add(label9, gbc);
        
        gbc.gridy++;
        label10 = new JLabel(" ");
        label10.setForeground(Color.white);
        detailsPanel.add(label10, gbc);
        
        gbc.gridy++;
        label11 = new JLabel(" ");
        label11.setForeground(Color.white);
        detailsPanel.add(label11, gbc);
        
        gbc.gridy++;
        label12 = new JLabel(" ");
        label12.setForeground(Color.white);
        detailsPanel.add(label12, gbc);
        
        gbc.gridy++;
        label13 = new JLabel(" ");
        label13.setForeground(Color.white);
        detailsPanel.add(label13, gbc);
        
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
        
        Border greyLine = BorderFactory.createLineBorder(Color.DARK_GRAY , 1);
        Border raisedbevel = BorderFactory.createRaisedBevelBorder();
        Border loweredbevel = BorderFactory.createLoweredBevelBorder();
        Border compound = BorderFactory.createCompoundBorder(
                raisedbevel, loweredbevel);
        compound = BorderFactory.createCompoundBorder(greyLine, compound);
        
        detailsPanel.setBorder(compound);
        detailsPanel.setPreferredSize(new Dimension(210,0));
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
        		
        		new Main();
        		dispose();
        	}
        	
        });
        
        Canvas3D c = new Canvas3D(SimpleUniverse.getPreferredConfiguration() );
        
        c.addKeyListener(this);
        
        cp.add(c, BorderLayout.CENTER);
        
        BranchGroup scene = new BranchGroup();
        createSceneGraph(scene);
        
        pickCanvas = new PickCanvas(c, scene);
        pickCanvas.setMode(PickCanvas.BOUNDS);
        
        SimpleUniverse u = new SimpleUniverse(c);
        u.addBranchGraph(scene);
        
        c.addMouseListener(this);
        
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
	
	public TransformGroup createRotationalTransform(int rotationTime, boolean directionForward) {
		
		TransformGroup rotationTransformGroup = new TransformGroup();
		rotationTransformGroup.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
		Alpha rotationAlpha = new Alpha(-1, rotationTime);
		float angle;
		
		if (directionForward)
			angle = (float) (2.0f * Math.PI);
		else
			angle = (float) (-2.0f * Math.PI);
		
		RotationInterpolator rotationInterpolator = new RotationInterpolator(
				rotationAlpha, rotationTransformGroup, new Transform3D(), 0.0f, angle);
		rotationInterpolator.setSchedulingBounds(bounds);
		rotationTransformGroup.addChild(rotationInterpolator);
		
		return rotationTransformGroup;
	}
	
	public TransformGroup createTranslationTransform(double x, double y, double z) {
		
		TransformGroup translateTransformGroup = new TransformGroup();
		Transform3D translation3D = new Transform3D();
		translation3D.setTranslation(new Vector3d(x,y,z));
		translateTransformGroup.setTransform(translation3D);
		return translateTransformGroup;
	}

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
	
	public TransformGroup createPlanetAndOrbit(Planet planet) {

		int primflags = Primitive.GENERATE_NORMALS
		        + Primitive.GENERATE_TEXTURE_COORDS;
		
		double ratioDistance = 0;
		int orbitalPeriod = 0;
		
		switch (planet.getName()) {
		case "Mercury":
			
			object = new Sphere((float)planet.getSize()/100000, primflags, 100, getAppearance(getClass().getClassLoader().getResource("2k_mercury.jpg")));
			object.setUserData(planet);

			ratioDistance = planet.getDistance() / 0.9;

			orbitalPeriod = 880;
			break;
		case "Venus":
			
			object = new Sphere((float)planet.getSize()/100000, primflags, 100, getAppearance(getClass().getClassLoader().getResource("2k_venus.jpg")));
			object.setUserData(planet);

			ratioDistance = planet.getDistance() / 0.9;

			orbitalPeriod = (int) Math.sqrt(Math.pow(ratioDistance, 3))*100000;
			
			orbitalPeriod = 2247;
			break;
		case "Earth":
			
			object = new Sphere((float)planet.getSize()/100000, primflags, 100, getAppearance(getClass().getClassLoader().getResource("2k_earth.jpg")));
			object.setUserData(planet);

			Sphere moon = new Sphere(0.03f, primflags, 100, getAppearance(getClass().getClassLoader().getResource("2k_moon.jpg")));
			moon.setUserData(planet);
			
			TransformGroup tg = new TransformGroup();
			Transform3D t3D = new Transform3D();
			t3D.setTranslation(new Vector3d(0.18f,0.0f,0.0f));
			
			tg.setTransform(t3D);
			
			TransformGroup moonOrbit = createRotationalTransform(360, true);
			
			moonOrbit.addChild(tg);
			tg.addChild(moon);
			object.addChild(moonOrbit);
			
			ratioDistance = planet.getDistance() / 0.9;

			orbitalPeriod = (int) Math.sqrt(Math.pow(ratioDistance, 3))*100000;
			
			orbitalPeriod = 3652;
			break;
		case "Mars":
			
			object = new Sphere((float)planet.getSize()/100000, primflags, 100, getAppearance(getClass().getClassLoader().getResource("2k_mars.jpg")));
			object.setUserData(planet);

			ratioDistance = planet.getDistance() / 0.9;

			orbitalPeriod = (int) Math.sqrt(Math.pow(ratioDistance, 3))*100000;
			
			orbitalPeriod = 6870;
			break;
		case  "Jupiter":
			
			object = new Sphere((float)planet.getSize()/100000, primflags, 100, getAppearance(getClass().getClassLoader().getResource("2k_jupiter.jpg")));
			object.setUserData(planet);

			ratioDistance = planet.getDistance() / 0.9;

			orbitalPeriod = (int) Math.sqrt(Math.pow(ratioDistance, 3))*100000;
			
			orbitalPeriod = 43310;
			break;
		case "Saturn":
			
			object = new Sphere((float)planet.getSize()/100000, primflags, 100, getAppearance(getClass().getClassLoader().getResource("2k_saturn.jpg")));
			object.setUserData(planet);

			//add rings
			Cylinder ring = new Cylinder((float)(planet.getSize()/100000)+0.9f,0.03f,primflags, 50, 50, getAppearance(getClass().getClassLoader().getResource("2k_moon.jpg")));
			 
			ring.setUserData(planet);
			
			object.addChild(ring);
			
			ratioDistance = planet.getDistance() / 0.9;

			orbitalPeriod = (int) Math.sqrt(Math.pow(ratioDistance, 3))*100000;
			
			orbitalPeriod = 107470;
			break;
		case "Uranus":
			
			object = new Sphere((float)planet.getSize()/100000, primflags, 100, getAppearance(getClass().getClassLoader().getResource("2k_uranus.jpg")));
			object.setUserData(planet);

			ratioDistance = planet.getDistance() / 0.9;

			orbitalPeriod = (int) Math.sqrt(Math.pow(ratioDistance, 3))*100000;
			
			orbitalPeriod = 305890;
			break;
		case "Neptune":
			
			object = new Sphere((float)planet.getSize()/100000, primflags, 100, getAppearance(getClass().getClassLoader().getResource("2k_neptune.jpg")));
			object.setUserData(planet);

			ratioDistance = planet.getDistance() / 0.9;

			orbitalPeriod = (int) Math.sqrt(Math.pow(ratioDistance, 3))*100000;
			
			orbitalPeriod = 598000;
			break;
		case "Pluto":
			
			object = new Sphere((float)planet.getSize()/100000, primflags, 100, getAppearance(getClass().getClassLoader().getResource("2k_moon.jpg")));
			object.setUserData(planet);

			ratioDistance = planet.getDistance() / 0.9;

			orbitalPeriod = (int) Math.sqrt(Math.pow(ratioDistance, 3))*100000;
			
			orbitalPeriod = 905600;
			break;
		
		}
		
		TransformGroup objectOrbit = createRotationalTransform(orbitalPeriod, true);
		
		TransformGroup objectTranslate = createTranslationTransform(planet.getDistance(),0,0);
		
		objectOrbit.addChild(objectTranslate);
		objectTranslate.addChild(object);
		
		return objectOrbit;
	}
	
	public void createSceneGraph(BranchGroup objRoot) {
		
		TransformGroup mainTG = new TransformGroup();		
		mainTG.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
		mainTG.setCapability(TransformGroup.ALLOW_TRANSFORM_READ);

        Planet planet1 = new Planet("Mercury", 4879, 2, 0.33, "White");
        Planet planet2 = new Planet("Venus", 12104, 3, 4.87, "Grey");
        Planet planet3 = new Planet("Earth", 12756, 4, 5.97, "Blue");
        Planet planet4 = new Planet("Mars", 6794, 5, 0.642, "Red");
        Planet planet5 = new Planet("Jupiter", 142980, 7, 1898, "Blue");
        Planet planet6 = new Planet("Saturn", 120540, 10, 568, "LightGreen");
        Planet planet7 = new Planet("Uranus", 51120, 12, 86.8, "Blue");
        Planet planet8 = new Planet("Neptune", 49530, 13, 102, "Blue");
        Planet planet9 = new Planet("Pluto", 2300, 14, 0.0146, "Grey");
        
        planetList.add(planet1);
        planetList.add(planet2);
        planetList.add(planet3);
        planetList.add(planet4);
        planetList.add(planet5);
        planetList.add(planet6);
        planetList.add(planet7);
        planetList.add(planet8);
        planetList.add(planet9);
        
        int primflags = Primitive.GENERATE_NORMALS
		        + Primitive.GENERATE_TEXTURE_COORDS;
        
        Planet sunPlanet = new Planet("Sun",1392684,0,1989000, "Yellow");
        Sphere sun = new Sphere((float)sunPlanet.getSize()/750000, primflags, 100, getAppearance(getClass().getClassLoader().getResource("2k_sun.jpg")));
        sun.setUserData(sunPlanet);
        
        objRoot.addChild(mainTG);
		mainTG.addChild(sun);
		
		AmbientLight ambientLight = new AmbientLight(new Color3f(3.0f, 3.0f, 3.0f));
	    ambientLight.setInfluencingBounds(bounds);
	    objRoot.addChild(ambientLight);
	    
        for (Planet planet: planetList) {
        	TransformGroup planetTG = createPlanetAndOrbit(planet);
        	mainTG.addChild(planetTG);
        }
		
		
		/*-- Start of functionality of the 3D universe--*/
		// *** Create the rotate behaviour node
		MouseRotate behavior = new MouseRotate();
		behavior.setTransformGroup(mainTG);
		behavior.setSchedulingBounds(bounds);
		objRoot.addChild(behavior);
		
		// *** Create the zoom behaviour node
		MouseZoom behavior2 = new MouseZoom();
		behavior2.setTransformGroup(mainTG);
		behavior2.setSchedulingBounds(bounds);
		objRoot.addChild(behavior2);
		
		// *** Create the translate behaviour node
		MouseTranslate behavior3 = new MouseTranslate();
		behavior3.setTransformGroup(mainTG);
		behavior3.setSchedulingBounds(bounds);
		objRoot.addChild(behavior3);
		/*-- End of functionality of the 3D universe--*/
		
		//To set a Background image:
		ImageComponent2D image = new TextureLoader(
				getClass().getClassLoader().getResource("SimBackground.jpg"), this).getImage();
		Background back = new Background( image );
		back.setApplicationBounds( bounds );

		objRoot.addChild(back);
		
	}

	public void setFacts(Planet planet) {
		
		switch (planet.getName()) {
		case "Mercury":
			label4.setText("________________________________");
			label5.setText("Planet Type: Terrestrial");
			label6.setText("Year Length: 88 days");
			label7.setText("Day Length: 175.97 days");
			label8.setText("Number of moons: 0");
			label9.setText("Rings: No");
			label10.setText("Temperature (°C): -170/449");
			label11.setText("Fun Fact: ");
			label12.setText("Sun appears 3 times");
			label13.setText("larger on it's surface.");
			break;
		case "Venus":
			label4.setText("________________________________");
			label5.setText("Planet Type: Terrestrial");
			label6.setText("Year Length: 225 days");
			label7.setText("Day Length: 243 days");
			label8.setText("Number of moons: 0");
			label9.setText("Rings: No");
			label10.setText("Temperature (°C): 870");
			label11.setText("Fun Fact: ");
			label12.setText("Rotates backwards.");
			label13.setText(" ");
			break;
		case "Earth":
			label4.setText("________________________________");
			label5.setText("Planet Type: Terrestrial");
			label6.setText("Year Length: 365.25 days");
			label7.setText("Day Length: 24");
			label8.setText("Number of moons: 1");
			label9.setText("Rings: No");
			label10.setText("Temperature (°C): -89/58");
			label11.setText("Fun Fact: ");
			label12.setText("Atmosphere is 78%");
			label13.setText("Nitrogen and 21% oxygen.");
			break;
		case "Mars":
			label4.setText("________________________________");
			label5.setText("Planet Type: Terrestrial");
			label6.setText("Year Length: 687 days");
			label7.setText("Day Length: 25 hours");
			label8.setText("Number of moons: 2");
			label9.setText("Rings: No");
			label10.setText("Temperature (°C): -125/20");
			label11.setText("Fun Fact: ");
			label12.setText("Called Red planet ");
			label13.setText("as it has iron in it's soil.");
			break;
		case  "Jupiter":
			label4.setText("________________________________");
			label5.setText("Planet Type: Gas Giant");
			label6.setText("Year Length: 4,333 days");
			label7.setText("Day Length: 10 hours");
			label8.setText("Number of moons: 75");
			label9.setText("Rings: Yes");
			label10.setText("Temperature (°C): -145");
			label11.setText("Fun Fact: ");
			label12.setText("Is the width of ");
			label13.setText("11 earths.");
			break;
		case "Saturn":
			label4.setText("________________________________");
			label5.setText("Planet Type: Gas Giant");
			label6.setText("Year Length: 10,759 days");
			label7.setText("Day Length: 10.7 hours");
			label8.setText("Number of moons: 62");
			label9.setText("Rings: Yes");
			label10.setText("Temperature (°C): -178");
			label11.setText("Fun Fact:  ");
			label12.setText("9 of these moons");
			label13.setText("are waiting confirmation.");
			break;
		case "Uranus":
			label4.setText("________________________________");
			label5.setText("Planet Type: Ice Giant");
			label6.setText("Year Length: 30,687 days");
			label7.setText("Day Length: 17 hours");
			label8.setText("Number of moons: 27");
			label9.setText("Rings: Yes");
			label10.setText("Temperature (°C): -224");
			label11.setText("Fun Fact: ");
			label12.setText("Rotates backwards and");
			label13.setText("on it's side.");
			break;
		case "Neptune":
			label4.setText("________________________________");
			label5.setText("Planet Type: Ice Giant");
			label6.setText("Year Length: 60,190 days");
			label7.setText("Day Length: 16 hours");
			label8.setText("Number of moons: 13");
			label9.setText("Rings: Yes");
			label10.setText("Temperature (°C): -214");
			label11.setText("Fun Fact: ");
			label12.setText("At times is further");
			label13.setText("from sun than Pluto.");
			break;
		case "Pluto":
			label4.setText("________________________________");
			label5.setText("Planet Type: Dwarf Planet");
			label6.setText("Year Length: 90,530 days");
			label7.setText("Day Length: 153 hours");
			label8.setText("Number of moons: 5");
			label9.setText("Rings: No");
			label10.setText("Temperature (°C): -223");
			label11.setText("Fun Fact:");
			label12.setText("Surface area smaller");
			label13.setText("than Russia.");
			break;
		
		}
	}
	
	@Override
	public void mouseClicked(MouseEvent e) {
		pickCanvas.setShapeLocation(e);
		
		PickResult result = pickCanvas.pickClosest();
		
		
		if (result == null) {
			label1.setText("Name: Space");
			label2.setText("Diameter: Null");
			label3.setText("Mass: Null");
			label4.setText(" ");
			label5.setText(" ");
			label6.setText(" ");
			label7.setText(" ");
			label8.setText(" ");
			label9.setText(" ");
			label10.setText(" ");
			label11.setText(" ");
			label12.setText(" ");
			label13.setText(" ");
		} 
		else {
			
			Planet planet = (Planet)result.getNode(PickResult.PRIMITIVE).getUserData();
			
			Shape3D s = (Shape3D)result.getNode(PickResult.SHAPE3D); 
			
			if (planet.getName().equals("Sun")) {
				label1.setText("Name: Sun");
				label2.setText("Diameter: "+planet.getSize()+" km");
				label3.setText("Mass: "+planet.getMass()+"e24kg");
				label4.setText(" ");
				label5.setText(" ");
				label6.setText(" ");
				label7.setText(" ");
				label8.setText(" ");
				label9.setText(" ");
				label10.setText(" ");
				label11.setText(" ");
				label12.setText(" ");
				label13.setText(" ");
			} else {
			
				label1.setText("Name: "+planet.getName());
				label2.setText("Diameter: "+planet.getSize()+" km");
				label3.setText("Mass: "+planet.getMass()+"e24kg");
				setFacts(planet);
			}
		}
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

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		
		
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
		setFacts(planetList.get(displayCount));
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
	
}