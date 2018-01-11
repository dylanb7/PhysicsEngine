package Movement;

import java.util.ArrayList;

import Entity.Entity;

public class OrderCollisions {

	private ArrayList<CollisionInstance> collisions;
	
	public OrderCollisions(ArrayList<CollisionInstance> collisions){
		this.collisions = collisions;
	}
	
	public void order(){
		for(int i = 0; i < collisions.size(); i++){
			CollisionInstance collision = collisions.get(i);
			Entity body1 = collision.getBodyOne();
			Entity body2 = collision.getBodyTwo();
			
			
		}
	}
	
}
