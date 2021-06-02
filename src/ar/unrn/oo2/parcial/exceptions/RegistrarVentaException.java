package ar.unrn.oo2.parcial.exceptions;

@SuppressWarnings("serial")
public class RegistrarVentaException extends EstacionDeServicioException {

	public RegistrarVentaException() {
		// TODO Auto-generated constructor stub
	}
	
	public RegistrarVentaException(String mensaje) {
		super(mensaje);
		// TODO Auto-generated constructor stub
	}

	public RegistrarVentaException(Throwable causa) {
		super(causa);
		// TODO Auto-generated constructor stub
	}
	
	public RegistrarVentaException(String mensaje, Throwable causa) {
		super(mensaje, causa);
		// TODO Auto-generated constructor stub
	}
	
}
