
public class Job {
	
	private double duration;
	private String deadline;
	private String type;
	private String intensity;
	private boolean inprogress;
	private boolean completed;
	private int completionTime;
	private int levelOfRedundancy;
	
	//Add an id field to uniquely identify each job
	private int id;
	private static int nextId = 1;
	
	public Job(double duration, String deadline, String type, String intensity, boolean inprogress, boolean completed, int completionTime) {
		this.duration = duration;
		this.deadline = deadline;
		this.type = type;
		this.intensity = intensity;
		this.inprogress = inprogress;
		this.completed = completed;
		//Default redundancy until VCController calculates and assigns one
		levelOfRedundancy = 1;
		
		//Assign the next available id to the job
		id = nextId;
		nextId++;
	}
	
	public double getDuration() {
		return duration;
	}

	public String getDeadline() {
		return deadline;
	}
	
	public String getType() {
		return type;
	}
	
	public String getIntensity() {
		return intensity;
	}

	public boolean getInprogress() {
		return inprogress;
	}
	
	public boolean getCompleted() {
		return completed;
	}
	
	public int getCompletionTime() {
		return completionTime;
	}
	
	public int getLevelOfRedundancy() {
		return levelOfRedundancy;
	}
	
	public void setDuration(double duration) {
		this.duration = duration;
	}
	
	public void setDeadline(String deadline) {
		this.deadline = deadline;
	}
	
	public void setType(String type) {
		this.type = type;
	}
	
	public void setIntensity(String intensity) {
		this.intensity = intensity;
	}
	
	public void setInprogress(boolean inprogress) {
		this.inprogress = inprogress;
	}
	
	public void setCompleted(boolean completed) {
		this.completed = completed;
	}
	
	public void setCompletionTime(int completionTime) {
		this.completionTime = completionTime;
	}
	
	public void setLevelOfRedundancy(int redundancy) {
		this.levelOfRedundancy = redundancy;
	}
	
	//Add a getter method for the id field
	public int getId() {
		return id;
	}
	
	//Override the toString() method to include the job id
	@Override
	public String toString() {
		return "Job [id=" + id + ", duration=" + duration + ", deadline=" + deadline + ", type=" + type + ", intensity="
				+ intensity + ", inprogress=" + inprogress + ", completed=" + completed + ", completionTime="
				+ completionTime + ", levelOfRedundancy=" + levelOfRedundancy + "]";
	}
}
