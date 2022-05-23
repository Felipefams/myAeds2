#include <stdlib.h>
#include <stdio.h>

int main(void){
//	int *p;
	int v[] = {10,7,2,6,3};
//	p = v;
//	printf("1- %d\n", *(v+3));
	int a = 5, *b, c = 10;
	b = &a;
//	scanf("%d", b);
//	printf("2- a-%d b-%d c-%d\n", a,b,c);
	int x = 1; int y = 2;
	int *p = &x;
	int *q = &y;
	y = (*p)+++*q;
	printf("3-%d %d",x,y);
	return (0);
}
