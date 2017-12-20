package rushhour;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

@SuppressWarnings("serial")
public class BottomBar extends JPanel
{
	
	RushHour model;
	
	JButton saveButton;
	JButton deleteButton;
	
	JCheckBox designModusOn;
	
	JComboBox<String> carColorChooser;
	
	JRadioButton orientationHorizontal;
	JRadioButton orientationVertical;
	ButtonGroup orientationChooser = new ButtonGroup();
	
	JRadioButton chooseTruck;
	JRadioButton chooseCar;
	ButtonGroup cartypeChooser = new ButtonGroup();
		
	JPanel upperPanel = new JPanel();
	JPanel middlePanel = new JPanel();
	JPanel lowerPanel = new JPanel();
		
	public void updateButtonState()
	{
		saveButton.setEnabled( model.isDesignMode() );
		deleteButton.setEnabled( model.isDesignMode());
	}
	
	public BottomBar(RushHour model)
	{
		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		
		this.add(upperPanel);
		this.add(middlePanel);
		this.add(lowerPanel);
						
		this.model = model;
		
		designModusOn = new JCheckBox();
		saveButton = new JButton("Opslaan");
		deleteButton = new JButton("Verwijder");
		
		orientationHorizontal = new JRadioButton("Horizontal");
		orientationVertical = new JRadioButton("Vertical");
		
		chooseCar = new JRadioButton("Car");
		chooseTruck = new JRadioButton("Truck");
		
		upperPanel.add(deleteButton);
		upperPanel.add(saveButton);
		
		carColorChooser = new JComboBox<>(RushHourColor.carColorStrEn);
		
		upperPanel.add(designModusOn);
		
						
		cartypeChooser.add(chooseCar);
		cartypeChooser.add(chooseTruck);
				
		middlePanel.add(chooseCar);
		middlePanel.add(chooseTruck);
		middlePanel.add(carColorChooser);
		
		lowerPanel.add(orientationHorizontal);
		lowerPanel.add(orientationVertical);

		
		orientationChooser.add(orientationHorizontal);
		orientationChooser.add(orientationVertical);
						
		designModusOn.addActionListener(new CheckBoxListener());
		saveButton.addActionListener(new saveButtonListener());
		deleteButton.addActionListener(new deleteButtonListener());
		updateButtonState();
	}
	
	private class CheckBoxListener implements ActionListener
	{

		@Override
		public void actionPerformed(ActionEvent e)
		{
			JCheckBox source = (JCheckBox) e.getSource(); 
			model.setDesignMode(source.isSelected());
			updateButtonState();
		}
		
	}
	
	private class saveButtonListener implements ActionListener
	{
		@Override
		public void actionPerformed(ActionEvent e)
		{
			System.out.println("Save");
		}
		
	}
	
	private class deleteButtonListener implements ActionListener
	{
		@Override
		public void actionPerformed(ActionEvent e)
		{
			System.out.println("delete");
			model.removeSelectedCar();
			
		}
		
	}
	
	
	
}
