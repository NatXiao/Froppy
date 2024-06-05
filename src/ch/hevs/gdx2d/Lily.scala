package ch.hevs.gdx2d

import ch.hevs.gdx2d.components.bitmaps.BitmapImage
import ch.hevs.gdx2d.lib.GdxGraphics
import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.math.MathUtils.{cos, sin}
import com.badlogic.gdx.math.{Interpolation, Vector2}

class Lily(var posi: Vector2, var nbLily: Int) extends AnimatedObject(posi){
  override var img: BitmapImage = new BitmapImage("data/images/lily.png")
  var r : Int = 200
  var rotationSpeed : Int = 1 //for more spicy playing!
  var rotationDirection : Boolean = true //trigonometric direction true, else false
  var direction : Int = 0
  var mooving : Boolean = false
  var destinationX : Float = 0

  //animations
  private var currentTime: Float = 0
  private val ANIMATION_LENGTH: Float = 1f

  def onGraphicsRender(g: GdxGraphics): Unit = {
    //DEPEND ON TIME
    if (direction >= 360 || direction <= -360) {
      direction = 0
    }
    if(mooving){
      currentTime += Gdx.graphics.getDeltaTime
      var animationTime: Float = currentTime / ANIMATION_LENGTH
      posi.x = Interpolation.linear.apply(posi.x, destinationX, animationTime)
      g.drawTransformedPicture(posi.x, posi.y, direction, 2, img)
      if (posi.x == destinationX) {
        mooving = false
        g.drawTransformedPicture(posi.x, posi.y, direction, 2, img)
        currentTime = 0f

      }
    }
    else {
      direction+= 1
      currentTime = 0f
      g.drawTransformedPicture(posi.x, posi.y, direction, 2, img)
    }
  }
}
