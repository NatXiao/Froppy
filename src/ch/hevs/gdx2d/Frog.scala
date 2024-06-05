package ch.hevs.gdx2d
import ch.hevs.gdx2d.components.bitmaps.BitmapImage
import ch.hevs.gdx2d.components.physics.primitives.PhysicsCircle
import ch.hevs.gdx2d.lib.GdxGraphics
import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.math.MathUtils.{cos, sin}
import com.badlogic.gdx.math.{Interpolation, Vector2}

class Frog(var posit : Vector2) extends AnimatedObject(posit) {
  override var img: BitmapImage = new BitmapImage("data/images/frog_arrow.png")
  var img_jump = new BitmapImage("data/images/jumping_frog.png")
  var r : Int = 30
  var direction : Float = 0 //degree
  var onLily : Boolean = true //rotation on
  var destination : Float = _

  val ANIMATION_LENGTH = 1.3f // Animation length (in seconds)
  var currentTime = 0f // In seconds

  def onGraphicsRender(g: GdxGraphics): Unit = {
    direction += 1
    if (direction >= 360) {
      direction = 0
    }
    if(!onLily){
      currentTime += Gdx.graphics.getDeltaTime
      var animationTime: Float = currentTime / ANIMATION_LENGTH
      posit.y = Interpolation.linear.apply(posit.y, destination, animationTime)
      g.drawTransformedPicture(posit.x, posit.y, direction, 2, img_jump)
      g.clear(Color.BLUE)
      if(posit.y == destination){
        print("stop")
        onLily = true
        g.drawTransformedPicture(posit.x, posit.y, direction, 1, img)
        currentTime = 0f

      }
    }
    else{
      currentTime = 0f
      g.drawTransformedPicture(posit.x, posit.y, direction, 1, img)
    }

  }
}
