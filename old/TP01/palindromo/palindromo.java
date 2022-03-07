public class palindromo 
{
   /*
   A funcao palindromo recebe a String original como parametro
   e retorna se ela eh ou nao um palindromo, ou seja, pode ser
   lido da direita ou da esquerda que formara a mesma String.
   */
	public static boolean palindromo ( String x )
	{
		boolean resp = true;
		int tam = x.length();
		int cont = tam - 1;
		for (int y = 0; y < tam/2; y = y + 1 )
		{
			if (x.charAt(y) != x.charAt(cont))
			{
				resp = resp && false;
			}//fim do if
			cont = cont - 1;
		}//fim do for
		return (resp);
	}//fim da teste
	public static void main (String [] args)
	{
		boolean resp;
		String x = "";
		while ( ! x.equals("FIM"))
		{
			x = MyIO.readLine();
			if ( ! x.equals ("FIM"))
			{
				resp = palindromo(x);
				if (resp)
				{
					MyIO.println ("SIM");
				}//fim do if	
				else
				{
					MyIO.println ("NAO");
				}//fim do else
			}//fim do if	
		}//fim do while
	}//fim da main	
}//fim da class
