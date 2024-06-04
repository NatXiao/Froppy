package ch.hevs.gdx2d
import ch.hevs.gdx2d.components.bitmaps.BitmapImage
import ch.hevs.gdx2d.components.physics.primitives.PhysicsCircle
import ch.hevs.gdx2d.lib.GdxGraphics
import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.math.MathUtils.{cos, sin}
import com.badlogic.gdx.math.Vector2

class Frog(var posit : Vector2) extends AnimatedObject(posit) {
  override var img: BitmapImage = new BitmapImage("data/images/frog_arrow.png")
  var r : Int = 30
  var direction : Float = 0 //degree
  var onLily : Boolean = true //rotation on

  def onGraphicsRender(g: GdxGraphics): Unit = {
    //g.setColor(Color.GREEN)
    //g.drawCircle(posit.x, posit.y, r)
    g.drawTransformedPicture(posit.x, posit.y, direction, 1, img)
    direction += 1
    if (direction >= 360) {
      direction = 0
    }
    //println(direction)
    //g.drawLine(posit.x, posit.y, cos(direction)*r+posit.x, sin(direction)*r+posit.y)
  }
}
