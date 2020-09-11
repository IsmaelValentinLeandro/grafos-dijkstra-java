package pck01;

import java.util.ArrayList;

import javax.swing.JLabel;

public class Vertice implements Comparable<Vertice>{
	public int x;
	public int y;
	public JLabel pontoMapa;
	public final String nome;
	public Vertice anterior;
	public ArrayList<Aresta> vizinho = new ArrayList<Aresta>();
	public double distanciaMinima = Double.POSITIVE_INFINITY;
	public ArrayList<Aresta> listaAresta;
	
	public Vertice(String nome) { 
		this.nome = nome; 
	}
	
	public String toString() { 
		return this.nome; 
	}
	
	public ArrayList<Aresta> getVizinhos() {
		return vizinho;
	}

	public void setVizinho(ArrayList<Aresta> vizinho) {
		this.vizinho = vizinho;
	}

	//Verifica se o vórtice tem a distancia menor
	public int compareTo(Vertice vertice)
	{
		return Double.compare(distanciaMinima, vertice.distanciaMinima);
	}

}
