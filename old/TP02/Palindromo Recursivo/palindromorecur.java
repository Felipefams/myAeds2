public class palindromorecur 
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
   A funcao palindromo recebe a String original e duas variaveis inteiras (cada uma vai por um lado da String,
   posicao 0 e a outra length()-1) como parametro e retorna se ela eh ou nao um palindromo de forma recursiva,
   ou seja, pode ser lido da direita ou da esquerda que formara a mesma String.
   */
		
	public static boolean palindromo ( String x, int y, int cont)
	{
		boolean resp = true;
		int tam = x.length();				
		if ( y < tam/2 )
		{			
			if (x.charAt(y) != x.charAt(cont))
			{	
				resp = false;
			}//fim do if
			resp = resp && palindromo (x,y+1,cont - 1);
		}//fim do if
		return(resp);
	}//fim do palindromo

	public static void main (String [] args)
	{
		boolean resp;
		String x = "";
		int y = 0;
		int cont;
		while ( ! iguais(x,"FIM"))
		{
			x = MyIO.readLine();
			if ( ! iguais (x,"FIM"))
			{
				cont = x.length() - 1; 
				resp = palindromo(x,y,cont);
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
