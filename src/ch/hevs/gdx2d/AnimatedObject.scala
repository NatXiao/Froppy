package ch.hevs.gdx2d

import ch.hevs.gdx2d.components.bitmaps.BitmapImage
import com.badlogic.gdx.math.Vector2
import window.ScreenSelector

abstract class AnimatedObject(var pos : Vector2) {
  var img : BitmapImage
  var currentTime: Float = 0
  val ANIMATION_LENGTH: Float = 0.5f
  var state : Vector2 = _

  def isAtDest(destination : Float, currentPosition : Float, state :Float ) : Boolean = {
    val sign = destination - state
    return currentPosition * sign >= destination * sign
  }

  def refactorImage(bitmapImage: BitmapImage) : Float= {
    ScreenSelector.SCREEN_HEIGHT
  }
}