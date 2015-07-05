package genetic

import util.Random

class Population(val initialSize: Integer) {
  val numChromosome: Integer = 8
  var pop: Array[Organism] = _

  val mutationRate = 0.015
  val uniformRate = 0.5

  def initialise = {
    pop = new Array[Organism](initialSize + 1)
  }

  /**
  * Populate with organisms
  */
  def populate = {
    pop = new Array[Organism](initialSize + 1)

    for( i <- 0 to initialSize ) {
      val bytes = new Array[Byte](numChromosome)
      Random.nextBytes(bytes)

      val organism = new Organism(bytes)
      pop(i) = organism
    }
  }

  def size: Integer = {
    pop.length
  }

  def fittest(evaluator: Evaluator): Organism = {
    var o: Organism = pop(0)

    for (i <- 1 to initialSize) {
      if (evaluator.fitness(pop(i)) > evaluator.fitness(o)) {
        o = pop(i)
      }
    }

    o
  }

  /**
   * todo refactor this to return a new population rather than mutate
   */
  def addOrganism(index: Integer, organism: Organism) = {
    pop(index) = organism
  }

  def evolve(evaluator: Evaluator): Population = {
    val nextGeneration = new Population(pop.size)
    nextGeneration.initialise

    // add fittest individual to next generation to ensure next gen is
    // at least as good as the previous generation
    nextGeneration.addOrganism(0, fittest(evaluator))

    for( index <- 1 to pop.size){
      val parent1: Organism = rouletteSelection(evaluator)
      val parent2: Organism = rouletteSelection(evaluator)
      val child: Organism = crossover(parent1, parent2)

      nextGeneration.addOrganism(index, mutate(child))
    }

    nextGeneration
  }

  def mutate(organism: Organism): Organism = {
    val mutatedOrganism = organism.clone

    var index: Integer = 0
    for (gene <- mutatedOrganism.genes) {
      if (Math.random <= mutationRate) {
        val newGenes = new Array[Byte](1)
        Random.nextBytes(newGenes)
        mutatedOrganism.replaceGene(index, newGenes(0))
      }

      index += 1
    }

    mutatedOrganism
  }

  def crossover(parent1: Organism, parent2: Organism): Organism = {
    val otherParentGenes = parent2.genes

    val chromosomes = new Array[Byte](otherParentGenes.length)

    println("parent1: " + parent1)
    println("parent2: " + parent2)

    var index: Integer = 0
    for (gene <- parent1.genes) {

      if (Math.random <= uniformRate) {
        chromosomes(index) = gene
      } else {
        chromosomes(index) = otherParentGenes(index)
      }
      index += 1
    }

    val child = new Organism(chromosomes)

    println("child:   " + child)

    child
  }

  /**
   * Select an organism from the population based on 'roulette wheel' selection.
   * @param evaluator
   * @return
   */
  def rouletteSelection(evaluator: Evaluator): Organism = {
    // todo algorithm
    fittest(evaluator)
  }
}
