package ar.unrn.oo2.parcial.main;

import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import ar.unrn.oo2.parcial.datosplano.RegistroVentas;
import ar.unrn.oo2.parcial.modelo.EstacionDeServicio;
import ar.unrn.oo2.parcial.modelo.ListaTiposDeCombustible;
import ar.unrn.oo2.parcial.ui.CargaDeCombustible;

public class Main1 {

	public static void main(String[] args) {
		// Main usando un archivo de texto plano para la persistencia
		try {
			UIManager.setLookAndFeel( UIManager.getSystemLookAndFeelClassName() );
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException
				| UnsupportedLookAndFeelException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		RegistroVentas r = new RegistroVentas("datos.txt");
		ListaTiposDeCombustible lista_combustibles = new ListaTiposDeCombustible();
		EstacionDeServicio estacion = new EstacionDeServicio(r, lista_combustibles);
		CargaDeCombustible v = new CargaDeCombustible( estacion );
		v.setVisible(true);
	}

}
