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
    private JLabel vehicleMakeLabel;	//New
    private JLabel vehicleModelLabel;	//New
    private JLabel vehicleYearLabel;	//New
    private JLabel vehicleColorLabel;	//New
    private JLabel vehicleLicenseLabel;	//New
    private JLabel vehicleResidencyLabel;//New
    private JLabel jobRequesterIDLabel;
    private JLabel jobDurationLabel; 	//New
    private JLabel jobDeadlineLabel;	//New
    private JLabel jobTypeLabel; 		//New
    private JLabel jobIntensityLabel;	//New
    private JTextField vehicleOwnerNameField;
    private JTextField jobRequesterNameField;
    private JTextField vehicleOwnerIDField;
    private JTextField vehicleMakeField;	//New
    private JTextField vehicleModelField;	//New
    private JTextField vehicleYearField;	//New
    private JTextField vehicleColorField;	//New
    private JTextField vehicleLicenseField;	//New
    private JTextField vehicleResidencyField;//New
    private JTextField jobRequesterIDField;
    private JTextField jobDurationField;	//New
    private JTextField jobDeadlineField;	//New
    private JTextField jobTypeField; 		//New
    private JTextField jobIntensityField;	//New
    private JFormattedTextField vehicleOwnerDOBField;
    private JFormattedTextField jobRequesterDOBField;
    private JButton vehicleOwnerButton;
    private JButton jobRequesterButton;
    private JButton vehicleOwnerSubmitButton; 
    private JButton jobRequesterSubmitButton; 
    private JButton vehicleOwnerBackButton;
    private JButton jobRequesterBackButton;
    private CardLayout layout;
    private JPanel cardsPanel;
    private PrintStream output;

    // Constructor
	public VehicularCloudFrame() throws FileNotFoundException {
		homeDescLabel = new JLabel("Please click on which type of user you are:");
        vehicleOwnerDescLabel = new JLabel("Please enter the following information:");
        jobRequesterDescLabel = new JLabel("Please enter the following information:");
        vehicleOwnerNameLabel = new JLabel("Name: ");
        jobRequesterNameLabel = new JLabel("Name: ");
        vehicleOwnerDOBLabel = new JLabel("Date of Birth: ");
        jobRequesterDOBLabel = new JLabel("Date of Birth: ");
        vehicleOwnerIDLabel = new JLabel("ID Number: ");
        vehicleMakeLabel = new JLabel("Make: ");				//New
        vehicleModelLabel = new JLabel("Model ");				//New
        vehicleYearLabel = new JLabel("Year ");					//New
        vehicleColorLabel = new JLabel("Color: ");				//New
        vehicleLicenseLabel = new JLabel("License Plate: ");	//New
        vehicleResidencyLabel = new JLabel("Residency Time (in hours): "); //New
        jobRequesterIDLabel = new JLabel("ID Number: ");
        jobDurationLabel = new JLabel("Job Duration (in hours): ");	//New
        jobDeadlineLabel = new JLabel("Job Deadline: ");			//New
        jobTypeLabel = new JLabel("Job Type: ");					//New
        jobIntensityLabel = new JLabel("Job Intensity: ");			//New
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
        jobRequesterSubmitButton.addActionListener(new VehicleOwnerSubmitListener());

    }

	private void saveUserDataToFile(String userData) {
	    try {
	        FileWriter writer = new FileWriter("userInformation.txt", true);
	        writer.write(userData);
	        writer.write(System.getProperty("line.separator"));
	        writer.close();
	        String currentDir = System.getProperty("user.dir");
	        JOptionPane.showMessageDialog(this, "User data saved successfully!\n\n Current working directory:" + currentDir);
	    } catch (IOException e) {
	        JOptionPane.showMessageDialog(this, "An error occurred while saving user data: " + e.getMessage());
	    }
	}

	class VehicleOwnerSubmitListener implements ActionListener {
	    public void actionPerformed(ActionEvent event) {
	        String name = vehicleOwnerNameField.getText();
	        String dob = vehicleOwnerDOBField.getText();
	        String id = vehicleOwnerIDField.getText();
	        String make = vehicleMakeField.getText();	//New
	        String model = vehicleModelField.getText();	//New
	        String year = vehicleYearField.getText();	//New
	        String color = vehicleColorField.getText();	//New
	        String license= vehicleLicenseField.getText();	//New
	        String residency = vehicleResidencyField.getText(); //New
	        
	        String outputString = "Vehicle Owner: " + name + ", DOB: " + dob + ", ID: " + id + ", Make: " + make + ", Model: " + model + ", Year: " + year + ", Color: " + color 
	        		+ ", License: " + license + ", Residency Time: " + residency;
	        saveUserDataToFile(outputString);
	        //Reset Text Fields
	        vehicleOwnerNameField.setText("");
	        vehicleOwnerDOBField.setText("mm/dd/yyyy");
	        vehicleOwnerIDField.setText("");
	    }
	}

	class JobRequesterSubmitListener implements ActionListener {
	    public void actionPerformed(ActionEvent event) {
	        String name = jobRequesterNameField.getText();
	        String dob = jobRequesterDOBField.getText();
	        String id = jobRequesterIDField.getText();
	        String duration = jobDurationField.getText(); //New
	        String deadline = jobDeadlineField.getText();//New
	        String type = jobTypeField.getText(); 	//New
	        String intensity = jobIntensityField.getText(); //New
	        String outputString = "Job Requester: " + name + ", DOB: " + dob + ", ID: " + id + ", Duration: " + duration + ", Deadline: " + deadline + ", Type: " +type +", Intensity: " + intensity;
	        saveUserDataToFile(outputString);
	        //Reset Text Fields
	        vehicleOwnerNameField.setText("");
	        vehicleOwnerDOBField.setText("mm/dd/yyyy");
	        vehicleOwnerIDField.setText("");
	        
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
        vehicleMakeField = new JTextField(FIELD_WIDTH);	//New
        vehicleMakeField.setText("");
        vehicleModelField = new JTextField(FIELD_WIDTH);	//New
        vehicleModelField.setText("");						//New
        vehicleYearField = new JTextField(FIELD_WIDTH);		//New
        vehicleYearField.setText("yyyy");					//New
        vehicleColorField = new JTextField(FIELD_WIDTH);	//New
        vehicleColorField.setText("");						//New
        vehicleLicenseField = new JTextField(FIELD_WIDTH);	//New
        vehicleLicenseField.setText("");					//New
        vehicleResidencyField = new JTextField(FIELD_WIDTH);//New
        vehicleResidencyField.setText("Hours");				//New

       
        //Job Requester Text Fields
        jobRequesterNameField = new JTextField(FIELD_WIDTH);
        jobRequesterNameField.setText("");
        jobRequesterDOBField = new JFormattedTextField(dateFormat);
        jobRequesterDOBField.setText("mm/dd/yyyy");
        jobRequesterDOBField.setColumns(10);
        jobRequesterIDField = new JTextField(FIELD_WIDTH);
        jobRequesterIDField.setText("");
        jobDurationField = new JTextField(FIELD_WIDTH);	//New
        jobDurationField.setText("Hours");				//New
        jobDeadlineField = new JTextField(FIELD_WIDTH);	//New
        jobDeadlineField.setText("mm/dd/yyyy");			//New
        jobTypeField = new JTextField(FIELD_WIDTH);		//New
        jobTypeField.setText("");						//New
        jobIntensityField = new JTextField(FIELD_WIDTH);//New
        jobIntensityField.setText("Easy/Medium/Hard");	//New
    }
	class VehicleOwnerListener implements ActionListener {
		public void actionPerformed(ActionEvent event) {
			layout.show(cardsPanel, "vehicleOwner");
			//Reset Text Fields
			vehicleOwnerNameField.setText("");
			vehicleOwnerDOBField.setText("mm/dd/yyyy");
			vehicleOwnerIDField.setText("");
	        vehicleMakeField.setText("");		//New
	        vehicleModelField.setText("");		//New
	        vehicleYearField.setText("yyyy");	//New
	        vehicleColorField.setText("");		//New
	        vehicleLicenseField.setText("");	//New
	        vehicleResidencyField.setText("Hours");//New
		}
	}
	
	class JobRequesterListener implements ActionListener {
		public void actionPerformed(ActionEvent event) {
			layout.show(cardsPanel, "jobRequester");
			
			//Reset Text Fields
			jobRequesterNameField.setText("");
			jobRequesterDOBField.setText("mm/dd/yyyy");
			jobRequesterIDField.setText("");
			jobDurationField.setText("Hours");				//New
			jobDeadlineField.setText("mm/dd/yyyy");			//New
			jobTypeField.setText("");						//New
			jobIntensityField.setText("Easy/Medium/Hard");	//New
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
                String make = vehicleMakeField.getText();				//New
    	        String model = vehicleModelField.getText();				//New
    	        String year = vehicleYearField.getText();				//New
    	        String color = vehicleColorField.getText();				//New
    	        String license = vehicleLicenseField.getText();			//New
    	        String residency = vehicleResidencyField.getText();		//New

                
                String outputString = "Vehicle Owner: " + name + "\nDate of Birth: " + dob + "\nID: " + id + "\nMake: " + make + "\nModel: " + model + "\nYear: " + year 
                		+ "\nColor: " + color + "\nlicense: " + license + "\nResidency: " + residency;
                try {
                    output.println(outputString);
                } catch (Exception ex) {
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
                String duration = jobDurationField.getText(); 	//New
    	        String deadline = jobDeadlineField.getText();	//New
    	        String type = jobTypeField.getText(); 			//New
    	        String intensity = jobIntensityField.getText(); //New
                String outputString = "Job Requester: " + name + "\nDate of Birth: " + dob + "\nID: " + id + "\nJob Duration: " + duration 
                		+ "\nJob Deadline: " + deadline + "\nJob Type: " + type + "\nJob Intensity: " + intensity;
                try {
                    output.println(outputString);
                } catch (Exception ex) {
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
	    JPanel jobDurationPanel = new JPanel(); //New
	    JPanel jobDeadlinePanel = new JPanel();//New
	    JPanel jobTypePanel = new JPanel(); 	//New
	    JPanel jobIntensityPanel = new JPanel(); //New
	    JPanel vehicleMakePanel = new JPanel();	//New
	    JPanel vehicleModelPanel = new JPanel();	//New
	    JPanel vehicleYearPanel = new JPanel();	//New
	    JPanel vehicleColorPanel = new JPanel();	//New
	    JPanel vehicleLicensePanel = new JPanel();	//New
	    JPanel vehicleResidencyPanel = new JPanel();//New
		
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
		
		vehicleMakePanel.add(vehicleMakeLabel);
		vehicleMakePanel.add(vehicleMakeField);
		
		vehicleModelPanel.add(vehicleModelLabel);
		vehicleModelPanel.add(vehicleModelField);
		
		vehicleYearPanel.add(vehicleYearLabel);
		vehicleYearPanel.add(vehicleYearField);
		
		vehicleColorPanel.add(vehicleColorLabel);
		vehicleColorPanel.add(vehicleColorField);
		
		vehicleLicensePanel.add(vehicleLicenseLabel);
		vehicleLicensePanel.add(vehicleLicenseField);
		
		vehicleResidencyPanel.add(vehicleResidencyLabel);
		vehicleResidencyPanel.add(vehicleResidencyField);
		
		vehicleOwnerPanel.add(vehicleOwnerDescLabel);
		vehicleOwnerPanel.add(vehicleOwnerNamePanel);
		vehicleOwnerPanel.add(vehicleOwnerDOBPanel);
		vehicleOwnerPanel.add(vehicleOwnerIDPanel);
		vehicleOwnerPanel.add(vehicleMakePanel);
		vehicleOwnerPanel.add(vehicleModelPanel);
		vehicleOwnerPanel.add(vehicleYearPanel);
		vehicleOwnerPanel.add(vehicleColorPanel);
		vehicleOwnerPanel.add(vehicleLicensePanel);
		vehicleOwnerPanel.add(vehicleResidencyPanel);
		vehicleOwnerPanel.add(vehicleOwnerBackButton);
		vehicleOwnerPanel.add(vehicleOwnerSubmitButton); // add to panel

		
		//Job Requester Panels
		jobRequesterNamePanel.add(jobRequesterNameLabel);
		jobRequesterNamePanel.add(jobRequesterNameField);
		
		jobRequesterDOBPanel.add(jobRequesterDOBLabel);
		jobRequesterDOBPanel.add(jobRequesterDOBField);
		
		jobRequesterIDPanel.add(jobRequesterIDLabel);
		jobRequesterIDPanel.add(jobRequesterIDField);
		
		jobDurationPanel.add(jobDurationLabel); //New
		jobDurationPanel.add(jobDurationField); //New
		
		jobDeadlinePanel.add(jobDeadlineLabel);  //New
		jobDeadlinePanel.add(jobDeadlineField);  //New
		
		jobTypePanel.add(jobTypeLabel);  //New
		jobTypePanel.add(jobTypeField);  //New
		
		
		jobIntensityPanel.add(jobIntensityLabel);  //New
		jobIntensityPanel.add(jobIntensityField);  //New

		
		jobRequesterPanel.add(jobRequesterDescLabel);
		jobRequesterPanel.add(jobRequesterNamePanel);
		jobRequesterPanel.add(jobRequesterDOBPanel);
		jobRequesterPanel.add(jobRequesterIDPanel);
		jobRequesterPanel.add(jobDurationPanel);	 //New
		jobRequesterPanel.add(jobDeadlinePanel);	//New
		jobRequesterPanel.add(jobTypePanel); 		//New
		jobRequesterPanel.add(jobIntensityPanel); 	//New
		
		jobRequesterPanel.add(jobRequesterBackButton);
		jobRequesterPanel.add(jobRequesterSubmitButton); // add to panel
	}
}