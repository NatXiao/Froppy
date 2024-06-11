package ch.hevs.gdx2d

import ch.hevs.gdx2d.components.bitmaps.BitmapImage
import ch.hevs.gdx2d.lib.GdxGraphics
import com.badlogic.gdx.Gdx
import com.badlogic.gdx.math.{Interpolation, Vector2}
import window.ScreenSelector

class Lily(var posi: Vector2, var nbLily: Int, var rotationDirection : Boolean = true, var powerUp : Boolean = false) extends AnimatedObject(posi){
  var fileName : String = "data/images/lily.png"
  var scale : Float = 2f

  if(ScreenSelector.skin){
    fileName = "data/images/ISC_lily.png"
  }
  override val img: BitmapImage = new BitmapImage(fileName)
  var r : Int = 200
  var rotationSpeed : Double = 1 //for more spicy playing!
  override var direction : Int = 0
  var mooving : Boolean = false
  var destinationX : Float = 0

  /*if(powerUp){
    //speed-up
    rotationSpeed = 0.5
    //slow-up
    rotationSpeed = 2
    //+1 life
    //arrow
    //img = new BitmapImage("data/images/lily.png")
  }*/

  override def onGraphicsRender(g: GdxGraphics): Unit = {
    super.onGraphicsRender(g)
    if(mooving){
      currentTime += Gdx.graphics.getDeltaTime
      var animationTime: Float = currentTime / ANIMATION_LENGTH
      posi.x = Interpolation.linear.apply(state.x, destinationX, animationTime)
      g.drawTransformedPicture(posi.x, posi.y, direction, scale, img)
      if (isAtDest(destinationX, posi.x, state.x)) {
        mooving = false
        g.drawTransformedPicture(posi.x, posi.y, direction, scale, img)
        currentTime = 0f
      }
    }
    else {
      if(rotationDirection){
        direction += 1*rotationSpeed.toInt
      }else{
        direction -=1*rotationSpeed.toInt
      }
      currentTime = 0f
      g.drawTransformedPicture(posi.x, posi.y, direction, scale, img)
    }
  }
}
