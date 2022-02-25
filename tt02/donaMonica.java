import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.StringTokenizer;

/*
OBS: eu sei que vcs falaram pra comentar, mas por enquanto os problemas estao tao bobos que qualquer comentario me parece inutil.
receber o input acaba sendo uma dor de cabeca maior que o problema em si...
 */
public class donaMonica {
    public static int solve(int monica, int filho1, int filho2) {
        int filho3 = monica - (filho1 + filho2);
        return Math.max(filho1,Math.max(filho2,filho3));
    }

    public static void main(String[] args) {
        FastReader fr = new FastReader();
        int monica = 1;
//        int f1 = 0;   eu tinha colocado aqui fora, mas o ideal eh colocar dentro mesmo.
//        int f2 = 0;
        while (monica != 0) {
            monica = fr.nextInt();
            if (monica != 0) {
                int f1 = fr.nextInt();
                int f2 = fr.nextInt();
                System.out.println(solve(monica, f1, f2));
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
