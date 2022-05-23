public class Fila {
	private Celula primeiro;
	private Celula ultimo;
	public Fila() {
		primeiro = new Celula();
		ultimo = primeiro;
	}
	public void inserir(int x) {
		ultimo.prox = new Celula(x);
		ultimo = ultimo.prox;
	}
	public int remover() throws Exception {
		if (primeiro == ultimo) {
			throw new Exception("Erro ao remover!");
		}
		Celula tmp = primeiro;
		primeiro = primeiro.prox;
		int resp = primeiro.elemento;
		tmp.prox = null;
		tmp = null;
		return resp;
	}
	public void mostrar() {
		for(Celula i = primeiro.prox; i != null; i = i.prox) {
			System.out.print(i.elemento + " ");
		}
	}
}
