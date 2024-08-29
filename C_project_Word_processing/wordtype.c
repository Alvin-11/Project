//Contains the logic associated with the word types
#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <ctype.h>
#include "fileread.h"
#include "ssort.h"
#include "output.h"
#include "wordtype.h"

int alpha(char character){ // verifies is the words is composed of alphabetical characters
    return isalpha(character);
}
int alphanum(char character){// verifies is the words is composed of alphabetical characters and number
    return isalnum(character);
}
int all(char character){// verifies is the words is composed of anything ither than a whitespace
    return !isspace(character);
}