#include <stdio.h>
#include <stdlib.h>
#include <ctype.h>
#include <string.h>
#include <math.h>

struct node{
  double data;
  struct node* next;
};

struct node* top;

void initialize(struct node* top){
  top=NULL;
}

void push(double value){
  struct node* tmp;
  tmp=malloc(sizeof(struct node));
  tmp -> data = value;
  tmp -> next = top;
  top = tmp;
}

double pop(){
  struct node* tmp;
  double data;
  tmp = top;
  data = tmp -> data;
  top = top -> next;
  free(tmp);
  return data;
}


struct node2{
  char* data;
  struct node2* next;
};

typedef struct node2 node2;
node2* top2;

void initialize2(struct node2* top2){
  top2=NULL;
}

void pushChar(char* value){
  node2* tmp;
  tmp=malloc(sizeof(node2));
  tmp -> data = value;
  tmp -> next = top2;
  top2 = tmp;
}

char* popChar(){
  if(top2!=NULL){
      node2* tmp;
      char* data;
      tmp = top2;
      data = tmp -> data;
      top2 = top2 -> next;
      free(tmp);
      return data;
  }else{
    return NULL;
  }
}

char* topOfCharStack(){
  if(top2!=NULL){
      node2* tmp;
      char* data;
      tmp = top2;
      data = tmp -> data;
      return data;
  }else{
    return NULL;
  }
}


char* infix();
double postfix(char array[]);
int precedence(char* topOfStack, char* token);


int main(int argc, char ** argv){
    const int maxSize=90;
    char* filename;
    char resultsFileName[255];
    char* array;
    double result=0;
    char infixArray[maxSize];
    initialize(top);
    initialize2(top2);

    if ( argc == 1 ) {
        printf("Error: No input filename provided\n");
        printf("Usage: %s <input filename>\n", argv[0]);
        exit(1);
    }
    else if ( argc > 2 ) {
        printf("Error: Too many command line parameters\n");
        printf("Usage: %s <input filename>\n", argv[0]);
        exit(1);
    }
    else {
        filename = argv[1];
    }

    FILE* file;
    FILE* resultsFile;
    strcpy(&resultsFileName[0],filename);
    strcat(&resultsFileName[0],".results");
    resultsFile=fopen(&resultsFileName[0], "w");
    file = fopen(filename, "r");

    if (file==NULL) {
        printf("Error: File %s not found\n", filename);
        printf("Usage: %s <input filename>\n", argv[0]);
        exit(1);
    }
    while(fgets(infixArray,maxSize,file)!=NULL){
        printf("%s",infixArray);
        fprintf(resultsFile, "%s",infixArray);
        if (strncmp(&infixArray[0], "i", 1)==0) {
            array = infix(infixArray);
        }
        else if (strncmp(&infixArray[0], "p", 1)==0) {
            array = infixArray;
        }
        result = postfix(array);
        fprintf(resultsFile,"%f\n",result);
    }
    fclose(file);
    fclose(resultsFile);
    return 0;
}

//calculates a solution for RPN expressions
double postfix(char array[]){
    double p=0,q=0,result=0,doubleToken=0;
	const char delimeter[2]=" ";
	char* token;
	token=strtok(array,delimeter);
	while(token!=NULL){
        if (strncmp(token, "postfix", 7)!=0 && strncmp(token, "infix", 5)!=0) {
            if (strncmp(token, "0", 1)!=0) {
                //  not zero number
                doubleToken=atof(token);
                if(doubleToken!=0){
                    push(doubleToken);
                }
                else if (doubleToken == 0) {
                    // means token is operator
                    p=pop();
                    q=pop();
                    // check which operator now
                        if(strncmp(token, "+", 1)==0){
                        result=q+p;
                        }
                        else if(strncmp(token, "-", 1)==0){
                        result=q-p;
                        }
                        else if(strncmp(token, "X", 1)==0){
                        result=q*p;
                        }
                        else if(strncmp(token, "/", 1)==0){
                        result=q/p;
                        }
                        else if(strncmp(token, "^", 1)==0){
                        result=pow(q,p);
                        }
                    push(result);
                }
            }
            else{
                // zero passed as number
                doubleToken = 0;
                push(doubleToken);
            }
        }
        token=strtok(NULL,delimeter);
	}
	printf("result = %g\n",result);
	return(result);
}



char* infix(char infixArray[]){
    static char postfixArray[90];
    memset(postfixArray,0,sizeof(postfixArray));
    double doubleToken;
    char* tmp;
    char* token;
	const char delimeter[2]=" ";
	token=strtok(infixArray,delimeter);

	while(token!=NULL){
        if (strncmp(token, "postfix", 7)!=0 && strncmp(token, "infix", 5)!=0) {
            if (strncmp(token, "(", 1)==0){
                  pushChar(token);
                  tmp = topOfCharStack();
                }
            else if (strncmp(token, ")", 1)==0){
                while (strncmp(topOfCharStack(), "(", 1)!=0) {
                //pop operator from stack and append to output string
                    tmp=popChar();
                    strcat(postfixArray, tmp );
                    strcat(postfixArray,delimeter);
                }
               //    pop left bracket
                popChar();
            }

            else if (strncmp(token, "0", 1)==0) {
            //  zero number
                // append token to the output string
                strcat(postfixArray,token);
                strcat(postfixArray,delimeter);
            }

            else if(strncmp(token, "0", 1)!=0){
                doubleToken=atof(token);
                if(doubleToken!=0){
                // not zero number
                    // append token number to the output string
                    strcat(postfixArray,token);
                    strcat(postfixArray,delimeter);
                }
                 else{
                    // token is an operator

                    //     while ( there is operator on top of stack with higher precedence ) {
                    while ( precedence(topOfCharStack(), token) > 0 ){
                    //       pop stack and append popped operator to output string
                        tmp=popChar();
                        strcat(postfixArray, tmp );
                        strcat(postfixArray,delimeter);
                    }

                    //     push token operator to stack
                    pushChar(token);
                    tmp = topOfCharStack();
                }
            }
        }
        token=strtok(NULL,delimeter);
	}
	while(topOfCharStack()!=NULL){
        tmp=popChar();
        strcat(postfixArray, tmp );
        strcat(postfixArray,delimeter);
	}
    return postfixArray;
}


int precedence(char* topOfStack, char* token){
    // while (token < topOfStack)
    // tok = token tmp value
    // stack = topOfStack tmp value
    int stack,tok,result;
    if(topOfStack!=NULL){
            if(strncmp(topOfStack, "^", 1)==0){
                stack=5;
            }
            else if(strncmp(topOfStack, "X", 1)==0){
                stack=4;
            }
            else if(strncmp(topOfStack, "/", 1)==0){
                stack=3;
            }
            else if(strncmp(topOfStack, "+", 1)==0){
                stack=2;
            }
            else if(strncmp(topOfStack, "-", 1)==0){
                stack=1;
            }
            else{
            stack=0;
        }
    }
    else{
        stack=0;
    }

        if(strncmp(token, "^", 1)==0){
            tok=5;
        }
        else if(strncmp(token, "X", 1)==0){
            tok=4;
        }
        else if(strncmp(token, "/", 1)==0){
            tok=3;
        }
        else if(strncmp(token, "+", 1)==0){
            tok=2;
        }
        else if(strncmp(token, "-", 1)==0){
            tok=1;
        }
        else{
            tok=0;
        }
    // compares the two assigned tmp values to see which
    // operator has the higher precedence
      result=stack-tok;
      return result;
}
