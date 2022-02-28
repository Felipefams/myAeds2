// Code by Felipe Augusto Morais Silva

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.RandomAccessFile;
import java.util.StringTokenizer;

public class file {
    public static String mirror_substring(StringBuilder s){
        String[] newS = s.toString().split("\n");
        StringBuilder text = new StringBuilder();
        for(int i = 0; i < newS.length;i++){
            StringBuilder sb = new StringBuilder(newS[i]);
            text.append(sb.reverse());
            if(i != 0){
                text.append("\n");
            }
        }
        return text.toString();
    }
    public static void rev_fPrintln(String filename, int lines) throws Exception {
        RandomAccessFile raf = new RandomAccessFile(filename, "r");
        StringBuilder bd = new StringBuilder();
        //posiciona o cursor no byte desejado
        int readLines = 0;
        long k = raf.length() - 1;
        raf.seek(k);
        for(long pointer = k; pointer >= 0; pointer--){
            raf.seek(pointer);
            char c; 
            //vai lendo byte a byte 
            c = (char) raf.read();
            //se o char for o sinal pra pular de linha, conta 1+ linha lida
            if(c == '\n'){
                readLines++;
                if(readLines == lines){
                    break;
                }
            }
            bd.append(c);
        }
        System.out.print(mirror_substring(bd));
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
            String str;
            if(k - Math.floor(k) == 0){
                int c = (int) k; 
                str = Integer.toString(c);
            }else{
                str = Double.toString(k);
            }
            raf.writeBytes(str + "\n");
            
        }
        rev_fPrintln(filename, n);
        raf.close();
    }

    //vou colocar minhas documentacoes aqui em baixo, pq no vim da mais trabalho pra descer ate aqui static class FastReader {
    public static class FastReader{
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
