package ar.unrn.oo2.parcial.ui;

import javax.swing.JFrame;

import ar.unrn.oo2.parcial.modelo.EstacionDeServicio;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class VentanaPrincipal extends JFrame {
	private EstacionDeServicio estacion;
	
	public VentanaPrincipal( EstacionDeServicio estacion )
	{
		super("Estación de servicio");
		setSize(256,128);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.estacion = estacion;
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu mnVentanas = new JMenu("Ventanas");
		menuBar.add(mnVentanas);
		
		JMenuItem mntmRegistrarVentas = new JMenuItem("Registrar ventas");
		mntmRegistrarVentas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new CargaDeCombustible(estacion).setVisible(true);
			}
		});
		mnVentanas.add(mntmRegistrarVentas);
		
		JMenuItem mntmConsultaDeVentas = new JMenuItem("Consulta de ventas");
		mntmConsultaDeVentas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new ConsultaDeVentas(estacion).setVisible(true);
			}
		});
		mnVentanas.add(mntmConsultaDeVentas);
	}
}
