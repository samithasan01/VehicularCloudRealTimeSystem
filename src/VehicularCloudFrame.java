import java.io.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

import javax.swing.*;

import java.awt.CardLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class VehicularCloudFrame extends JFrame {
	
	private static final int FRAME_WIDTH = 300;
	private static final int FRAME_HEIGHT = 250;
	private static final int FIELD_WIDTH = 10;


	private JLabel homeDescLabel;
	private JLabel vehicleOwnerDescLabel;
	private JLabel jobRequesterDescLabel;
	private JLabel vehicleOwnerNameLabel;
	private JLabel jobRequesterNameLabel;
	private JLabel vehicleOwnerDOBLabel;
	private JLabel jobRequesterDOBLabel;
	private JLabel vehicleOwnerIDLabel;
	private JLabel jobRequesterIDLabel;
	private JLabel vehicleMakeLabel;
	private JLabel vehicleModelLabel;
	private JLabel vehicleYearLabel;
	private JLabel vehicleColorLabel;
	private JLabel vehicleLicensePlateLabel;
	private JTextField vehicleOwnerNameField;
	private JTextField jobRequesterNameField;
	private JTextField vehicleOwnerIDField;
	private JTextField jobRequesterIDField;
	private JFormattedTextField vehicleOwnerDOBField;
	private JFormattedTextField jobRequesterDOBField;
	private JButton vehicleOwnerButton;
	private JButton jobRequesterButton;
	private JButton vehicleOwnerSubmitButton; // new
    	private JButton jobRequesterSubmitButton; // new
	private JButton vehicleOwnerBackButton;
	private JButton jobRequesterBackButton;
	private CardLayout layout;
	private JPanel cardsPanel;
	private JTextField vehicleMake;
	private JTextField vehicleModel;
	private JTextField vehicleYear;
	private JTextField vehicleColor;
	private JTextField vehicleLicensePlate;
	private PrintStream output;
	

	//Constructor
		public VehicularCloudFrame() throws FileNotFoundException {
			homeDescLabel = new JLabel("Please click on which type of user you are:");
			vehicleOwnerDescLabel = new JLabel("Please enter the following information:");
			jobRequesterDescLabel = new JLabel("Please enter the following information:");
			vehicleOwnerNameLabel = new JLabel("Name: ");
			jobRequesterNameLabel = new JLabel("Name: ");
			vehicleOwnerDOBLabel = new JLabel("Date of Birth: ");
			jobRequesterDOBLabel = new JLabel("Date of Birth: ");
			
			vehicleOwnerIDLabel = new JLabel("ID Number: ");
       			jobRequesterIDLabel = new JLabel("ID Number: ");
			
			vehicleMakeLabel = new JLabel("Make of the Vehicle: ");
			vehicleModelLabel = new JLabel("Model of the Vehicle: ");
			vehicleYearLabel = new JLabel("The year the model of the vehicle was released: ");
			vehicleColorLabel = new JLabel("The color of the vehicle: ");
			vehicleLicensePlateLabel = new JLabel("The license plate of the vehicle: ");
			vehicleOwnerIDLabel = new JLabel("ID Number: ");
			jobRequesterIDLabel = new JLabel("ID Number: ");
			layout = new CardLayout();
			cardsPanel = new JPanel(layout);
			output = new PrintStream(new File("userInformation.txt"));
			
			createTextFields();
			createButtons();
			createPanels();
			
			setSize(FRAME_WIDTH, FRAME_HEIGHT);
			setTitle("Vehicular Cloud Management System");
			setLocationRelativeTo(null);
			
			vehicleOwnerSubmitButton.addActionListener(new VehicleOwnerSubmitListener());
        		jobRequesterSubmitButton.addActionListener(new JobRequesterSubmitListener());
		}
		
		private void saveUserDataToFile(String userData) {
	    		try {
	        	FileWriter writer = new FileWriter("userInformation.txt", true);
	        	writer.write(userData);
	        	writer.write(System.getProperty("line.separator"));
	        	writer.close();
	        	String currentDir = System.getProperty("user.dir");
	        	JOptionPane.showMessageDialog(this, "User data saved successfully!\n\n Current working directory:" + currentDir);
	    		} 
			catch (IOException e) {
	        	JOptionPane.showMessageDialog(this, "An error occurred while saving user data: " + e.getMessage());
	    		}
		}
	
		class VehicleOwnerSubmitListener implements ActionListener {
	    		public void actionPerformed(ActionEvent event) {
	        		String name = vehicleOwnerNameField.getText();
	        		String dob = vehicleOwnerDOBField.getText();
	        		String id = vehicleOwnerIDField.getText();
	        		String outputString = "Vehicle Owner: " + name + ", DOB: " + dob + ", ID: " + id;
	        		saveUserDataToFile(outputString);
	        		//Reset Text Fields
	        		//vehicleOwnerNameField.setText("");
	        		//vehicleOwnerDOBField.setText("mm/dd/yyyy");
	        		//vehicleOwnerIDField.setText("");
	    		}
		}
	
		class JobRequesterSubmitListener implements ActionListener {
	    		public void actionPerformed(ActionEvent event) {
	       			String name = jobRequesterNameField.getText();
	        		String dob = jobRequesterDOBField.getText();
	        		String id = jobRequesterIDField.getText();
	        		String outputString = "Job Requester: " + name + ", DOB: " + dob + ", ID: " + id;
	        		saveUserDataToFile(outputString);
	        		//Reset Text Fields
	        		//jobRequesterNameField.setText("");
	        		//jobRequesterDOBField.setText("mm/dd/yyyy");
	        		//jobRequesterIDField.setText("");
	    		}
		}
	
		//Creates text fields
		private void createTextFields() {
			DateFormat dateFormat = new SimpleDateFormat("mm/dd/yyyy");
			//Vehicle Owner Text Fields
			vehicleOwnerNameField = new JTextField(FIELD_WIDTH);
			vehicleOwnerNameField.setText("");
			vehicleOwnerDOBField = new JFormattedTextField(dateFormat);
			vehicleOwnerDOBField.setText("mm/dd/yyyy");
			vehicleOwnerDOBField.setColumns(10);
			vehicleOwnerIDField = new JTextField(FIELD_WIDTH);
			vehicleOwnerIDField.setText("");
			
			//Job Requester Text Fields
			jobRequesterNameField = new JTextField(FIELD_WIDTH);
			jobRequesterNameField.setText("");
			jobRequesterDOBField = new JFormattedTextField(dateFormat);
			jobRequesterDOBField.setText("mm/dd/yyyy");
			jobRequesterDOBField.setColumns(10);
			jobRequesterIDField = new JTextField(FIELD_WIDTH);
			jobRequesterIDField.setText("");
		}
		
		class VehicleOwnerListener implements ActionListener {
			public void actionPerformed(ActionEvent event) {
				layout.show(cardsPanel, "vehicleOwner");
				//Reset Text Fields
				vehicleOwnerNameField.setText("");
				vehicleOwnerDOBField.setText("mm/dd/yyyy");
				vehicleOwnerIDField.setText("");
			}
		}
		
		class JobRequesterListener implements ActionListener {
			public void actionPerformed(ActionEvent event) {
				layout.show(cardsPanel, "jobRequester");
				
				//Reset Text Fields
				jobRequesterNameField.setText("");
				jobRequesterDOBField.setText("mm/dd/yyyy");
				jobRequesterIDField.setText("");
			}
		}
		
		class ReturnHomeListener implements ActionListener {
			public void actionPerformed(ActionEvent event) {
				layout.show(cardsPanel, "home");
			}
		}
		
		//Creates the buttons
		private void createButtons() {
			vehicleOwnerButton = new JButton("Vehicle Owner");
			jobRequesterButton = new JButton("Job Requester");
			vehicleOwnerBackButton = new JButton("Back");
			jobRequesterBackButton = new JButton("Back");
			
			ActionListener vehicleOwnerListener = new VehicleOwnerListener();
			vehicleOwnerButton.addActionListener(vehicleOwnerListener);
			
			ActionListener jobRequesterListener = new JobRequesterListener();
			jobRequesterButton.addActionListener(jobRequesterListener);
			
			ActionListener vehicleOwnerBackListener = new ReturnHomeListener();
			vehicleOwnerBackButton.addActionListener(vehicleOwnerBackListener);
			
			ActionListener jobRequesterBackListener = new ReturnHomeListener();
			jobRequesterBackButton.addActionListener(jobRequesterBackListener);
			
			//Vehicle Owner Submit Button
        		vehicleOwnerSubmitButton = new JButton("Submit");
        		ActionListener vehicleOwnerSubmitListener = new ActionListener() {
            			@Override
            			public void actionPerformed(ActionEvent e) {
                			String name = vehicleOwnerNameField.getText();
                			String dob = vehicleOwnerDOBField.getText();
                			String id = vehicleOwnerIDField.getText();
                			String outputString = "Vehicle Owner: " + name + "\nDate of Birth: " + dob + "\nID: " + id;
                			try {
                    			output.println(outputString);
                			}
					catch (Exception ex) {
                    			ex.printStackTrace();
                			}
            			}
        		};
        		vehicleOwnerSubmitButton.addActionListener(vehicleOwnerSubmitListener);
			
        		//Job Requester Submit Button
        		jobRequesterSubmitButton = new JButton("Submit");
        		ActionListener jobRequesterSubmitListener = new ActionListener() {
            			@Override
            			public void actionPerformed(ActionEvent e) {
                			String name = jobRequesterNameField.getText();
                			String dob = jobRequesterDOBField.getText();
                			String id = jobRequesterIDField.getText();
                			String outputString = "Job Requester: " + name + "\nDate of Birth: " + dob + "\nID: " + id;
                			try {
                    			output.println(outputString);
                			}
					catch (Exception ex) {
                    			ex.printStackTrace();
                			}
            			}
        		};
        		jobRequesterSubmitButton.addActionListener(jobRequesterSubmitListener);
        
		}
		
		//Creates the panels
		private void createPanels() {
			JPanel homePanel = new JPanel();
			JPanel vehicleOwnerPanel = new JPanel();
			JPanel vehicleOwnerNamePanel = new JPanel();
			JPanel vehicleOwnerDOBPanel = new JPanel();
			JPanel jobRequesterPanel = new JPanel();
			JPanel jobRequesterNamePanel = new JPanel();
			JPanel jobRequesterDOBPanel = new JPanel();
			JPanel vehicleOwnerIDPanel = new JPanel();
			JPanel jobRequesterIDPanel = new JPanel();
			
			cardsPanel.add(homePanel, "home");
			cardsPanel.add(vehicleOwnerPanel, "vehicleOwner");
			cardsPanel.add(jobRequesterPanel, "jobRequester");
			add(cardsPanel);
			
			//Home Screen Panel
			homePanel.add(homeDescLabel);
			homePanel.add(vehicleOwnerButton);
			homePanel.add(jobRequesterButton);
			
			//Vehicle Owner Panels
			vehicleOwnerNamePanel.add(vehicleOwnerNameLabel);
			vehicleOwnerNamePanel.add(vehicleOwnerNameField);
			
			vehicleOwnerDOBPanel.add(vehicleOwnerDOBLabel);
			vehicleOwnerDOBPanel.add(vehicleOwnerDOBField);
			
			vehicleOwnerIDPanel.add(vehicleOwnerIDLabel);
			vehicleOwnerIDPanel.add(vehicleOwnerIDField);
			
			vehicleOwnerPanel.add(vehicleOwnerDescLabel);
			vehicleOwnerPanel.add(vehicleOwnerNamePanel);
			vehicleOwnerPanel.add(vehicleOwnerDOBPanel);
			vehicleOwnerPanel.add(vehicleOwnerIDPanel);
			vehicleOwnerPanel.add(vehicleOwnerBackButton);
			
			vehicleOwnerPanel.add(vehicleOwnerSubmitButton); // add to panel
			
			//Job Requester Panels
			jobRequesterNamePanel.add(jobRequesterNameLabel);
			jobRequesterNamePanel.add(jobRequesterNameField);
			
			jobRequesterDOBPanel.add(jobRequesterDOBLabel);
			jobRequesterDOBPanel.add(jobRequesterDOBField);
			
			jobRequesterIDPanel.add(jobRequesterIDLabel);
			jobRequesterIDPanel.add(jobRequesterIDField);
			
			jobRequesterPanel.add(jobRequesterDescLabel);
			jobRequesterPanel.add(jobRequesterNamePanel);
			jobRequesterPanel.add(jobRequesterDOBPanel);
			jobRequesterPanel.add(jobRequesterIDPanel);
			jobRequesterPanel.add(jobRequesterBackButton);
			
			jobRequesterPanel.add(jobRequesterSubmitButton); // add to panel
			
		}
	}
