package Movement;

import java.awt.Point;
import java.awt.Rectangle;
import java.util.ArrayList;

import org.omg.PortableServer.ID_ASSIGNMENT_POLICY_ID;

import Entity.Entity;

public class HandleCollisions {

	private ArrayList<CollisionInstance> currentOverlap;
	
	private ArrayList<CollisionInstance> customOverlap;
	
	private ArrayList<Entity> entities;
	
	private Vector gravityVector;
	
	private ContactManager manager;
	
	private OrderTestCollisions testOrder;
	
	private OrderCollisions collisionOrder;
	
	public HandleCollisions(ArrayList<Entity> entities, ContactManager manager){
		currentOverlap = new ArrayList<>();
		customOverlap = new ArrayList<>();
		this.gravityVector = new Vector(0, 1);
		this.entities = entities;
		this.manager = manager;
		this.testOrder = new OrderTestCollisions(manager);
		this.collisionOrder = new OrderCollisions();
	}
	
	public void orderEntities(){
		evaluateEntitys();
		testOrder.setTestContacts(customOverlap);
		testOrder.order();
		collisionOrder.setCollisions(currentOverlap);
		collisionOrder.order();
		for(int i = 0; i < entities.size(); i++)
			entities.get(i).moveByVector();
	}
	
	private void evaluateEntitys(){
		currentOverlap.clear();
		customOverlap.clear();
		applyGravity();
		for(int i = 0; i < entities.size(); i++){
			loop:
			for(int j = 0; j < entities.size(); j++){
				if(i == j)
					continue;
				Entity body1 = entities.get(i);
				Entity body2 = entities.get(j);
				if(!body1.getTangible() || !body2.getTangible())
					continue;

				for(CollisionInstance k: currentOverlap)
					if(k.hasBeenMatched(body1, body2))
						continue loop;
				if(body1.getPostVectorBody().intersects(body2.getPostVectorBody()) || pathsCross(body1, body2)){
					if(body1.realContact(body2) || body2.realContact(body1))
						currentOverlap.add(new CollisionInstance(body1, body2));
					if(body1.testContact(body2) || body2.testContact(body1))
						customOverlap.add(new CollisionInstance(body1, body2));
					continue loop;
				}
			}
		}
	}
	
	
	private void applyGravity(){
		for(int i = 0; i < entities.size(); i++){
			Entity curr = entities.get(i);
			if(curr.getRemoved()){
				entities.remove(curr);
				curr = null;
				continue;
			}
			if(curr.isAffectedByGravity())
				curr.getVector().addVector(gravityVector);
		}		
	}
	
	private boolean pathsCross(Entity body1, Entity body2){
		Line[] one = getLinesFromEntitys(body1);
		Line[] two = getLinesFromEntitys(body2);
		for(int i = 0; i < one.length; i++)
			for(int j = 0; j < two.length; j++)
				if(one[i].intersectsLine(two[j]))
					return true;	
		return false;
	}
	
	private Line[] getLinesFromEntitys(Entity body){
		Line[] lines  = new Line[2];
		Rectangle post = body.getPostVectorBody();
		Vector v = body.getVector();
		int quad = v.getQuadrant(v);
		if(quad == 1 || quad == 3){
			lines[0] = new Line(new Point(body.x, body.y), new Point(post.x, post.y));
			lines[1] = new Line(new Point(body.x+body.width, body.y+body.height), new Point(post.x+post.width, post.y+post.height));
		}else{
			lines[0] = new Line(new Point(body.x, body.y+body.height), new Point(post.x, post.y+post.height));
			lines[1] = new Line(new Point(body.x+body.width, body.y), new Point(post.x+post.width, post.y));	
		}
		return lines;
	}
	
}
