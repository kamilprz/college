// code for a huffman coder
#include <stdio.h>
#include <stdlib.h>
#include <assert.h>
#include <ctype.h>

#include "huff.h"
#include "bitfile.h"

//function headers
void makeCompound(struct huffchar* smallest, struct huffchar* scndSmallest, struct huffchar * compound, int count);
int findMin(struct huffchar* arr[], int index);

// create a new huffcoder structure
struct huffcoder *  huffcoder_new()
{
    struct huffcoder* result;
    result = malloc(sizeof(struct huffcoder));
    return result;
}

// count the frequency of characters in a file; set chars with zero
// frequency to one
void huffcoder_count(struct huffcoder * this, char * filename)
{
    // set all the frequencies to 0 as start
    for(int i=0; i < NUM_CHARS; i++){
     this->freqs[i] = 0;
    }
    int tmp=0;

  unsigned char c;  // we need the character to be unsigned to use it as an index
  FILE * file;
  file = fopen(filename, "r");
  assert( file != NULL );
  c = fgetc(file);	// attempt to read a byte
  while( !feof(file) ) {
    tmp = (unsigned)c;
    if(tmp>=0 && tmp<256){
        this->freqs[tmp]++;
    }
    c = fgetc(file);
  }
    // set all the frequencies of 0 to frequencies of 1
    for(int i=0; i < NUM_CHARS; i++){
     if(this->freqs[i]==0){
        this->freqs[i] = 1;
     }
    }
  fclose(file);
}


// using the character frequencies build the tree of compound
// and simple characters that are used to compute the Huffman codes
void huffcoder_build_tree(struct huffcoder * this)
{
    struct huffchar* charList[NUM_CHARS];
    for(int i=0; i<NUM_CHARS;i++){
        struct huffchar * theChar = malloc(sizeof(struct huffchar));
        charList[i] = theChar;
        charList[i]->freq = this->freqs[i];
        charList[i]->is_compound=0;
        charList[i]->u.c=i;
        charList[i]->seqno=i;
    }

    int counter = NUM_CHARS - 1;
    for(int i=0; i<NUM_CHARS-1; i++){
        // find index of smallest frequency value
        int smallestIndex = findMin(charList, counter);

        //swap [counter] with smallest
        struct huffchar* tmpMin = charList[smallestIndex];
        struct huffchar* tmp = malloc(sizeof(struct huffchar));;
        tmp = charList[counter];
        charList[counter]=tmpMin;
        charList[smallestIndex]=tmp;

        // find index of second smallest frequency value
        int scndSmallestIndex = findMin(charList, counter-1);
        //swap [counter-1] with scndSmallest
        struct huffchar* tmpScndMin = charList[scndSmallestIndex];
        tmp=charList[counter-1];
        charList[counter-1]=tmpScndMin;
        charList[scndSmallestIndex]=tmp;

        // make compound char of smallest and scndSmallest
        struct huffchar* compound = malloc(sizeof(struct huffchar));
        makeCompound(charList[counter], charList[counter-1], compound, i);
        // make second last value of array the compound node just made
        charList[counter-1]=compound;
        // remove last value of array
        charList[counter]=NULL;
        counter--;
    }
    // pass pointer to root of tree
    this->tree = charList[counter];
}


// recursive function to convert the Huffman tree into a table of
// Huffman codes
void tree2table_recursive(struct huffcoder * this, struct huffchar * node,
		 unsigned int path, int depth)
{
    if(node->is_compound == 1){
        // go down left child = 0
        path = path << 1;
        tree2table_recursive(this, node->u.compound.left, path, depth+1);
        // go down right child = 1
        path = path | 1;
        tree2table_recursive(this, node->u.compound.right, path, depth+1);
    }
    else{
        unsigned char index = node->u.c;
        this->codes[index] = (long)path;
        this->code_lengths[index] = depth;
    }
}

