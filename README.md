# genetic-scala

Basic genetic algorithm implementation in Scala

##Running

```
sbt run
```

##Process:

###1. Initialisation

Creates a random population of 50 organisms each with a 64-bit chromosome, such as:

```
010110101001010111010100010100101111001001101010101110011101011
```

###2. Evaluation
 
Fitness is calculated for each individual in the population by comparing each bit of the candidate solution with each 
bit in the organism's chromosome. This gives a score which is then re-oriented to be a float between 0.0 and 1.0, using:

```
1.0 / ((M - S) / 100)
```

...where M is the maximum score and S is the organism's score.

###3. Selection

Organisms are selected based on [tournament selection](https://en.wikipedia.org/wiki/Tournament_selection); 10 rounds 
are performed, each picking a random organism from the population. Then the fittest organism is selected from the 
tournament. This picks a strong organism but also allows for a diverse population. 

By evolving the population in 'elitist' mode, the strongest individual is kept from the previous population generation 
and added to the new generation. This ensures the next generation is at least as fit as the fittest in the previous 
generation. 

###4. Crossover

To create a new organism, two parents are selected using tournament selection then a child is created using 
[uniform crossover](https://en.wikipedia.org/wiki/Crossover_(genetic_algorithm)#Uniform_Crossover_and_Half_Uniform_Crossover)
which uses a mixing ratio of 0.5, thereby taking an roughly equal number of genes from each organism. 

###5. Mutation

Randomness is added to the genetics of the population by making small changes to an organism's chromosome. A mutation rate
of 0.015 is used to change a low enough proportion of the genes to not adversely affect already fit organisms but high 
enough to introduce some healthy change in the population's genome to move closer to a solution. 

###6. Repeat

The process is repeated by evolving the population and checking the fittest in that 
[next generation](https://en.wikipedia.org/wiki/Star_Trek:_The_Next_Generation).

##7. Termination

The process is repeated until we find an organism with a fitness of 1.0 as we have an understandable 'solution'—so our 
fitness function can find a definite score.
