package pck01;

import java.awt.Font;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextPane;

import java.awt.event.MouseAdapter;
import java.awt.Color;

import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.Window.Type;

public class JFrameMarcaMapa extends JFramePAI {

	private JPanel contentPane;
	private JPanel panelMapa;
	private JLabel mapa;
	private int count = 0;
	private JTextPane log;
	private boolean controle;
	private boolean tipo;
	private boolean mapaCarregado = false;
	public ArrayList<Vertice> listVertice;
	Vertice verticePrimario = null; //V�rtice de liga��o 
	private JTextPane logArestas;
	private JButton btnArestas;
	private JButton btnVertice;

	public JFrameMarcaMapa() {
		setType(Type.NORMAL);
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosed(WindowEvent arg0) {
				JFrameMain form = new JFrameMain();
				JOptionPane.showMessageDialog(null, "LOL");
				form.show();
			}
		});
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1166, 657);
		contentPane = new JPanel();
		contentPane.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent ponto) {
				pegaXY(ponto);
			}
		});
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		panelMapa = new JPanel();
		panelMapa.setBounds(0, 0, 959, 625);
		contentPane.add(panelMapa);
		panelMapa.setLayout(null);
		
		mapa = new JLabel("New label");
		mapa.setIcon(new ImageIcon(JFrameMarcaMapa.class.getResource("/img/mapa.jpg")));
		mapa.setBounds(0, 0, 959, 625);
		panelMapa.add(mapa);
		
		log = new JTextPane();
		log.setFont(new Font("Tahoma", Font.PLAIN, 7));
		log.setForeground(Color.BLACK);
		log.setBackground(Color.WHITE);
		log.setBounds(969, 54, 64, 563);
		contentPane.add(log);
		
		JLabel lblVrtice = new JLabel("V\u00E9rtice");
		lblVrtice.setBounds(969, 37, 46, 14);
		contentPane.add(lblVrtice);
		
		JLabel lblArestas = new JLabel("Arestas");
		lblArestas.setBounds(1043, 37, 46, 14);
		contentPane.add(lblArestas);
		
		logArestas = new JTextPane();
		logArestas.setForeground(Color.BLACK);
		logArestas.setFont(new Font("Tahoma", Font.PLAIN, 7));
		logArestas.setBackground(Color.WHITE);
		logArestas.setBounds(1043, 54, 107, 563);
		contentPane.add(logArestas);
		
		btnVertice = new JButton("V\u00E9rtice");
		btnVertice.setBackground(Color.LIGHT_GRAY);
		btnVertice.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				liberaBotao(true);
			}
		});
		btnVertice.setFont(new Font("Tahoma", Font.PLAIN, 10));
		btnVertice.setBounds(969, 3, 64, 23);
		contentPane.add(btnVertice);
		
		btnArestas = new JButton("Arestas");
		btnArestas.setBackground(Color.LIGHT_GRAY);
		btnArestas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				liberaBotao(false);
			}
		});
		btnArestas.setFont(new Font("Tahoma", Font.PLAIN, 10));
		btnArestas.setBounds(1043, 3, 107, 23);
		contentPane.add(btnArestas);
		
		centralizaJanela();
	}
	
	private void liberaBotao(boolean isVertice)
	{
		//Faz o controle da aplica��o
		if (isVertice)
		{
			this.controle = true;
			this.tipo = true;
		}
		else
		{
			this.controle = true;
			this.tipo = false;
			verticePrimario = null;
			btnArestas.setText("Nova Origem");
				
		}
	}
	
	private void pegaXY(MouseEvent ponto)
	{

		//Pega as coordenadas do click do mouse
		int x = ponto.getX();
		int y = ponto.getY();
		
		double distancia = 0;
		double menorDistancia = 99999;
		
		if (controle)
		{
			if (tipo)
			{
				//Mapeia os vórtices
								
				//Da um nome aos vórtices
				String nomeVertice = "v"+count;
				
				//Cria label que representa o ponto no mapa
				JLabel pontoxy = new JLabel(nomeVertice);
				pontoxy.setFont(new Font("Tahoma", Font.PLAIN, 9));
				pontoxy.setIcon(new ImageIcon(JFrameMarcaMapa.class.getResource("/img/verde.png")));
				
				//Adiciona a label ao mapa
				mapa.add(pontoxy);
				
				//Da as coordenadas para a label dentro do mapa
				pontoxy.setBounds(x-4, y-10, 100, 20);
				
				log.setText(log.getText()+nomeVertice+","+x+","+y+"\r\n");
				
				count++;
			}
			else
			{
				//Mapeia as arestas

				//Verifica se existe mais de um v�rtice cadastrado para mapear as arestas
				if (listVertice.size() > 1)
				{
					
					//Verifica se ja carregou o mapa
					if (!mapaCarregado)
					{
						
						for (Vertice v : listVertice) {
							v.pontoMapa = new JLabel(v.nome);
							v.pontoMapa.setFont(new Font("Tahoma", Font.PLAIN, 9));
							v.pontoMapa.setIcon(new ImageIcon(JFrameMapa.class.getResource("/img/verde.png")));
							v.pontoMapa.setForeground(Color.LIGHT_GRAY);
							mapa.add(v.pontoMapa);
							v.pontoMapa.setBounds(v.x-4, v.y-6, 100, 15);
						}
						
						mapaCarregado = true;
						
					}
					else
					{
						
						//Verifica o v�rtice mais pr�ximo do click
						Vertice vertice = null;
						
						for (Vertice v : listVertice) {
							
							//Calcula a distancia do click
							distancia = Math.sqrt( Math.pow( (v.x - x),2 ) + Math.pow( (v.y - y),2 ) );
							
							//Verifica se a distancia � a menor que ja foi calculada anteriormente
							if ( menorDistancia > distancia )
							{
								menorDistancia = distancia;
								vertice = v;
							}
						}
						
						//Verifica se o v�rtice prim�rio j� foi selecionado
						if (verticePrimario == null)
						{
							for (Vertice v : listVertice) {

								v.pontoMapa.setIcon(new ImageIcon(JFrameMapa.class.getResource("/img/verde.png")));
								
							}
							verticePrimario = vertice;
							
							//Deixa o v�rtice origem com icone de alerta
							verticePrimario.pontoMapa.setIcon(new ImageIcon(JFrameMapa.class.getResource("/img/alerta.gif")));
						}
						else
						{
						
							//Gera a liga��o
							String custo = JOptionPane.showInputDialog("Aresta de ["+verticePrimario.nome+"] at� ["+vertice.nome+"]:");
							
							if (!custo.equals(""))
							{
								//Deixa o v�rtice destino com �vone vermelho
								vertice.pontoMapa.setIcon(new ImageIcon(JFrameMapa.class.getResource("/img/vermelho.png")));
								
								//Grava no log
								logArestas.setText(verticePrimario.nome+","+custo+","+vertice.nome+"\r\n"+logArestas.getText());
							}
						}
						
					}
				}
				else
				{
					JOptionPane.showMessageDialog(this, "Para criar arestas é necessário ao menos um vórtice cadastrado.");
				}
				
			}
			
		}
		
	}
	
}
