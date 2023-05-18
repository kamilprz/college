/* Number of philosophers, and forks! */
#define NUM 5 

/* We want to record which philosopher is holding which fork */
/*  fork[NUM][NUM] where fork[p][f] is true if 'p' holds 'f' */

bool Fork[NUM*NUM]; /* 2-d arrays not supported, so ... */

#define FORK(p,f) Fork[NUM*p+f]

#define leftFork(p) (p%NUM)

#define rightFork(p) ((p+1)%NUM)

#define myForkOnly(p,f) !(    FORK(((p+1)%NUM),f) \
                           || FORK(((p+2)%NUM),f) \
                           || FORK(((p+3)%NUM),f) \
                           || FORK(((p+4)%NUM),f) )


bool hasEaten, hasThought;
						   
ltl {always hasThought -> eventually (hasEaten)};
						   
active [NUM] proctype phil()
{ int p, lfork, rfork ;
  p = _pid;
  lfork = leftFork(p);
  rfork = rightFork(p);

  think: 
  
  printf("P%d thinks..\n",_pid);
  hasThought = true;
	atomic{
		if
		:: myForkOnly(p, lfork) -> firstfork: FORK(p,lfork) = true;
		fi;	
	}
	
	atomic{
		if
		:: myForkOnly(p, rfork) -> secondfork: FORK(p,rfork) = true;
		fi;	
	}
  
  assert(myForkOnly(p,lfork));
  assert(myForkOnly(p,rfork));
  
  progress_eat: printf("P%d eats!\n",_pid);
  hasEaten = true;
  dropfork1: FORK(p,lfork) = false;
  dropfork2: FORK(p,rfork) = false;
  hasEaten = false;
  hasThought = false;
  goto think
}

