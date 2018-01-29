package Movement;

import java.util.ArrayList;

import Entity.Entity;

public class OrderTestCollisions {

	private ArrayList<CollisionInstance> testContacts;
	
	private ContactManager manager;
	
	public OrderTestCollisions(ContactManager manager){
		if(manager == null){
			manager = new defaultManager();
		}
		this.manager = manager;
	}
	
	public void setTestContacts(ArrayList<CollisionInstance> testContacts){
		this.testContacts = testContacts;
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