/*====================================
File name: exerc_3_3.c (or cpp) Date: 2014‐01‐30
Group Number: 3
Members that contributed:
	Patrik Bäckström,
	John Burchell,
	William Granli
Demonstration code: [<Ass code 1‐4> <abc>]
======================================*/

#include <stdio.h>
#include <stdlib.h>
#include <time.h>

#define MAX 5

typedef struct q {
	int number;
	struct q *next;
	struct q *prev;
} REGTYPE;

REGTYPE* randomize(void);
REGTYPE* add_first(REGTYPE* temp, int data);

int main(int argc, char *argv[]) {
	
	int n = 0;
	REGTYPE *currentPos, *head = NULL;
	
	srand(time(0)); // Random seed
	
	head = randomize();
	currentPos = head;


	while(currentPos != NULL) {
		printf("\nList index %d: %d", n++, currentPos->number);
		currentPos=currentPos->next;
	}

	while((currentPos = head) != NULL) {
		head = currentPos->next;
		free(currentPos);
	}

	return 0;
}

REGTYPE* randomize(void) {

	REGTYPE *currentPos, *currentPrev, *first, *prevTemp;

	first = add_first(malloc(sizeof(REGTYPE)),(rand() % 100));
	currentPos = first;
	currentPrev = first;
	prevTemp = NULL;

	int i = 0;
	for (i = 0; i < MAX; i++) {
		currentPos->next = malloc(sizeof(REGTYPE));
		prevTemp = currentPos->next;

		printf("--- Index %i\n", i);
		printf("This: %p\n", currentPos);
		printf("Num: %i (random)\n", currentPos->number);
		printf("Next: %p\n", currentPos->next);
		printf("Prev: %p\n", currentPos->prev);
		
		currentPos = currentPos->next;
		currentPos->number = rand() % 100 + 1;
		currentPos->prev = currentPrev;
		currentPrev = prevTemp;

		

	}
	return first;
}


REGTYPE* add_first(REGTYPE* temp, int data) {
	
	temp->number = data;
	temp->prev = NULL;

	return temp;
}