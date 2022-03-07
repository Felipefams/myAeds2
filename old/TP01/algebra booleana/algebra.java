public class algebra
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
A funcao simpl recebe a String original como paramentro junto com os valores das variaveis lidas na main.
Ela tem o papel de simplificar a String, retirando os espacos em branco, as virgulas e substituir as variaveis
pelos seus respectivos valores. E retorna uma nova String simplificada.
*/
public static String simpl ( String x,int a , int b, int c )
{
	char d;
	String resp = "";
	for (int y = 0; y < x.length(); y = y + 1)
	{
		d = x.charAt(y);
		if ( d != ' ' && d != ',' )
		{
			if ( d == 'A' )
			{
				resp = resp + a;
			}//fim do if
			else if ( d == 'B' )
			{
				resp = resp + b;
			}//fim do if
			else if ( d == 'C' )
			{
				resp = resp + c;
			}//fim do if
			else
			{
				resp = resp + d;
			}//fim do else
		}//fim do if
	}//fim do for
	return (resp);
}//fim da simpl

/*
A funcao qual recebe a String aux como parametro, essa String nos dira o que esta sendo lido
se um not ou and ou or. Eles estao ao contrario pois minha leitura eh feita de tras para frente.
Ela retorna o respectivo valor.
*/
public static int qual ( String aux )
{
	int resp = 0;
	if ( iguais (aux,"ton"))
	{
		resp = 1;
	}//fim do if
	if ( iguais (aux,"dna"))
	{
		resp = 2;
	}//fim do if
	if ( iguais (aux,"ro"))
	{
		resp = 3;
	}//fim do if	
	return (resp);		
}//fim do qual

/*
A funcao not recebe a String var como parametro e troca o valor dentro dela.
*/
public static String not (String var)
{
	String resp = "";
	if ( iguais (var,"1"))
	{
		resp = "0";
	}//fim do if
	if ( iguais (var,"0"))
	{
		resp = "1";
	}//fim do if
	return (resp);
}//fim do not

/*
A funcao and recebe como parametro a String var e caso a String conter apenas 
o numeros 1 dentro dela eh retornado o valor 1. Ao contrario eh retornado 0.
*/
public static String and (String var)
{	
	String resp =  "0";	
	if ( iguais (var, "11"))
	{
		resp = "1";
	}//fim do if
	if (iguais (var,"111"))
	{
		resp = "1";
	}//fim do if
	return (resp);
}//fim da and

/*
A funcao or recebe a String var como parametro e caso ela contenha apenas
o numero 0 dentro dela eh retornado o valor 0. Ao contrario eh retornado 1.
*/
public static String or (String var)
{
	String resp =  "1";	
	if ( iguais (var, "00"))
	{
		resp = "0";
	}//fim do if
	if (iguais(var,"000"))
	{
		resp = "0";
	}//fim do if
	if (iguais(var,"0000"))
	{
		resp = "0";
	}//fim do if
	if (iguais(var,"00000"))
	{
		resp = "0";
	}//fim do if
	return (resp);
}//fim do or

/*
A funcao resol recebe quatro parametros. Ela eh quem vai resolver o problema.
O primeiro paramentro eh a String que sera sempre modificada, o segundo eh uma
String que devera ser encontrada dentro da primeira. Ja a terceira substitui a 
a segunda String por ela, isso dentro da primeira. O quarto parametro eh so para
saber quantas casa devo saltar na nova String, ele eh o tamanho do segundo parametro. 
Lembrando que tudo devera ser feito ao contrario pois estou indo do final da String
ate o inicio.
*/
public static String resol (String x, String h, String a, int tam )
{
	String resp = "";
	int teste = 0;
	int z;
	int y;
	char c;
	int cont = 0;
	int sub;	
	String base = "";	
	for (z = h.length()-1;z >=0;z=z-1)
	{
		c = h.charAt(z);
		base = base + c;
	}//fim do for
	
		
	for ( z = x.length()-1; z > 0; z = z - 1 )
	{		
		cont = cont + 1;
		for (y = 0; y < tam; y = y + 1)
		{				  
			c = x.charAt(z-y);
			resp = resp + c;			 
 		}//fim do for
		
		if ( iguais (resp,base) )
		{
			teste = z;
			z = 0;			
		}//fim do if
		resp = "";		
	}//fim do for
	resp = "";
	sub = teste - tam;
	for ( z = 0; z < x.length(); z = z + 1)
	{
		c = x.charAt(z);
		if ((sub+1) == z)
		{		
			z = z + (tam-1);
			resp = resp + a;
		}//fim do if
		else
		{
			resp = resp + c;
		}//fim do else
	}//fim do for
	return (resp);
	
}//fim do resol

