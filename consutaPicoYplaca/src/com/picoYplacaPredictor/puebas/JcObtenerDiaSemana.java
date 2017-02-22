package com.picoYplacaPredictor.puebas;

import static org.junit.Assert.*;

import org.junit.Test;

import com.picoYplacaPredictor.utilidades.Utilidades;

public class JcObtenerDiaSemana {

	@Test
	public void test() {
		Utilidades ut = new Utilidades();
		String dia = ut.obtenerDiaDeLaSemanaString("15/09/1992");
		int diaInt = 0;
		
		switch (dia) {
		case "Lunes":
			diaInt=1;
			break;
		case "Martes":
			diaInt=2;
			break;
		case "Miercoles":
			diaInt=3;
			break;
		case "Jueves":
			diaInt=4;
			break;
		case "Viernes":
			diaInt=5;
			break;
		case "Sabado":
			diaInt=6;
			break;
		case "Domingo":
			diaInt=7;
			break;
		default:
			break;
		}
		assertEquals(2, diaInt);
	}

}
