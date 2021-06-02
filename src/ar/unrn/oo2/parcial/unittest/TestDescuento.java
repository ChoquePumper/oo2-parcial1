package ar.unrn.oo2.parcial.unittest;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import java.time.LocalDateTime;
import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import ar.unrn.oo2.parcial.datosplano.RegistroVentas;
import ar.unrn.oo2.parcial.exceptions.ConsultarVentasException;
import ar.unrn.oo2.parcial.exceptions.EstacionDeServicioException;
import ar.unrn.oo2.parcial.exceptions.RegistrarVentaException;
import ar.unrn.oo2.parcial.modelo.EstacionDeServicio;
import ar.unrn.oo2.parcial.modelo.ListaTiposDeCombustible;
import ar.unrn.oo2.parcial.modelo.Persistencia;
import ar.unrn.oo2.parcial.modelo.TipoDeCombustible;
import ar.unrn.oo2.parcial.modelo.Venta;

public class TestDescuento {
	@Test
	public void probar() throws EstacionDeServicioException
	{
		//Persistencia p = new RegistroVentas("datos.txt");
		// Creo una clase anónima (que no hará nada) porque solo se probará lo del descuento.
		Persistencia p = new Persistencia() {
			@Override
			public void agregarVenta(Venta venta) throws RegistrarVentaException {
				// TODO Auto-generated method stub
			}
			@Override
			public ArrayList<Venta> consultarVentas(EstacionDeServicio estacion, LocalDateTime inicio,
					LocalDateTime fin) throws ConsultarVentasException {
				// TODO Auto-generated method stub
				return null;
			}};
		ListaTiposDeCombustible lista_combustibles = new ListaTiposDeCombustible();
		EstacionDeServicio estacion = new EstacionDeServicio(p, lista_combustibles);
		
		LocalDateTime fecha;

		TipoDeCombustible combustible_comun = lista_combustibles.getTipo("comun");
		TipoDeCombustible combustible_super = lista_combustibles.getTipo("super");
		
		// Los días domingos la nafta Super tiene un 10% de descuento.
		fecha = LocalDateTime.of(2021,05,22, 23,59,59); // Sábado
		assertEquals( (float)0.0,	combustible_super.getDescuento(fecha, 1), 0 );
		fecha = LocalDateTime.of(2021,05,23, 0,00,00); // Domingo
		assertEquals( (float)0.10,	combustible_super.getDescuento(fecha, 1), 0 );
		fecha = LocalDateTime.of(2021,05,23, 23,59,59); // Domingo
		assertEquals( (float)0.10,	combustible_super.getDescuento(fecha, 1), 0 );
		fecha = LocalDateTime.of(2021,05,24, 7,59,59); // Lunes
		assertEquals( (float)0.0,	combustible_super.getDescuento(fecha, 1), 0 );
		// Cargando más de 20 litros de super los días sábados, hay un 12% de descuento.
		fecha = LocalDateTime.of(2021,05,22, 7,59,59); // Sábado
		assertEquals( (float)0.0,	combustible_super.getDescuento(fecha, 20), 0 );
		fecha = LocalDateTime.of(2021,05,22, 7,59,59);
		assertEquals( (float)0.12,	combustible_super.getDescuento(fecha, (float)20.000001), 0 );
		fecha = LocalDateTime.of(2021,05,21, 23,59,59); // Viernes
		assertEquals( (float)0.0,	combustible_super.getDescuento(fecha, (float)20.000001), 0 );
		fecha = LocalDateTime.of(2021,05,23, 12,00,00); // Domingo (10% de descuento)
		assertEquals( (float)0.10,	combustible_super.getDescuento(fecha, (float)20.000001), 0 );
		
		// La nafta Común cargando cualquier día entre las 8 y las 10 de la mañana hay
		//un 5% de descuento
		fecha = LocalDateTime.of(2021,05,23, 7,59,59);
		assertEquals( (float)0.0,	combustible_comun.getDescuento(fecha, 1), 0 );
		System.out.println( estacion.consultarMonto(combustible_comun, fecha, 1) );
		
		fecha = LocalDateTime.of(2021,05,23, 8,00,00);
		assertEquals( (float)0.05,	combustible_comun.getDescuento(fecha, 1), 0 );
		System.out.println( estacion.consultarMonto(combustible_comun, fecha, 1) );
		
		fecha = LocalDateTime.of(2021,05,23, 9,59,59);
		assertEquals( (float)0.05,	combustible_comun.getDescuento(fecha, 1), 0 );
		System.out.println( estacion.consultarMonto(combustible_comun, fecha, 1) );
		
		fecha = LocalDateTime.of(2021,05,23, 10,00,00);
		assertEquals( (float)0.0,	combustible_comun.getDescuento(fecha, 1), 0 );
		System.out.println( estacion.consultarMonto(combustible_comun, fecha, 1) );
		
		
	}
}
