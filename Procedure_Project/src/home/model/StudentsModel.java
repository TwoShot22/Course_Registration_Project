package home.model;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class StudentsModel {
	private SimpleStringProperty userID;
	private SimpleStringProperty userPassword;
	
	public StudentsModel(String userID, String userPassword) {
		this.userID = new SimpleStringProperty(userID);
		this.userPassword = new SimpleStringProperty(userPassword);
	}
	
	public String getUserID() {
		return userID.get();
	}
	
	public void setUserID(String userID) {
		this.userID = new SimpleStringProperty(userID);
	}
	
	public StringProperty textProperty() {return userID;}
	
	public String getUserPassword() {
		return userPassword.get();
	}
	
	public void setUserPassword(String userPassword) {
		this.userPassword = new SimpleStringProperty(userPassword);
	}

}
