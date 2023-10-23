#include <stdio.h>
#include <unistd.h>
#include <sys/types.h>
#include <stdlib.h>
#include <sys/wait.h>
int main()
{
    char userChoice = 'z';
    while (userChoice != 'q')
    {
        printf("a: ls\n");
        printf("b: ps\n");
        printf("c: who\n");
        printf("q for quit\n");
        scanf(" %c", &userChoice);

        pid_t pid = fork();
        if (pid < 0)
        {
            exit(0);
        }

        if (pid == 0)
        {
            if (userChoice == 'a')
            {
                execlp("/bin/ls", "ls", NULL);
                exit(0);
            }
            else if (userChoice == 'b')
            {
                execlp("/bin/ps", "ps", NULL);
                exit(0);
            }
            else if (userChoice == 'c')
            {
                execlp("/usr/bin/whoami", "whoami", NULL);
                exit(0);
            }
            else if (userChoice == 'q')
            {
                exit(0);
            }
        }
        wait(NULL);
    }
    printf("parent terminated\n");
    return 1;
}