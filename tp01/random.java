// Code by Felipe Augusto Morais Silva

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Random;
import java.util.StringTokenizer;

public class random {
    //eu tenho certeza que a logica esta certa.
    public static StringBuilder solve(String s) {
        Random rGen = new Random();
        rGen.setSeed(4);
        final char tmp = (char) ('a' + (Math.abs(rGen.nextInt()) % 26));
        final char cng = (char) ('a' + (Math.abs(rGen.nextInt()) % 26));
        StringBuilder newSb = new StringBuilder(s);
        //troca todos os caracteres == 'tmp' da string pelo 2 caractere sorteado(cng)
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == tmp) {
                newSb.setCharAt(i, cng);
            }
        }
        return newSb;
    }

    public static void main(String[] args) {
        FastReader fr = new FastReader();
        String s;// = MyIO.readString();
        boolean b = true;
        while (b) {
            s = fr.nextLine();
            if (s.length() == 3 && (s.charAt(0) == 'F' && s.charAt(1) == 'I' && s.charAt(2) == 'M')) {
                b = false;
            } else {
                System.out.println(solve(s));
            }
        }
    }

    //vou colocar minhas documentacoes aqui em baixo, pq no vim da mais trabalho pra descer ate aqui
    static class FastReader {
        BufferedReader br;
        StringTokenizer st;

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
