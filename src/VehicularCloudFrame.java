import java.awt.CardLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;
import java.util.Queue;


public class VehicularCloudFrame extends JFrame {
    private static final int FRAME_WIDTH = 350;
    private static final int FRAME_HEIGHT = 500;
    private static final int FIELD_WIDTH = 10;

    private JLabel homeDescLabel;
    private JLabel vehicleOwnerDescLabel;
    private JLabel jobRequesterDescLabel;
    private JLabel vehicleOwnerNameLabel;
    private JLabel jobRequesterNameLabel;
    private JLabel vehicleOwnerDOBLabel;
    private JLabel jobRequesterDOBLabel;
    private JLabel vehicleOwnerIDLabel;
    private JLabel vehicleMakeLabel;	
    private JLabel vehicleModelLabel;	
    private JLabel vehicleYearLabel;	
    private JLabel vehicleColorLabel;	
    private JLabel vehicleLicenseLabel;	
    private JLabel vehicleResidencyLabel;
    private JLabel jobRequesterIDLabel;
    private JLabel jobDurationLabel; 	
    private JLabel jobDeadlineLabel;	
    private JLabel jobTypeLabel; 		
    private JLabel jobIntensityLabel;	
    private JTextField vehicleOwnerNameField;
    private JTextField jobRequesterNameField;
    private JTextField vehicleOwnerIDField;
    private JTextField vehicleMakeField;	
    private JTextField vehicleModelField;	
    private JTextField vehicleYearField;	
    private JTextField vehicleColorField;	
    private JTextField vehicleLicenseField;	
    private JTextField vehicleResidencyField;
    private JTextField jobRequesterIDField;
    private JTextField jobDurationField;	
    private JTextField jobDeadlineField;	
    private JTextField jobTypeField; 		
    private JTextField jobIntensityField;	
    private JFormattedTextField vehicleOwnerDOBField;
    private JFormattedTextField jobRequesterDOBField;
    private JButton vehicleOwnerButton;
    private JButton jobRequesterButton;
    private JButton vccButton;
    
    private JButton calculateCompletionTimeButton;

    private JButton vehicleOwnerSubmitButton; 
    private JButton jobRequesterSubmitButton; 
    private JButton vehicleOwnerBackButton;
    private JButton jobRequesterBackButton;
    private CardLayout layout;
    private JPanel cardsPanel;
    private PrintStream output;
    private Timestamp timestamp;
    private Date date;
    private VCController vcc;
    private String completionTime;
    private String jobRequesterName;
    private String jobID;
    private String jobDuration;
    
    private JFrame vccFrame;
    private CardLayout vccLayout;
    private JPanel vccCardsPanel;
    private DefaultTableModel model;
    private JTable completionTimeTable;
    private JScrollPane completionTable;
    private JButton vccCompletionTimeButton;
    private JButton vccCompletionTimeBackButton;
    private JButton vccViewRequestsButton;
    private JButton vccViewRequestsBackButton;
    
    private JButton vccAcceptButton;
    private JButton vccRejectButton;
    
    private String requestType;
    private String requestStatus;
    private Client jobClient;
    private Client vehicleClient;
    private Server server;
    private Thread clientThread;
    private Thread serverThread;
    private Queue<String> userInput;
    private JTextArea userInputTextArea;


