package ar.unrn.oo2.parcial.datosplano;

import java.io.File;
import java.time.LocalDateTime;
import java.util.ArrayList;

import ar.unrn.oo2.parcial.modelo.Persistencia;
import ar.unrn.oo2.parcial.modelo.Venta;

public class RegistroVentas implements Persistencia {

	private String nombre_de_archivo;
	
	
	public RegistroVentas(String nombre_de_archivo)
	{
		
	}
	
	@Override
	public void AgregarVenta(Venta venta) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public ArrayList<Venta> ConsultarVentas(LocalDateTime inicio, LocalDateTime fin) {
		// TODO Auto-generated method stub
		return null;
	}

}
