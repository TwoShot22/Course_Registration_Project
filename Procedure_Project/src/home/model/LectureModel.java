package home.model;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class LectureModel {
	private SimpleIntegerProperty number;
	private SimpleStringProperty name;
	private SimpleStringProperty professor;
	private SimpleIntegerProperty credit;
	private SimpleStringProperty time;
	
	public LectureModel(int number, String name, String professor, int credit, String time) {
		this.number = new SimpleIntegerProperty(number);
		this.name = new SimpleStringProperty(name);
		this.professor = new SimpleStringProperty(professor);
		this.credit = new SimpleIntegerProperty(credit);
		this.time = new SimpleStringProperty(time);
	}
	
	public int getNumber() {
		return number.get();
	}
	
	public void setNumber(int number) {
		this.number = new SimpleIntegerProperty(number);
	}
	
	public String getName() {
		return name.get();
	}
	
	public void setName(String name) {
		this.name = new SimpleStringProperty(name);
	}
	
	public String getProfessor() {
		return professor.get();
	}
	
	public void setProfessor(String professor) {
		this.professor = new SimpleStringProperty(professor);
	}
	
	public int getCredit() {
		return credit.get();
	}
	
	public void setCredit(int credit) {
		this.credit = new SimpleIntegerProperty(credit);
	}
	
	public String getTime() {
		return time.get();
	}
	
	public void setTime(String time) {
		this.time = new SimpleStringProperty(time);
	}
}
