package Frame;

import java.awt.Color;
import java.awt.event.KeyEvent;

import Entity.Entity;
import Frame.Frame;
import Frame.KeyManager;
import Movement.CollisionInstance;
import Movement.ContactManager;
import Movement.Vector;

public class Main {
	
	public static void main(String[] args) {
		keys k = new keys();
		Frame frame = new Frame(1000,500,null,k,35);
		k.setFrame(frame);
		frame.startPaint();
	}
    
}

class keys implements KeyManager {

	private Frame frame;
	
	public void setFrame(Frame frame){
		this.frame = frame;
	}
	
	private void add(){
		Color[] colors = {Color.CYAN, Color.MAGENTA, Color.RED, Color.ORANGE, Color.YELLOW, Color.BLUE, Color.GREEN};
		for(int i = 0; i < 10; i++){
			Entity ent = new Entity(487,100,26,26);
			ent.setAffectedByGravity(true);
			ent.setVector(new Vector((int)(Math.random()*21)-10,-(int)(Math.random()*14)));
			ent.setColor(colors[(int)(Math.random()*colors.length)]);
			frame.addEntity(ent);
		}
	}
	
	@Override
	public void pressed(KeyEvent pressed) {
		// TODO Auto-generated method stub
		add();
	}

	@Override
	public void released(KeyEvent released) {
		// TODO Auto-generated method stub
		
	}
	
	
}

class contact  implements ContactManager {

	private int currBounce = -25;
	
	@Override
	public void handle(CollisionInstance collision) {
		Entity one = collision.getBodyOne();
		Entity two = collision.getBodyTwo();
		currBounce+=1;
		if(currBounce > 0)
			currBounce = 0;
		if(one.getTestMask().equals("Ball")){
			one.setVector(new Vector(one.getVector().getX(),currBounce));
		}else if(two.getTestMask().equals("Ball")){
			two.setVector(new Vector(two.getVector().getX(),currBounce));
		}
	}
	
}

