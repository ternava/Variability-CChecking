package math.dsl

import scala.collection.mutable.ArrayBuffer
import scala.reflect.runtime.universe._

/**
  * Created by Tërnava on 6/5/2016.
  */

/* Documentation of variable asset elements of a Code Asset */
object asset {

  val variant_asset_elements: ArrayBuffer[Symbol] = new ArrayBuffer()

  def apply(assetElement: Symbol): Symbol = {
    variant_asset_elements += assetElement
    assetElement
  }

}


