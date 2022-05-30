
import java.io.BufferedReader;
// import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
// import java.text.ParseException;
// import java.text.SimpleDateFormat;
// import java.util.*;

public class ex17 {

    /*
    passa uma matriz no formato int[][]
    para matriz dinamica
    */
    static Matrix build(){
        int rows = MyIO.readInt();
        int columns = MyIO.readInt();
        int[][] tmp = new int[rows][columns];
        for(int i = 0; i < rows; ++i){
            for(int j = 0; j < columns; ++j){
                tmp[i][j] = MyIO.readInt();
            }
        }
        return new Matrix(tmp, rows, columns);
    }

    static Matrix multiply(Matrix a, Matrix b){ 
        //o numero de colunas da primeira matriz tem que 
        //ser igual ao numero de linhas da segunda matriz
        //e a matriz resultante vai ter o numero de linhas
        //da primeira matriz e o numero de colunas da segunda
        int[][] tmp = new int[a.rows][b.columns];
        for(int i = 0; i < a.matrix.length; ++i){
            for(int j = 0; j < b.matrix[0].length; ++j){
                for(int k = 0; k < a.matrix[0].length; ++k){
                    tmp[i][j] += a.matrix[i][k] * b.matrix[k][j];
                }
            }
        }
        // Matrix ans = 
        return new Matrix(tmp, a.rows, b.columns);
    }

    static Matrix sumMatrix(Matrix a, Matrix b){
        int[][] tmp = new int[a.rows][a.columns];
        for(int i = 0; i < a.rows; ++i){
            for(int j = 0; j < a.columns; ++j){
                tmp[i][j] = a.matrix[i][j] + b.matrix[i][j];
            }
        }
        return new Matrix(tmp, a.rows, a.columns);
    }

    static void solve(){
        //chama os comandos de criação da estrutura
        Matrix a = build();//new Matrix(tmp, rows, columns);
        Matrix b = build();//new Matrix(tmp2, rows2, columns2);
        a.start = a.createMatrix();
        b.start = b.createMatrix();
        //mostra as diagonais
        a.showMainDiagonal();
        b.showMainDiagonal();
        a.showSecondDiagonal();
        b.showSecondDiagonal();
        //resultado da soma
        Matrix sumAns = sumMatrix(a, b);
        sumAns.start = sumAns.createMatrix();
        sumAns.printMatrix(sumAns.start);
        //resultado da multiplicacao
        Matrix multiplyAns = multiply(a, b);
        multiplyAns.start = multiplyAns.createMatrix();
        multiplyAns.printMatrix(multiplyAns.start);
    }
    public static void main(String[] args) {
        int t = MyIO.readInt();
        while(t-- > 0){
            solve();
        }
    }

    static class Node {
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

    static class Matrix {
        public Node start;
        public int rows;
        public int columns;
        public int[][] matrix;

        public Matrix(int[][] matrix,int rows, int columns){
            this.start = null;// new Node(matrix[0][0]);
            this.matrix = matrix;
            this.matrix = matrix; 
            this.rows = rows;
            this.columns = columns;
        }
        /*
            isso aqui que eh o construtor na verdade.
            talvez de para fazer diferente, mas como o metodo
            e recursivo eu preferi fazer assim
        */
        public Node createMatrix(int i, int j, Node curr){
            //quando chegar na beirada da matrix vai retornar null
            if(i >= rows || j >= columns){// || (i < 0 || j < 0)){
                return null;
            }
            //pra fazer sem o auxilio da matriz estatica seria so trocar
            //o new Node() por um comando de leitura
            Node tmp = new Node(this.matrix[i][j]);
            tmp.left = curr;
            tmp.up = curr;
            tmp.right = createMatrix(i, j+1, tmp);
            tmp.down = createMatrix(i+1, j, tmp);
            // tmp.right = createMatrix(i, j+1, tmp);
            // tmp.up = createMatrix(i-1, j, tmp);
            // tmp.down = createMatrix(i+1, j, tmp);
            return tmp;
        }

        public Node createMatrix(){
            return createMatrix(0,0,null);
        }

        public void printMatrix(Node curr){
            Node rPtr;
            Node dPtr = curr;
            while (dPtr != null) {
                rPtr = dPtr;
                while (rPtr!=null) {
                    System.out.print(rPtr.data+" ");
                    rPtr = rPtr.right;
                }
                System.out.print("\n");
                dPtr = dPtr.down;
            }
        }

        public void showMainDiagonal(){
            Node i = start;
            while(i != null){
                System.out.print(i.data + " "); 
                i = i.right;
                if(i != null)
                i = i.down;
            }
            System.out.println();
        }

        public void showSecondDiagonal(){
            final int LEN = matrix.length;
            for (int i = 0; i < LEN; i++) {
                for (int j = 0; j < LEN; j++) {
                    if ((i + j) == (LEN - 1)) {
                        System.out.print(matrix[i][j] + " ");
                    }
                }
            }
            System.out.println();
        }

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
