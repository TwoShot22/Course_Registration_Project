package home.controllers;

import home.controllers.MainController;
import home.fileController.CheckDuplication;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.Scanner;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

public class LoginController implements Initializable {
	@FXML private Button loginButton;
	@FXML private Button signUpButton;
	@FXML private CheckBox saveUserName;
	@FXML private TextField loginTextField;
	@FXML private PasswordField loginPasswordField;
	@FXML private Label loginErrorMessage;
	
	private MainController controller;
	
	// ����� �Է� ID, Password
	private String inputID;
	private String inputPassword;
	
	// DB�� �ִ� ID, Password
	private String dataID;
	private String dataPassword;
	private String dataName;
	private String dataCollege;
	private String dataDepartment;
	private String dataNumber;
	
	// ID ����
	private String tempID;
	private boolean saveMode = false;
	
	public LoginController() {
		this.controller = new MainController();
	}
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// Action Handler ���
		loginButton.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				handleLoginButtonAction(event);
			}
		});
		
		signUpButton.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				handleSignUpButtonAction(event);
			}
		});
		
		loginTextField.setOnKeyPressed(new EventHandler<KeyEvent>() {
			@Override
			public void handle(KeyEvent event) {
				// TODO Auto-generated method stub
				if(event.getCode().equals(KeyCode.ENTER)) {
					handleLoginButtonAction(event);
				}
			}
		});
		
		loginPasswordField.setOnKeyPressed(new EventHandler<KeyEvent>() {
			@Override
			public void handle(KeyEvent event) {
				// TODO Auto-generated method stub
				if(event.getCode().equals(KeyCode.ENTER)) {
					handleLoginButtonAction(event);
				}
			}
		});
				
		saveUserName.selectedProperty().addListener(new ChangeListener<Boolean>() {
			@Override
			public void changed(ObservableValue<? extends Boolean> ov, Boolean old_val, Boolean new_val) {
				// TODO Auto-generated method stub
				saveUserNameAction(new_val);
			}
		});
	}
	
	// Login Button Ŭ�� �� Action ����
	public void handleLoginButtonAction(ActionEvent event) {
		this.loginProcess();
	}
	
	public void handleLoginButtonAction(KeyEvent event) {
		this.loginProcess();
	}
	
	// Sign Up Button Ŭ�� �� Action ����
	public void handleSignUpButtonAction(ActionEvent event) {
		this.controller.loadStage("src/home/fxml/SignUp.fxml","�������б� ������û �ý���");
		signUpButton.getScene().getWindow();
	}
	
	private void loginProcess() {
		// Login ���� Check
		boolean loginCheck;
				
		// ID, Password Field �� ��������
		this.inputID = loginTextField.getText();
		this.inputPassword = loginPasswordField.getText();
				
		// Login ���ο� ���� Action ����
		try {
			loginCheck = this.authenticate(inputID, inputPassword);
					
			if(loginCheck) {
				String userInfo = this.dataID + " "+this.dataName+" "+this.dataCollege + " "+this.dataDepartment + " "+this.dataNumber;
				CheckDuplication.manageCurrentUser(userInfo, "data/user/CurrentUser");
				
				this.controller.loadStage("src/home/fxml/Table.fxml","�������б� ������û �ý���");
				Stage login = (Stage)loginButton.getScene().getWindow();
				login.close();
			} else {
				loginErrorMessage.setText("Invalid UserName or Password");
				if(this.saveMode) {
					loginTextField.setText(tempID);
				} else {
					loginTextField.setText(null);					
				}
				
				loginPasswordField.setText(null);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	// Login DB �б� ���� �޼ҵ�
	public void read(Scanner scanner) {
		this.dataID = scanner.next();
		this.dataPassword = scanner.next();
		this.dataName = scanner.next();
		this.dataCollege = scanner.next();
		this.dataDepartment = scanner.next();
		this.dataNumber = scanner.next();
	}
	
	// Login DB�� ����� �Է°� �� �޼ҵ�
	public boolean authenticate(String inputID, String inputPassword) throws FileNotFoundException{
		Scanner scanner;
		scanner = new Scanner(new File("data/user/Login"));
			
		while(scanner.hasNext()) {
			this.read(scanner);

			if(this.dataID.equals(inputID)&&this.dataPassword.equals(inputPassword)) {
				scanner.close();
				return true;
			}
		}
		scanner.close();
		return false;
	}
	
	// ID ���� �޼ҵ�
	private void saveUserNameAction(Boolean val) {
		this.saveMode = val;
		
		if(this.saveMode) {
			this.tempID = loginTextField.getText();			
		} else {
			this.tempID = null;
			loginTextField.setText(null);
		}
	}
}