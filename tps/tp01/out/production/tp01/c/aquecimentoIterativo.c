#include <stdlib.h>
#include <stdio.h>
#include <string.h>

//#include <felipe.h>
//vou fazer o recursivo e o iterativo no mesmo arquivo, pra evitar a fadiga. 
int countUpperCase(char *string, int length){
	int count = 0;
	for(int i = 0; i < length; i = i + 1){
		if(string[i] >= 'A' && string[i] <= 'Z'){
			count = count + 1;
		}
	}

	return (count);
}
int main(void){
	char* string = malloc(1000);
	//input porco, quase que desisti e fiz em c++ mesmo. Esse negocio de terminar o input com FIM n entra na minha mente de jeito nenhum. 
	while(string != "FIM"){
		scanf("%[^\n]s", string);
		getchar();
		if(strlen(string) == 3 && (string[0] == 'F' && string[1] == 'I' && string[2] == 'M')){break;}
		int counter = countUpperCase(string,strlen(string));
		printf("%d\n", counter);
		strcpy(string,"");
	}
	free(string);
	return (0);
}
