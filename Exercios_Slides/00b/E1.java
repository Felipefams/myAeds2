import java.util.Scanner;
public class E1{
	public static boolean isInArray(int[]array, int x){
		for(int z : array){
			if(z == x)
				return true;
		}
		return false;
	}
	public static void main (String[] args){
		//vou fazer usando o Scanner pq to sem a IO nesse computador
		Scanner sc = new Scanner(System.in);
		int x = sc.nextInt();
		int[] array = {11,4,5,6,7};
		System.out.println(isInArray(array, x)?"YES":"NO");
		sc.close();
	}
}
