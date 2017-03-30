package SPL_Analysed_Examples.ArcadeGameMakerSPL.gamedefinitions

abstract class StationarySprite(
    val dimSS: Dimensions, val shapeSS: Shape) 
    extends Sprite (dimSS, shapeSS)

//I haven't use this class  at all!
class BrickPile(
    val dimBP: Dimensions, val ShapeSS: Shape = Rectangle)
    extends StationarySprite(dimBP, ShapeSS) 

class Brick(
    val dimBrick: Dimensions, val ShapeSS: Shape = Rectangle)
	extends StationarySprite(dimBrick, ShapeSS) 

class Wall(
    val dimW: Dimensions, val ShapeSS: Shape = Rectangle)
    extends StationarySprite(dimW, ShapeSS) 

class Floor(
    val dimF: Dimensions, val ShapeSS: Shape = Rectangle)
	extends StationarySprite(dimF, ShapeSS)

class Ceiling(
    val dimC: Dimensions, val ShapeSS: Shape = Rectangle)
	extends StationarySprite(dimC, ShapeSS)

class LeftWall(
    override val dimW: Dimensions) 
    extends Wall(dimW) {
  //dimW.x = 0
}
class RightWall(
    override val dimW: Dimensions) 
    extends Wall(dimW) {
  //dimW.x = 600
}
class PanelX(
    val dimPX: Dimensions, val ShapeSS: Shape = Rectangle)
    extends StationarySprite(dimPX, ShapeSS)
