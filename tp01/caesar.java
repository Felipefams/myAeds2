// Code by Felipe Augusto Morais Silva
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.ByteBuffer;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.StringTokenizer;

public class caesar {

    static class FastReader {
//        BufferedReader br;
        StringTokenizer st;
        private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in, StandardCharsets.ISO_8859_1));

        public static void setCharset(String charset_){
            br = new BufferedReader(new InputStreamReader(System.in, Charset.forName(charset_)));
        }
        public FastReader()
        {
            br = new BufferedReader(
                new InputStreamReader(System.in));
        }
 
        String next()
        {
            while (st == null || !st.hasMoreElements()) {
                try {
                    st = new StringTokenizer(br.readLine());
                }
                catch (IOException e) {
                    e.printStackTrace();
                }
            }
            return st.nextToken();
        }
 
        int nextInt() { return Integer.parseInt(next()); }
 
        long nextLong() { return Long.parseLong(next()); }
 
        double nextDouble()
        {
            return Double.parseDouble(next());
        }
 
        String nextLine()
        {
            String str = "";
            try {
                str = br.readLine();
            }
            catch (IOException e) {
                e.printStackTrace();
            }
            return str;
        }
    }
 
    public static String toCesar(String s){
        //isso aqui vai dar na mesma de printar (char) string.charAt(i) + 3;
	//mas pra evitar problema vou fazer outro metodo ali em baixo pra so printar + 3...
        StringBuilder newString = new StringBuilder(s);
        for(int i = 0; i < s.length(); ++i){
            newString.setCharAt(i, (char) (s.charAt(i)+3));
        }
        return newString.toString();
    }
    public static void toCesar2(String s){
	    for(int i = 0; i < s.length(); ++i){
		    if(s.charAt(i) != ' '){
                if(s.charAt(i) >= '0' && s.charAt(i) <= '9'){
                    System.out.print(s.charAt(i) -45);
                }else{
                    System.out.print((char) (s.charAt(i) + 3));
                }
	   	 }else{System.out.println(' ');}
	    }
	    System.out.println();
    }

    public static void main(String[] args) {
        FastReader fr = new FastReader();
		String s;// = MyIO.readString();
		boolean b = true;
		while(b){
			s = fr.nextLine();
			if(s.length() == 3 && (s.charAt(0) == 'F' && s.charAt(1) == 'I' && s.charAt(2) == 'M')){
				b = false;
			}
			else{
		//	System.out.println(toCesar(s));
			ByteBuffer buffer = StandardCharsets.ISO_8859_1.encode(s);
			String newString = StandardCharsets.ISO_8859_1.decode(buffer).toString();
			toCesar2(newString);
			}
		}
    }   
}
