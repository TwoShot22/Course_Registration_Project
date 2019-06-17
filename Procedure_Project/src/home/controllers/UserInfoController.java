package home.controllers;

import java.io.File;
import java.io.FileNotFoundException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.Scanner;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

public class UserInfoController implements Initializable{
	private MainController controller;
	
	@FXML Button closeWindowButton;
	
	@FXML Label nameLabel;
	@FXML Label numberLabel;
	@FXML Label collegeLabel;
	@FXML Label departmentLabel;	
	
	@FXML Label maxCreditLabel;
	@FXML Label currentCreditLabel;
	@FXML Label currentLectureLabel;
	
	// Current User Data
	private String userID;
	private String userName;
	private String userCollege;
	private String userDepartment;
	private String userNumber;
		
	public UserInfoController() {
		this.controller = new MainController();
		
		this.checkCurrentUser();
	}
	
	public void checkCurrentUser() {
		Scanner scanner;
		try {
			scanner = new Scanner(new File("data/user/CurrentUser"));
	
			while(scanner.hasNext()) {
				this.userID = scanner.next();
				this.userName = scanner.next();
				this.userCollege = scanner.next();
				this.userDepartment = scanner.next();
				this.userNumber = scanner.next();			
			}

			scanner.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
	}
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		closeWindowButton.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				handleCloseWindowButton(event);
			}
		});
		
		nameLabel.setText("Name : "+this.userName);
		numberLabel.setText("Number : "+this.userNumber);
		collegeLabel.setText("College : "+this.userCollege);
		departmentLabel.setText("Dept. : "+this.userDepartment);
	}
	
	private void handleCloseWindowButton(ActionEvent event) {
		Stage userInfo = (Stage)closeWindowButton.getScene().getWindow();
		userInfo.close();
	}
}
