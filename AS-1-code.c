#include <stdio.h>
#include <time.h>
void constant(int n){
    int x=0;
    x++;
}


void linear(int n){
    int s=0;
    for(int i=0;i<n;i++)
    s=s+i;
}


void quadratic(int n){
    int s=0;
    for(int i=0;i<n;i++)
        for(int j=0;j<n;j++)
            s=s+i+j;
}


int main(){
    int sizes[]={100,500,1000,2000};
    int k=4;
    clock_t start,end;


    for(int i=0;i<k;i++){
        int n=sizes[i];


        start=clock();
        constant(n);
        end=clock();
        printf("time=%f\n",(double)(end-start));


        start=clock();
        linear(n);
        end=clock();
        printf("time=%f\n",(double)(end-start));


        start=clock();
        quadratic(n);
        end=clock();
        printf("time=%f\n",(double)(end-start));
    }
    return 0;
}

