#include <stdio.h>
#include <stdlib.h>
#define null NULL
typedef struct Node{
	int data;
	struct Node* next;
}Node;
typedef Node* ref_node;
ref_node head;

int getSize(){
	int count = 0;
	for(ref_node i = head; i != null; i = i->next) count++;
	return count;
}

void append(int data){
	ref_node tmp = (ref_node) malloc(sizeof(Node));
	tmp->data = data;
	if(head == null)
		head = tmp;
	else{
		ref_node i = head;
		while(i != null){
			if(i->next == null){
				i->next = tmp;
				break;
			}
			i = i->next;
		}
	}
}

void insertBeginning(int data){
	ref_node tmp = (ref_node) malloc(sizeof(Node));
	tmp->data = data;
	if(head == null) head = tmp;
	tmp->next = head;
	head = tmp;
}

void insert(int data, int pos){
	ref_node newNode = (ref_node) malloc(sizeof(Node));
	newNode->data = data;
	if(pos == 0) insertBeginning(data);	
	else if (pos == getSize()) append(data);
	else if (pos > getSize()) printf("Error, invalid position\n");
	else{
		ref_node tmp = head;
		for(int i = 1; i < pos; ++i){
			tmp = tmp->next;
		}
		newNode->next = tmp->next;
		tmp->next = newNode;
	}
}

void removeFromEnd(){
	if(head == null) return;
	ref_node tmp = head;
	while(tmp->next->next != null){
		tmp = tmp->next;
	}
	free(tmp->next);
	tmp->next = null;
}

void removeFromBeginning(){
	if(head == null) return;
	ref_node tmp = head;
	tmp = head->next;
	head->next = null;
	head = tmp;
}

void removeAt(int pos){
	if(head == null) return;
	else if(pos == 0) removeFromBeginning();
	else if(pos == getSize()) removeFromEnd();
	else{
		ref_node tmp = head;
		for(int i = 1; i < pos; ++i){
			tmp = tmp->next;
		}	
		ref_node tmp2 = (ref_node) malloc(sizeof(Node));
		tmp2->next = tmp->next;
		tmp->next = tmp->next->next;
		tmp2->next->next = null;
		free(tmp2->next);
		free(tmp2);	
	}
}

ref_node reverse(ref_node i){
	if(i == null || i->next == null){
		head = i;
		return i;
	}
	ref_node tmp = reverse(i->next);
	i->next->next = i;
	i->next = null;
	return tmp;
}

void print(){
	printf("list elements:\n");
	for(ref_node i = head; i != null; i = i->next)
		printf("[%d]--->", i->data);
	printf("\n");
}

int main(void){
	head = null;
	printf("How many numbers to insert: ");
	int n, k;
	scanf("%d", &n);
	print("\nAppend:");
	for(int i = 0; i < n; ++i){
		printf("\nType a number: ");
		scanf("%d", &k);
		append(k);
		print();
	}
	printf("\nReversed: ");
	head = reverse(head);
	print();
	//testbench:
	/*
	printf("\n insert on first position: ");
	for(int i = 0; i < n; ++i){
		printf("\nType a number: ");
		scanf("%d", &k);
		insertBeginning(k);
		print();
	}
	printf("\n insert on third position: ");
	for(int i = 0; i < n; ++i){
		printf("\nType a number: ");
		scanf("%d", &k);
		insert(k,3);
		print();
	}
	printf("\nremove last element: ");
	removeFromEnd();
	print();
	printf("\nremove first element: ");
	removeFromBeginning();
	print();
	printf("\nremove element at third position: ");
	removeAt(3);
	print();
	*/
	return 0;
}
