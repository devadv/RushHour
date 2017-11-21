package rushhour;

import java.util.Observer;

import javax.swing.JFrame;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class RushHourApplication extends JFrame
{
	private JPanel mainView;
	private RushHour model;
	
	public RushHourApplication()
	{
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setTitle("RushHour");
		
		
		model = new RushHour();
		
		mainView = new RushHourView(model);
		
		model.addObserver((Observer) mainView);
		
		this.add(mainView);
		this.pack();

	}

	
	public static void main(String[] args)
	{
		System.out.println("Starting RushHourApplication");
		RushHourApplication rha = new RushHourApplication();
		rha.setVisible(true);
		
		Car car1 = new Car(2, Car.HORIZONTAL);
		
		rha.model.placeCar(car1, 2, 3);		
		
		rha.model.placeCar(car1, 2, 4);
		
		Car car2 = new Car(2, Car.VERTICAL);
		rha.model.placeCar(car2, 2, 3);
		
		rha.model.placeCar(car2, 2, 4);
		
		Car truck1 = new Car(3, Car.HORIZONTAL);
		rha.model.placeCar(truck1, 2, 0);
		
		System.out.println("Size of carList: " + rha.model.getCarList().size());
	}

}
