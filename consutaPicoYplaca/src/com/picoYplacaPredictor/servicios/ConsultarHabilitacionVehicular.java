package com.picoYplacaPredictor.servicios;

import com.picoYplacaPredictor.dtos.AutomovilDto;
import com.picoYplacaPredictor.dtos.excepciones.AutomovilDtoValidacionException;
import com.picoYplacaPredictor.utilidades.Utilidades;

/**
 * Clase ConsultarHabilitacionVehicular.
 * Clase que permite hacer las consultas para el pico y placa.
 * @author darellano
 * @version 1.0
 */
public class ConsultarHabilitacionVehicular {

	/**
	 * Método que verifica si el vehiculo está habilitado para circular
	 * @param automovil - objeto automovil que se requiere consular la habilitación vehicular
	 * @param fecha - fecha que se desea consultar la habilitacion vehicular
	 * @param hora - hora que se desea consultar la habilitacion vehicular
	 * @return - objeto automovil con los datos consultados
	 * @throws AutomovilDtoValidacionException 
	 */
	public AutomovilDto vehiculoHabilitado (AutomovilDto automovil, String fecha, String hora ) throws AutomovilDtoValidacionException {
		
		try {
			AutomovilDto retorno = automovil;
			Utilidades utilidades = new Utilidades();
			String diaDeLaSemana = new String();
			
			utilidades.verificarFechaRegex(fecha);
			utilidades.verificarHoraRegex(hora);
			utilidades.verificarPlacaRegex(automovil.getAutPlaca());
			
			
			diaDeLaSemana = utilidades.obtenerDiaDeLaSemanaString(fecha);
			Integer ultimoDigitoPlaca = Character.getNumericValue(automovil.getAutPlaca().charAt(automovil.getAutPlaca().length()-1));
				
			if(utilidades.tienePicoYplaca(diaDeLaSemana, ultimoDigitoPlaca, hora)){
				retorno.setAutPuedeConducir(1); // no puede circular
			}else{
				retorno.setAutPuedeConducir(0); // puede circular
			}
			
			return retorno;
			
		} catch (IllegalArgumentException e) {
			throw new AutomovilDtoValidacionException(e.getMessage());
		}
	}
	
}
