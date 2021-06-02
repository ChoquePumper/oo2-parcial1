package ar.unrn.oo2.parcial.datosplano;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Scanner;

import ar.unrn.oo2.parcial.exceptions.ConsultarVentasException;
import ar.unrn.oo2.parcial.exceptions.EstacionDeServicioException;
import ar.unrn.oo2.parcial.exceptions.RegistrarVentaException;
import ar.unrn.oo2.parcial.modelo.AuxFechaHora;
import ar.unrn.oo2.parcial.modelo.EstacionDeServicio;
import ar.unrn.oo2.parcial.modelo.Persistencia;
import ar.unrn.oo2.parcial.modelo.Venta;

public class RegistroVentas implements Persistencia {
	
	// Separado en tabuladores
	private static String getSeparador() { return "\t"; };
	
	//private String nombre_de_archivo;
	private File archivo;
	//private FileInputStream entrada;
	
	public RegistroVentas(String nombre_de_archivo) throws EstacionDeServicioException
	{
		archivo = new File(nombre_de_archivo);
		if ( !archivo.exists() )
			try {
				archivo.createNewFile();
			} catch (IOException e) {
				throw new EstacionDeServicioException("Error al crear el archivo", e);
			}
		if ( !archivo.canRead() )
			throw new EstacionDeServicioException("El archivo no se puede leer");
	}
	
	private String generarTexto(Venta venta)
	{
		return venta.generarTexto( getSeparador() );
	}
	
	private Venta ParsearLinea(String linea, EstacionDeServicio estacion)
	{
		String[] campos = linea.split( getSeparador() );
		return estacion.crearVenta(
				campos[0], campos[1], campos[2], campos[3]);
	}
	
	@Override
	public void agregarVenta(Venta venta) throws RegistrarVentaException {
		try {
			FileOutputStream salida = new FileOutputStream(archivo,true);
			salida.write( (generarTexto(venta)+"\n").getBytes());
			salida.close();
		} catch (IOException e) {
			//e.printStackTrace();
			throw new RegistrarVentaException("No se puede escribir en el archivo", e);
		}
	}

	@Override
	public ArrayList<Venta> consultarVentas(EstacionDeServicio estacion, LocalDateTime inicio, LocalDateTime fin) throws ConsultarVentasException {
		FileInputStream entrada = null;
		Scanner s = null;
		ArrayList<Venta> lista = new ArrayList<Venta>();
		try {
			entrada = new FileInputStream(archivo);
			s = new Scanner(entrada); // Para leer cada linea
			while ( s.hasNextLine() ) {
				String linea = s.nextLine();
				Venta venta = ParsearLinea(linea, estacion);
				if (AuxFechaHora.esEntreLasFechas(venta.getFecha(), inicio, fin))
					lista.add(venta);
			}
		} catch (IOException e) {
			//e.printStackTrace();
			throw new ConsultarVentasException("No se pudo obtener la lista de ventas", e);
		} finally {
			if (s != null) s.close();
			if (entrada != null)
				try {
					entrada.close();
				} catch (IOException ignore) {} // Ignorar
		}
		return lista;
	}
}
