package ar.unrn.oo2.parcial.main;

import ar.unrn.oo2.parcial.datosplano.RegistroVentas;
import ar.unrn.oo2.parcial.modelo.ListaTiposDeCombustible;
import ar.unrn.oo2.parcial.ui.CargaDeCombustible;

public class Main1 {

	public static void main(String[] args) {
		// Main usando un archivo de texto plano para la persistencia
		RegistroVentas r = new RegistroVentas("datos.txt");
		ListaTiposDeCombustible lista_combustibles = new ListaTiposDeCombustible();
		CargaDeCombustible v = new CargaDeCombustible(r, lista_combustibles);
		v.setVisible(true);
	}

}
