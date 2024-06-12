package ch.hevs.gdx2d

import ch.hevs.gdx2d.components.bitmaps.BitmapImage
import ch.hevs.gdx2d.lib.GdxGraphics
import com.badlogic.gdx.Gdx
import com.badlogic.gdx.math.{Interpolation, Vector2}
import window.ScreenSelector

abstract class Lily(var pos: Vector2, var nbLily: Int, var rotationDirection : Boolean = true) extends AnimatedObject(pos){
  override val img: BitmapImage
  override var direction: Int = 0
  var mooving: Boolean = false
  var destinationX: Float = 0
  override val scale : Float = 2f
  var rotationSpeed : Double = 1 //for more spicy playing!
  val r : Int = 200
  var frogIsOn : Boolean = _

  override def onGraphicsRender(g: GdxGraphics): Unit = {
    if (direction >= 360 || direction <= -360) {
      direction = 0
    }
    if(mooving){
      currentTime += Gdx.graphics.getDeltaTime
      var animationTime: Float = currentTime / ANIMATION_LENGTH
      pos.x = Interpolation.linear.apply(state.x, destinationX, animationTime)
      g.drawTransformedPicture(pos.x, pos.y, direction, scale, img)
      if (isAtDest(destinationX, pos.x, state.x)) {
        mooving = false
        g.drawTransformedPicture(pos.x, pos.y, direction, scale, img)
        currentTime = 0f
      }
    }else{
      if (rotationDirection) {
        direction += 1 * rotationSpeed.toInt
      } else {
        direction -= 1 * rotationSpeed.toInt
      }
      currentTime = 0f
      g.drawTransformedPicture(pos.x, pos.y, direction, scale, img)
    }
  }
}


/*
var rotationSpeed : Double = 1 //for more spicy playing!
if(powerUp){
  //speed-up
  rotationSpeed = 0.5
  //slow-up
  rotationSpeed = 2
  //+1 life
  //arrow
  //img = new BitmapImage("data/images/lily.png")
}*/