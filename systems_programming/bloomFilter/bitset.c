
#include "bitset.h"
#include <stdio.h>
#include <stdlib.h>
#include <assert.h>

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
