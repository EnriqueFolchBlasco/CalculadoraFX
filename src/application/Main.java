package application;

import controller.CalculadoraController;
import controller.HistorialController;
import javafx.application.Application;
import javafx.stage.Modality;
import javafx.stage.Stage;
import modelo.Calculadora;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.fxml.FXMLLoader;
 

public class Main extends Application {
	@Override
	public void start(Stage primaryStage) {
		try {
			String fxml = "vista/calculadora.fxml";
			// Cargar la ventana
			Parent root = FXMLLoader.load(getClass().getClassLoader().getResource(fxml));
			// Cargar la Scene
			Scene scene = new Scene(root);
			// Asignar propiedades al Stage
			primaryStage.setTitle("Barbie Calculadora");
			primaryStage.getIcons().add(new Image(getClass().getResource("/vista/icon.png").toExternalForm()));
			primaryStage.setResizable(false);
			// Asignar la scene y mostrar
			primaryStage.setScene(scene);
			primaryStage.show();
			//scene.getStylesheets().add("/vista/aplication.css");

		} catch(Exception e) {
			e.printStackTrace();
		}


	}





}
