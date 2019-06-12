package home.fileController;

import java.io.File;
import java.io.FileWriter;
import java.util.Scanner;
import java.util.Vector;

public class CheckDuplication {

	public static boolean checkOverLapData(String str1, String str2) {
		return str1.equals(str2);
	}
	
	@SuppressWarnings("resource")
	public static void deleteRow(String identifier, String targetFileName) {
		try {
			Vector<String> beforeContents = new Vector<String>();
			File targetFile = new File(targetFileName);
			
			//Read All Row
			Scanner scanner = new Scanner(targetFile);
			while(scanner.hasNextLine()) {
				beforeContents.add(scanner.nextLine());
			}
			
			//Clear File & Write Content Except Target Row
			FileWriter fileWriter = new FileWriter(targetFile, false);
			for(String row : beforeContents) {
				if(!row.split(" ")[0].equals(identifier)) {
					fileWriter.write(row);
				}
			}
			fileWriter.flush();
			
		} catch(Exception e) {
			System.out.println("File Not Founded");
		}
	}
}
