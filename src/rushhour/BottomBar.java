package rushhour;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;
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
	
	ComboBoxModel<String> carcolorCbm = new DefaultComboBoxModel<>(RushHourColor.carColorStrEn);
	ComboBoxModel<String> truckcolorCbm = new DefaultComboBoxModel<>(RushHourColor.truckColorStrEn);
	
	JRadioButton orientationHorizontal;
	JRadioButton orientationVertical;
	ButtonGroup orientationChooser = new ButtonGroup();
	
	JRadioButton chooseTruck;
	JRadioButton chooseCar;
	ButtonGroup cartypeChooser = new ButtonGroup();
	
	JButton addCarButton = new JButton("+");
	
			
	JPanel upperPanel = new JPanel();
	JPanel middlePanel = new JPanel();
	JPanel lowerPanel = new JPanel();
	
	/**
	 * Sets the design buttons active when design mode is on.
	 */
	public void updateButtonState()
	{
		boolean isDesignMode = model.isDesignMode();
		saveButton.setEnabled( isDesignMode );
		deleteButton.setEnabled( isDesignMode );
		orientationVertical.setEnabled( isDesignMode );
		orientationHorizontal.setEnabled(isDesignMode);
		carColorChooser.setEnabled(isDesignMode);
		chooseCar.setEnabled(isDesignMode);
		chooseTruck.setEnabled(isDesignMode);
		addCarButton.setEnabled(isDesignMode);
	}
	
	public BottomBar(RushHour model)
	{
		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		
		this.add(upperPanel);
		this.add(middlePanel);
		this.add(lowerPanel);
						
		this.model = model;
		
		designModusOn = new JCheckBox("Ontwerpmodus");
				
		saveButton = new JButton("Opslaan");
		deleteButton = new JButton("Verwijder");
		
		orientationHorizontal = new JRadioButton("Horizontal", true);
		orientationVertical = new JRadioButton("Vertical");
		
		chooseCar = new JRadioButton("Car", true);
		chooseTruck = new JRadioButton("Truck");
		
		upperPanel.add(deleteButton);
		upperPanel.add(saveButton);
		
		carColorChooser = new JComboBox<>();
		carColorChooser.setModel(carcolorCbm);
		
		upperPanel.add(designModusOn);
								
		cartypeChooser.add(chooseCar);
		cartypeChooser.add(chooseTruck);
				
		middlePanel.add(chooseCar);
		middlePanel.add(chooseTruck);
		middlePanel.add(carColorChooser);
		
		middlePanel.add(addCarButton);
		
		lowerPanel.add(orientationHorizontal);
		lowerPanel.add(orientationVertical);

		
		orientationChooser.add(orientationHorizontal);
		orientationChooser.add(orientationVertical);
						
		designModusOn.addActionListener(new CheckBoxListener());
		saveButton.addActionListener(new saveButtonListener());
		deleteButton.addActionListener(new deleteButtonListener());
		
		carChooserListener carChooserListener = new carChooserListener();
		chooseCar.addActionListener( carChooserListener );
		chooseTruck.addActionListener( carChooserListener );
		
		//addCarButton.setFont(new Font("", null, 20));
		
		addCarButton.addActionListener(new carAddListener());
		
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
	
	
	private class carChooserListener implements ActionListener
	{

		@Override
		public void actionPerformed(ActionEvent e)
		{
			String action = e.getActionCommand();
			System.out.println(action); 
			
			if (action == "Car")
			{
				carColorChooser.setModel(carcolorCbm);
			}
			else if (action == "Truck")
			{
				carColorChooser.setModel(truckcolorCbm);
			}
			
		}
		
	}
	
	private class carAddListener implements ActionListener
	{

		@Override
		public void actionPerformed(ActionEvent e)
		{
			System.out.println("Add car");
			
			getParent().setCursor(new Cursor(Cursor.CROSSHAIR_CURSOR));
			
			int size = 0;
			if (chooseCar.isSelected())
			{
				size = 2;
			}
			else if (chooseTruck.isSelected())
			{
				size = 3;
			}
			
			
			int orientation = -1;
			if (orientationHorizontal.isSelected())
			{
				orientation = Car.HORIZONTAL;
			}
			else if (orientationVertical.isSelected())
			{
				orientation = Car.VERTICAL;
			}
			
			
			String colorStr = (String) carColorChooser.getSelectedItem();
			
			Color color = RushHourColor.getColor(colorStr, size);
			
			Car carToAdd = new Car(size, orientation, color);
			
			
		}
		
	}
	
	
	
}
