package genetic

object Runner extends App {
  val evaluator = new Evaluator
  val candidate = "0101010101010101010101010101010101010101010101010101010101010101"
  evaluator.load(candidate)

  var population = new Population(50)
  population.populate

  try {
    var generation = 1
    while (evaluator.fitness(population.fittest(evaluator)) < 1.0) {

      val fittest = population.fittest(evaluator)
      val fitness = evaluator.fitness(fittest)

      println(f"g: $generation%03d c: $fittest%s f: $fitness%2.2f")

      // perform 'elitist' evolution
      population = population.evolve(true, evaluator)

      generation += 1
//      if (generation > 98) throw new Exception("The world is full")
    }

    println("generation: " + generation)
    println("candidate: " + candidate)
    println("solution:  " + population.fittest(evaluator))

  } catch {
    case e: Exception => println(e.getMessage)
  }
}
