package ar.unrn.oo2.parcial.modelo;

import java.text.DateFormatSymbols;
import java.time.LocalDateTime;

class AuxFechaHora {
	//private static String[] meses = new DateFormatSymbols().getMonths();
	//private static String[] dias = new DateFormatSymbols().getWeekdays();
	
	static boolean esSabado(LocalDateTime fecha)
	{
		return fecha.getDayOfWeek().getValue() == 6; // 6 es sábado
	}
	
	static boolean esDomingo(LocalDateTime fecha)
	{
		return fecha.getDayOfWeek().getValue() == 7; // 7 es domingo
	}
	
	static boolean esEntreLas8y10am(LocalDateTime fecha)
	{
		return fecha.getHour()>=8 && fecha.getHour()<=10;
	}
}
