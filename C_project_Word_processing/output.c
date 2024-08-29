// Contains the code preparing and printing the final output and the error messages
#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <ctype.h>
#include "fileread.h"
#include "ssort.h"
#include "output.h"
#include "wordtype.h"

void word_outputs(int n){// outputs the words by going through each word
    int number = 0;
   
    int skip = 0;
    
    for (int i=0 ; i< 1000;i++){
      
        skip = 0;
        for (int j=0 ; j < 1000; j++){
            if (strcmp(arrayword[i],skipword[j])!=0){
             

            }
            else if (strcmp(arrayword[i],skipword[j])==0){
                skip =1;
            }
        }
        if (skip == 0){
         if (number < n){
                    if ((number+1) % 10 == 0 && number!=1){
                        printf("%s\n",arrayword[i]);
                        
                      
                    }
                    else{
                        printf("%s",arrayword[i]);
                        printf(" ");
                        
                        
                    }
                  

                number++;
                
                }}

    }
    printf("\n");
}

void wtype(){ // error handling for wrong wtype format
    printf("Wrong word type.");
    exit(2);
}

void n(){ // error handling for wrong number of words format
    printf("Wrong amount of words.");
    exit(1);
}

void wrongamount(){ // error handling for wrong amount of inputs format
    printf("Wrong amount of inputs.");
    printf("Input using the following way : <name of the c file> <inputfile> <n> <wtype> <sorttype> [<skipword1>, <skipword2>,etc]");
    exit(1);
}

void memoryoverflow(){ // used to handle memory overflow
    printf("Memory Overflow");
    exit(1);
}