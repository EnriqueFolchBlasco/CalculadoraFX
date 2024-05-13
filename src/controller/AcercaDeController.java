package controller;

import java.net.URL;
import java.util.Random;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextArea;
import javafx.scene.layout.AnchorPane;
import modelo.Calculadora;

public class AcercaDeController implements Initializable{
	
    @FXML
    private AnchorPane menu_AcercaDe;

    @FXML
    private TextArea text_area;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		text_area.setWrapText(true);
		text_area.setText(randomizadorChistes());
		
	}
	
	public String randomizadorChistes() {
		
		String frase_seleccionada;
		int n = new Random().nextInt(5)+1;
		
		switch (n) {
		case 1:
			frase_seleccionada = "Un programador tuvo un problema, decidió usar Java. Ahora tiene una ProblemFactory.";
			break;
		case 2:
			frase_seleccionada = "¿Qué le dijo el código Java al código C? No tienes clase.";
			break;
		case 3:
			frase_seleccionada = "¿Qué es un algoritmo? Una palabra que usan los programadores cuando no quieren explicar lo que hicieron.";
			break;
		case 4:
			frase_seleccionada = "¿Cuál es la forma orientada a objetos para volverse rico? Herencia.";
			break;
		case 5:
			frase_seleccionada = "Alomillor Angel se sap algun chiste.";
			break;
		default:
			frase_seleccionada = "Hui no tens chiste"; 
			break;
		}
				
		
		return frase_seleccionada;
	}
	
	
}
