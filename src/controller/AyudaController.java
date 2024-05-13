package controller;

import java.awt.event.ActionEvent;
import java.net.URL;
import java.util.Calendar;
import java.util.ResourceBundle;

import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import modelo.Calculadora;

public class AyudaController implements Initializable{

	 @FXML
	    private Label label_hora;

	    @FXML
	    private Text num1;

	    @FXML
	    private Text num2;

	    @FXML
	    private Text numActual;

	    @FXML
	    private Text numMemoria;

	    @FXML
	    private Text operacion;

	    @FXML
	    private Text texto_tocho;
	    
	    @FXML
	    private ImageView icona;
	
	String texto2 = "Opciones de los botones: /n MC (Memory Clear):Elimina cualquier número almacenado en memoria. /n"
			+ "Combinación de teclas: CTRL+LMR (Memory Recall):Recupera el número almacenado en memoria./n"
			+ "Combinación de teclas: CTRL+RMS (Memory Storage):Almacena en memoria el número mostrado./n"
			+ "Combinación de teclas: CTRL+MM+:Suma el número mostrado a otro número que se encuentre en memoria pero no muestra la suma de estos números./n"
			+ "Combinación de teclas: CTRL+PM-:Resta el número mostrado a otro número que se encuentre en memoria pero no muestra la resta de estos números./n"
			+ "Combinación de teclas: CTRL+QCE (Clear error):Elimina el número mostrado. Se utiliza para cuando se comete un error en el ingreso de datos pero sin eliminar todo el calculo que se encuentra realizando./n"
			+ "Combinación de teclas: SUPRIMIRC (Clear):Elimina todo el cálculo actual.Combinación de teclas: ESC";
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		//texto_tocho.setWrapText(true);
		hora();
		datos();
		texto_tocho.setText(texto2);
		easterEgg();
		
	}

	int minute;
	int hour;
	int second;
	
	private void hora() {
		//es un lambda, funcio = sense nom pero lo mismo
		Thread clock = new Thread(()->{
		    while (true) {
		        Calendar cal = Calendar.getInstance();
		        second = cal.get(Calendar.SECOND);
		        minute = cal.get(Calendar.MINUTE);
		        hour = cal.get(Calendar.HOUR);
		        Platform.runLater(()->{label_hora.setText(hour + ":" + (minute) + ":" + second);});
		        try {
		            Thread.sleep(1000);
		        } catch (InterruptedException ex) {
		            
		        }
		    }
		});
		clock.start();
		
	}
	
	public void datos() {
		Thread datos = new Thread(()->{
			while(true) {
				num1.setText(Calculadora.getCalculadora().getNum1()+"");
				num2.setText(Calculadora.getCalculadora().getNum2()+"");
				numActual.setText(Calculadora.getCalculadora().getNumActual());
				numMemoria.setText(Calculadora.getCalculadora().getNumMemoria());
				operacion.setText(Calculadora.getCalculadora().getOperacion());
				
			}
		});
		datos.start();
	}
	
	
	public Thread easterEgg() {
		
		Thread easterEgg = new Thread(()->{
			float t = 1;
			boolean r = false;

			while(true) {

				if (Calculadora.getCalculadora().getNumActual().equals("777")) {
					//System.out.println(Calculadora.getCalculadora().getNumActual()+"sexo");
					//System.out.println(t);
					if (t >= 0) {
						if (t>=1) {
							r = false;
						}
					}else {
						r = true;
					}
					if (!r) {
						t -= 0.15;
					} else {
						t += 0.15;
					}
					icona.setOpacity(lerp(0,1,t));
					try {
						Thread.sleep(100);
					} catch (InterruptedException e) {
						System.out.println("bomba");
						e.printStackTrace();
					}
				}else {
					icona.setOpacity(0);
				}
			}
		});
		easterEgg.start();
		return easterEgg;
	}
	
//	private void tanca() {
//		var t= easterEgg();
//		CalculadoraController.menu_ay.setOnCloseRequest(new EventHandler<WindowEvent>() {
//			public void handle(WindowEvent me) {
//				t.interrupt();
//			}
//		});
//	}
	
	float lerp(float v0, float v1, float t) {   return (1 - t) * v0 + t * v1; }
	
//	public void test() {
//		 label_hora.setText(Calculadora.getCalculadora().getNumActual());
//	}
	
	
}
