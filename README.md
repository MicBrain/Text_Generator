# Text Generator

1. Introduction
2. Assignment
3. Algorithm
4. Examples


### Introduction

  This project has been assigned by the recruitment team of "Argonauts" as a "Coding Challenge" in order to complete in 48 hours. The main idea of this assignment was to create a public API that will allow users to randomly generate sensible output by generalizing patterns found in an input text. It took me about 12.5 hours in order to fully complete the whole project and test it manually. I used Java to implement this lybrary. In order to test this program I used Shakespeare's "Hamlet" to Martin Luther King's "I Have a Dream Speech".

### Asignment 

  The objective for this challenge is to design and implement a system that randomly generates sensible output by generalizing patterns found in an input text. This system should be able to produce unique sentences based on a model that I designed using the input text. Such a system could be useful for randomly generating poetry, song lyrics, or cryptic academic papers.
  The solution should:
 - Analyze the source text to build a model for randomly generated sentences.
 - Randomly generate unique sentences of user-defined length.
 
### Algorithm

   This algorithm has been developed mainly for Big Data, because the general idea is based on the size of the given data. Generator.java file provides the public API and TextGenerator.java file contains the actual implementation of the algorithm. In order to explain the logic of the algorithm I am going to show this simple example. Let's say our input.txt file contains following data:
   
                                X A B C X B L M N K X Z D P X A
                              
For simplicity let's consider that those letters are just words. Now we are going to look at all possible pairs of words: (X, A), (A, B), (B, C), (C, X), (X, B), (B, L), (L, M), (M, N), (N, K), (K, X), (X, Z), (Z, D), (D, P), (P, X), (X, A). Now let's create a mapping data structure, that will map each word to its successor and the number of pairs between the word and its successor in the whole data. In this case we will consider only the example of X.

                                X :-> (A, 2), (B, 1), (Z, 1), 
                              
 which means that (X, A) pair occurs twice in the whole data and (X, B) and (X, Z) pairs occur only once. Now we will keep a Sigma variable for our text such that Sigma = 2 + 1 + 1 = 4. As you have noticed Sigma contains information of the number of times we have a pair starting with X word. If you see that the probability of having A after X is higher than the probability of having B or Z after X, because the ration is 2:1:1 .
 
 In this algorithm it is very important of keeping the concept of ration. Let's say in our output file we will have X' instead of X, A' instead of A, B' instead of B and Z' instead of Z. However, the probability of having A' after X' still should be higher compared to the probability of having B' or X' after A'. Now, after having all this information let's associate certain values for successors of X word(A, B, Z). In order to associate those successors with certain values we will use the interval from 0, to Sigma -1. We can understand this concept using this small map:
 
                                A:-> (0, 1), B:-> (2), Z:->(3)

This reason we associated two values (0 and 1) for A is because (X, A) pair occurs twice in the input text, and one value for B and Z words, because (A, B) and (A, Z) occurs only once. I just used this convention of ordering numbers, you could start associating A with 3, 4 and go down to 0 for B and Z (doesn't really matter).

Another imortant part of this algorithm is having a function that will return a random number between the interval of lower bound and the upper bound. Now when we start creating our output file, the implementation is going to look like this:
- Take word X, get it's Sigma value
- Get a random value between the interval of 0 and Sigma - 1(let's name it random_num),
- Run through all successors of the word X and find the successor, whose associated value is equal to random_num,
- Put that successor after our word X.
- Continue these process untill you reach the n-th word from the input file, where n is a user-defined length.

   This algorithm provides very interesting results. I will discuss some of these results in the next section.

  
