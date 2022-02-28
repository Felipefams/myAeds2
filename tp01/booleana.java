import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.ByteBuffer;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.StringTokenizer;

public class booleana {
//divide and conquer
//usar stack e recursao
	int i; 
	string s;
    public static boolean orOperator(boolean... a) {
        for (boolean i : a) {
            if (i) {
                return true;
            }
        }
        return false;
    }

    public static boolean notOperator(boolean a) {
        return !a;
    }

    public static boolean andOperator(boolean... a) {
        for (int i = 1; i < a.length; i++) {
            if (a[i] != a[i - 1]) {
                return false;
            }
        }
        return true;
    }

    public static StringBuilder parseToTF(String text, int a, int b, int c) {
        StringBuilder sb = new StringBuilder(text);
	sb = text.replaceAll("and", "&");
	sb = text.replaceAll("not", "!");
	sb = text.replaceAll("or", "|");
        for (int i = 0; i < text.length(); i++) {

            if (text.charAt(i) == 'A') {
		sb.setCharAt(i, (a == 1)?'t':'f');
                sb.setCharAt(i, (char) ((char) a + '0'));
            } else if (text.charAt(i) == 'B') {
		sb.setCharAt(i, (b == 1)?'t':'f');
            } else if (text.charAt(i) == 'C') {	
		sb.setCharAt(i, (c == 1)?'t':'f');
            }
        }
        return sb.toString();
    }


    public static boolean parseBool(String text) {
	//global variables	
	i = 0;
	s = text; 
        return parse();
    }

    public static boolean parse(){
	    char op = s.charAt(i++);
	    List<Boolean> bools = new ArrayList();
	    while (i < s.length()){
		char c = s.charAt(i++);
		if(c == 't' || c =='f'){
			bools.add(c == 't');
		}
		else if(c == '|' || c == '&' || c == '!'){
			i--;
			bools.add(parse());
		}
		else if( c == ')'){
			break;
		}
	    }
	    return eval(bools, op);
    }

    public static boolean eval(List<Boolean> bools, char op){
		if(op == '!'){
			return notOperator(bools.get(0)); 
		}
		if(op == '&'){
			return andOperator(bools.get(0));
		}
		if(op == '|'){
			return orOperator(bools.get(0));
		}
    }

    public static void main(String[] args) {
        FastReader fr = new FastReader();
        //ja que a gente sabe que n vai ser 0 alguma hora, nao tem problema fazer o loop assim
        //essa nao eh a melhor solucao, ja que so vai funcionar pra no maximo n = 3;
        while (true) {
            int n = fr.nextInt();
            if (n == 0) {
                break;
            } else if (n == 2) {
                int a = fr.nextInt();
                int b = fr.nextInt();
                String text = fr.nextLine();
                //rodar a funcao
            } else if (n == 3) {
                int a = fr.nextInt();
                int b = fr.nextInt();
                int c = fr.nextInt();
                String text = fr.nextLine();
		parseBoolExpr(parseToTF(text, a, b, c));
            }
            /* isso aqui seria a ideia ideal, pra caso pudessem ter inumeras variaveis.
               while (n-- > 0) {
                int v1 = fr.nextInt();
            */
        }
    }

    static class FastReader {
        //        BufferedReader br;
        StringTokenizer st;
        private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in, StandardCharsets.ISO_8859_1));

        public static void setCharset(String charset_) {
            br = new BufferedReader(new InputStreamReader(System.in, Charset.forName(charset_)));
        }

        public FastReader() {
            br = new BufferedReader(
                    new InputStreamReader(System.in));
        }

        String next() {
            while (st == null || !st.hasMoreElements()) {
                try {
                    st = new StringTokenizer(br.readLine());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            return st.nextToken();
        }

        int nextInt() {
            return Integer.parseInt(next());
        }

        long nextLong() {
            return Long.parseLong(next());
        }

        double nextDouble() {
            return Double.parseDouble(next());
        }

        String nextLine() {
            String str = "";
            try {
                str = br.readLine();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return str;
        }
    }

}
