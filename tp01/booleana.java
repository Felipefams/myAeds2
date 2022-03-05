import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class booleana {
    // devo acabar optando pelo solucao recursiva pra matar dois coelhos em uma cajadada
    // opcoes de solucao:
    // divide and conquer
    // usar stack ou recursao

    // os operadores estao todos funcionando
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

    //ideia do daniel pra deixar mais "elegante"
    public static boolean parseBoolExpr(String expression) {
        return parse(expression, 0, expression.length());
    }

    private static boolean parse(String s, int lo, int hi) {
        char c = s.charAt(lo);
        if (hi - lo == 1) return c == 't'; // base case.
        boolean ans = c == '&'; // only when c is &, set ans to true; otherwise false.
        for (int i = lo + 2, start = i, level = 0; i < hi; ++i) {
            char d = s.charAt(i);
            if (level == 0 && (d == ',' || d == ')')) { // locate a valid sub-expression. 
                boolean cur = parse(s, start, i); // recurse to sub-problem.
                start = i + 1; // next sub-expression start index.
                if (c == '&') ans = andOperator(ans,cur); 
                else if (c == '|') ans = orOperator(ans, cur);
                else ans = notOperator(cur); // c == '!'.
            }
            if (d == '(') ++level;
            if (d == ')') --level;
        }
        return ans;
    }
    
    public static void main(String[] args) {
        FastReader fr = new FastReader(); 
        /* nesse caso aqui, como quem fez o input nao quis ajudar colocando os int
           em uma linha diferente da string. vou ler tudo como String e fazer os parse dos integers 
        */
        String s;
        //como a gente sabe que vai ter um zero no input alguma hora, nao tem problema fazer o loop assim
        while(true){
            s = fr.nextLine();
            int n = Integer.parseInt(String.valueOf(s.charAt(0)));

            if(n == 0){break;}
            else if(n == 2){
                int a = Integer.parseInt(String.valueOf(s.charAt(2)));
                int b = Integer.parseInt(String.valueOf(s.charAt(4)));
                //parse
                // parseToTF(s, a, b);
                System.out.println(parseToTF(s, a, b));
                // System.out.println(parseBoolExpr(parseToTF(s, a, b))); teste line 
                //solve
            }else if (n == 3){
                int a = Integer.parseInt(String.valueOf(s.charAt(2)));
                int b = Integer.parseInt(String.valueOf(s.charAt(4)));
                int c = Integer.parseInt(String.valueOf(s.charAt(6)));
                //parse
                System.out.println(parseBoolExpr(parseToTF(s, a, b, c)));
                // System.out.println(parseToTF(s, a, b, c)); test line
                //function call 
            }
            //print after solved 
        }
    }

    static class FastReader {
        // BufferedReader br;
        StringTokenizer st;
        private static BufferedReader br = new BufferedReader(
                new InputStreamReader(System.in, StandardCharsets.ISO_8859_1));

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
