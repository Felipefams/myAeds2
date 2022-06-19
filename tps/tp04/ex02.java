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

public class ex02 {

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
		String path = "filmes/";// "/tmp/filmes/";
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
		ArvoreArvore ab = new ArvoreArvore();
		while (true) {
			String s = MyIO.readLine();
			if (s.equals("FIM")) {
				break;
			} else {
				ab.inserir(solve(s));
			}
		}
		int t = MyIO.readInt();
		while (t-- > 0) {
			String s = MyIO.readLine();
			if (s.charAt(0) == 'I') {
				ab.inserir(solve(s.substring(2, s.length())));
			} else if (s.charAt(0) == 'R') {
				System.out.println("ainda bem que nao precisa");
				// ab.remover(s.substring(2, s.length()));
			}
		}
		while (true) {
			String s = MyIO.readLine();
			if (s.equals("FIM")) {
				break;
			} else {
				MyIO.println(s);
				MyIO.print("raiz ");
				MyIO.println((ab.pesquisar(s)) ? "SIM" : "NAO");
			}
		}
		/*
		 * long startTime = System.nanoTime();
		 * // filmeList.quicksort(0, filmeList.n - 1);//o input ta bugado.
		 * long stopTime = System.nanoTime();
		 * long elapsedTime = stopTime - startTime;
		 * double seconds = (double) elapsedTime / 1_000_000_000.0;
		 * Arq.openWriteClose("748473_arvoreBinaria.txt", "UTF-8",
		 * seconds + "segundos\t" +
		 * "748473_Felipe_Augusto_Morais_Silva");
		 */

	}

	public static class No {
		public char elemento; // Conteudo do no.
		public No esq; // No da esquerda.
		public No dir; // No da direita.
		public No2 outro;

		No(char elemento) {
			this.elemento = elemento;
			this.esq = this.dir = null;
			this.outro = null;
		}

		No(char elemento, No esq, No dir) {
			this.elemento = elemento;
			this.esq = esq;
			this.dir = dir;
			this.outro = null;
		}
	}

	public static class No2 {
		public Filme elemento; // Conteudo do no.
		public No2 esq; // No da esquerda.
		public No2 dir; // No da direita.

		No2(Filme elemento) {
			this.elemento = elemento;
			this.esq = this.dir = null;
		}

		No2(Filme elemento, No2 esq, No2 dir) {
			this.elemento = elemento;
			this.esq = esq;
			this.dir = dir;
		}
	}

	public static class ArvoreArvore {
		private No raiz; // Raiz da arvore.
		public int comp = 0;

		public ArvoreArvore() {
			raiz = null;
			inserir('D');
			inserir('R');
			inserir('Z');
			inserir('X');
			inserir('V');
			inserir('B');
			inserir('F');
			inserir('P');
			inserir('U');
			inserir('I');
			inserir('G');
			inserir('E');
			inserir('J');
			inserir('L');
			inserir('H');
			inserir('T');
			inserir('A');
			inserir('W');
			inserir('S');
			inserir('O');
			inserir('M');
			inserir('N');
			inserir('K');
			inserir('C');
			inserir('Y');
			inserir('Q');
		}

		public void inserir(char letra) {
			try {
				raiz = inserir(letra, raiz);
			} catch (Exception e) {
				MyIO.println(e.getMessage());
			}
		}

		private No inserir(char x, No i) throws Exception {
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

		public void inserir(Filme x) throws Exception {
			inserir(x, raiz);
		}

		public void inserir(Filme x, No i) throws Exception {
			if (i == null) {
				throw new Exception("Erro ao inserir: caractere invalido!");

			} else if (x.tituloOriginal.charAt(0) < i.elemento) {
				inserir(x, i.esq);

			} else if (x.tituloOriginal.charAt(0) > i.elemento) {
				inserir(x, i.dir);

			} else {
				i.outro = inserir(x, i.outro);
			}
		}

		private No2 inserir(Filme x, No2 i) throws Exception {
			if (i == null) {
				i = new No2(x);

			} else if (x.tituloOriginal.compareTo(i.elemento.tituloOriginal) < 0) {
				i.esq = inserir(x, i.esq);

			} else if (x.tituloOriginal.compareTo(i.elemento.tituloOriginal) > 0) {
				i.dir = inserir(x, i.dir);

			} else {
				throw new Exception("Erro ao inserir: elemento existente!");
			}

			return i;
		}

		public boolean mostrar(String s) {
			return mostrar(raiz, s);
		}

		public boolean mostrar(No i, String s) {
			boolean resultado = false;
			if (i != null) {
				resultado = pesquisar(s);

				if (resultado) {
					return resultado;
				}
				MyIO.print(" ESQ ");
				resultado = mostrar(i.esq, s);
				if (resultado) {
					return resultado;
				}
				MyIO.print(" DIR ");
				resultado = mostrar(i.dir, s);
				if (resultado) {
					return resultado;
				}
			}
			return resultado;
		}

		public void mostrar(No2 i) {
			if (i != null) {
				mostrar(i.esq);
				// System.out.println(i.elemento);
				mostrar(i.dir);
			}
		}

		public boolean pesquisar(String elemento) {
			return pesquisar(raiz, elemento);
		}

		private boolean pesquisar(No no, String x) {
			boolean resp;
			if (no == null) {
				resp = false;

			} else if (x.charAt(0) < no.elemento) {
				MyIO.print("esq ");
				resp = pesquisar(no.esq, x);

			} else if (x.charAt(0) > no.elemento) {
				MyIO.print("dir ");
				resp = pesquisar(no.dir, x);

			} else {
				resp = pesquisarSegundaArvore(x, no.outro);
			}
			return resp;
		}

		private boolean pesquisarSegundaArvore(String x, No2 i) {
			boolean resp;
			if (i == null) {
				comp++;
				resp = false;
			} else if (x.compareTo(i.elemento.tituloOriginal) == 0) {
				comp += 2;
				resp = true;
			} else if (x.compareTo(i.elemento.tituloOriginal) < 0) {
				comp += 3;
				resp = pesquisarSegundaArvore(x, i.esq);

			} else {
				comp += 3;
				resp = pesquisarSegundaArvore(x, i.dir);

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
