package controller;

import java.io.FileNotFoundException;
import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.Scanner;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextArea;

public class HistorialController implements Initializable{

	@FXML
	private TextArea historial_area;
	private ArrayList<String> historial = new ArrayList<String>();


	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {

		leerFichero("./src/historial.txt");


	}

		public void leerFichero(String fichero) {

			Scanner sc;

			try {
				sc = new Scanner(new File(fichero));
				
				while (sc.hasNextLine()) {
					String linea = sc.nextLine();
					System.out.println("La linea "+ linea);
					historial.add(linea);
				}

			} catch (FileNotFoundException e) {
				System.out.println("El fichero no se encuentra");

			}

			
			for (int i = 0; i < historial.size(); i++) {
				for (String linea : historial) {
		            historial_area.appendText(linea + "\n");
		        }
			}
		}
		
		



}
