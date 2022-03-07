import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.Stack;
import java.util.StringTokenizer;

public class booleana {
    // devo acabar optando pelo solucao recursiva pra matar dois coelhos em uma
    // cajadada
    // opcoes de solucao:
    // divide and conquer
    // usar stack ou recursao
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

    /*
     * expr model: 2 1 1 and(not(A) , not(B)) 3 0 0 0 or(and(A , B , C) , and(or(A ,
     * B) , C))
     */
    public static boolean parseBoolExpr(String expr, int index, int length) {
        // vou de tras pra frente
        // index = length - 1 - index; acho que vai ser melhor controlar o index pelas
        // chamadas
        // condicao de parada
        // if (length == 1 || length == 0) {
        // resolvido
        // }
        if (expr.charAt(0) == 't') {
            return true;
        } else if (expr.charAt(0) == 'f') {
            return false;
        }
        if (expr.charAt(index) == '(') {
            boolean found = false;
            int count = 0;
            while (!found) {
                if (expr.charAt(index + count) == ')') {
                    break;
                }
                count = count + 1;
            }
            // boolean pra salvar o resultado da substring
            // small result
            boolean smres = parseKuai(expr.substring(index - 1, index + count + 1));
            // length vai ser length - count (nao vai precisar fazer - count + 1, pq tem que
            // ficar 1 espaco pro resultado
            StringBuilder sb = new StringBuilder(expr);// expr.toCharArray();
            // o ideal eh fazer por fora pra reduzir o numero de comparacoes, mas depois eu
            // melhoro
            // trocar a substring pelo resultado da substring e chamar o metodo
            if (smres) {
                for (int i = index - 1; i <= index + count; i++) {
                    // charArr[i] = (smres)?'t':'f';
                    sb.setCharAt(i, 't');
                }
            } else {
                for (int i = index - 1; i <= index + count; i++) {
                    // charArr[i] = (smres)?'t':'f';
                    sb.setCharAt(i, 'f');
                }
            }
            // string sem a parte que ja foi resolvida
            String newStr = sb.toString();
            // chamada recursiva com a nova string
            return parseBoolExpr(newStr, length - 1, newStr.length());
        }
        return parseBoolExpr(expr, index - 1, expr.length());
    }

    /*
     * geralmente nao eh uma boa pratica ter varios retornos.
     * mas como eh uma logica simples, nao tem tanto problema.
     * Aqui a gente nao vai ter o problema da ordem tambem, ja
     * que essa expressao serve pra dar parse em uma expressao com
     * so 1 operador
     */
    public static boolean parseKuai(String substr) {
        // so pra ter um retorno no final
        boolean b = false;
        // vamos checar cada elemento
        if (substr.charAt(0) == '&') {
            // na comparacao and, se tiver 1 falso ja da falso
            for (int i = 0; i < substr.length(); i++) {
                if (substr.charAt(i) == 'f') {
                    return false;
                }
            }
            return true;
        } else if (substr.charAt(0) == '|') {
            // na comparacao or, se tiver 1 true ja da verdadeiro
            for (int i = 0; i < substr.length(); i++) {
                if (substr.charAt(i) == 't') {
                    return true;
                }
            }
            return false;
        }
        // na comparacao not, so vai ter um elemento, entao se a gente achar um F
        // retorna true e um T retorna false;
        for (int i = 0; i < substr.length(); i++) {
            if (substr.charAt(i) == 't') {
                b = false;
                break;
            } else if (substr.charAt(i) == 'f') {
                b = true;
                break;
            }
        }

        return b;
    }

    public static void main(String[] args) {
        FastReader fr = new FastReader();
        /*
         * nesse caso aqui, como quem fez o input nao quis ajudar colocando os int
         * em uma linha diferente da string. vou ler tudo como String e fazer os parse
         * dos integers
         */
        String s;
        // como a gente sabe que vai ter um zero no input alguma hora, nao tem problema
        // fazer o loop assim
        while (true) {
            s = fr.nextLine();
            int n = Integer.parseInt(String.valueOf(s.charAt(0)));

            if (n == 0) {
                break;
            } else if (n == 2) {
                int a = Integer.parseInt(String.valueOf(s.charAt(2)));
                int b = Integer.parseInt(String.valueOf(s.charAt(4)));
                // parse
                // parseToTF(s, a, b);
                String newStr = parseToTF(s, a, b).replaceAll(" ", "");
                String newestStr = newStr.replaceAll("\\d", "");
                System.out.println(parseBoolExpr(newestStr, newestStr.length() - 1, newestStr.length())?1:0);
                // System.out.println(parseBoolExpr(parseToTF(s, a, b))); teste line
                // solve
            } else if (n == 3) {
                int a = Integer.parseInt(String.valueOf(s.charAt(2)));
                int b = Integer.parseInt(String.valueOf(s.charAt(4)));
                int c = Integer.parseInt(String.valueOf(s.charAt(6)));
                // parse
                String newStr = parseToTF(s, a, b, c).replaceAll(" ", "");
                String newestStr = newStr.replaceAll("\\d", "");
                System.out.println(parseBoolExpr(newestStr, newestStr.length() - 1, newestStr.length())?1:0);
                // System.out.println(parseToTF(s, a, b, c)); test line
                // function call
            }
            // print after solved
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
