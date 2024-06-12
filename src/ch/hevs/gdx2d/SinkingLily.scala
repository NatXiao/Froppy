package ch.hevs.gdx2d

import ch.hevs.gdx2d.components.bitmaps.BitmapImage
import ch.hevs.gdx2d.lib.GdxGraphics
import com.badlogic.gdx.Gdx
import com.badlogic.gdx.math.{Interpolation, Vector2}
import window.ScreenSelector

class SinkingLily(var positi : Vector2, nberLily : Int, rotation : Boolean = true) extends Lily(positi, nberLily) {
  var sunk : Boolean = false
  var fileName: String = "data/images/sinkinglily.png"
  if (ScreenSelector.skin) {
    fileName = "data/images/ISC_logo_sinking.png"
  }
  override val img = new BitmapImage(fileName)
  var alpha = 0.3f
  //var numbTurn : Int = _

  override def onGraphicsRender(g : GdxGraphics) = {
    super.onGraphicsRender(g)
    //println(frogIsOn)
    //if(frogIsOn){
      var numbTurn = 0
      if (direction == 0) {
        numbTurn += 1
      }
      if (numbTurn >= 2) {
        sunk = true
      }
      println("direction : " + direction)
      println("nbeturn : " + numbTurn)

    if(sunk){
      currentTime += Gdx.graphics.getDeltaTime
      var animationTimeDeath: Float = currentTime / ANIMATION_LENGTH
      var alpha = Interpolation.linear.apply(0.9f, 0, animationTimeDeath)
      g.drawAlphaPicture(positi.x, positi.y,direction, scale, alpha, img)
      if (alpha <= 0) {
        numbTurn = 0
      }
    }
    //}
  }
}
