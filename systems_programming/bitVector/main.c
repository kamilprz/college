#include <stdio.h>
#include <stdlib.h>
#include <assert.h>

struct bitset{
    unsigned int* bits;
    int sizeInBits;
    int sizeInWords;
    int bitsPerWord;
};

// create a new, empty bit vector set with a universe of 'size' items
struct bitset* bitset_new(int size){
    struct bitset* result;
    int sizeInWords,i,bitsPerWord;
    result = malloc(sizeof(struct bitset));
    bitsPerWord = sizeof(unsigned int)*8;
    sizeInWords=size/bitsPerWord;

    if(size%(sizeof(unsigned int)*8!=0)){
        sizeInWords++;
    }
    result->bits = malloc(sizeof(unsigned int)*sizeInWords);
    for(i=0; i<sizeInWords; i++){
        result->bits[i]=0;
    }
    result->bitsPerWord = bitsPerWord;
    result->sizeInBits = size;
    result->sizeInWords = sizeInWords;
    return result;
};

// get the size of the universe of items that could be stored in the set
int bitset_size(struct bitset * this){
    return this->sizeInBits;
};

// get the number of items that are stored in the set
int bitset_cardinality(struct bitset * this){
    int counter=0;
    for(int i=0; i<(this->sizeInBits); i++){
        if(this->bits[i] &= (1<<(i)) > 0){
            counter++;
        }
    }
    return counter;
};


// check to see if an item is in the set
int bitset_lookup(struct bitset * this, int item){
    if(item >= 0 && item < this->sizeInBits){
        int wordIndex = item / this->bitsPerWord;
        int bitIndex = item % this->bitsPerWord;
        if((this->bits[wordIndex] & 1<<(bitIndex)) > 0){
           return 1;
        }
        else{
            return 0;
        }
    }
    return 0;
};


// add an item, with number 'item' to the set
// has no effect if the item is already in the set
int bitset_add(struct bitset* this, int item){
    if(item >= 0 && item < this->sizeInBits){
        int wordIndex = item / this->bitsPerWord;
        int bitIndex = item % this->bitsPerWord;
        int mask = (1<<(bitIndex));
        this->bits[wordIndex] |= (mask);
        return 1;
    }
    else{
        return 0;
    }
};


// remove an item with number 'item' from the set
int bitset_remove(struct bitset * this, int item){
    if(item >= 0 && item < this->sizeInBits){
        int wordIndex = item / this->bitsPerWord;
        int bitIndex = item % this->bitsPerWord;
        int mask = 1<<(bitIndex);
        mask = ~mask;   // inverts the mask
        this->bits[wordIndex] &= mask;
        return 1;
    }
    else{
        return 0;
    }
};


// place the union of src1 and src2 into dest;
// all of src1, src2, and dest must have the same size universe
void bitset_union(struct bitset * dest, struct bitset * src1, struct bitset * src2){
    assert(src1->sizeInBits == src2->sizeInBits);
    assert(src1->sizeInBits == dest->sizeInBits);
    for(int i=0; i<src1->sizeInWords; i++){
        dest->bits[i] = src1->bits[i] | src2->bits[i];
    }
};


// place the intersection of src1 and src2 into dest
// all of src1, src2, and dest must have the same size universe
void bitset_intersect(struct bitset * dest, struct bitset * src1, struct bitset * src2){
    assert(src1->sizeInBits == src2->sizeInBits);
    assert(src1->sizeInBits == dest->sizeInBits);
    for(int i=0; i< src1->sizeInWords; i++){
        dest->bits[i] = src1->bits[i] & src2->bits[i];
    }
};





//////////////////////////////////////////// TESTS ////////////////////////////////////////////////////////////////


// print the contents of the bitset
void bitset_print(struct bitset * this)
{
  int size = bitset_size(this);
  for (int i = 0; i < size; i++ ) {
    if ( bitset_lookup(this, i) == 1 ) {
      printf("%d ", i);
    }
  }
  printf("\n");
}

// add the characters from a string to a bitset
void add_chars_to_set(struct bitset * this, char * s)
{
  for (int i = 0; s[i] != 0; i++ ) {
    unsigned char temp = s[i];
    bitset_add(this, temp);
  }
}

// small routine to test a bitset
void mytest()
{
  struct bitset * a = bitset_new(256);
  struct bitset * b = bitset_new(256);
  struct bitset * c = bitset_new(256);
  char * string1 = "What can you hear";
  char * string2 = "Nothing but the rain";

  add_chars_to_set(a, string1);
  add_chars_to_set(b, string2);

  // print the contents of the sets
  bitset_print(a);
  bitset_print(b);

  // compute and print the union of sets
  bitset_union(c, a, b);
  bitset_print(c);

  // compute and print the intersection of sets
  bitset_intersect(c, a, b);
  bitset_print(c);
}

int main(){
    mytest();
    return 0;
};