    // Constructor
	public VehicularCloudFrame() throws FileNotFoundException, IOException {
		homeDescLabel = new JLabel("Please click on which type of user you are:");
        vehicleOwnerDescLabel = new JLabel("Please enter the following information:");
        jobRequesterDescLabel = new JLabel("Please enter the following information:");
        vehicleOwnerNameLabel = new JLabel("Name: ");
        jobRequesterNameLabel = new JLabel("Name: ");
        vehicleOwnerDOBLabel = new JLabel("Date of Birth: ");
        jobRequesterDOBLabel = new JLabel("Date of Birth: ");
        vehicleOwnerIDLabel = new JLabel("ID Number: ");
        vehicleMakeLabel = new JLabel("Make: ");				
        vehicleModelLabel = new JLabel("Model ");				
        vehicleYearLabel = new JLabel("Year ");					
        vehicleColorLabel = new JLabel("Color: ");				
        vehicleLicenseLabel = new JLabel("License Plate: ");	
        vehicleResidencyLabel = new JLabel("Residency Time (in hours): "); 
        jobRequesterIDLabel = new JLabel("ID Number: ");
        jobDurationLabel = new JLabel("Job Duration (in hours): ");	
        jobDeadlineLabel = new JLabel("Job Deadline: ");			
        jobTypeLabel = new JLabel("Job Type: ");					
        jobIntensityLabel = new JLabel("Job Intensity: ");			
        layout = new CardLayout();
        cardsPanel = new JPanel(layout);
		output = new PrintStream(new File("userInformation.txt"));
		date = new Date();
		timestamp = new Timestamp(date.getTime());
		requestType = "";
		requestStatus = "";
		vcc = new VCController();
		
		vccFrame = new JFrame();
		vccLayout = new CardLayout();
        vccCardsPanel = new JPanel(vccLayout);
        model = new DefaultTableModel();
        completionTimeTable = new JTable(model);
        userInput = new LinkedList<String>();
        userInputTextArea = new JTextArea("No Requests Available");
        
        completionTable = new JScrollPane(completionTimeTable);
		completionTable.setPreferredSize(new Dimension(525,400));
		
		
		//Creates new instance of Server and executes thread
	    server = new Server();
	    serverThread = new Thread(server);
	    serverThread.start();

        createTextFields();
        createButtons();
        createPanels();
        createTable();

        setSize(FRAME_WIDTH, FRAME_HEIGHT);
        setTitle("Vehicular Cloud Management System");
        setLocationRelativeTo(null);
        
        vehicleOwnerSubmitButton.addActionListener(new VehicleOwnerSubmitListener());
        jobRequesterSubmitButton.addActionListener(new JobRequesterSubmitListener());
    }

	//Saves User Data to a File
	private void saveUserDataToFile(String userData) {
	    output.println(userData);
	    output.println();
		String currentDir = System.getProperty("user.dir");
		JOptionPane.showMessageDialog(this, "User data saved successfully!\n\n Current working directory:" + currentDir);
	}

	//Register Vehicle Button Listener
	class VehicleOwnerSubmitListener implements ActionListener {
	    public void actionPerformed(ActionEvent event) {
	    	date = new Date();
	    	timestamp = new Timestamp(date.getTime());
	    	requestType = "vehicle";
	    	
	        String name = vehicleOwnerNameField.getText();
	        String dob = vehicleOwnerDOBField.getText();
	        String id = vehicleOwnerIDField.getText();
	        String make = vehicleMakeField.getText();	
	        String model = vehicleModelField.getText();	
	        String year = vehicleYearField.getText();	
	        String color = vehicleColorField.getText();	
	        String license= vehicleLicenseField.getText();	
	        String residency = vehicleResidencyField.getText(); 
	        
	        String outputString = timestamp + " \nVehicle Owner: " + name + "\nDOB: " + dob + "\nID: " + id + "\nMake: " + make + "\nModel: " + model + "\nYear: " + year + "\nColor: " + color + "\nLicense: " + license + "\nResidency Time: " + residency;
	        
	        //Creates an instance of Client class and executes thread
	        vehicleClient = new Client();
	        vehicleClient.setOutput(outputString);
		    Thread clientThread = new Thread(vehicleClient);
		    clientThread.start();
		    try {
				clientThread.join();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		    //Adds the request received by the server to a queue
		    userInput.add(server.getUserData());
		    updateUserInputText();
		    
		    //Outputs a message to the user that data is being processed by server
		   JOptionPane.showMessageDialog(null, vehicleClient.getProcessingMessage());
	        
	        //Reset Text Fields
	        vehicleOwnerNameField.setText("");
	        vehicleOwnerDOBField.setText("mm/dd/yyyy");
	        vehicleOwnerIDField.setText("");
	        vehicleMakeField.setText("");
	        vehicleModelField.setText("");
	        vehicleYearField.setText("yyyy");
	        vehicleColorField.setText("");
	        vehicleLicenseField.setText("");
	        vehicleResidencyField.setText("Hours");
	    }
	}

	//Submit Job Button Listener
	class JobRequesterSubmitListener implements ActionListener {

