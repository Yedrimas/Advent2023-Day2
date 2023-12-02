case class Configuration(
                          maxRed: Int,
                          maxGreen: Int,
                          maxBlue: Int
                        )

case object Configuration {

    def reduceToMinimumConfiguration(configurations: Iterable[Configuration]): Configuration = {
        configurations
          .reduce((c1, c2) => {
            Configuration(
                maxRed = Math.max(c1.maxRed, c2.maxRed),
                maxGreen = Math.max(c1.maxGreen, c2.maxGreen),
                maxBlue = Math.max(c1.maxBlue, c2.maxBlue)
            )
        })
    }
}