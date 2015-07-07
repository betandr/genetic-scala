# Genetic Scala

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
 
Fitness is calculated for each organism in the population by comparing each bit of the candidate solution with each 
bit in the organism's chromosome. This gives a score which is then re-oriented to be a float between 0.0 and 1.0, using:

```
1.0 / ((m - s) / 100)
```

...where m is the maximum score and s is the organism's score.

###3. Selection

Organisms are selected based on 
[stochastic universal sampling](https://en.wikipedia.org/wiki/Stochastic_universal_sampling) which can avoid issues 
with approaches such as roulette wheel selection when dealing with organisms that have very high fitness scores 
saturating the candidate space.

10 rounds are performed, each picking a random organism from the population. Then the fittest organism is selected from 
the  tournament. This picks a strong organism but also allows for a diverse population as there is a potential for a 
weaker organism to be selected which could contain genetic information useful for later recombination.

By evolving the population in 'elitist' mode, the strongest organism is kept from the previous population generation 
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

###7. Termination

The process is repeated until we find an organism with a fitness of 1.0 (as we have an understandable solution) so our 
fitness function can find a definite score rather than just a best fit.

##Results

Generally a result is found within 10 - 20 generations. Output shows the generation, the chromosome of the fittest 
organism in that generation and the fitness. Finally the candidate solution is displayed along with the solution found
from the fittest organism.

```
generation: 01 chromosome: 1011111111000001110110000100001101100101011101101110010011010101 fitness: 0.75
generation: 02 chromosome: 1001011111010101110111000100011101110101011110101110010111011101 fitness: 0.80
generation: 03 chromosome: 0000010111010101000101010101000001100101000101011100010001111101 fitness: 0.86
generation: 04 chromosome: 0001010101010101110101010101000101100101010101011100010011110101 fitness: 0.90
generation: 05 chromosome: 0001010101010100100101010100010101010101010101010111010101110101 fitness: 0.93
generation: 06 chromosome: 0001010101010100100101010100010101010101010101010111010101110101 fitness: 0.93
generation: 07 chromosome: 0001010101010101010101010101011101010101010101010100010101010101 fitness: 0.97
generation: 08 chromosome: 0001010101010101010101010101011101010101010101010100010101010101 fitness: 0.97
generation: 09 chromosome: 0101010101010101000101010101010101010101010101010101010101010101 fitness: 0.99
generation: 10 chromosome: 0101010101010101010101010101010101010101010101010101010101010101 fitness: 0.99

candidate:  0101010101010101010101010101010101010101010101010101010101010101
solution:   0101010101010101010101010101010101010101010101010101010101010101
```