	    public void actionPerformed(ActionEvent event) {
	    	date = new Date();
	    	timestamp = new Timestamp(date.getTime());
	    	requestType = "job";
	    	
	        String name = jobRequesterNameField.getText();
	        String dob = jobRequesterDOBField.getText();
	        String id = jobRequesterIDField.getText();
	        double duration = Double.parseDouble(jobDurationField.getText());
	        String deadline = jobDeadlineField.getText();
	        String type = jobTypeField.getText(); 	
	        String intensity = jobIntensityField.getText(); 
	        
	        //Add a job to VCC Job Queue 
	        Job job = new Job(duration, deadline, type, intensity, false, false, 0);
	        vcc.addJob(job);
	        
	        //Get Completion Time and add row to VCC Table
	        completionTime = "" + job.getCompletionTime();
	        jobID = id;
	        jobDuration = "" + job.getDuration();
	        jobRequesterName = name;
	        model.addRow(new Object[] {jobRequesterName, jobID, jobDuration, completionTime});
	        
	        //Output Job Info to File
	        String outputString = timestamp + " \nJob Requester: " + name + "\nDOB: " + dob + "\nID: " + id + "\nDuration: " + duration + "\nDeadline: " + deadline + "\nType: " +type +"\nIntensity: " + intensity + "\nCompletion Time: " + completionTime;
	        
	        //Creates an instance of Client class and executes thread
	        jobClient = new Client();
	        jobClient.setOutput(outputString);
		    clientThread = new Thread(jobClient);
		    clientThread.start();
		    try {
				clientThread.join();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
	        
		    //Adds the request received by the server to a queue
		    userInput.add(server.getUserData());
		    updateUserInputText();
		    
		    //Outputs a message to the user that data is being processed by server
		    JOptionPane.showMessageDialog(null, jobClient.getProcessingMessage());
	        
	        //Reset Text Fields
	        jobRequesterNameField.setText("");
	        jobRequesterDOBField.setText("mm/dd/yyyy");
	        jobRequesterIDField.setText("");
	        jobDurationField.setText("");
	        jobDeadlineField.setText("mm/dd/yyyy");
	        jobTypeField.setText("");
	        jobIntensityField.setText("Easy/Medium/Hard");
	        
	    }
	    
	}
	
	//Individual User Calculate Completion Time Button Listener
    class CompletionTimeListener implements ActionListener {
        public void actionPerformed(ActionEvent event) {
        	JOptionPane.showMessageDialog(null, "Completion Time: " + completionTime + " hours");
        }
    }
    
    //VCC Overall Calculate Completion Time Button Listener
    class VCCCompletionTimeListener implements ActionListener {
        public void actionPerformed(ActionEvent event) {
        	vccLayout.show(vccCardsPanel, "completionTime");
        	vccFrame.setSize(800, 500);
        }
    }
    
    //Update User Input Text Method
    public void updateUserInputText() {
    	if(userInput.peek() != null) {
    		userInputTextArea.setText(userInput.peek());
        	userInputTextArea.revalidate();
    	}
    	else {
    		userInputTextArea.setText("No Requests Available");
    	}
    }
    
    //VCC View Requests Button Listener
    class VCCViewRequestsListener implements ActionListener {
        public void actionPerformed(ActionEvent event) {
        	vccLayout.show(vccCardsPanel, "viewRequests");
        	vccFrame.setSize(FRAME_WIDTH, FRAME_HEIGHT);
        	updateUserInputText();
        }
    }
	
