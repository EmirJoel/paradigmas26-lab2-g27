// =====================================================================
// Ejercicios 3 y 5: Detección y conteo de entidades
// =====================================================================

/**
 * Responsable de detectar entidades nombradas en texto libre y
 * producir estadísticas sobre ellas.
 */
object Analyzer {

  /**
   * Detecta las entidades del diccionario que aparecen en el texto dado.
   *
   * @param text       texto a analizar (ej: título o cuerpo de un post)
   * @param dictionary lista de entidades conocidas (cargadas desde los diccionarios)
   * @return lista de entidades cuyo texto aparece en el texto analizado
   */
  def detectEntities(text: String, dictionary: List[NamedEntity]): List[NamedEntity] = {
    // Normalizamos el texto: minúsculas y cambiamos puntuación por espacios
    val normalizedText = s" ${text.toLowerCase.replaceAll("[^a-z0-9+]+", " ")} "

    dictionary.filter { ent =>
      // Normalizamos la entidad igual que el texto
      val normalizedEntity = s" ${ent.text.toLowerCase.replaceAll("[^a-z0-9+]+", " ")} "
      normalizedText.contains(normalizedEntity)
    }.distinct // distinct para evitar contar la misma entidad varias veces si aparece repetida 
  }

  /**
   * Cuenta cuántas entidades de cada tipo fueron detectadas.
   *
   * @param entities lista de entidades detectadas
   * @return mapa de entityType → cantidad de apariciones
   */
  def countByType(entities: List[NamedEntity]): Map[String, Int] = {
    //Tomando la lista de entidades las agrupamos en un diccionario a traves de grounBy y de alli tomamos la longitud  de list para saber cuantas entidades tienen ese entityType
    entities.groupBy(entity => entity.entityType).map { case (entity, list) => (entity, list.size)}
  } 
}