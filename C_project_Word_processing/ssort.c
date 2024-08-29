// Contains the main function
#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <ctype.h>
#include "fileread.h"
#include "ssort.h"
#include "output.h"
#include "wordtype.h"
char skipword[1000][1000];

int main(int argc, char **argv){ // main function whihc will take the input 
     
        if (argc < 4){
         wrongamount();

      }
   
        if (argv[3] == NULL || argv[3][0] == '\0'){}// verifies if the inputs are correct
        else if (strcmp(argv[3],"ALPHA")==0 || strcmp(argv[3],"ALPHANUM")==0 || strcmp(argv[3],"ALL")==0){}
        else if (strcmp(argv[3],"ALPHA")!=0 || strcmp(argv[3],"ALPHANUM")!=0 || strcmp(argv[3],"ALL")!=0){wtype();}
       
        if (argv[2] == NULL || argv[2][0] == '\0'){} // verifies if the inputs are correct
        else {
          int i;
          for (i =0; i< strlen(argv[2]);i++){
               if (!isdigit(argv[2][i])){
               n();
               break;}}
          }
       
        char *inputfile = argv[1];
        int n = atoi(argv[2]);
        char *wtype = argv[3];
        char *sorttype = NULL;
        if (argv[4] == NULL || argv[4][0] == '\0'){}// initialise the sortype
        else if (strcmp(argv[4],"ASC")==0 || strcmp(argv[4],"DESC")==0){ 
        sorttype = argv[4];}

       
         
      if (argc == 4){ // this conditional statement helps to input all the skipwords into the proper 2d array
           
        }
       else if (strcmp(argv[4],"ASC")==0 || strcmp(argv[4],"DESC")==0 ){
              if (argc-5>999){memoryoverflow();}// handles memoryoverflow
              for(int i=5; i< argc;i++){
               if (strlen(argv[i])>999){memoryoverflow();}// handles memoryoverflow
               for (int j=0; j< strlen(argv[i]);j++){
                skipword[i-5][j]= argv[i][j] ;
               }
              }}
       
        else if (strcmp(argv[4],"ASC")!=0 || strcmp(argv[4],"DESC")!=0 ){
             if (argc-4>999){memoryoverflow();}// handles memoryoverflow
             for(int i=4; i< argc;i++){
               if (strlen(argv[i])>999){memoryoverflow();}// handles memoryoverflow
               for (int j=0; j< strlen(argv[i]);j++){
                skipword[i-4][j]= argv[i][j] ;
               }
              }}


       read_words(inputfile, wtype);// this reads the file
      

       if (sorttype==NULL){ // this calls the bubblesort function
          bubbleSort("ASC");
       } else if (sorttype!=NULL){
          bubbleSort(sorttype);
       
      }

      
       word_outputs(n);
   
   return 0;
}