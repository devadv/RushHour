package rushhour;

import java.awt.BasicStroke;
import java.awt.Stroke;

public class Utilities
{
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
			
			BasicStroke bs = (BasicStroke) (s);
			System.out.print("  EndCap: ");
			int endcap = bs.getEndCap();
			if (endcap == BasicStroke.CAP_BUTT)
			{
				System.out.println("CAP_BUTT, no added decoration");
			}
			else if (endcap == BasicStroke.CAP_ROUND)
			{
				System.out.println("CAP_ROUND");
			}
			else if (endcap == BasicStroke.CAP_SQUARE)
			{
				System.out.println("CAP_SQUARE");
			}
			
			
			int linejoin = bs.getLineJoin();
			System.out.print("  LineJoin: ");
			if (linejoin == BasicStroke.JOIN_MITER)
			{
				System.out.println("JOIN_METER, extending their outside");
			}
			else if (linejoin == BasicStroke.JOIN_ROUND)
			{
				System.out.println("JOIN_ROUND");
			}
			else if (linejoin == BasicStroke.JOIN_BEVEL)
			{
				System.out.println("JOIN_BEVEL, connecting the outer corners straight");
			}
			
			
			System.out.println("  MiterLimit: " + bs.getMiterLimit());
			System.out.println("  LineWidth: " + bs.getLineWidth());
			
			
			
		}
		
	}


}
