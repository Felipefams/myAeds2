
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
        ArvoreBinaria ab = new ArvoreBinaria();
        while (true) {
            String s = MyIO.readLine();
            if (s.equals("FIM")) {
                break;
            } else {
                x.push(solve(s));
            }
        }

    }

    public static class No{
        public Filme elemento; 
        public No esq, dir;
        public No (Filme elemento){
            this.elemento = elemento;
        }
        public No(Filme elemento, No esq, No dir){
            this.elemento = elemento;
            this.esq = esq;
            this.dir = dir;
        }
    }

    public static class ArvoreBinaria {
        private No raiz; // Raiz da arvore.

        /**
         * Construtor da classe.
         */
        public ArvoreBinaria() {
            raiz = null;
        }

        /**
         * Metodo publico iterativo para pesquisar elemento.
         * 
         * @param x Elemento que sera procurado.
         * @return <code>true</code> se o elemento existir,
         *         <code>false</code> em caso contrario.
         */
        public boolean pesquisar(Filme x) {
            return pesquisar(x, raiz);
        }

        /**
         * Metodo privado recursivo para pesquisar elemento.
         * 
         * @param x Elemento que sera procurado.
         * @param i No em analise.
         * @return <code>true</code> se o elemento existir,
         *         <code>false</code> em caso contrario.
         */
        private boolean pesquisar(Filme x, No i) {
            boolean resp;
            if (i == null) {
                resp = false;

            } else if (x == i.elemento) {
                resp = true;

            } else if (x < i.elemento) {
                resp = pesquisar(x, i.esq);

            } else {
                resp = pesquisar(x, i.dir);
            }
            return resp;
        }

        /**
         * Metodo publico iterativo para exibir elementos.
         */
        public void caminharCentral() {
            System.out.print("[ ");
            caminharCentral(raiz);
            System.out.println("]");
        }

        /**
         * Metodo privado recursivo para exibir elementos.
         * 
         * @param i No em analise.
         */
        private void caminharCentral(No i) {
            if (i != null) {
                caminharCentral(i.esq); // Elementos da esquerda.
                System.out.print(i.elemento + " "); // Conteudo do no.
                caminharCentral(i.dir); // Elementos da direita.
            }
        }

        /**
         * Metodo publico iterativo para exibir elementos.
         */
        public void caminharPre() {
            System.out.print("[ ");
            caminharPre(raiz);
            System.out.println("]");
        }

        /**
         * Metodo privado recursivo para exibir elementos.
         * 
         * @param i No em analise.
         */
        private void caminharPre(No i) {
            if (i != null) {
                System.out.print(i.elemento + " "); // Conteudo do no.
                caminharPre(i.esq); // Elementos da esquerda.
                caminharPre(i.dir); // Elementos da direita.
            }
        }

        /**
         * Metodo publico iterativo para exibir elementos.
         */
        public void caminharPos() {
            System.out.print("[ ");
            caminharPos(raiz);
            System.out.println("]");
        }

        /**
         * Metodo privado recursivo para exibir elementos.
         * 
         * @param i No em analise.
         */
        private void caminharPos(No i) {
            if (i != null) {
                caminharPos(i.esq); // Elementos da esquerda.
                caminharPos(i.dir); // Elementos da direita.
                System.out.print(i.elemento + " "); // Conteudo do no.
            }
        }

        /**
         * Metodo publico iterativo para inserir elemento.
         * 
         * @param x Elemento a ser inserido.
         * @throws Exception Se o elemento existir.
         */
        public void inserir(int x) throws Exception {
            raiz = inserir(x, raiz);
        }

        /**
         * Metodo privado recursivo para inserir elemento.
         * 
         * @param x Elemento a ser inserido.
         * @param i No em analise.
         * @return No em analise, alterado ou nao.
         * @throws Exception Se o elemento existir.
         */
        private No inserir(int x, No i) throws Exception {
            if (i == null) {
                i = new No(x);

            } else if (x < i.elemento) {
                i.esq = inserir(x, i.esq);

            } else if (x > i.elemento) {
                i.dir = inserir(x, i.dir);

            } else {
                throw new Exception("Erro ao inserir!");
            }

            return i;
        }

        /**
         * Metodo publico para inserir elemento.
         * 
         * @param x Elemento a ser inserido.
         * @throws Exception Se o elemento existir.
         */
        public void inserirPai(int x) throws Exception {
            if (raiz == null) {
                raiz = new No(x);
            } else if (x < raiz.elemento) {
                inserirPai(x, raiz.esq, raiz);
            } else if (x > raiz.elemento) {
                inserirPai(x, raiz.dir, raiz);
            } else {
                throw new Exception("Erro ao inserirPai!");
            }
        }

        /**
         * Metodo privado recursivo para inserirPai elemento.
         * 
         * @param x   Elemento a ser inserido.
         * @param i   No em analise.
         * @param pai No superior ao em analise.
         * @throws Exception Se o elemento existir.
         */
        private void inserirPai(int x, No i, No pai) throws Exception {
            if (i == null) {
                if (x < pai.elemento) {
                    pai.esq = new No(x);
                } else {
                    pai.dir = new No(x);
                }
            } else if (x < i.elemento) {
                inserirPai(x, i.esq, i);
            } else if (x > i.elemento) {
                inserirPai(x, i.dir, i);
            } else {
                throw new Exception("Erro ao inserirPai!");
            }
        }

        /**
         * Metodo publico iterativo para remover elemento.
         * 
         * @param x Elemento a ser removido.
         * @throws Exception Se nao encontrar elemento.
         */
        public void remover(int x) throws Exception {
            raiz = remover(x, raiz);
        }

        /**
         * Metodo privado recursivo para remover elemento.
         * 
         * @param x Elemento a ser removido.
         * @param i No em analise.
         * @return No em analise, alterado ou nao.
         * @throws Exception Se nao encontrar elemento.
         */
        private No remover(int x, No i) throws Exception {

            if (i == null) {
                throw new Exception("Erro ao remover!");

            } else if (x < i.elemento) {
                i.esq = remover(x, i.esq);

            } else if (x > i.elemento) {
                i.dir = remover(x, i.dir);

                // Sem no a direita.
            } else if (i.dir == null) {
                i = i.esq;

                // Sem no a esquerda.
            } else if (i.esq == null) {
                i = i.dir;

                // No a esquerda e no a direita.
            } else {
                i.esq = maiorEsq(i, i.esq);
            }

            return i;
        }

        /**
         * Metodo para trocar o elemento "removido" pelo maior da esquerda.
         * 
         * @param i No que teve o elemento removido.
         * @param j No da subarvore esquerda.
         * @return No em analise, alterado ou nao.
         */
        private No maiorEsq(No i, No j) {

            // Encontrou o maximo da subarvore esquerda.
            if (j.dir == null) {
                i.elemento = j.elemento; // Substitui i por j.
                j = j.esq; // Substitui j por j.ESQ.

                // Existe no a direita.
            } else {
                // Caminha para direita.
                j.dir = maiorEsq(i, j.dir);
            }
            return j;
        }

        /**
         * Metodo que retorna o maior elemento da árvore
         * 
         * @return int maior elemento da árvore
         */
        public int getMaior() {
            int resp = -1;

            if (raiz != null) {
                No i;
                for (i = raiz; i.dir != null; i = i.dir)
                    ;
                resp = i.elemento;
            }

            return resp;
        }

        /**
         * Metodo que retorna o menor elemento da árvore
         * 
         * @return int menor elemento da árvore
         */
        public int getMenor() {
            int resp = -1;

            if (raiz != null) {
                No i;
                for (i = raiz; i.esq != null; i = i.esq)
                    ;
                resp = i.elemento;
            }

            return resp;
        }

        /**
         * Metodo que retorna a altura da árvore
         * 
         * @return int altura da árvore
         */
        public int getAltura() {
            return getAltura(raiz, 0);
        }

        /**
         * Metodo que retorna a altura da árvore
         * 
         * @return int altura da árvore
         */
        public int getAltura(No i, int altura) {
            if (i == null) {
                altura--;
            } else {
                int alturaEsq = getAltura(i.esq, altura + 1);
                int alturaDir = getAltura(i.dir, altura + 1);
                altura = (alturaEsq > alturaDir) ? alturaEsq : alturaDir;
            }
            return altura;
        }

        /**
         * Metodo publico iterativo para remover elemento.
         * 
         * @param x Elemento a ser removido.
         * @throws Exception Se nao encontrar elemento.
         */
        public void remover2(int x) throws Exception {
            if (raiz == null) {
                throw new Exception("Erro ao remover2!");
            } else if (x < raiz.elemento) {
                remover2(x, raiz.esq, raiz);
            } else if (x > raiz.elemento) {
                remover2(x, raiz.dir, raiz);
            } else if (raiz.dir == null) {
                raiz = raiz.esq;
            } else if (raiz.esq == null) {
                raiz = raiz.dir;
            } else {
                raiz.esq = maiorEsq(raiz, raiz.esq);
            }
        }

        /**
         * Metodo privado recursivo para remover elemento.
         * 
         * @param x   Elemento a ser removido.
         * @param i   No em analise.
         * @param pai do No em analise.
         * @throws Exception Se nao encontrar elemento.
         */
        private void remover2(int x, No i, No pai) throws Exception {
            if (i == null) {
                throw new Exception("Erro ao remover2!");
            } else if (x < i.elemento) {
                remover2(x, i.esq, i);
            } else if (x > i.elemento) {
                remover2(x, i.dir, i);
            } else if (i.dir == null) {
                pai = i.esq;
            } else if (i.esq == null) {
                pai = i.dir;
            } else {
                i.esq = maiorEsq(i, i.esq);
            }
        }

        public int getRaiz() throws Exception {
            return raiz.elemento;
        }

        public static boolean igual(ArvoreBinaria a1, ArvoreBinaria a2) {
            return igual(a1.raiz, a2.raiz);
        }

        private static boolean igual(No i1, No i2) {
            boolean resp;
            if (i1 != null && i2 != null) {
                resp = (i1.elemento == i2.elemento) && igual(i1.esq, i2.esq) && igual(i1.dir, i2.dir);
            } else if (i1 == null && i2 == null) {
                resp = true;
            } else {
                resp = false;
            }
            return resp;
        }

        public int soma() {
            return soma(raiz);
        }

        public int soma(No i) {
            int resp = 0;
            if (i != null) {
                resp = i.elemento + soma(i.esq) + soma(i.dir);
            }
            return resp;
        }

        public int quantidadePares() {
            return quantidadePares(raiz);
        }

        public int quantidadePares(No i) {
            int resp = 0;
            if (i != null) {
                resp = ((i.elemento % 2 == 0) ? 1 : 0) + quantidadePares(i.esq) + quantidadePares(i.dir);
            }
            return resp;
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
