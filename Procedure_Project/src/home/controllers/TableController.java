package home.controllers;

import home.model.LectureModel;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class TableController implements Initializable{	
	@FXML TableView<LectureModel> lectureTable;
	
	@FXML TableColumn<LectureModel, Integer> numberColumn;
	@FXML TableColumn<LectureModel, String> nameColumn;
	@FXML TableColumn<LectureModel, String> professorColumn;
	@FXML TableColumn <LectureModel, Integer>creditColumn;
	@FXML TableColumn <LectureModel, String>timeColumn;	
	
	private ObservableList<LectureModel> lectureList= FXCollections.observableArrayList();
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		loadLecture();
	}
	
	private ObservableList<LectureModel> lectureModel = FXCollections.observableArrayList(
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
