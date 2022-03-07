#include  <stdio.h>

int length ( char x[] )
{
	int tam = 0;
	int z = 0;
	 while (x[z] != '\0')          
  	 {
     		 tam = tam + 1;
    		  z = z + 1;
  	 }//fim do while
	return (tam);
}//fim da length


int iguais ( char s1[], char s2[])
{
	int resp = 1;
	int z;
	int tams1 = length (s1);
	int tams2 = length (s2);
	if ( tams1 == tams2 )
	{
		for ( z = 0 ;z < tams1 ; z = z + 1)
		{
			if ( s1[z] != s2[z] )
			{
				resp = 0;
				z = tams1;
			}//fim do if
		}//fim do for	
	}//fim do if
	else 
	{
		resp = 0;
	}//fim do else	
	return (resp);
}//fim do iguais
 
int main ()
{
   char x[1000] ;	
   char s2[] = "FIM";
	int resp= 1;
   int tam;
   scanf(" %[^\n]s",x);
	while (iguais (x,s2) == 0)
	{	
	tam = length (x);		
   int cont = tam - 1;

	for (int y = 0; y < tam/2 ; y = y + 1 )
	{
   	if (x[y] != x[cont])
		{
			resp = 0;
         y = tam/2;
		}//fim do if
		cont = cont - 1;
	}//fim do for	
   if (resp)
   {
      printf ("SIM\n");
   }//fim do if
   else
   {
      printf ("NAO\n");
   }//fim do else
		resp = 1;
		scanf(" %[^\n]s",x);
   	}//fim do while
   return (0);
   }//fim da main
