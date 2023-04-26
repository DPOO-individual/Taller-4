package vista;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.LayoutManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Menu extends JPanel implements ActionListener {
	private final Color colorFondo = Color.decode("#69a8f5");
	private General general;
	
	public Menu(General general) {
		// TODO Auto-generated constructor stub
		
		this.general = general;
		
		setPreferredSize(new Dimension(150, 630));
		setLayout(new GridLayout(20, 3));
		
		
		JButton nuevo = new JButton("Nuevo");
		JButton reiniciar = new JButton("Reiniciar");
		JButton top = new JButton("Top-10");
		JButton CambJugador = new JButton("Cambiar jugador");
		
		JButton [] botones = {nuevo, reiniciar, top, CambJugador};
		
		for (JButton boton : botones) {
			boton.setBackground(colorFondo);
			boton.addActionListener( this );
		}
		
		add(new JLabel( ));
		add(new JLabel( ));
		add(new JLabel( ));
		add(nuevo);
		add(new JLabel( ));
		add(reiniciar);
		add(new JLabel( ));
		add(top);
		add(new JLabel( ));
		add(CambJugador);
		add(new JLabel( ));
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
		String command = e.getActionCommand();
		
		switch (command) {
		case "Nuevo":
			general.nuevo();
			break;
		case "Reiniciar":
			
			general.reiniciar();
			
			break;
		case "Top-10":
			general.top10();
			break;
		case "Cambiar jugador":
			break;

		default:
			break;
		}
		

	}

}
