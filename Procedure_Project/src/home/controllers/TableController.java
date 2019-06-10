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
	@FXML ComboBox<String> campusPickBox;
	@FXML ComboBox<String> collegePickBox;
	@FXML ComboBox<String> departmentPickBox;
	
	private Vector<DirectoryModel> campusModels;
	private ObservableList<String> campusItems;
	private ObservableList<String> campusList;
	
	private Vector<DirectoryModel> collegeModels;
	private ObservableList<String> collegeItems;
	private ObservableList<String> collegeList;
	
	private Vector<DirectoryModel> departmentModels;
	private ObservableList<String> departmentItems;
	private ObservableList<String> departmentList;
	
	private String startPath = "root";
	private String campusPath = " ";
	private String collegePath = " ";
	private String departmentPath = " ";
	
	// Lecture
	@FXML TableView<LectureModel> lectureTable;
	
	@FXML TableColumn<LectureModel, Integer> numberColumn;
	@FXML TableColumn<LectureModel, String> nameColumn;
	@FXML TableColumn<LectureModel, String> professorColumn;
	@FXML TableColumn<LectureModel, Integer> creditColumn;
	@FXML TableColumn<LectureModel, String> timeColumn;
	
	private Vector<LectureModel> lectureModels;
	ObservableList<LectureModel> lectureList = FXCollections.observableArrayList();
		
	// Button
	@FXML Button confirmButton;
	@FXML Button cancelButton;
	
	@FXML Button basketMove;
	@FXML Button userMove;
	@FXML Button settingMove;
	
	// Load Basket.fxml
	private MainController controller;
	
	public TableController() {
		this.controller = new MainController();
	}
	
	// Initialize Methods
	public void initialize(URL location, ResourceBundle resources) {
		
		// Directory Part
		campusItems = FXCollections.observableArrayList();
		campusList = FXCollections.observableArrayList();
		
		collegeItems = FXCollections.observableArrayList();
		collegeList = FXCollections.observableArrayList();
		
		departmentItems = FXCollections.observableArrayList();
		departmentList = FXCollections.observableArrayList();
		
		// Directory Search Start
		try {
			this.getCampusHyperLink(this.startPath);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		this.campusDirectory();
		this.collegeDirectory();
		this.departmentDirectory();

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
		
		// »ó´Ü Progress Bar
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
		
		// Control Bar
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
		campusList = campusItems;
		campusPickBox.setItems(campusList);
		campusPickBox.getSelectionModel().selectedIndexProperty().addListener(new ChangeListener<Number>() {
			@Override
			public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number campusValue) {
				try {
					System.out.println("[Campus]");
					campusRefresh(campusValue.intValue());
				} catch (FileNotFoundException e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	private void collegeDirectory() {
		collegeList = collegeItems;
		collegePickBox.setItems(collegeList);
		collegePickBox.getSelectionModel().selectedIndexProperty().addListener(new ChangeListener<Number>() {
			@Override
			public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number collegeValue) {
				try {
					System.out.println("[College]");
					collegeRefresh(collegeValue.intValue());
				} catch (FileNotFoundException e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	private void departmentDirectory() {
		departmentList = departmentItems;
		departmentPickBox.setItems(departmentList);
		departmentPickBox.getSelectionModel().selectedIndexProperty().addListener(new ChangeListener<Number>() {
			@Override
			public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number departmentValue) {
				try {
					System.out.println("[Department]");
					departmentRefresh(departmentValue.intValue());
				} catch (FileNotFoundException e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	// [Campus] File Read and Add Items
	private Vector<DirectoryModel> getCampusData(String fileName) throws FileNotFoundException {
		campusModels = new Vector<DirectoryModel>();

		Scanner scanner = new Scanner(new File(fileName));

		while(scanner.hasNext()) {
			DirectoryModel campusModel = new DirectoryModel();
			campusModel.read(scanner);
			campusModels.add(campusModel);
		}
		scanner.close();
		return campusModels;
	}
	
	private String getCampusHyperLink(String fileName) throws FileNotFoundException {
		campusModels = getCampusData("data/"+fileName);			
		
		campusItems.clear();
		
		for(DirectoryModel campusModel: campusModels) {
			campusItems.add(campusModel.getName());
		}
		
		return campusModels.get(0).getHyperLink();
	}
	
	private void campusRefresh(Object source) throws FileNotFoundException {
		int item = (int)source;
		this.campusPath = this.campusModels.get(item).getHyperLink();
		System.out.println(this.campusPath);
		getCampusHyperLink(this.campusPath);
	}
	
	// [College] File Read and Add Items
	private Vector<DirectoryModel> getCollegeData(String fileName) throws FileNotFoundException {
		collegeModels = new Vector<DirectoryModel>();

		Scanner scanner = new Scanner(new File(fileName));

		while(scanner.hasNext()) {
			DirectoryModel collegeModel = new DirectoryModel();
			collegeModel.read(scanner);
			collegeModels.add(collegeModel);
		}
		scanner.close();
		return collegeModels;
	}
		
	private String getCollegeHyperLink(String fileName) throws FileNotFoundException {		
		collegeModels = getCollegeData("data/"+fileName);
			
		collegeItems.clear();
			
		for(DirectoryModel collegeModel: collegeModels) {
			collegeItems.add(collegeModel.getName());
		}
			
		return collegeModels.get(0).getHyperLink();
	}
		
	private void collegeRefresh(Object source) throws FileNotFoundException {
		int item = (int)source;
		this.collegePath = this.collegeModels.get(item).getHyperLink();
		System.out.println(this.collegePath);
		getCollegeHyperLink(this.collegePath);
	}
		
	// [Department] File Read and Add Items
	private Vector<DirectoryModel> getDepartmentData(String fileName) throws FileNotFoundException {
		departmentModels = new Vector<DirectoryModel>();

		Scanner scanner = new Scanner(new File(fileName));

		while(scanner.hasNext()) {
			DirectoryModel departmentModel = new DirectoryModel();
			departmentModel.read(scanner);
			departmentModels.add(departmentModel);
		}
		scanner.close();
		return departmentModels;
	}
		
	private String getDepartmentHyperLink(String fileName) throws FileNotFoundException {		
		departmentModels = getDepartmentData("data/"+fileName);
			
		departmentItems.clear();
			
		for(DirectoryModel departmentModel: departmentModels) {
			departmentItems.add(departmentModel.getName());
		}
			
		return departmentModels.get(0).getHyperLink();
	}
		
	private void departmentRefresh(Object source) throws FileNotFoundException {
		int item = (int)source;
		this.departmentPath = this.departmentModels.get(item).getHyperLink();
		System.out.println(this.departmentPath);
		getDepartmentHyperLink(this.departmentPath);
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
	
	// Control Bar Method
	public void handleBasketMoveAction(ActionEvent event) {
		this.controller.loadStage("src/home/fxml/Basket.fxml");
		basketMove.getScene().getWindow();
	}
}