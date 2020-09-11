package pck01;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.ImageIcon;
import javax.swing.JProgressBar;
import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.awt.Color;
import java.awt.Toolkit;

public class JFrameMain extends JFramePAI
{
	
	/*
	 * Grafos
	 * Ismael Valentin
	 * 2020
	 */

	private JPanel contentPane;
	private JProgressBar pgb;
	private ArrayList<Vertice> listVertice = new ArrayList<Vertice>();
	private JLabel lbLoading;
	private JButton btIniciar;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					JFrameMain frame = new JFrameMain();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public JFrameMain() 
	{
		setType(Type.NORMAL);
		setIconImage(Toolkit.getDefaultToolkit().getImage(JFrameMain.class.getResource("/img/ladrao.png")));
		setTitle("Pega Ladr\u00E3o");
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 505, 427);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		pgb = new JProgressBar();
		pgb.setBounds(234, 367, 255, 20);
		contentPane.add(pgb);
		pgb.setStringPainted(true);
		pgb.setVisible(false);
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.DARK_GRAY);
		panel.setBounds(234, 343, 255, 23);
		contentPane.add(panel);
		panel.setVisible(false);
		
		lbLoading = new JLabel("New label");
		panel.add(lbLoading);
		lbLoading.setForeground(Color.WHITE);
		lbLoading.setBackground(Color.GRAY);
		
		btIniciar = new JButton("INICIAR");
		btIniciar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				iniciaMapa();
			}
		});
		btIniciar.setBackground(Color.LIGHT_GRAY);
		btIniciar.setBounds(255, 364, 114, 23);
		contentPane.add(btIniciar);
		
		JButton btnMarcarMapa = new JButton("Marcar Mapa");
		btnMarcarMapa.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				JFrameMarcaMapa mp = new JFrameMarcaMapa();
				mp.listVertice = listVertice;
				mp.show();
			}
		});
		btnMarcarMapa.setBackground(Color.LIGHT_GRAY);
		btnMarcarMapa.setBounds(375, 364, 114, 23);
		contentPane.add(btnMarcarMapa);
		
		JLabel lblNewLabel = new JLabel("New label");
		lblNewLabel.setIcon(new ImageIcon(JFrameMain.class.getResource("/img/PegaLadrao.jpg")));
		lblNewLabel.setBounds(0, 0, 500, 400);
		contentPane.add(lblNewLabel);
		
		btIniciar.setVisible(false);
		
		this.centralizaJanela();
		
		loading();

	}
	
	private void loading()
	{
		
		lbLoading.setText("Carregando V�rtices...");
		
		pgb.setValue(0);
		
		ProcessamentoMapa processa = new ProcessamentoMapa();
		processa.listVertice = listVertice;
		processa.pgb = pgb;
		processa.lbLoading = lbLoading;
		processa.btIniciar = btIniciar;		
		processa.start();
	}
	
	private void iniciaMapa()
	{
		JFrameMapa map = new JFrameMapa();
		map.mostraPontosPelaLista(listVertice);
		map.show();
		JOptionPane.showMessageDialog(this, "Clique em um ponto que é a origem da polícia, depois "
				+ "\r\nclique em outro ponto que será o destino onde o ladrão esta.");
		this.dispose();
	}
}




