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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.input.MouseEvent;

public class BasketController implements Initializable{
	// Basket
	@FXML TableView<LectureModel> basketTable;
	
	@FXML TableColumn<LectureModel, Integer> basketNumberColumn;
	@FXML TableColumn<LectureModel, String> basketNameColumn;
	@FXML TableColumn<LectureModel, String> basketProfessorColumn;
	@FXML TableColumn<LectureModel, Integer> basketCreditColumn;
	@FXML TableColumn<LectureModel, String> basketTimeColumn;
	
	@FXML Button basketToRegister;
	@FXML Button basketDelete;
	
	// Register
	@FXML TableView<LectureModel> registerTable;
	
	@FXML TableColumn<LectureModel, Integer> registerNumberColumn;
	@FXML TableColumn<LectureModel, String> registerNameColumn;
	@FXML TableColumn<LectureModel, String> registerProfessorColumn;
	@FXML TableColumn<LectureModel, Integer> registerCreditColumn;
	@FXML TableColumn<LectureModel, String> registerTimeColumn;
	
	@FXML Button registerToBasket;
	@FXML Button registerDelete;
	
	// Data
	private Vector<LectureModel> lectureModels;
	ObservableList<LectureModel> lectureList = FXCollections.observableArrayList();
	
	// Control Bar
	@FXML Button lectureMove;
	@FXML Button userMove;
	@FXML Button settingMove;
	
	public void initialize(URL location, ResourceBundle resources) {
		// Basket
		basketNumberColumn.setCellValueFactory(cellData->cellData.getValue().numberProperty().asObject());
		basketNameColumn.setCellValueFactory(cellData->cellData.getValue().nameProperty());
		basketProfessorColumn.setCellValueFactory(cellData->cellData.getValue().professorProperty());
		basketCreditColumn.setCellValueFactory(cellData->cellData.getValue().creditProperty().asObject());
		basketTimeColumn.setCellValueFactory(cellData->cellData.getValue().timeProperty());
		
		try {
			this.getLectureList("Basket");
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
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
		
		// Register
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
			this.getLectureList("Register");
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		registerDelete.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				//lectureTable.getItems().add(new LectureModel(new SimpleIntegerProperty(Integer.parseInt(numberField.getText())), new SimpleStringProperty(nameField.getText()), 
					//	new SimpleStringProperty(professorField.getText()), new SimpleIntegerProperty(Integer.parseInt(creditField.getText())), new SimpleStringProperty(timeField.getText())));
			}
		});
		
		// Control Bar
		lectureMove.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				// TODO Auto-generated method stub
				
			}
		});
	}
	
	// Load Data Method
	private Vector<LectureModel> getLectureData(String fileName) throws FileNotFoundException{
		lectureModels = new Vector<LectureModel>();

		Scanner scanner = new Scanner(new File(fileName));

		while(scanner.hasNext()) {
			LectureModel lectureModel = new LectureModel();
			lectureModel.read(scanner);
			lectureModels.add(lectureModel);
		}
		scanner.close();
		return lectureModels;
	} 
	
	private void getLectureList(String fileName) throws FileNotFoundException {		
		lectureModels = getLectureData("data/user/"+fileName);
		
		lectureList.clear();
		
		for(LectureModel lectureModel: lectureModels) {
			basketTable.getItems().add(new LectureModel(new SimpleIntegerProperty(lectureModel.getNumber()), new SimpleStringProperty(lectureModel.getName()), 
					new SimpleStringProperty(lectureModel.getProfessor()), new SimpleIntegerProperty(lectureModel.getCredit()), new SimpleStringProperty(lectureModel.getTime())));
		}
	}
}
