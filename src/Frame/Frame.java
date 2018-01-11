package Frame;

import javax.swing.JFrame;

import Movement.ContactManager;

public class Frame {

	private JFrame frame;
	
	private SetUpPaint paint;
	
	public Frame(int width, int height, ContactManager manager){
		frame = new JFrame();
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
