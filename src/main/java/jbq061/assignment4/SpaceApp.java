package jbq061.assignment4;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class SpaceApp extends Application {
	@Override
	public void start(Stage stage) {
		MainUI root = new MainUI();
		Scene scene = new Scene(root);
		stage.setTitle("Theophilus Nyasha Nenhanga - Assignment 4");
		stage.setScene(scene);
		stage.show();
		root.setFocus();
	}

	public static void main(String[] args) {
		launch();
	}
}