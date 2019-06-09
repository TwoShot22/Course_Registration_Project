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
			
	public LectureModel(IntegerProperty number, StringProperty name, StringProperty professor, IntegerProperty credit, StringProperty time) {
		this.number = number;
		this.name = name;
		this.professor = professor;
		this.credit = credit;
		this.time = time;
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
}