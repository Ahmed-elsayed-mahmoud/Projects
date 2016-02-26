package eg.edu.alexu.csd.oop.calculator;

import java.io.File;

import javax.swing.JFileChooser;
import javax.swing.JFrame;

public class SaveDialog extends JFrame {

	private String filePath = null;

	public SaveDialog() {
		JFileChooser fileChooser = new JFileChooser();
		fileChooser
				.setCurrentDirectory(new File(System.getProperty("user.dir")));
		if (fileChooser.showSaveDialog(this) == JFileChooser.APPROVE_OPTION) {
			File selectedFile = fileChooser.getSelectedFile();
			filePath = selectedFile.getAbsolutePath();
			History.path = filePath + ".txt";
		}

	}

	public String getPath() {
		return this.filePath;
	}
}
