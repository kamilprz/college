// C code file for  a file ADT where we can read a single bit at a
// time, or write a single bit at a time

#include <stdlib.h>
#include <assert.h>
#include <string.h>
#include <stdio.h>

#include "bitfile.h"

// open a bit file in "r" (read) mode or "w" (write) mode
struct bitfile * bitfile_open(char * filename, char * mode)
{
    // opens a new bitfile and set its base values
    struct bitfile* this = malloc(sizeof(struct bitfile));
    this->buffer = 0;
    this->index = 0;
    this->is_EOF = 0;
    // if in read mode
    if(strcmp(mode, "r")==0){
        this->file = fopen(filename, "r");
        this->is_read_mode = 1;
        this->index = 8;
    }
    // if in write mode
    else if(strcmp(mode, "w")==0){
        this->file = fopen(filename, "w");
        this->is_read_mode = 0;
    }
    else{
        assert(0 && "not a valid mode");
    }
    return this;
}

// write a bit to a file; the file must have been opened in write mode
void bitfile_write_bit(struct bitfile * this, int bit)
{
    if(bit==1){
        this->buffer = this->buffer | (1 << (this->index));
    }
    this->index++;
    if(this->index == 8){
        fputc(this->buffer, this->file);
        this->index = 0;
        this->buffer = 0;
    }
}

// read a bit from a file; the file must have been opened in read mode
int bitfile_read_bit(struct bitfile * this)
{
    int result;
    if(this->index > 7){
        this->buffer = fgetc(this->file);
        this->index = 0;
    }
    result = ((this->buffer & (1<<this->index)) >> this->index);
    this->index++;
    return result;
}

// close a bitfile; flush any partially-filled buffer if file is open
// in write mode
void bitfile_close(struct bitfile * this) {
    if(this->is_read_mode != 1){
        fputc(this->buffer, this->file);
    }
    fclose(this->file);
    free(this);
}

// check for end of file
int bitfile_end_of_file(struct bitfile * this)
{
    return feof(this->file);
}
