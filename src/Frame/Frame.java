package Frame;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

import javax.swing.JFrame;

import Entity.Entity;
import Movement.ContactManager;

public class Frame {

	private JFrame frame;
	
	private SetUpPaint paint;
	
	public Frame(int width, int height, ContactManager manager, KeyManager keys, int paintsPerSecond){
		Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
		frame = new JFrame();
		frame.setLocation((screen.width/2)-(width/2), (screen.height/2)-(height/2));
		frame.setSize(width, height);
		frame.setVisible(true);
		frame.setResizable(false);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		if(keys == null)
			keys = new defaultKeys();
		KeySet set = new KeySet(frame, keys);
		SetUpPaint paint = new SetUpPaint(frame, manager, paintsPerSecond);
		this.paint = paint;
	}
	
	public void startPaint(){
		paint.runActions();
	}
	
	public void stopPaint(){
		paint.stopUpdates();
	}
	
	public void addEntity(Entity ent){
		paint.getPaint().addEntity(ent);
	}
	
	public void setEntities(ArrayList<Entity> ents){
		paint.getPaint().setEntities(ents);
	}
	
}

class defaultKeys implements KeyManager{

	@Override
	public void pressed(KeyEvent pressed) {
	}

	@Override
	public void released(KeyEvent released) {
	}
	
}
