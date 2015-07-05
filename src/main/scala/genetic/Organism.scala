package genetic

class Organism(val chromosome: Array[Byte]) {

  def genes = chromosome

  override def toString: String = {

    val sb: StringBuilder = new StringBuilder

    for (gene <- chromosome) {
      val s: String = String.format("%8s", Integer.toBinaryString(gene & 0xFF)).replace(' ', '0');
      sb.append(s)
    }

    sb.toString
  }

  def replaceGene(index: Integer, gene: Byte) = {
    genes(index) = gene
  }

  override def clone: Organism = {
    new Organism(chromosome)
  }
}
