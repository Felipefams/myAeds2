//import javafx.util.Pair; eu usaria a classe pronta do javafx, mas como nao temos a classe, da pra criar nossa propria
import java.lang.Math;
public class E4{
	/*Bom, o que realmente reduz o numero de comparacoes nessa solucao em comparacao com a ultima eh o primeior if ali pro tamanho do array. O uso dos pares acabou so deixando a solucao mais complexa mesmo. Acho que so compensa usar quando for um par <Integer, String> por exemplo. De qualquer forma, vou enviar assim.*/
	public static class Pair {
	//como eh uma classe bem simples so pra resolver esse problema, nao vou fazer encapsulamento
		int min;
		int max;
		public Pair (int min,int max){
			this.min = min; 
			this.max = max;
		}
	}

	public static Pair solve (int[] array){
		Pair pair = new Pair(array[0],array[0]);
		if(array.length == 1){
			return pair;
		}
		else
		{
			pair.max = 0;
			pair.min = array[0];
			for(int i = 0; i < array.length; i++){
				if(array[i] < pair.min)
					pair.min = array[i];
				if(array[i] > pair.max)
					pair.max = array[i];
			}
		}
		return pair;
	}

	public static void main (String[] args){
		int[] array = {20,2,3,4,5,6,7,8,9,10};
		Pair tmp = solve(array);
		System.out.println("Min = " + tmp.min + "\nMax = " + tmp.max);
	}
}
