package ar.unrn.oo2.parcial.ui;

import javax.swing.JFrame;

import ar.unrn.oo2.parcial.modelo.ListaTiposDeCombustible;
import ar.unrn.oo2.parcial.modelo.Persistencia;

public class ConsultaDeVentas extends JFrame {
	Persistencia per;
	ListaTiposDeCombustible lista_combustibles;
	
	public ConsultaDeVentas( Persistencia per, ListaTiposDeCombustible lista_combustibles )
	{
		super("Consulta de ventas");
		this.per = per;
		this.lista_combustibles = lista_combustibles;
	}
}
