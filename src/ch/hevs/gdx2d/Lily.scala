package ch.hevs.gdx2d

import ch.hevs.gdx2d.components.bitmaps.BitmapImage
import com.badlogic.gdx.math.Vector2

class Lily(pos: Vector2) extends AnimatedObject(pos){
  override var img: BitmapImage = ???
  var rotationSpeed : Int = ???
  var rotationDirection : Int = ???
  var distance : Int = ???
  final private val ANIMATION_LENGTH: Float = 2f // Animation length (in seconds)
  final private val MIN_ANGLE: Float = -20
  final private val MAX_ANGLE: Float = 20
}
