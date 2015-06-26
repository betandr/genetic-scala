package genetic

object Runner extends App {
  val candidate = "0100010001011110011110010011100111110111010111000101010001001100"
  val solution: Array[Byte] = candidate.getBytes

  val evaluator = new Evaluator(solution)
  val evolution = new Evolution

  // TODO make this functional
  var population = new Population(50)
  population.populate

  // TODO refactor this as it's imperative
  var generation = 0
  while({population.fittest.fitness < evaluator.maximumFitness}) {
    generation += 1
    println("Gen: " + generation + " Fit: " + population.fittest)
    population = evolution.evolve(population)
  }

  println("Candidate:" + candidate)
  println("Solution: " + population.fittest)
}
