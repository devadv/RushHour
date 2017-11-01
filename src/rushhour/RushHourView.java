package rushhour;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Stroke;

import javax.swing.JPanel;

@SuppressWarnings("serial")
public class RushHourView extends JPanel
{
	
	public RushHourView()
	{
		this.setPreferredSize(new Dimension(600, 700));
		
	}
	
	
	
	@Override
	protected void paintComponent(Graphics g)
	{
		Graphics2D g2 = (Graphics2D) g;
		
		Stroke defaultStroke = g2.getStroke();
		
		int squareSize = 50;
		int squareMarge = 5;
		
		int boardStartXPos = 30;
		int boardStartYPos = 30;
		
		int boardSizeX = RushHour.numCols * (squareSize + 2 * squareMarge) + squareMarge;
		int boardSizeY = RushHour.numRows * (squareSize + 2 * squareMarge) + squareMarge;
		
		
		System.out.println("Boardsize " + boardSizeX + " x " + boardSizeY );
		
		g2.setStroke(new BasicStroke(2));
		
		g2.drawRect(boardStartXPos, boardStartYPos, boardSizeX, boardSizeY);
		
		g2.setStroke(defaultStroke);
		
		g2.setColor(Color.GRAY);
		
		for (int i = 0; i < RushHour.numRows; i++)
		{
			for (int j = 0; j < RushHour.numCols; j++)
			{
				g2.draw3DRect(boardStartXPos + squareMarge + j * (squareSize + 2 * squareMarge),
						      boardStartYPos+ squareMarge + i * (squareSize + 2 * squareMarge),
						      squareSize, squareSize, false);
			}
		}
		
		
		
		
	}
	
	
	
	
		
	

}
