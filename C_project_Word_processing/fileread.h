#ifndef FILEREAD_H
#define FILEREAD_H

int skip_words(char *word) ; // skips the words when reading the file
int read_words(char *filename, char *wtype); // read the files and inputs them into the char array
extern char arrayword[1000][1000]; // stores all the words
extern char skipword[1000][1000]; // store all the skipwords
extern char substitutarrayword[1000][1000]; // store all the words
void convertToLowercase2D() ; // This converts uppercase letters into lowercase

void bubbleSort(char * sorttype); // sort the words to be outputed to the user 
#endif