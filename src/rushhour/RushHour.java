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
	
	public static final int PORTROW = 2;
	
	private Field[][] board;
	
	
	private boolean designMode = false;
	
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
	 * Does nothing when the row/column argument is out of range.
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
			//throw new IllegalArgumentException("Row or column argument outside board. "
				//	+ "Row="+ row + ", Column = " + column);
			if (row == RushHour.PORTROW && column >= numCols)
			{
				System.out.println("Car is through port");
			}
			else
			{
				System.out.println("Trying to place car outside board. Row=" + row + " column=" + column);
				return;
			}
		}
		
		  
		if (car.isOnBoard())
		{
			// clear previous positions
			int startX = car.getColumn();
			int startY = car.getRow();
			int carSize = car.getSize();
			
			
			carList.remove(car);
			
			if (car.getOrientation() == Car.HORIZONTAL)
			{
				for (int i = startX; i < startX + carSize && i < numCols; i++)
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
					if (car.getRow() != RushHour.PORTROW)
					{
						outSideBoard = true;
					}
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
				placeCar(car, car.getRow(), car.getColumn());
			}
		}
		
		else
		{
			if (car.getOrientation() == Car.HORIZONTAL)
			{
				for (int i = column; i < column + car.getSize() && i < numCols; i++)
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

		
			car.setRow(row);
			car.setColumn(column);
			car.setOnBoard(true);
			carList.add(car);
			
			setChanged();
			notifyObservers();
			
		}
		
	}
	
	
	/**
	 * Moves car forward or back
	 * @param car Car to move 
	 * @param steps <ul><li>Positive is forward (to the right or down).
	 * 				    <li>Negative is backward (to the left or up).
	 * 					</ul>
	 */
	public void moveCar (Car car, int steps)
	{
		if (car.getOrientation() == Car.HORIZONTAL)
		{
			placeCar(car, car.getRow(), car.getColumn() + steps);
		}
		else if ( car.getOrientation() == Car.VERTICAL)
		{
			placeCar(car, car.getRow() + steps, car.getColumn());
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
		

	/**
	 * Finds the car at the given board position.
	 * @param row Row position / vertical position
	 * @param column Column position / horizontal position
	 * @return Car object at the position 
	 */
	public Car findCar(int row, int column)
	{
		Car foundCar = null;
		for(Car car : carList)
		{
			if (car.getRow() == row && car.getColumn() == column)
			{
				foundCar = car;
				break;
			}
			
			
			if (car.getOrientation() == Car.HORIZONTAL)
			{
				if (car.getRow() == row)
				{
					for (int i = 1; i < car.getSize(); i++)
					{
						if (column == car.getColumn() + i)
						{
							foundCar = car;
							break;
						}
					}
				}
			}
			else if (car.getOrientation() == Car.VERTICAL)
			{
				if (car.getColumn() == column)
				{
					for (int i = 1; i < car.getSize(); i++)
					{
						if (row == car.getRow() + i)
						{
							foundCar = car;
							break;
						}
					}
				}
			}
			
		}
		
		return foundCar;
	}
	
	
	
	public boolean isDesignMode()
	{
		return designMode;
	}
	public void setDesignMode(boolean designModeOn)
	{
		designMode = designModeOn;
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
