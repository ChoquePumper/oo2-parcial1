package ar.unrn.oo2.parcial.modelo;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;

import ar.unrn.oo2.parcial.exceptions.ConsultarVentasException;
import ar.unrn.oo2.parcial.exceptions.RegistrarVentaException;

public class EstacionDeServicio {
	private ListaTiposDeCombustible tipos_de_combustible;
	private Persistencia medio_de_persistencia;
	
	public EstacionDeServicio(Persistencia p, ListaTiposDeCombustible tipos_de_combustible)
	{
		this.tipos_de_combustible = tipos_de_combustible;
		this.medio_de_persistencia = p;
		
		if (!validarTiposDeCombustible())
			throw new RuntimeException("La lista de tipos de combustible no es válida");
		if (!validarMedioDePersistencia())
			throw new RuntimeException("El medio de persistencia no es válido");
	}
	
	private boolean validarTiposDeCombustible()
	{
		return this.tipos_de_combustible != null;
	}
	
	private boolean validarMedioDePersistencia()
	{
		return this.medio_de_persistencia != null;
	}
	
	public void registrarVenta(Venta venta) throws RegistrarVentaException
	{
		medio_de_persistencia.agregarVenta(venta);
	}
	
	public void registrarVenta(String tipo_de_combustible, String litros_a_cargar, String monto) throws RegistrarVentaException
	{
		LocalDateTime hoy = LocalDateTime.now();
		Venta venta = crearVenta(
				//tipos_de_combustible,
				hoy.toString(),
				tipo_de_combustible,
				litros_a_cargar,
				monto);
		// Comprobar precio
		float monto_actual = consultarMonto(tipo_de_combustible, hoy, venta.getLitrosCargados());
		float monto_declarado = venta.getMontoFacturado();
		// Si ha cambiado el precio, entonces no se registra.
		if ( monto_actual != monto_declarado )
			throw new RuntimeException("El monto ha cambiado.");
		
		// Registrar la venta
		registrarVenta(venta);
	}
	
	public TipoDeCombustible[] getListaDeTiposDeCombustible()
	{
		return tipos_de_combustible.getLista();
	}
	
	public float consultarMonto(TipoDeCombustible tipo, LocalDateTime fecha_hora, float litros)
	{
		return tipo.calcularPrecio(fecha_hora, litros);
	}
	
	public float consultarMonto(String tipo_de_combustible, LocalDateTime fecha_hora, float litros)
	{
		TipoDeCombustible tipo =  tipos_de_combustible.getTipo(tipo_de_combustible);
		return consultarMonto(tipo, fecha_hora, litros);
	}
	
	public float ConsultarMonto(String tipo_de_combustible, LocalDateTime fecha_hora, String litros)
	{
		return consultarMonto(tipo_de_combustible,
				fecha_hora,
				Float.parseFloat(litros));
	}
	
	public ArrayList<Venta> obtenerListaDeVentas(LocalDateTime inicio, LocalDateTime fin) throws ConsultarVentasException
	{
		return medio_de_persistencia.consultarVentas(this, inicio, fin);
	}
	
	public Venta crearVenta(String fecha_hora, String tipo_de_combustible, String litros_cargados, String monto_facturado)
	{
		return new Venta(
			AuxFechaHora.getFechaHora(fecha_hora),
			tipos_de_combustible.getTipo(tipo_de_combustible),
			Float.parseFloat(litros_cargados),
			Float.parseFloat(monto_facturado)
		);
	}
}
