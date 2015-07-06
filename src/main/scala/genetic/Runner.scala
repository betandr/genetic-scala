package genetic

object Runner extends App {
  val candidate = "0101010101010101010101010101010101010101010101010101010101010101"
  val solution: Array[Byte] = candidate.getBytes

  val evaluator = new Evaluator(solution)

  var population = new Population(50)
  population.populate

  val initial = population.fittest(evaluator)

  try {
    var generation = 0
    while (evaluator.fitness(population.fittest(evaluator)) < 1) {
      generation += 1
      val fittest = population.fittest(evaluator)
      val fitness = evaluator.fitness(fittest)

      println(f"gen: $generation%02d chro: $fittest%s fit: $fitness%2.2f")

      // perform 'elitist' evolution
      population = population.evolve(true, evaluator)

      if (generation > 98) throw new Exception("The world is full")
    }
  } catch {
    case e: Exception => println(e.getMessage)
  }

  println("candidate: " + candidate)
  println("initial:   " + initial)
  println("solution:  " + population.fittest(evaluator))
}
