public class aquecimento
{
public static int maiusculas ( String x )
{	
	int cont = 0;
		int tam = x.length();
	for (int y = 0; y < tam; y = y + 1)
	{
		if (x.charAt(y)>= 'A' && x.charAt(y) <= 'Z')
		{
			cont = cont + 1;
		}//fim do if
	}//fim do for
return (cont);
}//fim da maiusculas
public static void main (String [] args)
{
	int resp;
	String x = "" ;
	while (! x.equals("FIM"))
	{
		x = MyIO.readLine ();
		if (! x.equals("FIM"))
		{
			resp = maiusculas (x);			 
			MyIO.println (resp);
		}//fim do fim	
	}//fim do while
}//fim da main
}//fim da class 
