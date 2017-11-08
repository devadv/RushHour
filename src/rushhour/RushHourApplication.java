package rushhour;

import javax.swing.JFrame;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class RushHourApplication extends JFrame
{
	private JPanel mainView;
	
	public RushHourApplication()
	{
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setTitle("RushHour");
		
		mainView = new RushHourView();
		this.add(mainView);
		this.pack();

	}

	
	public static void main(String[] args)
	{

		
		System.out.println("Starting RushHourApplication");
		RushHourApplication rh = new RushHourApplication();
		rh.setVisible(true);

	}

}
