
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class ex17 {
    static void solve(){
        int rows = MyIO.readInt();
        int columns = MyIO.readInt();
        int[][] tmp = new int[rows][columns];
        for(int i = 0; i < rows; ++i){
            for(int j = 0; j < columns; ++j){
                tmp[i][j] = MyIO.readInt();
            }
        }
    }
    public static void main(String[] args) {
        int t = MyIO.readInt();
        while(t-- > 0){
            solve();
        }
    }

    public class Node {
        public int data;
        public Node up, down, left, right;

        public Node() {
            this(0);
        }

        public Node(int data) {
            this(data, null, null, null, null);
        }

        public Node(int data, Node up, Node down, Node left, Node right) {
            this.data = data;
            this.up = up;
            this.down = down;
            this.left = left;
            this.right = right;
        }
    }

    class Matrix {
        private Node start;
        private int rows;
        private int columns;
        private int[][] matrix;

        public Matrix(int[][] matrix,int rows, int columns){
            this.start = new Node(matrix[0][0]);
            this.matrix = matrix; 
            this.rows = rows;
            this.columns = columns;
        }

        public Node createMatrix(int[][] matrix, int i, int j, Node curr){
            //quando chegar na beirada da matrix vai retornar null
            if(i >= rows && j >= columns || (i < 0 || j < 0)){
                return null;
            }
            Node tmp = new Node(matrix[i][j]);
            tmp.left = curr;
            tmp.right = createMatrix(matrix, i+1, j, tmp);
            tmp.up = createMatrix(matrix, i, j-1, tmp);
            tmp.down = createMatrix(matrix, i, j+1, tmp);
            return tmp;
        }
        public void printMatrix(Node curr){
            if(curr != null){
                printMatrix(curr.right);
                printMatrix(curr.down);
                System.out.print(curr.data + " ");
            }
        }
        // public void readMatrix(int rows, int columns){
        //     this.rows = rows;
        //     this.columns = columns;
        //     this.matrix = new int[rows][columns];
        //     for(int i = 0; i < rows; ++i){
        //         for(int j = 0; j < columns; ++j){
        //             this.matrix[i][j] = MyIO.readInt();
        //         }
        //     }
        // }

    }

    public static class MyIO {

        private static BufferedReader in = new BufferedReader(
                new InputStreamReader(System.in, StandardCharsets.ISO_8859_1));
        private static String charset = "ISO-8859-1";

        public static void setCharset(String charset_) {
            charset = charset_;
            in = new BufferedReader(new InputStreamReader(System.in, Charset.forName(charset)));
        }

        public static void print() {
        }

        public static void print(int x) {
            try {
                PrintStream out = new PrintStream(System.out, true, charset);
                out.print(x);
            } catch (UnsupportedEncodingException e) {
                System.out.println("Erro: charset invalido");
            }
        }

        public static void print(float x) {
            try {
                PrintStream out = new PrintStream(System.out, true, charset);
                out.print(x);
            } catch (UnsupportedEncodingException e) {
                System.out.println("Erro: charset invalido");
            }
        }

        public static void print(double x) {
            try {
                PrintStream out = new PrintStream(System.out, true, charset);
                out.print(x);
            } catch (UnsupportedEncodingException e) {
                System.out.println("Erro: charset invalido");
            }
        }

        public static void print(String x) {
            try {
                PrintStream out = new PrintStream(System.out, true, charset);
                out.print(x);
            } catch (UnsupportedEncodingException e) {
                System.out.println("Erro: charset invalido");
            }
        }

        public static void print(boolean x) {
            try {
                PrintStream out = new PrintStream(System.out, true, charset);
                out.print(x);
            } catch (UnsupportedEncodingException e) {
                System.out.println("Erro: charset invalido");
            }
        }

        public static void print(char x) {
            try {
                PrintStream out = new PrintStream(System.out, true, charset);
                out.print(x);
            } catch (UnsupportedEncodingException e) {
                System.out.println("Erro: charset invalido");
            }
        }

        public static void println() {
        }

        public static void println(int x) {
            try {
                PrintStream out = new PrintStream(System.out, true, charset);
                out.println(x);
            } catch (UnsupportedEncodingException e) {
                System.out.println("Erro: charset invalido");
            }
        }

        public static void println(float x) {
            try {
                PrintStream out = new PrintStream(System.out, true, charset);
                out.println(x);
            } catch (UnsupportedEncodingException e) {
                System.out.println("Erro: charset invalido");
            }
        }

        public static void println(double x) {
            try {
                PrintStream out = new PrintStream(System.out, true, charset);
                out.println(x);
            } catch (UnsupportedEncodingException e) {
                System.out.println("Erro: charset invalido");
            }
        }

        public static void println(String x) {
            try {
                PrintStream out = new PrintStream(System.out, true, charset);
                out.println(x);
            } catch (UnsupportedEncodingException e) {
                System.out.println("Erro: charset invalido");
            }
        }

        public static void println(boolean x) {
            try {
                PrintStream out = new PrintStream(System.out, true, charset);
                out.println(x);
            } catch (UnsupportedEncodingException e) {
                System.out.println("Erro: charset invalido");
            }
        }

        public static void println(char x) {
            try {
                PrintStream out = new PrintStream(System.out, true, charset);
                out.println(x);
            } catch (UnsupportedEncodingException e) {
                System.out.println("Erro: charset invalido");
            }
        }

        public static void printf(String formato, double x) {
            try {
                PrintStream out = new PrintStream(System.out, true, charset);
                out.printf(formato, x);// "%.2f"
            } catch (UnsupportedEncodingException e) {
                System.out.println("Erro: charset invalido");
            }
        }

        public static double readDouble() {
            double d = -1;
            try {
                d = Double.parseDouble(readString().trim().replace(",", "."));
            } catch (Exception e) {
            }
            return d;
        }

        public static double readDouble(String str) {
            try {
                PrintStream out = new PrintStream(System.out, true, charset);
                out.print(str);
            } catch (UnsupportedEncodingException e) {
                System.out.println("Erro: charset invalido");
            }
            return readDouble();
        }

        public static float readFloat() {
            return (float) readDouble();
        }

        public static float readFloat(String str) {
            return (float) readDouble(str);
        }

        public static int readInt() {
            int i = -1;
            try {
                i = Integer.parseInt(readString().trim());
            } catch (Exception e) {
            }
            return i;
        }

        public static int readInt(String str) {
            try {
                PrintStream out = new PrintStream(System.out, true, charset);
                out.print(str);
            } catch (UnsupportedEncodingException e) {
                System.out.println("Erro: charset invalido");
            }
            return readInt();
        }

        public static String readString() {
            String s = "";
            char tmp;
            try {
                do {
                    tmp = (char) in.read();
                    if (tmp != '\n' && tmp != ' ' && tmp != 13) {
                        s += tmp;
                    }
                } while (tmp != '\n' && tmp != ' ');
            } catch (IOException ioe) {
                System.out.println("lerPalavra: " + ioe.getMessage());
            }
            return s;
        }

        public static String readString(String str) {
            try {
                PrintStream out = new PrintStream(System.out, true, charset);
                out.print(str);
            } catch (UnsupportedEncodingException e) {
                System.out.println("Erro: charset invalido");
            }
            return readString();
        }

        public static String readLine() {
            String s = "";
            char tmp;
            try {
                do {
                    tmp = (char) in.read();
                    if (tmp != '\n' && tmp != 13) {
                        s += tmp;
                    }
                } while (tmp != '\n');
            } catch (IOException ioe) {
                System.out.println("lerPalavra: " + ioe.getMessage());
            }
            return s;
        }

        public static String readLine(String str) {
            try {
                PrintStream out = new PrintStream(System.out, true, charset);
                out.print(str);
            } catch (UnsupportedEncodingException e) {
                System.out.println("Erro: charset invalido");
            }
            return readLine();
        }

        public static char readChar() {
            char resp = ' ';
            try {
                resp = (char) in.read();
            } catch (Exception e) {
            }
            return resp;
        }

        public static char readChar(String str) {
            try {
                PrintStream out = new PrintStream(System.out, true, charset);
                out.print(str);
            } catch (UnsupportedEncodingException e) {
                System.out.println("Erro: charset invalido");
            }
            return readChar();
        }

        public static boolean readBoolean() {
            boolean resp = false;
            String str = "";

            try {
                str = readString();
            } catch (Exception e) {
            }

            if (str.equals("true") || str.equals("TRUE") || str.equals("t") || str.equals("1") ||
                    str.equals("verdadeiro") || str.equals("VERDADEIRO") || str.equals("V")) {
                resp = true;
            }

            return resp;
        }

        public static boolean readBoolean(String str) {
            try {
                PrintStream out = new PrintStream(System.out, true, charset);
                out.print(str);
            } catch (UnsupportedEncodingException e) {
                System.out.println("Erro: charset invalido");
            }
            return readBoolean();
        }

        public static void pause() {
            try {
                in.read();
            } catch (Exception e) {
            }
        }

        public static void pause(String str) {
            try {
                PrintStream out = new PrintStream(System.out, true, charset);
                out.print(str);
            } catch (UnsupportedEncodingException e) {
                System.out.println("Erro: charset invalido");
            }
            pause();
        }
    }
}
