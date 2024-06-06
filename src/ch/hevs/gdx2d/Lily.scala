package ch.hevs.gdx2d

import ch.hevs.gdx2d.components.bitmaps.BitmapImage
import ch.hevs.gdx2d.lib.{GdxGraphics, ScreenManager}
import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.math.MathUtils.{cos, sin}
import com.badlogic.gdx.math.{Interpolation, Vector2}
import window.ScreenSelector

class Lily(var posi: Vector2, var nbLily: Int, var rotationDirection : Boolean = true, var powerUp : Boolean = false) extends AnimatedObject(posi){
  override var img: BitmapImage = new BitmapImage("data/images/lily.png")
  var r : Int = 200
  var rotationSpeed : Double = 1 //for more spicy playing!
  var direction : Int = 0
  var mooving : Boolean = false
  var destinationX : Float = 0
  if(powerUp){
    //speed-up
    rotationSpeed = 0.5
    //slow-up
    rotationSpeed = 2
    //+1 life
    //arrow
    img = new BitmapImage("data/images/lily.png")
  }

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
      if(rotationDirection){
        direction+= 1*rotationSpeed
      }else{
        direction -=1*rotationSpeed
      }

      currentTime = 0f
      g.drawTransformedPicture(posi.x, posi.y, direction, 2, img)
    }
  }
}
