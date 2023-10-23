// 65050777
// Received a SIGUSR1. The max n is 20! = 2432902008176640000

/* This program demonstrates the use of ANSI C
library function raise(). Detecting overflow */

#include <stdio.h>
#include <stdlib.h>
#include <signal.h>

signed long prev_computed, i; /* global variables */
void SIGhandler(int sig)
{
    printf("\nReceived a ");
    printf("SIGUSR1. The max n is ");
    printf("%ld! = %ld\n", i - 1, prev_computed);
    exit(0);
}

int main(void)
{ // no need prototype
    signed long cur_value;

    // to know type size, printf("%ld\n",sizeof(long));
    printf("2 power n:\n");
    signal(SIGUSR1, SIGhandler);
    /* install SIGUSR1 handler */
    prev_computed = 1;
    for (i = 1;; i++)
    {
        cur_value = prev_computed * i;
        if (cur_value < prev_computed)
            raise(SIGUSR1);
        prev_computed = cur_value;
    }
    return 0;
}