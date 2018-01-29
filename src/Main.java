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
		contact c = new contact();
		Entity grass = new Entity(0,400,1000,100);
		grass.setColor(Color.GREEN);
		grass.setTestMask("Grass");
		Entity ball = new Entity(10,10,20,20);
		ball.setColor(Color.RED);
		ball.setAffectedByGravity(true);
		ball.setTestMask("Ball");
		ball.addTestMask(grass);
		ball.setVector(new Vector(2,0));
		Frame frame = new Frame(1000,500,c,null,35);
		frame.addEntity(grass);
		frame.addEntity(ball);
		frame.startPaint();
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

