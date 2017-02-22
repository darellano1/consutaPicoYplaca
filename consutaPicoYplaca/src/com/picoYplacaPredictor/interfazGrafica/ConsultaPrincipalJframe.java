package com.picoYplacaPredictor.interfazGrafica;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import com.picoYplacaPredictor.dtos.AutomovilDto;
import com.picoYplacaPredictor.dtos.excepciones.AutomovilDtoValidacionException;
import com.picoYplacaPredictor.servicios.ConsultarHabilitacionVehicular;
import com.picoYplacaPredictor.utilidades.Utilidades;

/**
 * Clase (Jframe) ConsultaPrincipalJframe.
 * Jframe que permite generar la visualización de la pantalla de la aplicación y sus acciones.
 * @author darellano.
 * @version 1.0
 */
public class ConsultaPrincipalJframe extends JFrame implements ActionListener{
	private static final long serialVersionUID = -8537352720586130325L;
	
	private JLabel jlblPlaca,jlblFecha,jlblHora,jlblIngrese,jlblFondo;
	private JTextField jtfPlaca,jtfFecha,jtfHora;
	private JButton jbtnConsultar, jbtnLimpiar;
	private ImageIcon imgImagenIcon;
	
	
	public ConsultaPrincipalJframe(String titulo) {
		setLayout(null);
		setTitle(titulo);
		
		String path = "/com/picoYplacaPredictor/imagenes/fondo.jpg";  
		URL url = this.getClass().getResource(path);  
		imgImagenIcon = new ImageIcon(url.toString());
		jlblFondo = new JLabel(imgImagenIcon);	
		jlblPlaca = new JLabel("Placa (Ej: PBL4570) : ");
		jlblFecha = new JLabel("Fecha (Ej: dd/MM/aaaa) : ");
		jlblHora = new JLabel("Hora (Ej: hh:mm) :");
		jlblIngrese = new JLabel("CONSULTA DE HABILITACIÓN VEHICULAR DEL PICO Y PLACA");
		jtfPlaca = new JTextField();
		jtfFecha = new JTextField();
		jtfHora = new JTextField();
		jbtnConsultar = new JButton("Consultar");
		jbtnLimpiar = new JButton("Limpiar");
		
		jlblFondo.setBounds(0, 0, 400, 350);
		jlblIngrese.setBounds(20, 40, 350, 30);
		jlblPlaca.setBounds(20, 90, 160, 30);
		jlblFecha.setBounds(20, 140, 160, 30);
		jlblHora.setBounds(20,190, 160, 30);
		jtfPlaca.setBounds(190, 90, 160, 30);
		jtfFecha.setBounds(190, 140, 160, 30);
		jtfHora.setBounds(190,190, 160, 30);
		jbtnConsultar.setBounds(60, 250, 120, 40);
		jbtnLimpiar.setBounds(200, 250, 120, 40);
		
		add(jlblIngrese);
		add(jlblPlaca);
		add(jlblFecha);
		add(jlblHora);
		add(jtfPlaca);
		add(jtfFecha);
		add(jtfHora);
		add(jbtnConsultar);
		add(jbtnLimpiar);
		add(jlblFondo);
		
		jbtnConsultar.addActionListener(this);
		jbtnLimpiar.addActionListener(this);
		
	}
	

	public static void main(String[] args) {
		
		ConsultaPrincipalJframe ventana = new ConsultaPrincipalJframe("Consulta Pico y Placa");
		ventana.setBounds(500,150,400,350);
		ventana.setVisible(true);
		ventana.setResizable(false);
		
	}
	
	 public void actionPerformed (ActionEvent e) {
			Utilidades ut = new Utilidades();
			ConsultarHabilitacionVehicular habilitacionVehicular = new ConsultarHabilitacionVehicular();
			if (e.getSource()==jbtnConsultar) {
				try {
					if(!jtfPlaca.getText().isEmpty() && !jtfFecha.getText().isEmpty() && !jtfHora.getText().isEmpty()){
						AutomovilDto automovil = new AutomovilDto();
						automovil.setAutId(1);
						automovil.setAutPlaca(jtfPlaca.getText());
						automovil.setAutPuedeConducir(1);// no puede conducir
	
						automovil = habilitacionVehicular.vehiculoHabilitado(automovil, jtfFecha.getText(), jtfHora.getText());
						if(automovil.getAutPuedeConducir() == 0){
							JOptionPane.showConfirmDialog(null
									     ,"El automóvil con placa "+automovil.getAutPlaca().toUpperCase()
										 +" el día "+ut.obtenerDiaDeLaSemanaString(jtfFecha.getText())+" "+jtfFecha.getText().toUpperCase() 
										 +" a las "+ jtfHora.getText().toUpperCase() +" puede circular","RESULTADO", 
										 JOptionPane.DEFAULT_OPTION,
										 JOptionPane.INFORMATION_MESSAGE);
						}else{
							JOptionPane.showConfirmDialog(null
										,"El automovil con placa "+automovil.getAutPlaca().toUpperCase()
										+" el día "+ut.obtenerDiaDeLaSemanaString(jtfFecha.getText())+" "+jtfFecha.getText().toUpperCase() 
										+" a las "+ jtfHora.getText().toUpperCase() +" no puede circular","RESULTADO", 
										JOptionPane.DEFAULT_OPTION, 
										JOptionPane.INFORMATION_MESSAGE);
								 }
					 }else{
						 JOptionPane.showConfirmDialog(null, "Debe ingresar todos los datos para poder consultar", "Error", JOptionPane.DEFAULT_OPTION, JOptionPane.ERROR_MESSAGE);
					 }
				} catch (AutomovilDtoValidacionException e1) {
					JOptionPane.showConfirmDialog(null, e1.getMessage(), "Error", JOptionPane.DEFAULT_OPTION, JOptionPane.ERROR_MESSAGE);
				}
			 }
			 
			 if(e.getSource()==jbtnLimpiar){
				jtfFecha.setText("");
				jtfHora.setText("");
				jtfPlaca.setText("");
			 }
			 
	 } 
}
