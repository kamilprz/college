
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

// boolean to determine whether whatever the producer produced has been consumed by consumer
int hasBeenConsumed = 1;

pthread_mutex_t mutex = PTHREAD_MUTEX_INITIALIZER;	// mutex
pthread_cond_t changedPnum = PTHREAD_COND_INITIALIZER;	


int (*pred)(int); // predicate indicating number to be consumed

int produceT() {
  pthread_mutex_lock(&mutex);
  // loop until whatever is produced has been consumed
  while(!hasBeenConsumed){
    pthread_cond_wait(&changedPnum, &mutex);
  }
  scanf("%d",&pnum);
  hasBeenConsumed = 0;			//	reset boolean as new value produced
  pthread_mutex_unlock(&mutex);
  pthread_cond_signal(&changedPnum);
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
  pthread_exit(NULL);
}


int consumeT() {
  pthread_mutex_lock(&mutex);
  // loop until producer produces something which hasn't been consumed yet
  while (hasBeenConsumed)
  {
    pthread_cond_wait(&changedPnum, &mutex);
  }
  if ( pred(pnum) ) { csum += pnum; }
  // now consumed, so update boolean accordingly
  hasBeenConsumed = 1;
  pthread_mutex_unlock(&mutex);
  pthread_cond_signal(&changedPnum);
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
  pthread_exit(NULL);
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

  pthread_mutex_destroy(&mutex);
  pthread_cond_destroy(&changedPnum);

  printf("csum=%d.\n",csum);

  return 0;
}