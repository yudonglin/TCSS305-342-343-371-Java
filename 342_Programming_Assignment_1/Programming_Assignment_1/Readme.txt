a. Time Complexity of encode(), decode() and equal() methods in terms of Big-O notation.
The time complexity of encode(), decode() and equal() methods in terms of Big-O notation are all O(n).


b. What are the advantages and disadvantages of RLE? What are the applications of RLE?

RLE can reduce the size of a String if duplicated letters appear simultaneously. It also makes transferring data between
different programming languages becomes more accessible. As a result, one application of RLE I can think of is that if
you implement the linked list data structure in both Java and C++ and wants to transfer the data between these two
languages, RLE can be a viable solution. The compressed String may also take up less memory to store than its original
form and linked list form.
However, as example 3 from test 1 shows, RLE is not good at reducing the size of ordinary English sentences. This is a
huge disadvantage for a data structure that is designed to compress data. Since the encode and decode progress also
takes up some time and computing power, it is not suitable (and even harmful) for speed-sensitive services.


c. Amount of time you spent on implementing the assignment? Challenges you faced while implementing the assignment?
How did you overcome these challenges?
I spent around 15 hours implementing the assignment. I do face some minor difficulties from small things, such as reading
and writing to a raw text file. I overcame these challenges by doing reach and going through Oracle's document.
