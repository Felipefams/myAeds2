// code by Felipe Augusto Morais Silva
//libraries
#include <stdio.h>  // standard input out put
#include <stdlib.h> // std functions
#include <string.h> // string type
#include <stdbool.h>// bool type
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
// actual code

bool checkConsonants(char* string, int length)
{
    for (int i = 0; i < length; i = i + 1)
    {
        if((string[i] >= 'a' && string[i] <= 'z') || (string[i] >= 'A' && string[i] <= 'Z')){
        if (string[i] == 'a' || string[i] == 'e' || string[i] == 'i' || string[i] == 'o' || string[i] == 'u'
		|| string[i] == 'A' || string [i] == 'E' || string[i] == 'I' || string[i] == 'O' || string [i] == 'U')
        {
		return false;
        }
        }
        else{return false;}
    }
    return true;
}

bool checkVowels(char* string, int length)
{
    for (int i = 0; i < length; i = i + 1)
    {
        if ((string[i] != 'a' && string[i] != 'e' && string[i] != 'i' && string[i] != 'o' && string[i] != 'u')
		&& (string[i] != 'A' && string [i] != 'E' && string[i] != 'I' && string[i] != 'O' && string [i] != 'U'))
        {
		    return false;
        }
    }
    return true;
}

bool checkInteger(char* string, int length){
    for(int i = 0; i < length; i = i + 1){
        if(string[i] < '0' || string[i] > '9'){
            return false;
        }
    }
    return true;
}

bool checkDouble(char* string, int length){
    int countComma = 0;
    for(int i = 0; i < length; i = i + 1){
        if(string[i] == ',' || string[i] == '.'){countComma++;}
        if(countComma > 1){return false;}
        if((string[i] < '0' || string[i] > '9') && (string[i] != ',' && string[i] != '.')){
            return false;
        }
    }
    return true;
}

int main(void){
	char* string = malloc(1000);
	while(string != "FIM"){
		scanf("%[^\n]s", string);
		getchar();
		if(strlen(string) == 3 && (string[0] == 'F' && string[1] == 'I' && string[2] == 'M')){break;}
		printf(checkVowels(string, strlen(string))?"SIM ": "NAO ");
        printf(checkConsonants(string, strlen(string))?"SIM ": "NAO ");
        printf(checkInteger(string, strlen(string))?"SIM ": "NAO ");
        printf(checkDouble(string, strlen(string))?"SIM\n": "NAO\n");
	}
	free(string);
	return (0);
}
