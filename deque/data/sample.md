kmalloc (9)
 
NAME

kmalloc, kfree - allocate and free pieces of memory
 

SYNOPSIS
#include <linux/malloc.h>

void * kmalloc (size_t size, int priority);
void kfree (void * __ptr);  
DESCRIPTION
The kmalloc function allocates a piece of memory.

The parameter size is the number of bytes that will be allocated. The parameter priority indicates the importance and type of the memory request. Some possible values are GFP_DMA, GFP_ATOMIC, GFP_BUFFER, and GFP_NFS.

The kfree function releases a piece of memory that is passed as the __ptr parameter.  
RETURN VALUE
On success, kmalloc returns a pointer to the newly allocated memory.

If there is an error, NULL is returned instead.

 
AVAILABILITY
Linux 2.0  
AUTHOR
Kirk Petersen (kirk@speakeasy.org)  