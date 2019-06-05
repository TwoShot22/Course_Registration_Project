package home.model;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class LectureModel {
	private final IntegerProperty number;
	private final StringProperty name;
	private final StringProperty professor;
	private final IntegerProperty credit;
	private final StringProperty time;
	
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
		this.number.set(number);
	}
	
	public IntegerProperty numberProperty() {
		return number;
	}
	
	public String getName() {
		return name.get();
	}
	
	public void setName(String name) {
		this.name.set(name);
	}
	
	public StringProperty nameProperty() {
		return name;
	}
	
	public String getProfessor() {
		return professor.get();
	}
	
	public void setProfessor(String professor) {
		this.professor.set(professor);
	}
	
	public StringProperty professorProperty() {
		return professor;
	}
	
	public int getCredit() {
		return credit.get();
	}
	
	public void setCredit(int credit) {
		this.credit.set(credit);
	}
	
	public IntegerProperty creditProperty() {
		return credit;
	}
	
	public String getTime() {
		return time.get();
	}
	
	public void setTime(String time) {
		this.time.set(time);
	}
	
	public StringProperty timeProperty() {
		return time;
	}
}
