package home.controllers;

import java.io.File;
import java.io.FileNotFoundException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.Scanner;
import java.util.Vector;

import home.model.LectureModel;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class BasketController implements Initializable{
	private MainController controller;
	
	// Basket
	@FXML TableView<LectureModel> basketTable;
	
	@FXML TableColumn<LectureModel, Integer> basketNumberColumn;
	@FXML TableColumn<LectureModel, String> basketNameColumn;
	@FXML TableColumn<LectureModel, String> basketProfessorColumn;
	@FXML TableColumn<LectureModel, Integer> basketCreditColumn;
	@FXML TableColumn<LectureModel, String> basketTimeColumn;
	
	@FXML Button basketToRegister;
	@FXML Button basketDelete;
	@FXML Button basketRefresh;
	
	// Register
	@FXML TableView<LectureModel> registerTable;
	
	@FXML TableColumn<LectureModel, Integer> registerNumberColumn;
	@FXML TableColumn<LectureModel, String> registerNameColumn;
	@FXML TableColumn<LectureModel, String> registerProfessorColumn;
	@FXML TableColumn<LectureModel, Integer> registerCreditColumn;
	@FXML TableColumn<LectureModel, String> registerTimeColumn;
	
	@FXML Button registerToBasket;
	@FXML Button registerDelete;
	@FXML Button registerRefresh;
	
	// Data
	private Vector<LectureModel> basketModels;
	ObservableList<LectureModel> basketList = FXCollections.observableArrayList();
	private Object basketOldValue;
	
	private Vector<LectureModel> registerModels;
	ObservableList<LectureModel> registerList = FXCollections.observableArrayList();
	private Object registerOldValue;
	
	// Control Bar
	@FXML Button lectureMove;
	@FXML Button userMove;
	@FXML Button loginMove;
	
	public BasketController() {
		this.controller = new MainController();
	}
	
	public void initialize(URL location, ResourceBundle resources) {
		// Basket
		basketTable.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
		
		basketNumberColumn.setCellValueFactory(cellData->cellData.getValue().numberProperty().asObject());
		basketNameColumn.setCellValueFactory(cellData->cellData.getValue().nameProperty());
		basketProfessorColumn.setCellValueFactory(cellData->cellData.getValue().professorProperty());
		basketCreditColumn.setCellValueFactory(cellData->cellData.getValue().creditProperty().asObject());
		basketTimeColumn.setCellValueFactory(cellData->cellData.getValue().timeProperty());
		
		try {
			this.getBasketList("Basket");
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		basketTable.setOnMouseClicked(event->{
			if(basketTable.getSelectionModel().getSelectedItem()!=null) {
				if(event.getPickResult().getIntersectedNode().equals(basketOldValue)) {
					basketTable.getSelectionModel().clearSelection();
					basketOldValue = null;
				} else {
					basketOldValue = event.getPickResult().getIntersectedNode();
				}
			}
		});
		
		basketToRegister.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				//lectureTable.getItems().add(new LectureModel(new SimpleIntegerProperty(Integer.parseInt(numberField.getText())), new SimpleStringProperty(nameField.getText()), 
					//	new SimpleStringProperty(professorField.getText()), new SimpleIntegerProperty(Integer.parseInt(creditField.getText())), new SimpleStringProperty(timeField.getText())));
			}
		});
		
		basketDelete.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				//lectureTable.getItems().add(new LectureModel(new SimpleIntegerProperty(Integer.parseInt(numberField.getText())), new SimpleStringProperty(nameField.getText()), 
					//	new SimpleStringProperty(professorField.getText()), new SimpleIntegerProperty(Integer.parseInt(creditField.getText())), new SimpleStringProperty(timeField.getText())));
			}
		});
		
		basketRefresh.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				basketTable.getSelectionModel().clearSelection();
			}
		});
		
		// Register
		registerTable.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);

		registerNumberColumn.setCellValueFactory(cellData->cellData.getValue().numberProperty().asObject());
		registerNameColumn.setCellValueFactory(cellData->cellData.getValue().nameProperty());
		registerProfessorColumn.setCellValueFactory(cellData->cellData.getValue().professorProperty());
		registerCreditColumn.setCellValueFactory(cellData->cellData.getValue().creditProperty().asObject());
		registerTimeColumn.setCellValueFactory(cellData->cellData.getValue().timeProperty());
		
		registerToBasket.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				//lectureTable.getItems().add(new LectureModel(new SimpleIntegerProperty(Integer.parseInt(numberField.getText())), new SimpleStringProperty(nameField.getText()), 
					//	new SimpleStringProperty(professorField.getText()), new SimpleIntegerProperty(Integer.parseInt(creditField.getText())), new SimpleStringProperty(timeField.getText())));
			}
		});
		
		try {
			this.getRegisterList("Register");
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		registerTable.setOnMouseClicked(event->{
			if(registerTable.getSelectionModel().getSelectedItem()!=null) {
				if(event.getPickResult().getIntersectedNode().equals(registerOldValue)) {
					registerTable.getSelectionModel().clearSelection();
					registerOldValue = null;
				} else {
					registerOldValue = event.getPickResult().getIntersectedNode();
				}
			}
		});
		
		registerDelete.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				//lectureTable.getItems().add(new LectureModel(new SimpleIntegerProperty(Integer.parseInt(numberField.getText())), new SimpleStringProperty(nameField.getText()), 
					//	new SimpleStringProperty(professorField.getText()), new SimpleIntegerProperty(Integer.parseInt(creditField.getText())), new SimpleStringProperty(timeField.getText())));
			}
		});
		
		registerRefresh.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				registerTable.getSelectionModel().clearSelection();
			}
		});
		
		// Control Bar
		lectureMove.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				// TODO Auto-generated method stub
				handleLectureMoveAction(event);
			}
		});
		
		loginMove.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				// TODO Auto-generated method stub
				handleLoginMoveAction(event);
			}
		});
	}
	
	// Load Data Method
	private Vector<LectureModel> getBasketData(String fileName) throws FileNotFoundException{
		basketModels = new Vector<LectureModel>();

		Scanner scanner = new Scanner(new File(fileName));

		while(scanner.hasNext()) {
			LectureModel lectureModel = new LectureModel();
			lectureModel.read(scanner);
			basketModels.add(lectureModel);
		}
		scanner.close();
		return basketModels;
	} 
	
	private void getBasketList(String fileName) throws FileNotFoundException {		
		for(int i=0;i<basketTable.getItems().size();i++) {
			basketTable.getItems().clear();
		}
		
		basketModels = getBasketData("data/user/"+fileName);
		
		basketList.clear();
		
		for(LectureModel basketModel: basketModels) {
			basketTable.getItems().add(new LectureModel(new SimpleIntegerProperty(basketModel.getNumber()), new SimpleStringProperty(basketModel.getName()), 
					new SimpleStringProperty(basketModel.getProfessor()), new SimpleIntegerProperty(basketModel.getCredit()), new SimpleStringProperty(basketModel.getTime())));
		}
	}
	
	private Vector<LectureModel> getRegisterData(String fileName) throws FileNotFoundException{
		registerModels = new Vector<LectureModel>();

		Scanner scanner = new Scanner(new File(fileName));

		while(scanner.hasNext()) {
			LectureModel registerModel = new LectureModel();
			registerModel.read(scanner);
			registerModels.add(registerModel);
		}
		scanner.close();
		return registerModels;
	} 
	
	private void getRegisterList(String fileName) throws FileNotFoundException {		
		for(int i=0;i<registerTable.getItems().size();i++) {
			registerTable.getItems().clear();
		}
		
		registerModels = getRegisterData("data/user/"+fileName);
		
		registerList.clear();
		
		for(LectureModel registerModel: registerModels) {
			registerTable.getItems().add(new LectureModel(new SimpleIntegerProperty(registerModel.getNumber()), new SimpleStringProperty(registerModel.getName()), 
					new SimpleStringProperty(registerModel.getProfessor()), new SimpleIntegerProperty(registerModel.getCredit()), new SimpleStringProperty(registerModel.getTime())));
		}
	}
	
	// Control Bar Method
		public void handleLectureMoveAction(ActionEvent event) {
			this.controller.loadStage("src/home/fxml/Table.fxml","명지대학교 수강신청 시스템");
			Stage basket = (Stage)lectureMove.getScene().getWindow();
			basket.close();
		}
		
		public void handleLoginMoveAction(ActionEvent event) {
			this.controller.loadStage("src/home/fxml/Login.fxml", "명지대학교 수강신청 시스템");
			Stage basket = (Stage)loginMove.getScene().getWindow();
			basket.close();
		}
}