// using the Huffman tree, build a table of the Huffman codes
// with the huffcoder object
void huffcoder_tree2table(struct huffcoder * this)
{
    unsigned int path = 0;
    int depth = 0;
    if(this->tree != NULL){
        tree2table_recursive(this, this->tree  , path, depth);
    }
}


// print the Huffman codes for each character in order
void huffcoder_print_codes(struct huffcoder * this)
{
  int i, j;
  char buffer[NUM_CHARS];

  for ( i = 0; i < NUM_CHARS; i++ ) {
    // put the code into a string
    for ( j = this->code_lengths[i]-1; j >= 0; j--) {
      buffer[this->code_lengths[i]-1-j] = ((this->codes[i] >> j) & 1) + '0';
    }
    // don't forget to add a zero to end of string
    buffer[this->code_lengths[i]] = '\0';

    // print the code
    printf("char: %d, freq: %d, code: %s\n", i, this->freqs[i], buffer);;
  }
}

// sets all the values of a newly created compound node
void makeCompound(struct huffchar* smallest, struct huffchar* scndSmallest, struct huffchar * compound, int i){
    compound->freq = (smallest->freq + scndSmallest->freq);
    compound->seqno = NUM_CHARS + i;
    compound->is_compound = 1;
    compound->u.compound.left = smallest;
    compound->u.compound.right = scndSmallest;
}

// finds the minimum value in an array of size counter
int findMin(struct huffchar* arr[], int counter){
    int smallestIndex = 0;
    int smallestFreq = arr[0]->freq;
    for(int i=0; i<=counter; i++){
        if(arr[i]->freq < smallestFreq){
            smallestFreq = arr[i]->freq;
            smallestIndex = i;
        }
        // if frequencies are the same, make the one with the smaller seqno the smaller value
        else if(arr[i]->freq == smallestFreq){
            if(arr[i]->seqno < arr[smallestIndex]->seqno){
                smallestIndex = i;
                smallestFreq = arr[i]->freq;
            }
        }
    }
    return smallestIndex;
}

// encode the input file and write the encoding to the output file
void huffcoder_encode(struct huffcoder * this, char * input_filename, char * output_filename)
{
    struct bitfile* writeFile = bitfile_open(output_filename,"w");
    FILE* readFile = fopen(input_filename, "rb");
    unsigned char tmp = fgetc(readFile);
    // encode until EOT is hit
    while(!feof(readFile)){
        for(int i = this->code_lengths[tmp]-1; i >=0; i--){
            bitfile_write_bit(writeFile, ((this->codes[tmp]>>i)&1));
        }
        tmp = fgetc(readFile);
    }
    // loop ended when EOT is hit, meaning we have to manually encode EOT now
    // EOT = 4 ASCII
    tmp = 4;
    for(int i = this->code_lengths[tmp]-1; i >=0; i--){
        bitfile_write_bit(writeFile, ((this->codes[tmp]>>i)&1));
    }
    bitfile_close(writeFile);
    fclose(readFile);
}

// decode the input file and write the decoding to the output file
void huffcoder_decode(struct huffcoder * this, char * input_filename, char * output_filename)
{
    struct bitfile* readFile = bitfile_open(input_filename, "r");
    FILE* writeFile = fopen(output_filename, "w");
    struct huffchar* node = this->tree;
    while(readFile->is_EOF == 0){
        int bit = bitfile_read_bit(readFile);
        // if bit = 0 go down left child
        if(bit == 0){
            node = node->u.compound.left;
        }
        // if bit = 1 go down right child
        else if(bit == 1){
            node = node->u.compound.right;
        }
        // if the node is not compound - means we hit bottom of huffman tree
        // we need to write the character to the output file
        if(node->is_compound == 0){
            // if character is EOT - end loop
            if(node->u.c == 4){
                readFile->is_EOF = 1;
            }
            // if character is !=EOT write it to output file
            // and reset the node back to root of tree
            else{
                fputc(node->u.c, writeFile);
                node = this->tree;
            }
        }

    }
    bitfile_close(readFile);
    fclose(writeFile);
}


