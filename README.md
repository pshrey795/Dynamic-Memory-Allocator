Dynamic Memory Allocation
===

The exact specifications can be found in the [problem statement](https://github.com/pshrey795/Dynamic-Memory-Allocator/blob/master/Statement.pdf).

Overview 
---

* A dynamic memory allocator accomodates memory requests of different processes/programs by allocating the available memory blocks. It also reclaims the memory blocks once the process if over to avoid wastage.
* Out of many available memory blocks, the choice of block to allocate can vary depending on the underlying algorithm, the most prevalent ones of which are 
 1. **First Fit**: Allocate the first memory block in sequence which is large enough to satisfy the requirement.
 2. **Best Fit**: Allocate the shortest memory block which is large enough to satisfy the requirement.
* Both algorithms have their own advantages and drawbacks, such as First Fit is time efficient but not space efficient while the Best Fit is space efficient but not time efficient(we might need to search through all memory blocks, depending on the data structure). 
* However, we use a hybrid algorithm called **First Split Fit(or Best Split Fit)** in which we find the first memory block large enough, and then split it into two fragments, one of whose size will be exactly equal to the amount of memory we require. In this way, we save both time and space.
* However, this strategy causes the single memory block to be fragmented into many smaller parts after multiple allocations, which causes wastage of a lot of space as smaller memory blocks are less likely to accomodate our memory requirements. So, we also need an efficient way to merge or **Defragment** contiguous memory blocks for re-use.

Implementation
---

* We start with a single large memory block. And as different memory blocks are allocated, we maintain two different collections for for storing the free memory blocks and allocated memory blocks. 
* This collection can be constructed out of different data structures, which is abstracted using the _Dictionary class_ as abstract parent class.
* All the different data structures and their associated methods have been implemented separately in sub-directories, and each of them have been tested separately. 