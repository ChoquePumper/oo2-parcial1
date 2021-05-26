package ar.unrn.oo2.parcial.datosplano;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Scanner;

import ar.unrn.oo2.parcial.modelo.AuxFechaHora;
import ar.unrn.oo2.parcial.modelo.EstacionDeServicio;
import ar.unrn.oo2.parcial.modelo.ListaTiposDeCombustible;
import ar.unrn.oo2.parcial.modelo.Persistencia;
import ar.unrn.oo2.parcial.modelo.Venta;

public class RegistroVentas implements Persistencia {
	
	// Separado en tabuladores
	private static String getSeparador() { return "\t"; };
	
	//private String nombre_de_archivo;
	private File archivo;
	//private FileInputStream entrada;
	
	public RegistroVentas(String nombre_de_archivo)
	{
		archivo = new File(nombre_de_archivo);
		if ( !archivo.exists() )
			try {
				archivo.createNewFile();
			} catch (IOException e) {
				throw new RuntimeException("Error al crear el archivo", e);
			}
		if ( !archivo.canRead() )
			throw new RuntimeException("El archivo no se puede leer");
	}
	
	private String generarTexto(Venta venta)
	{
		return venta.generarTexto( getSeparador() );
	}
	
	private Venta ParsearLinea(String linea, ListaTiposDeCombustible tipos_de_combustible)
	{
		String[] campos = linea.split( getSeparador() );
		return EstacionDeServicio.crearVenta(tipos_de_combustible,
				campos[0], campos[1], campos[2], campos[3]);
	}
	
	@Override
	public void agregarVenta(Venta venta) {
		try {
			FileOutputStream salida = new FileOutputStream(archivo,true);
			salida.write( (generarTexto(venta)+"\n").getBytes());
			salida.close();
		} catch (IOException e) {
			//e.printStackTrace();
			throw new RuntimeException("No se puede escribir en el archivo", e);
		}
	}

	@Override
	public ArrayList<Venta> consultarVentas(ListaTiposDeCombustible tipos_de_combustible,LocalDateTime inicio, LocalDateTime fin) {
		FileInputStream entrada = null;
		Scanner s = null;
		ArrayList<Venta> lista = new ArrayList<Venta>();
		try {
			entrada = new FileInputStream(archivo);
			s = new Scanner(entrada); // Para leer cada linea
			while ( s.hasNextLine() ) {
				String linea = s.nextLine();
				Venta venta = ParsearLinea(linea, tipos_de_combustible);
				if (AuxFechaHora.esEntreLasFechas(venta.getFecha(), inicio, fin))
					lista.add(venta);
			}
		} catch (IOException e) {
			//e.printStackTrace();
			throw new RuntimeException("No se pudo leer obtener la lista de ventas", e);
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
