package ch.hevs.gdx2d

import ch.hevs.gdx2d.components.bitmaps.BitmapImage
import ch.hevs.gdx2d.lib.GdxGraphics
import com.badlogic.gdx.Gdx
import com.badlogic.gdx.math.{Interpolation, Vector2}
import window.ScreenSelector

class SinkingLily(var positi : Vector2, nberLily : Int, rotation : Boolean = true) extends Lily(positi, nberLily, rotation) {
  var sunk : Boolean = false
  var fileName_sink: String = "data/images/sinkinglily.png"
  if (ScreenSelector.skin) {
    fileName = "data/images/ISC_logo_sinking.png"
  }
  var imag : BitmapImage = new BitmapImage(fileName_sink)
  //override var img = new BitmapImage(fileName_sink)
  var alpha = 0.3f
  var dir1 = -1
  var numbTurn : Int = 0

  override def onGraphicsRender(g : GdxGraphics) = {
    g.drawTransformedPicture(posi.x, posi.y, direction, 2, imag)
    if(sunk){
      currentTime += Gdx.graphics.getDeltaTime
      var animationTimeDeath: Float = currentTime / ANIMATION_LENGTH
      var alpha = Interpolation.linear.apply(0.9f, 0, animationTimeDeath)
      g.drawAlphaPicture(positi.x, positi.y,direction, 2, alpha, imag)
      if (alpha <= 0) {
        numbTurn = 0
      }
    }
    else{
      super.onGraphicsRender(g)
      g.drawTransformedPicture(posi.x, posi.y, direction, 2, imag)
    }
  }
}
