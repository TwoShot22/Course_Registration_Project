package home.fileController;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Vector;

public class FileTool {

	public static void writeOnTxtFile(Vector<String> selectedItem, String fileName) {
		try {
			@SuppressWarnings("resource")
			FileWriter fileWriter = new FileWriter(fileName, true);
			
			for(int i=0;i<selectedItem.size();i++) {
				if(i%5==0) {
					fileWriter.write("\r\n" +selectedItem.get(i)+" ");
				} else if(i%5==4){
					fileWriter.write(selectedItem.get(i));										
				} else {
					fileWriter.write(selectedItem.get(i)+" ");
				}
			}
			
			fileWriter.close();
		} catch (IOException e) {
			System.out.println("File Not Found");
		}
	}
	
}
