package rushhour;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Stroke;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class RushHourView extends JPanel
{
	private Image carImg = null;
	private Image truckImg = null;
	
	private int boardStartXPos = 30;
	private int boardStartYPos = 30;
	
	private int squareSize = 50;
	private int squareMarge = 5;



	public RushHourView()
	{
		this.setPreferredSize(new Dimension(600, 700));
		
		try
		{
			
			
			URL auto2URL = getResource("/images/auto2.png");
			
			carImg = ImageIO.read(auto2URL);
			
			URL truck5URL = getResource("/images/truck5.png");
			truckImg = ImageIO.read(truck5URL);
		} catch (IOException e)
		{
			System.out.println("Cannot load image");
			System.out.println(e.getMessage());
		}
		
		
		
	}
	
	
	private URL getResource(String name)
	{
		
		Class c = getClass();
		URL resource = c.getResource(name);
		
		if (resource == null)
		{
			System.out.println("Cannot find resource " + name + ", exiting");
			System.exit(1);
		}

		return resource;
		
	}
	

	
	@Override
	protected void paintComponent(Graphics g)
	{
		Graphics2D g2 = (Graphics2D) g;
		
		Stroke defaultStroke = g2.getStroke();
		
		int boardSizeX = RushHour.numCols * (squareSize + 2 * squareMarge);
		int boardSizeY = RushHour.numRows * (squareSize + 2 * squareMarge);
		
		System.out.println("Boardsize " + boardSizeX + " x " + boardSizeY );
		
		// draw border of board
		int borderWidth = 2;
		g2.setStroke(new BasicStroke(borderWidth));
		g2.drawRect(boardStartXPos - borderWidth,
				    boardStartYPos - borderWidth,
				    boardSizeX + 2*borderWidth, boardSizeY + 2*borderWidth);

		
		// draw squares in board
		g2.setStroke(defaultStroke);
		
		g2.setColor(Color.GRAY);
		
		for (int i = 0; i < RushHour.numRows; i++)
		{
			for (int j = 0; j < RushHour.numCols; j++)
			{
				g2.draw3DRect(boardStartXPos + squareMarge + j * (squareSize + 2 * squareMarge),
						      boardStartYPos+ squareMarge + i * (squareSize + 2 * squareMarge),
						      squareSize - 1, squareSize - 1, false);
			}
		}
		
		
		// Draw cars
		
		Car drawTestTruck = new Car(3, Car.HORIZONTAL);
		
		drawTestTruck.setXPos(0);
		drawTestTruck.setYPos(1);
		
		drawCar(g2, drawTestTruck);
		
		drawTestTruck.setXPos(3);
		drawCar(g2, drawTestTruck);
		
		Car drawTestCar = new Car(2, Car.HORIZONTAL);
		drawTestCar.setYPos(2);
		drawCar(g2, drawTestCar);
		
		drawTestCar.setXPos(2);
		drawCar(g2, drawTestCar);
		drawTestCar.setXPos(4);
		drawCar(g2, drawTestCar);
		
		Car drawTestCarVert = new Car(2, Car.VERTICAL);

		drawCar(g2, drawTestCarVert);
		
		drawTestCarVert.setYPos(3);
		drawCar(g2, drawTestCarVert);
		
		drawTestCarVert.setXPos(1);
		drawCar(g2, drawTestCarVert);
		
		
		

		

	}
	
	
	/**
	 * Draws the image representation from the car on the board. 
	 * Uses a png image loaded in a BufferedImage. Car type, position and orientation
	 * is read from the car.
	 * @param g Graphics context from the component to paint on
	 * @param car The Car to draw 
	 */
	private void drawCar(Graphics2D g, Car car)
	{
		Image vehicleImage = null;
				
		// determine which image to use
		if (car.getSize() == 2)
		{
			vehicleImage = carImg;
		}
		else if (car.getSize() >= 3)
		{
			vehicleImage = truckImg;
		}
		
		// total square size for car position
		int squareSizeIncl = squareSize + 2*squareMarge;
		int lengthPlaceMarge = 0;
		int widthPlaceMarge = 0;
		
		// drawing size of the car 
		int carDrawLength = (squareSizeIncl)*car.getSize() - lengthPlaceMarge;
		int carDrawWidth = (squareSizeIncl) - widthPlaceMarge; 
		
		if (car.getOrientation() == Car.HORIZONTAL)
		{
			// horizontal car: positioning and resing in one method 
			g.drawImage(vehicleImage,
					boardStartXPos + squareSizeIncl * car.getXPos() + lengthPlaceMarge / 2, 
					boardStartYPos + squareSizeIncl * car.getYPos() + widthPlaceMarge / 2,
					carDrawLength,
					carDrawWidth, null);
		}
		else if (car.getOrientation() == Car.VERTICAL)
		{
			// uses transform operation to draw the car 90 degree clockwise rotated
			
			// resize factor to fit image in block length
			double lengthScale = (double) carDrawLength / vehicleImage.getWidth(null);
			double widthScale = (double) carDrawWidth / vehicleImage.getHeight(null);
			
			AffineTransform rotXForm = AffineTransform.getQuadrantRotateInstance(1);
	
			// modify transform:
			
			// correction after rotating
			rotXForm.translate(0, -carDrawWidth);
			rotXForm.translate(boardStartXPos, -boardStartXPos);
			
			// positioning
			rotXForm.translate(squareSizeIncl * car.getYPos(), - squareSizeIncl * car.getXPos());
			
			//scaling
			rotXForm.scale(lengthScale, widthScale);
			
			g.drawImage(vehicleImage, rotXForm, null);
		}

						
	}
		
	

}
