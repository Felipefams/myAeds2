#include <stdlib.h>
#include <stdio.h>
#include <string.h>

//#include <felipe.h>
//acabei fazend os 2 em arquivos separados
int supMethod(char *string, int length){
	//ideia do pdf (muito boa ideia por sinal)
	//sei como eu nunca tinha pensado nisso, fica bem mais "elegante" mesmo
	//so nao sei se eh uma boa ideia fazer isso em C
	//inclusive, deu aquele warning de implicit function declaration. 
	return r_countUpperCase(string, length, -1);
}
int r_countUpperCase(char *string, int length, int i){
	int count = (string[i] >= 'A' && string [i] <= 'Z')?1 : 0;
	if(length - 1 > i){
		count = count + r_countUpperCase(string,length,i+1);
	}
	return count;
}
int main(void){
	char* string = malloc(1000);
	//input porco, quase que desisti e fiz em c++ mesmo. Esse negocio de terminar o input com FIM n entra na minha mente de jeito nenhum. 
	while(string != "FIM"){
		scanf("%[^\n]s", string);
		getchar();
		if(strlen(string) == 3 && (string[0] == 'F' && string[1] == 'I' && string[2] == 'M')){break;}
		int counter = supMethod(string, strlen(string));	
		printf("%d\n", counter);
		strcpy(string,"");
	}
	free(string);
	return (0);
}
