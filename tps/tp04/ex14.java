
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

public class ex14 {

    static String removeTag(String s) {
        return s.replaceAll("<[^>]*>", "");
    }

    /*
     * filtra a posicao onde o nome esta e retorna apenas o conteudo desejado
     */
    static String filterTitle(String s) {
        String tmp = removeTag(s);
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < tmp.length(); i++) {
            if (tmp.charAt(i) == '(') {
                break;
            } else {
                sb.append(tmp.charAt(i));
            }
        }
        return sb.toString();
    }

    /*
     * faz o parse da variavel adquirida de string pra date
     */
    static Date filterDate(String s) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '(') {
                break;
            } else if (s.charAt(i) != ' ') {
                sb.append(s.charAt(i));
            }
        }
        return sdf.parse(sb.toString());
    }

    static String filterGenre(String s) {
        String tmp = removeTag(s);
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < tmp.length(); i++) {
            if (tmp.charAt(i) != ' ') {
                sb.append(tmp.charAt(i));
            }
        }
        return sb.toString().replaceAll("&nbsp;", "");
    }

    /*
     * convert a String in the format: xH yM to the integer equivalent in minutes
     */
    static int stringHoursToIntMinutes(String s) {
        int[] intArr = new int[2];
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == 'h') {
                // da pra fazer utilizando i-1, porque a gente sabe
                // que nenhum filme vai ter mais de 10 horas
                intArr[0] = (int) s.charAt(i - 1) - '0';
            } else if (s.charAt(i) == 'm') {
                if (Character.isDigit(s.charAt(i - 1))) {
                    StringBuilder tmp = new StringBuilder();
                    if (i >= 2) {
                        if (Character.isDigit(s.charAt(i - 2))) {
                            tmp.append(s.charAt(i - 2));
                            tmp.append(s.charAt(i - 1));
                            intArr[1] = Integer.parseInt(tmp.toString());
                            break;
                        }
                    }
                    intArr[1] = (int) s.charAt(i - 1) - '0';
                    break;
                }
                break;
            }
        }
        return ((intArr[0] * 60) + intArr[1]);
    }

    static int filterRuntime(String s) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) != ' ') {
                sb.append(s.charAt(i));
            }
        }
        return stringHoursToIntMinutes(sb.toString());
    }

    /*
     * filtra todas as tags StrongBdi, no caso o budget, situacao e idioma original.
     */
    static String filterStrongBdiTag(String s) {
        String tmp = removeTag(s);
        StringBuilder sb = new StringBuilder();
        for (int i = tmp.length() - 1; i > 0; i--) {
            if (tmp.charAt(i) != ' ') {
                int j = i;
                while (tmp.charAt(j) != ' ') {
                    if (tmp.charAt(j) == ' ') {
                        break;
                    } else {
                        sb.append(tmp.charAt(j));
                        j--;
                    }
                }
                break;
            }
        }
        return sb.reverse().toString();
    }

    static String filterOriginalTitle(String s) {
        StringBuilder sb = new StringBuilder();
        int countGreaterSimbol = 0;
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '>') {
                countGreaterSimbol++;
            }
            if (countGreaterSimbol == 3) {
                for (int j = i + 2; j < s.length(); j++) {
                    if (s.charAt(j) == '<') {
                        break;
                    } else {
                        sb.append(s.charAt(j));
                    }
                }
                break;
            }
        }
        return sb.toString();
    }

    public static Filme solve(String name) throws ParseException {
        String path = "/tmp/filmes/";
        String filename = path + name;
        SimpleDateFormat sdf2 = new SimpleDateFormat("dd/MM/yyyy");
        Arq.openRead(filename);
        Filme filme = new Filme();
        /*
         * Eu sei que esses loops com while true nao sao o ideal,
         * mas nesses casos vai dar literalmente na mesma de fazer
         * assim ou usando o seu !linha.contains, os excessoes que
         * podem surgir desse uso sao as mesmas.
         */
        // nome
        while (true) {
            String tmp = Arq.readLine();
            if (tmp.contains("<title>")) {
                filme.setNome(filterTitle(tmp).trim());
                break;
            }
        }
        // data de Lancamento
        while (true) {
            String tmp = Arq.readLine();
            if (tmp.contains("class=\"release\"")) {
                tmp = Arq.readLine();
                tmp = tmp.trim();
                // precisa do throws na main ou o try-catch
                filme.setDataLancamento(filterDate(tmp));
                break;
            }
        }
        // genero
        while (true) {
            String tmp = Arq.readLine();
            if (tmp.contains("class=\"genres\"")) {
                tmp = Arq.readLine();
                tmp = Arq.readLine();
                filme.setGenero(filterGenre(tmp));
                break;
            }
        }
        // duracao
        while (true) {
            String tmp = Arq.readLine();
            if (tmp.contains("class=\"runtime\"")) {
                tmp = Arq.readLine();
                tmp = Arq.readLine();
                filme.setDuracao(filterRuntime(tmp));
                break;
            }
        }
        // titulo original
        boolean b = false;
        while (true) {
            String tmp = Arq.readLine();
            if (tmp.contains("ulo original")) {
                filme.setTituloOriginal(filterOriginalTitle(tmp));
                break;
            } else if (tmp.contains("<strong><bdi>Situ")) {
                filme.setTituloOriginal(filme.getNome());
                filme.setSituacao(filterStrongBdiTag(tmp));
                b = true;
                break;
            }
        }
        // situacao
        if (!b) {
            while (true) {
                String tmp = Arq.readLine();
                if (tmp.contains("<strong><bdi>Situ")) {
                    // o filter budget resolve o problema
                    filme.setSituacao(filterStrongBdiTag(tmp));
                    break;
                }
            }
        }
        // idioma original
        while (true) {
            String tmp = Arq.readLine();
            if (tmp.contains("Idioma original")) {
                tmp = filterStrongBdiTag(tmp);
                filme.setIdiomaOriginal(tmp);
                break;
            }
        }
        // aqui nao vai precisar fazer o while, pq a informacao ja vai estar na linha
        // seguinte
        // vou fazer o while so pra usar a String tmp como var local
        // orcamento
        while (true) {
            String tmp = Arq.readLine();
            if (tmp.contains("mento")) {
                tmp = filterStrongBdiTag(tmp);
                try {
                    filme.setOrcamento(Float.parseFloat(tmp.replaceAll("[^\\d.]+", "")));
                } catch (Exception ParseException) {
                    filme.setOrcamento(0.0F);
                }
                break;
            }
        }
        int control = 0;

        while (true) {
            String tmp = Arq.readLine();
            if (control > 20) {
                String[] k = {};
                filme.setPalavrasChave(k);
                break;
            }
            if (tmp.contains("<ul>")) {
                // ler mais uma linha pra sair do <ul>
                tmp = Arq.readLine();
                List<String> stringList = new ArrayList<>();
                for (int i = 0; i < Arq.length(); i++) {
                    tmp = Arq.readLine();
                    if (tmp.contains("<li>")) {
                        stringList.add(removeTag(tmp).trim());
                    } else if (tmp.contains("<ul>")) {
                        break;
                    }
                }
                String[] tmpArr = new String[stringList.size()];
                for (int i = 0; i < stringList.size(); i++) {
                    tmpArr[i] = stringList.get(i);
                }
                filme.setPalavrasChave(tmpArr);
                break;
            }
            control++;
        }
        Arq.close();
        return filme;
    }

    static int findIntInString(String s) {
        int ans = 0;
        for (int i = 0; i < s.length(); i++) {
            if (Character.isDigit(s.charAt(i))) {
                String tmp = Character.toString(s.charAt(i));
                if (Character.isDigit(s.charAt(i + 1))) {
                    tmp = tmp + Character.toString(s.charAt(i + 1));
                }
                ans = Integer.parseInt(tmp);
                return ans;
            }
        }
        return ans;
    }

    public static void main(String[] args) throws Exception {
        doubleLinkedList x = new doubleLinkedList();
        int g = 0;
        while (true) {
            String s = MyIO.readLine();
            if (s.equals("FIM")) {
                break;
            } else {
                x.push(solve(s));
            }
        }
        long startTime = System.nanoTime();
        // filmeList.quicksort(0, filmeList.n - 1);//o input ta bugado.
        x.quicksort(x.head);
        long stopTime = System.nanoTime();
        long elapsedTime = stopTime - startTime;
        x.r_printlist();
        final int countC = x.countComparisons;
        final int countM = x.countMoves;
        double seconds = (double) elapsedTime / 1_000_000_000.0;
        Arq.openWriteClose("748473_quicksort2.txt", "UTF-8",
                seconds + "segundos\t" +
                        countC + "comparacoes\t" +
                        countM + "movimentacoes\t" +
                        "748473_Felipe_Augusto_Morais_Silva");

    }

    public static class Node {
        public Filme data;
        public Node next;
        public Node prev;

        Node(Filme f) {
            data = f;
            next = null;
            prev = null;
        }
    }

    public static class doubleLinkedList {
        public Node head;
        public int countComparisons;
        public int countMoves;

        public doubleLinkedList(){
            this.head = null;
        }

        public Node lastNode(Node node) {
            while (node.next != null)
                node = node.next;
            return node;
        }

        public Node partition(Node l, Node h) {
            Filme pivot = h.data;
            countMoves++;
            Node i = l.prev;
            for (Node j = l; j != h; j = j.next) {
                countComparisons += 2;
                if (j.data.getSituacao().compareTo(pivot.getSituacao()) <= 0) {
                    i = (i == null) ? l : i.next;
                    Filme tmp = i.data;
                    i.data = j.data;
                    j.data = tmp;
                    countMoves += 3;
                }
            }
            i = (i == null) ? l : i.next;
            countMoves += 3;
            Filme tmp = i.data;
            i.data = h.data;
            h.data = tmp;
            return i;
        }

        public void _quickSort(Node l, Node h) {
            if (h != null && l != h && l != h.next) {
                Node tmp = partition(l, h);
                _quickSort(l, tmp.prev);
                _quickSort(tmp.next, h);
            }
        }

        public void quicksort(Node node) {
            Node head = lastNode(node);
            _quickSort(node, head);
        }

        public void printList() {
            while (head != null){
                MyIO.println(/* "[" + i + "] " + */ head.data + " ");
            head = head.next;
            }
        }

        public void r_printlist(){
            r_printlist(head);
        }

        public void r_printlist(Node toPrint){
            if( toPrint != null){
                r_printlist(toPrint.next);
                MyIO.println(/* "[" + i + "] " + */ toPrint.data + " ");
            }
        }

        public void push(Filme newData) {
            Node newNode = new Node(newData);
            if (head == null) {
                head = newNode;
                return;
            }
            newNode.next = head;
            head.prev = newNode;
            newNode.prev = null;
            head = newNode;
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

    public static class Arq {
        private static String nomeArquivo = "";
        private static String charsetArquivo = "ISO-8859-1";
        private static boolean write = false, read = false;
        private static Formatter saida = null;
        private static Scanner entrada = null;

        public static boolean openWrite(String nomeArq, String charset) {
            boolean resp = false;
            close();
            try {
                saida = new Formatter(nomeArq, charset);
                nomeArquivo = nomeArq;
                resp = write = true;
            } catch (Exception ignored) {
            }
            return resp;
        }

        public static void openWrite(String nomeArq) {
            openWrite(nomeArq, charsetArquivo);
        }

        public static boolean openWriteClose(String nomeArq, String charset, String conteudo) {
            boolean resp = openWrite(nomeArq, charset);
            if (resp) {
                println(conteudo);
                close();
            }
            return resp;
        }

        public static boolean openWriteClose(String nomeArq, String conteudo) {
            return openWriteClose(nomeArq, charsetArquivo, conteudo);
        }

        public static void openRead(String nomeArq) {
            openRead(nomeArq, charsetArquivo);
        }

        public static void openRead(String nomeArq, String charset) {
            boolean resp = false;
            close();
            try {
                entrada = new Scanner(new File(nomeArq), charset);
                nomeArquivo = nomeArq;
                resp = read = true;
            } catch (Exception ignored) {
            }
        }

        public static String openReadClose(String nomeArq) {
            openRead(nomeArq);
            String resp = readAll();
            close();
            return resp;
        }

        public static void close() {
            if (write) {
                saida.close();
            }
            if (read) {
                entrada.close();
            }
            write = read = false;
            nomeArquivo = "";
            charsetArquivo = "ISO-8859-1";
        }

        public static long length() {
            long resp = -1;
            if (read != write) {
                File file = new File(nomeArquivo);
                resp = file.length();
            }
            return resp;
        }

        public static void print(int x) {
            if (write) {
                saida.format("%d", x);
            }
        }

        public static void print(double x) {
            if (write) {
                saida.format("%f", x);
            }
        }

        public static void print(String x) {
            if (write) {
                saida.format("%s", x);
            }
        }

        public static void print(boolean x) {
            if (write) {
                saida.format("%s", ((x) ? "true" : "false"));
            }
        }

        public static void print(char x) {
            if (write) {
                saida.format("%c", x);
            }
        }

        public static void println(int x) {
            if (write) {
                saida.format("%d\n", x);
            }
        }

        public static void println(double x) {
            if (write) {
                saida.format("%f\n", x);
            }
        }

        public static void println(String x) {
            if (write) {
                saida.format("%s\n", x);
            }
        }

        public static void println(boolean x) {
            if (write) {
                saida.format("%s\n", ((x) ? "true" : "false"));
            }
        }

        public static void println(char x) {
            if (write) {
                saida.format("%c\n", x);
            }
        }

        public static int readInt() {
            int resp = -1;
            try {
                resp = entrada.nextInt();
            } catch (Exception ignored) {
            }
            return resp;
        }

        public static char readChar() {
            char resp = ' ';
            try {
                resp = (char) entrada.nextByte();
            } catch (Exception ignored) {
            }
            return resp;
        }

        public static double readDouble() {
            double resp = -1;
            try {
                resp = Double.parseDouble(readString().replace(",", "."));
            } catch (Exception ignored) {
            }
            return resp;
        }

        public static String readString() {
            String resp = "";
            try {
                resp = entrada.next();
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
            return resp;
        }

        public static boolean readBoolean() {
            boolean resp = false;
            try {
                resp = entrada.next().equals("true");
            } catch (Exception ignored) {
            }
            return resp;
        }

        public static String readLine() {
            String resp = "";
            try {
                resp = entrada.nextLine();
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
            return resp;
        }

        public static boolean hasNext() {
            return entrada.hasNext();
        }

        public static String readAll() {
            String resp = "";
            while (hasNext()) {
                resp += (readLine() + "\n");
            }
            return resp;
        }
    }

    public static class Filme {
        private String nome;
        private String tituloOriginal;
        private Date dataLancamento;
        private int duracao;
        private String genero;
        private String idiomaOriginal;
        private String situacao;
        private float orcamento;
        private String[] palavrasChave;

        public Filme() {
        }

        ;

        public Filme(String nome, String tituloOriginal, Date dataLancamento, int duracao, String genero,
                String idiomaOriginal, String situacao, float orcamento, String[] palavrasChave) {
            this.nome = nome;
            this.tituloOriginal = tituloOriginal;
            this.dataLancamento = dataLancamento;
            this.duracao = duracao;
            this.genero = genero;
            this.idiomaOriginal = idiomaOriginal;
            this.situacao = situacao;
            this.orcamento = orcamento;
            this.palavrasChave = palavrasChave;
        }

        public String getNome() {
            return nome;
        }

        public void setNome(String nome) {
            this.nome = nome;
        }

        public String getTituloOriginal() {
            return tituloOriginal;
        }

        public void setTituloOriginal(String tituloOriginal) {
            this.tituloOriginal = tituloOriginal;
        }

        public Date getDataLancamento() {
            return dataLancamento;
        }

        public void setDataLancamento(Date dataLancamento) {
            this.dataLancamento = dataLancamento;
        }

        public int getDuracao() {
            return duracao;
        }

        public void setDuracao(int duracao) {
            this.duracao = duracao;
        }

        public String getGenero() {
            return genero;
        }

        public void setGenero(String genero) {
            this.genero = genero;
        }

        public String getIdiomaOriginal() {
            return idiomaOriginal;
        }

        public void setIdiomaOriginal(String idiomaOriginal) {
            this.idiomaOriginal = idiomaOriginal;
        }

        public String getSituacao() {
            return situacao;
        }

        public void setSituacao(String situacao) {
            this.situacao = situacao;
        }

        public float getOrcamento() {
            return orcamento;
        }

        public void setOrcamento(float orcamento) {
            this.orcamento = orcamento;
        }

        public String[] getPalavrasChave() {
            return palavrasChave;
        }

        public void setPalavrasChave(String[] palavrasChave) {
            this.palavrasChave = palavrasChave;
        }

        @Override
        public String toString() {
            SimpleDateFormat sdf2 = new SimpleDateFormat("dd/MM/yyyy");
            return nome + ' ' + tituloOriginal + ' ' + sdf2.format(dataLancamento) + ' ' + duracao + ' ' + genero +
                    ' ' + idiomaOriginal + ' ' + situacao + ' ' + orcamento + ' ' + Arrays.toString(palavrasChave);
        }
    }

}
