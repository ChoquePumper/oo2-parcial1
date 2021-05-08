package ar.unrn.oo2.parcial.modelo;

import java.util.ArrayList;

public class ListaTiposDeCombustible {
	private ArrayList<TipoDeCombustible> lista;
	
	public ListaTiposDeCombustible() {
		lista = new ArrayList<TipoDeCombustible>();
		AgregarTipo(new CombustibleComun());
		AgregarTipo(new CombustibleSuper());
	}
	
	public void AgregarTipo(TipoDeCombustible t)
	{
		if ( yaExisteEnLaLista( t ) )
			throw new RuntimeException("Ya existe en la lista");
		lista.add(t);
	}
	
	public TipoDeCombustible getTipo(String nombre)
	{
		for( TipoDeCombustible tipo : lista )
			if ( tipo.getNombreCorto().equals(nombre) )
				return tipo;
		return null;
	}

	public boolean yaExisteEnLaLista(String nombre)
	{
		return getTipo(nombre) != null;
	}
	public boolean yaExisteEnLaLista(TipoDeCombustible t)
	{
		return yaExisteEnLaLista( t.getNombreCorto() );
	}

}
