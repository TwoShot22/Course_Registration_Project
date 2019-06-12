package home.fileController;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import home.model.LectureModel;

public class FileTool {

	public static void writeOnTxtFile(LectureModel lectureModel, String fileName) {
		try {
			@SuppressWarnings("resource")
			FileWriter fileWriter = new FileWriter(new File(fileName));
			//fileWriter.write(lectureModel);
			fileWriter.flush();
		} catch (IOException e) {
			System.out.println("File Not Found");
		}
	}
	
}
