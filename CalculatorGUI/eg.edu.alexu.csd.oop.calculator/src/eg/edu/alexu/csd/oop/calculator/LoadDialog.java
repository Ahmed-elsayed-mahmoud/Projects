package eg.edu.alexu.csd.oop.calculator;

import java.io.File;

import javax.swing.JFileChooser;
import javax.swing.JFrame;

public class LoadDialog extends JFrame {

	private String filePath = null;

	public LoadDialog() {
		JFileChooser fileChooser = new JFileChooser();
		fileChooser
				.setCurrentDirectory(new File(System.getProperty("user.dir")));
		if (fileChooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
			File selectedFile = fileChooser.getSelectedFile();
			filePath = selectedFile.getAbsolutePath();
			History.path = filePath;
		}

	}

	public String getPath() {
		return this.filePath;
	}
}
