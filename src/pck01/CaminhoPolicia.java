package pck01;

import java.util.ArrayList;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextPane;

public class CaminhoPolicia extends Thread
{
	
	public Vertice saida;
	public Vertice chegada;
	public JLabel policia;
	public JLabel ladrao;
	public JTextPane log1;
	public JTextPane log2;
	public ArrayList<Vertice> caminho;
	private int count = 0 ;
	
	@Override
	public void run() {
		try
		{
			count++;
			log1.setText(log1.getText()+"  ["+count+"] "+saida.nome+"\r\n");
			for (Vertice vertice : caminho) 
			{
				
				Thread.sleep(500);
				if (!vertice.equals(saida))
					policia.setBounds(vertice.x-38, vertice.y-93, 100, 100);
				
				if (!vertice.equals(saida) && !vertice.equals(chegada))
				{
					if (count <= 15)
						log1.setText(log1.getText()+"  ["+count+"] "+vertice.nome+"\r\n");
					else
						log2.setText(log2.getText()+"  ["+count+"] "+vertice.nome+"\r\n");
				}
				
				count++;
				
			}
			
			ladrao.setVisible(false);
			policia.setVisible(false);
			
			if (count <= 15)
				log1.setText(log1.getText()+"  ["+count+"] "+chegada.nome+"\r\n");
			else
				log2.setText(log2.getText()+"  ["+count+"] "+chegada.nome+"\r\n");
			
			Thread.interrupted();

			JOptionPane.showMessageDialog(null, "O ladrão foi pego com sucesso!!! "
					+ "\r\nGraçaas ao algoritmo DIJIKSTRA que lhe mostrou o menor caminho.");
			JOptionPane.showMessageDialog(null, "E graças ao ISMAEL que programou ele :P");
			JOptionPane.showMessageDialog(null, "Para rodar novamente feche a janela do mapa e clieque em "
					+ "\r\niniciar na janela principal.");
			
			
		}
		catch(Exception e)
		{
			Thread.interrupted();
			e.printStackTrace();
		}
	}

}
