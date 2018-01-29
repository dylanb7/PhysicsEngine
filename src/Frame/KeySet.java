package Frame;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.HashSet;

import javax.swing.JFrame;

public class KeySet implements KeyListener {
	
	private HashSet<KeyEvent> keys;
	
	private KeyManager manager;
	
	public KeySet(JFrame frame, KeyManager manager){
		keys = new HashSet<>();
		frame.addKeyListener(this);
		this.manager = manager;
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
	}

	@Override
	public void keyPressed(KeyEvent e) {
		keys.add(e);
		manager.pressed(e);
	}

	@Override
	public void keyReleased(KeyEvent e) {
		keys.remove(e);
		manager.released(e);
	}

	public HashSet<KeyEvent> getPressed(){
		return keys;
	}
	
}
