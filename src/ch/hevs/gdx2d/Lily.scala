package ch.hevs.gdx2d

import ch.hevs.gdx2d.components.bitmaps.BitmapImage
import ch.hevs.gdx2d.lib.GdxGraphics
import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.math.Vector2

class Lily(var posi: Vector2) extends AnimatedObject(posi){
  override var img: BitmapImage = new BitmapImage("data/images/lily.png")
  var r : Int = 100
  var rotationSpeed : Int = 1
  var rotationDirection : Boolean = true //trigonometric direction true, else false
  var direction : Int = 0

  def onGraphicsRender(g: GdxGraphics): Unit = {
    g.drawTransformedPicture(posi.x, posi.y, direction, 2, img)
    direction += 1
    if (direction >= 360 || direction <= -360) {
      direction = 0
    }
  }
}
