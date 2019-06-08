package home.model;

import java.util.Scanner;

import javafx.scene.control.Button;

public class LectureModel {
	private int number;
	private String name;
	private String professor;
	public int credit;
	public String time;
	
	public Button button;
		
	public LectureModel(int number, String name, String professor, int credit, String time, Button button) {
		this.number = number;
		this.name = name;
		this.professor = professor;
		this.credit = credit;
		this.time = time;
		this.button = button;
		
		this.button.setOnAction(e->{
			System.out.println("GET Success");
		});
	}

	public void read(Scanner scanner) {
		this.number = scanner.nextInt();
		this.name = scanner.next();
		this.professor = scanner.next();
		this.credit = scanner.nextInt();
		this.time = scanner.next();
	}
	
	public int getNumber() {
		return number;
	}
	
	public void setNumber(int number) {
		this.number = number;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getProfessor() {
		return professor;
	}
	
	public void setProfessor(String professor) {
		this.professor = professor;
	}
	
	public int getCredit() {
		return credit;
	}
	
	public void setCredit(int credit) {
		this.credit = credit;
	}
	
	public String getTime() {
		return time;
	}
	
	public void setTime(String time) {
		this.time = time;
	}
}