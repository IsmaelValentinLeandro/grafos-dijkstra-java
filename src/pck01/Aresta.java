package pck01;

public class Aresta {
	public Vertice trajeto;
	public double custo;
	
	public Aresta()
	{
		this(null, 0);
	}
	
	public Aresta(Vertice trajeto, double custo)
	{ 
		this.trajeto = trajeto; 
		this.custo = custo;
	}
}
