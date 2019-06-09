package home.controllers;

import home.controllers.MainController;
import home.model.DirectoryModel;
import home.model.LectureModel;

import java.io.File;
import java.io.FileNotFoundException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.Scanner;
import java.util.Vector;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.input.MouseEvent;

public class TableController implements Initializable{	
	// Directory
	@FXML ComboBox<String> campusList;
	@FXML ComboBox<String> collegeList;
	@FXML ComboBox<String> departmentList;
	
	private Vector<DirectoryModel> directoryModels;
	private ObservableList<String> listItems;
	
	private ObservableList<String> campusListItems;
	private ObservableList<String> collegeListItems;
	private ObservableList<String> departmentListItems;
	
	// Lecture
	@FXML TableView<LectureModel> lectureTable;
	
	@FXML TableColumn<LectureModel, Integer> numberColumn;
	@FXML TableColumn<LectureModel, String> nameColumn;
	@FXML TableColumn<LectureModel, String> professorColumn;
	@FXML TableColumn<LectureModel, Integer> creditColumn;
	@FXML TableColumn<LectureModel, String> timeColumn;
	
	private Vector<LectureModel> lectureModels;
	ObservableList<LectureModel> lectureList = FXCollections.observableArrayList();
	
	String directoryPath = " ";
	
	// Button
	@FXML Button confirmButton;
	@FXML Button cancelButton;
	
	@FXML Button basketMove;
	@FXML Button userMove;
	@FXML Button settingMove;
	
	private MainController controller;
	
	public TableController() {
		this.controller = new MainController();
	}
	
	// Initialize Methods
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// Directory Part
		listItems = FXCollections.observableArrayList();
		campusListItems = FXCollections.observableArrayList();
		collegeListItems = FXCollections.observableArrayList();
		departmentListItems = FXCollections.observableArrayList();
		
		String startPath = "root";
		try {
			this.getDirectory(startPath);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		campusDirectory();
		collegeDirectory();
		departmentDirectory();

		// Lecture Part
		numberColumn.setCellValueFactory(cellData->cellData.getValue().numberProperty().asObject());
		nameColumn.setCellValueFactory(cellData->cellData.getValue().nameProperty());
		professorColumn.setCellValueFactory(cellData->cellData.getValue().professorProperty());
		creditColumn.setCellValueFactory(cellData->cellData.getValue().creditProperty().asObject());
		timeColumn.setCellValueFactory(cellData->cellData.getValue().timeProperty());
		
		try {
			getLectureList("english");
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		confirmButton.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				//lectureTable.getItems().add(new LectureModel(new SimpleIntegerProperty(Integer.parseInt(numberField.getText())), new SimpleStringProperty(nameField.getText()), 
					//	new SimpleStringProperty(professorField.getText()), new SimpleIntegerProperty(Integer.parseInt(creditField.getText())), new SimpleStringProperty(timeField.getText())));
			}
		});
		
		cancelButton.addEventHandler(MouseEvent.MOUSE_CLICKED,new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent arg0) {
				lectureTable.getSelectionModel().clearSelection();
			}
		});
		
		basketMove.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				// TODO Auto-generated method stub
				handleBasketMoveAction(event);
			}
		});
	}
	
	// Directory Methods
	private void campusDirectory() {
		campusListItems = listItems;
		campusList.setItems(campusListItems);
		campusList.getSelectionModel().selectedIndexProperty().addListener(new ChangeListener<Number>() {
			@Override
			public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number campusValue) {
				try {
					System.out.println("[Campus]");
					refresh(campusValue.intValue());
				} catch (FileNotFoundException e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	private void collegeDirectory() {
		collegeListItems = listItems;
		collegeList.setItems(collegeListItems);
		collegeList.getSelectionModel().selectedIndexProperty().addListener(new ChangeListener<Number>() {
			@Override
			public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number collegeValue) {
				try {
					System.out.println("[College]");
					refresh(collegeValue.intValue());
				} catch (FileNotFoundException e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	private void departmentDirectory() {
		departmentListItems = listItems;
		departmentList.setItems(departmentListItems);
		departmentList.getSelectionModel().selectedIndexProperty().addListener(new ChangeListener<Number>() {
			@Override
			public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number departmentValue) {
				try {
					System.out.println("[Department]");
					refresh(departmentValue.intValue());
				} catch (FileNotFoundException e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	// File Read with Scanner
	private Vector<DirectoryModel> getDataFile(String fileName) throws FileNotFoundException {
		directoryModels = new Vector<DirectoryModel>();

		Scanner scanner = new Scanner(new File(fileName));

		while(scanner.hasNext()) {
			DirectoryModel directoryModel = new DirectoryModel();
			directoryModel.read(scanner);
			directoryModels.add(directoryModel);
		}
		scanner.close();
		return directoryModels;
	}
	
	// Add items to ListItems
	private String getDirectory(String fileName) throws FileNotFoundException {		
		directoryModels = getDataFile("data/"+fileName);
		
		listItems.clear();
		
		for(DirectoryModel directoryModel: directoryModels) {
			listItems.add(directoryModel.getName());
		}
		
		return directoryModels.get(0).getHyperLink();
	}
	
	// Get SelectedIndex from ChangeValue && Change Directory
	private void refresh(Object source) throws FileNotFoundException {
		int item = (int)source;
		String hyperLink = this.directoryModels.get(item).getHyperLink();
		System.out.println(hyperLink);
		getDirectory(hyperLink);
	}
	
	
	// Lecture Methods	
	
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
		lectureModels = getLectureData("data/"+fileName);
		
		lectureList.clear();
		
		for(LectureModel lectureModel: lectureModels) {
			lectureTable.getItems().add(new LectureModel(new SimpleIntegerProperty(lectureModel.getNumber()), new SimpleStringProperty(lectureModel.getName()), 
					new SimpleStringProperty(lectureModel.getProfessor()), new SimpleIntegerProperty(lectureModel.getCredit()), new SimpleStringProperty(lectureModel.getTime())));
		}
	}
	
	public void handleBasketMoveAction(ActionEvent event) {
		this.controller.loadStage("src/home/fxml/Basket.fxml");
		basketMove.getScene().getWindow();
	}
}