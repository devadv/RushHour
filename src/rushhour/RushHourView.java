package rushhour;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Stroke;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import java.awt.geom.AffineTransform;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Observable;
import java.util.Observer;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class RushHourView extends JPanel implements Observer
{
	private int boardStartXPos = 30;
	private int boardStartYPos = 30;
	
	private int squareSize = 50;
	private int squareMarge = 5;
	
	private RushHour model;
	
	/**
	 * Map for preloaded images. Key is the letter code of the image.
	 */
	private HashMap<String, Image> vehicleImage;
	
	
	public RushHourView(RushHour model)
	{
		this.model = model;
				
		System.out.println("Starting view");
		this.setPreferredSize(new Dimension(550, 550));
		vehicleImage = new HashMap<>();
		
		try
		{
			// load all images in the vehicleImage hashmap
			vehicleImage.put("X",  ImageIO.read( getResource("/auto_x_rood.png") ) );
			vehicleImage.put("A",  ImageIO.read( getResource("/auto_a_groenblauw.png") ) );
			vehicleImage.put("B",  ImageIO.read( getResource("/auto_b_geel.png") ) );
			vehicleImage.put("C",  ImageIO.read( getResource("/auto_c_lichtblauw.png") ) );
			vehicleImage.put("D",  ImageIO.read( getResource("/auto_d_roze.png") ) );
			vehicleImage.put("E",  ImageIO.read( getResource("/auto_e_blauw.png") ) );
			vehicleImage.put("F",  ImageIO.read( getResource("/auto_f_groen.png") ) );
			vehicleImage.put("G",  ImageIO.read( getResource("/auto_g_donkergroen.png") ) );
			vehicleImage.put("H",  ImageIO.read( getResource("/auto_h_grijs.png") ) );
			vehicleImage.put("I",  ImageIO.read( getResource("/auto_i_wit.png") ) );
			vehicleImage.put("J",  ImageIO.read( getResource("/auto_j_bruin.png") ) );
			vehicleImage.put("O",  ImageIO.read( getResource("/truck_o_geel.png") ) );
			vehicleImage.put("P",  ImageIO.read( getResource("/truck_p_paars.png") ) );
			vehicleImage.put("Q",  ImageIO.read( getResource("/truck_q_blauw.png") ) );
			vehicleImage.put("R",  ImageIO.read( getResource("/truck_r_groen.png") ) );
			vehicleImage.put("stdcar",  ImageIO.read( getResource("/auto2.png") ) );
			vehicleImage.put("stdtruck",  ImageIO.read( getResource("/truck5.png") ) );
			
		} catch (IOException e)
		{
			System.out.println("Cannot load image");
			System.out.println(e.getMessage());
		}
		
		BoardMouseResponse imageMouseResponse = new BoardMouseResponse();
		this.addMouseListener(imageMouseResponse);
		this.addMouseMotionListener(imageMouseResponse);
		this.addMouseWheelListener(imageMouseResponse);
		
	}
	
	
		
	
	private URL getResource(String name)
	{
		
		URL resource = getClass().getResource(name);
		
		if (resource == null)
		{
			System.out.println("Cannot find resource " + name + ", exiting");
			System.exit(1);
		}

		// System.out.println("URL="+resource);
		return resource;
	}
	
	@Override
	protected void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D) g;
		
		Stroke defaultStroke = g2.getStroke();
		
		int boardSizeX = RushHour.numCols * (squareSize + 2 * squareMarge);
		int boardSizeY = RushHour.numRows * (squareSize + 2 * squareMarge);
		
		System.out.println("Boardsize " + boardSizeX + " x " + boardSizeY );
		
		//g2.clearRect(0, 0, this.getWidth(), this.getHeight());
		
		// draw border of board
		int borderWidth = 2;
		g2.setStroke(new BasicStroke(borderWidth));
		g2.drawRect(boardStartXPos - borderWidth,
				    boardStartYPos - borderWidth,
				    boardSizeX + 2*borderWidth, boardSizeY + 2*borderWidth);

		
		// draw squares in board
		g2.setStroke(defaultStroke);
		
		g2.setColor(Color.LIGHT_GRAY);
		
		for (int i = 0; i < RushHour.numRows; i++)
		{
			for (int j = 0; j < RushHour.numCols; j++)
			{
				g2.drawRect(boardStartXPos + squareMarge + j * (squareSize + 2 * squareMarge),
						      boardStartYPos+ squareMarge + i * (squareSize + 2 * squareMarge),
						      squareSize - 1, squareSize - 1);
			}
		}
		
		
		// Draw cars
		//testDrawCar(g2);
		drawCarList(g2);

	}
	
	
	
	
	private void drawCarList(Graphics2D g2)
	{
		if (model == null)
		{
			System.out.println("drawCarList: model is null");
			return;
		}
		
		ArrayList<Car> carList = model.getCarList();
		
		for (Car car : carList)
		{
			drawCar(g2, car);
			
		}
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
		Image vehicleImg = null;
				
		// determine which image to use
		if (car.getSize() == 2)
		{
			vehicleImg = vehicleImage.get("A");
		}
		else if (car.getSize() >= 3)
		{
			vehicleImg = vehicleImage.get("Q");
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
			g.drawImage(vehicleImg,
					boardStartXPos + squareSizeIncl * car.getColumn() + lengthPlaceMarge / 2, 
					boardStartYPos + squareSizeIncl * car.getRow() + widthPlaceMarge / 2,
					carDrawLength,
					carDrawWidth, null);
		}
		else if (car.getOrientation() == Car.VERTICAL)
		{
			// uses transform operation to draw the car 90 degree clockwise rotated
			
			// resize factor to fit image in block length
			double lengthScale = (double) carDrawLength / vehicleImg.getWidth(null);
			double widthScale = (double) carDrawWidth / vehicleImg.getHeight(null);
			
			AffineTransform rotXForm = AffineTransform.getQuadrantRotateInstance(1);
	
			// modify transform:
			
			// correction after rotating
			rotXForm.translate(0, -carDrawWidth);
			rotXForm.translate(boardStartXPos, -boardStartXPos);
			
			// positioning
			rotXForm.translate(squareSizeIncl * car.getRow(), - squareSizeIncl * car.getColumn());
			
			//scaling
			rotXForm.scale(lengthScale, widthScale);
			
			g.drawImage(vehicleImg, rotXForm, null);
		}
		
		
		

						
	}
	
	
	/**
	 * For testing draw operations
	 * @param g2
	 */
	private void testDrawCar(Graphics2D g2)
	{
		Car drawTestTruck = new Car(3, Car.HORIZONTAL);
		
		drawTestTruck.setColumn(3);
		drawTestTruck.setRow(1);
		
		drawCar(g2, drawTestTruck);
		
		drawTestTruck.setColumn(0);
		drawTestTruck.setRow(5);
		drawCar(g2, drawTestTruck);
		
		Car drawTestCar = new Car(2, Car.HORIZONTAL);
		drawTestCar.setRow(2);
		drawCar(g2, drawTestCar);
		
		drawTestCar.setColumn(2);
		drawCar(g2, drawTestCar);
		drawTestCar.setColumn(4);
		drawCar(g2, drawTestCar);
		
		Car drawTestCarVert = new Car(2, Car.VERTICAL);

		drawCar(g2, drawTestCarVert);
		
		drawTestCarVert.setRow(3);
		drawCar(g2, drawTestCarVert);
		
		drawTestCarVert.setColumn(1);
		drawCar(g2, drawTestCarVert);
	}


	@Override
	public void update(Observable o, Object arg)
	{
		model = (RushHour) o;
		repaint();
		
	}
	
	
	/**
	 * Listener for mouse clicks and drags on the Board
	 * @author david
	 */
	private class BoardMouseResponse implements MouseListener, MouseMotionListener, MouseWheelListener
	{
		
		int getRow(int yPos)
		{
			int row = (yPos - boardStartYPos ) / (squareSize + 2 * squareMarge);
			if (yPos < boardStartYPos || row >= RushHour.numRows)
			{
				return -1;
			}
			else
			{
				return row;
			}
		}
		int getColumn(int xPos)
		{
			int column = ( xPos - boardStartXPos ) / (squareSize + 2 * squareMarge);
			if (xPos < boardStartXPos || column >= RushHour.numCols)
			{
				return -1;
			}
			else
			{
				return column;
			}
		}
		
		int startX;
		int startY;


		@Override
		public void mouseClicked(MouseEvent e)
		{
			
		}

		@Override
		public void mousePressed(MouseEvent e)
		{
			startX = e.getX();
			startY = e.getY();
			
			System.out.println("MousePress at x = " + startX + "   y = " + startY);
			
			int row, column;
			
			if ( (row = getRow(startY) ) == -1 || (column = getColumn(startX) ) == -1 )
			{
				System.out.println("Mousepress outside board");
			}
			else
			{
				System.out.println("Mousepress on row " + row + ", column " + column);
				
				
				Car foundCar = model.findCar(row, column);
				
				if (foundCar == null)
				{
					System.out.println("No car found");
				}
				else
				{
					System.out.println("Found car at position");
				}
				
			}
			
			

		}

		@Override
		public void mouseReleased(MouseEvent e)
		{
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseEntered(MouseEvent e)
		{
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseExited(MouseEvent e)
		{
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseDragged(MouseEvent e)
		{
			int dX = e.getX() - startX;
			int dY = e.getY() - startY;
			
			System.out.println("Dragged  " + "dx: " + dX + "  dy: " + dY );
			
			
		}

		@Override
		public void mouseMoved(MouseEvent e)
		{
		}

		@Override
		public void mouseWheelMoved(MouseWheelEvent e)
		{
			int rotation = e.getWheelRotation();
			int steps = 0;
			
			if (rotation < 0)
			{
				steps = -1;				
			}
			else
			{
				steps = 1;
			}
			
			int row, column;
			
			if ( (row = getRow(e.getY()) ) == -1 || (column = getColumn(e.getX()) ) == -1 )
			{
				System.out.println("Wheel rotation outside board");
			}
			else
			{
				System.out.println("Wheel rotation " + rotation + " on row " + row + ", column " + column);
				
				Car carToMove = model.findCar(row, column);
				
				if (carToMove != null)
				{
					model.moveCar(carToMove, steps);
				}
					
				
				
				
			}
			
			

			
		}
		
	}

		
	

}
