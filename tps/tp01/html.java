import java.io.*;
import java.net.*;
import java.nio.charset.*;

class html {
   //funcionando perfeitamente no vscode, mas esta dando problema no vim e no verde
   public static String getHtml(String endereco) {
      URL url;
      InputStream is = null;
      BufferedReader br;
      String resp = "", line;

      try {
         url = new URL(endereco);
         is = url.openStream(); // throws an IOException
         br = new BufferedReader(new InputStreamReader(is));

         while ((line = br.readLine()) != null) {
            resp += line + "\n";
         }
      } catch (MalformedURLException mue) {
         mue.printStackTrace();
      } catch (IOException ioe) {
         ioe.printStackTrace();
      }

      try {
         is.close();
      } catch (IOException ioe) {
         // nothing to see here

      }

      return resp;
   }

   public static void main(String[] args){
      
      /*
      Muito ruim fazer esse tanto de if, deus me livre.
      */
      MyIO.setCharset("UTF-8");
      // String endereco, html;
      // endereco = "http://maratona.crc.pucminas.br/series/Friends.html";
      // html = getHtml(endereco);
      boolean b = true;
      while (b) {
         String nome = MyIO.readLine();
         if (nome.length() == 3 && (nome.charAt(0) == 'F' && nome.charAt(1) == 'I' && nome.charAt(2) == 'M')) {
            b = false;
         } else {
            String endereco = MyIO.readLine();
            String html = getHtml(endereco);
            // count A's
            int countA1 = 0, countA2 = 0, countA3 = 0, countA4 = 0, countA5 = 0;
            // count E's
            int countE1 = 0, countE2 = 0, countE3 = 0, countE4 = 0;
            // count I's
            int countI1 = 0, countI2 = 0, countI3 = 0, countI4 = 0;
            // count O's
            int countO1 = 0, countO2 = 0, countO3 = 0, countO4 = 0, countO5 = 0;
            // count U's
            int countU1 = 0, countU2 = 0, countU3 = 0, countU4 = 0;
            //counte other
            int countConsonant = 0;
            int countBr = 0;
            int countTable = 0;


            for (int i = 0; i < html.length(); i++) {
               if(html.charAt(i) == '<'){
                  if(html.charAt(i+1) == 't'){
                     if(html.charAt(i+2) == 'a'){
                        countTable++;
                     }
                  }else if(html.charAt(i+1) == 'b'){
                     if(html.charAt(i+2) == 'r'){
                        countBr++;
                     }
                  }
               }else if(Character.isLetter(html.charAt(i))){
                  if(html.charAt(i)!='a'||html.charAt(i)!='e'||html.charAt(i)!='i'||html.charAt(i)!='o'||html.charAt(i)!='u'||html.charAt(i)!='A'||html.charAt(i)!='E'||html.charAt(i)!='I'||html.charAt(i)!='O'||html.charAt(i)!='U'){
                        countConsonant++;
                  }
               }

               switch (html.charAt(i)) {
                  // 'A' cases
                  case tv.a1:
                     countA1++;
                     break;
                  case tv.a2:
                     countA2++;
                     break;
                  case tv.a3:
                     countA3++;
                     break;
                  case tv.a4:
                     countA4++;
                     break;
                  case tv.a5:
                     countA5++;
                     break;
                  // 'E' cases
                  case tv.e1:
                     countE1++;
                     break;
                  case tv.e2:
                     countE2++;
                     break;
                  case tv.e3:
                     countE3++;
                     break;
                  case tv.e4:
                     countE4++;
                     break;
                  // 'I' cases
                  case tv.i1:
                     countI1++;
                     break;
                  case tv.i2:
                     countI2++;
                     break;
                  case tv.i3:
                     countI3++;
                     break;
                  case tv.i4:
                     countI4++;
                     break;
                  // 'O' cases
                  case tv.o1:
                     countO1++;
                     break;
                  case tv.o2:
                     countO2++;
                     break;
                  case tv.o3:
                     countO3++;
                     break;
                  case tv.o4:
                     countO4++;
                     break;
                  case tv.o5:
                     countO5++;
                     break;
                   // 'U' cases
                  case tv.u1:
                     countU1++;
                     break;
                  case tv.u2:
                     countU2++;
                     break;
                  case tv.u3:
                     countU3++;
                     break;
                  case tv.u4:
                     countU4++;
                     break; 
               }
            }
            // resolver questao
            MyIO.println("a("+countA1+')'+ "e("+countE1+')'+ "i("+countI1+')'+ "o("+countO1+')'+ "u("+countU1+')'+ "á("+countA2+')'+ "é("+countE2+')'+ "í("+countI2+')'+ "ó("+countO2+')'+ "ú("+countU2+')'+ "à("+countA3+')'+ "è("+countE3+')'+ "ì("+countI3+')'+ "ò("+countO3+')'+ "ù("+countU3+')'+ "ã("+countA4+')'+ "õ("+countO4+')'+ "â("+countA5+')'+ "ê("+countE4+')'+ "î("+countI4+')'+ "ô("+countO5+')'+ "û("+countU4+')'+ "consoante("+countConsonant+')'+ "<br>("+countBr+')'+ "<table>("+countTable+") "+ nome);
         }
      }
   }

   public class tv {
      // a definitions
      static final char a1 = 'a';
      static final char a2 = 'á';
      static final char a3 = 'à';
      static final char a4 = 'ã';
      static final char a5 = 'â';
      // e definitions
      static final char e1 = 'e';
      static final char e2 = 'é';
      static final char e3 = 'è';
      static final char e4 = 'ê';
      // i definitions
      static final char i1 = 'i';
      static final char i2 = 'í';
      static final char i3 = 'ì';
      static final char i4 = 'î';
      // o definitions
      static final char o1 = 'o';
      static final char o2 = 'ó';
      static final char o3 = 'ò';
      static final char o4 = 'õ';
      static final char o5 = 'ô';
      // u definitions
      static final char u1 = 'u';
      static final char u2 = 'ú';
      static final char u3 = 'ù';
      static final char u4 = 'û';
   }

   public class MyIO {

      private static BufferedReader in = new BufferedReader(
            new InputStreamReader(System.in, Charset.forName("ISO-8859-1")));
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