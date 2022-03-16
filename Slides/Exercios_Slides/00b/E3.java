public class E3{
	// uma opcao melhor seria usar um Pair<int,int> mas acredito que no momento essa ja baste. 
	public static void solve(int[]array){
		int max = 0;
		int min = Integer.MAX_VALUE;
		for(int x : array){
			if(x > max){
				max = x;
			}
			if(x < min){
				min = x;
			}
		}
		System.out.println("Max Value:" + max);
		System.out.println("Min Value:" + min);
	}

	public static void main (String[] args){
		//vou fazer usando o Scanner pq to sem a IO nesse computador
		int[] array = {1,2,3,4,5,6,7,8,9,10};
		solve(array);
	}
}
