a. Height  of  the  Huffman  Tree  in  terms  of  n,  where  n  is  the  number  of  unique
characters in the string. Time Complexity of encode() and decode() methods
in terms of Big-O notation.

The time complexity should be O(nlogn) for both encode() and decode() methods.


b. What are the advantages and disadvantages of using Huffman Coding? Do you
think using a simple Queue (FIFO) implementation for Huffman encoding will
improve or deteriorate the compression ratio ?

Compared to the compression algorithm we implemented in our last assignment, Huffman Coding is doing a far better job
compressing the String. Such an algorithm allows the developers to better compress the String, so it takes fewer
resources to deliver the message through the network. One disadvantage is the time complexity of encoding and decoding.
As the String size grows, it will take more resources to encode the String and decode the message. I believe using a
Simple Queue will deteriorate the compression ratio. Since the Queue is not "sorted," each character may have a longer
code, thus worsening the compression ratio.


c. Amount of time you spent on implementing the assignment. Challenges you faced
while implementing the assignment? How did you overcome these challenges?

I spent around 30 hours implementing this assignment. In general, I feel that this assignment's descriptions are a bit
vague, and I also have to spend a lot of time learning the Huffman Tree Node before I can even start working on this
assignment. This assignment, in general, has many challenging parts, especially regarding how to encode the provided
String. I overcome these challenges by re-watching your videos and rewinding your PPTs.

