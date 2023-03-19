
public class Job {
	
	double duration;
	String deadline;
	String type;
	String intensity;
	boolean inProgress;
	boolean completed;
	int completionTime;
	
	
	public Job(double duration, String deadline, String type, String intensity, boolean inProgress, boolean completed, int completionTime) {
		
		transfer();				//needs to be defined
		erase();				//needs to be defined
		trackCompletion();		//needs to be defined
	}
}
