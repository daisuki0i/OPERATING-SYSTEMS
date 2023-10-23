#include <stdio.h>
#include <unistd.h>
#include <sys/types.h>
#include <stdlib.h>
#include <sys/wait.h>

int main()
{
    pid_t cpid;
    pid_t gcpid;

    cpid = fork();

    if (cpid == 0)
    {
        gcpid = fork();
        if (gcpid == 0)
        {
            printf("Can you see this3?\n");
            exit(0);
        }
        execlp("/bin/123ls", "ls", NULL);
        exit(0);
        printf("can you see this2?\n");
    }

    wait(NULL);
    printf("can you see this1?\n");
    printf("received Child Complete\n");
    exit(0);

    return 0;
}