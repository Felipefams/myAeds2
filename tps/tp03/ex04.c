// code by Felipe Augusto Morais Silva
// libraries
#include <stdio.h>	 // standard input out put
#include <stdlib.h>	 // std functions
#include <string.h>	 // string type
#include <stdbool.h> // bool type
#include <assert.h>
#include <ctype.h>
#include <time.h>
// definitions
#define MAX 100
#define ll long long
#define sqr(a) a *a
#define FOR_EACH(item, array)                        \
	for (int keep = 1,                               \
			 count = 0,                              \
			 size = sizeof(array) / sizeof *(array); \
		 keep && count != size;                      \
		 keep = !keep, count++)                      \
		for (item = (array) + count; keep; keep = !keep)
#define each(item, array, length) (typeof(*(array)) *p = (array), (item) = *p; p < &((array)[length]); p++, (item) = *p)
#define len(x) strlen(x)
#define szc sizeof(char)
#define szi sizeof(int)
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

char *strremove(char *str, const char *sub)
{
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
	// int size;
	char tmpStr[1000];
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
	char *tmp = (char *)calloc(length, szc);
	char *tmp2 = (char *)calloc(length, szc);
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

char *filterOriginalTitle(const char *s)
{
	const size_t length = len(s);
	char *tmp = (char *)calloc(length, szc);
	int c = 0; // control 2
	int k = 0; // control
	for (int i = 0; i < length; i++)
	{
		if (s[i] == '>')
		{
			k++;
		}
		if (k == 3)
		{
			for (int j = i + 2; j < length; j++)
			{
				if (s[j] == '<')
				{
					break;
				}
				else
				{
					tmp[c] = s[j];
					c++; // saudades
				}
			}
			break;
		}
	}
	return remove_nbsp_amp(tmp);
}

/*
filtra tudo que tiver as tags <strong> & <bdi>
a string como parametro ja tem que estar formatada
pelo trim
*/
char *filterStrongBdiTag(const char *s)
{
	const size_t length = len(s);
	char *tmp = (char *)calloc(length, szc);
	int count = 0;
	for (int i = length - 1; i > 0; i--)
	{
		// se nao for vazio, nesse caso, vai ser letra
		if (s[i] != ' ')
		{
			int j = i;
			while (s[j] != ' ')
			{
				if (s[j] == ' ')
				{
					break;
				}
				else
				{
					tmp[count] = s[j];
					j--, count++;
				}
			}
			break;
		}
	}
	return strrev(tmp);
}

float filterOrcamento(const char *s)
{
	char *tmp = (char *)calloc(len(s), szc);
	char *tmp2 = (char *)calloc(len(s), szc);
	tmp = filterStrongBdiTag(s);
	tmp = trim(tmp);
	int k = 0; // control
	if (tmp[0] == '-')
	{
		return 0.0;
	}
	for (int i = 0; i < len(tmp); i++)
	{
		// intervalo ascii dos digitos
		if (tmp[i] > 47 && tmp[i] < 58)
		{
			tmp2[k] = tmp[i];
			k++;
		}
	}
	free(tmp);
	return atol(tmp2) / 100;
}

/*
 * recebe o nome do arquivo como parametro
 * abre ele e chama as outras funcoes auxiliares
 * pra tratar os casos
 */
ref_filme solve(char *filename)
{
	/*
	contains do C:
	if(strstr(suaString, substring) != NULL)
	*/
	/*
	vai ter que ler usando o fgets, e o fseek funciona pra otimizar.
	*/
	ref_filme filme = malloc(1000 * sizeof(ref_filme));
	FILE *file = fopen(filename, "r");
	char *line = (char *)calloc(1000, sizeof(char));
	fgets(line, 1000 * sizeof(char), file);
	// nome
	while (!feof(file))
	{
		fgets(line, 1000 * sizeof(char), file);
		if (strstr(line, "<title>") != NULL)
		{
			filme->nome = filterName(line);
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
			filme->dataLancamento = filterDate(line);
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
			filme->genero = filterGenre(line);
			break;
		}
	}
	// duracao
	while (!feof(file))
	{
		fgets(line, 1000 * szc, file);
		if (strstr(line, "class=\"runtime\"") != NULL)
		{
			fgets(line, 1000 * szc, file);
			fgets(line, 1000 * szc, file);
			filme->duracao = filterRuntime(line);
			break;
		}
	}
	// titulo original
	bool b = false;
	while (!feof(file))
	{
		fgets(line, 1000 * szc, file);
		if (strstr(line, "ulo original") != NULL)
		{
			filme->tituloOriginal = filterOriginalTitle(line);
			break;
		}
		else if (strstr(line, "<strong><bdi>Situ") != NULL)
		{
			filme->tituloOriginal = filme->nome;
			filme->situacao = filterStrongBdiTag(trim(line));
			b = true;
			break;
		}
	}

	// situacao
	if (!b)
	{
		while (!feof(file))
		{
			fgets(line, 1000 * szc, file);
			if (strstr(line, "<strong><bdi>Situ") != NULL)
			{
				filme->situacao = filterStrongBdiTag(trim(line));
				break;
			}
		}
	}
	// idioma original
	while (!feof(file))
	{
		fgets(line, 1000 * szc, file);
		if (strstr(line, "Idioma") != NULL)
		{
			filme->idiomaOriginal = filterStrongBdiTag(trim(removeTags(line)));
			break;
		}
	}
	// orcamento
	while (!feof(file))
	{
		fgets(line, 1000 * szc, file);
		if (strstr(line, "mento") != NULL)
		{
			filme->orcamento = filterOrcamento(line);
			break;
		}
	}
	// filtro palavras chave)
	// char *tmpStr = calloc(1000, szc);
	while (!feof(file))
	{
		fgets(line, 1000 * szc, file);
		if (strstr(line, "keywords") != NULL)
		{
			bool ctrl = false;
			// int posPtr = ftell(file);
			// fpos_t posPtr;
			// fgetpos(file, &posPtr);
			while (true)
			{
				fgets(line, 1000 * szc, file);
				if (strstr(line, "<li>") != NULL)
				{
					char tmp[len(line)];
					// tmp = trim(removeTags(line));
					strcpy(tmp, trim(removeTags(line)));
					strcat(tmp, ", ");
					strcat(filme->tmpStr, tmp);
					ctrl = true;
				}
				else if (strstr(line, "</section>") != NULL)
				{
					if (!ctrl)
					{
						// filme->palavrasChave[0] = " ";
						strcpy(filme->tmpStr, "");
					}
					break;
				}
			}
			break;
		}
	}
	// lembrar que existe a chance de nao ter orcamento nem palavras chaves
	int size = len(filme->tmpStr);
	filme->tmpStr[size - 2] = '\0';
	/*
	printf("%s %s %s %d %s %s %s %g [%s]\n", filme->nome, filme->tituloOriginal,
		   filme->dataLancamento, filme->duracao, filme->genero, filme->idiomaOriginal, filme->situacao, filme->orcamento, tmpStr);
		   */

	free(line);
	return filme;
	fclose(file);
}

typedef struct
{
	ref_filme array[MAX];
	int countComparisons;
	int countMoves;
	int n;
} list;
typedef list *ref_list;

// comandos da "classe" lista
void inserir(ref_list x, ref_filme y, int pos)
{
	if (x->n >= MAX || pos < 0 || pos > x->n)
	{
		printf("ERRO ao inserir!");
		exit(1);
	}
	for (int i = x->n; i > pos; i--)
	{
		x->array[i] = x->array[i - 1];
	}
	x->array[pos] = y;
	x->n++;
}

void inserirInicio(ref_list x, ref_filme y)
{
	if (x->n >= MAX)
	{
		printf("ERRO ao inserir!");
		exit(1);
	}
	for (int i = x->n; i > 0; i--)
	{
		x->array[i] = x->array[i - 1];
	}
	x->array[0] = y;
	x->n++;
}

// desnecessario
// void start(ref_list x){
// 	x->n = 0;
// }

void inserirFim(ref_list x, ref_filme y)
{
	if (x->n >= MAX)
	{
		printf("Erro ao inserir!");
		exit(1);
	}
	x->array[x->n] = y;
	x->n++;
}

ref_filme removerInicio(ref_list x)
{
	if (x->n == 0)
	{
		printf("Erro ao remover!");
		exit(1);
	}
	ref_filme ans = x->array[0];
	x->n--;
	for (int i = 0; i < x->n; i++)
	{
		x->array[i] = x->array[i + 1];
	}
	return ans;
}

ref_filme removerFim(ref_list x)
{
	if (x->n == 0)
	{
		printf("Erro ao remover");
		exit(1);
	}
	// subtrai primeiro depois manda a resposta
	return x->array[--x->n];
}

ref_filme remover(ref_list x, int pos)
{
	if (x->n == 0 || pos < 0 || pos >= x->n)
	{
		printf("Erro ao remover!");
		exit(1);
	}
	ref_filme resp = x->array[pos];
	x->n--;
	// tava como int i = 0, por isso tava dando bosta
	for (int i = pos; i < x->n; i++)
	{
		x->array[i] = x->array[i + 1];
	}
	return resp;
}

/*funcionando pra ate 2 digitos*/
int filterAsterisk(char *s)
{
	int ans = 0;
	int count = 0;
	for (int i = 3; i < len(s); i++)
		if (isdigit(s[i]))
			count++;
	if (count == 1)
		return s[3] - '0';
	else
		ans = (s[3] - '0') * 10 + s[4] - '0';

	return ans;
}

void filterF_I(char *s)
{
	int pointer = 0;
	for (int i = 2; i < len(s); i++)
	{
		if (isupper(s[i]))
		{
			pointer = i;
			break;
		}
	}
	int count = 0;
	for (int i = pointer; i < len(s); i++)
	{
		s[count++] = s[i];
	}
	s[len(s) - pointer] = '\0';
}

void mostrar(ref_list x)
{
	for (int i = 0; i < x->n; i++)
	{
		// printf("%s\n", x->array[i]->nome);
		printf("%s %s %s %d %s %s %s %g [%s]\n", x->array[i]->nome,
			   x->array[i]->tituloOriginal,
			   x->array[i]->dataLancamento,
			   x->array[i]->duracao,
			   x->array[i]->genero,
			   x->array[i]->idiomaOriginal,
			   x->array[i]->situacao,
			   x->array[i]->orcamento,
			   x->array[i]->tmpStr);
	}
}

//working
void swap(ref_list filmeList, int a, int b){
	ref_filme tmp = filmeList->array[a];
	filmeList->array[a] = filmeList->array[b];
	filmeList->array[b] = tmp;
}

void shellsort(ref_list a, int n){
	for(int gap = n/2; gap > 0; gap /= 2){
		for(int i = gap; i < n; i++){
			ref_filme tmp = a->array[i];
			for(int j = i; j >= gap && strcmp(a->array[j-gap]->idiomaOriginal, tmp->idiomaOriginal) > 0; j = j - gap){
				a->array[j] = a->array[j - gap];
			}
		}
	}
}

void colorInsert(ref_list a, int c, int h, int n){
	for(int i = (h + c); i < n; i += h){
		ref_filme tmp = a->array[i];
		int j = i - h;
		while((j >= 0) && (a->array[j] > tmp)){
			a->countComparisons++;
			a->countMoves++;
			a->array[j+h] = a->array[j];
			j -= h;
		}
		a->countMoves++;
		a->array[j + h] = tmp;
	}
}

void ssort(ref_list a, int n){
	int h = 1;
	do 
	h = (h*3) + 1;
	while(h < n);
	
	do{
		h /= 3;
		for(int c = 0;  c < h; c++)
			colorInsert(a,c,h,n);
	}while(h != 1);
}


// Driver Code
int main()
{
	ref_list filmeList = (ref_list)malloc(1000 * sizeof(ref_list));
	// tem que trocar pra /tmp/filmes/ depois
	const char *path = "/tmp/filmes/";
	char *name = calloc(300, szc);
	while (name != "FIM")
	{
		scanf("%[^\n]s", name);
		char filename[300]; // = calloc(300, szc);
		strcpy(filename, path);
		strcat(filename, name);
		getchar();
		if (strlen(name) == 3 && (name[0] == 'F' && name[1] == 'I' && name[2] == 'M'))
		{
			break;
		}
		inserirFim(filmeList, solve(filename));
		// free(filename);
	}
	// mostrar(filmeList);
	FILE *tmp = fopen("748473_shellsort.txt", "w");
	clock_t start = clock();
	ssort(filmeList, filmeList->n);
	clock_t end = clock();
	float seconds = (float)(end - start) / CLOCKS_PER_SEC;
	mostrar(filmeList);
	fprintf(tmp, "%d\t%d\t%f\t%s", seconds,filmeList->countComparisons,
	filmeList->countMoves, "748473_Felipe_Augusto_Morais_Silva");
	fclose(tmp);
	free(filmeList);
	free(name);
	return 0;
}
