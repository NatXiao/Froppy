package ch.hevs.gdx2d

import ch.hevs.gdx2d.components.bitmaps.BitmapImage
import com.badlogic.gdx.math.Vector2

abstract class AnimatedObject(var pos : Vector2) {
  var img : BitmapImage

  def isAtDest(destination : Float, currentPosition : Float, state :Float ) : Boolean = {
    val sign = destination - state
    return currentPosition * sign >= destination * sign
  }
}