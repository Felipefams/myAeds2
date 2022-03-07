import java.net.*;
import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;

public class http
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
		A funcao encontString so eh chamado para encontrar as Strings "<br>" e
		"<table>".
	*/
	
	public static String encontString (String s, int y, int tam)
	{
		String resp = "";
		int x; 
		
		if ( y + tam <= s.length() )
		{
			for(x = y; x < (y + tam); x=x+1)
			{
				resp = resp + s.charAt(x);
			}
		}//fim do if
		return(resp);
	}//fim encontString
	
	/*
		A funcao somEncontString tambem so eh chamdo para encontrar as Strings
		"<br>" e "<table>" e for encontrado eh acrentado um no somatario
		anterior.
	*/

	public static int somEncontString ( String x, String z )
	{
		int resp = 0;
		char c = z.charAt(0);
		char d;
		String teste = "";	
		for ( int y = 0; y < x.length(); y = y + 1 )
		{
			d = x.charAt(y);
			if ( d == c )
			{
				teste = encontString ( x,y,z.length() );
				if ( iguais (teste,z) )
				{		
					resp = resp + 1;
				}//fim do if
			}//fim do if
		}//fim do for
		return (resp);
	}//fim do somEncontString

	/*
		A funcao somando tem o objetivo de procurar determinadas letras,
		caso encontrado eh acrescentado um no somatorio anterior.
	*/

	public static int somando ( String x, char c )
	{
		int resp = 0;
		char d;
		for ( int y = 0; y < x.length(); y = y + 1 )
		{
			d = x.charAt(y);
			if ( d == c )
			{
				resp = resp + 1;
			}//fim do if
		}//fim do for
		return (resp);
	}//fim do somando

	/*
		A funcao consoante retorna se a letra eh consoante, independente se
		essa consoante tiver acento.
	*/

	public static boolean consoante ( char c )
	{
		boolean resp = false;
		if ( c == 'ĉ' || c == 'ĝ' || c == 'ĥ' || c == 'ĵ' || c == 'ŝ' )
		{
			resp = true;
		}//fim do if
		else
		{
			if ( c >= 'a' && c <= 'z' || c >= 'A' && c <= 'Z' ) // c >= 'A' && c <= 'z'
			{	
				if ( c != 'a' && c != 'e' && c != 'i' && c != 'o' && c != 'u' &&
					c != 'A' && c != 'E' && c != 'I' && c != 'O' && c != 'U') 
				{
					resp = true;
				}//fim do if
			}//fim do if
		}//fim do else
		return (resp);
	}//fim da consoante
	
	/*
		O metodo http eh o metodo onde as outras funcoes sao chamadas e retorna
		a saida para o usuario.
	*/

	public static void http (String s, String site) throws Exception
	{		
		String line = "";
		String  html = "";
		int x;
		int cont = 0;
		int resp;
		URL url = new URL(site);
		URLConnection conn = url.openConnection();
		InputStream is = url.openStream();
		InputStreamReader isr = new InputStreamReader(is);
		BufferedReader br = new BufferedReader(isr);

		line = br.readLine();
		while(line != null)
		{
			html = html+line;
			line = br.readLine();
		}//fim do while
		
		resp = somando (html,'a');
		MyIO.print("a("+resp+") ");
		resp = somando (html,'e');
		MyIO.print("e("+resp+") ");
		resp = somando (html,'i');
		MyIO.print("i("+resp+") ");
		resp = somando (html,'o');
		MyIO.print("o("+resp+") ");
		resp = somando (html,'u');
		MyIO.print("u("+resp+") ");
		resp = somando (html,'á');
		MyIO.print("á("+resp+") ");
		resp = somando (html,'é');
		MyIO.print("é("+resp+") ");
		resp = somando (html,'í');
		MyIO.print("í("+resp+") ");
		resp = somando (html,'ó');
		MyIO.print("ó("+resp+") ");
		resp = somando (html,'ú');
		MyIO.print("ú("+resp+") ");
		resp = somando (html,'à');
		MyIO.print("à("+resp+") ");
		resp = somando (html,'è');
		MyIO.print("è("+resp+") ");
		resp = somando (html,'ì');
		MyIO.print("ì("+resp+") ");
		resp = somando (html,'ò');
		MyIO.print("ò("+resp+") ");
		resp = somando (html,'ù');
		MyIO.print("ù("+resp+") ");
		resp = somando (html,'ã');
		MyIO.print("ã("+resp+") ");
		resp = somando (html,'õ');
		MyIO.print("õ("+resp+") ");
		resp = somando (html,'â');
		MyIO.print("â("+resp+") ");
		resp = somando (html,'ê');
		MyIO.print("ê("+resp+") ");
		resp = somando (html,'î');
		MyIO.print("î("+resp+") ");
		resp = somando (html,'ô');
		MyIO.print("ô("+resp+") ");
		resp = somando (html,'û');
		MyIO.print("û("+resp+") ");

		for(x = 0; x < html.length(); x++)
		{
			if(consoante(html.charAt(x))){
				cont = cont + 1;
			}//fim do if
		}//fim do for

		MyIO.print("consoante("+cont+") ");
		resp = somEncontString (html,"<br>");
		MyIO.print("<br>("+resp+") ");
		resp = somEncontString (html,"<table>");
		MyIO.print("<table>("+resp+") ");
		MyIO.println(s);
	}//fim da http	

	public static void main (String [] args)throws Exception
	{		
		String s, site;
		MyIO.setCharset("UTF8");
		s = MyIO.readLine ();
		while(!iguais(s, "FIM"))
		{
			if (!iguais(s, "FIM"))
			{
				site = MyIO.readLine ();
				http(s,site);
			}
			s = MyIO.readLine();
		}
	}//fim da main

}//fim da class http
