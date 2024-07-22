package modelo;

import java.util.ArrayList;
import java.util.Locale;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.format.FormatStyle;
import java.time.format.TextStyle;
import java.time.temporal.ChronoField;

import excepciones.DivisionPorCeroException;
import excepciones.RaizNegativaException;
import javafx.scene.input.Clipboard;
import javafx.scene.input.ClipboardContent;

public class Calculadora {
	//Atributos
	private static Calculadora calculadora;
	
	private double num1;
	private double num2;
	private String operacion;
	private String numActual;
	private String numMemoria;
	private ArrayList<String> historial;
	
	//Constructor
	public Calculadora() {
		setCalculadora(this);
		this.num1 = 0;
		this.num2 = 0;
		this.operacion = "";
		this.numActual = "";
		this.numMemoria = "";
		this.historial = new ArrayList<String>();
		
	} 

	//Metodos
	public void insertarNumero(String numero) {
		
		if (this.numActual.equals("0") && !numero.equals("0")) {
			this.numActual = numero;
			
		} else if ((this.numActual.contains(".%"))) {
			
			
			this.numActual = ( "0." + numero );
		}

		else if (!(this.numActual.contains(".") && numero.equals("."))) {
			
			
			this.numActual = ( numActual + numero );
		}
		
	}
	
	public void asignarOperacion(String operacion) throws DivisionPorCeroException {
		
		if (this.numActual.equals("") && operacion.equals("-")) {
			
		this.numActual = "-";
		
		}else {
			
			if (this.numActual.isEmpty()) {
				
				if (this.numActual.equals(operacion) && this.numActual.length() == 1) {
					this.numActual = operacion;
				} else {
					setOperacion(operacion);
					setNum1(StringToDouble(getNumActual()));
					setNumActual("");
				}
				
			} else {
				if (!getOperacion().equals("") && !(getNum1() == 0)) {
					calcular(false);
					quitarCeros();
				}
				
				setOperacion(operacion);
				setNum1(StringToDouble(getNumActual()));
				setNumActual("");
							
			}
		
		}
		

	}
	
	
	public void calcular(boolean b) throws DivisionPorCeroException{
		double resultado = 0;
		
//		if (this.num1 = "" && this.num2.empty()) {
//			
//		}
		
		try {
			
			if (this.num2 == 0 && this.operacion.isEmpty() && this.num1 == 0) {
				resultado = StringToDouble(this.numActual);
				
			} else {

				setNum2(StringToDouble(getNumActual()));
				switch (getOperacion()) {
				case "+":
					resultado = sumar();
					break;
					
				case "-":
					resultado = restar();
					break;
					
				case "*":
					resultado = multiplicar();
					break;
					
				case "/":
					try {
						resultado = dividir();
					} catch (DivisionPorCeroException e) {
						setNumActual("Se ha divididio por cero");
					}
					break;

				default:
					break;
				}
				this.historial.add("[ " + hora() +" ] Operacion: "+num1+ " " +operacion+ " " +num2+ " = " +resultado);
		
			}
			
		} catch (NumberFormatException e) {
			//System.out.println("algo");
		}
		
		this.operacion = "";
		setNumActual(resultado + "");
		setNum1(resultado);
//		if (!(this.numActual.contains("%.0%"))) {
//			quitarCeros();
//
//		}
		quitarCeros();

//		System.out.println("Num1: "+ num1);
//		System.out.println("Num2: "+ num2);
//		System.out.println("NumActual: "+ numActual);
		
	}
	
	public void quitarCeros() {
		
//		if (this.getNumActual().endsWith(".0")) {
//			setNumActual(numActual.substring(0,numActual.length()-2));
//		}
		
		if (this.getNumActual().contains(".0")) {
			setNumActual(numActual.substring(0,numActual.length()-2));
		}
				
	}
	
	public void clear() {
		this.num1 = 0;
		this.num2 = 0;
		this.operacion = "";
		this.numActual = "";
		
	}
	
	public void retroceder(){

		if (this.getNumActual().length() > 0) {
			this.numActual = this.getNumActual().substring(0, this.getNumActual().length()-1);
		} 
		
	}
	
	public double StringToDouble(String string) {
		return Double.parseDouble(string);
	}
	
	//Operaciones aritméticas
	
	public double sumar(){
		
		// BigDecimal sum = x.add(y);
		
		BigDecimal num1_Big = BigDecimal.valueOf(this.num1);
		BigDecimal num2_Big = BigDecimal.valueOf(this.num2);
		
		BigDecimal resultado = num1_Big.add(num2_Big);
		return resultado.doubleValue();
		
	}
	
	public double restar(){
		
		BigDecimal B1 = BigDecimal.valueOf(this.num1);
		BigDecimal B2 = BigDecimal.valueOf(this.num2);
		
		double resultado = B1.subtract(B2).doubleValue();
		
		return resultado;
	}
	
	public double multiplicar(){
		
		BigDecimal num1_Big = BigDecimal.valueOf(this.num1);
		BigDecimal num2_Big = BigDecimal.valueOf(this.num2);
		
		BigDecimal resultado = num1_Big.multiply(num2_Big);
		return resultado.doubleValue();
	}
	
	public void clearError() {
		this.numActual = "0";
	}

	public double dividir() throws DivisionPorCeroException{
		
		double resultado = this.num1 / this.num2;
		//SI divide por cero lanzar excepcion
		if (Double.toString(resultado).equals("Infinity")) {
			throw new DivisionPorCeroException();
		}
		return resultado;
		
	}

