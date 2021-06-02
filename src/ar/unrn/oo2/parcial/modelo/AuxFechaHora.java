package ar.unrn.oo2.parcial.modelo;

import java.time.LocalDate;
//import java.text.DateFormatSymbols;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class AuxFechaHora {
	//private static String[] meses = new DateFormatSymbols().getMonths();
	//private static String[] dias = new DateFormatSymbols().getWeekdays();
	
	public static boolean esEntreLasFechas(LocalDateTime fecha_a_evaluar, LocalDateTime inicio, LocalDateTime fin)
	{
		return inicio.isBefore(fecha_a_evaluar) && fin.isAfter(fecha_a_evaluar);
	}
	
	public static boolean esSabado(LocalDateTime fecha)
	{
		return fecha.getDayOfWeek().getValue() == 6; // 6 es sábado
	}
	
	public static boolean esDomingo(LocalDateTime fecha)
	{
		return fecha.getDayOfWeek().getValue() == 7; // 7 es domingo
	}
	
	public static boolean esEntreLas8y10am(LocalDateTime fecha)
	{
		LocalDateTime ALas8 = LocalDateTime.of(
				fecha.getYear(),fecha.getMonthValue(),fecha.getDayOfMonth(),
				8,00,00, 0);
		LocalDateTime ALas10 = LocalDateTime.of(
				fecha.getYear(),fecha.getMonthValue(),fecha.getDayOfMonth(),
				10,00,00, 0);
		return (ALas8.isBefore(fecha) || ALas8.isEqual(fecha)) && fecha.isBefore(ALas10);
	}
	
	public static LocalDateTime getFechaHora(String s)
	{
		return LocalDateTime.parse(s);
	}
	
	public static LocalDate getFecha(String s)
	{
		return LocalDate.parse(s, DateTimeFormatter.ofPattern("dd/MM/yyyy"));
	}
}
