/*====================================
File name: exerc_3_5.c (or cpp) Date: 2014‐01‐30
Group Number: 3
Members that contributed:
        John Burchell,
        Patrik Bäckström,
        William Granli
Demonstration code: [<Ass code 1‐4> <abc>]
======================================*/


#include "exerc_3_5.h"

void printfileAlabetic(){

	PERSON *temp = malloc(sizeof(PERSON));
	FILE *file = fopen(FILENAME, "r");
	PERSON people[100];
	int count = 0, i = 0;

	while(fread(temp, sizeof(PERSON), 1, file)){
		people[count] = *temp;
		count++;
	}

	if(count > 0){

		sort_alpha(people, count-1, 0);

		for(i = 0; i < count; i++){
			printStruct(people[i]);
		}
	}

	free(temp);
	fclose(file);
}
void printStruct(PERSON toPrint){

	printf("Name: %s\n", toPrint.firstName);
	printf("Family Name: %s\n", toPrint.familyName);
	printf("Personal Number: %s\n", toPrint.pers_number);
}

void sort_alpha(PERSON *list, int count, int number){

	int i;
	PERSON *temp = malloc(sizeof(PERSON));
	PERSON *min = malloc(sizeof(PERSON));
	*min = list[number];

	if(number == count){
		free(temp);
		free(min);
		return;
	}

	for(i = number; i < count + 1; i++){
		if(toupper(min->firstName[0]) > toupper(list[i].firstName[0])){
			*min = list[i];
		}
		if(toupper(list[number].firstName[0]) > toupper(min->firstName[0])){
			*temp = list[number];
			list[number] = *min;
			list[i] = *temp;
		}
	}
	
	free(temp);
	free(min);
	sort_alpha(list, count, number+1);
}