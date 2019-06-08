package home.controllers;

import home.model.DirectoryModel;
import home.model.LectureModel;

import java.io.File;
import java.io.FileNotFoundException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.Scanner;
import java.util.Vector;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

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
	@FXML TableColumn<LectureModel, Button> basketColumn;
	
	private Vector<LectureModel> lectureModels;
	
	// Button
	@FXML
	private void confirmSelect() {
		TableController tableController = new TableController();
		
	}
	
	// Initialize Methods
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// Directory Part
		this.listItems = FXCollections.observableArrayList();
		this.campusListItems = FXCollections.observableArrayList();
		this.collegeListItems = FXCollections.observableArrayList();
		this.departmentListItems = FXCollections.observableArrayList();
		
		String startPath = "root";
		try {
			this.getDirectory(startPath);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		this.campusDirectory();
		this.collegeDirectory();
		this.departmentDirectory();

		// Lecture Part
		initializeTable();
		loadLecture();
	}

	// Directory Methods
	private void campusDirectory() {
		this.campusListItems = this.listItems;
		this.campusList.setItems(this.campusListItems);
		this.campusList.getSelectionModel().selectedIndexProperty().addListener(new ChangeListener<Number>() {
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
		this.collegeListItems = this.listItems;
		this.collegeList.setItems(this.collegeListItems);
		this.collegeList.getSelectionModel().selectedIndexProperty().addListener(new ChangeListener<Number>() {
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
		this.departmentListItems = this.listItems;
		this.departmentList.setItems(this.departmentListItems);
		this.departmentList.getSelectionModel().selectedIndexProperty().addListener(new ChangeListener<Number>() {
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
		this.directoryModels = this.getDataFile("data/"+fileName);
		
		for(int i=0;i<listItems.size();i++) {
			listItems.remove(i);
		}
		
		if(listItems.size()==0) {
			System.out.println("remove success");
		}
		
		for(DirectoryModel directoryModel: directoryModels) {
			listItems.add(directoryModel.getName());
		}
		
		return this.directoryModels.get(0).getHyperLink();
	}
	
	// Get SelectedIndex from ChangeValue && Change Directory
	private void refresh(Object source) throws FileNotFoundException {
		int item = (int)source;
		String hyperLink = this.directoryModels.get(item).getHyperLink();
		System.out.println(hyperLink);
		// this.getDirectory(hyperLink);
	}
	
	
	// Lecture Methods
	private ObservableList<LectureModel> lectureModel = FXCollections.observableArrayList(
			// Dummy Data
			new LectureModel(3214,"객체지향적사고와프로그래밍","최성운",3,"월수9:00-10:30",new Button("GET")),
			new LectureModel(3216,"시스템프로그래밍","김일주",3,"화목14:00-15:30",new Button("GET"))
	);
	
	
	private void initializeTable() {
		this.numberColumn.setCellValueFactory(new PropertyValueFactory<>("number"));
		this.nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
		this.professorColumn.setCellValueFactory(new PropertyValueFactory<>("professor"));
		this.creditColumn.setCellValueFactory(new PropertyValueFactory<>("credit"));
		this.timeColumn.setCellValueFactory(new PropertyValueFactory<>("time"));
		this.basketColumn.setCellValueFactory(new PropertyValueFactory<>("button"));
	}
	
	private void loadLecture() {
		this.numberColumn.setCellValueFactory(new PropertyValueFactory<>("number"));
		this.nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
		this.professorColumn.setCellValueFactory(new PropertyValueFactory<>("professor"));
		this.creditColumn.setCellValueFactory(new PropertyValueFactory<>("credit"));
		this.timeColumn.setCellValueFactory(new PropertyValueFactory<>("time"));
		this.basketColumn.setCellValueFactory(new PropertyValueFactory<>("button"));
		
		this.lectureTable.setItems(lectureModel);
	}
	
	private Vector<LectureModel> getLectureData(String fileName) throws FileNotFoundException{
		lectureModels = new Vector<LectureModel>();
		
		Scanner scanner = new Scanner(new File(fileName));
		
		while(scanner.hasNext()) {
			//LectureModel lectureModel = new LectureModel();
			
		}
		
		return lectureModels;
	}
}