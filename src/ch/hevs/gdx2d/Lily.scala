package ch.hevs.gdx2d

import ch.hevs.gdx2d.components.bitmaps.BitmapImage
import ch.hevs.gdx2d.lib.GdxGraphics
import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.math.MathUtils.{cos, sin}
import com.badlogic.gdx.math.{Interpolation, Vector2}

class Lily(var posi: Vector2) extends AnimatedObject(posi){
  override var img: BitmapImage = new BitmapImage("data/images/lily.png")
  var r : Int = 100
  var rotationSpeed : Int = 1
  var rotationDirection : Boolean = true //trigonometric direction true, else false
  var direction : Int = 0
  //SLOW THE DIRECTION

  private var currentTime: Float = 0
  final private val ANIMATION_LENGTH: Float = 5f

  def onGraphicsRender(g: GdxGraphics): Unit = {
    //DEPEND ON TIME
    currentTime += Gdx.graphics.getDeltaTime
    var t = currentTime / ANIMATION_LENGTH

    val angle: Float = Interpolation.linear.apply(0f, 360f, t)

    g.drawTransformedPicture(posi.x, posi.y, angle, 2, img)
    direction += 1
    if (direction >= 360 || direction <= -360) {
      direction = 0
    }
    g.drawLine(posi.x, posi.y, cos(direction)*r+posi.x, sin(direction)*r+posi.y)
  }
}
