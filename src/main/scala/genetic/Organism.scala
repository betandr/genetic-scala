package genetic

class Organism {
  var genes: Array[Byte] = _

  def fitness = {
    1
  }

  def gene: Byte = {
    return 1
  }

  override def toString: String = {
    "0101010101010101010101010101010101010101010101010101010101010101"
  }
}
