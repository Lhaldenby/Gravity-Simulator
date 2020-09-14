import java.util.Enumeration;

import javax.media.j3d.Behavior;

import com.sun.j3d.utils.geometry.Sphere;

public class SunCollisionDetector extends Behavior {

	private Sphere sphere;
	
	public SunCollisionDetector(Sphere s) {
		sphere = s;
	}
	
	@Override
	public void initialize() {
		// TODO Auto-generated method stub
		//adds initial trigger event
		//wakeupcondition and set of values of ay behavior state
	}

	@Override
	public void processStimulus(Enumeration arg0) {
		// TODO Auto-generated method stub
		//invoked when tigger event occurs 
	}
	
}