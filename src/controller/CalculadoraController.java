package controller;

import java.io.File;
import java.net.URL;
import java.util.Locale;
import java.util.ResourceBundle;

import excepciones.DivisionPorCeroException;
import excepciones.RaizNegativaException;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCodeCombination;
import javafx.scene.input.KeyCombination;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Modality;
import javafx.stage.Stage;
import modelo.Calculadora;
import utilidades.I18N;




public class CalculadoraController implements Initializable{

	

    @FXML
    private Button BARRA;

    @FXML
    private Button BUTTON_MC;

    @FXML
    private Button BUTTON_MINUS_M;

    @FXML
    private Button BUTTON_MR;

    @FXML
    private Button BUTTON_MS;

    @FXML
    private Button BUTTON_PLUS_M;

    @FXML
    private Button CLEAR;

    @FXML
    private Button CLEARError;

    @FXML
    private Button COMMA;

    @FXML
    private Button DIGIT0;

    @FXML
    private Button DIGIT1;

    @FXML
    private Button DIGIT2;

    @FXML
    private Button DIGIT3;

    @FXML
    private Button DIGIT4;

    @FXML
    private Button DIGIT5;

    @FXML
    private Button DIGIT6;

    @FXML
    private Button DIGIT7;

    @FXML
    private Button DIGIT8;

    @FXML
    private Button DIGIT9;

    @FXML
    private Button EQUAL;

    @FXML
    private Button FLECHA;

    @FXML
    private Button OneX;

    @FXML
    private Button PLUS;

    @FXML
    private Button PORCENTAGE;

    @FXML
    private Button RAIZ;

    @FXML
    private Button SIGNS;

    @FXML
    private TextField barra_superior;
    
    @FXML
    private Button MINUS;

    @FXML
    private Button PER;
    
    @FXML
    private MenuItem menu_Historial;
    
    @FXML
    private MenuItem menu_Salir;
    
    @FXML
    private MenuItem menu_Ayuda;
    
    @FXML
    private MenuItem menu_Copiar;
    
    @FXML
    private MenuItem menu_Pegar;
    
    @FXML
    private MenuItem menu_AcercaDe;
    
    @FXML
    private MenuItem ver;
    
    @FXML
    private MenuItem edicion;
    
    @FXML
    private MenuItem ayuda;
    
    @FXML
    private MenuItem idiomas;
    
    @FXML
    private MenuItem menu_ES;
    
    @FXML
    private MenuItem menu_EN;
    
    @FXML
    private MenuItem menu_CA;
    
