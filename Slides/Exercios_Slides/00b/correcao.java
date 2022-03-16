import java.lang.*;
public class correcao{

	public static boolean isVogal(char c){
		c = Character.toUpperCase(c);
		return(c == 'A' || c == 'E' || c == 'I' || c == 'O' || c == 'U');
	}
	public static boolean isConsoante(String s, int i){
		return !isVogal(s.charAt(i)); 
	}
	public static void main (String [] args){
		String text = "Testando";
		int pos = 1;
		System.out.println(isConsoante(text,pos)?"YES":"NO");
	}
}
