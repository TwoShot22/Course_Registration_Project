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
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class TableController implements Initializable{	
	// Directory
	@FXML ListView<String> campusList;
	@FXML ListView<String> collegeList;
	@FXML ListView<String> departmentList;
	
	private Vector<DirectoryModel> directoryModels;
	private ObservableList<String> listItems;
	
	private ObservableList<String> campusListItems;
	private ObservableList<String> collegeListItems;
	private ObservableList<String> departmentListItems;
	
	private String path= "";
	private String campusPath = "";
	private String collegePath = "";
	private String departmentPath = "";
	
	private Boolean campusCheck = false;
	private Boolean collegeCheck = false;
	private Boolean departmentCheck = false;
	
	// Lecture
	@FXML TableView<LectureModel> lectureTable;
	
	@FXML TableColumn<LectureModel, Integer> numberColumn;
	@FXML TableColumn<LectureModel, String> nameColumn;
	@FXML TableColumn<LectureModel, String> professorColumn;
	@FXML TableColumn <LectureModel, Integer> creditColumn;
	@FXML TableColumn <LectureModel, String> timeColumn;
	
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
		//loadLecture();
	}

	// Directory Methods
	private void campusDirectory() {
		this.campusListItems = this.listItems;
		this.campusList.setItems(this.campusListItems);
		this.campusList.getSelectionModel().selectedIndexProperty().addListener(new ChangeListener<Number>() {
			@Override
			public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number campusValue) {
				// TODO Auto-generated method stub
				try {
					refresh(campusValue.intValue(),"CAMPUS");
				} catch (FileNotFoundException e) {
					e.printStackTrace();
				}
			}
		});
		System.out.println("[[[Campus On]]]");
	}
	
	private void collegeDirectory() {
		this.collegeListItems = this.listItems;
		this.collegeList.setItems(this.collegeListItems);			
		this.collegeList.getSelectionModel().selectedIndexProperty().addListener(new ChangeListener<Number>() {
			@Override
			public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number collegeValue) {
				// TODO Auto-generated method stub
				try {
					refresh(collegeValue.intValue(),"COLLEGE");
				} catch (FileNotFoundException e) {
					e.printStackTrace();
				}
			}
		});
		System.out.println("[[[College On]]]");
	}
	
	private void departmentDirectory() {
		this.departmentListItems = this.listItems;
		this.departmentList.setItems(this.departmentListItems);
		this.departmentList.getSelectionModel().selectedIndexProperty().addListener(new ChangeListener<Number>() {
			@Override
			public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number departmentValue) {
				// TODO Auto-generated method stub
				try {
					refresh(departmentValue.intValue(),"DEPARTMENT");
				} catch (FileNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		System.out.println("[[[Department On]]]");
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
				
		this.listItems.clear();
		
		for(DirectoryModel directoryModel: directoryModels) {
			this.listItems.add(directoryModel.getName());
		}
		
		return this.directoryModels.get(0).getHyperLink();
	}
	
	// Get SelectedIndex from ChangeValue && Change Directory
	private void refresh(Object source, String path) throws FileNotFoundException {
		int item = (int)source;
		this.path = path;
		String hyperLink = this.directoryModels.get(item).getHyperLink();
		
		if(path=="CAMPUS") {
			this.campusPath = hyperLink;
			this.collegePath = "";
			this.departmentPath = "";
			
			this.getDirectory(this.campusPath);
			this.collegeDirectory();
		} else if(path=="COLLEGE") {
			this.collegePath = hyperLink;
			this.departmentPath = "";
			
			this.getDirectory(this.collegePath);
			this.departmentDirectory();
		} else if(path=="DEPARTMENT") {
			this.departmentPath = hyperLink;
			this.loadLecture();
		} else {
			System.out.println("ERROR");
		}
		
		System.out.println("Method : "+hyperLink);
		System.out.println("Path : "+path);
		System.out.println("Campus : "+this.campusPath);
		System.out.println("College : "+this.collegePath);
		System.out.println("Department : "+this.departmentPath);
	}
	
	
	// Lecture Methods
	private ObservableList<LectureModel> lectureModel = FXCollections.observableArrayList(
			// Dummy Data
			new LectureModel(3214,"객체지향적사고와프로그래밍","최성운",3,"월수9:00-10:30"),
			new LectureModel(3216,"시스템프로그래밍","김일주",3,"회목14:00-15:30")
	);
	
	private void loadLecture() {
		numberColumn.setCellValueFactory(new PropertyValueFactory<>("Number"));
		nameColumn.setCellValueFactory(new PropertyValueFactory<>("Name"));
		professorColumn.setCellValueFactory(new PropertyValueFactory<>("Professor"));
		creditColumn.setCellValueFactory(new PropertyValueFactory<>("Credit"));
		timeColumn.setCellValueFactory(new PropertyValueFactory<>("Time"));
		
		lectureTable.setItems(lectureModel);
	}
}