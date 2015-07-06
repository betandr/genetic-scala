package genetic

import util.Random

class Population(val populationSize: Integer) {
  val numChromosome: Integer = 8
  var pop: Array[Organism] = _

  val mutationRate = 0.015
  val mixingRatio = 0.5

  def initialise = {
    pop = new Array[Organism](populationSize + 1)
  }

  /**
  * Populate with organisms
  */
  def populate = {
    pop = new Array[Organism](populationSize + 1)

    for( i <- 0 to populationSize ) {
      val bytes = new Array[Byte](numChromosome)
      Random.nextBytes(bytes)

      val organism = new Organism(bytes)
      pop(i) = organism
    }
  }

  def size: Integer = {
    pop.length
  }

  override def toString: String = {
    val sb: StringBuilder = new StringBuilder

    sb.append("[")
    for (organism <- pop) {
      sb.append(organism + ", ")
    }
    sb.append("]")

    sb.toString
  }

  def fittest(evaluator: Evaluator): Organism = {
    var o: Organism = pop(0)

    for (i <- 1 to populationSize) {
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

  def evolve(elitist: Boolean, evaluator: Evaluator): Population = {
    val nextGeneration = new Population(pop.size)
    nextGeneration.initialise

    var offset = 0

    if (elitist) {
      val eliteOrganism = fittest(evaluator)
      nextGeneration.addOrganism(0, mutate(eliteOrganism))
      offset += 1
    }

    for(index <- offset to pop.size) {
      val parent1: Organism = tournamentSelection(evaluator)
      val parent2: Organism = tournamentSelection(evaluator)
      val child: Organism = crossover(parent1, parent2)

      nextGeneration.addOrganism(index, mutate(child))
    }

    nextGeneration
  }

  def mutate(organism: Organism): Organism = {
    val c: Array[Byte] = organism.genes

    for(index <- 1 to c.length - 1) {

      if (Math.random <= mutationRate) {
        val newGenes = new Array[Byte](1)
        Random.nextBytes(newGenes)
        c(index) = newGenes(0)
      }
    }

    new Organism(c)
  }

  /**
   * Use one-point crossover to create a child organism
   */
  def crossover(parent1: Organism, parent2: Organism): Organism = {
    val cutPoint = Random.nextInt(parent1.genes.length)

    new Organism(
      parent1.genes.slice(0, cutPoint) ++
      parent2.genes.slice(cutPoint, parent1.genes.length)
    )
  }

  /**
   * Select an organism from the population based on 'tournament' selection.
   * @param evaluator
   * @return
   */
  def tournamentSelection(evaluator: Evaluator): Organism = {
    val numberOfRounds = 5

    val tournament = new Population(numberOfRounds)
    tournament.initialise

    for (i <- 0 to numberOfRounds) {
      val randomOrganism = pop(Random.nextInt(populationSize))
      tournament.addOrganism(i, randomOrganism)
    }

    tournament.fittest(evaluator)
  }
}
