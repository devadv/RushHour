package rushhour;

import java.awt.Color;


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
