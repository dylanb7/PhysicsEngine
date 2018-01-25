package Frame;

import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JFrame;

import Movement.ContactManager;

public class Frame {

	private JFrame frame;
	
	private SetUpPaint paint;
	
	public Frame(int width, int height, ContactManager manager){
		Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
		frame = new JFrame();
		frame.setLocation((screen.width/2)-(width/2), (screen.height/2)-(height/2));
		frame.setSize(width, height);
		frame.setVisible(true);
		frame.setResizable(false);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		SetUpPaint paint = new SetUpPaint(frame, manager, 10);
		this.paint = paint;
		paint.runActions();
	}
	
	public SetUpPaint getPaint(){
		return paint;
	}
	
}
