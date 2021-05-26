package ar.unrn.oo2.parcial.modelo;

//import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class ListaTiposDeCombustible {
	//private ArrayList<TipoDeCombustible> lista;
	private Map<String, TipoDeCombustible> tipos;
	
	public ListaTiposDeCombustible() {
		//lista = new ArrayList<TipoDeCombustible>();
		tipos = new HashMap<String, TipoDeCombustible>();
		agregarTipo(new CombustibleComun());
		agregarTipo(new CombustibleSuper());
	}
	
	public void agregarTipo(TipoDeCombustible t)
	{
		/*if ( yaExisteEnLaLista( t ) )
			throw new RuntimeException("Ya existe en la lista");
		lista.add(t);*/
		tipos.put(t.getNombreCorto(), t);
	}
	
	public TipoDeCombustible getTipo(String nombre)
	{
		/*for( TipoDeCombustible tipo : lista )
			if ( tipo.getNombreCorto().equals(nombre) )
				return tipo;
		return null*/
		return tipos.getOrDefault(nombre,null);
	}
	
	public TipoDeCombustible[] getLista()
	{
		int size = getCantidad();
		if (size == 0)
			return new TipoDeCombustible[0];
		TipoDeCombustible[] lista = new TipoDeCombustible[size];
		return tipos.values().toArray(lista);
	}
	
	public int getCantidad()
	{
		return tipos.size();
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
