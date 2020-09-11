package pck01;

import javax.swing.JFrame;

public class JFramePAI extends JFrame {

	/**
	 * Create the frame.
	 */
	public JFramePAI() {
		setResizable(false);
		setType(Type.UTILITY);
		//Chama método para centralizar a janela
		centralizaJanela();
		
	}	
	
	//Método para deixar a janela no centro da tela
	protected void centralizaJanela()
	{
		this.setLocationRelativeTo(null);
	}

}
