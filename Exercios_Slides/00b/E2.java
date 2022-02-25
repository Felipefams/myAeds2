import java.util.Scanner;
import java.lang.Math;
public class E2{

	public static boolean isInArray(int[]array, int x){
			/*o que podemos fazer para otimizar o problema, eh checar
			* se o numero do meio eh maior que o numero desejado, se sim
			* ir para tras, caso contrario ir para frente
			*/
		final int hfLen =(int) Math.ceil((double)array.length/2);
		if(hfLen < x){
			for(int i = 0; i < hfLen; ++i){
				if(array[i] == x)
					return true;
			}
		}
		else{
			for(int i = hfLen; i >= 0; --i){
				if(array[i] == x)
					return true;
			}
		}
			return false; 
	}

	public static void main (String[] args){
		//vou fazer usando o Scanner pq to sem a IO nesse computador
		Scanner sc = new Scanner(System.in);
		int x = sc.nextInt();
		int[] array = {1,2,3,4,5,6,7,8,9,10};
		System.out.println(isInArray(array, x)?"YES":"NO");
		sc.close();
	}
}
