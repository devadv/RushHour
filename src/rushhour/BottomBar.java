package rushhour;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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
	
	JRadioButton orientationChooser;
		
	public void updateButtonState()
	{
		saveButton.setEnabled( model.isDesignMode() );
		deleteButton.setEnabled( model.isDesignMode());
	}
	
	public BottomBar(RushHour model)
	{
		this.model = model;
		designModusOn = new JCheckBox();
		saveButton = new JButton("Opslaan");
		deleteButton = new JButton("Verwijder");
		
		this.add(deleteButton);
		this.add(saveButton);
		
		carColorChooser = new JComboBox<>(RushHourColor.colorStrEn);
		
		this.add(designModusOn);
		this.add(carColorChooser);
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
