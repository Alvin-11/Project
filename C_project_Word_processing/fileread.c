// Contains the file read and skip logic
#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <ctype.h>
#include "fileread.h"
#include "ssort.h"
#include "output.h"
#include "wordtype.h"
char arrayword[1000][1000];
char substitutarrayword[1000][1000];

void convertToLowercase2D() { // This converts uppercase letters into lowercase
    for (int i = 0; i < 1000; i++) {
        for (int j = 0; j < 1000; j++) {
            substitutarrayword[i][j] = tolower(arrayword[i][j]);
        }
       
    }
}

int skip_words(char *word) { // this alert that this word is to be skipped or not
    for (int i =0; i< strlen(skipword[i]); i++){
        if (strcmp(word,skipword[i])==0){
            return 1;
        }
    }
    return 0;
}


int read_words(char *filename, char *wtype) { // read the files and inputs them into the char array
    FILE *fptr = fopen(filename, "r");
   
   char character;
   int is_previous_alpha = 0; // Flag to track if previous character was alphabetic
   int i = 0;
   int j=0;

   if(fptr==NULL){
     printf("File not found");
     return 1;
   }
   
   if (strcmp(wtype,"ALPHA")==0){ // This treats all alphabetical characters as words and the rest as whitespace
   while ((character = fgetc(fptr)) != EOF) {
        if (alpha(character)) { // If character is alphabetic
            is_previous_alpha = 1;
            if (i>999){memoryoverflow();}
            if (j>999){memoryoverflow();}
            arrayword[i][j++] = character;
        } else { // If character is non-alphabetic
            if (is_previous_alpha) { // If previous character was alphabetic
                i++;
                j=0;
            }
            is_previous_alpha = 0;
        }
    }

    }
    else if (strcmp(wtype,"ALPHANUM")==0){  // This treats all alphabetical characters and number as words and the rest as whitespace
        while ((character = fgetc(fptr)) != EOF) {
        if (alphanum(character)) { // If character is alphanumeric
            is_previous_alpha = 1;
            if (i>999){memoryoverflow();}
            if (j>999){memoryoverflow();}
            arrayword[i][j++] = character; // Store in the 2D array
        } else { // If character is not alphanumeric
            if (is_previous_alpha) { // If a word has been found
                i++;
                j = 0;
            }
            is_previous_alpha = 0;
            // If it's not an alphanumeric character, it's whitespace
        }
    }

    }
    else if (strcmp(wtype,"ALL")==0){  // This treats everything as words except the whitespace characters
        while ((character = fgetc(fptr)) != EOF) {
        if (all(character) & (character!=',') & (character!='\t') & (character!='\n') & (character!='\v') & (character!='\f') & (character!='\r')) { // If character is not whitespaces
            is_previous_alpha = 1;
            if (i>999){memoryoverflow();}
            if (j>999){memoryoverflow();}
            arrayword[i][j++] = character; // Store in the 2D array
        } else { // If character is not a word
            if (is_previous_alpha) { // If a word has been found
                i++;
                j = 0;
            }
            is_previous_alpha = 0;
            
        }
    }
    }
   convertToLowercase2D();
   fclose(fptr);
   return 0;
}

void bubbleSort(char * sorttype) { // sort the words to be outputed to the user 
    int i, j;
    char temp[1000]; // temporary storage 
    char temp2[1000]; // temporary storage 

    if (strcmp(sorttype,"ASC")==0){ // sort the outputs in ascending order using the bubble sorting

    for (i = 0; i < 1000; i++) {
        for (j = 0; j < 1000-i-1; j++) {
            if (strcmp(substitutarrayword[j], substitutarrayword[j+1]) > 0) {
                strcpy(temp, arrayword[j]);
                strcpy(arrayword[j], arrayword[j+1]);
                strcpy(arrayword[j+1], temp);
                strcpy(temp2, substitutarrayword[j]);
                strcpy(substitutarrayword[j], substitutarrayword[j+1]);
                strcpy(substitutarrayword[j+1], temp2);
            }
        }
    }} else if (strcmp(sorttype,"DESC")==0){ // sort the outputs in descending order using the bubble sorting
    
     for (i = 0; i < 1000; i++) {
        for (j = 0; j < 1000-i-1; j++) {
            if (strcmp(substitutarrayword[j], substitutarrayword[j+1]) < 0) {
                strcpy(temp, arrayword[j]);
                strcpy(arrayword[j], arrayword[j+1]);
                strcpy(arrayword[j+1], temp);
                strcpy(temp2, substitutarrayword[j]);
                strcpy(substitutarrayword[j], substitutarrayword[j+1]);
                strcpy(substitutarrayword[j+1], temp2);
            }
        }
    }}
    }

