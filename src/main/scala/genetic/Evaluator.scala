package genetic

class Evaluator {
  var solutionBytes: Array[Byte] = _

  def load(solution: String) = {
    solutionBytes = new Array[Byte](solution.length)

    for (i <- 0 to solution.length - 1) {
      solutionBytes(i) = solution.charAt(i).asDigit.toByte
    }
  }

  /**
  * Calculate an organism's fitness by comparing it to the optimal solution
  */
  def fitness(organism: Organism): Double = {
    var score: Integer = 0
    var index: Integer = 0

    for (gene <- organism.genes) {
      if (solutionBytes(index) == gene) { score += 1 }
      index += 1
    }

    val maxScore = organism.genes.size

    1.0 - ((maxScore.toDouble - score.toDouble) / 10.0)
  }
}
