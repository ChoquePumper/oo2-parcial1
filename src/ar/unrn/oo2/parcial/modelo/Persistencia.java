package ar.unrn.oo2.parcial.modelo;

import java.time.LocalDateTime;
import java.util.ArrayList;

public interface Persistencia {
	void AgregarVenta(Venta venta);
	ArrayList<Venta> ConsultarVentas(LocalDateTime inicio, LocalDateTime fin);
}
