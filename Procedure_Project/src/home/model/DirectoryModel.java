package home.model;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class DirectoryModel {
	private SimpleIntegerProperty number;
	private SimpleStringProperty name;
	private SimpleStringProperty hyperLink;
	
	public DirectoryModel(int number, String name, String hyperLink) {
		this.number = new SimpleIntegerProperty(number);
		this.name = new SimpleStringProperty(name);
		this.hyperLink = new SimpleStringProperty(hyperLink);
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
	
	public String getHyperLink() {
		return hyperLink.get();
	}
	
	public void setProfessor(String hyperLink) {
		this.hyperLink = new SimpleStringProperty(hyperLink);
	}
}
