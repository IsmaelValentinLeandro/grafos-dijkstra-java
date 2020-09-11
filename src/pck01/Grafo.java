package pck01;

import java.util.ArrayList;

public class Grafo {
	
	ArrayList<Vertice> listaVertice = new ArrayList<Vertice>();
	ArrayList<Aresta> listaAresta = new ArrayList<Aresta>();
	
	public ArrayList<Vertice> getListaVertice() {
		return listaVertice;
	}
	
	public void setVertice(Vertice listaVertice) {
		this.listaVertice.add(listaVertice);
	}
	
	public ArrayList<Aresta> getListaAresta() {
		return listaAresta;
	}
	
	public void setAresta(Aresta listaAresta) {
		this.listaAresta.add(listaAresta);
	}
	
}
