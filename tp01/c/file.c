// code by Felipe Augusto Morais Silva
//libraries
#include <stdio.h>  // standard input out put
#include <stdlib.h> // std functions
#include <string.h> // string type
#include <stdbool.h>// bool type
//definitions
#define MAX 100
#define ll long long
#define sqr(a) a*a
#define each(item, array, length) (typeof(*(array)) *p = (array), (item) = *p; p < &((array)[length]); p++, (item) = *p)
//helpful pre-made Structures
typedef struct Pairs{
	int x;
	int y;
}Pair;
typedef Pair *ref_pair;
//actual code
 
//code to mirror the string we received
char* mirror_String(char* string){
	
	return string;
}

void read_from_file(char* filename, int lines){	
	FILE* file = fopen(filename, "r");
	if(file == NULL){
		printf("Error!");
		exit(-1);
	}
	int readLines = 0;
	fseek(file, 0L, SEEK_END);
	long size = ftell(file); 
	char* string = malloc(size*sizeof(char));
	for(int i = 0; i < size; i++){
		fseek(file, 0L, (SEEK_END - 1) - i);
		string[i] = (char) fgetc(file);
		if(string[i] == '\n'){
			readLines = readLines + 1;
			if(readLines == lines){
				break;
			}
		}
	}
	printf("%s\n", string);
	free(string);
	fclose(file);
}

void write_to_file(char* filename, int lines){
	FILE* file = fopen(filename, "w");
	char* string = malloc(1000);
	while(lines > 0){
		scanf("%[^\n]s", string);
		getchar();
		fprintf(file, "%s\n", string);
		lines = lines - 1;
	}	
	free(string);
	fclose(file);	
}

// Driver Code
int main()
{
	int k = 0;
	char* filename = "file.txt";
	scanf("%d", &k);
	write_to_file(filename, k);
	read_from_file(filename, k);
	return 0;
}
