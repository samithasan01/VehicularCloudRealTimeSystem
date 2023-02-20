
import java.io.FileNotFoundException;

import javax.swing.JFrame;

public class VehicularCloudViewer {
	public static void main(String[] args) throws FileNotFoundException {
		JFrame frame = new VehicularCloudFrame();
		
	    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    frame.setVisible(true);
	}
}

