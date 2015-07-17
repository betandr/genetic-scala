package genetic

trait Evolver {

  def run(candidate: String, evaluator: Evaluator, population: Population): Organism = {

    def run(pop: Population, generation: Int): Organism = {
      val fittest = evaluator.fittest(pop)
      val fitness = evaluator.fitness(fittest)

      println(f"generation: $generation%02d chromosome: $fittest%s fitness: $fitness%2.2f")

      if (fitness >= 1.0)
        fittest
      else
        run(
          pop.evolve(true, evaluator),
          generation + 1
        )
    }

    run(population, 1)
  }
}

object Runner extends App with Evolver {
  val evaluator = new Evaluator
  val candidate = "0101010101010101010101010101010101010101010101010101010101010101"
  evaluator.load(candidate)

  var population = new Population(50)
  population.populate

  val solution: Organism = run(candidate, evaluator, population)

  println("\ncandidate:  " + candidate)
  println("solution:   " + solution)
}
