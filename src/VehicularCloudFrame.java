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
import javax.swing.text.AttributeSet.ColorAttribute;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;
import java.util.Queue;
import javax.swing.*;
import java.awt.*;


public class VehicularCloudFrame extends JFrame {
    private static final int FRAME_WIDTH = 500;
    private static final int FRAME_HEIGHT = 500;
    private static final int FIELD_WIDTH = 10;
	
    private JTextField dialogueBox;
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
    private String residencyTime;//

    private String jobRequesterName;
    private String jobID;
    private String jobDuration;
    
    private JFrame vccFrame;
    private CardLayout vccLayout;
    private JPanel vccCardsPanel;
    private DefaultTableModel model;
    private DefaultTableModel model1;//
    private DefaultTableModel model2;//
    
    private JTable jobCompletionTimeTable;//
    private JTable vehicleResidencyTimeTable;//
    private JScrollPane jobCompletionTable;//
    private JScrollPane vehicleResidencyTable;//

    private JButton vccJobCompletionTimeButton;//
    private JButton vccJobCompletionTimeBackButton;//
    
    private JButton vccVehicleResidencyTimeButton;//
    private JButton vccVehicleResidencyTimeBackButton;//

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
    
    private Queue<Job> tempJobQueue; //new
    private Queue<String> jobIDQueue; //new
    private Queue<String> jobNameQueue; //new
	private Queue<String> jobDOBQueue; // new
    private Queue<String> jobTimeStampQueue; // new
    private Queue<String> requestTypeQueue; //new
    
    private Queue<Vehicle> tempVehicleQueue;//
    private Queue<String> vehicleIDQueue;//
    private Queue<String> vehicleNameQueue;//
	private Queue<String> vehicleDOBQueue; // new
    private Queue<String> vehicleTimeStampQueue; // new

	private Database database; //new

  public void setLabelFont(JLabel label, int size) {
	    Font font = new Font("SansSerif", Font.BOLD, size);
	    label.setFont(font);
	    label.setForeground(Color.decode("#FFFFFF"));
	}

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
        vehicleMakeLabel = new JLabel("Make: ");
        vehicleModelLabel = new JLabel("Model ");				
        vehicleYearLabel = new JLabel("Year ");		
        vehicleColorLabel = new JLabel("Color: ");		
        vehicleLicenseLabel = new JLabel("License Plate: ");	
        vehicleResidencyLabel = new JLabel("Residency Time: "); 
        jobRequesterIDLabel = new JLabel("ID Number: ");
        jobDurationLabel = new JLabel("Job Duration (in hours): ");	
        jobDeadlineLabel = new JLabel("Job Deadline: ");		
        jobTypeLabel = new JLabel("Job Type: ");			
        jobIntensityLabel = new JLabel("Job Intensity: ");		
        setLabelFont(homeDescLabel, 20);
        setLabelFont(vehicleOwnerDescLabel, 20);
        setLabelFont(jobRequesterDescLabel, 20);
        setLabelFont(vehicleOwnerNameLabel, 15);
        setLabelFont(jobRequesterNameLabel, 15);
        setLabelFont(vehicleOwnerDOBLabel, 15);
        setLabelFont(jobRequesterDOBLabel, 15);
        setLabelFont(vehicleOwnerIDLabel, 15);
        setLabelFont(vehicleMakeLabel, 15);
        setLabelFont(vehicleModelLabel, 15);
        setLabelFont(vehicleYearLabel, 15);
        setLabelFont(vehicleColorLabel, 15);
        setLabelFont(vehicleLicenseLabel, 15);
        setLabelFont(vehicleResidencyLabel, 15);
        setLabelFont(jobRequesterIDLabel, 15);
        setLabelFont(jobDurationLabel, 15);
        setLabelFont(jobDeadlineLabel, 15);
        setLabelFont(jobTypeLabel, 15);
        setLabelFont(jobIntensityLabel, 15);
		
        layout = new CardLayout();
        cardsPanel = new JPanel(layout);
		output = new PrintStream(new File("userInformation.txt"));
		date = new Date();
		timestamp = new Timestamp(date.getTime());
		requestType = "";
		requestStatus = "";
		vcc = new VCController();
		database = new Database(); //new
		
