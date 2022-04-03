// code by Felipe Augusto Morais Silva
// libraries
#include <stdio.h>	 // standard input out put
#include <stdlib.h>	 // std functions
#include <string.h>	 // string type
#include <stdbool.h> // bool type
#include <assert.h>
#include <ctype.h>
// definitions
#define MAX 100
#define ll long long
#define sqr(a) a *a
#define each(item, array, length) (typeof(*(array)) *p = (array), (item) = *p; p < &((array)[length]); p++, (item) = *p)
#define len(x) strlen(x)
#define szc sizeof(char)
// helpful pre-made Structures
typedef struct Pairs
{
	int x;
	int y;
} Pair;
typedef Pair *ref_pair;
// left trim
char *ltrim(char *s)
{
	while (isspace(*s))
		s++;
	return s;
}
// right trim
char *rtrim(char *s)
{
	char *back = s + strlen(s);
	while (isspace(*--back))
		;
	*(back + 1) = '\0';
	return s;
}
// both sides trim
char *trim(char *s)
{
	return rtrim(ltrim(s));
}

char *strremove(char *str, const char *sub){
	char *p, *q, *r;
	if (*sub && (q = r = strstr(str, sub)) != NULL)
	{
		size_t len = strlen(sub);
		while ((r = strstr(p = r + len, sub)) != NULL)
		{
			memmove(q, p, r - p);
			q += r - p;
		}
		memmove(q, p, strlen(p) + 1);
	}
	return str;
}

void removeSpaces(char *s)
{
	char *d = s;
	do
		while (isspace(*s))
			s++;
	while (*d++ = *s++);
}

