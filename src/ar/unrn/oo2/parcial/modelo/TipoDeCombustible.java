package ar.unrn.oo2.parcial.modelo;

import java.time.LocalDateTime;

public abstract class TipoDeCombustible {
	private float precio_por_litro;
	private String nombre_corto;
	private String nombre_a_mostrar;
	
	public TipoDeCombustible( String nombre_corto, String nombre_a_mostrar, float precioPorLitro )
	{
		this.nombre_corto = nombre_corto;
		this.nombre_a_mostrar = nombre_a_mostrar;
		this.precio_por_litro = precioPorLitro;

		if (!ValidarNombreCorto())
			throw new RuntimeException("");
		if (!ValidarString(nombre_a_mostrar))
			throw new RuntimeException("El nombre a mostrar no puede quedar vacio");
		if (!ValidarPrecioPorLitro())
			throw new RuntimeException("Precio por litro inválido, debe ser mayor a 0");
	}
	
	private boolean ValidarNombreCorto() {
		return ValidarString(nombre_corto) && !nombre_corto.contains(" ");
	}
	private boolean ValidarString(String texto) { return texto!=null && !texto.isEmpty(); }
	private boolean ValidarPrecioPorLitro()
		{ return precio_por_litro > 0; }

	public String getNombreCorto() { return nombre_corto; }
	public String getNombreAMostrar() { return nombre_a_mostrar; }
	public float getPrecioPorLitro() { return precio_por_litro; }
	
	public String toString() { return getNombreCorto(); }
	
	/** Debe devolver un valor entre 0 y 1 (0% y 100% respectivamente).*/
	public abstract float getDescuento(LocalDateTime fecha_hora, float litros_a_cargar);
	
	public float calcularPrecio(LocalDateTime fecha_hora, float litros_a_cargar)
	{
		float descuento = getDescuento(fecha_hora, litros_a_cargar);
		return getPrecioPorLitro()*litros_a_cargar * (1-descuento);
	}
}
