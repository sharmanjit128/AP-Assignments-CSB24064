#include &lt;stdio.h&gt;
#include &lt;stdlib.h&gt;
#include &lt;string.h&gt;
typedef struct {
char *data;
size_t length;
size_t capacity;
} StringBuffer;
/* Initialize the StringBuffer with a given initial capacity */
StringBuffer *sb_create(size_t init_cap) {
StringBuffer *sb = (StringBuffer *)malloc(sizeof(StringBuffer));
if (!sb) {
fprintf(stderr, "Memory allocation failed for StringBuffer.\n");
return NULL;
}
sb-&gt;data = (char *)malloc(init_cap);
if (!sb-&gt;data) {
fprintf(stderr, "Memory allocation failed for data buffer.\n");
free(sb);
return NULL;
}
sb-&gt;length = 0;
sb-&gt;capacity = init_cap;
sb-&gt;data[0] = '\0'; /* Null-terminate immediately */
return sb;
}
/* Append a string to the buffer, growing it as necessary */
int sb_append(StringBuffer *sb, const char *str) {
if (!sb || !str) return -1;
size_t str_len = strlen(str);
size_t needed = sb-&gt;length + str_len + 1;
/* Double capacity until sufficient */
while (needed &gt; sb-&gt;capacity) {
size_t new_cap = sb-&gt;capacity * 2;
char *tmp = (char *)realloc(sb-&gt;data, new_cap);
if (!tmp) {
fprintf(stderr, "Reallocation failed.\n");
return -1;
}
sb-&gt;data = tmp;
sb-&gt;capacity = new_cap;
printf(" [info] Buffer resized to capacity: %zu\n", sb-&gt;capacity);

}
memcpy(sb-&gt;data + sb-&gt;length, str, str_len + 1);
sb-&gt;length += str_len;
return 0;
}
/* Release all memory held by the StringBuffer */
void sb_destroy(StringBuffer *sb) {
if (sb) {
free(sb-&gt;data);
free(sb);
}
}
int main(void) {
/* Start with a small capacity to trigger resizing */
StringBuffer *sb = sb_create(8);
if (!sb) return 1;
printf("Initial capacity : %zu\n\n", sb-&gt;capacity);
/* Test 1 */
sb_append(sb, "Hello");
printf("After append \"Hello\":\n");
printf(" String : %s\n", sb-&gt;data);
printf(" Length : %zu | Capacity: %zu\n\n",
sb-&gt;length, sb-&gt;capacity);
/* Test 2 */
sb_append(sb, ", World!");
printf("After append \", World!\":\n");
printf(" String : %s\n", sb-&gt;data);
printf(" Length : %zu | Capacity: %zu\n\n",
sb-&gt;length, sb-&gt;capacity);
/* Test 3 – longer string forces multiple doublings */
sb_append(sb, " Dynamic buffers prevent overflow.");
printf("After append long string:\n");
printf(" String : %s\n", sb-&gt;data);
printf(" Length : %zu | Capacity: %zu\n\n",
sb-&gt;length, sb-&gt;capacity);
sb_destroy(sb);
printf("All memory released successfully.\n");
return 0;
}
