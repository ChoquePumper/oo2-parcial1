package ar.unrn.oo2.parcial.modelo;

import java.time.LocalDateTime;
import java.util.ArrayList;

import ar.unrn.oo2.parcial.exceptions.ConsultarVentasException;
import ar.unrn.oo2.parcial.exceptions.RegistrarVentaException;

public interface Persistencia {
	void agregarVenta(Venta venta) throws RegistrarVentaException;
	//ArrayList<Venta> consultarVentas(LocalDateTime inicio, LocalDateTime fin) throws RuntimeException;
	ArrayList<Venta> consultarVentas(EstacionDeServicio estacion,
			LocalDateTime inicio, LocalDateTime fin) throws ConsultarVentasException;
}
