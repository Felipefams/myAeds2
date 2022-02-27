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
//(comments in portuguese starting from here)
bool checkVowels(char *string, int length)
{
    for (int i = 0; i < length; i = i + 1)
    {
	tolower(string[i]);
        if (string[i] != 'a' || string[i] != 'e' || string[i] != 'i' || string[i] != 'o' || string[i] != 'u')
        {
		return false;	
        }
    }
    return true;
}
bool checkConsonants(char *string, int length)
{
    for (int i = 0; i < length; i = i + 1)
    {
	tolower(string[i]);
        if (string[i] == 'a' || string[i] == 'e' || string[i] == 'i' || string[i] == 'o' || string[i] == 'u')
        {
		return false;	
        }
    }
    return true;
}

int main(void){
	return(0);
}
