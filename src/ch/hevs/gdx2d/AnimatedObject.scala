package ch.hevs.gdx2d

import ch.hevs.gdx2d.components.bitmaps.BitmapImage
import ch.hevs.gdx2d.lib.GdxGraphics
import com.badlogic.gdx.math.Vector2
import window.ScreenSelector

abstract class AnimatedObject(var position : Vector2) {
  val img : BitmapImage
  var currentTime: Float = _
  var direction: Int
  val ANIMATION_LENGTH: Float = 0.6f
  var state : Vector2 = _
  val scale : Float

  def isAtDest(destination : Float, currentPosition : Float, state :Float ) : Boolean = {
    val sign = destination - state
    return currentPosition * sign >= destination * sign
  }
  def onGraphicsRender(g: GdxGraphics): Unit = {}
}