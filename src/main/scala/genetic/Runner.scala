package genetic

class WorldFullException(msg: String) extends RuntimeException(msg) { }

object Runner extends App {
//  val candidate = "0100010001011110011110010011100111110111010111000101010001001100"
  val candidate = "0101010101010101010101010101010101010101010101010101010101010101"
  val solution: Array[Byte] = candidate.getBytes

  val evaluator = new Evaluator(solution)

  // TODO make this functional
  var population = new Population(50)
  population.populate

  try {
    // TODO refactor this as it's imperative
    var generation = 0
    //    todo refactor this to use 1 / (size/current) to get fitness score
    while ( {
      evaluator.fitness(population.fittest(evaluator)) < evaluator.maximumFitness
    }) {
      generation += 1
      val fittest = population.fittest(evaluator)
      println(
        "Generation: " + generation +
          " Chromosome: " + fittest +
          " Fitness: " + evaluator.fitness(fittest)
      )
      population = population.evolve(evaluator)

      if (generation > 98) throw new WorldFullException("The world is full")
    }
  } catch {
    case wfe: Exception => println(wfe.getMessage)
  }

  println("Candidate:" + candidate)
  println("Solution: " + population.fittest(evaluator))
}
