#include <stdlib.h>
#include <unistd.h>
#include <string.h>
#define SIZE 10
#include <stdio.h>

int main()
{
    int pfd[2];
    int nread;
    int pid;
    char buf[SIZE];
    char inbuf[SIZE * 2];
    pipe(pfd); /* q1.1 */
    printf("write pipe id = %d ", pfd[1]);
    printf(" read file id = %d\n", pfd[0]);

    pid = fork();
    if (pid == 0)
    {
        close(pfd[1]);
        while ((nread = read(pfd[0], buf, SIZE)) != 0) /* q1.2 */
            if (nread > 11)
                printf("avoid overflow no conversion %s to int", buf);
            else
                printf("child received %s. After + 5 = %d\n", buf, atoi(buf) + 5);
        close(pfd[0]);
    }
    else
    {
        close(pfd[0]); /* q1.5 */
        sprintf(inbuf, "%ld", 123456789012);
        write(pfd[1], inbuf, strlen(inbuf) + 1); /* q1.3 */
        close(pfd[1]);                           /* q1.4 */
        wait(NULL);                              /* q1.5 */
    }
    return 0;
}