// actual code
typedef struct Filmes
{
	char *nome;
	char *tituloOriginal;
	char *dataLancamento;
	int duracao;
	char *genero;
	char *idiomaOriginal;
	char *situacao;
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
!Aloca Memoria
@char* tmp - string sem as tags
@int length - tamanho da string s
*/
char *removeTags(const char *s)
{
	const size_t length = len(s);
	if (!s || length < 1)
	{
		exit(EXIT_FAILURE);
	}
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
	/*
	exemplo de uso do remove tags
	char* teste = "<a><teste>Ahhhhhhhh</teste> mds</a>";
	teste = removeTags(teste, strlen(teste));
	*/
}

char *filterName(const char *s)
{
	const size_t length = len(s);
	char *tmp = (char *)calloc(length, sizeof(char));
	char *tmp2 = (char *)calloc(length, sizeof(char));
	tmp = removeTags(s);
	int count = 0;
	for (int i = 0; i < length; i++)
	{
		if (tmp[i] == '(')
		{
			break;
		}
		else
		{
			tmp2[count] = tmp[i];
			count++;
		}
	}
	free(tmp);
	return trim(tmp2);
}

char *filterDate(const char *s)
{
	const size_t length = len(s);
	char *tmp = (char *)calloc(length, sizeof(char));
	int count = 0;
	for (int i = 0; i < length; i++)
	{
		if (s[i] == '(')
		{
			break;
		}
		else
		{
			tmp[count] = s[i];
			count++;
		}
	}
	return trim(tmp);
}

char *remove_nbsp_amp(char *s)
{
	return strremove(strremove(s, "&nbsp;"), "&amp;");
}

char *filterGenre(const char *s)
{
	const size_t length = len(s);
	char *tmp = (char *)calloc(length, sizeof(char));
	char *tmp2 = (char *)calloc(length, sizeof(char));
	tmp = removeTags(s);
	int count = 0;
	for (int i = 0; i < length; i++)
	{
		tmp2[count] = tmp[i];
		count++;
	}
	free(tmp);
	return remove_nbsp_amp(trim(tmp2)); //, "&nbsp;");
}

int stringHoursToMinutes(const char *s)
{
	const size_t length = len(s);
	int arr[2]; //*arr = (int*) malloc(2*sizeof(int));
	for (int i = 0; i < length; i++)
	{
		if (s[i] == 'h')
		{
			arr[0] = (int)s[i - 1] - '0';
			char *tmp = (char *)calloc(length, sizeof(char));
			int count = 0;
			for (int k = i + 1; k < length; k++)
			{
				if (s[k] != ' ')
				{
					if (s[k] == 'm')
					{
						// isso aqui eh so pra evitar dar problema, pq deu muito problema no atoi
						removeSpaces(tmp);
						break;
					}
					else
					{
						tmp[count] = s[k];
						count++;
					}
				}
			}
			arr[1] = (int)atoi(tmp);
			free(tmp);
			break;
		}
	}
	int ans = (arr[0] * 60) + arr[1];
	return ans;
}

int filterRuntime(const char *s)
{
	const size_t length = len(s);
	char *tmp = (char *)calloc(length, szc);
	int count = 0;
	for (int i = 0; i < length; i++)
	{
		if (s[i] != ' ')
		{
			tmp[count] = s[i];
			count++;
		}
	}
	int ans = stringHoursToMinutes(tmp);
	free(tmp);
	return ans;
}

char *filterOriginalTitle(const char *s){
	const size_t length = len(s);
	char *tmp = (char *)calloc(length, szc);
	int c = 0; // control 2
	int k = 0; // control
	for (int i = 0; i < length; i++){
		if (s[i] == '>'){
			k++;
		}
		if (k == 3){
			for (int j = i + 2; j < length; j++){
				if (s[j] == '<'){
					break;
				}else{
					tmp[c] = s[j];
					c++; // saudades
				}
			}
			break;
		}
	}
	return tmp;
}

char * filterStrongBdiTag(const char *s){

	return s;
}

/*
 * recebe o nome do arquivo como parametro
 * abre ele e chama as outras funcoes auxiliares
 * pra tratar os casos
 */
void solve(char *filename)
{
	/*
	contains do C:
	if(strstr(suaString, substring) != NULL)
	*/
	/*
	vai ter que ler usando o fgets, e o fseek funciona pra otimizar.
	*/
	Filme filme;
	FILE *file = fopen(filename, "r");
	char *line = (char *)calloc(1000, sizeof(char));
	fgets(line, 1000 * sizeof(char), file);
	// nome
	while (!feof(file))
	{
		fgets(line, 1000 * sizeof(char), file);
		if (strstr(line, "<title>") != NULL)
		{
			filme.nome = filterName(line);
			break;
		}
		// printf("%s\n", line);
	}
	// data de lancamento
	while (!feof(file))
	{
		fgets(line, 1000 * sizeof(char), file);
		if (strstr(line, "class=\"release\"") != NULL)
		{
			fgets(line, 1000 * sizeof(char), file);
			filme.dataLancamento = filterDate(line);
			break;
		}
	}
	// genero
	while (!feof(file))
	{
		fgets(line, 1000 * sizeof(char), file);
		if (strstr(line, "class=\"genres\"") != NULL)
		{
			fgets(line, 1000 * sizeof(char), file);
			fgets(line, 1000 * sizeof(char), file);
			filme.genero = filterGenre(line);
			break;
		}
	}
	// duracao
	while (!feof(file))
	{
		fgets(line, 1000 * szc, file);
		if (strstr(line, "class=\"runtime\""))
		{
			fgets(line, 1000 * szc, file);
			fgets(line, 1000 * szc, file);
			filme.duracao = filterRuntime(line);
			break;
		}
	}
	bool b = false;
	while (!feof(file)){
		fgets(line, 1000 * szc, file);
		if (strstr(line, "ulo original")){
			filme.tituloOriginal = filterOriginalTitle(line);
			break;
		}else if(strstr(line, "<strong><bdi>Situ")){
			// filme.situacao = filterStrongBdiTag(line);
			b = true;
			break;
		}
	}
	// lembrar que existe a chance de nao ter orcamento nem palavras chaves
	printf("nome:%s\ndata:%s\ngenre:%s\nduracao:%d\ntitulo:%s\n", filme.nome, filme.dataLancamento, filme.genero, filme.duracao,
	filme.tituloOriginal);
	free(line);
	fclose(file);
}

// Driver Code
int main()
{
	// tem que trocar pra /tmp/filmes/ depois
	char *path = "filmes/";
	char *name = calloc(300, szc);
	while (name != "FIM")
	{
		scanf("%[^\n]s", name);
		char *filename = calloc(300, szc);
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
