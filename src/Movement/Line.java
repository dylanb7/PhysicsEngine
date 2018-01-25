package Movement;

import java.awt.Point;
import java.awt.Rectangle;

public class Line {

	private Point start, end;
	
	private double slope;
	
	private double yIntercept;
	
	public Line(Point start, Point end){
		this.start = start;
		this.end = end;
		double dx = end.x - start.x;
		double dy = end.y - start.y;
		if(dx != 0)
			this.slope = dy/dx;
		else 
			this.slope = 0;
		this.yIntercept = start.y - (slope*start.x);
		if(start.equals(end))
			this.end = new Point(end.x+1,end.y);
	}
	
	public double getSlope(){
		return slope;
	}
	
	public Point getStart(){
		return start;
	}
	
	public Point getEnd(){
		return end;
	}
	
	public double getYIntercept(){
		return yIntercept;
	}
	
	public double getYAtX(int x){
		return (slope*x)+yIntercept;
	}
	
	public boolean intersectsLine(Line line){
		Rectangle self = getRectFromLine(this);
		Rectangle other = getRectFromLine(line);
		return self.intersects(other) && crossing(line,this) && crossing(this, line);
	}
	
	private double getCrossProduct(Point one, Point two){
		return (one.x * two.y) - (one.y * two.x);
	}
	
	private boolean isPointOnLine(Line line, Point point){
		Point temp = new Point(line.getEnd().x-line.getStart().x, line.getEnd().y-line.getStart().y);
		Point other = new Point(point.x - line.getStart().x, point.y - line.getStart().y);
		return Math.abs(getCrossProduct(temp, other)) < 0.000001;
	}
	
	private boolean isPointRightOfLine(Line line, Point point){
		Point temp = new Point(line.getEnd().x-line.getStart().x, line.getEnd().y-line.getStart().y);
		Point other = new Point(point.x - line.getStart().x, point.y - line.getStart().y);
		return getCrossProduct(temp, other) < 0;
	}
	
	private boolean crossing(Line line, Line line1){
		return isPointOnLine(line1, line.getStart()) ||
			   isPointOnLine(line1, line.getEnd()) ||
			   (isPointRightOfLine(line1, line.getStart()) ^ isPointRightOfLine(line1, line.getEnd()));
	}
	
	private Rectangle getRectFromLine(Line line){
		int upperLeftY = getMax(line.getEnd().y, line.getStart().y);
		int upperLeftX = getMin(line.getStart().x, line.getEnd().x);
		int height = upperLeftY - getMin(line.getEnd().y, line.getStart().y);
		int width = getMax(line.getStart().x, line.getEnd().x) - upperLeftX;
		return new Rectangle(upperLeftX, upperLeftY, width, height);
	}
	
	private int getMin(int num1, int num2){
		if(num1 < num2)
			return num1;
		return num2;
	}
	
	private int getMax(int num1, int num2){
		if(num1 > num2)
			return num1;
		return num2;
	}
	
}
