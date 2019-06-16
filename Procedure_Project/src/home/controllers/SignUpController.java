package home.controllers;

import java.io.File;
import java.io.FileNotFoundException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.Scanner;

import home.fileController.CheckDuplication;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class SignUpController implements Initializable {
	private MainController controller;
	
	@FXML Button registerButton;
	@FXML Button cancelButton;
	
	@FXML TextField idTextField;
	@FXML PasswordField passwordField;
	@FXML TextField nameTextField;
	@FXML TextField collegeTextField;
	@FXML TextField departmentTextField;
	@FXML TextField numberTextField;
	
	private Boolean signUpCheck = false;
	// 사용자 입력 Data
	private String inputID;
	private String inputPassword;
	private String inputName;
	private String inputCollege;
	private String inputDepartment;
	private String inputNumber;
	
	// DB에 있는 기존 정보
	private String dataID;
	private String dataPassword;
	private String dataName;
	private String dataCollege;
	private String dataDepartment;
	private String dataNumber;
	
	public SignUpController() {
		this.controller = new MainController();
	}
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		registerButton.setOnAction(new EventHandler<ActionEvent>(){
			@Override
			public void handle(ActionEvent event) {
				handleRegisterButtonAction(event);
			}
		});
		
		cancelButton.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				// TODO Auto-generated method stub
				handleCancelButtonAction(event);
			}
		});
	}
	
	public void handleRegisterButtonAction(ActionEvent event) {
		this.signUpProcess();
		
		if(this.signUpCheck) {
			Stage signUp = (Stage)registerButton.getScene().getWindow();
			signUp.close();
		} else {
			idTextField.setText(null);
			passwordField.setText(null);
			nameTextField.setText(null);
			collegeTextField.setText(null);
			departmentTextField.setText(null);
			numberTextField.setText(null);
		}
	}
	
	public void handleCancelButtonAction(ActionEvent event) {
		Stage signUp = (Stage)cancelButton.getScene().getWindow();
		signUp.close();
	}
	
	private void signUpProcess() {
		boolean idCheck;
		boolean numberCheck;
		
		this.inputID = idTextField.getText();
		this.inputPassword = passwordField.getText();
		this.inputName = nameTextField.getText();
		this.inputCollege = collegeTextField.getText();
		this.inputDepartment = departmentTextField.getText();
		this.inputNumber = numberTextField.getText();
		
		try {
			idCheck = this.idAuthenticate(this.inputID);
			numberCheck = this.personAuthenticate(this.inputNumber);
			
			if(!idCheck && !numberCheck) {
				String userInfo = this.inputID+" "+this.inputPassword+" "+this.inputName+" "+this.inputCollege + " "+this.inputDepartment + " "+this.inputNumber;
				CheckDuplication.manageUserFile(userInfo, "data/User/Login");
				this.signUpCheck = true;
			} else if(!idCheck && numberCheck) {
				System.out.println("한 사람당 한 ID");
				
				this.signUpCheck = false;
			} else {
				System.out.println("중복 ID");
				
				this.signUpCheck = false;
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public boolean idAuthenticate(String inputID) throws FileNotFoundException {
		Scanner scanner;
		scanner = new Scanner(new File("data/user/Login"));
		
		while(scanner.hasNext()) {
			this.read(scanner);
			
			if(dataID.equals(inputID)) {
				scanner.close();
				return true;
			}
		}
		
		scanner.close();
		return false;
	}
	
	public boolean personAuthenticate(String inputNumber) throws FileNotFoundException {
		Scanner scanner;
		scanner = new Scanner(new File("data/user/Login"));
		
		while(scanner.hasNext()) {
			this.read(scanner);
			
			if(dataNumber.equals(inputNumber)) {
				scanner.close();
				return true;
			}
		}
		
		scanner.close();
		return false;
	}
	
	public void read(Scanner scanner) {
		this.dataID = scanner.next();
		this.dataPassword = scanner.next();
		this.dataName = scanner.next();
		this.dataCollege = scanner.next();
		this.dataDepartment = scanner.next();
		this.dataNumber = scanner.next();
	}
}
