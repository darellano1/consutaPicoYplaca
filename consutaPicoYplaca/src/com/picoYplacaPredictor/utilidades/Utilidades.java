package com.picoYplacaPredictor.utilidades;

import java.time.DateTimeException;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Clase Utilidades.
 * Clase que permite hacer uso de utilidades para la consulta del pico y placa.
 * @author darellano
 * @version 1.0
 */
public class Utilidades {
	
	/**
	 * Método para obtener el dia de la semana a partir de una fecha
	 * @param fechaEnString - fecha en String que se desea buscar el día de la semana que es
	 * @return Objeto String del día de la semana que le corresponde a una fecha
	 * @exception IllegalArgumentException - lanzada cuando la fecha no existe o la fecha no es válida
	 */
	public String obtenerDiaDeLaSemanaString(String fechaEnString) throws IllegalArgumentException{
		String retorno = new String();
		try {
			String[] partes = fechaEnString.split("/");
			int dia = Integer.parseInt(partes[0]);  
			int mes = Integer.parseInt(partes[1]);
			int anio = Integer.parseInt(partes[2]);
			    
			LocalDate fecha = LocalDate.of(anio, mes, dia);
			String [] diasDeLaSemana = new String []{"Lunes","Martes","Miercoles","Jueves","Viernes","Sabado","Domingo"};
			DayOfWeek diaSemana = fecha.getDayOfWeek();
			retorno = diasDeLaSemana[diaSemana.getValue() -1];
		} catch (DateTimeException e) {
			 throw new IllegalArgumentException("No existe la fecha ingresada");
		}
		
		return retorno;
		
	}
	
	/**
	 * Método para transformar la hora en String a un arreglo
	 * @param horaString - hora en string que se quiere transformar en un arreglo
	 * @return - Objeto Array de integer de la hora 
	 */
	public Integer[] transformarHoraAarray(String horaString){
		String[] partes = horaString.split(":");
		Integer[] retorno = new Integer[2];
		retorno[0]= Integer.parseInt(partes[0]); //horas
		retorno[1]= Integer.parseInt(partes[1]); //minutos
		return retorno;
	}
	
	/**
	 * Método que permite verificar si las horas del pico y placa se cumplen
	 * @param hora - string de la hora que esta consultando
	 * @return Booleno que indica: true si la hora consultada esta en pico y placa, false caso contrario
	 */
	public Boolean verificarHorasPicoYplaca(String hora) {
		Boolean retorno = false;
		Integer[] horaArray = transformarHoraAarray(hora);
		
		//verifico que las horas esten entre 7 y 9 am y entre 16 y 19 pm
		if((horaArray[0] >= 7 && horaArray[0] < 9 ) || (horaArray[0] >= 16 && horaArray[0] <= 19)){
			retorno = true;
		}else{
			retorno = false;
		}
		// si la hora es 19, se verifica que los minutos sean mayores o  iguales a 30
		if(horaArray[0] == 19 && horaArray[1] >= 30){
			retorno = false;
		}
		
		return retorno;
	}
	
	/**
	 * Método para verificar si un auto tiene o no pico y placa
	 * @param diaDeLaSemana - dia de la semana que se requeire consultar
	 * @param ultimoDigitoPlaca - utimo dígito de la placa del automovil que se requiere consultar
	 * @return - Objeto Boolean que indica true si tiene pico y placa, false caso contrario
	 */
	public Boolean tienePicoYplaca(String diaDeLaSemana, Integer ultimoDigitoPlaca, String hora){
		Boolean tienePicoYplaca = false;
		switch (diaDeLaSemana) {
		case "Lunes":
			if(ultimoDigitoPlaca == 1 || ultimoDigitoPlaca == 2  ){
				tienePicoYplaca = verificarHorasPicoYplaca(hora); 
			}
			break;
		case "Martes":
			if(ultimoDigitoPlaca == 3 || ultimoDigitoPlaca == 4){
				tienePicoYplaca = verificarHorasPicoYplaca(hora); 
			}
			break;
		case "Miercoles":
			if(ultimoDigitoPlaca == 5 || ultimoDigitoPlaca == 6 ){
				tienePicoYplaca = verificarHorasPicoYplaca(hora); 
			}
			break;
		case "Jueves":
			if(ultimoDigitoPlaca == 7 || ultimoDigitoPlaca == 8 ){
				tienePicoYplaca = verificarHorasPicoYplaca(hora); 
			}
			break;
		case "Viernes":
			if(ultimoDigitoPlaca == 9 || ultimoDigitoPlaca == 0 ){
				tienePicoYplaca = verificarHorasPicoYplaca(hora); 
			}
			break;
		case "Sabado":
			tienePicoYplaca = false;
			break;
		case "Domingo":
			tienePicoYplaca = false;
			break;
		default:
			break;
		}
		
		return tienePicoYplaca;
	}
	
	/**
	 * Método que permite verificar que la fecha esté escrito en el formato solicitado mediante un regex
	 * @param fecha - string de la fecha que se desea validar
	 */
	public void verificarFechaRegex(String fecha) throws IllegalArgumentException{
		try {
			String PATRON_FECHA = "[0-9]{1,2}/[0-9]{2}/[0-9]{4}";
			Pattern pattern = Pattern.compile(PATRON_FECHA);
			Matcher matcher = pattern.matcher(fecha);
			if(!matcher.matches()){
				throw new IllegalArgumentException("No escribió la fecha en el parámetro indicado (dd/MM/aa) ");
			}else{
				String[] partes = fecha.split("/");
		        int dia = Integer.parseInt(partes[0]);  
		        int mes = Integer.parseInt(partes[1]);
		        int anio = Integer.parseInt(partes[2]);
		        if (anio < 1900) {
		            throw new IllegalArgumentException("No puede ser un año menor a 1900");
		        }
		        LocalDate.of(anio, mes, dia);
			}
				
		} catch (IllegalArgumentException e) {
			throw new IllegalArgumentException(e.getMessage());
		} catch (DateTimeException e) {
		 	throw new IllegalArgumentException("No existe la fecha ingresada");
		}
	}
	
	/**
	 * Método que permite verificar que la hora esté escrito en el formato solicitado mediante un regex
	 * @param hora - string de la horaque se desea validar
	 */
	public void verificarHoraRegex(String hora) throws IllegalArgumentException{
			String PATRON_HORA = "[0-9]{1,2}:[0-9]{2}";
			Pattern pattern = Pattern.compile(PATRON_HORA);
			Matcher matcher = pattern.matcher(hora);
			if(!matcher.matches()){
				throw new IllegalArgumentException("No escribió la hora en el parámetro indicado (HH:mm)");
			}else{
				String[] partes = hora.split(":");
				Integer horas = Integer.parseInt(partes[0]); // horas
				Integer minutos = Integer.parseInt(partes[1]); // minutos
				if((horas <= 24 && horas >= 0) && (minutos <= 60 && minutos >= 0)){
				}else{
					throw new IllegalArgumentException("No es una hora válida");
				}
			}
	}

	
	/**
	 * Método que permite verificar que la placa esté escrita en el formato correcto mediante un regex
	 * @param placa - string de la placa que se desea validar
	 */
	public void verificarPlacaRegex(String placa) throws IllegalArgumentException{
		try {
			String PATRON_PLACA = "[A-Za-z]{3}+[0-9]{3,4}";
			Pattern pattern = Pattern.compile(PATRON_PLACA);
			pattern.matcher(placa);
		} catch (IllegalArgumentException e) {
			throw new IllegalArgumentException("No es una Placa Válida");
		}
	}
}
