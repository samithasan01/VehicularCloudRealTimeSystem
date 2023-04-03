
import java.io.FileNotFoundException;
import java.io.IOException;

import javax.swing.JFrame;

public class VehicularCloudViewer {
	public static void main(String[] args) throws IOException, FileNotFoundException { //added IOException because it started giving me a new error
		JFrame frame = new VehicularCloudFrame();
		
	    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    frame.setVisible(true);
	}
}

