
#include <pthread.h>
#include <stdio.h>
#include <stdlib.h>
#include <assert.h>
#include <string.h>
#include <time.h>
#include <unistd.h>
#include "cond.c"

int pnum;  // number updated when producer runs.
int csum;  // sum computed using pnum when consumer runs.

int newPnum = 0; 			// condition to access mutex / signal variable

pthread_mutex_t mutex= PTHREAD_MUTEX_INITIALIZER;
pthread_cond_t consumerCond;
pthread_cond_t producerCond;

int (*pred)(int); // predicate indicating number to be consumed

int produceT() {
	pthread_mutex_lock(&mutex);
	while(newPnum){
		pthread_cond_wait(&producerCond, &mutex);
	}
	assert(!newPnum);
	scanf("%d",&pnum);
	newPnum = 1;
	pthread_cond_signal(&consumerCond);
	pthread_mutex_unlock(&mutex);
	return pnum;
}

void *Produce(void *a) {
  int p;

  p=1;
  while (p) {	
    printf("producer thinking...\n");
    sleep(1);
    printf("..done!\n");
    p = produceT();
    printf("PRODUCED %d\n",p);
  }
  printf("EXIT-P\n");
}


int consumeT() {
	pthread_mutex_lock(&mutex);
	while(!newPnum){
		pthread_cond_wait(&consumerCond, &mutex);
	}
	assert(newPnum);
	newPnum = 0;
	if ( pred(pnum) ) { csum += pnum; }
	pthread_cond_signal(&producerCond);
	pthread_mutex_unlock(&mutex);
	return pnum;
}

void *Consume(void *a) {
  int p;

  p=1;
  while (p) {
	
    printf("consumer thinking...\n");
    sleep(rand()%3);
    printf("..done!\n");
    p = consumeT();
    printf("CONSUMED %d\n",csum);
  }
  printf("EXIT-C\n");
}


int main (int argc, const char * argv[]) {
  // the current number predicate
  static pthread_t prod,cons;
	long rc;

  pred = &cond1;
  if (argc>1) {
    if      (!strncmp(argv[1],"2",10)) { pred = &cond2; }
    else if (!strncmp(argv[1],"3",10)) { pred = &cond3; }
  }


  pnum = 999;
  csum=0;
  srand(time(0));

  printf("Creating Producer:\n");
 	rc = pthread_create(&prod,NULL,Produce,(void *)0);
	if (rc) {
			printf("ERROR return code from pthread_create(prod): %ld\n",rc);
			exit(-1);
		}
  printf("Creating Consumer:\n");
 	rc = pthread_create(&cons,NULL,Consume,(void *)0);
	if (rc) {
			printf("ERROR return code from pthread_create(cons): %ld\n",rc);
			exit(-1);
		}

	pthread_join( prod, NULL);
	pthread_join( cons, NULL);


  printf("csum=%d.\n",csum);

  return 0;
}
