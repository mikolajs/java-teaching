package pl.edu.osp;

import javafx.application.*;
import javafx.scene.*;
import javafx.stage.*;
import javafx.scene.layout.*;

public class MainApp extends Application {

	public static void main(String[] args) {
		System.out.println("Start aplikacji JavaFX");
		launch(args);
	}
	
	public void init() {
		System.out.println("Inicjowanie aplikacji");
	}
	public void start(Stage stage) {
		FlowPane root = new FlowPane();
		stage.setTitle("Apliakcja javaFX");
		Scene scene = new Scene(root, 300, 300);
		stage.setScene(scene);
		stage.show();
	}
	
	public void stop() {
		System.out.println("Zatrzymanie aplikacji");
	}
}
