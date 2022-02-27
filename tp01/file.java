// Code by Felipe Augusto Morais Silva

import java.io.*;
import java.util.StringTokenizer;

public class file {
    public static void rev_fPrintln(String filename, int lines) throws Exception {
        RandomAccessFile raf = new RandomAccessFile(filename, "r");
        //posiciona o cursor no byte desejado
        int k = (byte) raf.length() - 1;
        raf.seek(k);
        while(k-- > 0){
            char s = raf.readChar();
	    System.out.print(s);
            if(s == '\n'){
                System.out.println();
            }
        }
	raf.close();
    }

    public static void main(String[] args) throws Exception {
        FastReader fr = new FastReader();
        String filename = "file.txt";
        int n = fr.nextInt();
        RandomAccessFile raf = new RandomAccessFile(filename, "rw");
        boolean b = true;
        while (n-- > 0) {
            double k = fr.nextDouble();
            String str = Double.toString(k);
            //cada char eh 1 byte, cada \n eh 2 bytes, entao se eu quiser os bytes por linha, basta fazer length()+2
//            chars = str.length() + 2;
            raf.writeBytes(str + "\n");
        }
        rev_fPrintln(filename, n);
        raf.close();
    }

    //vou colocar minhas documentacoes aqui em baixo, pq no vim da mais trabalho pra descer ate aqui static class FastReader {
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
