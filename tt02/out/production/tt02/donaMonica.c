//code by Felipe Augusto Morais Silva 
#include <stdlib.h>
#include <stdio.h>
#include <string.h>
#include <stdbool.h>
//obs: seria muito interessante se vcs pudessem deixar a gente usar um C++ limitado, sem orientacao a objetos. a stream docin e cout ajuda demais.Mas no fim do semestre eu deixo pra reclamar, vou trabalhar com o que tem sem reclamar. 
int solve(int monica, int f1, int f2){
	return monica - (f1+f2);
}
int main(void){
	int monica = 1;
	while(monica != 0){
		int f1 = 0; 
		int f2 = 0; 
		scanf("%d", &monica);
		if (monica != 0){
		//	getchar();
			scanf("%d", &f1);
		//	getchar();
			scanf("%d", &f2);
			printf("%d\n", solve(monica,f1,f2));
		}
	}
	return (0);
}
