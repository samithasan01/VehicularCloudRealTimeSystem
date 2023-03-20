public class Vehicle {
	
	private String make;
	private String model;
	private int year;
	private String color;
	private String licensePlate;
	private double residencyTime;
	
	public Vehicle(String make, String model, int year, String color, String licensePlate, double residencyTime) {
		this.make = make;
		this.model = model;
		this.year = year;
		this.color = color;
		this.licensePlate = licensePlate;
		this.residencyTime = residencyTime;
	}
	
	public String getMake() {
		return make;
	}
	
	public void setMake(String make) {
		this.make = make;
	}
	
	public String getModel() {
		return model;
	}
	
	public void setModel(String model) {
		this.model = model;
	}
	
	public int getYear() {
		return year;
	}
	
	public void setYear(int year) {
		this.year = year;
	}
	
	public String getColor() {
		return color;
	}
	
	public void setColor(String color) {
		this.color = color;
	}
	
	public String getLicensePlate() {
		return licensePlate;
	}
	
	public void setLicensePlate(String licensePlate) {
		this.licensePlate = licensePlate;
	}
	
	public double getResidencyTime() {
		return residencyTime;
	}
	
	public void setResidencyTime(double residencyTime) {
		this.residencyTime = residencyTime;
	}
	
	public void arrive() {
		System.out.println("Vehicle arrived.");
	}
	
	public void depart() {
		System.out.println("Vehicle departed.");
	}
}
