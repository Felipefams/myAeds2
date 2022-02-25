import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.ByteBuffer;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.StringTokenizer;

public class booleana {


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

    public static StringBuilder parseLetters(String text, int a, int b, int c) {
        StringBuilder sb = new StringBuilder(text);
        for (int i = 0; i < text.length(); i++) {
            if (text.charAt(i) == 'A') {
                sb.setCharAt(i, (char) ((char) a + '0'));
            } else if (text.charAt(i) == 'B') {
                sb.setCharAt(i, (char) ((char) b + '0'));
            } else if (text.charAt(i) == 'C') {
                sb.setCharAt(i, (char) ((char) c + '0'));
            }
        }
        return sb;
    }

    public static void solveNotOps(StringBuilder text) {
        System.out.println(text);
        //checa a string pra ver se tem algum not(0) ou not(1)
        boolean b = false;
        for (int i = 0; i < text.length(); i++) {
            if (text.length() - i > 6) {
                if (text.charAt(i) == 'n' && text.charAt(i + 1) == 'o' && text.charAt(i + 2) == 't') {
                    //tem que fazer esse tanto de checagem pra ver se dentro do not so tem um parametro
                    //nao da pra usar ternario pq tem que ser elseif ali em baixo
                    if (text.charAt(i + 4) == '0') {
                        text.replace(i, i + 6, "1");
                    } else if (text.charAt(i + 4) == '1') {
                        text.replace(i, i + 6, "1");
                    }
                }
            }
        }
    }

    public static boolean solve(String text) {
        char[] array = text.toCharArray();
        return true;
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
