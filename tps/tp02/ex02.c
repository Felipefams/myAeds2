// code by Felipe Augusto Morais Silva
// libraries
#include <stdio.h>	 // standard input out put
#include <stdlib.h>	 // std functions
#include <string.h>	 // string type
#include <stdbool.h> // bool type
#include <assert.h>
// definitions
#define MAX 100
#define ll long long
#define sqr(a) a *a
#define each(item, array, length) (typeof(*(array)) *p = (array), (item) = *p; p < &((array)[length]); p++, (item) = *p)
// helpful pre-made Structures
typedef struct Pairs
{
	int x;
	int y;
} Pair;
typedef Pair *ref_pair;
// actual code
typedef struct Filmes
{
	char *nome;
	char *tituloOriginal;
	char *dataLancamento;
	int duracao;
	char *genero;
	char *idiomaOriginal;
	char *stuacao;
	float orcamento;
	char **palavrasChave;
} Filme;
typedef Filme *ref_filme;
/*
	strrev does not exist for linux, so i just made my own
*/
char *strrev(char *str)
{
	char *p1, *p2;
	if (!str || !*str)
	{
		return str;
	}
	for (p1 = str, p2 = str + strlen(str) - 1; p2 > p1; p1 = p1 + 1, p2 = p2 - 1)
	{
		*p1 ^= *p2;
		*p2 ^= *p1;
		*p1 ^= *p2;
	}
	return str;
}

/*
remove as tags html
@_alloc - aloca memoria, cuidado.
@char* tmp - string sem as tags
@int length - tamanho da string s
*/
char *removeTags_alloc(char *s, int length)
{
	char *tmp = calloc(length, sizeof(char));
	int count = 0;
	for (int i = 0; i < length; i++)
	{
		if (s[i] == '<')
		{
			for (int k = i; k < length; k++)
			{
				if (s[k] == '>')
				{
					i = k;
					break;
				}
			}
		}
		else if (s[i] != '>' && s[i] != '<')
		{
			tmp[count] = s[i];
			count++;
		}
	}
	return tmp;
}

/*
 * recebe o nome do arquivo como parametro
 * abre ele e chama as outras funcoes auxiliares
 * pra tratar os casos
 */
void solve(char *filename)
{
	FILE *file = fopen(filename, "r");
	char *teste = "<a><teste>Ahhhhhhhh</teste> mds</a>";
	teste = removeTags_alloc(teste, strlen(teste));
	printf("%s\n", teste);
	free(teste);
	fclose(file);
}

// Driver Code
int main()
{
	// tem que trocar pra /tmp/filmes/ depois
	char *path = "filmes/";
	char *name = calloc(300, sizeof(char));
	while (name != "FIM")
	{
		scanf("%[^\n]s", name);
		char *filename = calloc(300, sizeof(char));
		strcpy(filename, path);
		strcat(filename, name);
		getchar();
		if (strlen(name) == 3 && (name[0] == 'F' && name[1] == 'I' && name[2] == 'M'))
		{
			break;
		}
		solve(filename);
		free(filename);
	}
	free(name);
	return 0;
}
