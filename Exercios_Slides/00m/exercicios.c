#include <stdio.h>
#include <math.h>
#include <stdlib.h>
#define max INT_MAX
#define ll long long

void smallT(int n){
	
}

void solve(char* filename, int n){
	FILE* file = fopen(filename,"wr");
	int * array = (int*) malloc(n*sizeof(int));
	for(int i = 0; i < n; i = i + 1){
		scanf("%d",&array[n]);
	}
	
	if( file != NULL){
		printf("\nfile succesfully opened");
		for(int i = 0; i < n; i = i + 1){
			fwrite(&array,sizeof(int),sizeof(&array),file);
		}
	}else{
		printf ("\nUnable to open file");
	}
	free(array);
	fclose(file);
}

int main(){
	char* s = (char*) malloc(20*sizeof(char));
	int n = 0; 
	printf("\ninsert N");
	scanf("%d", &n);
	printf("\ninsert filename:");
	//eu tinha esquecido que pro %s nao precisa do & no nome da variavel, ai tava dando segmentation fault sem parar
	scanf("%s", s);
	solve(s,n);
	free(s);
	return (0);
}
