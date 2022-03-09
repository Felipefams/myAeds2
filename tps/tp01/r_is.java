import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.StringTokenizer;
public class r_is {
	/*
	a ideia dos metodos recursivos eh basicamente a mesma em todos. roda a recursao, passa na posicao 0, retorna uma substring sem a posicao que acabou de passar
	*/
	public static boolean checkConsonants(String s){
		if(Character.isLetter(s.charAt(0))){
			if(s.charAt(0) != 'a' && s.charAt(0) != 'e' && s.charAt(0) != 'i' && s.charAt(0) != 'o' && s.charAt(0) != 'u'&&s.charAt(0) != 'A' && s.charAt(0) != 'E' && s.charAt(0) != 'O' && s.charAt(0) != 'I' && s.charAt(0) != 'U'){
			        if (s.length() == 0 || s.length() == 1) {
            return true;
        }
				return checkConsonants(s.substring(1, s.length() - 1));	
			}
		}
		return false;
	}

	public static boolean checkVowels(String s){
		if(Character.isLetter(s.charAt(0))){
			if(s.charAt(0) == 'a' || s.charAt(0) == 'e' || s.charAt(0) == 'i' || s.charAt(0) == 'o' || s.charAt(0) == 'u'|| s.charAt(0) == 'A' || s.charAt(0) == 'E' || s.charAt(0) == 'O' || s.charAt(0) == 'I' || s.charAt(0) == 'U'){
			        if (s.length() == 0 || s.length() == 1) {
            return true;
        }
				return checkVowels(s.substring(1, s.length() - 1));
			}
		}
		return false;
	}

	public static boolean checkInteger(String s){
	        if (s.length() == 0 || s.length() == 1) {
            return true;
        }
		if(Character.isDigit(s.charAt(0))){
			return checkInteger(s.substring(1, s.length() - 1));
		}
		return false;
	}

	public static boolean checkDouble(String s, int countComma){
		if(countComma > 1){
			return false;
		}
		        if (s.length() == 0 || s.length() == 1) {
            return true;
        }
		if(s.charAt(0) == ',' || s.charAt(0) == '.'){
			return checkDouble(s.substring(1, s.length() - 1), countComma + 1);
		}
		if(Character.isDigit(s.charAt(0))){
			return checkDouble(s.substring(1, s.length() - 1), countComma);	
		}
		return false;
	}

	public static boolean cd(String s){
		return checkDouble(s, 0);
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
				System.out.print(cd(s)?"SIM\n":"NAO\n");
			}
		}
	}
//biblioteca auxiliar
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
