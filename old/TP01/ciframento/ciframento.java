public class ciframento
{
   /*
   A funcao ciframento recebe a String original como parametro
   e retorna uma String nova apos o ciframento (que modifica a
   letra de cada posicao por uma letra 3 posicoes para frente.
   ex: l = o;
   */
	public static String ciframento (String x)
	{	
		int tam = x.length();
		String resp  = "";
		int d;
		char c;
		for (int y = 0;y < tam;y = y +1)
		{	
			d = ((int)x.charAt(y)+3)%255;
			c = (char)d;
			resp = resp + c;	
		}//fim do for
		return (resp);
	}//fim do ciframento
	public static void main (String [] args)
	{
		String resp;
		String x = "";
		while (!x.equals("FIM"))
		{	
			x = MyIO.readLine();
			if (!x.equals("FIM"))
			{
				resp = ciframento (x);
				MyIO.println (resp);
			}//fim do if	
		}//fim do while
	}//fim da main
}//fim da ciframento
