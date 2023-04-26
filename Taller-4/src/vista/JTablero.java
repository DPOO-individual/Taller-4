package vista;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.LayoutManager;
import java.awt.Point;
import java.awt.TexturePaint;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.geom.Rectangle2D;
import java.awt.geom.RoundRectangle2D;
import java.awt.geom.RoundRectangle2D.Double;
import java.awt.image.BufferedImage;
import java.io.File;
import java.net.http.WebSocket.Listener;

import javax.imageio.ImageIO;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import uniandes.dpoo.taller4.modelo.Tablero;

public class JTablero extends JPanel implements MouseListener, MouseMotionListener {
	
	private int tamaño;
	private General general;
	private int tamañoTotal;
	private Tablero tablero;
    private BufferedImage image;
    private int[] presente;
    
	
	public JTablero(int tamaño, General general) {
		
		this.general = general;
		
		setBackground(Color.black);
		addMouseListener(this);
		addMouseMotionListener(this);
				
		this.tamaño = tamaño;
		
		presente = new int[2];

		
		presente[0] = tamaño;
		presente[1] = tamaño;
		
		tamañoTotal = 630;

		tablero = new Tablero(tamaño);
		tablero.desordenar(0);

		
		
		   try {
	            // Cargar la imagen desde un archivo
	            image = ImageIO.read(new File("./data/luz.png"));
	        } catch (Exception ex) {
	            ex.printStackTrace();
	        }
		
	}
	
	
	
	public void setTamaño(int tamaño) {
		this.tamaño = tamaño;
		tablero = new Tablero(tamaño);
		repaint();
	} 
	
	public void reiniciar() {
		tablero.reiniciar();
		general.barraInferior.setJugadas(-1);
		repaint();
	}
	
	
	public void nuevo() {
		int dificultad = general.getDificultad();
		tablero = new Tablero(tamaño);
		tablero.desordenar(dificultad);
		general.barraInferior.setJugadas(-1);
		repaint();
	}

	public void paint(Graphics g) {
		super.paint(g);
		Graphics2D g2d = (Graphics2D) g;
		int tRect = (int)630/tamaño;
		RoundRectangle2D.Double rect ;
		RoundRectangle2D.Double rectImg ;
		TexturePaint texture;
	    Color darkGray = new Color(80, 80, 80);
	    Color black = new Color(10, 10, 10);

		
		boolean[][] jugadas = tablero.darTablero();
		for (int x = 0; x < tamaño; x++ ) {
			for (int y = 0; y < tamaño; y++ ) {
				rect = new RoundRectangle2D.Double(x*tRect+5, y*tRect+5, tRect-10, tRect-10,15,15);
				rectImg = new RoundRectangle2D.Double((x*tRect)+20, (y*tRect)+20, tRect-40, tRect-40,15,15);

				if (presente[0] == x && presente[1] == y) {
					GradientPaint gradient = new GradientPaint((float) rect.getMinX(), (float) rect.getMinY(), Color.decode("#1E90FF"),(float) rect.getMaxX(),(float) rect.getMaxY(), Color.decode("#87CEEB"));
					g2d.setPaint(gradient);
					g2d.fill(rect);
				} else if (jugadas[x][y]) {
					// relleno
					
					g2d.setColor(Color.decode("#F9E076"));
					g2d.fill(rect);
					
					texture = new TexturePaint(image, rectImg.getBounds());
					g2d.setPaint(texture);
					g2d.fill(rectImg);
					
			       
				}
				else {
					// relleno
					GradientPaint gradient = new GradientPaint((float) rect.getMinX(), (float) rect.getMinY(), black,(float) rect.getMaxX(),(float) rect.getMaxY(), darkGray);
					g2d.setPaint(gradient);
					g2d.fill(rect);
			       
					
				}			
				g2d.setColor(darkGray);
				g2d.draw(rect);;
			}
			
		}
		
	}
	
	public Tablero darTablero() {
		return this.tablero;
	}



	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		if (e!= null) {
			boolean hayJugador = general.checkJugador();
			if (hayJugador) {
				int tRect = (int)630/tamaño;
				int x = e.getX()/tRect;
				int y = e.getY()/tRect;
				tablero.jugar(x, y);
				int jugadas = tablero.darJugadas();
				general.barraInferior.setJugadas(jugadas-1);
				general.revisarGanador();
				repaint();
			}
			else {
				JOptionPane.showMessageDialog(null, "Ingresa tu nombre en el cuadro de abajo\n a la derecha para poder continuar.", "Ingresar nombre", JOptionPane.INFORMATION_MESSAGE);
			}
		}

		
		
	}



	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub

	}



	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub

	}



	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub

	}



	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stu
		presente[0] = tamaño;
		presente[1] = tamaño;
		repaint();

	}



	@Override
	public void mouseDragged(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}



	@Override
	public void mouseMoved(MouseEvent e) {
		// TODO Auto-generated method stub
		if (e!= null) {
			int tRect = (int)630/tamaño;
			presente[0] = e.getX()/tRect;
			presente[1] = e.getY()/tRect;

			repaint();			

		} 
	}

}
