package home.controllers;

import home.model.DirectoryModel;
import home.model.LectureModel;

import java.beans.EventHandler;
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
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class TableController implements Initializable{	
	@FXML ComboBox<String> campusSelect;
	@FXML ComboBox<DirectoryModel> collegeSelect;
	@FXML ComboBox<DirectoryModel> departmentSelect;

	@FXML TableView<LectureModel> lectureTable;
	
	@FXML TableColumn<LectureModel, Integer> numberColumn;
	@FXML TableColumn<LectureModel, String> nameColumn;
	@FXML TableColumn<LectureModel, String> professorColumn;
	@FXML TableColumn <LectureModel, Integer> creditColumn;
	@FXML TableColumn <LectureModel, String> timeColumn;
	
	private Vector<DirectoryModel> directoryModels;
	private ObservableList<String> campusList = FXCollections.observableArrayList("용인캠퍼스","서울캠퍼스");
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		loadLecture();
		
		campusSelect.setItems(campusList);
//		campusSelect.setItems(FXCollections.observableArrayList());
//
//		campusSelect.getItems().add(new DirectoryModel("용인캠퍼스"));
//		campusSelect.getItems().add(new DirectoryModel("서울캠퍼스"));
		
		campusSelect.setOnMouseClicked((mouseEvent)->{
			Object source = campusSelect.getSelectionModel().getSelectedItem();
			
		});
		
		collegeSelect.setOnMouseClicked((mouseEvent)->{
			
		});
		
		departmentSelect.setOnMouseClicked((mouseEvent)->{
			
		});
	}
	
	// Directory Methods
	public Vector<DirectoryModel> getDirectory(String fileName) throws FileNotFoundException{
		Vector<DirectoryModel> directoryModels = new Vector<DirectoryModel>();
		
		Scanner scanner = new Scanner(new File(fileName));
		
		while(scanner.hasNext()) {
			DirectoryModel directoryModel = new DirectoryModel();
			directoryModel.read(scanner);
			directoryModels.add(directoryModel);
		}
		scanner.close();
		return directoryModels;
	}
	
//	public String getSelectedFileName() {
//		int selectedIndex = this.getSelectedIndex();
//		return this.directoryModels.get(selectedIndex).getHyperLink();
//	}
	
	public String refresh(String fileName) throws FileNotFoundException {
		this.directoryModels = this.getDirectory("data/"+fileName);
		
		for(DirectoryModel directoryModel: directoryModels) {
		//	this.listData.add(directoryModel.getName());
		}
		
		return this.directoryModels.get(0).getHyperLink();
	}
	
	// Lecture Methods
	private ObservableList<LectureModel> lectureModel = FXCollections.observableArrayList(
			// Dummy Data
			new LectureModel(3214,"객체지향적사고와프로그래밍","최성운",3,"월수9:00-10:30")
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
