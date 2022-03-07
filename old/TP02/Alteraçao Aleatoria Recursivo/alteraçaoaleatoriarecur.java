import java.util.Random;
public class alteraçaoaleatoriarecur
{	
   /*
   Fiz a funcao iguais para substituir a funcao .equals.
   */
	public static boolean iguais ( String x , String y )
	{
		int tam = y.length();
		boolean resp = true;
		if (x.length() != y.length())
		{
			resp = false;
		}//fim do if
		else
		{
			for (int z = 0; z < tam;z = z +1)
			{
				if (x.charAt(z) == y.charAt(z))
				{
					resp = true;
				}//fim do if
				else
				{
					resp = false;
					z = tam;
				}//fim do else
			}//fim do for
		}//fim do else
		return (resp);	
	}//fim do iguais

   /*
   A funcao aleatoria escolhe aleatoriamente um char na String 
   para ser substituido por outro char aleatorio. Ela retorna
   a nova String formada de forma recursiva. O inteiro dado como
   parametro serve como contador de posicao para percorrer a String.
   */
	public static String aleatoria (String x, int y,char c , char d)
	{
		String resp = "";		
		char e;
		if ( y < x.length() )
		{			
			e = x.charAt(y);
			if (e == c)
			{
				e = d;
			}//fim do if
			resp = resp + e;
			resp = resp + aleatoria (x, y +1,c,d);
		}//fim do for
		return (resp);
	}//fim do aleatoria
	
	public static void main (String [] args)
	{
		String x = "";
		Random gerador = new Random();	
		gerador.setSeed(4);
		String resp = "";
		int y = 0;
		while (! iguais(x,"FIM"))
		{
			x = MyIO.readLine ();
			if (! iguais (x,"FIM"))
			{
				char c=(char)('a' + (Math.abs(gerador.nextInt()) % 26));
				char d=(char)('a' + (Math.abs(gerador.nextInt()) % 26));

				resp = aleatoria (x,y,c,d);
				MyIO.println (resp);
			}//fim do if
		}//fim do while
	}//fim da main
}//fim da class
