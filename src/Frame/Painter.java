package Frame;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;

import javax.swing.JComponent;
import javax.swing.JFrame;

import Entity.Entity;

public class Painter extends JComponent{

	private boolean showOutline = false;
	
	private ArrayList<Entity> entities;
	
	private JFrame frame;
	
	public Painter(ArrayList<Entity> entities, JFrame frame){
		setEntities(entities);
		this.frame = frame;
	}
	
	public void setEntities(ArrayList<Entity> entities){
		this.entities = entities;
		repaint();
	}
	
	public void showsOutline(boolean showing){
		showOutline = showing;
	}
	
	protected void paintComponent(Graphics g){
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, frame.getWidth(), frame.getHeight());
		System.out.println("Ran");
		for(Entity entity : entities){
			if(entity.getColor() != null){
				g.setColor(entity.getColor());
				g.drawRect(entity.x, entity.y, entity.width, entity.height);
			}else if(entity.getImage() != null){
				g.drawImage(entity.getImage(), entity.x, entity.y, entity.width, entity.height, this);
			}
			if(showOutline){
				g.setColor(Color.BLACK);
				g.drawRect(entity.x, entity.y, entity.width, entity.height);
			}
		}	
	}
	
}
