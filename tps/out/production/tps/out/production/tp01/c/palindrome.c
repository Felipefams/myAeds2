//code by Felipe Augusto Morais Silva
#include <stdlib.h>
#include <stdio.h>
#include <string.h>
#include <stdbool.h>

bool checkPalindrome(char* string, int length){
	for(int a = 0, b = length - 1; a < b; a++, b--)
		if(string[a] != string[b])
			return false;

	return true;
}

int main(void){
	char* string = malloc(1000);
	while(string != "FIM"){
		scanf("%[^\n]s", string);
		getchar();
		if(strlen(string) == 3 && (string[0] == 'F' && string[1] == 'I' && string[2] == 'M')){break;}
		if(checkPalindrome(string, strlen(string))){
			printf("SIM\n");
		}else{
			printf("NAO\n");
		}
	
		strcpy(string,"");
	}
	free(string);
	return (0);
}
