import java.util.Scanner;
public class fib2{
	public static void main(String[] args){
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		System.out.println(fib(n));
		sc.close();
	}

	public static long fib(int n){
		if(n == 0){
			return 0;
		}else if(n == 1){
			return 1;
		}
		return fib(n-2) + fib(n-1);
	}
}
