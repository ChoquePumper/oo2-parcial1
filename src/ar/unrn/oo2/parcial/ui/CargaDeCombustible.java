package ar.unrn.oo2.parcial.ui;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.SpringLayout;
import javax.swing.JPanel;

import ar.unrn.oo2.parcial.modelo.EstacionDeServicio;
import ar.unrn.oo2.parcial.modelo.ListaTiposDeCombustible;
import ar.unrn.oo2.parcial.modelo.Persistencia;
import ar.unrn.oo2.parcial.modelo.TipoDeCombustible;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Container;
import java.awt.LayoutManager;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;
import com.jgoodies.forms.layout.FormSpecs;
import java.awt.GridLayout;
import javax.swing.DefaultComboBoxModel;
import java.awt.event.ActionListener;
import java.time.LocalDateTime;
import java.awt.event.ActionEvent;

public class CargaDeCombustible extends JFrame {
	
	private EstacionDeServicio estacion; 
	
	private JButton btnConfirmar, btnConsultarMonto;
	private JTextField inputLitrosACargar;
	private JTextField roMonto;
	private JComboBox<cbElementoTiposDeCombustible> cbTipoDeCombustible;
	private LocalDateTime fecha;
	
	public CargaDeCombustible( EstacionDeServicio estacion )
	{
		super("Carga de combustible");
		this.estacion = estacion;
		//this.per = per;
		//this.lista_combustibles = lista_combustibles;
		setSize(256,164);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		getContentPane().setLayout(new BorderLayout(0, 0));
		
		JPanel centerPanel = new JPanel();
		getContentPane().add(centerPanel, BorderLayout.CENTER);
		SpringLayout sl_centerPanel = new SpringLayout();
		centerPanel.setLayout(sl_centerPanel);
		
		cbTipoDeCombustible = new JComboBox<cbElementoTiposDeCombustible>();
		cbTipoDeCombustible.setModel(new DefaultComboBoxModel(
				cbElementoTiposDeCombustible.generarLista( estacion.getListaDeTiposDeCombustible() ) ));
		sl_centerPanel.putConstraint(SpringLayout.NORTH, cbTipoDeCombustible, 10, SpringLayout.NORTH, centerPanel);
		sl_centerPanel.putConstraint(SpringLayout.EAST, cbTipoDeCombustible, -10, SpringLayout.EAST, centerPanel);
		centerPanel.add(cbTipoDeCombustible);
		
		inputLitrosACargar = new JTextField();
		sl_centerPanel.putConstraint(SpringLayout.NORTH, inputLitrosACargar, 6, SpringLayout.SOUTH, cbTipoDeCombustible);
		sl_centerPanel.putConstraint(SpringLayout.EAST, inputLitrosACargar, -10, SpringLayout.EAST, centerPanel);
		centerPanel.add(inputLitrosACargar);
		inputLitrosACargar.setColumns(10);
		
		roMonto = new JTextField();
		roMonto.setEditable(false);
		sl_centerPanel.putConstraint(SpringLayout.NORTH, roMonto, 6, SpringLayout.SOUTH, inputLitrosACargar);
		sl_centerPanel.putConstraint(SpringLayout.EAST, roMonto, -10, SpringLayout.EAST, centerPanel);
		centerPanel.add(roMonto);
		roMonto.setColumns(10);
		
		JLabel lblTipoDeCombustible = new JLabel("Tipo de combustible");
		sl_centerPanel.putConstraint(SpringLayout.WEST, roMonto, 8, SpringLayout.EAST, lblTipoDeCombustible);
		sl_centerPanel.putConstraint(SpringLayout.WEST, inputLitrosACargar, 8, SpringLayout.EAST, lblTipoDeCombustible);
		sl_centerPanel.putConstraint(SpringLayout.WEST, cbTipoDeCombustible, 8, SpringLayout.EAST, lblTipoDeCombustible);
		sl_centerPanel.putConstraint(SpringLayout.NORTH, lblTipoDeCombustible, 10, SpringLayout.NORTH, centerPanel);
		sl_centerPanel.putConstraint(SpringLayout.WEST, lblTipoDeCombustible, 10, SpringLayout.WEST, centerPanel);
		centerPanel.add(lblTipoDeCombustible);
		
		JLabel lblLitrosACargar = new JLabel("Litros a cargar");
		sl_centerPanel.putConstraint(SpringLayout.NORTH, lblLitrosACargar, 1, SpringLayout.NORTH, inputLitrosACargar);
		sl_centerPanel.putConstraint(SpringLayout.WEST, lblLitrosACargar, 0, SpringLayout.WEST, lblTipoDeCombustible);
		centerPanel.add(lblLitrosACargar);
		
		JLabel lblMonto = new JLabel("Monto ($)");
		sl_centerPanel.putConstraint(SpringLayout.NORTH, lblMonto, 1, SpringLayout.NORTH, roMonto);
		sl_centerPanel.putConstraint(SpringLayout.WEST, lblMonto, 0, SpringLayout.WEST, lblTipoDeCombustible);
		centerPanel.add(lblMonto);
		
		JPanel bottomPanel = new JPanel();
		getContentPane().add(bottomPanel, BorderLayout.SOUTH);
		
		JButton btnConsultarMonto_1 = new JButton("Consultar monto");
		btnConsultarMonto_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				accionConsultarMonto();
			}
		});
		bottomPanel.add(btnConsultarMonto_1);
		
		JButton btnConfirmar_1 = new JButton("Confirmar");
		btnConfirmar_1.setEnabled(false);
		bottomPanel.add(btnConfirmar_1);
		
		
	}
	
	private void accionConsultarMonto()
	{
		fecha = LocalDateTime.now();
		float monto = estacion.ConsultarMonto(
				cbTipoDeCombustible.getItemAt( cbTipoDeCombustible.getSelectedIndex() ).getObjeto(),
				fecha,
				Float.parseFloat( inputLitrosACargar.getText()) );
		roMonto.setText( Float.toString(monto) );
		
		//btnConfirmar.setEnabled(true);
	}
}

class cbElementoTiposDeCombustible
{
	private TipoDeCombustible tipo;
	
	private cbElementoTiposDeCombustible(TipoDeCombustible tipo)
	{
		this.tipo = tipo;
	}
	
	public TipoDeCombustible getObjeto() { return tipo; }
	
	public String toString() { return tipo.getNombreAMostrar(); }
	
	static cbElementoTiposDeCombustible[] generarLista( TipoDeCombustible[] lista )
	{
		cbElementoTiposDeCombustible[] lista_para_cb = new cbElementoTiposDeCombustible[lista.length];
		for (int i=0; i<lista.length; i++)
			lista_para_cb[i] = new cbElementoTiposDeCombustible(lista[i]);
		return lista_para_cb;
	}
}
