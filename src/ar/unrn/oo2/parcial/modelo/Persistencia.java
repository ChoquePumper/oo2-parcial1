package ar.unrn.oo2.parcial.modelo;

import java.time.LocalDateTime;
import java.util.ArrayList;

public interface Persistencia {
	void agregarVenta(Venta venta) throws RuntimeException;
	//ArrayList<Venta> consultarVentas(LocalDateTime inicio, LocalDateTime fin) throws RuntimeException;
	ArrayList<Venta> consultarVentas(ListaTiposDeCombustible tipos_de_combustible,
			LocalDateTime inicio, LocalDateTime fin);
}
