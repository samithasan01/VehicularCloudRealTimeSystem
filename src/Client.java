import java.net.ServerSocket;
import java.net.Socket;
import java.io.*;

public class Client implements Runnable{
	
	static ServerSocket serverSocket;
	static Socket socket;
	static DataInputStream inputStream;
	static DataOutputStream outputStream;
	private String outputData;
	private String processingMessage;
	private String messageIn = "";
	private String messageOut = "";
	private String messageUpdate = "";
	
	public void setOutput(String userInput) {
		outputData = userInput;
	}
	
	private String getOutput() {
		return outputData;
	}
	
	public String getProcessingMessage() {
		return processingMessage;
	}
	
	public void run() {
		try {
			
			System.out.println("----------*** This is client side ***--------");
			System.out.println("client started!");
			//connect the client socket to server
			Socket socket = new Socket("localhost", 9806);
			
			//client reads a message from Server
			inputStream = new DataInputStream(socket.getInputStream());
			outputStream = new DataOutputStream(socket.getOutputStream());
			
			
			// client reads a message from keyboard
			messageOut = getOutput();
			System.out.println(messageOut);
			// server sends the message to client
			outputStream.writeUTF(messageOut);
			
			messageIn = inputStream.readUTF();
			processingMessage = messageIn;
			// client prints the message received from server to console
			System.out.println("message received from server: " + "\"" + messageIn + "\"");
			
		} 
		catch (Exception e) {
			
			e.printStackTrace();

		}
	}
	
	public String checkForResponse() {
		try {
			messageUpdate = inputStream.readUTF();
		} 
		catch (IOException e) {
			e.printStackTrace();
		}
		return messageUpdate;
	}
}
