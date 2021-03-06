# Text Generator

1. Introduction
2. Assignment
3. Algorithm
4. Examples


### Introduction

  This project has been assigned by the recruitment team of "Argonauts" as a "Coding Challenge" in order to complete in 48 hours. The main idea of this assignment was to create a public API that will allow users to randomly generate sensible output by generalizing patterns found in an input text. It took me about 12.5 hours in order to fully complete the whole project and test it manually. I used Java to implement this lybrary. In order to test this program I used Shakespeare's "Hamlet" and Martin Luther King's "I Have a Dream Speech".

### Asignment 

  The objective for this challenge is to design and implement a system that randomly generates sensible output by generalizing patterns found in an input text. This system should be able to produce unique sentences based on a model that I designed using the input text. Such a system could be useful for randomly generating poetry, song lyrics, or cryptic academic papers.
  The solution should:
 - Analyze the source text to build a model for randomly generated sentences.
 - Randomly generate unique sentences of user-defined length.
 
### Algorithm

   This algorithm has been developed mainly for Big Data, because the general idea is based on the size of the given source. Generator.java file provides the public API and TextGenerator.java file contains the actual implementation of the algorithm. In order to explain the logic of the algorithm I am going to show this simple example. Let's assume our input.txt file contains following data:
   
                                X A B C X B L M N K X Z D P X A
                              
For simplicity let's consider that those letters are just words. Now we are going to look at all possible successive pairs of words: (X, A), (A, B), (B, C), (C, X), (X, B), (B, L), (L, M), (M, N), (N, K), (K, X), (X, Z), (Z, D), (D, P), (P, X), (X, A). Now let's create a mapping data structure, that will map each word to its successor and the number of pairs between that word and its successor in the whole data. Here we will consider only the example of X.

                                X :-> (A, 2), (B, 1), (Z, 1), 
                              
 which means that (X, A) pair occurs twice in the whole data and (X, B) and (X, Z) pairs occur only once. Now we will keep a Sigma variable for our text such that Sigma = 2 + 1 + 1 = 4. As you have noticed Sigma contains information about the number of successive pairs starting with X word. If you have noticed  the probability of having A after X is higher than the probability of having B or Z after X, because the ratio is 2:1:1 .
 
 In this algorithm it is very important of use the concept of the ratio. Let's say in our output file we will have X' instead of X, A' instead of A, B' instead of B and Z' instead of Z. However, the probability of having A' after X' still should be higher compared to the probability of having B' or X' after A'(A' should occur twice, and B' and Z' should occur only once each). Now, after having all this information let's associate certain values for successors of X word(A, B, Z). In order to associate those successors with certain values we will use the interval from 0 to Sigma - 1. We can understand this concept using this small map:
 
                                A:-> (0, 1), B:-> (2), Z:->(3)

The reason we associated two values (0 and 1) for A is because (X, A) pair occurs twice in the input text, and one value for B and Z words, because (X, B) and (X, Z) occurs only once. This is just my convention of ordering numbers. You could start associating A with 3, 4 and go down to 0 for B and Z (doesn't really matter).

Another imortant part of this algorithm is having a function that will return a random number between the interval of lower bound and the upper bound. Now when we start creating our output file, the implementation is going to look like this:
- Take word X, get it's Sigma value
- Get a random value between the interval of 0 and Sigma - 1(let's name it random_num),
- Run through all successors of the word X and find the successor, whose associated value is equal to random_num,
- Put that successor after our word X,

Continue this process untill you reach the n-th word from the input file, where n is a user-defined length. This algorithm provides very interesting results. I will show some of these results in the next section.

### Examples
 
 Example #1: Some portion from  Martin Luther King's I Have a Dream Speech:
 
 **Input file:**
 
```
  I am happy to join with you today in what will go down in history as the greatest demonstration for freedom in the history of our nation. Five score years ago, a great American, in whose symbolic shadow we stand today, signed the Emancipation Proclamation.
```

**Output file:**
```
  Be say happy to believe with the have in the will be back in the of usual. Negro demonstration for many ring, the chains as our nation And score years later, a state American, in the symbolic shadow we face up have the mighty Proclamation.
```

 Example #2: Some portion from  Shakespeare's Hamlet:
 
 **Input file:**
 ```
Bernardo. Who's there? Francisco. Nay, answer me. Stand and unfold yourself. Bernardo. Long live the King!
Francisco. Bernardo? 5
```

**Output file:**
 ```
The How there? Horatio. Bernardo? do no? Stand and bulwark yourself. I He. stay'd and matter. Francisco. Francisco. 5
```

  
