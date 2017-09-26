package pl.edu.osp;

import javafx.scene.canvas.*;
import javafx.scene.control.*;
import javafx.application.*;
import javafx.scene.*;
import javafx.stage.*;
import javafx.scene.layout.*;

public class MainApp extends Application {
	
	private Label lab1  = new Label("Maksimum");
	private Label lab2 = new  Label("Krok");
	private Canvas can = new Canvas();
	final Spinner<Integer> spin1 = new Spinner<Integer>();
	final Spinner<Integer> spin2 = new Spinner<Integer>();
	
	SpinnerValueFactory<Integer> spinVal1 = 
            new SpinnerValueFactory.IntegerSpinnerValueFactory(1000, 1000000, 1000, 1000);
	SpinnerValueFactory<Integer> spinVal2 = 
            new SpinnerValueFactory.IntegerSpinnerValueFactory(100, 1000, 100, 100);
	
	public static void main(String[] args) {
		System.out.println("Start aplikacji JavaFX");
		launch(args);
	}
	
	public void init() {
		System.out.println("Inicjowanie aplikacji");
	}
	public void start(Stage stage) {
		spin1.setValueFactory(spinVal1);
		spin2.setValueFactory(spinVal2);
		FlowPane root = new FlowPane();
		root.getChildren().addAll(lab1, lab2, can, spin1, spin2);
		stage.setTitle("Apliakcja javaFX");
		Scene scene = new Scene(root, 300, 300);
		
		stage.setScene(scene);
		stage.show();
	}
	
	public void stop() {
		System.out.println("Zatrzymanie aplikacji");
	}
}
