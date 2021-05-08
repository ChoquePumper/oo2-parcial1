package ar.unrn.oo2.parcial.modelo;

import java.time.LocalDateTime;

public class CombustibleSuper extends TipoDeCombustible {

	public CombustibleSuper() {
		super("super", "Super", 90);
		// TODO Auto-generated constructor stub
	}

	@Override
	public float getDescuento(LocalDateTime fecha_hora, float litros_a_cargar) {
		if( AuxFechaHora.esDomingo(fecha_hora) )
			return (float)0.1;	// 10%
		if( AuxFechaHora.esSabado(fecha_hora) && litros_a_cargar > 20 )
			return (float)0.12;	// 12%
		return 0;	// Sin descuento
	}

}
