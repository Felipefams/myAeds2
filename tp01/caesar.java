// Code by Felipe Augusto Morais Silva
import java.io.*;
import java.nio.ByteBuffer;
//import java.nio.charset.Charset;
//import java.nio.charset.StandardCharsets;
import java.util.StringTokenizer;
import java.io.*;
import java.nio.charset.*;

public class caesar {

    public static class FastReader {
//        BufferedReader br;
        StringTokenizer st;
        private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in, StandardCharsets.ISO_8859_1));
        private static final String charset = "ISO-8859-1";
        public static void setCharset(String charset_){
            br = new BufferedReader(new InputStreamReader(System.in, Charset.forName(charset_)));
        }
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

    public static class MyIO {

        private static BufferedReader in = new BufferedReader(new InputStreamReader(System.in, Charset.forName("ISO-8859-1")));
        private static String charset = "ISO-8859-1";

        public static void setCharset(String charset_){
            charset = charset_;
            in = new BufferedReader(new InputStreamReader(System.in, Charset.forName(charset)));
        }

        public static void print(){
        }

        public static void print(int x){
            try {
                PrintStream out = new PrintStream(System.out, true, charset);
                out.print(x);
            }catch(UnsupportedEncodingException e){ System.out.println("Erro: charset invalido"); }
        }

        public static void print(float x){
            try {
                PrintStream out = new PrintStream(System.out, true, charset);
                out.print(x);
            }catch(UnsupportedEncodingException e){ System.out.println("Erro: charset invalido"); }
        }

        public static void print(double x){
            try {
                PrintStream out = new PrintStream(System.out, true, charset);
                out.print(x);
            }catch(UnsupportedEncodingException e){ System.out.println("Erro: charset invalido"); }
        }

        public static void print(String x){
            try {
                PrintStream out = new PrintStream(System.out, true, charset);
                out.print(x);
            }catch(UnsupportedEncodingException e){ System.out.println("Erro: charset invalido"); }
        }

        public static void print(boolean x){
            try {
                PrintStream out = new PrintStream(System.out, true, charset);
                out.print(x);
            }catch(UnsupportedEncodingException e){ System.out.println("Erro: charset invalido"); }
        }

        public static void print(char x){
            try {
                PrintStream out = new PrintStream(System.out, true, charset);
                out.print(x);
            }catch(UnsupportedEncodingException e){ System.out.println("Erro: charset invalido"); }
        }

        public static void println(){
        }

        public static void println(int x){
            try {
                PrintStream out = new PrintStream(System.out, true, charset);
                out.println(x);
            }catch(UnsupportedEncodingException e){ System.out.println("Erro: charset invalido"); }
        }

        public static void println(float x){
            try {
                PrintStream out = new PrintStream(System.out, true, charset);
                out.println(x);
            }catch(UnsupportedEncodingException e){ System.out.println("Erro: charset invalido"); }
        }

        public static void println(double x){
            try {
                PrintStream out = new PrintStream(System.out, true, charset);
                out.println(x);
            }catch(UnsupportedEncodingException e){ System.out.println("Erro: charset invalido"); }
        }

        public static void println(String x){
            try {
                PrintStream out = new PrintStream(System.out, true, charset);
                out.println(x);
            }catch(UnsupportedEncodingException e){ System.out.println("Erro: charset invalido"); }
        }

        public static void println(boolean x){
            try {
                PrintStream out = new PrintStream(System.out, true, charset);
                out.println(x);
            }catch(UnsupportedEncodingException e){ System.out.println("Erro: charset invalido"); }
        }

        public static void println(char x){
            try {
                PrintStream out = new PrintStream(System.out, true, charset);
                out.println(x);
            }catch(UnsupportedEncodingException e){ System.out.println("Erro: charset invalido"); }
        }

        public static void printf(String formato, double x){
            try {
                PrintStream out = new PrintStream(System.out, true, charset);
                out.printf(formato, x);// "%.2f"
            }catch(UnsupportedEncodingException e){ System.out.println("Erro: charset invalido"); }
        }

        public static double readDouble(){
            double d = -1;
            try{
                d = Double.parseDouble(readString().trim().replace(",","."));
            }catch(Exception e){}
            return d;
        }

        public static double readDouble(String str){
            try {
                PrintStream out = new PrintStream(System.out, true, charset);
                out.print(str);
            }catch(UnsupportedEncodingException e){ System.out.println("Erro: charset invalido"); }
            return readDouble();
        }

        public static float readFloat(){
            return (float) readDouble();
        }

        public static float readFloat(String str){
            return (float) readDouble(str);
        }

        public static int readInt(){
            int i = -1;
            try{
                i = Integer.parseInt(readString().trim());
            }catch(Exception e){}
            return i;
        }

        public static int readInt(String str){
            try {
                PrintStream out = new PrintStream(System.out, true, charset);
                out.print(str);
            }catch(UnsupportedEncodingException e){ System.out.println("Erro: charset invalido"); }
            return readInt();
        }

        public static String readString(){
            String s = "";
            char tmp;
            try{
                do{
                    tmp = (char)in.read();
                    if(tmp != '\n' && tmp != ' ' && tmp != 13){
                        s += tmp;
                    }
                }while(tmp != '\n' && tmp != ' ');
            }catch(IOException ioe){
                System.out.println("lerPalavra: " + ioe.getMessage());
            }
            return s;
        }

        public static String readString(String str){
            try {
                PrintStream out = new PrintStream(System.out, true, charset);
                out.print(str);
            }catch(UnsupportedEncodingException e){ System.out.println("Erro: charset invalido"); }
            return readString();
        }

        public static String readLine(){
            String s = "";
            char tmp;
            try{
                do{
                    tmp = (char)in.read();
                    if(tmp != '\n' && tmp != 13){
                        s += tmp;
                    }
                }while(tmp != '\n');
            }catch(IOException ioe){
                System.out.println("lerPalavra: " + ioe.getMessage());
            }
            return s;
        }

        public static String readLine(String str){
            try {
                PrintStream out = new PrintStream(System.out, true, charset);
                out.print(str);
            }catch(UnsupportedEncodingException e){ System.out.println("Erro: charset invalido"); }
            return readLine();
        }

        public static char readChar(){
            char resp = ' ';
            try{
                resp  = (char)in.read();
            }catch(Exception e){}
            return resp;
        }

        public static char readChar(String str){
            try {
                PrintStream out = new PrintStream(System.out, true, charset);
                out.print(str);
            }catch(UnsupportedEncodingException e){ System.out.println("Erro: charset invalido"); }
            return readChar();
        }

        public static boolean readBoolean(){
            boolean resp = false;
            String str = "";

            try{
                str = readString();
            }catch(Exception e){}

            if(str.equals("true") || str.equals("TRUE") || str.equals("t") || str.equals("1") ||
                    str.equals("verdadeiro") || str.equals("VERDADEIRO") || str.equals("V")){
                resp = true;
            }

            return resp;
        }

        public static boolean readBoolean(String str){
            try {
                PrintStream out = new PrintStream(System.out, true, charset);
                out.print(str);
            }catch(UnsupportedEncodingException e){ System.out.println("Erro: charset invalido"); }
            return readBoolean();
        }

        public static void pause(){
            try{
                in.read();
            }catch(Exception e){}
        }

        public static void pause(String str){
            try {
                PrintStream out = new PrintStream(System.out, true, charset);
                out.print(str);
            }catch(UnsupportedEncodingException e){ System.out.println("Erro: charset invalido"); }
            pause();
        }
    }

    public static String toCesar(String s){
        //isso aqui vai dar na mesma de printar (char) string.charAt(i) + 3;
	//mas pra evitar problema vou fazer outro metodo ali em baixo pra so printar + 3...
        StringBuilder newString = new StringBuilder(s);
        for(int i = 0; i < s.length(); ++i){
            newString.setCharAt(i, (char) (s.charAt(i)+3));
        }
        return newString.toString();
    }
    public static void toCesar2(String s){
	    for(int i = 0; i < s.length(); ++i){
                //System.out.print((char) (s.charAt(i) + 3));
                MyIO.print((char) (s.charAt(i) + 3));
	    }
       // MyIO.println();
	    System.out.println();
    }
    public static void main(String[] args) {
        //FastReader fr = new FastReader();
        //FastReader.setCharset("UTF-8");
        MyIO.setCharset("ISO-8859-1");
		String s;// = MyIO.readString();
		boolean b = true;
		while(b){
			//s = fr.nextLine();
            s = MyIO.readLine();
			if(s.length() == 3 && (s.charAt(0) == 'F' && s.charAt(1) == 'I' && s.charAt(2) == 'M')){
				b = false;
			}
			else{
		//	System.out.println(toCesar(s));
		//	ByteBuffer buffer = StandardCharsets.ISO_8859_1.encode(s);
		//	String newString = StandardCharsets.ISO_8859_1.decode(buffer).toString();
			toCesar2(s);
			}
		}
    }   
}
