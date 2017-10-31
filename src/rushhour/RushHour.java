package rushhour;

public class RushHour
{
	public static final int numRows = 6;
	public static final int numCols = 6;
	
	private Veld[][] bord;
	

	public RushHour()
	{
		bord = new Veld[numRows][numCols];
		
		for (int i = 0; i < numRows; i++)
		{
			for (int j = 0; j < numCols; j++)
			{
				bord[i][j] = new Veld();
				bord[i][j].unBlock();
			}
		}
		
	}
	
	
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
			
			if (car.getOrientation() == Car.HORIZONTAL)
			{
				for (int i = startX; i < startX + carSize; i++)
				{
					bord[startY][i].unBlock();
				}
				
			}
			else if (car.getOrientation() == Car.VERTICAL)
			{
				for (int i = startY; i < startY + carSize; i++)
				{
					bord[i][startX].unBlock();
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
				
				if (bord[row][i].isBlocked())
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

				
				
				if (bord[i][column].isBlocked())
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
					bord[row][i].block();
				}
				
			}
			else if (car.getOrientation() == Car.VERTICAL)
			{
				for (int i = row; i < row + car.getSize(); i++)
				{
					bord[i][column].block();
				}
			}

		
			car.setYPos(row);
			car.setXPos(column);
			car.setOnBoard(true);
			
		}
		
	}
	
	
	
	
		
		
	public void printBoard()
	{
		System.out.println("Print bord");
		if (bord == null)
		{
			System.out.println("Error, bord null");
		}
		else
		{
			System.out.println("bord rows: "+ bord.length);
			System.out.println("bord columns: "+ bord[0].length);
			
		}
		
		
		for (Veld[] row : bord)
		{
			for(Veld veld : row)
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
		
		rh.placeCar(car1,2, 1);
		
		rh.placeCar(car1,4, 1);
		
		rh.printBoard();
		
	
		
	}
			
}
