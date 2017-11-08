package rushhour;

import java.awt.Color;

/**
 * All vehicles are a Car object. The size of the car is 2 (two blocklengths on the board) for a
 * normal car and 3 for a truck.  The orientation can be horizontal or vertical. The position is
 * stored and whether or not the car is placed on the board.
 * @author david
 */
public class Car
{
	public static final int HORIZONTAL = 0;
	public static final int VERTICAL = 1;
	
	private int size;
	private int orientation;
	
	private int xPos = 0;
	private int yPos = 0;
	
	private boolean onBoard = false;
	
	private Color color = Color.BLUE;
	
	Car(int size, int orientation)
	{
		if ( size < 2 || size > 3)
		{
			throw new IllegalArgumentException(size+ ": Size out of range");
		}
		if (orientation < 0 || orientation > 1)
		{
			throw new IllegalArgumentException(orientation+ ": invalid orientation");
		}
		
		
		this.size = size;
		this.orientation = orientation;
		
	}
	
	public int getSize()
	{
		return size;
	}
	
	public int getOrientation()
	{
		return orientation;
	}
	
	public Color getColor()
	{
		return color;
	}
	
	public void setColor(Color color)
	{
		this.color = color;
	}
	
	public void setXPos(int xPos)
	{
		this.xPos = xPos;
	}
	
	public void setYPos(int yPos)
	{
		this.yPos = yPos;
	}
	
	public int getXPos()
	{
		return xPos;
	}
	
	public int getYPos()
	{
		return yPos;
	}
	
	public boolean isOnBoard()
	{
		return onBoard;
	}
	
	public void setOnBoard(boolean onBoard)
	{
		this.onBoard = onBoard;
	}
	
	
}
