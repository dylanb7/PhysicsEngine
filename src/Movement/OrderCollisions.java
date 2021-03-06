package Movement;

import java.util.ArrayList;

import Entity.Entity;

public class OrderCollisions {

	private ArrayList<CollisionInstance> collisions;
	
	public void setCollisions(ArrayList<CollisionInstance> collisions){
		this.collisions = collisions;
	}
	
	public void order(){
		for(int i = 0; i < collisions.size(); i++){
			CollisionInstance collision = collisions.get(i);
			Entity body1 = collision.getBodyOne();
			Entity body2 = collision.getBodyTwo();
			body1.setVector(new Vector(0,0));
			body2.setVector(new Vector(0,0));
		}
	}
	
}
