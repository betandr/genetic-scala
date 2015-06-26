# genetic-scala

Basic genetic algorithm in Scala

##Process:

1. Initialisation — creating a random population
2. Evaluation — fitness is calculated for each individual in the population
3. Selection — keep the individuals with the best fit and discard the rest
4. Crossover — combine aspects of the selected individuals to make fitter offspring
5. Mutation — add randomness to genetics of the population by making small changes to an individuals genome
6. Repeat — create successive generations taking the best genetic material from ancestors
7. Termination — when an acceptable solution is found (or our computation takes too long)

##Issues

It is possible for this model to become trapped in a local optimum although this can be escaped if the local optimum is shallow enough.

![Local vs Global Optimum Solution in Genetic Algorithms](https://www.evernote.com/shard/s316/sh/0f88f651-5397-4637-963a-74cd207bc983/841789b23de4f449/res/8f55cf59-5434-4fca-ba86-9107dc027835/Screen%20Shot%202015-06-26%20at%2014.25.37.png?resizeSmall&width=832)
