// =====================================================================
// Ejercicio 6: Integración del sistema completo
// =====================================================================

object Main {
  def main(args: Array[String]): Unit = {

    // Leer diccionario
    val dictionary: List[NamedEntity] = Dictionary.loadAll()

    println(s"Diccionario cargado: ${dictionary.size} entidades.\n")

    // Leer subscripciones
    val subscriptions = FileIO.readSubscriptions()

    val allPosts: List[(String, List[String])] = subscriptions.map { url =>
      println(s"Descargando posts de: $url")
      val json = FileIO.downloadFeed(url)
      val titles = FileIO.extractPostTitles(json)
      (url, titles)
    }

    // Detecto entidades una sola vez, las muestro y las guardo
    val listaEntidades = allPosts.flatMap { case (url, titles) =>
      titles.flatMap { titulo =>
        val entidades = Analyzer.detectEntities(titulo, dictionary)
        println(Formatters.formatNERResult(titulo, entidades))
        entidades
      }
    }
    val cuenta = Analyzer.countByType(listaEntidades)
    println(s"${Formatters.formatEntityStats(cuenta)}\n")
  }
}
