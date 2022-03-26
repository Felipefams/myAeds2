// code by Felipe Augusto Morais Silva
//libraries
#include <stdio.h>  // standard input out put
#include <stdlib.h> // std functions
#include <string.h> // string type
#include <stdbool.h>// bool type
#include <assert.h>
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
 
/*
	strrev does not exist for linux, so i just made my own 
*/
char* strrev(char* str){
	char *p1, *p2;
	if(! str || ! *str){
		return str;
	}
	for(p1 = str, p2 = str + strlen(str) - 1; p2 > p1; p1 = p1 + 1, p2 = p2 - 1){
		*p1 ^= *p2;
		*p2 ^= *p1;
		*p1 ^= *p2;
	}
	return str;
}

/*
returns a string with the content read
*/
char* read_from_eof(char* filename, int lines){	
	FILE* file = fopen(filename, "r");
	if(file == NULL){
		printf("Error!");
		exit(-1);
	}
	int readLines = 0;
	//set the pointer at the end of the file, so we can measure the size
	fseek(file, 0L, SEEK_END);
	long size = ftell(file); 
	char* string = calloc(size, sizeof(char));
	int countC = 0;
	for(int i = 0; i < size; i++){
		/*
		set the pointer at the current position - 1(it's a must, since the file works like an array and the end is actually end - 2)
		and the - i is decrementing the array	
		*/
		fseek(file, 0L, (SEEK_CUR - 1) - i);
		string[i] = (char) fgetc(file);
		//@countC - counts everytime a character is read 
		countC++;	
		if(string[i] == '\n'){
			//start of the current "word" in the line
			int start_of_word = i - countC + 1;
			//temporary char to safe de reverse of the current word 
			char* tmp = calloc(countC, sizeof(char));
			//save the reverse of the current word in the temp char 
			for(int k = 0; k < countC; k++){
				tmp[k] = string[i-k];
			}
			//reverse the current word 
			for(int k = 0; k < countC; k++){
				string[start_of_word + k] = tmp[k];
			}
			countC = 0;
			free(tmp);
			readLines = readLines + 1;
			//if all lines are read, break the loop
			if(readLines == lines + 1){
				break;
			}
		}
	}
	fclose(file);
	return strrev(string);
}

void write_to_file(char* filename, int lines){
	FILE* file = fopen(filename, "w");
	char* string = malloc(1000);
	while(lines >= 0){
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
	char* string = calloc(1000, sizeof(char));
	string = read_from_eof(filename, k);
	printf("%s", string);
	free(string);
	return 0;
}