    @FXML
    void pulTecla(KeyEvent event) throws DivisionPorCeroException {
    	switch (event.getCode()) {
		case DIGIT0:
		case NUMPAD0:
			insertarNumero("0");
			break;
		case DIGIT1:
		case NUMPAD1:
			insertarNumero("1");
			break;
		case DIGIT2:
		case NUMPAD2:
			insertarNumero("2");
			break;
		case DIGIT3:
		case NUMPAD3:
			insertarNumero("3");
			break;
		case DIGIT4:
		case NUMPAD4:
			insertarNumero("4");
			break;
		case DIGIT5:
		case NUMPAD5:
			insertarNumero("5");
			break;
		case DIGIT6:
		case NUMPAD6:
			insertarNumero("6");
			break;
		case DIGIT7:
		case NUMPAD7:
			insertarNumero("7");
			break;
		case DIGIT8:
		case NUMPAD8:
			insertarNumero("8");
			break;
		case DIGIT9:
		case NUMPAD9:
			insertarNumero("9");
			break;
		case ADD:
		case PLUS:
			asignarOperacionController("+");
			break;
		case MINUS:
		case SUBTRACT:
			asignarOperacionController("-");
			break;
		case ENTER:
		case FINAL:
			calcular();
			break;
        }
    
	    KeyCombination cntrlV = new KeyCodeCombination(KeyCode.V, KeyCodeCombination.CONTROL_DOWN);
	    KeyCombination cntrlC = new KeyCodeCombination(KeyCode.C, KeyCodeCombination.CONTROL_DOWN);

    	
	    KeyCombination division = new KeyCodeCombination(KeyCode.DIGIT7, KeyCodeCombination.SHIFT_DOWN);
	    KeyCombination division2 = new KeyCodeCombination(KeyCode.NUMPAD7, KeyCodeCombination.SHIFT_DOWN);

	    KeyCombination multiplicacion = new KeyCodeCombination(KeyCode.ADD, KeyCodeCombination.SHIFT_DOWN);
	    KeyCombination multiplicacion2 = new KeyCodeCombination(KeyCode.PLUS, KeyCodeCombination.SHIFT_DOWN);
	    
	    KeyCombination memoryClear = new KeyCodeCombination(KeyCode.L, KeyCodeCombination.CONTROL_DOWN);
	    KeyCombination memoryRecall = new KeyCodeCombination(KeyCode.R, KeyCodeCombination.CONTROL_DOWN);
	    KeyCombination memoryStorage = new KeyCodeCombination(KeyCode.M, KeyCodeCombination.CONTROL_DOWN);
	    KeyCombination memoryPlus = new KeyCodeCombination(KeyCode.P, KeyCodeCombination.CONTROL_DOWN);
	    KeyCombination memoryMinus = new KeyCodeCombination(KeyCode.Q, KeyCodeCombination.CONTROL_DOWN);
	    KeyCombination memoryCE = new KeyCodeCombination(KeyCode.DELETE);
	    KeyCombination memoryC = new KeyCodeCombination(KeyCode.ESCAPE);


	    if (cntrlV.match(event)) {
			calculadora.pegar();
			mostrarInformacion();
		}

	    if (cntrlC.match(event)) {
			calculadora.copiar();
		}
	    
	    if (multiplicacion.match(event) || multiplicacion2.match(event)) {
			calculadora.asignarOperacion("*");
		}
	    
	    if (division.match(event) || division2.match(event)) {
			calculadora.asignarOperacion("/");
		}
	    
	    if (memoryClear.match(event)) {
			calculadora.memoryClear();
		}
	    
	    if (memoryRecall.match(event)) {
			calculadora.memoryRecall();
		}
	    
	    if (memoryStorage.match(event)) {
			calculadora.memoryStorage();
		}
	    
	    if (memoryPlus.match(event)) {
			calculadora.memorySumar();
		}
	    
	    if (memoryMinus.match(event)) {
			calculadora.memoryRestar();
		}
	    
	    if (memoryCE.match(event)) {
	    	calculadora.clearError();
		}
	    
	    if (memoryC.match(event)) {
	    	calculadora.clear();
		}
	    
    }
   
    
    public Calculadora calculadora = new Calculadora();


    
    public Calculadora giveCalculadora() {
    	return calculadora;
    }
    
//    @FXML 
//    void setCastellano(MouseEvent event) {
//    	I18N.setLocale(new Locale("es"));
//    }
//
//    @FXML 
//    void setIngles(MouseEvent event) {
//    	I18N.setLocale(new Locale("en"));
//    }
//
//    @FXML
//    void setValenciano(MouseEvent event) {
//    	I18N.setLocale(new Locale("ca"));
//    }
	static Stage menu_ay;

