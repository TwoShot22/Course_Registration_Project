package home.model;

import java.util.Scanner;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.StringProperty;
import javafx.scene.control.Button;

public class LectureModel {
	private IntegerProperty number;
	private StringProperty name;
	private StringProperty professor;
	private IntegerProperty credit;
	private StringProperty time;
	
	private int tempNumber;
	private String tempName;
	private String tempProfessor;
	private int tempCredit;
	private String tempTime;
			
	public LectureModel(IntegerProperty number, StringProperty name, StringProperty professor, IntegerProperty credit, StringProperty time) {
		this.number = number;
		this.name = name;
		this.professor = professor;
		this.credit = credit;
		this.time = time;
	}
	
	public void read(Scanner scanner) {
		this.tempNumber = scanner.nextInt();
		this.tempName = scanner.next();
		this.tempProfessor = scanner.next();
		this.tempCredit = scanner.nextInt();
		this.tempTime = scanner.next();
		
		this.setNumber(this.tempNumber);
		this.setName(this.tempName);
		this.setProfessor(this.tempProfessor);
		this.setCredit(this.tempCredit);
		this.setTime(this.tempTime);
	}
	
	public IntegerProperty numberProperty() {
		return number;
	}
	
	public StringProperty nameProperty() {
		return name;
	}
	
	public StringProperty professorProperty() {
		return professor;
	}
	
	public IntegerProperty creditProperty() {
		return credit;
	}
	
	public StringProperty timeProperty() {
		return time;
	}
	
	public int getNumber() {
		return number.get();
	}
	
	public void setNumber(int number) {
		this.number.set(number);
	}
	
	public String getName() {
		return name.get();
	}
	
	public void setName(String name) {
		this.name.set(name);
	}
	
	public String getProfessor() {
		return professor.get();
	}
	
	public void setProfessor(String professor) {
		this.professor.set(professor);
	}
	
	public int getCredit() {
		return credit.get();
	}
	
	public void setCredit(int credit) {
		this.credit.set(credit);
	}
	
	public String getTime() {
		return time.get();
	}
	
	public void setTime(String time) {
		this.time.set(time);
	}
}