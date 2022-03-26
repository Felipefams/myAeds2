import entities.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.StringTokenizer;

public class ex01{
   /*
   remove todas as tags menos o <script>
    */
    static String removeTag(String s){
         return s.replaceAll("<[^>]*>", "");
    }

    /*
    checa o limite superior da tag, no caso o '>'
     */
    static int getTagBound(String s, int pos){
        int found = 0;
        for(int i = pos; i < s.length(); i++){
            if(s.charAt(i) == '>'){
                found = i + 1;
                break;
            }
        }
        return found;
    }
   /*
   checa ate onde vao os espacos em branco
    */
    static int getSpaceBound(String s, int pos){
        int found = 0;
        for(int i = pos; i < s.length(); i++){
            if(s.charAt(i) != ' '){
                found = i;//nesse caso aqui vai ser i e nao i+1, porque a gente nao quer a letra, so os espacos em branco
                break;
            }
        }
        return found;
    }
    /*
    remove as tags de script e tudo que estiver dentro.
     */
    static String removeScript(String s){
       StringBuilder sb = new StringBuilder(s);
        for(int i =0; i < s.length(); i++){
            int left = 0;
            int right = 0;
            if(s.charAt(i) == '<'){
                left = i;
                if(s.substring(i, getTagBound(s,i)).equals("<script>")){
                    for(int k = i; k < s.length(); k++) {
                        if(s.charAt(k) == '<'){
                            if(s.substring(k, getTagBound(s, k)).equals("</script>")){
                                right = getTagBound(s,k);
                                sb.replace(left, right, "");
                                break;
                            }
                        }
                    }
                }
            }
        }
        return sb.toString();
    }

    static String filterHtml(String s){
        return removeTag(removeScript(s));
    }

    public static void main(String[] args){
        String s =  "<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\"\n" +
                "        \"http://www.w3.org/TR/html4/loose.dtd\">\n" +
                "<html>\n" +
                "<head>\n" +
                "    <title>This is the page title</title>\n" +
                "</head>\n" +
                "<script>\n" +
                "    // some interesting script functions\n" +
                "</script>\n" +
                "<body>\n" +
                "    <p>\n" +
                "        If the application X doesn't start, the possible causes could be:<br/>\n" +
                "        1. <a\n" +
                "            id=\"link\"\n" +
                "            href=\"http://maven.apache.org/\">\n" +
                "            Maven\n" +
                "            </a> is not installed.<br/>\n" +
                "        2. Not enough (<1G) disk space.<br/>\n" +
                "        3. Not enough (<64MB) memory.<br/>\n" +
                "    </p>\n" +
                "</body>\n" +
                "</html>";
        System.out.println("String antiga:");
        System.out.println(s);
        System.out.println("String Nova:");
//        String noScript = removeScript(s);
        String result = filterHtml(s);
        System.out.println(result);
//        System.out.println(noScript);
    }

    //Minha classe pra leitura

}
