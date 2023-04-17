import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

public class Database {

	static Connection connection = null;
	//this part is the address and name of your database server: jdbc:mysql://localhost:3306/VCRTS
	//this part of the string is for time adjustment: ?useTimezone=true&serverTimezone=UTC
	static String url = "jdbc:mysql://localhost:3306/VCRTS?useTimezone=true&serverTimezone=UTC";
	static String username = "root";
	static String password = "password";
	
	public void connectDatabase() {
		try {
			//declares a connection to your database
			connection = DriverManager.getConnection(url, username, password);
			
		} catch (SQLException e) {
			e.getMessage();

		}
	}
	
	public void addVehicle(Vehicle vehicle, String vehicleOwnerName, String vehicleOwnerID, String vehicleOwnerDOB, String timestamp) {
		try {
			//creates an insert query
			String sql = "INSERT INTO vehicleTable " + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
			//establishes the connection session
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setString(1, timestamp);
			statement.setString(2, vehicleOwnerName);
			statement.setString(3, vehicleOwnerDOB);
			statement.setString(4, vehicleOwnerID);
			statement.setString(5, vehicle.getMake());
			statement.setString(6, vehicle.getModel());
			statement.setInt(7, vehicle.getYear());
			statement.setString(8, vehicle.getColor());
			statement.setString(9, vehicle.getLicensePlate());
			statement.setDouble(10, vehicle.getResidencyTime());
			//executes the query 
			System.out.println("Executing: "+ statement.toString());
			int row = statement.executeUpdate();
			//the return value is the indication of success or failure of the query execution
			if (row > 0) {
				System.out.println("Data was inserted!" + row);
			}
			
		} catch (SQLException e) {
			e.getMessage();
			e.printStackTrace();

		}
	}
	
	public void addJob(Job job, String jobRequesterName, String jobRequesterID, String jobRequesterDOB, String timestamp) {
		try {
			//creates an insert query
			String sql = "INSERT INTO jobTable " + "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
			//establishes the connection session
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setString(1, timestamp);
			statement.setString(2, jobRequesterName);
			statement.setString(3, jobRequesterDOB);
			statement.setString(4, jobRequesterID);
			statement.setDouble(5, job.getDuration());
			statement.setString(6, job.getDeadline());
			statement.setString(7, job.getType());
			statement.setString(8, job.getIntensity());
			//executes the query 
			int row = statement.executeUpdate();
			//the return value is the indication of success or failure of the query execution
			if (row > 0) {
				System.out.println("Data was inserted!" + row);
			}
			
		} catch (SQLException e) {
			e.getMessage();

		}
	}
}
