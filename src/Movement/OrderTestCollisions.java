package Movement;

import java.util.ArrayList;

import Entity.Entity;

public class OrderTestCollisions {

	private ArrayList<CollisionInstance> testContacts;
	
	private ContactManager manager;
	
	public OrderTestCollisions(ArrayList<CollisionInstance> testContacts, ContactManager manager){
		this.testContacts = testContacts;
		if(manager == null){
			manager = new defaultManager();
		}
		this.manager = manager;
	}
	
	public void order(){
		for(int i = 0; i < testContacts.size(); i++){
			manager.handle(testContacts.get(i));
		}
	}
	
}

class defaultManager implements ContactManager{

	@Override
	public void handle(CollisionInstance collision) {
		Entity body1 = collision.getBodyOne();
		Entity body2 = collision.getBodyTwo();
		System.out.println(body1+", "+body2);
		System.out.println(body1+", "+body2);
	}
	
}