
public class JobRequester extends User{
	private int clientID;
	
	public JobRequester(String name, String dob, int clientID) {
		super (name, dob);
		this.clientID = clientID;
	}
	
	public int getClientID() {
		return clientID;
	}
	
	public void setClientID(int id) {
		this.clientID = id;
	}
}

