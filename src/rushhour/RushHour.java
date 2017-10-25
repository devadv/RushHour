package rushhour;

public class RushHour
{
	public static final int numRows = 6;
	public static final int numCols = 6;
	
	Veld[][] bord = new Veld[numRows][numCols];
	
	
	public RushHour()
	{
		for (Veld[] row : bord)
		{
			for(Veld veld : row)
			{
				veld = new Veld();
				veld.unBlock();
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
		
		
		
		
		
		
		
		car.setYPos(row);
		car.setXPos(column);
	}
	
	
	
	public static void main(String[] args)
	{
		System.out.println("Test rushhour");
		new RushHour();
		
		Car car1 = new Car(2, Car.HORIZONTAL);
		
		
		
		
	}
			
}
