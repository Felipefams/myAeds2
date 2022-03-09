// code by Felipe Augusto Morais Silva
//libraries
#include <stdio.h>  // standard input out put
#include <math.h>   // math functions
#include <stdlib.h> // std functions
#include <string.h> // string type
#include <stdbool.h>// bool type
#include <ctype.h>  // text formatting
//definitions
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
//essa questao minha ta errada, tenho que concertar depois
bool isPalindrome(char* s, int length){
	if(length == 0 || length == 1){
		return true;
	}
	if(s[0] == s[length -1]){
		char* newS = malloc(length*sizeof(char));
		for(int i = 1, count = 0; i < length - 1; i++, count++){
			newS[count] = s[i];
		}
		return isPalindrome(newS, strlen(newS));		
	}	
	return false;
}

int main(void){
	char* string = malloc(1000);
	while(string != "FIM"){
		scanf("%[^\n]s", string);
		getchar();
		if(strlen(string) == 3 && (string[0] == 'F' && string[1] == 'I' && string[2] == 'M')){break;}
		if(isPalindrome(string, strlen(string))){
			printf("SIM\n");
		}else{
			printf("NAO\n");
		}
	
		strcpy(string,"");
	}
	free(string);
	return (0);
}
