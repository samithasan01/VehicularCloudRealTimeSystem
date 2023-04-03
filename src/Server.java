import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Server implements Runnable {

	static ServerSocket serverSocket;
	static Socket socket;
	static DataInputStream inputStream;
	static DataOutputStream outputStream;
	private String userData;
	private boolean approved;
	private String messageIn = "";
	private String messageOut = "";
	
	private void setUserData(String input) {
		userData = input;
	}
	
	public String getUserData() {
		return userData;
	}
	
	public Boolean approveData(Boolean approved) {
		return this.approved = approved;
	}
	
	public void run() {
		try {
			System.out.println("----------$$$ This is server side $$$--------");
			System.out.println("wating for client to connect...");
			
			// creating the server
			serverSocket = new ServerSocket(9806);
			
			//Loop will listen for any new client requests
			while(true) {
				// sever accepts connection request from client
				socket = serverSocket.accept();
				
				// server reads data from client
				inputStream = new DataInputStream(socket.getInputStream());
				
				// server sends a message to client
				outputStream = new DataOutputStream(socket.getOutputStream());

				// extract the message from client
				messageIn = inputStream.readUTF();
				// server prints the message received from client to console
				setUserData(messageIn);
				
				// server sends the message to client
				messageOut = "Your request has been received and is currently being processed...";
				outputStream.writeUTF(messageOut);
			}			
			
		} catch (Exception e) {

			e.printStackTrace();
		}
	}
	
	public void receiveClientMessage() {
		try {
			
			// server reads data from client
			inputStream = new DataInputStream(socket.getInputStream());

			// server sends a message to client
			outputStream = new DataOutputStream(socket.getOutputStream());

			// extract the message from client
			messageIn = inputStream.readUTF();
			// server prints the message received from client to console
			setUserData(messageIn);
			
		} catch (Exception e) {

			e.printStackTrace();
		}
	}
	
	public void respondToClient() {
		try {
			// ********************************************************
			// server reads a message from keyboard
			if(approved == true) {
				messageOut = "approved";
			}
			else{
				messageOut = "denied";
			}
			// server sends the message to client
			outputStream.writeUTF(messageOut);
		}
		catch (Exception e) {

			e.printStackTrace();
		}
	}

}
