package rushhour;

import java.util.ArrayList;
import java.util.Observable;

/**
 * Model / logic for the RushHour game. Contains a 6x6 board of fields. 
 * @author david
 */
public class RushHour extends Observable
{
	public static final int numRows = 6;
	public static final int numCols = 6;
	
	private Field[][] board;
	
	/**
	 * List of all cars on the board.
	 */
	private ArrayList<Car> carList = new ArrayList<>();
	

	public RushHour()
	{
		board = new Field[numRows][numCols];
		
		for (int i = 0; i < numRows; i++)
		{
			for (int j = 0; j < numCols; j++)
			{
				board[i][j] = new Field();
				board[i][j].unBlock();
			}
		}
		
	}
	
	/**
	 * Places a car on the board. If the car is already on the board, the car is replaced.
	 * If the car can be placed or replaced, the free/unfree marks on the board
	 * are adjusted and the car's coordinates are set.
	 * Exceptions thrown:
	 * IllegalArgument exception when the row/column argument is out of range.
	 * 
	 * 
	 *  <p>
	 *  The given coordinates are for the top-left position of the car.
	 *  Rows and columns start with 0. Range is 0-5.
	 *  
	 * @param car The car object to place or replace
	 * @param row The row (vertical coordinate) to place the top-left position of the car
	 * @param column The column (horizontal coordinate) to place the top-left position of the car
	 */
	public void placeCar(Car car, int row, int column)
	{
		if (row < 0 || row >= numRows || column < 0 || column >= numCols)
		{
			throw new IllegalArgumentException("Row or column argument outside board. "
					+ "Row="+ row + ", Column = " + column);
		}
		
		  
		if (car.isOnBoard())
		{
			// clear previous positions
			int startX = car.getXPos();
			int startY = car.getYPos();
			int carSize = car.getSize();
			
			
			carList.remove(car);
			
			if (car.getOrientation() == Car.HORIZONTAL)
			{
				for (int i = startX; i < startX + carSize; i++)
				{
					board[startY][i].unBlock();
				}
				
			}
			else if (car.getOrientation() == Car.VERTICAL)
			{
				for (int i = startY; i < startY + carSize; i++)
				{
					board[i][startX].unBlock();
				}
			}
		}
		
		
		
		
		
		boolean blocked = false;
		boolean outSideBoard = false;
		
		if (car.getOrientation() == Car.HORIZONTAL)
		{

			for (int i = column; i < column + car.getSize(); i++)
			{
				if (i >= numCols)
				{
					outSideBoard = true;
					break;
				}
				
				if (board[row][i].isBlocked())
				{
					blocked = true;
					break;
				}
			}
			
		}
		else if (car.getOrientation() == Car.VERTICAL)
		{
			for (int i = row; i < row + car.getSize(); i++)
			{
				if (i >= numRows)
				{
					outSideBoard = true;
					break;
				}

				
				
				if (board[i][column].isBlocked())
				{
					
					blocked = true;
					break;
				}
			}
		}

		
		if (outSideBoard || blocked)
		{
			if (outSideBoard)
				System.out.println("Trying to place car outside board");
			else
				System.out.println("Blocked, can not place car");
		
			
			if (car.isOnBoard())
			{
				// restore original position
				placeCar(car, car.getYPos(), car.getXPos());
			}
		}
		
		else
		{
			if (car.getOrientation() == Car.HORIZONTAL)
			{
				for (int i = column; i < column + car.getSize(); i++)
				{
					board[row][i].block();
				}
				
			}
			else if (car.getOrientation() == Car.VERTICAL)
			{
				for (int i = row; i < row + car.getSize(); i++)
				{
					board[i][column].block();
				}
			}

		
			car.setYPos(row);
			car.setXPos(column);
			car.setOnBoard(true);
			carList.add(car);
			
			setChanged();
			notifyObservers();
			
		}
		
	}
	
	
	
	public ArrayList<Car> getCarList()
	{
		return carList;
	}
		
	/**
	 * Simple text output of the board's blocked positions.
	 */
	public void printBoard()
	{
		System.out.println("Print bord");
		if (board == null)
		{
			System.out.println("Error, bord null");
		}
		else
		{
			System.out.println("bord rows: "+ board.length);
			System.out.println("bord columns: "+ board[0].length);
			
		}
		
		
		for (Field[] row : board)
		{
			for(Field veld : row)
			{
				if (veld == null)
				{
					System.out.println("veld null");
				}
				else
				{
					if (veld.isBlocked() )
					{
						System.out.print("X");
					}
					else
					{
						System.out.print("O");
					}
					

					
				}
				
			}
			System.out.println();
		}

	}
		

	
	
	
	
	public static void main(String[] args)
	{
		System.out.println("Test rushhour");
		RushHour rh = new RushHour();
		
		Car car1 = new Car(2, Car.VERTICAL);
		
		rh.placeCar(car1, 2, 1);
		
		rh.placeCar(car1, 4, 1);
		
		rh.printBoard();
		
		
		
	
		
	}
			
}
