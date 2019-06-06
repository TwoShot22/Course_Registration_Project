package home.model;

import java.util.Scanner;

public class DirectoryModel {
	private int number;
	private String name;
	private String hyperLink;
	
	public void read(Scanner scanner) {
		this.number = scanner.nextInt();
		this.name = scanner.next();
		this.hyperLink = scanner.next();
	}
	
	public int getNumber() {
		return this.number;
	}
	
	public String getName() {
		return this.name;
	}
	
	public String getHyperLink() {
		return this.hyperLink;
	}
}
