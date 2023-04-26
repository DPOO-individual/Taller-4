package vista;

import com.formdev.flatlaf.FlatLightLaf;

public class app {

	public static void main(String[] args) {
		FlatLightLaf.install();
		General general = new General();
		general.setLocationRelativeTo( null );
		general.setVisible(true);
		

	}
}
