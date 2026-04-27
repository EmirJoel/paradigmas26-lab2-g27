// =====================================================================
// Ejercicio 2: Cargar diccionarios de entidades
// =====================================================================

/**
 * Responsable de cargar colecciones de entidades nombradas desde archivos.
 *
 * Un diccionario es un archivo de texto plano donde cada línea contiene
 * el nombre de una entidad conocida del mismo tipo.
 *
 * Ejemplo — data/people.txt:
 *   Martin Odersky
 *   Alan Turing
 *   Ada Lovelace
 *
 * Ejemplo — data/languages.txt:
 *   Scala
 *   Python
 *   Haskell
 */
object Dictionary {

  /**
   * Lee un archivo de diccionario y crea una lista de entidades del tipo indicado.
   *
   * @param filePath   ruta al archivo de diccionario (ej: "data/people.txt")
   * @param entityType tipo de entidad: "Person", "University", "ProgrammingLanguage", etc.
   * @return lista de NamedEntity del tipo correspondiente
   */
  def loadFromFile(filePath: String, entityType: String): List[NamedEntity] = {
    val lines = FileIO.readLines(filePath)
    entityType match {
      case "Person" => lines.map(new Person(_))
      case "Organization" => lines.map(new Organization(_))
      case "University" => lines.map(new University(_))
      case "Place" => lines.map(new Place(_))
      case "Technology" => lines.map(new Technology(_))
      case "ProgrammingLanguage" => lines.map(new ProgrammingLanguage(_))
      case _ => throw new IllegalArgumentException(s"Tipo de entidad desconocido: $entityType")
    }
  }

  /**
   * Carga todos los diccionarios disponibles y combina sus entidades.
   *
   * @return lista con todas las entidades de todos los diccionarios
   */
  def loadAll(): List[NamedEntity] = {
    val config = List(
    ("data/people.txt", "Person"),
    ("data/universities.txt", "University"),
    ("data/languages.txt", "ProgrammingLanguage"),
    ("data/organizations.txt", "Organization"),
    ("data/places.txt", "Place")
    )

    config.flatMap{case (path, entityType) =>
      loadFromFile(path, entityType)
    }
  }
}
