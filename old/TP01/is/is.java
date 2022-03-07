public class is 
{
   /*
   A funcao vogais recebe a String original como parametro
   e retorna se ela so contem voagais ou nao.
   */
	public static boolean vogais ( String x )
	{
		boolean resp = true; 
		int tam = x.length ();
		for(int y = 0;y < tam; y = y + 1)
		{
			if (x.charAt(y)=='a' || x.charAt(y)=='e' || x.charAt(y)=='i' || x.charAt(y)=='o' || x.charAt(y)=='u')
			{
				resp = resp && true;
			}//fim do for
			else
			{
				resp = false;
			}//fim do else
		}//fim do for
		return (resp);
	}//fim do vogais

   /*
   A funcao consoantes recebe a String original como parametro
   e retorna se a String contem apenas consoantes.
   */
   public static boolean consoantes ( String x )
	{
		boolean resp = true; 
		int tam = x.length ();
		for(int y = 0;y < tam; y = y + 1)
		{
			if ( x.charAt(y)>='0' && x.charAt(y) <='9')
			{
				resp = false;
			}//fim do for
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
		}//fim do for
		return (resp);
	}//fim do consoantes

   /*
   A funcao inteiros recebe a String original como paramentro
   e retorna se a String contem apenas numeros inteiros.
   */
	public static boolean inteiros  (String x)
	{
		boolean resp = true;
		int tam = x.length();
		for (int y=0;y < tam; y = y + 1)
		{
			if (x.charAt(y) >= '0'&& x.charAt(y) <='9')
			{
				resp = resp && true;
			}//fim do if
			else
			{
				resp = false;
			}//fim do else
		}//fim do for
		return (resp);
	}//fim do inteiros

   /*
   A funcao reais recebe a String original como paramentro
   e retorna se ela contem um numero real, para nao existir
   erros contei o numero de pontos e virgulas.
   */
	public static boolean reais (String x)
	{
		int cont = 0;
		boolean resp = true;
		int tam = x.length();
		for (int y=0;y < tam; y = y + 1)
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
		}//fim do for
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
				vogal = vogais(x);
				if (vogal)
				{
					MyIO.print ("SIM ");
				}//fim do if		
				else
				{
					MyIO.print ("NAO ");
				}//fim do else

				consoante = consoantes(x);
				if (consoante)
				{
					MyIO.print ("SIM ");
				}//fim do if		
				else
				{
					MyIO.print ("NAO ");
				}//fim do else

				inteiro = inteiros(x);
				if (inteiro)
				{
					MyIO.print ("SIM ");
				}//fim do if		
				else
				{
					MyIO.print ("NAO ");
				}//fim do else

				real = reais(x);
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

}//fim da is
