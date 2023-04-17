
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.LinkedList;
import java.util.Queue;

public class VCController {
	private Queue<Job> jobList;
	private Queue<Vehicle> vehicleList;
	private PrintStream completedJobsOutput;
	
	public VCController() throws FileNotFoundException {
		jobList = new LinkedList<Job>();
		vehicleList = new LinkedList<Vehicle>();
		completedJobsOutput = new PrintStream(new File("completedJobs.txt"));
	}
	
	public int calculateCompletionTime(){
		int completionTime = 0;
		for(Job job : jobList) {
			completionTime += job.getDuration();
		}
		return completionTime;
	}
	
	public void chooseRedundancy(Job job) {
		int redundancy;
		if(job.getIntensity().equalsIgnoreCase("hard")) {
			redundancy = 5;
		}
		else if(job.getIntensity().equalsIgnoreCase("medium")) {
			redundancy = 3;
		}
		else {
			redundancy = 2;
		}
		job.setLevelOfRedundancy(redundancy);
	}
	
	public void addJob(Job job) {
		jobList.add(job);
		int completionTime = calculateCompletionTime();
		job.setCompletionTime(completionTime);
	}
	
	public void addVehicle(Vehicle vehicle) {
		vehicleList.add(vehicle);
	}
	
	public void transferJob(Job job){
		if (job.getCompleted()) {
			String completedJob = jobList.peek().toString();
			completedJobsOutput.println(completedJob);
			completedJobsOutput.println();
			eraseJob();
		}
	}
	
	public void eraseJob(){
		jobList.remove();
	}
	
	public void assignJob(Job job){
		
	}
	
public int calculateResidencyTime(){
	int residencyTime = 0;
	for(Vehicle vehicle: vehicleList) {
		residencyTime += vehicle.getResidencyTime();
	}
	return residencyTime;
}
}
