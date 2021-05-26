package ar.unrn.oo2.parcial.modelo;

import java.time.LocalDateTime;

public class Venta {
	private LocalDateTime fecha;
	private TipoDeCombustible tipo_de_combustible;
	private float litros_cargados;
	private float monto_facturado;
	
	public Venta(LocalDateTime fecha, TipoDeCombustible tipo, float litros_cargados, float monto_facturado)
	{
		this.fecha = fecha;
		this.tipo_de_combustible = tipo;
		this.litros_cargados = litros_cargados;
		this.monto_facturado = monto_facturado;
		
		if( !ValidarFecha() )
			throw new RuntimeException("Fecha inválida");
		if( !ValidarTipoDeCombustible() )
			throw new RuntimeException("Tipo de combustible inválido");	
	}
	
	// Validar
	private boolean ValidarFecha() { return fecha != null; }
	private boolean ValidarTipoDeCombustible() {
		return tipo_de_combustible != null;
	}
	
	// Getters
	public LocalDateTime getFecha() { return fecha; }
	public TipoDeCombustible getTipoDeCombustible()
		{ return tipo_de_combustible; }
	public float getLitrosCargados() { return litros_cargados; }
	public float getMontoFacturado() { return monto_facturado; }

	// Otros
	public String generarTexto( String separador )
	{
		return String.join( separador,
				getFecha().toString(),
				getTipoDeCombustible().getNombreCorto(),
				Float.toString( getLitrosCargados() ),
				Float.toString( getMontoFacturado() )
			);
	}
	
	public String toString() {
		return "<Venta: "+generarTexto(", ")+">";
	}
}
