import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
public class is {
	public static boolean checkConsonants(String s){
		for(int i = 0; i < s.length(); i++){
			if(Character.isLetter(s.charAt(i))){
				if(s.charAt(i) == 'a' || s.charAt(i) == 'e' || s.charAt(i) == 'i' || s.charAt(i) == 'o' || s.charAt(i) == 'u'||s.charAt(i) == 'A' || s.charAt(i) == 'E' || s.charAt(i) == 'O' || s.charAt(i) == 'I' || s.charAt(i) == 'U'){
					return false;
					} 
			}else{return false;}
		}
	return true;
	}
	public static boolean checkVowels(String s){
		for(int i = 0; i < s.length(); i++){
			if(s.charAt(i) != 'a' && s.charAt(i) != 'e' && s.charAt(i) != 'i' && s.charAt(i) != 'o' && s.charAt(i) != 'u' && s.charAt(i) != 'A' && s.charAt(i) != 'E' && s.charAt(i) != 'O' && s.charAt(i) != 'I' && s.charAt(i) != 'U'){
				return false;	
			}
		}
		return true;
	}
	public static boolean checkInteger(String s){
		for(int i = 0; i < s.length(); i = i + 1){
			if(!Character.isDigit(s.charAt(i))){
				return false;	
			}
		}
		return true; 
	}
	public static boolean checkDouble(String s){
		int countComma = 0; 
		for(int i = 0; i < s.length(); i = i + 1){
			if(s.charAt(i) == ',' || s.charAt(i) == '.'){countComma++;}
			if(countComma > 1){return false;}
			if((!Character.isDigit(s.charAt(i)) && (s.charAt(i) != ',' && s.charAt(i) != '.'))){
				return false;
			}
		}
		return true;
	}
	public static void main(String[] args){
		FastReader fr = new FastReader();
		String s;
		boolean b = true;
		while(b){
			s = fr.nextLine();
			if(s.length() == 3 && (s.charAt(0) == 'F' && s.charAt(1) == 'I' && s.charAt(2) == 'M')){
				b = false;
			}else{
				System.out.print(checkVowels(s)?"SIM ":"NAO ");
				System.out.print(checkConsonants(s)?"SIM ":"NAO ");
				System.out.print(checkInteger(s)?"SIM ":"NAO ");
				System.out.print(checkDouble(s)?"SIM\n":"NAO\n");
			}
		}
	}

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
}
