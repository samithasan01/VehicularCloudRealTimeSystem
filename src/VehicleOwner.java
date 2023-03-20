
public class VehicleOwner extends User{
	private int ownerID;
	
	public VehicleOwner(String name, String dob, int ownerID) {
		super(name, dob);
		this.ownerID = ownerID;
	}
	
	public int getOwnerID() {
		return ownerID;
	}
	
	public void setOwnerID(int id) {
		this.ownerID = id;
	}

}
