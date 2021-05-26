package ar.unrn.oo2.parcial.main;

import java.time.LocalDateTime;

import ar.unrn.oo2.parcial.datosplano.RegistroVentas;
import ar.unrn.oo2.parcial.modelo.EstacionDeServicio;
import ar.unrn.oo2.parcial.modelo.ListaTiposDeCombustible;
import ar.unrn.oo2.parcial.modelo.Venta;

public class Main_cli {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ListaTiposDeCombustible l = new ListaTiposDeCombustible();
		EstacionDeServicio estacion = new EstacionDeServicio(
				new RegistroVentas("datos2.txt"), l);
		
		estacion.registrarVenta(
				estacion.crearVenta(l, "2020-12-03T00:00:00","comun", "3.32", "401")
		);
		System.out.println("ListaDeVentas");
		for(Venta v: estacion.obtenerListaDeVentas(LocalDateTime.parse("2020-01-01T00:00"), LocalDateTime.now().plusDays(1)) )
			System.out.println(v);
	}

}
