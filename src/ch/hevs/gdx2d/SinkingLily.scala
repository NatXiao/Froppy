package ch.hevs.gdx2d

import ch.hevs.gdx2d.components.bitmaps.BitmapImage
import ch.hevs.gdx2d.lib.GdxGraphics
import com.badlogic.gdx.math.Vector2

class SinkingLily(var positi : Vector2, nberLily : Int, rotation : Boolean = true) extends Lily(positi, nberLily, rotation) {
  private val nbeTurnMAX : Int = 2
  var sunk : Boolean = false
  var imag : BitmapImage = new BitmapImage("data/images/sinkinglily.png")
  var alpha = 0.3f
  var dir1 = -1
  var numbTurn : Int = 0

  override def onGraphicsRender(g : GdxGraphics) = {
    if(direction== 0){
      numbTurn+= 1
    }
    if(numbTurn>=nbeTurnMAX){
      sunk = true
    }
    if (dir1 > 0)alpha += 0.01f
    else alpha += -0.01f
    super.onGraphicsRender(g) //let's see if it work later shall we
    g.drawTransformedPicture(posi.x, posi.y, direction, 2, imag)
    if(sunk){
      g.drawAlphaPicture(positi.x, positi.y,direction, 2, alpha, imag)
    }
  }
}
