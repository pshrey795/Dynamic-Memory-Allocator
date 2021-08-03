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
* Each memory block is represented by its starting memory address and its size. 
* And every data structure essentially implements three functions:
 1. Free(memory address): Frees/reclaims the memory block whose starting address matches the given memory address.
 2. Allocate(block size): Allocates a block of required size according to the above algorithm.
 3. Defragment: Traverses the collection of free memory blocks and merges contiguous memory fragments.
* All the data structures have the same method for compilation, running and testing.

Compliling the files
---

1. Clone the directory locally using `git clone https://github.com/pshrey795/Dynamic-Memory-Allocator.git`.
2. Then navigate to the sub-directory based on the data structure you want to use for memory allocation.
3. To build the Java files, simply type `make all` in the terminal.
4. The .class files can be removed after their use, by typing `make clean` in the console.
5. Note that you need to compile separately if you want to use another data structure.

Testing
--- 

1. For testing, use the _run.sh_. First, this file may have restricted permission. If so, type `chmod a+x run.sh` in the console to allow permission to execute it.
2. After compiling all the java files, choose any of the testcases present in the appropriate folder as an input file and an empty output file (one such output file _output.txt_ has been provided).
3. Then, run the command `./run.sh <input_file_path> <output_file_path>` in the terminal and the output of the program will be printed in the output file.
4. The correctness of the output file can be checked against the model output provided in the same folder as the testcases using the _compare.py_ file. This file can be invoked using `python3` in the terminal and then entering the files to be comparing in the console.

Analysis
---
The main purpose of using different data structures is to show the variations in time and space complexities. Such analysis is done in the document provided in the Analysis folder for every data structure.