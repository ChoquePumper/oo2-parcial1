package ar.unrn.oo2.parcial.modelo;

import java.time.LocalDateTime;
import java.util.ArrayList;

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
	
	public void registrarVenta(Venta venta)
	{
		medio_de_persistencia.agregarVenta(venta);
	}
	
	public TipoDeCombustible[] getListaDeTiposDeCombustible()
	{
		return tipos_de_combustible.getLista();
	}
	
	public float ConsultarMonto(TipoDeCombustible tipo, LocalDateTime fecha, float litros)
	{
		float precio_por_litro = tipo.getPrecioPorLitro();
		return precio_por_litro*litros * (1-tipo.getDescuento(fecha, litros));
	}
	
	public float ConsultarMonto(String tipo_de_combustible, LocalDateTime fecha, float litros)
	{
		TipoDeCombustible tipo =  tipos_de_combustible.getTipo(tipo_de_combustible);
		return ConsultarMonto(tipo, fecha, litros);
	}
	
	public ArrayList<Venta> obtenerListaDeVentas(LocalDateTime inicio, LocalDateTime fin)
	{
		return medio_de_persistencia.consultarVentas(tipos_de_combustible, inicio, fin);
	}
	
	public static Venta crearVenta(ListaTiposDeCombustible tipos_de_combustible, String fecha, String tipo_de_combustible, String litros_cargados, String monto_facturado)
	{
		return new Venta(
			LocalDateTime.parse(fecha),
			tipos_de_combustible.getTipo(tipo_de_combustible),
			Float.parseFloat(litros_cargados),
			Float.parseFloat(monto_facturado)
		);
	}
}
