package rushhour;

import java.awt.BorderLayout;
import java.util.Observer;

import javax.swing.JFrame;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class RushHourApplication extends JFrame
{
	private JPanel mainView;
	private RushHour model;
	private BottomBar bottomBar;
	
	
	public RushHourApplication()
	{
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setTitle("RushHour");
		
		
		model = new RushHour();
		
		mainView = new RushHourView(model);
		
		model.addObserver((Observer) mainView);
		
		
		bottomBar = new BottomBar(model);
		
		this.add(mainView);
		this.add(bottomBar, BorderLayout.SOUTH);
		this.pack();

	}

	
	public static void main(String[] args)
	{
		System.out.println("Starting RushHourApplication");
		RushHourApplication rha = new RushHourApplication();
		rha.setVisible(true);
		
		Car car1 = new Car(2, Car.HORIZONTAL);
		
		rha.model.placeCar(car1, 2, 5);
		
		Car car2 = new Car(2, Car.VERTICAL);
		rha.model.placeCar(car2, 2, 3);
		
		rha.model.placeCar(car2, 2, 4);
		
		Car carX = new Car(2, Car.HORIZONTAL, RushHourColor.X_RED);
		rha.model.placeCar(carX, 2, 0);
	
		Car truck1 = new Car(3, Car.HORIZONTAL);
		rha.model.placeCar(truck1, 1, 0);
		
		Car truck2 = new Car(3, Car.VERTICAL, RushHourColor.R_GREEN);
		rha.model.placeCar(truck2, 0, 5);
		
		Car car3 = new Car(2, Car.HORIZONTAL, RushHourColor.E_BLUE);
		Car car4 = new Car(2, Car.HORIZONTAL, RushHourColor.I_WHITE);
		rha.model.placeCar(car3, 4, 0);
		rha.model.placeCar(car4, 5, 1);
		
		System.out.println("Size of carList: " + rha.model.getCarList().size());
	}

}
