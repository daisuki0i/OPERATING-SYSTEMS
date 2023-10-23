#include <pthread.h>
#include <stdio.h>
#include <stdlib.h>

int csum;
void *runner(void *param);

int main(int argc, char *argv[])
{
    pthread_t tid;
    pthread_attr_t attr;
    pthread_attr_init(&attr);

    pthread_create(&tid, &attr, runner, argv[1]);

    int num = atoi(argv[1]);
    int msum = 0;

    if (num > 0)
    {
        for (int i = 1; i <= num; i++)
        {
            msum += i;
        }
    }
    // pthread_join(tid, NULL);

    printf("csum = %d\n", csum);
    printf("msum = %d\n", msum);
    printf("csum - msum = %d\n", csum - msum);
    return 0;
}
void *runner(void *param)
{
    int num = atoi(param);
    csum = 0;

    if (num > 0)
    {
        int numX2 = num * 2;
        for (int i = 1; i <= numX2; i++)
        {
            csum += i;
        }
    }
    pthread_exit(0);
}