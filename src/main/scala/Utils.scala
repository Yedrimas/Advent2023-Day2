case object Utils{
    def readLinesFromRsc(resourceName: String): Iterable[String] = {
        scala
          .io
          .Source
          .fromResource(resourceName)
          .mkString
          .split("\n")
    }

}
