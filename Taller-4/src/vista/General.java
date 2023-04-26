package vista;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.LayoutManager;
import java.io.File;
import java.net.http.WebSocket.Listener;
import java.util.Collection;

import javax.swing.Box;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import uniandes.dpoo.taller4.modelo.RegistroTop10;
import uniandes.dpoo.taller4.modelo.Top10;

public class General extends JFrame implements Listener {

	PanelConf panelConf;
	
	JTablero tablero;
	
	Menu menu;
	
	Barra barraInferior;
	Top10 top10;

	
	public General() {
		setSize(800, 765);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setResizable(false);
		setLayout(new BorderLayout());
		
		panelConf = new PanelConf(this);
		add(panelConf, BorderLayout.NORTH);
		
		
		tablero = new JTablero(5,this);
		add(tablero, BorderLayout.CENTER);
		
		menu = new Menu(this);
		add(menu,BorderLayout.EAST);
		
		barraInferior = new Barra(this);
		add(barraInferior,BorderLayout.SOUTH);
	
		top10 = new Top10();

	}


	public void setTamañoTablero(int i) {
		tablero.setTamaño(i);
		
	}
	
	public void reiniciar() {
		tablero.reiniciar();
	}
	
	public int getDificultad() {
		return panelConf.getDificultad();
	}


	public void nuevo() {
		tablero.nuevo();
		
	}


	public void top10() {
		JTop10 Jtop10 = new JTop10(this);
		Jtop10.setVisible(true);
		
	}
	
	public void cargarTop10() {
		top10.cargarRecords(new File("./data/top10.csv"));
	}
	
	public Collection<RegistroTop10> getTop() {
		return top10.darRegistros();
	}
	
	public void revisarGanador() {
		boolean ganador = tablero.darTablero().tableroIluminado();
		if (ganador) {
			JOptionPane.showMessageDialog(null, "¡Ganaste!\nGracias por jugar :)", "¡Felcidades!", JOptionPane.INFORMATION_MESSAGE);
		}
	}
	
	public boolean checkJugador() {
		boolean nombre = barraInferior.darJugador();
		if (nombre) {
			return false;
		}
		else {
			return true;
		}
	}
	
	
}
