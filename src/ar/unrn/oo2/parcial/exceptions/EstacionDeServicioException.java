package ar.unrn.oo2.parcial.exceptions;

@SuppressWarnings("serial")
public class EstacionDeServicioException extends Exception {

	public EstacionDeServicioException() {
		// TODO Auto-generated constructor stub
	}

	public EstacionDeServicioException(String mensaje) {
		super(mensaje);
		// TODO Auto-generated constructor stub
	}

	public EstacionDeServicioException(Throwable causa) {
		super(causa);
		// TODO Auto-generated constructor stub
	}

	public EstacionDeServicioException(String mensaje, Throwable causa) {
		super(mensaje, causa);
		// TODO Auto-generated constructor stub
	}
	
	public String getMessage()
	{
		Throwable causa = getCause();
		StringBuffer msj = new StringBuffer();
		msj.append( super.getMessage() );
		if (causa != null)
			msj.append(": ").append(causa.getMessage());
		return msj.toString();
	}
	
}
