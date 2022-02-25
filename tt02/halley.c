//code by Felipe Augusto Morais Silva
#include <stdlib.h>
#include <stdio.h>
#include <stdbool.h>
#include <string.h>

typedef struct Pairs{
	int year;
	int yearsLeft;
}Pair;
typedef Pair *ref_pair; 

ref_pair halleyDates(int yearToStart, const int lastHalley){
	ref_pair array = (ref_pair) malloc((980) * sizeof(ref_pair));
	int count = 76 - (yearToStart - lastHalley);
	int countArray = 0;
	for(int i = yearToStart; i <= 3000; i = i + 1){
		Pair myPair;
		myPair.year = i;
		myPair.yearsLeft = count; 
		if(count == 0){
			myPair.yearsLeft = 76;
			count = 76;
		}
		array[countArray] = myPair;
		countArray = countArray + 1;
		count = count - 1;
	}
	return array; 
}

int main(void){
	const int yearToStart = 2020; 
	const int lastHalley = 1986;
	int date = 1;
	ref_pair dateArray = halleyDates(yearToStart, lastHalley); 
	while(date != 0){
		scanf("%d", &date);
		if(date != 0){
			//date + 1 pra evitar checar if dateArray[i].year > date
			for(int i = 0; i <= 980; i = i + 1){
				if(dateArray[i].year > date && dateArray[i].yearsLeft == 76){
					printf("%d\n", dateArray[i].year);
					break;		
				}
			}
		}
	}
	free(dateArray);
	return (0);
}
