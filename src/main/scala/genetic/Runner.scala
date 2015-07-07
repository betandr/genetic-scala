package genetic

object Runner extends App {
  val evaluator = new Evaluator
  val candidate = "0101010101010101010101010101010101010101010101010101010101010101"
  evaluator.load(candidate)

  var population = new Population(50)
  population.populate

  try {
    var generation = 1
    var fittest = evaluator.fittest(population)
    var fitness = evaluator.fitness(fittest)

    while (evaluator.fitness(fittest) < 1.0) {
      fitness = evaluator.fitness(fittest)
      println(f"generation: $generation%02d chromosome: $fittest%s fitness: $fitness%2.2f")

      population = population.evolve(true, evaluator)
      generation += 1

      fittest = evaluator.fittest(population)
    }

    println(f"generation: $generation%02d chromosome: $fittest%s fitness: $fitness%2.2f")
    println("\ncandidate:" + candidate)
    println("solution: " + evaluator.fittest(population))

  } catch {
    case e: Exception => println(e.getMessage)
  }
}
