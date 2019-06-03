package home;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		Parent login = FXMLLoader.load(getClass().getResource("fxml/Login.fxml"));
        primaryStage.setTitle("명지대학교 수강신청 시스템");
        primaryStage.setScene(new Scene(login));
        primaryStage.show();
	}

	public static void main(String[] args) {
		launch(args);
	}

}
