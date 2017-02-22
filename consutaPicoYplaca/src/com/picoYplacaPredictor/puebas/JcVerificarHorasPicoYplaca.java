package com.picoYplacaPredictor.puebas;

import static org.junit.Assert.*;

import org.junit.Test;

import com.picoYplacaPredictor.utilidades.Utilidades;

public class JcVerificarHorasPicoYplaca {

	@Test
	public void test() {
		Utilidades ut = new Utilidades();
		Boolean picoYplaca = ut.verificarHorasPicoYplaca("8:45");
		assertEquals(true, picoYplaca);
	}

}
