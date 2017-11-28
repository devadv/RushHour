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
	
	private int column = 0; // xPosition, horizontal
	private int row = 0; // yPosition, vertical
	
	private boolean onBoard = false;
	
	private Color color = Color.BLUE;
	
	private boolean selected = false;
	
	Car(int size, int orientation)
	{
		this(size, orientation, Color.BLUE);
	}
	
	
	Car(int size, int orientation, Color color)
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
		this.color = color;
				
	}

	
	
	
	/**
	 * Returns the length of the car. That is the number of blocks the car spans. 2 for normal cars 3 for trucks.
	 * @return Size, number of blocks that the car spans. 
	 */
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
	
	/**
	 * Sets the horizontal position (column) of the car.
	 * @param xPos
	 */
	public void setColumn(int column)
	{
		this.column = column;
	}
	
	/**
	 * Sets the vertical position (row) of the car.
	 * @param yPos
	 */
	public void setRow(int row)
	{
		this.row = row;
	}
	
	/**
	 * Gets the horizontal position (column) of the car.
	 * @return Horizontal / column / X position
	 */
	public int getColumn()
	{
		return column;
	}
	
	/**
	 * Gets the vertical position (row) of the car.
	 * @return Vertical / row / Y Position
	 */
	public int getRow()
	{
		return row;
	}
	
	public boolean isOnBoard()
	{
		return onBoard;
	}
	
	public void setOnBoard(boolean onBoard)
	{
		this.onBoard = onBoard;
	}
	
	
	public void select()
	{
		selected = true;
	}
	
	public void deselect()
	{
		selected = false;
	}
	
	public boolean isSelected()
	{
		return selected;
	}
	
	
}
