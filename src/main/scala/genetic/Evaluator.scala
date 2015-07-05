package genetic

class Evaluator(solution: Array[Byte]) {

  /**
  * Calculate an organism's fitness by comparing it to the optimal solution
  */
  def fitness(organism: Organism): Integer = {
    var score: Integer = 0
    var index: Integer = 0

    for (gene <- organism.genes) {
      if (solution(index) == gene) {
        score += 1
      }
      index += 1
    }
    score
  }

  /**
  * The maximum possible fitness from the solution
  */
  def maximumFitness: Integer = {
    solution.size
  }
}
