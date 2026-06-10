#include <stdio.h>
#include <stdlib.h>
#include <pthread.h>

#define NUM_THREADS 4
#define INCREMENTS 1000000

/* Shared global counter */
long long counter = 0;

/* Thread function */
void *increment_counter(void *arg) {
   for (int i = 0; i < INCREMENTS; i++) {
       counter++;   // Critical section (unsafe)
   }

   return NULL;
}

int main() {
   pthread_t threads[NUM_THREADS];

   /* Create threads */
   for (int i = 0; i < NUM_THREADS; i++) {
       if (pthread_create(&threads[i], NULL,
                          increment_counter, NULL) != 0) {
           perror("pthread_create failed");
           return 1;
       }
   }

   /* Wait for all threads to finish */
   for (int i = 0; i < NUM_THREADS; i++) {
       pthread_join(threads[i], NULL);
   }

   printf("Final Counter Value (Without Mutex): %lld\n", counter);
   printf("Expected Value: %d\n",
          NUM_THREADS * INCREMENTS);

   return 0;
}

OUTPUT-
 

CODE WITH PTHREAD_MUTEX-
#include <stdio.h>
#include <stdlib.h>
#include <pthread.h>

#define NUM_THREADS 4
#define INCREMENTS 1000000

/* Shared global counter */
long long counter = 0;

/* Mutex declaration */
pthread_mutex_t lock;

/* Thread function */
void *increment_counter(void *arg) {
   for (int i = 0; i < INCREMENTS; i++) {

       /* Lock critical section */
       pthread_mutex_lock(&lock);

       counter++;

       /* Unlock critical section */
       pthread_mutex_unlock(&lock);
   }

   return NULL;
}

int main() {
   pthread_t threads[NUM_THREADS];

   /* Initialize mutex */
   pthread_mutex_init(&lock, NULL);

   /* Create threads */
   for (int i = 0; i < NUM_THREADS; i++) {
       if (pthread_create(&threads[i], NULL,
                          increment_counter, NULL) != 0) {
           perror("pthread_create failed");
           return 1;
       }
   }

   /* Wait for all threads */
   for (int i = 0; i < NUM_THREADS; i++) {
       pthread_join(threads[i], NULL);
   }

   printf("Final Counter Value (With Mutex): %lld\n", counter);
   printf("Expected Value: %d\n",
          NUM_THREADS * INCREMENTS);

   /* Destroy mutex */
   pthread_mutex_destroy(&lock);

   return 0;
}

