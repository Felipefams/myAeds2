import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.ByteBuffer;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class bcpy {
    //opcoes:
    //divide and conquer
    //usar stack ou recursao
	static int x; 
	static String s;

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

    public static String parseToTF(String text, int a, int b, int c) {
        String v = text.replaceAll("and", "&");
        String nv = v.replaceAll("not", "!");
        String mv = nv.replaceAll("or", "|");
        StringBuilder sb = new StringBuilder(mv);
        for (int i = 0; i < mv.length(); i++) {

            if (mv.charAt(i) == 'A') {
                sb.setCharAt(i, (a == 1) ? 't' : 'f');
            } else if (mv.charAt(i) == 'B') {
                sb.setCharAt(i, (b == 1) ? 't' : 'f');
            } else if (mv.charAt(i) == 'C') {
                sb.setCharAt(i, (c == 1) ? 't' : 'f');
            }
        }
        return sb.toString();
    }

    public static String parseToTF(String text, int a, int b) {
        String v = text.replaceAll("and", "&");
        String nv = v.replaceAll("not", "!");
        String mv = nv.replaceAll("or", "|");
        StringBuilder sb = new StringBuilder(mv);
        for (int i = 0; i < mv.length(); i++) {
            if (mv.charAt(i) == 'A') {
                sb.setCharAt(i, (a == 1) ? 't' : 'f');
            } else if (mv.charAt(i) == 'B') {
                sb.setCharAt(i, (b == 1) ? 't' : 'f');
            }
        }
        return sb.toString();
    }

    public static boolean parseBoolExpr(String text) {
	//global variables	
	    x = 0;
	    s = text; 
        return parse();
    }

    public static boolean parse(){
	    char op = s.charAt(x++);
	    List<Boolean> bools = new ArrayList<>();
	    while (x < s.length()){
		char c = s.charAt(x++);
		if(c == 't' || c =='f'){
			bools.add(c == 't');
		}
		else if(c == '|' || c == '&' || c == '!'){
			x--;
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
        boolean result = (op == '|')? false : true;
        for(Boolean bool : bools){
            result = (op == '|')? orOperator(result, bool):andOperator(result, bool);
        }
        return result;
    }

    public static void main(String[] args) {
        FastReader fr = new FastReader();
        /* nesse caso aqui, como quem fez o input nao quis ajudar colocando os int
           em uma linha diferente da string. vou ler tudo como String e fazer os parse dos integers 
        */
        String text;
        //como a gente sabe que vai ter um zero no input alguma hora, nao tem problema fazer o loop assim
        while(true){
            text = fr.nextLine();
            int n = Integer.parseInt(String.valueOf(text.charAt(0)));

            if(n == 0){break;}
            else if(n == 2){
                int a = Integer.parseInt(String.valueOf(text.charAt(2)));
                int b = Integer.parseInt(String.valueOf(text.charAt(4)));
                // solve 
                System.out.println(parseBoolExpr(parseToTF(text, a, b))?1:0);
            }else if (n == 3){
                int a = Integer.parseInt(String.valueOf(text.charAt(2)));
                int b = Integer.parseInt(String.valueOf(text.charAt(4)));
                int c = Integer.parseInt(String.valueOf(text.charAt(6)));
                // solve
                System.out.println(parseBoolExpr(parseToTF(text, a, b, c))?1:0);
            }
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