/*
A funcao algebra eh a que une todas as outras funcoes.
Ela recebe a String que passou pela simpl. Dentro dessa funcao sao chamadas as outras
nisso vai formando um ciclo ate chegar na resposta, mas nada disso seria possivel
sem a funcao resol que literalmente resolve o exercicio.
O retorno desse funcao sempre sera 0 ou 1.
*/
public static String algebra ( String x )
{
	int tam;		
	int y;
	int z;
	boolean teste = false;
	String aux = "";
	String a =  "";
	String var =  "";	
	String b = "";
	String resp = "";
	char c;
	int d;
	char g;
	String h = "";
	String f = "";
	String e = "";
	for (y = 0; y < x.length();y =y+1)
	{
		c = x.charAt(y);
		resp = resp + c;
	}//fim do for
	
	for (y = resp.length() - 1; y >= 0; y = y - 1)
	{
		c = resp.charAt(y);
		if ( c == 'a' || c == 'n' || c == 'd' || c == 'o' || c == 't' || c == 'r')
		{									
			aux = aux + c;
			if (iguais (aux,"ton") || iguais (aux,"ro") || iguais (aux,"dna"))
			{
				teste = true;	
			}//fim do if 
			
		}//fim do if
		f = f + c;
		if (teste)
		{		
			d = qual (aux);			
			for (z = f.length()-1;z >=0; z = z-1)
			{
				g = f.charAt(z);
				e = e + g;
			}//fim do for
			for (z = 0;z < e.length() ; z = z +1)
			{
				g = e.charAt(z);
				if ( g== '0' || g == '1')
				{
					var = var + g;
				}//fim do if
				if ( g == ')' )
				{
					h = h + g;
					z = e.length();
				}//fim do if
				else
				{
					h = h + g;
				}//fim do else
			}//fim do for
									
			if ( d == 1 )
			{
				b = not(var);				
			}//fim do if	
			if ( d == 2 )
			{
				b = and(var);							
			}//fim do if
			if ( d == 3 )
			{
				b = or(var);								
			}//fim do if
						
			a = b;					
			tam = h.length();
			resp = resol ( resp, h , a , tam );
								
			aux = "";
			var = "";		
			e = "";
			teste = false;
			h = "";
			f = "";
			if ( y != 0 )
			{
				y = resp.length()-1 ;
			}//fim do if			 	
		}//fim do if
	}//fim do for
	
	return (resp);
}//fim da algebra

public static void main (String [] args)
{
	int quantos=1;
	int a;
	int b;
	int c;
	String x = "";
	String y = "";
	String resp = "";
	while (quantos != 0)
	{
		quantos = MyIO.readInt ();
		if (quantos != 0)
		{
			a = MyIO.readInt ();
			b = MyIO.readInt ();
			c = 0;	
			if (quantos == 3)
			{
				c = MyIO.readInt(); 
			}//fim do if
			x = MyIO.readLine ();
			y = simpl (x,a,b,c);
			resp = algebra (y);
			MyIO.println (resp);
		}//fim do if
	}//fim do while
}//fim da main

}//fim da algebra
