package com.picoYplacaPredictor.dtos;

/**
 * Clase (DTO) AutomovilDto.
 * DTO que sirve para manejar los datos del automovil.
 * @author darellano.
 * @version 1.0
 */
public class AutomovilDto {
	

	/****************************************/
	/********* variables para el DTO ********/
	/****************************************/
	
	private int autId;
	private String autPlaca;
	private Integer autPuedeConducir;
	
	/***********************************************/
	/***************** Contructores ****************/
	/***********************************************/
	
	public AutomovilDto() {
	}
	
	public AutomovilDto(int autId) {
		this.autId = autId;
	}
	/***********************************************/
	/***************** Geters y Seters**************/
	/***********************************************/
	
	public int getAutId() {
		return autId;
	}

	public void setAutId(int autId) {
		this.autId = autId;
	}

	public String getAutPlaca() {
		return autPlaca;
	}

	public void setAutPlaca(String autPlaca) {
		this.autPlaca = autPlaca;
	}

	public Integer getAutPuedeConducir() {
		return autPuedeConducir;
	}

	public void setAutPuedeConducir(Integer autPuedeConducir) {
		this.autPuedeConducir = autPuedeConducir;
	}
	
	/***********************************************/
	/***************** toString ********************/
	/***********************************************/
	
    public String toString() {
    	String tabulador = "\t";
		StringBuilder sb = new StringBuilder();
		sb.append("autId : " + autId);
		sb.append(tabulador + "autPlaca : " + (autPlaca==null? "NULL":autPlaca));
		sb.append(tabulador + "autPuedeConducir : " + (autPuedeConducir==null? "NULL":autPuedeConducir));
		return sb.toString();
    }

	

}
