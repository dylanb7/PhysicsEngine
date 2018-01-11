package Movement;

public class Vector {

	private int dx,dy;
	
	public Vector(int dx, int dy){
		this.dx = dx;
		this.dy = dy;
	}
	
	public double getMagnitude(){
		return Math.sqrt((dx*dx) + (dy*dy));
	}
	
	public void addVector(Vector vector){
		dx += vector.getX();
		dy += vector.getY();
	}
	
	public void subVector(Vector vector){
		dx -= vector.getX();
		dy -= vector.getY();
	}
	
	public int getQuadrant(Vector vector){
		if(vector.getX() < 0){
			if(vector.getY() < 0)
				return 3;
			return 2;
		}else{
			if(vector.getY() < 0)
				return 4;
			return 1;
		}
	}
	
	public int getX(){
		return dx;
	}
	
	public int getY(){
		return dy;
	}
	
}
