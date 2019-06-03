package home.controllers;

import home.controllers.MainController;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.Scanner;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class LoginController implements Initializable {
	@FXML private Button loginButton;
	@FXML private Button signUpButton;
	@FXML private TextField loginTextField;
	@FXML private PasswordField loginPasswordField;
	
	private MainController controller;
	
	// 사용자 입력 ID, Password
	private String inputID;
	private String inputPassword;
	
	// DB에 있는 ID, Password
	private String dataID;
	private String dataPassword;
	
	public LoginController() {
		this.controller = new MainController();
	}
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// Action Handler 등록
		loginButton.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				handleLoginButtonAction(event);
			}
		});
		signUpButton.setOnAction(event->handleSignUpButtonAction(event));
	}
	
	// Login Button 클릭 시 Action 제어
	public void handleLoginButtonAction(ActionEvent event) {
		// Login 여부 Check
		boolean loginCheck;
		
		// ID, Password Field 값 가져오기
		this.inputID = loginTextField.getText();
		this.inputPassword = loginPasswordField.getText();
		
		// Login 여부에 따라 Action 제어
		try {
			// System.out.println("Input : "+inputID+" / "+inputPassword);
			loginCheck = this.authenticate(inputID, inputPassword);
			
			if(loginCheck) {
				this.controller.loadStage("src/home/fxml/Table.fxml");
			} else {
				System.out.println("실패");
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	// Sign Up Button 클릭 시 Action 제어
	public void handleSignUpButtonAction(ActionEvent event) {
		System.out.println("SignUp Detected");
	}
	
	// Login DB 읽기 전용 메소드
	public void read(Scanner scanner) {
		this.dataID = scanner.next();
		this.dataPassword = scanner.next();
	}
	
	// Login DB와 사용자 입력값 비교 메소드
	public boolean authenticate(String inputID, String inputPassword) throws FileNotFoundException{
		Scanner scanner;
		scanner = new Scanner(new File("data/login"));
			
		while(scanner.hasNext()) {
			this.read(scanner);
			// System.out.println("Data : "+dataID+" / "+dataPassword);
			
			if(this.dataID.equals(inputID)&&this.dataPassword.equals(inputPassword)) {
				scanner.close();
				return true;
			}
		}
		scanner.close();
		return false;
	}
}
