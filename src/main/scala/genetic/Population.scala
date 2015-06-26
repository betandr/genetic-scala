package genetic

class Population(val initialSize: Integer) {
  var pop: Array[Organism] = _

  /**
  * Populate with organisms
  * todo refactor this to functional!
  */
  def populate {
    pop = new Array[Organism](initialSize + 1)

    for( i <- 0 to initialSize ) {
      val organism = new Organism
      pop(i) = organism
    }
  }

  def size: Integer = {
    pop.length
  }

  def fittest: Organism = {
    pop(0)
  }
}
