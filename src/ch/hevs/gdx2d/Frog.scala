package ch.hevs.gdx2d
import ch.hevs.gdx2d.components.bitmaps.BitmapImage
import ch.hevs.gdx2d.components.physics.primitives.PhysicsCircle
import ch.hevs.gdx2d.lib.GdxGraphics
import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.math.MathUtils.{cos, sin}
import com.badlogic.gdx.math.{Interpolation, Vector2}
import window.ScreenSelector

class Frog(var posit : Vector2) extends AnimatedObject(posit) {
  var fileName : String = "data/images/frog_arrow.png"
  var fileName_jump : String = "data/images/jumping_frog.png"
  var fileName_dead : String = "data/images/dead_frog.png"

  if (ScreenSelector.skin) {
    fileName = "data/images/pam.png"
    fileName_jump = "data/images/pingouin.png"
    fileName_jump = "data/images/Jacquemet.png"
  }
  var img_jump = new BitmapImage(fileName_jump)
  var img_dead = new BitmapImage(fileName_dead)
  override var img: BitmapImage = new BitmapImage(fileName)
  var r : Int = 30
  var direction : Float = 0 //degree
  var onLily : Boolean = true //rotation on
  var destination : Float = _
/*
  var alpha = 0.3f
  var dir1 = -1
  var passed : Boolean = _
 */

  val ANIMATION_LENGTH = 1f // Animation length (in seconds)
  var currentTime = 0f // In seconds

  def onGraphicsRender(g: GdxGraphics): Unit = {

    if (direction >= 360) {
      direction = 0
    }
/*    if (dir1 > 0) alpha += 0.01f
    else alpha += -0.01f*/
    if(!onLily){
      currentTime += Gdx.graphics.getDeltaTime
      var animationTime: Float = currentTime / ANIMATION_LENGTH
      posit.y = Interpolation.linear.apply(posit.y, destination, animationTime)
      g.drawTransformedPicture(posit.x, posit.y, direction, 2, img_jump)
      if(posit.y == destination){
        onLily = true
        g.drawTransformedPicture(posit.x, posit.y, direction, 1, img)
        currentTime = 0f
      }
      /*if (!passed) {
        g.drawAlphaPicture(posit.x, posit.y, direction, 2, alpha, img_dead)
      }*/
    }
    else{
      direction += 1
      currentTime = 0f
      g.drawTransformedPicture(posit.x, posit.y, direction, 1, img)
    }

  }
}
