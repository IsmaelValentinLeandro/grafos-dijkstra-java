package pck01;

import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.ImageIcon;

import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.JTextPane;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.SystemColor;

public class JFrameMapa extends JFramePAI {

	private JPanel contentPane;
	public  JPanel panelGraph;
	private JLabel mapa;
	private JLabel policia;
	private JLabel ladrao;
	private ArrayList<Vertice> listVertice = new ArrayList<>();
	private Vertice saida = null;
	private Vertice chegada = null;
	private JTextPane log;
	private int qtdClique; 
	private JTextPane log1;
	private JTextPane log2;
	
	public JFrameMapa() {
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosed(WindowEvent arg0) {
				JFrameMain form = new JFrameMain();
				form.show();
			}
		});
		
		setTitle("<<Pega Ladrão>> Ismael Valentin");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 1162, 651);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(220, 220, 220));
		contentPane.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				//L� o ponto do clique do usu�rio
				if (qtdClique < 2)
				{
					pontoXY(arg0);
					qtdClique++;
				}
			}
		});
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		panelGraph = new JPanel();
		panelGraph.setLocation(0, 0);
		contentPane.add(panelGraph);
		panelGraph.setSize(960,637);
		panelGraph.setBackground(Color.WHITE);
		panelGraph.setLayout(null);
		
		policia = new JLabel("");
		policia.setIcon(new ImageIcon(JFrameMapa.class.getResource("/img/policia.png")));
		policia.setVisible(false);
		
		ladrao = new JLabel("");
		ladrao.setIcon(new ImageIcon(JFrameMapa.class.getResource("/img/ladrao.png")));
		ladrao.setVisible(false);
		
		panelGraph.add(policia);
		panelGraph.add(ladrao);
		
		mapa = new JLabel("New label");
		mapa.setIcon(new ImageIcon(JFrameMapa.class.getResource("/img/mapa.jpg")));
		mapa.setBounds(0, 0, 960, 626);
		panelGraph.add(mapa);
		
		JLabel lblNewLabel_2 = new JLabel("New label");
		lblNewLabel_2.setBounds(58, 28, 46, 14);
		panelGraph.add(lblNewLabel_2);
		
		log = new JTextPane();
		log.setBounds(973, 202, 166, 126);
		contentPane.add(log);
		log.setForeground(new Color(255, 255, 0));
		log.setText("Selecione um caminho");
		log.setBackground(new Color(105, 105, 105));
		log.setEditable(false);
		log.setFont(new Font("Arial Narrow", Font.BOLD, 13));
		
		JLabel label = new JLabel("");
		label.setIcon(new ImageIcon(JFrameMapa.class.getResource("/img/policia_way.png")));
		label.setBounds(970, 11, 176, 218);
		contentPane.add(label);
		
		log1 = new JTextPane();
		log1.setForeground(Color.YELLOW);
		log1.setFont(new Font("Arial Narrow", Font.BOLD, 13));
		log1.setEditable(false);
		log1.setBackground(SystemColor.controlDkShadow);
		log1.setBounds(973, 328, 84, 252);
		contentPane.add(log1);
		
		log2 = new JTextPane();
		log2.setForeground(Color.YELLOW);
		log2.setFont(new Font("Arial Narrow", Font.BOLD, 13));
		log2.setEditable(false);
		log2.setBackground(SystemColor.controlDkShadow);
		log2.setBounds(1055, 328, 84, 252);
		contentPane.add(log2);
		
		JLabel label_1 = new JLabel("");
		label_1.setIcon(new ImageIcon(JFrameMapa.class.getResource("/img/fundo.png")));
		label_1.setBounds(971, 545, 169, 66);
		contentPane.add(label_1);
		
		centralizaJanela();
		
	}
	
	
	private void pontoXY(MouseEvent ponto)
	{
		
		//Pega as coordenadas X e Y do clique
		int x = ponto.getX();
		int y = ponto.getY();

		//Cria vari�veis para calcular o ponto mais pr�ximo do clique do usu�rio
		double distancia = 0;
		double menorDistancia = 99999;
		
		//V�rtice que usu�rio selecionou
		Vertice vertice = null;
		
		//Calcula o v�rtice mais pr�ximo do clique
		for (Vertice v : listVertice) {

			distancia = Math.sqrt( Math.pow( (v.x - x),2 ) + Math.pow( (v.y - y),2 ) );
			
			if ( menorDistancia > distancia )
			{
				menorDistancia = distancia;
				//Caso o v�rtice for menor que o anterior atribui para o objeto vertice
				vertice = v;
			}
			
		}
		
		//Caso o v�rtice de sa�da for nulo vai selecionar a pol�cia
		if (saida == null)
		{			
			policia.setBounds(vertice.x-38, vertice.y-93, 100, 100);
			policia.setVisible(true);
			saida = vertice;
			vertice.pontoMapa.setIcon(new ImageIcon(JFrameMapa.class.getResource("/img/azul.png")));
			log.setText("Ponto de sa�da:\r\n "+vertice.nome+"\r\n");
		}
		// caso v�rtice de chegada for nulo vai selecionar o ladr�o
		else if (chegada == null)
		{
			ladrao.setBounds(vertice.x-38, vertice.y-93, 100, 100);
			ladrao.setVisible(true);
			chegada = vertice;
			vertice.pontoMapa.setIcon(new ImageIcon(JFrameMapa.class.getResource("/img/vermelho.png")));
			log.setText(log.getText()+"Ponto de Chegada:\r\n "+vertice.nome+"\r\n");
		}
		
		try
		{
		
			if (saida != null && chegada != null)
			{
				
				if (chegada.equals(saida))
				{
					saida = null;
					chegada = null;
					vertice.pontoMapa.setIcon(null);
					pontoXY(ponto);
				}
				
				processaCaminho();
			}
		
		}
		catch(Exception e)
		{ 
			e.printStackTrace(); 
		}

	}
	
	public void mostraPontosPelaLista(ArrayList<Vertice> listVertice)
	{
		
		for (Vertice v : listVertice) {
			v.pontoMapa = new JLabel(v.nome);
			v.pontoMapa.setFont(new Font("Tahoma", Font.PLAIN, 9));
			v.pontoMapa.setIcon(new ImageIcon(JFrameMapa.class.getResource("/img/verde.png")));
			v.pontoMapa.setBounds(v.x-4, v.y-6, 100, 15);
			v.pontoMapa.setForeground(Color.LIGHT_GRAY);
			mapa.add(v.pontoMapa);
		}
		
		this.listVertice = listVertice;
		
	}
	
	public void processaCaminho()
	{		
		
		try
		{
			
			if (chegada == null)
				throw new Exception("Vórtice de chegada é nulo!");
			
			Dijkstra dijkstra = new Dijkstra();
			dijkstra.processaCaminho(saida);
			
			ArrayList<Vertice> caminho = dijkstra.getMenorCaminho(chegada);
	
			log.setText(log.getText()+"Distancia total:\r\n "+chegada.distanciaMinima+"\r\n");
			log.setText(log.getText()+"Tempo total: [Segundos]\r\n");
	
			for (Vertice vertice : caminho) 
			{
				if (!vertice.equals(saida) && !vertice.equals(chegada))
					vertice.pontoMapa.setIcon(new ImageIcon(JFrameMapa.class.getResource("/img/alerta.gif")));
			}
	
			ativaCaminhadaPolicial(caminho);
			
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		
	}
	
	private void ativaCaminhadaPolicial(ArrayList<Vertice> caminho)
	{
		//Executa Thread da policia se movendo no mapa
		CaminhoPolicia cm = new CaminhoPolicia();
		cm.saida = saida;
		cm.chegada = chegada;
		cm.policia = policia;
		cm.ladrao = ladrao;
		cm.log1 = log1;
		cm.log2 = log2;
		cm.caminho = caminho;
		cm.start();
	}
}