		tempJobQueue = new LinkedList<Job>(); //new
		jobIDQueue = new LinkedList<String>(); //new
		jobNameQueue = new LinkedList<String>(); //new
		jobDOBQueue = new LinkedList<String>(); //new
		jobTimeStampQueue = new LinkedList<String>(); //new
		requestTypeQueue = new LinkedList<String>();
		
		tempVehicleQueue = new LinkedList<Vehicle>();//
		vehicleIDQueue = new LinkedList<String>();//
		vehicleNameQueue = new LinkedList<String>();//
		vehicleDOBQueue = new LinkedList<String>(); //new
		vehicleTimeStampQueue = new LinkedList<String>(); //new
		
		vccFrame = new JFrame();
		vccLayout = new CardLayout();
        vccCardsPanel = new JPanel(vccLayout);
        model = new DefaultTableModel();
        
        model1 = new DefaultTableModel();//
        model2 = new DefaultTableModel();//

        //jobCompletionTimeTable = new JTable(model);//
        jobCompletionTimeTable = new JTable(model1);//

        //vehicleResidencyTimeTable = new JTable(model);//
        vehicleResidencyTimeTable = new JTable(model2);//

        userInput = new LinkedList<String>();
        userInputTextArea = new JTextArea("No Requests Available");
        userInputTextArea.setBackground(Color.decode("#68AAC3"));
        userInputTextArea.setForeground(Color.WHITE);
        userInputTextArea.setFont(new Font("Ariel", Font.BOLD, 15));
        
        jobCompletionTable = new JScrollPane(jobCompletionTimeTable);//
		jobCompletionTable.setPreferredSize(new Dimension(525,400));//
		
        vehicleResidencyTable = new JScrollPane(vehicleResidencyTimeTable);//
        vehicleResidencyTable.setPreferredSize(new Dimension(525,400));//

	    //Connects to Database
	    database.connectDatabase();//new
		
		//Creates new instance of Server and executes thread
	    server = new Server();
	    serverThread = new Thread(server);
	    serverThread.start();

        createTextFields();
        createButtons();
        createPanels();
        //createTable();
        createJobTable();//
        createVehicleTable();//


        setSize(FRAME_WIDTH, FRAME_HEIGHT);
        setTitle("Cloud Cruiser");
        setLocationRelativeTo(null);
        
