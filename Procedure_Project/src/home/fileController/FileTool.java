package home.fileController;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Vector;

import home.model.LectureModel;

public class FileTool {

	public static void writeOnTxtFile(Vector<LectureModel> lectureModels, String fileName) {
		try {
			@SuppressWarnings("resource")
			FileWriter fileWriter = new FileWriter(new File(fileName));
			for(LectureModel lectureModel : lectureModels) {
				fileWriter.write(lectureModel.getNumber()+" "+lectureModel.getName()+" "+lectureModel.getProfessor()+" "+lectureModel.getCredit()+" "+lectureModel.getTime());				
			}
			fileWriter.close();
		} catch (IOException e) {
			System.out.println("File Not Found");
		}
	}
	
}
