package Entity;

import java.awt.Color;
import java.awt.Image;
import java.awt.Rectangle;
import java.util.HashSet;
import java.util.Timer;
import java.util.TimerTask;

import Movement.Vector;

public class Entity extends Rectangle {

	private boolean isTangable = false, isAffectedByGravity = false, toBeRemoved = false;
	
	private Image image;
	
	private Color color;
	
	private Vector currentVector;
	
	private Image[] animationFrames;
	
	private Timer animationTimer;
	
	private String contactMask = "";
	
	private String controlledContact = "";
	
	private HashSet<String> controlled;
	
	private HashSet<String> collisionTest;
	
	public Entity(int w, int h){
		setBounds(0, 0, w, h);
		collisionTest = new HashSet<>();
		controlled = new HashSet<>();
	}
	
	public Entity(int x, int y, int w, int h){
		setBounds(x, y, w, h);
		collisionTest = new HashSet<>();
		controlled = new HashSet<>();
	}
	
	public void setTestMask(String testMask){
		controlledContact = testMask;
	}
	
	public void setContactMask(String contactMask){
		this.contactMask = contactMask;
	}
	
	public String getTestMask(){
		return controlledContact;
	}
	
	public String getContactMask(){
		return contactMask;
	}
	
	public void addTestMask(Entity entity){
		controlled.add(entity.controlledContact);
	}
	
	public void addContact(Entity entity){
		collisionTest.add(entity.contactMask);
	}
	
	public boolean realContact(Entity contact){
		return collisionTest.contains(contact.contactMask);
	}
	
	public boolean testContact(Entity contact){
		return controlled.contains(contact);
	}
	
	public void setVector(Vector vector){
		currentVector = vector;
	}
	
	public Vector getVector(){
		return currentVector;
	}
	
	public void moveByVector(){
		setBounds(x+currentVector.getX(), y+currentVector.getY(), width, height);
	}
	
	public void setImage(Image image){
		this.image = image;
	}
	
	public Image getImage(){
		return image;
	}
	
	public void setColor(Color color){
		this.color = color;
	}
	
	public Color getColor(){
		return color;
	}
	
	public void setFrames(Image[] frames){
		this.animationFrames = frames;
	}
	
	public void runAnimation(int timePerFrame){
		if(animationFrames.length == 0)
			return;
		animationTimer = new Timer();
		animationTimer.schedule(new TimerTask() {
			int frameIndex = -1;
			@Override
			public void run() {
				frameIndex++;
				if(frameIndex > animationFrames.length - 1)
					animationTimer.cancel();
				setImage(animationFrames[frameIndex]);
			}
		}, 0, timePerFrame);
	}
	
	public void setTangible(boolean isTangible){
		this.isTangable = isTangible;
	}
	
	public void setAffectedByGravity(boolean isAffectedByGravity){
		this.isAffectedByGravity = isAffectedByGravity;
	}
	
	public boolean getTangible(){
		return isTangable;
	}
	
	public boolean isAffectedByGravity(){
		return isAffectedByGravity;
	}
	
	public void setRemoved(){
		toBeRemoved = true;
	}
	
	public boolean getRemoved(){
		return toBeRemoved;
	}
	
	public Rectangle getPostVectorBody(){
		return new Rectangle(x+currentVector.getX(), y+currentVector.getY(), width, height);
	}
	
}
