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
                              X A B C X B L M N K X Z D P
For simplicity let's consider that those letters are just words.


  
