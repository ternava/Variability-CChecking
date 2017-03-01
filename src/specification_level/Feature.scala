package specification_level

/**
  * Created by Tërnava on 2/28/2017.
  */

class feature(val name: String)

object feature {
  def apply(name: String) = new feature(name)
}
