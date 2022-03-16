import entities.Arq;

import static entities.MyIO.readInt;
import static entities.MyIO.readString;

public class exercios {
    /*
    max, esses ultimos metodos estao ficando corridos, porque o slide nao
    abre por completo no github, entao eu estava fazendo tudo pelo vim achando
    que era pouco exercicio. Ledo engano meu.
     */
    public static void questao1(){
        System.out.print("Paragraph:");
        String s = readString();
        System.out.print("FileName:");
        String filename = readString();
        Arq.openWrite(filename);
        Arq.println(s);
        Arq.close();
    }
    public static void questao2(){
        System.out.print("FileName:");
        String filename = readString();
        Arq.openRead(filename);
        System.out.println(Arq.readAll());
        Arq.close();
    }
    public static void questao3(){
        System.out.println("FileName:");
        String filename = readString();
        Arq.openRead(filename);
        String s = Arq.readAll();
        String newS = s.toUpperCase();
        System.out.println(newS);
        Arq.close();
    }
    public static void questao4(){
        System.out.println("filename 1:");
        String name1 = readString();
        System.out.println("filename 2:");
        String name2 = readString();
        Arq.openRead(name1);
        Arq.openWrite(name2);
        String cpy = Arq.openReadClose(name1);
        Arq.println(cpy);
        Arq.close();
    }

    public static void questao5(){
        System.out.println("filename 1:");
        String name1 = readString();
        System.out.println("filename 2:");
        String name2 = readString();
        String s = Arq.openReadClose(name1);
        String toUpperCase = s.toUpperCase();
        Arq.openWriteClose(name2,toUpperCase);
    }

    public static String reverseString(String s){
        StringBuilder newString = new StringBuilder(s);
        int k = s.length() - 1;
        for(int i = 0; i < s.length(); ++i, --k){
            newString.setCharAt(k,s.charAt(i));
        }
        return newString.toString();
    }
    public static void questao6(){
        System.out.println("filename 1:");
        String name1 = readString();
        System.out.println("filename 2:");
        String name2 = readString();
        String s = Arq.openReadClose(name1);
        String reverse = reverseString(s);
        Arq.openWriteClose(name2, reverse);
    }

    public static String toCesar(String s){
        StringBuilder newString = new StringBuilder(s);
        int k = s.length() - 1;
        for(int i = 0; i < s.length(); ++i, --k){
            newString.setCharAt(k, (char) (s.charAt(i)+3));
        }
        return newString.toString();
    }
    public static void questao7(){
        System.out.println("filename:");
        String name1 = readString();
        String content = Arq.openReadClose(name1);
        String newContent = toCesar(content);
        System.out.println(newContent);
    }

    public static String fromCesar(String s){
        StringBuilder newString = new StringBuilder(s);
        int k = s.length() - 1;
        for(int i = 0; i < s.length(); ++i, --k){
            newString.setCharAt(k, (char) (s.charAt(i)-3));
        }
        return newString.toString();
    }
    public static void questao8(){
        System.out.println("filename:");
        String name1 = readString();
        String content = Arq.openReadClose(name1);
        String newContent = fromCesar(content);
        System.out.println(newContent);
    }
    public static void main(String[] args){
        //chamar a funcao que quiser
    }
}
