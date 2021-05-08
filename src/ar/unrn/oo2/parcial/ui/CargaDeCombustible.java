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

import ar.unrn.oo2.parcial.modelo.ListaTiposDeCombustible;
import ar.unrn.oo2.parcial.modelo.Persistencia;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Container;
import java.awt.LayoutManager;

public class CargaDeCombustible extends JFrame {
	private Persistencia per;
	ListaTiposDeCombustible lista_combustibles;
	
	private JComboBox<?> cbTipoDeCombustible;
	private JTextField inputLitrosACargar;
	private JTextField roMonto; // Read-only
	private JButton btnConfirmar, btnConsultarMonto;
	
	public CargaDeCombustible( Persistencia per, ListaTiposDeCombustible lista_combustibles )
	{
		super("Carga de combustible");
		this.per = per;
		this.lista_combustibles = lista_combustibles;
		setSize(256,192);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		BorderLayout b = new BorderLayout();
		setLayout(b);
		
		Container pane = getContentPane();
		
		JPanel centerPanel = new JPanel();
		SpringLayout spl = new SpringLayout();
		centerPanel.setLayout(spl);
		centerPanel.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
		//centerPanel.add();
		cbTipoDeCombustible = new JComboBox();
		inputLitrosACargar = new JTextField();
		
		Component[][] componentes = {
				{new JLabel("Tipo de combustible"), cbTipoDeCombustible},
				{new JLabel("Litros a cargar"), inputLitrosACargar},
		};
		
		for (Component[] fila: componentes)
			for(Component col: fila)
				centerPanel.add(col);
		
		for (int i_fila=0; i_fila < componentes.length; i_fila++) {
			Component[] fila = componentes[i_fila];
			
			spl.putConstraint(SpringLayout.WEST, fila[0],
					5, SpringLayout.WEST, pane);
			spl.putConstraint(SpringLayout.WEST, fila[1],
					5, SpringLayout.EAST, fila[0]);
			spl.putConstraint(SpringLayout.EAST, fila[1],
					0, SpringLayout.EAST, pane);
		}
		
		
		//SpringUtilities;
		//spl.putConstraint(SpringLayout.WEST, pane, ABORT, getName(), centerPanel);
		
		JPanel botones = new JPanel();
		botones.setLayout(new BoxLayout(botones, BoxLayout.X_AXIS));
		botones.setBorder(BorderFactory.createEmptyBorder(0,10,10,10));
		btnConsultarMonto = new JButton("Consultar monto");
		btnConfirmar = new JButton("Confirmar");
		botones.add( btnConsultarMonto );
		botones.add( btnConfirmar );
		
		pane.add(centerPanel,BorderLayout.CENTER);
		pane.add(botones, BorderLayout.SOUTH);
	}
	
}
