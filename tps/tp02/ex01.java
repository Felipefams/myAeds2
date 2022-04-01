import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collector;

import entities.*;

public class ex01 {
    /*
     * remove todas as tags menos o <script>
     */
    static String removeTag(String s) {
        return s.replaceAll("<[^>]*>", "");
    }

    /*
     * checa o limite superior da tag, no caso o '>'
     */
    static int getScriptBound(String s, int pos) {
        int found = 0;
        for (int i = pos; i < s.length(); i++) {
            if (s.charAt(i) == '>') {
                found = i + 1;
                break;
            }
        }
        return found;
    }

    /*
     * checa ate onde vao os espacos em branco
     */
    static int getSpaceBound(String s, int pos) {
        int found = 0;
        for (int i = pos; i < s.length(); i++) {
            if (s.charAt(i) != ' ') {
                found = i;// nesse caso aqui vai ser i e nao i+1, porque a gente nao quer a letra, so os
                          // espacos em branco
                break;
            }
        }
        return found;
    }

    /*
     * remove as tags de script e tudo que estiver dentro.
     */
    static String removeScript(String s) {
        StringBuilder sb = new StringBuilder(s);
        for (int i = 0; i < sb.length(); i++) {
            if (sb.charAt(i) == '<') {
                StringBuilder tmp = new StringBuilder();
                for (int k = i; k < sb.length(); k++) {
                    tmp.append(sb.charAt(k));
                    if (sb.charAt(k) == '>') {
                        break;
                    }
                }
                if (tmp.toString().contains("<script")) {
                    int k = 0;
                    while (!tmp.toString().contains("</script>")) {
                        tmp.append(sb.charAt(i + k));
                        sb.setCharAt(i + k, ' ');
                        k++;
                    }
                }
            }
        }
        return sb.toString();
    }

    static List<String> toStringList(String s) {
        List<String> sList = new ArrayList<>();
        for (int i = 0; i < s.length(); i++) {
            StringBuilder tmp = new StringBuilder();
            if (Character.isLetterOrDigit(s.charAt(i))) {
                int k = i;
                while (s.charAt(k) != ' ') {
                    if (s.charAt(k) == ' ') {
                        break;
                    }
                    tmp.append(s.charAt(k));
                    k++;
                }
                sList.add(tmp.toString());
            }
        }
        return sList;
    }

    static String filterHtml(String s) {
        return removeTag(removeScript(s));
    }

    public static void main(String[] args) {
        String filename = "filmes/Cruella.html";
        String toFilter = Arq.openReadClose(filename);
        List<String> sList = new ArrayList<>();
        /*
        sList = toStringList(filterHtml(toFilter));
        for (String x : sList) {
            System.out.println(x);
        }
        */
        System.out.println(filterHtml(toFilter.replaceAll("\n", "")));
    }

}
