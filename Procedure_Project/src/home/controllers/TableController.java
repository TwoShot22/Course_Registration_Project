package home.controllers;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.ListIterator;
import java.util.ResourceBundle;
import java.util.Scanner;
import java.util.Vector;

import home.fileController.CheckDuplication;
import home.fileController.FileTool;
import home.model.DirectoryModel;
import home.model.LectureModel;
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
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

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
	private String campusPath = "yongin";
	private String collegePath = "generalY";
	private String departmentPath = "englishYG";
	
	// Lecture
	@FXML TableView<LectureModel> lectureTable;
	
	@FXML TableColumn<LectureModel, Integer> numberColumn;
	@FXML TableColumn<LectureModel, String> nameColumn;
	@FXML TableColumn<LectureModel, String> professorColumn;
	@FXML TableColumn<LectureModel, Integer> creditColumn;
	@FXML TableColumn<LectureModel, String> timeColumn;
	
	private Vector<LectureModel> lectureModels;
	ObservableList<LectureModel> lectureList = FXCollections.observableArrayList();
	
	private Object oldValue;
	
	// Button
	@FXML Button lectureToBasket;
	@FXML Button lectureRefresh;
	
	@FXML Button basketMove;
	@FXML Button userMove;
	@FXML Button loginMove;
	
	// Load Basket.fxml
	private MainController controller;
	private FileTool fileTool;
	private CheckDuplication checkDuplication;
	
	public TableController() {
		this.controller = new MainController();
		this.fileTool = new FileTool();
		this.checkDuplication = new CheckDuplication();
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
		
		
		// Directory Method	
		try {
			this.refreshDirectory();
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		campusList = campusItems;
		campusPickBox.setItems(campusList);
		campusPickBox.getSelectionModel().select(0);
		campusPickBox.getSelectionModel().selectedIndexProperty().addListener(new ChangeListener<Number>() {
			@Override
			public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number campusValue) {
				try {
					campusRefresh(campusValue.intValue());
				} catch (FileNotFoundException e) {
					e.printStackTrace();
				}
			}
		});
		
		collegeList = collegeItems;
		collegePickBox.setItems(collegeList);
		collegePickBox.getSelectionModel().select(0);
		collegePickBox.getSelectionModel().selectedIndexProperty().addListener(new ChangeListener<Number>() {
			@Override
			public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number collegeValue) {
				try {
					collegeRefresh(collegeValue.intValue());
				} catch (FileNotFoundException e) {
					e.printStackTrace();
				}
			}
		});
		
		departmentList = departmentItems;
		departmentPickBox.setItems(departmentList);
		departmentPickBox.getSelectionModel().select(0);
		departmentPickBox.getSelectionModel().selectedIndexProperty().addListener(new ChangeListener<Number>() {
			@Override
			public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number departmentValue) {
				try {
					departmentRefresh(departmentValue.intValue());
				} catch (FileNotFoundException e) {
					e.printStackTrace();
				}
			}
		});

		// Lecture Part
		lectureTable.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
		
		numberColumn.setCellValueFactory(cellData->cellData.getValue().numberProperty().asObject());
		nameColumn.setCellValueFactory(cellData->cellData.getValue().nameProperty());
		professorColumn.setCellValueFactory(cellData->cellData.getValue().professorProperty());
		creditColumn.setCellValueFactory(cellData->cellData.getValue().creditProperty().asObject());
		timeColumn.setCellValueFactory(cellData->cellData.getValue().timeProperty());
		
		lectureTable.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<LectureModel>() {
			public void changed(ObservableValue<? extends LectureModel> observable, LectureModel oldValue,
					LectureModel newValue) {
				LectureModel selected = lectureTable.getSelectionModel().getSelectedItem();
				//getSelectedLecture(selected);
			}
		});
		
		lectureTable.setOnMouseClicked(event->{
			if(lectureTable.getSelectionModel().getSelectedItem()!=null) {
				if(event.getPickResult().getIntersectedNode().equals(oldValue)) {
					lectureTable.getSelectionModel().clearSelection();
					oldValue = null;
				} else {
					oldValue = event.getPickResult().getIntersectedNode();
				}
			}
		});
		
		// 상단 Progress Bar
		lectureToBasket.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				Vector<String> selectedLectures = new Vector<>();
				ObservableList<LectureModel> selectedItem = lectureTable.getSelectionModel().getSelectedItems();
				
				for(int i=0;i<selectedItem.size();i++) {
					selectedLectures.add(String.valueOf(selectedItem.get(i).getNumber()));
					selectedLectures.add(selectedItem.get(i).getName());
					selectedLectures.add(selectedItem.get(i).getProfessor());
					selectedLectures.add(String.valueOf(selectedItem.get(i).getCredit()));
					selectedLectures.add(selectedItem.get(i).getTime());
				}
				
				FileTool.writeOnTxtFile(selectedLectures, "data/user/Basket");
			}
		});
		
		lectureRefresh.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
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
		
		loginMove.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent event) {
				handleLoginMoveAction(event);
			}
		});
	}
	
	// Directory Methods
	
	private void refreshDirectory() throws FileNotFoundException {
		this.campusPath = getCampusHyperLink(this.startPath);
		this.campusRefresh(0);
		
		this.collegePath = getCollegeHyperLink(this.campusPath);
		this.collegeRefresh(0);
		
		this.departmentPath = getDepartmentHyperLink(this.collegePath);
		this.departmentRefresh(0);
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
		
		this.getCollegeHyperLink(this.campusPath);
		this.collegeRefresh(0);
		this.collegePickBox.getSelectionModel().select(0);
		this.departmentRefresh(0);
		this.departmentPickBox.getSelectionModel().select(0);
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
		if(item<0) return;
		this.collegePath = this.collegeModels.get(item).getHyperLink();
		
		this.getDepartmentHyperLink(this.collegePath);
		this.departmentRefresh(0);
		this.departmentPickBox.getSelectionModel().select(0);
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
		if(item<0) return;
		this.departmentPath = this.departmentModels.get(item).getHyperLink();
		this.getLectureList(this.departmentPath);
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
		lectureTable.getItems().clear();
		
		lectureModels = getLectureData("data/"+fileName);
		lectureList.clear();
		
		for(LectureModel lectureModel: lectureModels) {
			lectureTable.getItems().add(new LectureModel(new SimpleIntegerProperty(lectureModel.getNumber()), new SimpleStringProperty(lectureModel.getName()), 
					new SimpleStringProperty(lectureModel.getProfessor()), new SimpleIntegerProperty(lectureModel.getCredit()), new SimpleStringProperty(lectureModel.getTime())));
		}
	}
	
	// Control Bar Method
	public void handleBasketMoveAction(ActionEvent event) {
		this.controller.loadStage("src/home/fxml/Basket.fxml", "명지대학교 수강신청 시스템");
		Stage lecture = (Stage)basketMove.getScene().getWindow();
		lecture.close();
	}
	
	public void handleLoginMoveAction(ActionEvent event) {
		this.controller.loadStage("src/home/fxml/Login.fxml", "명지대학교 수강신청 시스템");
		Stage lecture = (Stage)loginMove.getScene().getWindow();
		lecture.close();
	}
}