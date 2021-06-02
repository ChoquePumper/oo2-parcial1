package ar.unrn.oo2.parcial.exceptions;

@SuppressWarnings("serial")
public class ConsultarVentasException extends EstacionDeServicioException {

	public ConsultarVentasException() {
		// TODO Auto-generated constructor stub
	}

	public ConsultarVentasException(String mensaje) {
		super(mensaje);
		// TODO Auto-generated constructor stub
	}

	public ConsultarVentasException(Throwable causa) {
		super(causa);
		// TODO Auto-generated constructor stub
	}

	public ConsultarVentasException(String mensaje, Throwable causa) {
		super(mensaje, causa);
		// TODO Auto-generated constructor stub
	}

}
