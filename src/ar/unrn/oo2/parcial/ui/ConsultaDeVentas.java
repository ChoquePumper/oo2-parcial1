package ar.unrn.oo2.parcial.ui;

import javax.swing.JFrame;

import ar.unrn.oo2.parcial.exceptions.ConsultarVentasException;
import ar.unrn.oo2.parcial.modelo.EstacionDeServicio;
import ar.unrn.oo2.parcial.modelo.ListaTiposDeCombustible;
import ar.unrn.oo2.parcial.modelo.Persistencia;
import ar.unrn.oo2.parcial.modelo.Venta;

import javax.swing.JTable;
import java.awt.BorderLayout;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.temporal.TemporalAccessor;
import java.util.ArrayList;
import java.util.logging.SimpleFormatter;

import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableColumnModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.text.DateFormatter;
import javax.swing.text.DefaultFormatterFactory;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import java.awt.event.ActionListener;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

public class ConsultaDeVentas extends JFrame {
	private EstacionDeServicio estacion;
	private JTable tabla_ventas;
	private LocalDateTime fecha_inicio, fecha_fin;
	private ArrayList<Venta> lista_ventas;
	private CampoFormatoFecha fmt_fecha_inicio, fmt_fecha_fin;
	
	public ConsultaDeVentas( EstacionDeServicio estacion )
	{
		super("Consulta de ventas");
		this.estacion = estacion;
		this.fecha_inicio = null;
		this.fecha_fin = null;
		
		setSize(400,320);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		
		//JScrollPane center_panel = new JScrollPane();
		tabla_ventas = new JTable();
		tabla_ventas.setModel(new DefaultTableModel(
			new Object[][] {
				{null, null, null, null},
			},
			new String[] {
				"Fecha y hora", "Tipo de combustible", "Litros cargados", "Monto facturado"
			}
		) {
			Class[] columnTypes = new Class[] {
				String.class, String.class, Float.class, Float.class
			};
			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
			
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		});
		tabla_ventas.getColumnModel().getColumn(0).setPreferredWidth(137);
		tabla_ventas.getColumnModel().getColumn(1).setPreferredWidth(116);
		tabla_ventas.getColumnModel().getColumn(3).setPreferredWidth(93);
		tabla_ventas.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		getContentPane().add( new JScrollPane(tabla_ventas) , BorderLayout.CENTER);
		
		JPanel panel = new JPanel();
		getContentPane().add(panel, BorderLayout.SOUTH);
		
		
		JButton btnConsultarVentas = new JButton("Consultar ventas");
		btnConsultarVentas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//System.out.println( fmt_fecha_incio.getValue().getClass() );
				//System.out.println( LocalDate.from( (TemporalAccessor)fmt_fecha_incio.getValue() ) );
				setearTabla( 
					LocalDateTime.of(fmt_fecha_inicio.getFecha(), LocalTime.MIN),
					LocalDateTime.of(fmt_fecha_fin.getFecha(), LocalTime.MAX)
				);
			}
		});
		panel.add(btnConsultarVentas);
		
		JLabel lblEntreLasFechas = new JLabel("entre las fechas");
		panel.add(lblEntreLasFechas);
		
		fmt_fecha_inicio = new CampoFormatoFecha();
		fmt_fecha_inicio.setFecha( LocalDate.now().minusDays(6) );
		panel.add(fmt_fecha_inicio);
		
		fmt_fecha_fin = new CampoFormatoFecha();
		fmt_fecha_fin.setFecha( LocalDate.now() );
		panel.add(fmt_fecha_fin);
	}
	
	@SuppressWarnings("serial")
	private DefaultTableModel generarModeloDeTabla(ArrayList<Venta> ventas)
	{
		String[] columnas = new String[] {
			"Fecha y hora", "Tipo de combustible", "Litros cargados", "Monto facturado"
		};
		Object[][] celdas = new Object[ventas.size()][4];
		for (int i=0; i<ventas.size(); i++) {
			Venta venta = ventas.get(i);
			celdas[i] = new Object[] {
				venta.getFecha().toString(),	// Fecha y hora
				venta.getTipoDeCombustible().getNombreAMostrar(),	// Tipo de combustible
				venta.getLitrosCargados(),	// Litros cargados
				venta.getMontoFacturado()	// Monto facuturado
			};
		}
		
		return new DefaultTableModel( celdas, columnas ) {
			Class[] columnTypes = new Class[] {
				String.class, String.class, Float.class, Float.class
			};
			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
	}
	
	private void setearTabla(LocalDateTime fecha_inicio, LocalDateTime fecha_fin)
	{
		tabla_ventas.setEnabled(false);
		try {
			// Obtener la lista de ventas
			ArrayList<Venta> lista_ventas = estacion.obtenerListaDeVentas(fecha_inicio, fecha_fin);
			tabla_ventas.setModel( generarModeloDeTabla( lista_ventas ) );
			tabla_ventas.getColumnModel().getColumn(0).setPreferredWidth(137);
			tabla_ventas.getColumnModel().getColumn(1).setPreferredWidth(116);
			tabla_ventas.getColumnModel().getColumn(3).setPreferredWidth(93);
			
			// Guardar las listas y fechas
			this.lista_ventas = lista_ventas;
			this.fecha_inicio = fecha_inicio;
			this.fecha_fin = fecha_fin;
		} catch (ConsultarVentasException e) {
			// TODO Auto-generated catch block
			JOptionPane.showMessageDialog(this, e.getMessage(), "Error al consultar ventas", JOptionPane.ERROR_MESSAGE);
			//e.printStackTrace();
		}
		finally {
			tabla_ventas.setEnabled(true);
		}
	}
	
	private void actualizarTabla()
	{
		setearTabla(fecha_inicio, fecha_fin);
	}
}

class CampoFormatoFecha extends JFormattedTextField
{
	public CampoFormatoFecha() {
		super( DateTimeFormatter.ofPattern("dd/MM/yyyy").toFormat() );
		setFecha( LocalDate.now() );
	}
	
	public LocalDate getFecha() {
		return LocalDate.from( (TemporalAccessor)super.getValue() );
	}
	
	public void setFecha( LocalDate fecha ) {
		super.setValue( fecha );
	}
}
