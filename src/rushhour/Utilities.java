package rushhour;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GraphicsConfiguration;
import java.awt.Stroke;

public class Utilities
{
	public static void printGraphicProperties(Graphics g)
	{
		System.out.println("Graphics properties " + g.getClass().getName());
		
		System.out.println("  Color: " + colorStr(g.getColor()));
		System.out.println("  Font: " + fontStr(g.getFont()));
		System.out.println("  Clip: "+ g.getClip());
		
		Graphics2D g2;
		if (g instanceof Graphics2D)
		{
			g2 = (Graphics2D) g;
			System.out.println("  Background Color: " + colorStr(g2.getBackground()));
			System.out.println("  Paint: " + g2.getPaint());
			System.out.println("  Composite: " +g2.getComposite());
			System.out.println("  Stroke: " + g2.getStroke());
			
			GraphicsConfiguration gc = g2.getDeviceConfiguration();
			System.out.println("  GraphicsConfiguration: " + gc);
			
			System.out.println("    "+gc.getColorModel());
			System.out.println("    Bounds: " + gc.getBounds());
		}
	}
	
	public static String colorStr(Color color)
	{
		String colorStr = color.getRed() + ", " +
				color.getGreen() +", " + color.getBlue();
		
		String alpha = (color.getAlpha() != 25) ? " Alpha: " + color.getAlpha() : "";
	                      
		return ("RGB: {" + colorStr + "}" + alpha);
		
	}
	
	


	public static String fontStr(Font font)
	{
		StringBuilder styleSb = new StringBuilder(" Style=");
		if (font.isBold())
			styleSb.append("bold");
		if (font.isItalic())
			styleSb.append("italic");
		if (font.isPlain())
			styleSb.append("plain");
		
		
		
		return " Family="+ font.getFamily() + " Name=" + font.getName() +
				" FontName=" + font.getFontName() + styleSb;
				
	}
	
	
	/**
	 * Returns a string with the properties of the BasicStroke
	 */
	public static String basicStrokeStr(BasicStroke bs)
	{
	    /*
	     * BasicStroke constants:
	     * 
	     * Joins path segments by
	     * 
	     * JOIN_MITER (0)  extending their outside
	     * JOIN_ROUND (1)  rounding off the corner
	     * JOIN_BEVEL (2)  connecting the outer corners straight
	     * 
	     * Ends unclosed subpaths and dash segments 
	     * 
	     * CAP_BUTT   (0) with no added decoration.
	     * CAP_ROUND  (1) with a round decoration 
	     * CAP_SQUARE (2)  square beyond the end of the segment
	     */
		
		StringBuilder result = new StringBuilder();

		result.append("  EndCap: ");
		int endcap = bs.getEndCap();
		if (endcap == BasicStroke.CAP_BUTT)
		{
			result.append("CAP_BUTT, no added decoration"); result.append("\n");
		}
		else if (endcap == BasicStroke.CAP_ROUND)
		{
			result.append("CAP_ROUND"); result.append("\n");
		}
		else if (endcap == BasicStroke.CAP_SQUARE)
		{
			result.append("CAP_SQUARE"); result.append("\n");
		}
		
		
		int linejoin = bs.getLineJoin();
		result.append("  LineJoin: ");
		if (linejoin == BasicStroke.JOIN_MITER)
		{
			result.append("JOIN_METER, extending their outside"); result.append("\n");
		}
		else if (linejoin == BasicStroke.JOIN_ROUND)
		{
			result.append("JOIN_ROUND"); result.append("\n");
		}
		else if (linejoin == BasicStroke.JOIN_BEVEL)
		{
			result.append("JOIN_BEVEL, connecting the outer corners straight"); result.append("\n");
		}
		
		result.append("  MiterLimit: " + bs.getMiterLimit()); result.append("\n");
		result.append("  LineWidth: " + bs.getLineWidth()); result.append("\n");
		
		return result.toString();		
	}
	
	
	
	/**
	 * Print properties of an AWT BasicStroke object 
	 * @param s
	 */
	public static void printStrokeProperties(Stroke s)
	{
		System.out.println("Stroke properties:");
		System.out.println(s);
		
		if (s instanceof BasicStroke)
		{
			System.out.println(basicStrokeStr((BasicStroke) s));

		}
		
	}
}