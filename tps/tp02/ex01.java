import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collector;

import entities.*;

public class ex01 {

    static String removeTag(String s) {
        return s.replaceAll("<[^>]*>", "");
    }

    /*
    filtra a posicao onde o nome esta e retorna apenas o conteudo desejado
     */
    static String filterTitle(String s) {
        String tmp = removeTag(s);
        var sb = new StringBuilder();
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
    faz o parse da variavel adquirida de string pra date
     */
    static Date filterDate(String s) throws ParseException {
        var sdf = new SimpleDateFormat("dd/MM/yyyy");
        var sb = new StringBuilder();
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
        var sb = new StringBuilder();
        for (int i = 0; i < tmp.length(); i++) {
            if (tmp.charAt(i) != ' ') {
                sb.append(tmp.charAt(i));
            }
        }
        return sb.toString().replaceAll("&nbsp;", "");
    }

    /*
    convert a String in the format: xH yM to the integer equivalent in minutes
     */
    static int stringHoursToIntMinutes(String s) {
        int[] intArr = new int[2];
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == 'h') {
                //da pra fazer utilizando i-1, porque a gente sabe
                //que nenhum filme vai ter mais de 10 horas
                intArr[0] = (int) s.charAt(i - 1) - '0';
                var tmp = new StringBuilder();
                //i + 1 pra nao pegar o h na string
                for (int j = i + 1; j < s.length(); j++) {
                    if (s.charAt(j) != ' ') {
                        if (s.charAt(j) == 'm') {
                            break;
                        } else {
                            tmp.append(s.charAt(j));
                        }
                    }
                }
                intArr[1] = Integer.parseInt(tmp.toString());
                break;
            }
        }
        return ((intArr[0] * 60) + intArr[1]);
    }

    static int filterRuntime(String s) {
        var sb = new StringBuilder();
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) != ' ') {
                sb.append(s.charAt(i));
            }
        }
        return stringHoursToIntMinutes(sb.toString());
    }

    /*
    filtra todas as tags StrongBdi, no caso o budget, situacao e idioma original.
     */
    static String filterStrongBdiTag(String s) {
        String tmp = removeTag(s);
        var sb = new StringBuilder();
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

    static String filterOriginalTitle(String s){
        var sb = new StringBuilder();
        int countGreaterSimbol = 0;
        for(int i = 0; i < s.length(); i++){
            if(s.charAt(i) == '>'){
               countGreaterSimbol++;
            }
            if (countGreaterSimbol == 3){
                for(int j = i+2; j < s.length(); j++){
                   if(s.charAt(j) == '<'){
                       break;
                   }else{
                       sb.append(s.charAt(j));
                   }
                }
                break;
            }
        }
        return sb.toString();
    }

    static void solve(String name) throws ParseException {
        Locale usa = new Locale("en", "US");
        String path = "tmp/filmes/";
        String filename = path + name;
        var sdf2 = new SimpleDateFormat("dd/MM/yyyy");
        Arq.openRead(filename);
        Filme filme = new Filme();
        /*
        Eu sei que esses loops com while true nao sao o ideal,
        mas nesses casos vai dar literalmente na mesma de fazer
        assim ou usando o seu !linha.contains, os excessoes que
        podem surgir desse uso sao as mesmas.
         */
        //nome
        while (true) {
            String tmp = Arq.readLine();
            if (tmp.contains("<title>")) {
                filme.setNome(filterTitle(tmp).trim());
                break;
            }
        }
        //data de Lancamento
        while (true) {
            String tmp = Arq.readLine();
            if (tmp.contains("class=\"release\"")) {
                tmp = Arq.readLine();
                tmp = tmp.trim();
                //precisa do throws na main ou o try-catch
                filme.setDataLancamento(filterDate(tmp));
                break;
            }
        }
        //genero
        while (true) {
            String tmp = Arq.readLine();
            if (tmp.contains("class=\"genres\"")) {
                tmp = Arq.readLine();
                tmp = Arq.readLine();
                filme.setGenero(filterGenre(tmp));
                break;
            }
        }
        //duracao
        while (true) {
            String tmp = Arq.readLine();
            if (tmp.contains("class=\"runtime\"")) {
                tmp = Arq.readLine();
                tmp = Arq.readLine();
                filme.setDuracao(filterRuntime(tmp));
                break;
            }
        }
        //titulo original
        int count = 0;
        while(true){
            String tmp = Arq.readLine();
            if(count < 0){
                filme.setTituloOriginal(filme.getNome());
                break;
            }
            if(tmp.contains("ulo original")){
                filme.setTituloOriginal(filterOriginalTitle(tmp));
                break;
            }else{
                count--;
            }
        }
        //situacao
        while (true) {
            String tmp = Arq.readLine();
            if (tmp.contains("<strong><bdi>Situ")) {
                //o filter budget resolve o problema
                filme.setSituacao(filterStrongBdiTag(tmp));
                break;
            }
        }
        //idioma original
        while (true) {
            String tmp = Arq.readLine();
            if (tmp.contains("Idioma original")) {
                tmp = filterStrongBdiTag(tmp);
                filme.setIdiomaOriginal(tmp);
                break;
            }
        }
        //aqui nao vai precisar fazer o while, pq a informacao ja vai estar na linha seguinte
        //vou fazer o while so pra usar a String tmp como var local
        //orcamento
        while (true) {
            String tmp = Arq.readLine();
            if (tmp.contains("$")) {
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
                //ler mais uma linha pra sair do <ul>
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
        MyIO.println(filme.toString());
        Arq.close();
    }

    public static void main(String[] args) throws ParseException {
        while (true) {
            String s = MyIO.readLine();
            if (s.equals("FIM")) {
                break;
            } else {
                solve(s);
            }
        }

    }

}
