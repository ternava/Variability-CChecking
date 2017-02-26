package MicrowaveOvenSPL.oven

import scala.collection.mutable.Map

/* object Variability {

  /* Importing the DSL implementation */
  import dsl_variability_documenting._
  import scala.reflect.runtime.universe._

  /* Recording / tagging the variant elements of this module / asset */
  val assetLanguage   = Asset(typeOf[Language].typeSymbol)
  val assetEnglish    = Asset(typeOf[English.type].termSymbol)
  val assetFrench     = Asset(typeOf[French.type].termSymbol)
  val assetItalian    = Asset(typeOf[Italian.type].termSymbol)
  val assetAlbanian   = Asset(typeOf[Albanian.type].termSymbol)
  val assetGerman     = Asset(typeOf[German.type].termSymbol)
  val assetSpanish    = Asset(typeOf[Spanish.type].termSymbol)

  /* Similarly we can record the val-s, var-s, and def-s,
  * e.g.: supposing that a VP or variant is implemented by the method "kingOfDisplay"
  * val assetDisplay = Asset(typeOf[DisplayFeature].member(TermName("kindOfDisplay")).asMethod)
  * */

  /* Naming VPs and Vs, and associating / connecting them with their
  * corresponding variant elements (Classes, Traits, Objects, Methods,
  * Val-s, Var-s) of this module */
  val vp_language     = VariationPoint(assetLanguage)
  val english         = Variant(assetEnglish)
  val french          = Variant(assetFrench)
  val italian         = Variant(assetItalian)
  val albanian        = Variant(assetAlbanian)
  val german          = Variant(assetGerman)
  val spanish         = Variant(assetSpanish)


  /* Finally: This is where the DSL will be used mostly and
  * the variability of this module will be documented using it. */
  val variability_module_a =
    vp_language isA
      List(english, french, italian, albanian, german, spanish)

}
 */
//Variation Point
sealed trait Language

//One variant
case object English extends Language {
  val messages: Map[String, String] =
    Map("M01" -> "Ready for cooking - press START",
      "M02" -> "Close the door to start cooking",
      "M03" -> "Put an item and Close the door",
      "M04" -> "Give the baking time...",
      "M05" -> "Baking...",
      "M06" -> "Idle...",
      "M07" -> "Baking is interrupted!",
      "M08" -> "Baking has finished",
      "M09" -> "Item is set",
      "M10" -> "Item is removed",
      "M11" -> "Cannot remove Item with a closed door",
      "M12" -> "Cannot put Item with a closed door",      
      "M13" -> "Unknown ERROR!")
}
//Another variant
case object French extends Language {
  val messages: Map[String, String] =
    Map("M01" -> "Pr�t pour la cuisson - presse START",
      "M02" -> "Fermez la porte pour commencer la cuisson",
      "M03" -> "Mettez un �l�ment et Fermez la porte",
      "M04" -> "Donnez le temps de cuisson...",
      "M05" -> "Cuisson...",
      "M06" -> "Au repos...",
      "M07" -> "La cuisson est interrompu!",
      "M08" -> "La cuisson soit termin�e",
      "M09" -> "�l�ment est r�gl�",
      "M10" -> "�l�ment est supprim�",
      "M11" -> "Vous ne pouvez pas retir� Point avec une porte ferm�e",
      "M12" -> "Vous ne pouvez pas mettre l'article avec une porte ferm�e",      
      "M13" -> "Unknown ERROR!")
}
//Another variant
case object Italian extends Language {
  val messages: Map[String, String] =
    Map("M01" -> "Pronto per la cottura -stampa START",
      "M02" -> "Chiudere la porta per iniziare la cottura",
      "M03" -> "Mettete una voce e chiudere la porta",
      "M04" -> "Dare il tempo di cottura...",
      "M05" -> "Cottura al forno...",
      "M06" -> "Inattivo...",
      "M07" -> "Baking � interrotto!",
      "M08" -> "Baking ha terminato",
      "M09" -> "Elemento � impostato",
      "M10" -> "Item viene rimosso",
      "M11" -> "Impossibile rimuovere Item con una porta chiusa",
      "M12" -> "Impossibile mettere Item con una porta chiusa",      
      "M13" -> "ERRORE sconosciuto!")
}
//Another variant
case object Albanian extends Language {
  val messages: Map[String, String] =
    Map("M01" -> "Gati p�r gatim - shtyp START",
      "M02" -> "Mbylle der�n p�r t� filluar gatimi",
      "M03" -> "Vendoseni pjekurin�n dhe mbylleni der�n",
      "M04" -> "Jepeni koh�n e gatimit...",
      "M05" -> "Duke u gatuar...",
      "M06" -> "Bosh...",
      "M07" -> "Gatimi �sht� nd�prer�!",
      "M08" -> "Gatimi p�rfundoi",
      "M09" -> "Pjekurina �sht� vendosur",
      "M10" -> "Pjekurina �sht� larguar",
      "M11" -> "S'mund ta nxjer�sh pjekurin�n me der� mbyllur",
      "M12" -> "S'mund ta vendos�sh pjekurin�n me der� mbyllur",      
      "M13" -> "GABIM i panjohur!")
}
//Another variant
case object German extends Language {
  val messages: Map[String, String] =
    Map("M01" -> "Bereit f�r das kochen - presse START",
      "M02" -> "Schlie�en Sie die T�r, um den Garvorgang zu starten",
      "M03" -> "Setzen Sie einen Artikel aus und Schlie�en Sie die T�r",
      "M04" -> "Geben Sie die Backzeit...",
      "M05" -> "Backen...",
      "M06" -> "Unt�tig...",
      "M07" -> "Back unterbrochen!",
      "M08" -> "Backen fertig ist",
      "M09" -> "Artikel eingestellt",
      "M10" -> "Artikel wird entfernt",
      "M11" -> "Kann nicht Artikel mit einer geschlossenen T�r entfernen",
      "M12" -> "K�nnen nicht Artikel mit einer geschlossenen T�r",      
      "M13" -> "Unbekannter FEHLER!")
}
//Another variant
case object Spanish extends Language {
  val messages: Map[String, String] =
    Map("M01" -> "Preparado para cocinar - prensa START",
      "M02" -> "Cierre la puerta para empezar a cocinar",
      "M03" -> "Ponga un elemento y cerrar la puerta",
      "M04" -> "Dar el tiempo de cocci�n...",
      "M05" -> "Cocci�n...",
      "M06" -> "Ocioso...",
      "M07" -> "La cocci�n se interrumpe!",
      "M08" -> "Haya terminado de hornear",
      "M09" -> "El art�culo se establece",
      "M10" -> "Se retira del art�culo",
      "M11" -> "No se puede eliminar de art�culos con una puerta cerrada",
      "M12" -> "No se puede poner de art�culos con una puerta cerrada",      
      "M13" -> "ERROR desconocido!")
}

/* The configuration class for variants! */
class LanguageFeature {
  /*The default feature of Language */
  private var language: Language = English
  def chosen(lang: Language) = language = lang
  def displayMessage(msg: String) = language match {
    case English => English messages msg
    case French => French messages msg 
    case Italian => Italian messages msg 
    case Albanian => Albanian messages msg
    case German => German messages msg
    case Spanish => Spanish messages msg
  }
}

