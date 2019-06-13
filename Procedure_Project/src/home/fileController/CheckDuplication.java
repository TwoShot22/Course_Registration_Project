package home.fileController;

import java.io.File;
import java.io.FileWriter;
import java.util.List;
import java.util.Scanner;
import java.util.Vector;

public class CheckDuplication {

	public static boolean checkOverLapData(String str1, String str2) {
		return str1.equals(str2);
	}
	
	@SuppressWarnings("resource")
	public static void addLectureToFile(Vector<String> selectedItem, String targetFileName) {
		try {
			Vector<String> beforeContents = new Vector<String>();
			File targetFile = new File(targetFileName);
			String compareItem = "";
			
			for(int i=0;i<selectedItem.size();i++) {
				if(i==4) {
					compareItem+=(selectedItem.get(i));
				} else {
					compareItem+=(selectedItem.get(i) + " ");					
				}
			}
			
			//Read All Row
			Scanner scanner = new Scanner(targetFile);
			while(scanner.hasNextLine()) {
				beforeContents.add(scanner.nextLine());
			}
			
			//Clear File & Write Content Except Target Row
			FileWriter fileWriter = new FileWriter(targetFile, false);
			for(String row : beforeContents) {
				if(!row.equals(compareItem)) {
					fileWriter.write(row+"\r\n");
				}
			}
			fileWriter.write(compareItem);
			fileWriter.flush();
			
		} catch(Exception e) {
			System.out.println("File Not Founded");
		}
	}
}
