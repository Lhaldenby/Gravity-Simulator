import java.io.File;
import java.io.IOException;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

/**
 * @author ldh13/ Lewis Haldenby
 * C03015 - Computer Science Project
 * Gravity Simulator in 3D
 */

public class Context {
	
	//used to play music in the program
	private static Clip clip;
	
	//used to tell if user needs to see create warning or not
	private static boolean confirmCreate = false;
	
	//used to tell if user needs to see delete warning or not
	private static boolean confirmDelete = false;
	
	//used to tell if the program needs to auto start the simulation or not
	private static boolean autoStart = false;
	
	//used to tell if the program needs to dislay saftey warnings
	private static boolean safety = true;
	
	public Context() {
		
		//play and loop music clip
		try {
			AudioInputStream audioInputStream =  
			        AudioSystem.getAudioInputStream(getClass().getClassLoader().getResource("media.io_bensound-slowmotion.wav"));

			clip = AudioSystem.getClip();

			clip.open(audioInputStream);
			
			clip.loop(Clip.LOOP_CONTINUOUSLY);
			
		} catch (UnsupportedAudioFileException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (LineUnavailableException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
	
	public static float getVolume() {
	    FloatControl gainControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);        
	    return (float) Math.pow(10f, gainControl.getValue() / 20f);
	}

	public static void setVolume(float volume) {
		
	    FloatControl gainControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);        
	    gainControl.setValue(20f * (float) Math.log10(volume));
	}

	public static boolean getConfirmCreate() {
		return confirmCreate;
	}

	public static void setConfirmCreate(boolean confirmCreate) {
		Context.confirmCreate = confirmCreate;
	}

	public static boolean getConfirmDelete() {
		return confirmDelete;
	}

	public static void setConfirmDelete(boolean confirmDelete) {
		Context.confirmDelete = confirmDelete;
	}

	public static boolean getSafety() {
		return safety;
	}

	public static void setSafety(boolean safety) {
		Context.safety = safety;
	}

	public static boolean getAutoStart() {
		return autoStart;
	}

	public static void setAutoStart(boolean autoStart) {
		Context.autoStart = autoStart;
	}
}