    //Creates Table of All Job Completion Times for VCC
    private void createTable() {
    	
    	model.addColumn("Job Requester Name");
    	model.addColumn("Job Requester ID");
    	model.addColumn("Job Duration");
    	model.addColumn("Total Completion Time");
    	
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
        vehicleMakeField = new JTextField(FIELD_WIDTH);	
        vehicleMakeField.setText("");
        vehicleModelField = new JTextField(FIELD_WIDTH);	
        vehicleModelField.setText("");						
        vehicleYearField = new JTextField(FIELD_WIDTH);		
        vehicleYearField.setText("yyyy");					
        vehicleColorField = new JTextField(FIELD_WIDTH);	
        vehicleColorField.setText("");						
        vehicleLicenseField = new JTextField(FIELD_WIDTH);	
        vehicleLicenseField.setText("");					
        vehicleResidencyField = new JTextField(FIELD_WIDTH);
        vehicleResidencyField.setText("Hours");				

       
        //Job Requester Text Fields
        jobRequesterNameField = new JTextField(FIELD_WIDTH);
        jobRequesterNameField.setText("");
        jobRequesterDOBField = new JFormattedTextField(dateFormat);
        jobRequesterDOBField.setText("mm/dd/yyyy");
        jobRequesterDOBField.setColumns(10);
        jobRequesterIDField = new JTextField(FIELD_WIDTH);
        jobRequesterIDField.setText("");
        jobDurationField = new JTextField(FIELD_WIDTH);	
        jobDurationField.setText("");				
        jobDeadlineField = new JTextField(FIELD_WIDTH);	
        jobDeadlineField.setText("mm/dd/yyyy");			
        jobTypeField = new JTextField(FIELD_WIDTH);		
        jobTypeField.setText("");						
        jobIntensityField = new JTextField(FIELD_WIDTH);
        jobIntensityField.setText("Easy/Medium/Hard");	
        jobIntensityField.setColumns(12);
    }
    
    //Vehicle Owner Page Button Listener
	class VehicleOwnerListener implements ActionListener {
		public void actionPerformed(ActionEvent event) {
			layout.show(cardsPanel, "vehicleOwner");
			//Reset Text Fields
			vehicleOwnerNameField.setText("");
			vehicleOwnerDOBField.setText("mm/dd/yyyy");
			vehicleOwnerIDField.setText("");
	        vehicleMakeField.setText("");		
	        vehicleModelField.setText("");		
	        vehicleYearField.setText("yyyy");	
	        vehicleColorField.setText("");		
	        vehicleLicenseField.setText("");	
	        vehicleResidencyField.setText("Hours");
		}
	}
	
	//Job Requester Page Button Listener
	class JobRequesterListener implements ActionListener {
		public void actionPerformed(ActionEvent event) {
			layout.show(cardsPanel, "jobRequester");
			
			//Reset Text Fields
			jobRequesterNameField.setText("");
			jobRequesterDOBField.setText("mm/dd/yyyy");
			jobRequesterIDField.setText("");
			jobDurationField.setText("");				
			jobDeadlineField.setText("mm/dd/yyyy");			
			jobTypeField.setText("");						
			jobIntensityField.setText("Easy/Medium/Hard");	
		}
	}

	//VCC Page Button Listener
	class VCCListener implements ActionListener {
		public void actionPerformed(ActionEvent event) {
	        vccFrame.setSize(FRAME_WIDTH, FRAME_HEIGHT);
	        vccFrame.setTitle("Vehicular Cloud Controller");
	        vccFrame.setLocationRelativeTo(null);
		    vccFrame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		    vccFrame.setVisible(true);
		    vccLayout.show(vccCardsPanel, "home");
		}
	}
	
	//VCC Page Accept Button Listener
		class VCCAcceptListener implements ActionListener {
			public void actionPerformed(ActionEvent event) {
				server.approveData(true);
				server.respondToClient();
				saveUserDataToFile(userInput.peek());
				userInput.remove();
				updateUserInputText();
			}
		}
		
	//VCC Page Accept Button Listener
	//NEEDS TO BE PROPERLY DEFINED
			class VCCRejectListener implements ActionListener {
				public void actionPerformed(ActionEvent event) {
			        vccFrame.setSize(FRAME_WIDTH, FRAME_HEIGHT);
			        vccFrame.setTitle("Vehicular Cloud Controller");
			        vccFrame.setLocationRelativeTo(null);
				    vccFrame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
				    vccFrame.setVisible(true);
				    vccLayout.show(vccCardsPanel, "home");
				    
				}
			}

	
	//Return to Main Home Page Listener
	class ReturnHomeListener implements ActionListener {
		public void actionPerformed(ActionEvent event) {
			layout.show(cardsPanel, "home");
		}
	}
	
