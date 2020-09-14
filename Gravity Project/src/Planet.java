import java.io.Serializable;

import javax.media.j3d.Appearance;

/**
 * @author ldh13/ Lewis Haldenby
 * C03015 - Computer Science Project
 * Gravity Simulator in 3D
 */

public class Planet implements Serializable{
	
	private String name;
	private int size;
	private double distance;
	private double mass;
	private String app;
	
	public Planet(String name, int size, double distance, double mass, String app) {
		this.name = name;
		this.size = size;
		this.distance = distance;
		this.mass = mass;
		this.app = app;
	}
	
	public double getDistance() {
		return distance;
	}
	
	public void setDistance(double distance) {
		this.distance = distance;
	}
	
	public int getSize() {
		return size;
	}
	
	public void setSize(int size) {
		this.size = size;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String toString(){
		return "Name: " + name + ", Size: " + Integer.toString(size) +
				", Distance: " + Double.toString(distance) + ", Mass: " + Double.toString(mass) + ", Appearance: " + app;
	}

	public double getMass() {
		return mass;
	}

	public void setMass(double mass) {
		this.mass = mass;
	}
	
	public String getAppearance() {
		return app;
	}

	public void setAppearance(String app) {
		this.app = app;
	}
	
}