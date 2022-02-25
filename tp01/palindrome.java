// Code by Felipe Augusto Morais Silva
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
// import entities.MyIO;
public class palindrome{
	//obs, a unica coisa que eu fiz fora do Vim for importar essa classe, pq ia dar mt trabalho pra identar
	static class FastReader {
        BufferedReader br;
        StringTokenizer st;
 
        public FastReader()
        {
            br = new BufferedReader(
                new InputStreamReader(System.in));
        }
 
        String next()
        {
            while (st == null || !st.hasMoreElements()) {
                try {
                    st = new StringTokenizer(br.readLine());
                }
                catch (IOException e) {
                    e.printStackTrace();
                }
            }
            return st.nextToken();
        }
 
        int nextInt() { return Integer.parseInt(next()); }
 
        long nextLong() { return Long.parseLong(next()); }
 
        double nextDouble()
        {
            return Double.parseDouble(next());
        }
 
        String nextLine()
        {
            String str = "";
            try {
                str = br.readLine();
            }
            catch (IOException e) {
                e.printStackTrace();
            }
            return str;
        }
    }
 

	public static boolean checkPalindrome(final String s){
		for(int a = 0, b = s.length() - 1; a < b; a++,b--){
			if(s.charAt(a) != s.charAt(b))
				return false;
		}
		return true;
	}
	
	public static void main(String[] args){
		FastReader fr = new FastReader();
		String s;// = MyIO.readString();
		boolean b = true;
		while(b){
			s = fr.nextLine();
			if(s.length() == 3 && (s.charAt(0) == 'F' && s.charAt(1) == 'I' && s.charAt(2) == 'M')){
				b = false;
			}
			else{
			System.out.println(checkPalindrome(s)?"SIM":"NAO");
			}
		}
	}
}
		
	
	


