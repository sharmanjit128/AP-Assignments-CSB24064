#include <stdio.h>
#include <stdlib.h>


void operation1()
{
    int a = 10;
    int b = 20;
    int c = a + b;
    printf("%d\n", c);
}


void operation2(int n)
{
    int *arr = (int *)malloc(n * sizeof(int));
    for(int i = 0; i < n; i++)
        arr[i] = i;
    for(int i = 0; i < n; i++)
        printf("%d ", arr[i]);
    printf("\n");
    free(arr);
}


void operation3(int n)
{
    int **matrix = (int **)malloc(n * sizeof(int *));
    for(int i = 0; i < n; i++)
        matrix[i] = (int *)malloc(n * sizeof(int));
    for(int i = 0; i < n; i++)
        for(int j = 0; j < n; j++)
            matrix[i][j] = i + j;
    for(int i = 0; i < n; i++)
        free(matrix[i]);
    free(matrix);
}


int main()
{
    int n;
    scanf("%d", &n);
    operation1();
    operation2(n);
    operation3(n);
    return 0;
}


