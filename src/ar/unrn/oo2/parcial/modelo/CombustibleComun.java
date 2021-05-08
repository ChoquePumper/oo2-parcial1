package ar.unrn.oo2.parcial.modelo;

import java.time.LocalDateTime;

public class CombustibleComun extends TipoDeCombustible {

	public CombustibleComun() {
		super("comun", "Común", 70);
		// TODO Auto-generated constructor stub
	}

	@Override
	public float getDescuento(LocalDateTime fecha_hora, float litros_a_cargar) {
		// Si se carga nafta entre las 8 y 10 de la mañana
		if (AuxFechaHora.esEntreLas8y10am(fecha_hora))
			return (float)0.05;	// 5% de descuento
		return 0;
	}

}
