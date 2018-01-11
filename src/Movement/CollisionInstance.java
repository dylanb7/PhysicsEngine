package Movement;

import Entity.Entity;

public class CollisionInstance {

	private Entity body1, body2;
	
	public CollisionInstance(Entity ent1, Entity ent2){
		body1 = ent1;
		body2 = ent2;
	}
	
	public Entity getBodyOne(){
		return body1;
	}
	
	public Entity getBodyTwo(){
		return body2;
	}
	
	private boolean isEqual(CollisionInstance instance){
		if(instance.getBodyOne().equals(body1) && instance.getBodyTwo().equals(body2))
			return true;
		return false;
	}
	
	public boolean hasBeenMatched(Entity bod1, Entity bod2){
		if(isEqual(new CollisionInstance(bod1, bod2)) || isEqual(new CollisionInstance(bod2, bod1)))
			return true;
		return false;
	}
	
	public String toString(){
		return body1.getTestMask()+", "+body2.getTestMask();
	}
	
}
