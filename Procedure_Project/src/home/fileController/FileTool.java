package home.fileController;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class FileTool {

	public static void writeOnTxtFile(String content, String fileName) {
		try {
			@SuppressWarnings("resource")
			FileWriter fileWriter = new FileWriter(new File(fileName));
			fileWriter.write(content);
			fileWriter.flush();
		} catch (IOException e) {System.out.println("File Not Found");}
	}
	
}
