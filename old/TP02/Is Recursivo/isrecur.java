public class isrecur
{
   /*
   A funcao vogais recebe a String original e um inteiro que representa as posicao
    como parametro e retorna se ela so contem voagais ou nao de forma recursiva.
   */
	public static boolean vogais ( String x, int y )
	{
		boolean resp = true; 
		int tam = x.length ();
		if ( y < tam )
		{
			if (x.charAt(y)=='a' || x.charAt(y)=='e' || x.charAt(y)=='i' || x.charAt(y)=='o' || x.charAt(y)=='u')
			{
				resp = resp && true;
			}//fim do if
			else
			{
				resp = false;
			}//fim do else
			resp = resp && vogais(x,y+1);
		}//fim do if
		return (resp);
	}//fim do vogais

   /*
   A funcao consoantes recebe a String original e um inteiro que representa posicao
   como parametro e retorna se a String contem apenas consoantes de forma recursiva.
   */
   public static boolean consoantes ( String x, int y )
	{
		boolean resp = true; 
		int tam = x.length ();
		if ( y < tam )
		{
			if ( x.charAt(y)>='0' && x.charAt(y) <='9')
			{
				resp = false;
			}//fim do if
			else
			{
				 if ( x.charAt(y)=='a' || x.charAt(y)=='e'|| x.charAt(y)=='i' || x.charAt(y)=='o' || x.charAt(y)=='u')
				{
					resp = false;
				}//fim do if
				else
				{	
					resp = resp && true;
				}//fim do else
			}//fim do else
			resp = resp && consoantes ( x, y+1 );
		}//fim do if
		return (resp);
	}//fim do consoantes

   /*
   A funcao inteiros recebe a String original e um inteiro que representa a posicao
   como paramentro e retorna se a String contem apenas numeros inteiros de forma recursiva.
   */
	public static boolean inteiros  (String x, int y )
	{
		boolean resp = true;
		int tam = x.length();
		if ( y < tam )
		{
			if (x.charAt(y) >= '0'&& x.charAt(y) <='9')
			{
				resp = resp && true;
			}//fim do if
			else
			{
				resp = false;
			}//fim do else
			resp = resp && inteiros (x,y+1);
		}//fim do if
		return (resp);
	}//fim do inteiros

   /*
   A funcao reais recebe a String original, um inteiro que representa a posicao e um inteiro que representa um contador 
   de pontos e virgulas como paramentro e retorna se ela contem um numero real de forma recursiva, para nao existir 
   erros o contador sera testado.
   */
	public static boolean reais (String x, int y, int cont)
	{		
		boolean resp = true;
		int tam = x.length();
		if ( y < tam )
		{
			if (x.charAt(y) >= '0' && x.charAt(y) <='9') 
			{
				resp = resp && true;
			}//fim do if
			else
			{	
				if (x.charAt (y) == '.' || x.charAt(y)==',')
				{
					cont  = cont + 1;
					if (cont <= 1)
					{
						resp = resp && true;
					}//fim do if
					else
					{
						resp = false ;
					}//fim do else
				}//fim do if
				else
				{
					resp = false;
				}//fim do else
			}//fim do else
			resp = resp && reais ( x,y+1,cont );
		}//fim do if
		return (resp);
	}//fim do reais

	public static void main (String [] args)
	{
		String x = "";
		boolean vogal;
		boolean consoante;
		boolean inteiro;
		boolean real;
		while ( ! x.equals ("FIM"))
		{
			x = MyIO.readLine ();
			if (! x.equals("FIM"))
			{	
				vogal = vogais(x,0);
				if (vogal)
				{
					MyIO.print ("SIM ");
				}//fim do if		
				else
				{
					MyIO.print ("NAO ");
				}//fim do else

				consoante = consoantes(x,0);
				if (consoante)
				{
					MyIO.print ("SIM ");
				}//fim do if		
				else
				{
					MyIO.print ("NAO ");
				}//fim do else

				inteiro = inteiros(x,0);
				if (inteiro)
				{
					MyIO.print ("SIM ");
				}//fim do if		
				else
				{
					MyIO.print ("NAO ");
				}//fim do else

				real = reais(x,0,0);
				if (real)
				{
					MyIO.print ("SIM");
				}//fim do if		
				else
				{
					MyIO.print ("NAO");
				}//fim do else
				MyIO.println ("");
			}//fim do if
			
		}//fim do while
	}//fim da main	

}//fim da isrecur
