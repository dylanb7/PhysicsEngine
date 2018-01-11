package Frame;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JFrame;

import Entity.Entity;
import Movement.ContactManager;
import Movement.HandleCollisions;

public class SetUpPaint {
	
	private ArrayList<Entity> entities;
	
	private int repaintsPerMinute;
	
	private HandleCollisions collisions;
	
	private Painter painter;
	
	private Timer timer;
	
	public SetUpPaint(JFrame frame, ContactManager manager, int repaintsPerMinute){
		this.repaintsPerMinute = repaintsPerMinute;
		this.painter = new Painter(entities);
		this.collisions = new HandleCollisions(entities, manager);
		frame.add(painter);
		frame.validate();
	}
	
	public void runActions(){
		timer = new Timer();
		timer.schedule(new TimerTask() {
			
			@Override
			public void run() {
				collisions.orderEntities();
				painter.setEntities(entities);
				
			}
		}, 0, 1000/repaintsPerMinute);
	}
	
	public void stopUpdates(){
		if(timer != null)
			timer.cancel();
	}
	
}
