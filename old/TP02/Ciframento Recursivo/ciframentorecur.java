public class ciframentorecur
{
	
   /*
    funcao iguais para substituir a funcao .equals.
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
	A funcao ciframento recebe a String original e um inteiro (que representa a posicao 0) como parametro
        e retorna uma String nova de modo recursivo apos o ciframento (que modifica a letra de cada posicao por uma 
   	letra 3 posicoes para frente do alfabeto).
   	ex: l = o;
  	 */
	public static String ciframento (String x, int y)
	{
		String resp = "";
		int tam = x.length();
		int d;
		char c;
		if ( y < tam )
		{
			d = ((int)x.charAt(y)+3)%255;
			c = (char)d;
			resp = resp + c;	
			resp = resp + ciframento(x,y+1);
		}//fim do if
		return (resp);	
	}//fim da ciframento

	public static void main (String [] args)
	{
		String resp;
		String x = "";
		int y = 0;
		while (!iguais(x,"FIM"))
		{	
			x = MyIO.readLine();
			if (!iguais(x,"FIM"))
			{
				resp = ciframento (x,y);
				MyIO.println (resp);
			}//fim do if	
		}//fim do while
	}//fim da main
}//fim da ciframentorecur
