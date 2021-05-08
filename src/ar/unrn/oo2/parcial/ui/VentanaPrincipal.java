package ar.unrn.oo2.parcial.ui;

import javax.swing.JFrame;

import ar.unrn.oo2.parcial.modelo.ListaTiposDeCombustible;
import ar.unrn.oo2.parcial.modelo.Persistencia;

public class VentanaPrincipal extends JFrame {
	Persistencia per;
	ListaTiposDeCombustible lista_combustibles;
	
	public VentanaPrincipal( Persistencia per, ListaTiposDeCombustible lista_combustibles )
	{
		this.per = per;
		this.lista_combustibles = lista_combustibles;
	}
}
