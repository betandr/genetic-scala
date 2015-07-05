package genetic

object Runner extends App {
  val candidate = "0100010001011110011110010011100111110111010111000101010001001100"
  val solution: Array[Byte] = candidate.getBytes

  val evaluator = new Evaluator(solution)

  // TODO make this functional
  var population = new Population(50)
  population.populate

  object WorldFullException extends Exception { }

  // TODO refactor this as it's imperative
  var generation = 0
  while({evaluator.fitness(population.fittest(evaluator)) < evaluator.maximumFitness}) {
    generation += 1
    val fittest = population.fittest(evaluator)
    println(
      "Generation: " + generation +
      " Chromosome: " + fittest +
      " Fitness: " + evaluator.fitness(fittest)
    )
    population = population.evolve(evaluator)

    if (generation > 100) throw WorldFullException
  }

  println("Candidate:" + candidate)
  println("Solution: " + population.fittest(evaluator))
}
