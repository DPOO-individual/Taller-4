package vista;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Insets;
import java.awt.LayoutManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.http.WebSocket.Listener;

import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JToggleButton;
import javax.swing.border.Border;
import javax.swing.AbstractButton;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.ButtonModel;
import javax.swing.Icon;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

public class PanelConf extends JPanel implements ActionListener {

	final Color colorFondo = Color.decode("#69a8f5");
	JComboBox<String> listTama�o;
	General general;
	ButtonGroup difGRupo;
	
	public PanelConf(General general) {
		this.general = general;
		setBackground(colorFondo);		
		setLayout(new FlowLayout(FlowLayout.CENTER, 15, 10)); // selecionar una distibuci�n
		
		JLabel size = new JLabel("Tama�o:"); // crea el label de tama�o
		add(size); // a�ade el label
		
		// COMBO BOX --------------------------
		String[] optionsTama�o = {"5x5", "7x7", "9x9"}; // Tama�os
		listTama�o = new JComboBox<>(optionsTama�o); // creamos el comboBox
		listTama�o.setPreferredSize(new Dimension(100, 25));
		add(listTama�o); // a�ade el comboBox
		listTama�o.addActionListener( this );
		listTama�o.setActionCommand("listTama�o");
		
		// Radio Buttons ------------
		JLabel difLabel = new JLabel("Dificultad:"); // crea el label de tama�o
		add(difLabel); // a�ade el label
		difGRupo = new ButtonGroup();
		

		createRadioButtons();
		
		
	}

	public void createRadioButtons() {

		JRadioButton facilButton = new JRadioButton();
		JRadioButton medioButton = new JRadioButton();
		JRadioButton dificilButton = new JRadioButton();		
				
		difGRupo.add(facilButton); // a�ade el boton facil al grupo
		difGRupo.add(medioButton);// a�ade el boton medio al grupo
		difGRupo.add(dificilButton);// a�ade el boton dificil al grupo
		
		facilButton.setText("Facil");
		facilButton.setActionCommand("Facil");
		medioButton.setText("Medio");
		medioButton.setActionCommand("Medio");
		dificilButton.setText("Dificil");
		dificilButton.setActionCommand("Dificil");
		
		add(facilButton);
		add(medioButton);
		add(dificilButton);
		
		facilButton.setSelected(true);
	}

	public int getDificultad() {
		ButtonModel selected = difGRupo.getSelection();
		String dificultad = selected.getActionCommand();
		int num = 0;
		
		switch (dificultad) {
		case "Facil":
			num = 1;
			break;
		case "Medio":
			num = 10;
			break;
		case "Dificil":
			num = 20;
			break;

		default:
			break;
		}
		
		return num;
	}
	
	@Override
    public void actionPerformed( ActionEvent e )
    {
		   String[] selecionado = ((String)listTama�o.getSelectedItem()).split("");
		   //JOptionPane.showMessageDialog( this, selecionado );
		   
		   general.setTama�oTablero(Integer.parseInt(selecionado[0]));
		   
		
	}
}
