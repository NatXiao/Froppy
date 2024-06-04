package ch.hevs.gdx2d

import ch.hevs.gdx2d.components.bitmaps.BitmapImage
import ch.hevs.gdx2d.lib.GdxGraphics
import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.math.MathUtils.{cos, sin}
import com.badlogic.gdx.math.{Interpolation, Vector2}

class Lily(var posi: Vector2) extends AnimatedObject(posi){
  override var img: BitmapImage = new BitmapImage("data/images/lily.png")
  var r : Int = 200
  var rotationSpeed : Int = 1 //for more spicy playing!
  var rotationDirection : Boolean = true //trigonometric direction true, else false
  var direction : Int = 0

  //animations
  private var currentTime: Float = 0
  private val ANIMATION_LENGTH: Float = 10f

  def onGraphicsRender(g: GdxGraphics): Unit = {
    //DEPEND ON TIME
    g.drawCircle(posi.x, posi.y, r)
    g.drawTransformedPicture(posi.x, posi.y, direction, 2, img)
    direction+= 1 //merry-go-rounnnnnd
    if (direction >= 360 || direction <= -360) {
      direction = 0
    }
    g.drawLine(posi.x, posi.y, cos((direction*(math.Pi/180)).toFloat)*r+posi.x, sin((direction*(math.Pi/180)).toFloat)*r+posi.y)
  }
}