	@Override
	public void initialize(URL url, ResourceBundle resourceBundle) {

		menu_AcercaDe.textProperty().bind(I18N.createStringBinding("calculadora.menu_AcercaDe"));
		menu_Historial.textProperty().bind(I18N.createStringBinding("calculadora.menu_Historial"));
		menu_Salir.textProperty().bind(I18N.createStringBinding("calculadora.menu_Salir"));
		menu_Copiar.textProperty().bind(I18N.createStringBinding("calculadora.menu_Copiar"));
		menu_Pegar.textProperty().bind(I18N.createStringBinding("calculadora.menu_Pegar"));
		menu_Ayuda.textProperty().bind(I18N.createStringBinding("calculadora.menu_Ayuda"));
		ver.textProperty().bind(I18N.createStringBinding("calculadora.ver"));
		edicion.textProperty().bind(I18N.createStringBinding("calculadora.edicion"));
		ayuda.textProperty().bind(I18N.createStringBinding("calculadora.ayuda"));
		idiomas.textProperty().bind(I18N.createStringBinding("calculadora.idiomas"));
		
		 //LABEL
		barra_superior.setText("0");
		//cancionPlay();
		// Asignar los Eventos de las opciones de menu
		//menu_Salir.setOnAction((event) -> salir());
		
		// NUMEROS
		DIGIT0.setOnMouseClicked((event) -> insertarNumero("0"));
		DIGIT1.setOnMouseClicked((event) -> insertarNumero("1"));
		DIGIT2.setOnMouseClicked((event) -> insertarNumero("2"));
		DIGIT3.setOnMouseClicked((event) -> insertarNumero("3"));
		DIGIT4.setOnMouseClicked((event) -> insertarNumero("4"));
		DIGIT5.setOnMouseClicked((event) -> insertarNumero("5"));
		DIGIT6.setOnMouseClicked((event) -> insertarNumero("6"));
		DIGIT7.setOnMouseClicked((event) -> insertarNumero("7"));
		DIGIT8.setOnMouseClicked((event) -> insertarNumero("8"));
		DIGIT9.setOnMouseClicked((event) -> insertarNumero("9"));
		
		//Las Ms
		
		BUTTON_MC.setOnMouseClicked((event) -> lasMs("BUTTON_MC"));
		BUTTON_MINUS_M.setOnMouseClicked((event) -> lasMs("BUTTON_MINUS_M"));
		BUTTON_MR.setOnMouseClicked((event) -> lasMs("BUTTON_MR"));
		BUTTON_MS.setOnMouseClicked((event) -> lasMs("BUTTON_MS"));
		BUTTON_PLUS_M.setOnMouseClicked((event) -> lasMs("BUTTON_PLUS_M"));


		
		//IGUAL
		EQUAL.setOnMouseClicked((event) -> calcular());
		
		//OPERACIONES
		PLUS.setOnMouseClicked((event) -> asignarOperacionController("+"));
		MINUS.setOnMouseClicked((event) -> asignarOperacionController("-"));
		PER.setOnMouseClicked((event) -> asignarOperacionController("*"));
		BARRA.setOnMouseClicked((event) -> asignarOperacionController("/"));
		SIGNS.setOnMouseClicked((event) -> cambiarSigno());
		PORCENTAGE.setOnMouseClicked((event) -> {
			try {
				porcentage();
			} catch (DivisionPorCeroException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		});


		RAIZ.setOnMouseClicked((event) -> {
			try {
				raiz();
			} catch (RaizNegativaException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		});
		
		OneX.setOnMouseClicked((event) -> {
			try {
				inversa();
			} catch (DivisionPorCeroException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		});


		//COMMA
		COMMA.setOnMouseClicked((event) -> insertarNumero("."));
		
		//LIMPIADOS
		FLECHA.setOnMouseClicked((event) -> retroceder());
		CLEAR.setOnMouseClicked((event) -> clear());
		CLEARError.setOnMouseClicked((event) -> clearError());


		//+++MENUS++++
		

		menu_Ayuda.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent arg0) {
				//mostrarAdmin();
				menu_ay=mostrarVentana("/vista/ayuda.fxml", "Ayuda", 0);
			}
		});
		
		menu_Historial.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent arg0) {
				calculadora.historial();
				mostrarVentana("/vista/historial.fxml", "Historial log", 1);
			}
		});
		
		menu_Salir.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent arg0) {
				final Stage stage = (Stage) menu_Salir.getParentPopup().getOwnerWindow();
				stage.close();
			}
		});
		
		menu_AcercaDe.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent arg0) {
				mostrarVentana("/vista/acercaDe.fxml", "Acerca de la calculadora", 0);
			}
		});

		menu_Copiar.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent arg0) {
				calculadora.copiar();
			}
		});
		
		menu_Pegar.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent arg0) {
				calculadora.pegar();
				mostrarInformacion();
			}
		});
		
		menu_ES.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent arg0) {
		    	I18N.setLocale(new Locale("es"));
			}
		});
		
		menu_EN.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent arg0) {
		    	I18N.setLocale(new Locale("en"));
			}
		});
		
		menu_CA.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent arg0) {
		    	I18N.setLocale(new Locale("ca"));
			}
		});
		
	}

	
	public void lasMs(String M) {
		
		switch (M) {
		case "BUTTON_MC":
			calculadora.memoryClear();
			break;

		case "BUTTON_MINUS_M":
			calculadora.memoryRestar();
			barra_superior.setText(calculadora.getNumActual());
			break;
			
		case "BUTTON_MR":
			calculadora.memoryRecall();
			barra_superior.setText(calculadora.getNumActual());
			break;
			
		case "BUTTON_MS":
			calculadora.memoryStorage();
			break;
			
		case "BUTTON_PLUS_M":
			calculadora.memorySumar();
			barra_superior.setText(calculadora.getNumActual());
			break;

		default:
			barra_superior.setText("Las M's han fallado");
			break;
		}

	}
	
	private void insertarNumero(String numero){
		
		calculadora.insertarNumero(numero);
		mostrarInformacion();
		
	}
	
	private void mostrarInformacion() {
		if (calculadora.getNumActual().equals("")) {
			barra_superior.setText("0");
		}
		else {
			
			barra_superior.setText(calculadora.getNumActual());
		}
		
	}

	private void asignarOperacionController(String operacion){
		//TODO Arreglar
		try {
			calculadora.asignarOperacion(operacion);
		} catch (DivisionPorCeroException e) {
			e.printStackTrace();
		}
		barra_superior.setText(operacion);
	}
	
	private void retroceder(){
		calculadora.retroceder();
		mostrarInformacion();
		
	}
	
	private void porcentage() throws DivisionPorCeroException{
		calculadora.porcentaje();
		mostrarInformacion();
	}
	
		
	
	
	private void cambiarSigno(){
		calculadora.cambiarSigno();
		
		mostrarInformacion();
	}

	private void calcular() {
		try {
			calculadora.calcular(false);
		} catch (DivisionPorCeroException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		mostrarInformacion();
		
	}
	
	private void raiz() throws RaizNegativaException {
		
		try {
			calculadora.raiz();
		} catch (RaizNegativaException e) {
			barra_superior.setText("Raiz negativa");
		}

		
	}
	private void inversa() throws DivisionPorCeroException {
		calculadora.inversa();
		mostrarInformacion();
	}
	
	private void clearError() {
		barra_superior.setText("");
	}
	
	private void clear() {
		calculadora.clear();
		barra_superior.setText("0");
		
		
//		calculadora.setNum1(0);
//		calculadora.setNum2(0);
//		calculadora.setNumActual("");
//		calculadora.setOperacion("");
	
	}
	
	
	private void refrescarTextoResultado() {
		
	}
	

	
	public Stage mostrarVentana(String rutaFXML, String titulo, int n) {
		try{
			//Léeme el source del archivo que te digo fxml y te pongo el path
			FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(rutaFXML));
			Parent root = (Parent) fxmlLoader.load();
			//Creame un nuevo Stage (una nueva ventana vacía)
			Stage stage = new Stage();
			//Asignar al Stage la escena que anteriormente hemos leído y guardado en root
			stage.setTitle(titulo);
			stage.setResizable(false);
			if (n == 1) {
				stage.initModality(Modality.APPLICATION_MODAL);
			}else {
				stage.initModality(Modality.NONE);
			}
			stage.setScene(new Scene(root));
			stage.getIcons().add(new Image(getClass().getResource("/vista/icon.png").toExternalForm()));
			//Mostrar el Stage (ventana)
			stage.show();
			return stage;
		}
		catch (Exception e){
			e.printStackTrace();
		}
		return null;
	}
	

//	public void cancionPlay() {
//		// Create a Media object with the file path
//        Media media = new Media(new File("cancion.mp3").toURI().toString());
//        
//        // Create a MediaPlayer with the Media object
//        MediaPlayer mediaPlayer = new MediaPlayer(media);
//        
//        // Play the audio
//        mediaPlayer.play();
//	}
	
}