	//Return to VCC Home Page Listener
	class ReturnVCCHomeListener implements ActionListener {
		public void actionPerformed(ActionEvent event) {
			vccLayout.show(vccCardsPanel, "home");
			vccFrame.setSize(FRAME_WIDTH, FRAME_HEIGHT);
		}
	}
	
	
	//Creates the buttons
	private void createButtons() {
		vehicleOwnerButton = new JButton("Vehicle Owner");
		jobRequesterButton = new JButton("Job Requester");
		vccButton = new JButton("VCController");
		vehicleOwnerBackButton = new JButton("Back");
		jobRequesterBackButton = new JButton("Back");
		calculateCompletionTimeButton = new JButton("Calculate Completion Time");
		vccCompletionTimeButton = new JButton("Calculate All Completion Times");
		vccCompletionTimeBackButton = new JButton("Back");
		vccViewRequestsButton = new JButton("View Requests");
		vccViewRequestsBackButton = new JButton("Back");
		
		
		//UPDATED- Creates accept/reject buttons within VCController
		vccAcceptButton = new JButton("Accept");
		vccRejectButton = new JButton("Reject");
		
		ActionListener vehicleOwnerListener = new VehicleOwnerListener();
		vehicleOwnerButton.addActionListener(vehicleOwnerListener);
		
		ActionListener jobRequesterListener = new JobRequesterListener();
		jobRequesterButton.addActionListener(jobRequesterListener);
		
		ActionListener vccListener = new VCCListener();
		vccButton.addActionListener(vccListener);
		
		ActionListener vehicleOwnerBackListener = new ReturnHomeListener();
		vehicleOwnerBackButton.addActionListener(vehicleOwnerBackListener);
		
		ActionListener jobRequesterBackListener = new ReturnHomeListener();
		jobRequesterBackButton.addActionListener(jobRequesterBackListener);
		
		ActionListener completionTimeButtonListener = new CompletionTimeListener();
		calculateCompletionTimeButton.addActionListener(completionTimeButtonListener);
		
		ActionListener vccCompletionTimeButtonListener = new VCCCompletionTimeListener();
		vccCompletionTimeButton.addActionListener(vccCompletionTimeButtonListener);
		
		ActionListener vccCompletionTimeBackListener = new ReturnVCCHomeListener();
		vccCompletionTimeBackButton.addActionListener(vccCompletionTimeBackListener);
		
		ActionListener vccViewRequestsButtonListener = new VCCViewRequestsListener();
		vccViewRequestsButton.addActionListener(vccViewRequestsButtonListener);
		
		ActionListener vccViewRequestsBackListener = new ReturnVCCHomeListener();
		vccViewRequestsBackButton.addActionListener(vccViewRequestsBackListener);
		
		
		//UPDATED- Adds action listeners to accept/reject buttons
		ActionListener vccAcceptListener = new VCCAcceptListener();
		vccAcceptButton.addActionListener(vccAcceptListener);

		ActionListener vccRejectListener = new VCCListener();
		vccRejectButton.addActionListener(vccRejectListener);
		
		
		//Vehicle Owner Submit Button
        vehicleOwnerSubmitButton = new JButton("Submit");
        ActionListener vehicleOwnerSubmitListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String name = vehicleOwnerNameField.getText();
                String dob = vehicleOwnerDOBField.getText();
                String id = vehicleOwnerIDField.getText();
                String make = vehicleMakeField.getText();				
    	        String model = vehicleModelField.getText();				
    	        String year = vehicleYearField.getText();				
    	        String color = vehicleColorField.getText();				
    	        String license = vehicleLicenseField.getText();			
    	        String residency = vehicleResidencyField.getText();		

                
                String outputString = timestamp.toString() + "Vehicle Owner: " + name + "\nDate of Birth: " + dob + "\nID: " + id + "\nMake: " + make + "\nModel: " + model + "\nYear: " + year 
                		+ "\nColor: " + color + "\nlicense: " + license + "\nResidency: " + residency;

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
                String duration = jobDurationField.getText(); 	
    	        String deadline = jobDeadlineField.getText();	
    	        String type = jobTypeField.getText(); 			
    	        String intensity = jobIntensityField.getText(); 
                String outputString = timestamp.toString() + "Job Requester: " + name + "\nDate of Birth: " + dob + "\nID: " + id + "\nJob Duration: " + duration 
                		+ "\nJob Deadline: " + deadline + "\nJob Type: " + type + "\nJob Intensity: " + intensity;
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
		JPanel vehicleOwnerButtonPanel = new JPanel();
		JPanel jobRequesterButtonPanel = new JPanel();
	    JPanel jobDurationPanel = new JPanel(); 
	    JPanel jobDeadlinePanel = new JPanel();
	    JPanel jobTypePanel = new JPanel(); 	
	    JPanel jobIntensityPanel = new JPanel(); 
	    JPanel vehicleMakePanel = new JPanel();	
	    JPanel vehicleModelPanel = new JPanel();	
	    JPanel vehicleYearPanel = new JPanel();	
	    JPanel vehicleColorPanel = new JPanel();	
	    JPanel vehicleLicensePanel = new JPanel();	
	    JPanel vehicleResidencyPanel = new JPanel();
	    JPanel vccHomePanel = new JPanel();
	    JPanel vccCompletionTimePanel = new JPanel();
	    JPanel vccViewRequestsPanel = new JPanel();
	    JPanel vccRequestsButtonPanel = new JPanel();
		
		cardsPanel.add(homePanel, "home");
		cardsPanel.add(vehicleOwnerPanel, "vehicleOwner");
		cardsPanel.add(jobRequesterPanel, "jobRequester");
		add(cardsPanel);
		
		vccCardsPanel.add(vccHomePanel, "home");
		vccCardsPanel.add(vccCompletionTimePanel, "completionTime");
		vccCardsPanel.add(vccViewRequestsPanel, "viewRequests");
		vccFrame.add(vccCardsPanel);
		
		//Home Screen Panel
		homePanel.add(homeDescLabel);
		homePanel.add(vehicleOwnerButton);
		homePanel.add(jobRequesterButton);
		homePanel.add(vccButton);
		
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
		
		vehicleOwnerButtonPanel.add(vehicleOwnerBackButton);
		vehicleOwnerButtonPanel.add(vehicleOwnerSubmitButton);
		
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
		
		vehicleOwnerPanel.add(vehicleOwnerButtonPanel);
		
		//Job Requester Panels
		jobRequesterNamePanel.add(jobRequesterNameLabel);
		jobRequesterNamePanel.add(jobRequesterNameField);
		
		jobRequesterDOBPanel.add(jobRequesterDOBLabel);
		jobRequesterDOBPanel.add(jobRequesterDOBField);
		
		jobRequesterIDPanel.add(jobRequesterIDLabel);
		jobRequesterIDPanel.add(jobRequesterIDField);
		
		jobDurationPanel.add(jobDurationLabel); 
		jobDurationPanel.add(jobDurationField); 
		
		jobDeadlinePanel.add(jobDeadlineLabel);  
		jobDeadlinePanel.add(jobDeadlineField);  
		
		jobTypePanel.add(jobTypeLabel);  
		jobTypePanel.add(jobTypeField);  
		
		
		jobIntensityPanel.add(jobIntensityLabel);  
		jobIntensityPanel.add(jobIntensityField); 

		jobRequesterButtonPanel.add(jobRequesterBackButton);
		jobRequesterButtonPanel.add(jobRequesterSubmitButton);

		jobRequesterPanel.add(jobRequesterDescLabel);
		jobRequesterPanel.add(jobRequesterNamePanel);
		jobRequesterPanel.add(jobRequesterDOBPanel);
		jobRequesterPanel.add(jobRequesterIDPanel);
		jobRequesterPanel.add(jobDurationPanel);	
		jobRequesterPanel.add(jobDeadlinePanel);	
		jobRequesterPanel.add(jobTypePanel); 		
		jobRequesterPanel.add(jobIntensityPanel); 	
		jobRequesterPanel.add(jobRequesterButtonPanel);
		jobRequesterPanel.add(calculateCompletionTimeButton);
		
		//VCC Panels
		vccHomePanel.add(vccCompletionTimeButton);
		vccHomePanel.add(vccViewRequestsButton);
		vccCompletionTimePanel.add(completionTable);
		vccCompletionTimePanel.add(vccCompletionTimeBackButton);
		
		//Added separate panel to view user requests
		vccViewRequestsPanel.add(userInputTextArea);
		vccRequestsButtonPanel.add(vccAcceptButton); //not properly defined, currently does the same thing as the back button
		vccRequestsButtonPanel.add(vccRejectButton); //not properly defined, currently does the same thing as the back button
		vccRequestsButtonPanel.add(vccViewRequestsBackButton);
		vccViewRequestsPanel.add(vccRequestsButtonPanel);
	}
}
