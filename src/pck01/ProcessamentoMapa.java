package pck01;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.LineNumberReader;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JProgressBar;

public class ProcessamentoMapa extends Thread{

	public JProgressBar pgb;
	public JLabel lbLoading;
	public ArrayList<Vertice> listVertice;
	public JButton btIniciar;
	private int progress = 0;
	
	@Override
	public void run() {
		
		carregaVertice();
		
		Thread.interrupted();
		
	}
	
	
	public void carregaVertice()
	{
		
		try {
			
			//Pega o arquivo
			File arquivo = new File("./lista_vertice.txt");
						
			//Lê o número de linhas
			LineNumberReader nrLinha = new LineNumberReader(new FileReader("./lista_vertice.txt"));
			nrLinha.skip(arquivo.length());
			nrLinha.close();
			int qtdLinha = nrLinha.getLineNumber();
		
			pgb.setMaximum(qtdLinha);
			pgb.setMinimum(0);
			
			progress = 0;
			
			//Joga o arquivo para ser processado em memória
			BufferedReader in = new BufferedReader(new FileReader("./lista_vertice.txt"));
			
			//Variavel que controla string retornada por linha
			String str;

			while (in.ready()) 
			{
				progress ++;
				
				str = in.readLine();
				processaVertices(str);
				pgb.setValue(progress);
				
				//Thread.sleep(2);
				
			}
			
			in.close();
			
			pgb.setValue(100);
			
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		
		pgb.setValue(0);
		
		progress = 0;
		
		lbLoading.setText("Carregando Arestas...");
		
		carregaAresta();
		
		btIniciar.setVisible(true);
		
	}
	
	private void processaVertices(String str)
	{
		String[] linha = str.split(",");
		Vertice vertice = new Vertice(linha[0]);
		vertice.x = Integer.parseInt(linha[1]); //Coordenada X
		vertice.y = Integer.parseInt(linha[2]); //Coordenada Y
		listVertice.add(vertice);
	}
	
	private void carregaAresta()
	{

		try {
			
			//Pega o arquivo
			File arquivo = new File("./lista_aresta.txt");
						
			//L� o n�mero de linhas
			LineNumberReader nrLinha = new LineNumberReader(new FileReader("./lista_aresta.txt"));
			nrLinha.skip(arquivo.length());
			int qtdLinha = nrLinha.getLineNumber();
			nrLinha.close();
			
			pgb.setMaximum(qtdLinha);
			pgb.setMinimum(0);
						
			progress = 0;
			
			//Joga o arquivo para ser processado em mem�ria
			BufferedReader in = new BufferedReader(new FileReader("./lista_aresta.txt"));
			
			//Variavel que controla string retornada por linha
			String str;

			while (in.ready()) 
			{
				progress++;
				
				str = in.readLine();
				processaAresta(str);
				pgb.setValue(progress);
				
				//Thread.sleep(2);
				
			}
			
			in.close();
			
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}
	}
	
	private void processaAresta(String str)
	{
		
		String[] linha = str.split(",");
		Aresta aresta = new Aresta();
		
		for (Vertice v : listVertice) {
			if (v.nome.equals(linha[0])) 
			{
				for (Vertice vr : listVertice) {
					if (vr.nome.equals(linha[2])) 
					{
						aresta.custo = Integer.parseInt(linha[1]);
						aresta.trajeto = vr;
						v.vizinho.add(aresta);
					}
				}
			}
		}
		
	}
	
}
