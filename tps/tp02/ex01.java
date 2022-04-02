import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.stream.Collector;

import entities.*;

public class ex01 {

    static String removeTag(String s){
        return s.replaceAll("<[^>]*>", "");
    }
    
    /*
    filtra a posicao onde o nome esta e retorna apenas o conteudo desejado
     */
    static String filterName(String s){
        String tmp = removeTag(s);
        var sb = new StringBuilder();
        for(int i = 0; i < tmp.length(); i++){
            if(tmp.charAt(i) == '('){
                break;
            }else{
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
        for(int i = 0; i < s.length(); i++){
            if(s.charAt(i) == '('){
                break;
            }else if(s.charAt(i) != ' '){
                sb.append(s.charAt(i));
            }
        }
        return sdf.parse(sb.toString());
    }

    static String filterGenre(String s){
        String tmp = removeTag(s);
        var sb = new StringBuilder();
        for(int i = 0; i < tmp.length(); i++){
            if(tmp.charAt(i) != ' '){
                sb.append(tmp.charAt(i));
            }
        }
        return sb.toString().replaceAll("&nbsp;", "");
    }
    /*
    convert a String in the format: xH yM to the integer equivalent in minutes
     */
    static int stringHoursToIntMinutes(String s){
        int[] intArr = new int[2];
        for(int i = 0; i < s.length(); i++){
           if(s.charAt(i) == 'h'){
               //da pra fazer utilizando i-1, porque a gente sabe
               //que nenhum filme vai ter mais de 10 horas
               intArr[0] = (int) s.charAt(i-1) - '0';
               var tmp = new StringBuilder();
               //i + 1 pra nao pegar o h na string
               for(int j = i+1; j < s.length(); j++){
                   if(s.charAt(j) != ' '){
                      if(s.charAt(j) == 'm'){
                          break;
                      }else{
                          tmp.append(s.charAt(j));
                      }
                   }
               }
               intArr[1] = Integer.parseInt(tmp.toString());
               break;
           }
        }
        return ((intArr[0]*60) + intArr[1]);
    }

    static int filterRuntime(String s){
        var sb = new StringBuilder();
        for(int i = 0; i < s.length(); i++){
            if(s.charAt(i) != ' '){
                sb.append(s.charAt(i));
            }
        }
        return stringHoursToIntMinutes(sb.toString());
    }

    /*
    filtra todas as tags StrongBdi, no caso o budget, situacao e idioma original.
     */
    static String filterStrongBdiTag(String s){
        String tmp = removeTag(s);
        var sb = new StringBuilder();
        for(int i = tmp.length() - 1; i > 0; i--){
            if(tmp.charAt(i) != ' '){
                int j = i;
                while(tmp.charAt(j--) != ' '){
                    if(tmp.charAt(j) == ' '){
                        break;
                    }else{
                        sb.append(tmp.charAt(j));
                    }
                }
                break;
            }
        }
        return sb.reverse().toString();
    }

    public static void main(String[] args) throws ParseException {

        String filename = "filmes/Cruella.html";
        var sdf2 = new SimpleDateFormat("dd/MM/yyyy");
        Arq.openRead(filename);
        Filme filme = new Filme();
        /*
        Eu sei que esses loops com while true nao sao o ideal,
        mas nesses casos vai dar literalmente na mesma de fazer
        assim ou usando o seu !linha.contains, os excessoes que
        podem surgir desse uso sao as mesmas.
         */
        while(true){
            String tmp = Arq.readLine();
            if(tmp.contains("<title>")){
                filme.setNome(filterName(tmp).trim());
				break;
            }
        }
        while(true){
            String tmp = Arq.readLine();
            if(tmp.contains("class=\"release\"")){
                tmp = Arq.readLine();
                tmp = tmp.trim();
                //precisa do throws na main ou o try-catch
                filme.setDataLancamento(filterDate(tmp));
                break;
            }
        }
        while(true){
            String tmp = Arq.readLine();
            if(tmp.contains("href=\"/genre")){
                filme.setGenero(filterGenre(tmp));
                break;
            }
        }
        while(true){
            String tmp = Arq.readLine();
            if(tmp.contains("class=\"runtime\"")){
                tmp = Arq.readLine();
                tmp = Arq.readLine();
                filme.setDuracao(filterRuntime(tmp));
                break;
            }
        }
        while(true){
            String tmp = Arq.readLine();
            if(tmp.contains("Situ")){
                //o filter budget resolve o problema
                filme.setSituacao(filterStrongBdiTag(tmp));
                break;
            }
        }
        while(true){
            String tmp = Arq.readLine();
            if(tmp.contains("Idioma original")){
                tmp = filterStrongBdiTag(tmp);
                filme.setIdiomaOriginal(tmp);
               break;
            }
        }
        //aqui nao vai precisar fazer o while, pq a informacao ja vai estar na linha seguinte
        //vou fazer o while so pra usar a String tmp como var local
        while(true){
            String tmp = Arq.readLine();
            if(tmp.contains("$")){
                tmp = filterStrongBdiTag(tmp);
                filme.setOrcamento(Float.parseFloat(tmp.replaceAll("[^\\d.]", "")));
                break;
            }
        }

        System.out.println(filme.getNome());
        //na hora de printar o Date tem que lembrar sempre de formatar
        System.out.println(sdf2.format(filme.getDataLancamento()));
        System.out.println(filme.getDuracao());
        System.out.println(filme.getGenero());
        System.out.println(filme.getIdiomaOriginal());
        System.out.println(filme.getSituacao());
        System.out.println(filme.getOrcamento());
        /*
        ordem que ele quer que printa:
        nome
        titOrig
        dataLanc
        Durac
        Genero
        idioma
        Situacao
        orcamento
        keywords
         */
        Arq.close();
    }

}