	public void porcentaje() throws DivisionPorCeroException {
		Double aux = Double.parseDouble(numActual);
        aux /= 100;


        if (this.num1 == 0) {
            this.numActual = aux + "";
        }

        else if (this.operacion.equals("") || this.operacion.equals("/")) {

            this.numActual = aux + "";

        }
        else {
            this.numActual = (this.num1*aux) + "";
        }

        calcular(false);

	}
	
	public double raiz() throws RaizNegativaException {
		
		if (!(this.numActual.equals(""))) {
			
			//NumActual pasa de string a double
			double n = Double.parseDouble(this.numActual);
			
			//Numero negativo == raiz negativa
			if (n < 0) {
				throw new RaizNegativaException();
			}
			
			//Calculo raiz que hace  java
			numActual = Double.toString(Math.sqrt(n));
			this.historial.add("Raiz de " + n + "da: " + this.numActual);
			quitarCeros();
			return Math.sqrt(n);
		} else {
			return 0;
		}
		
	}

	public void inversa() throws DivisionPorCeroException{
		if (!(this.numActual.equals(""))) {
			//Divide uno entre el numero actual
			double numerito = Double.parseDouble(numActual);
			
			//LOcurooooooon
			String resultado = Double.toString(1 / numerito);
//			this.numActual = Double.toString(1 / numerito);
			if (resultado.equals("Infinity")) {
				this.numActual = "0";
			} else {
				this.numActual = resultado;
			}
			
			this.historial.add("La inversa de " + numerito + "da: " + this.numActual);
			quitarCeros();
		}
	}
	
	
//	//Cambiar signo al valor actual
	
	public void cambiarSigno(){
		double resultat = 0;
		double pepito = StringToDouble(this.numActual);
		
		if (pepito != 0) {
			resultat = pepito * -1;
			this.numActual = resultat + "";
		}
		quitarCeros();

	}
	
	public void memoryClear() {
		//MC
		this.numMemoria = "";
	}
	
	public void memoryRecall() {
		//MR
		//return StringToDouble(this.numMemoria);
		this.numActual = this.numMemoria;
	}
	
	public void memoryStorage() {
		//MS
		this.numMemoria = this.numActual;
	}
	
	public void memorySumar() {
		
		if (!(this.numActual.equals(""))) {
			//MP: Suma el numero que se muestra al de la memoria y va al num1
			double calculo = StringToDouble(this.numActual) + StringToDouble(this.numMemoria);
			this.numActual = calculo + "";
			quitarCeros();
		}
		

	}
	
	public void memoryRestar() {
		
		if (!(this.numActual.equals(""))) {
			//MM:  Suma el numero que se muestra al de la memoria y va al num1
			double calculo = StringToDouble(this.numActual) - StringToDouble(this.numMemoria);
			this.numActual = calculo + "";
			quitarCeros();
		}

	}
	
	public void ClearError() {
		this.numActual = "";
	}
	
//	//Concatenar número al numActual

	
	public void copiar(){
		Clipboard clipboard = Clipboard.getSystemClipboard();
		ClipboardContent content = new ClipboardContent();
		content.putString(this.numActual);
		clipboard.setContent(content);
	}
	
	public void pegar(){
		Clipboard clipboard = Clipboard.getSystemClipboard();
		this.numActual = clipboard.getString();
	}

	
	
	//Getters y Setters

	public double getNum1() {
		return num1;
	}

	public void setNum1(double num1) {
		this.num1 = num1;
	}

	public double getNum2() {
		return num2;
	}

	public void setNum2(double num2) {
		this.num2 = num2;
	}

	public String getOperacion() {
		return operacion;
	}

	public void setOperacion(String operacion) {
		this.operacion = operacion;
	}

	public String getNumMemoria() {
		return numMemoria;
	}

	public void setNumMemoria(String numMemoria) {
		this.numMemoria = numMemoria;
	}

	public String getNumActual() {
		return numActual;
	}

	public void setNumActual(String numActual) {
		this.numActual = numActual;
	}
	
	public void historial() {
		try {
			FileWriter file = new FileWriter("src/historial.txt");
			PrintWriter pw = new PrintWriter(file);
			for (String string : historial) {
				pw.println(string);
			}

			pw.close();
		} catch (IOException e1) {

			//System.out.println("Error" + e1.getMessage());
		}

	}
	
	   public String hora() {
	        ZoneId madridZone = ZoneId.of("Europe/Madrid");
	        ZonedDateTime madridTime = ZonedDateTime.now(madridZone);
	        DateTimeFormatter formatter = new DateTimeFormatterBuilder()
	                .appendText(ChronoField.DAY_OF_WEEK, TextStyle.FULL)
	                .appendLiteral(", ")
	                .appendValue(ChronoField.DAY_OF_MONTH)
	                .appendLiteral(" de ")
	                .appendText(ChronoField.MONTH_OF_YEAR, TextStyle.FULL)
	                .appendLiteral(" de ")
	                .appendValue(ChronoField.YEAR)
	                .appendLiteral(", ")
	                .appendPattern("HH:mm:ss")
	                .toFormatter(new Locale("es", "ES"));

	        String formattedDateTime = madridTime.format(formatter);
	        formattedDateTime = formattedDateTime.substring(0, 1).toUpperCase() + formattedDateTime.substring(1);
			return "" + formattedDateTime;
	    }

	public static Calculadora getCalculadora() {
		return calculadora;
	}

	public static void setCalculadora(Calculadora calculadora) {
		Calculadora.calculadora = calculadora;
	}

	
}
