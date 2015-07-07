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

    while (evaluator.fitness(fittest) < 1.0) {
      val fitness = evaluator.fitness(fittest)
      population = population.evolve(true, evaluator)
      generation += 1

      fittest = evaluator.fittest(population)

      println(f"g: $generation%03d ch: $fittest%s f: $fitness%2.2f")
//      if (generation > 98) throw new Exception("The world is full")
    }

    println("\ncandidate: " + candidate)
    println("solution:  " + evaluator.fittest(population))

  } catch {
    case e: Exception => println(e.getMessage)
  }
}