        vehicleOwnerSubmitButton.addActionListener(new VehicleOwnerSubmitListener());
        jobRequesterSubmitButton.addActionListener(new JobRequesterSubmitListener());
    }

	//Displays Processing Message to User
	private void displayProcessingMessage(String message) { //newUpdate
		JOptionPane.showMessageDialog(this, message); //newUpdate
	}
	
	//Displays Request Rejected Message to User
	private void displayRejectionMessage(String userID) { //newUpdate
		JOptionPane.showMessageDialog(this, "User " + userID + ": Data Rejected"); //newUpdate
	}
	
	//Saves Vehicle to a Database
	private void saveVehicleToDatabase(String userID) { //newUpdate
	    database.addVehicle(tempVehicleQueue.peek(), vehicleNameQueue.peek(), vehicleIDQueue.peek(), vehicleDOBQueue.peek(), vehicleTimeStampQueue.peek()); //new
		JOptionPane.showMessageDialog(this, "User " + userID + ": Data Accepted and Stored in Database!"); //newUpdate
	}
	
	//Saves Job to a Database
	private void saveJobToDatabase(String userID) { //newUpdate
		System.out.println(jobTimeStampQueue.peek());
	    database.addJob(tempJobQueue.peek(), jobNameQueue.peek(), jobIDQueue.peek(), jobDOBQueue.peek(), jobTimeStampQueue.peek()); //new
		JOptionPane.showMessageDialog(this, "User " + userID + ": Data Accepted and Stored in Database!"); //newUpdate
	}

	//Register Vehicle Button Listener
	class VehicleOwnerSubmitListener implements ActionListener {
		
	    public void actionPerformed(ActionEvent event) {
	    	date = new Date();
	    	timestamp = new Timestamp(date.getTime());
	    	requestType = "vehicle";
	    	requestTypeQueue.add(requestType);
	    	
	        String name = vehicleOwnerNameField.getText();
	        String dob = vehicleOwnerDOBField.getText();
	        String id = vehicleOwnerIDField.getText();
	        String make = vehicleMakeField.getText();	
	        String model = vehicleModelField.getText();	
	        int year = Integer.parseInt(vehicleYearField.getText());	
	        String color = vehicleColorField.getText();	
	        String license= vehicleLicenseField.getText();	
	        double residency = Double.parseDouble(vehicleResidencyField.getText()); 
	        
	        Vehicle vehicle = new Vehicle(make, model, year, color, license, residency);
	        tempVehicleQueue.add(vehicle);
	        
	        //Get Vehicle Total Residency Time
	        residencyTime = "" + (vcc.calculateResidencyTime() + residency); //new
	        vehicleIDQueue.add(id); //new
	        vehicleNameQueue.add(name); //new
	        
	        //Output Vehicle Info to File
	        String outputString = timestamp + " \nVehicle Owner: " + name + "\nDOB: " + dob + "\nID: " + id + "\nMake: " + make + "\nModel: " + model + "\nYear: " + year + "\nColor: " + color + "\nLicense: " + license + "\nResidency Time: " + residency;
	        
 			//Add a vehicle to VCC Vehicle Queue 
 			vehicleDOBQueue.add(dob); //new
 			vehicleTimeStampQueue.add(timestamp.toString()); //new
			
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
		    displayProcessingMessage(vehicleClient.getProcessingMessage());//newUpdate
	        
	        //Reset Text Fields
	        vehicleOwnerNameField.setText("");
	        vehicleOwnerDOBField.setText("mm/dd/yyyy");
	        vehicleOwnerIDField.setText("");
	        vehicleMakeField.setText("");
	        vehicleModelField.setText("");
	        vehicleYearField.setText("yyyy");
	        vehicleColorField.setText("");
	        vehicleLicenseField.setText("");
	        vehicleResidencyField.setText("");
	    }
	}

	//Submit Job Button Listener
	class JobRequesterSubmitListener implements ActionListener {

	    public void actionPerformed(ActionEvent event) {
	    	date = new Date();
	    	timestamp = new Timestamp(date.getTime());
	    	requestType = "job";
	    	requestTypeQueue.add(requestType);
	    	
	        String name = jobRequesterNameField.getText();
	        String dob = jobRequesterDOBField.getText();
	        String id = jobRequesterIDField.getText();
	        double duration = Double.parseDouble(jobDurationField.getText());
	        String deadline = jobDeadlineField.getText();
	        String type = jobTypeField.getText(); 	
	        String intensity = jobIntensityField.getText(); 
	        
	        //String jobName = jobRequesterNameField.getText();
	        //String jobDob = jobRequesterDOBField.getText();
	        //String jobId = jobRequesterIDField.getText();
	        //double jobDuration = Double.parseDouble(jobDurationField.getText());
	        //String jobDeadline = jobDeadlineField.getText();
	        //String jobType = jobTypeField.getText(); 	
	        //String jobIntensity = jobIntensityField.getText(); 

	        
	        //Add a job to VCC Job Queue// 
	        Job job = new Job(duration, deadline, type, intensity, false, false, 0);
	        tempJobQueue.add(job);
	        
	        
	        //String vehicleName = vehicleOwnerNameField.getText();
	        //String vehicleDob = vehicleOwnerDOBField.getText();
	        //String vehicleId = vehicleOwnerIDField.getText();
	        //String vehicleMake = vehicleMakeField.getText();
	        //String vehicleModel = vehicleModelField.getText();
	        //int vehicleYear = Integer.parseInt(vehicleYearField.getText());
	        //String vehicleColor = vehicleColorField.getText();
	        //String vehicleLicence = vehicleLicenseField.getText();
	        //double vehicleResidency= Double.parseDouble(vehicleResidencyField.getText());
	        
	        //Add a job to VCC Vehicle Queue// 
	        //Vehicle vehicle = new Vehicle(vehicleMake, vehicleModel, vehicleYear, vehicleColor, vehicleLicence, vehicleResidency);
	        //tempVehicleQueue.add(vehicle);
	        
	        //Get Job Completion Time
	        completionTime = "" + (vcc.calculateCompletionTime() + duration); //new
	        jobIDQueue.add(id); //new
	        jobNameQueue.add(name); //new
			jobDOBQueue.add(dob); //update
	        jobTimeStampQueue.add(timestamp.toString()); //update
	        
	        //Output Job Info to File
	        String outputString = timestamp + " \nJob Requester: " + name + "\nDOB: " + dob + "\nID: " + id + "\nDuration: " + duration + "\nDeadline: " + deadline + "\nType: " + type +"\nIntensity: " + intensity + "\nCompletion Time: " + completionTime;
	        
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
		    displayProcessingMessage(jobClient.getProcessingMessage());//newUpdate
	        
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
    
	//Individual User Calculate Residency Time Button Listener//
    class ResidencyTimeListener implements ActionListener {
        public void actionPerformed(ActionEvent event) {
        	JOptionPane.showMessageDialog(null, "Residency Time: " + residencyTime + " hours");
        }
    }
   
    //VCC Overall Calculate Job Completion Time Button Listener//
    class VCCJobCompletionTimeListener implements ActionListener {
        public void actionPerformed(ActionEvent event) {
        	vccLayout.show(vccCardsPanel, "completionTime");
        	vccFrame.setSize(1200, 500);
        }
    }
    
    //VCC Overall Calculate Vehicle Residency Time Button Listener//
    class VCCVehicleResidencyTimeListener implements ActionListener {
        public void actionPerformed(ActionEvent event) {
        	vccLayout.show(vccCardsPanel, "residencyTime");
        	vccFrame.setSize(1200, 500);
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
    private void createJobTable() {
    	
    	model1.addColumn("Job Requester Name");
    	model1.addColumn("Job Requester ID");
    	model1.addColumn("Job Duration");
    	model1.addColumn("Total Completion Time");
    	
    }
    
  //Creates Table of All Vehicle Residency Times for VCC
    private void createVehicleTable() {
    	
    	model2.addColumn("Vehicle Owner Name");
    	model2.addColumn("Vehicle Owner ID");
    	model2.addColumn("Vehicle Residency Time");
    	//model2.addColumn("Total Vehicle Residency Time");
    	
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
        vehicleResidencyField.setText("");				

       
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
        
        //dialogueBox Text field
        dialogueBox = new JTextField(FIELD_WIDTH);
        
        boolean jobAccepted = false;// Change to true for accepted job
        
        //Show a dialogue box indicating whether the job was accepted or rejected
        //dialogueBox accept or reject text
        if(jobAccepted) {
        	dialogueBox.setText("Your job request has been accepted");
        }
        else {
        	dialogueBox.setText("Your job request has been declined");
        	
        }
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
	        vehicleResidencyField.setText("");
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
	        vccFrame.setTitle("Cloud Cruiser Admin");
	        vccFrame.setLocationRelativeTo(null);
		    vccFrame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		    vccFrame.setVisible(true);
		    vccLayout.show(vccCardsPanel, "home");
		}
	}
	
	//VCC Page Accept Button Listener//
		class VCCAcceptListener implements ActionListener {
			public void actionPerformed(ActionEvent event) {
				server.approveData(true);
				server.respondToClient();
				if(requestTypeQueue.peek().equalsIgnoreCase("job")) {
					saveJobToDatabase(jobIDQueue.peek());//newUpdate
					vcc.addJob(tempJobQueue.peek());
					model1.addRow(new Object[] {jobNameQueue.peek(), jobIDQueue.peek(), tempJobQueue.peek().getDuration(), tempJobQueue.peek().getCompletionTime()}); //new
					tempJobQueue.remove();
					jobIDQueue.remove();
					jobNameQueue.remove();
					jobDOBQueue.remove(); //new
					jobTimeStampQueue.remove(); //new
				}
				else if(requestTypeQueue.peek().equalsIgnoreCase("vehicle")) {
					saveVehicleToDatabase(vehicleIDQueue.peek()); //newUpdate
					vcc.addVehicle(tempVehicleQueue.peek());
					model2.addRow(new Object[] {vehicleNameQueue.peek(), vehicleIDQueue.peek(), tempVehicleQueue.peek().getResidencyTime()});//
					tempVehicleQueue.remove();//
					vehicleIDQueue.remove();//
					vehicleNameQueue.remove();//
					vehicleDOBQueue.remove(); //new
					vehicleTimeStampQueue.remove(); //new
				} //commenting this fixed the accept button?
				requestTypeQueue.remove();
				userInput.remove();
				updateUserInputText();
			}
		}
		
		/*//VCC Page Accept Button Listener//
		class VCCVehicleAcceptListener implements ActionListener {
			public void actionPerformed(ActionEvent event) {
				server.approveData(true);
				server.respondToClient();
				saveUserDataToFile(userInput.peek());
				if(requestTypeQueue.peek().equalsIgnoreCase("vehicle")) {
					vcc.addVehicle(tempVehicleQueue.peek());
					model2.addRow(new Object[] {vehicleNameQueue.peek(), vehicleIDQueue.peek(), tempVehicleQueue.peek().getResidencyTime()});//
					tempVehicleQueue.remove();//
					vehicleIDQueue.remove();//
					vehicleNameQueue.remove();//
				} //added new class "VCCVehicleAcceptListener" to handle model2
				requestTypeQueue.remove();
				userInput.remove();
				updateUserInputText();
			}
		}
		*/
		
	//VCC Page Reject Button Listener
		class VCCRejectListener implements ActionListener {
		    public void actionPerformed(ActionEvent event) {
		        server.approveData(false);
		        server.respondToClient();
				if(requestTypeQueue.peek().equalsIgnoreCase("job")) {
					displayRejectionMessage(jobIDQueue.peek());//newUpdate
			        tempJobQueue.remove(); //new
					jobIDQueue.remove(); //new
					jobNameQueue.remove(); //new
					jobDOBQueue.remove(); //new
					jobTimeStampQueue.remove(); //new
				}
				else if(requestTypeQueue.peek().equalsIgnoreCase("vehicle")) {
					displayRejectionMessage(vehicleIDQueue.peek());//newUpdate
					tempVehicleQueue.remove(); //new
					vehicleIDQueue.remove(); //new
					vehicleNameQueue.remove(); //new
					vehicleDOBQueue.remove(); //new
					vehicleTimeStampQueue.remove(); //new
				}
				requestTypeQueue.remove();
		        userInput.remove();
		        updateUserInputText(); //new
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
		vehicleOwnerButton.setBackground(Color.WHITE);
		vehicleOwnerButton.setForeground(Color.decode("#68AAC3"));
		
		jobRequesterButton = new JButton("Job Requester");
		jobRequesterButton.setBackground(Color.WHITE);
		jobRequesterButton.setForeground(Color.decode("#68AAC3"));
		
		vccButton = new JButton("Admin");
		vccButton.setBackground(Color.WHITE);
		vccButton.setForeground(Color.decode("#68AAC3"));
		
		vehicleOwnerBackButton = new JButton("Back");
		vehicleOwnerBackButton.setBackground(Color.WHITE);
		vehicleOwnerBackButton.setForeground(Color.decode("#68AAC3"));
		
		jobRequesterBackButton = new JButton("Back");
		jobRequesterBackButton.setBackground(Color.WHITE);
		jobRequesterBackButton.setForeground(Color.decode("#68AAC3"));
		
		calculateCompletionTimeButton = new JButton("Calculate Completion Time");
		calculateCompletionTimeButton.setBackground(Color.WHITE);
		calculateCompletionTimeButton.setForeground(Color.decode("#68AAC3"));
		
		vccJobCompletionTimeButton = new JButton("Calculate All Completion Times");
		vccJobCompletionTimeButton.setBackground(Color.WHITE);
		vccJobCompletionTimeButton.setForeground(Color.decode("#68AAC3"));
		
		vccJobCompletionTimeBackButton = new JButton("Back");
		vccJobCompletionTimeBackButton.setBackground(Color.WHITE);
		vccJobCompletionTimeBackButton.setForeground(Color.decode("#68AAC3"));
		
		vccViewRequestsButton = new JButton("View Requests");
		vccViewRequestsButton.setBackground(Color.WHITE);
		vccViewRequestsButton.setForeground(Color.decode("#68AAC3"));
		
		vccViewRequestsBackButton = new JButton("Back");
		vccViewRequestsBackButton.setBackground(Color.WHITE);
		vccViewRequestsBackButton.setForeground(Color.decode("#68AAC3"));
		
		
		//UPDATED- Creates accept/reject buttons within VCController
		vccAcceptButton = new JButton("Accept");
		vccAcceptButton.setBackground(Color.WHITE);
		vccAcceptButton.setForeground(Color.decode("#68AAC3"));
		
		vccRejectButton = new JButton("Reject");
		vccRejectButton.setBackground(Color.WHITE);
		vccRejectButton.setForeground(Color.decode("#68AAC3"));
		
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
		
		ActionListener vccJobCompletionTimeButtonListener = new VCCJobCompletionTimeListener();//
		vccJobCompletionTimeButton.addActionListener(vccJobCompletionTimeButtonListener);
		
		ActionListener vccJobCompletionTimeBackListener = new ReturnVCCHomeListener();//
		vccJobCompletionTimeBackButton.addActionListener(vccJobCompletionTimeBackListener);
		
		//ActionListener vccVehicleResidencyTimeButtonListener = new VCCVehicleResidencyTimeListener();//
		//vccVehicleResidencyTimeButton.addActionListener(vccVehicleResidencyTimeButtonListener);//
		
		//ActionListener vccVehicleResidencyTimeBackListener = new ReturnVCCHomeListener();//
		//vccVehicleResidencyTimeBackButton.addActionListener(vccVehicleResidencyTimeBackListener);

		
		ActionListener vccViewRequestsButtonListener = new VCCViewRequestsListener();
		vccViewRequestsButton.addActionListener(vccViewRequestsButtonListener);
		
		ActionListener vccViewRequestsBackListener = new ReturnVCCHomeListener();
		vccViewRequestsBackButton.addActionListener(vccViewRequestsBackListener);
		
		
		//UPDATED- Adds action listeners to accept/reject buttons
		ActionListener vccAcceptListener = new VCCAcceptListener();
		vccAcceptButton.addActionListener(vccAcceptListener);

		ActionListener vccRejectListener = new VCCRejectListener();
		vccRejectButton.addActionListener(vccRejectListener);
		
		
		//Vehicle Owner Submit Button
        vehicleOwnerSubmitButton = new JButton("Submit");
        vehicleOwnerSubmitButton.setBackground(Color.WHITE);
        vehicleOwnerSubmitButton.setForeground(Color.decode("#68AAC3"));
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
        jobRequesterSubmitButton.setBackground(Color.WHITE);
        jobRequesterSubmitButton.setForeground(Color.decode("#68AAC3"));
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
		JPanel jobRequesterPanel = new JPanel();
		JPanel vehicleOwnerButtonPanel = new JPanel();
		JPanel jobRequesterButtonPanel = new JPanel();
	    JPanel vccHomePanel = new JPanel();
	    JPanel vccCompletionTimePanel = new JPanel();
	    JPanel vccViewRequestsPanel = new JPanel();
	    JPanel vccRequestsButtonPanel = new JPanel();
	    vehicleOwnerButton.setPreferredSize(new Dimension(200, 30));
	    jobRequesterButton.setPreferredSize(new Dimension(200, 30));
	    vccButton.setPreferredSize(new Dimension(200, 30));
	    vehicleOwnerButton.setFont(new Font("SansSerif", Font.BOLD, 16));
	    jobRequesterButton.setFont(new Font("SansSerif", Font.BOLD, 16));
	    vccButton.setFont(new Font("SansSerif", Font.BOLD, 16));
		
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
		homePanel.setBackground(Color.decode("#68AAC3"));
		vehicleOwnerPanel.setBackground(Color.decode("#68AAC3"));
		jobRequesterPanel.setBackground(Color.decode("#68AAC3"));
		vccHomePanel.setBackground(Color.decode("#68AAC3"));
		vccCompletionTimePanel.setBackground(Color.decode("#68AAC3"));
		vccViewRequestsPanel.setBackground(Color.decode("#68AAC3"));
		
		//Vehicle Owner Panels
		vehicleOwnerPanel.setLayout(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.anchor = GridBagConstraints.LINE_START;
		gbc.insets = new Insets(10, -200, 10, 10);
		gbc.ipadx=100;

		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.gridwidth = 2;
		gbc.insets = new Insets(20, 10, 0, 10); 
		gbc.anchor = GridBagConstraints.CENTER;
		vehicleOwnerPanel.add(vehicleOwnerDescLabel, gbc);

		gbc.gridx = 0;
		gbc.gridy = 1;
		gbc.gridwidth=1;
		vehicleOwnerPanel.add(vehicleOwnerNameLabel, gbc);
		gbc.gridx = 1;
		vehicleOwnerPanel.add(vehicleOwnerNameField, gbc);

		gbc.gridx = 0;
		gbc.gridy = 2;
		vehicleOwnerPanel.add(vehicleOwnerDOBLabel, gbc);
		gbc.gridx = 1;
		vehicleOwnerPanel.add(vehicleOwnerDOBField, gbc);

		gbc.gridx = 0;
		gbc.gridy = 3;
		vehicleOwnerPanel.add(vehicleOwnerIDLabel, gbc);
		gbc.gridx = 1;
		vehicleOwnerPanel.add(vehicleOwnerIDField, gbc);

		gbc.gridx = 0;
		gbc.gridy = 4;
		vehicleOwnerPanel.add(vehicleMakeLabel, gbc);
		gbc.gridx = 1;
		vehicleOwnerPanel.add(vehicleMakeField, gbc);

		gbc.gridx = 0;
		gbc.gridy = 5;
		vehicleOwnerPanel.add(vehicleModelLabel, gbc);
		gbc.gridx = 1;
		vehicleOwnerPanel.add(vehicleModelField, gbc);

		gbc.gridx = 0;
		gbc.gridy = 6;
		vehicleOwnerPanel.add(vehicleYearLabel, gbc);
		gbc.gridx = 1;
		vehicleOwnerPanel.add(vehicleYearField, gbc);

		gbc.gridx = 0;
		gbc.gridy = 7;
		vehicleOwnerPanel.add(vehicleColorLabel, gbc);
		gbc.gridx = 1;
		vehicleOwnerPanel.add(vehicleColorField, gbc);

		gbc.gridx = 0;
		gbc.gridy = 8;
		vehicleOwnerPanel.add(vehicleLicenseLabel, gbc);
		gbc.gridx = 1;
		vehicleOwnerPanel.add(vehicleLicenseField, gbc);

		gbc.gridx = 0;
		gbc.gridy = 9;
		vehicleOwnerPanel.add(vehicleResidencyLabel, gbc);
		gbc.gridx = 1;
		vehicleOwnerPanel.add(vehicleResidencyField, gbc);

		gbc.gridx = 0;
		gbc.gridy = 10;
		gbc.gridwidth = 2;
		gbc.anchor = GridBagConstraints.CENTER;
		gbc.insets = new Insets(10, 0, 10, 0); 
		vehicleOwnerPanel.add(vehicleOwnerButtonPanel, gbc);

		vehicleOwnerButtonPanel.add(vehicleOwnerBackButton);
		vehicleOwnerButtonPanel.add(vehicleOwnerSubmitButton);
		vehicleOwnerButtonPanel.setBackground(Color.decode("#68AAC3"));
		
		//Job Requester Panels
		jobRequesterPanel.setLayout(new GridBagLayout());
		GridBagConstraints jrp = new GridBagConstraints();
		jrp.anchor = GridBagConstraints.LINE_START;
		jrp.insets = new Insets(10, -200, 10, 10);
		jrp.ipadx=100;

		jrp.gridx = 0;
		jrp.gridy = 0;
		jrp.gridwidth = 2;
		jrp.insets = new Insets(20, 10, 0, 10); 
		jrp.anchor = GridBagConstraints.CENTER;
		jobRequesterPanel.add(jobRequesterDescLabel, jrp);

		jrp.gridx = 0;
		jrp.gridy = 1;
		jrp.gridwidth=1;
		jobRequesterPanel.add(jobRequesterNameLabel, jrp);
		jrp.gridx = 1;
		jobRequesterPanel.add(jobRequesterNameField, jrp);

		jrp.gridx = 0;
		jrp.gridy = 2;
		jobRequesterPanel.add(jobRequesterDOBLabel, jrp);
		jrp.gridx = 1;
		jobRequesterPanel.add(jobRequesterDOBField, jrp);

		jrp.gridx = 0;
		jrp.gridy = 3;
		jobRequesterPanel.add(jobRequesterIDLabel, jrp);
		jrp.gridx = 1;
		jobRequesterPanel.add(jobRequesterIDField, jrp);

		jrp.gridx = 0;
		jrp.gridy = 4;
		jobRequesterPanel.add(jobDurationLabel, jrp);
		jrp.gridx = 1;
		jobRequesterPanel.add(jobDurationField, jrp);

		jrp.gridx = 0;
		jrp.gridy = 5;
		jobRequesterPanel.add(jobDeadlineLabel, jrp);
		jrp.gridx = 1;
		jobRequesterPanel.add(jobDeadlineField, jrp);

		jrp.gridx = 0;
		jrp.gridy = 6;
		jobRequesterPanel.add(jobTypeLabel, jrp);
		jrp.gridx = 1;
		jobRequesterPanel.add(jobTypeField, jrp);

		jrp.gridx = 0;
		jrp.gridy = 7;
		jobRequesterPanel.add(jobIntensityLabel, jrp);
		jrp.gridx = 1;
		jobRequesterPanel.add(jobIntensityField, jrp);

		jrp.gridx = 0;
		jrp.gridy = 10;
		jrp.gridwidth = 2;
		jrp.anchor = GridBagConstraints.CENTER;
		jrp.insets = new Insets(10, 0, 10, 0); 
		jobRequesterPanel.add(jobRequesterButtonPanel, jrp);

		jobRequesterButtonPanel.add(jobRequesterBackButton);
		jobRequesterButtonPanel.add(jobRequesterSubmitButton);
		jobRequesterButtonPanel.add(calculateCompletionTimeButton);
		jobRequesterButtonPanel.setBackground(Color.decode("#68AAC3"));
		
		//VCC Panels
		vccHomePanel.add(vccJobCompletionTimeButton);//
		//vccHomePanel.add(vccVehicleResidencyTimeButton);//

		vccHomePanel.add(vccViewRequestsButton);
		vccCompletionTimePanel.add(jobCompletionTable);//
		vccCompletionTimePanel.add(vehicleResidencyTable);//
		vccHomePanel.setBackground(Color.decode("#68AAC3"));

		vccCompletionTimePanel.add(vccJobCompletionTimeBackButton);//
		//vccCompletionTimePanel.add(vccVehicleResidencyTimeBackButton);//

		
		//Added separate panel to view user requests
		vccViewRequestsPanel.add(userInputTextArea);
		vccRequestsButtonPanel.add(vccAcceptButton); 
		vccRequestsButtonPanel.add(vccRejectButton); 
		vccRequestsButtonPanel.add(vccViewRequestsBackButton);
		vccViewRequestsPanel.add(vccRequestsButtonPanel);
		vccRequestsButtonPanel.setBackground(Color.decode("#68AAC3"));
		vccViewRequestsPanel.setBackground(Color.decode("#68AAC3"));
	}
}
