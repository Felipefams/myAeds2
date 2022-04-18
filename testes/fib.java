import java.util.Scanner;
public class fib{
	private static long[] fib_cache;

	public static void main (String[] args){
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		fib_cache = new long[n + 1];
		System.out.println(fibonacci(n));
		sc.close();
	}

	public static long fibonacci(int n){
		if(n <= 1){
			return n;
		}
		if(fib_cache[n] != 0){
			return fib_cache[n];
		}
		long fibAns = fibonacci(n-2) + fibonacci(n-1);
		fib_cache[n] = fibAns;
		return fibAns;
	}

}
