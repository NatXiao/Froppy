package ch.hevs.gdx2d

import ch.hevs.gdx2d.components.bitmaps.BitmapImage
import com.badlogic.gdx.math.Vector2

class Lily(pos: Vector2) extends AnimatedObject(pos){
  override var img: BitmapImage = ???
  var r : Int = 40
  var rotationSpeed : Int = ???
  var rotationDirection : Boolean = true
}
