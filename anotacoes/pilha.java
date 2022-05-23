public class Pilha {
	private Celula topo;
	public Pilha() {topo = null;}
	public void inserir(int x){Celula tmp = new Celula(x); tmp.prox = topo; topo = tmp; tmp = null;}
	public int remover() throws Exception {
		if (topo == null) {throw new Exception("Erro ao remover!");}
		int resp = topo.elemento;
		Celula tmp = topo;
		topo = topo.prox;
		tmp.prox = null;
		tmp = null;
		return resp;
	}
	public void mostrar() {
		for (Celula i = topo; i != null; i = i.prox) {
			System.out.print(i.elemento + " ");
		}
	}
	public int getSoma() {
		return getSoma(topo);
	}
	private int getSoma(Celula i) {
		int resp = 0;
		if (i != null) {
			resp += i.elemento + getSoma(i.prox);
		}
		return resp;
	}
	public int getMax() {
		int max = topo.elemento;
		for (Celula i = topo.prox; i != null; i = i.prox) {
			if (i.elemento > max)
				max = i.elemento;
		}
		return max;
	}
}
