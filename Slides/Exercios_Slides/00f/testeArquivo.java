import entities.Arq;

//nao precisa dar include/package na Arq, ja que o programa e a arq estao no mesmo pacote
public class testeArquivo{
	public static <String> void main (String[] args){
		Arq.openWrite("exemplo.txt");

		Arq.println(1);
		Arq.println(5.3);
		Arq.println('X');
		Arq.println(true);
		Arq.println("Algoritmos");

		Arq.close();
	}
}
