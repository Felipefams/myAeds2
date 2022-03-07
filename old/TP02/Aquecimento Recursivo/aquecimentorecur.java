public class aquecimentorecur
{
	/*
    Funcao para  substituir a funcao .equals.
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
Essa funcao recebe a String original e um inteiro que representa as posicoes,
essa funcao tem o objetivo de contar o numero de letras maiusculas na String. 
*/
public static int maiusculas ( String x, int y )
{	
	int cont = 0;
	int tam = x.length();
	if ( y < tam )
	{
		if (x.charAt(y)>= 'A' && x.charAt(y) <= 'Z')
		{
			cont = cont + 1;
		}//fim do if
		cont = cont + maiusculas (x,y+1);
	}//fim do for
	return (cont);
}//fim da maiusculas


public static void main (String [] args)
{
	int resp;
	String x = "" ;
	int y = 0;
	while (! iguais(x,"FIM"))
	{
		x = MyIO.readLine ();
		if (! iguais(x,"FIM"))
		{
			resp = maiusculas (x,y);
			MyIO.println (resp);
		}//fim do fim	
	}//fim do while
}//fim da main
}//fim da class 
