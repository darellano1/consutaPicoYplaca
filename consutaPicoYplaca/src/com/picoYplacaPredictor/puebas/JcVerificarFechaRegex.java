package com.picoYplacaPredictor.puebas;

import org.junit.Test;

import com.picoYplacaPredictor.utilidades.Utilidades;

public class JcVerificarFechaRegex {

	@Test
	public void test() {
		Utilidades ut = new Utilidades();
		ut.verificarFechaRegex("15/09/1992");
//		assertEquals(true, picoYplaca);
	}

}
