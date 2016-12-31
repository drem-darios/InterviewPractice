//
//  main.c
//  urlify
//
//  Created by Parlee, Michael on 12/30/16.
//  Copyright (C) 2016 Parlee, Michael. All rights reserved.
//

#include <stdio.h>

#define TRUE_LENGTH 17

int main(int argc, constchar * argv[]) {
    char chars[] = "My name is Michael..................";
    int new_size = sizeof(chars);
    int end_pos = new_size;
    for (int x=TRUE_LENGTH; x >= 0; x--) {
        char current_char = chars[x];
        if (current_char != ' ') {
            chars[end_pos] = current_char;
        } else {
            chars[end_pos] = '0';
            chars[--end_pos] = '2';
            chars[--end_pos] = '%';
        }
        chars[x] = '.';
        end_pos--;
    }
    
    printf("Result: %s\n",chars);
    return0;
}
