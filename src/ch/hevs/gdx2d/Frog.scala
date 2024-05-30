package ch.hevs.gdx2d
import ch.hevs.gdx2d.components.bitmaps.BitmapImage
import ch.hevs.gdx2d.components.physics.primitives.PhysicsCircle
import ch.hevs.gdx2d.lib.GdxGraphics
import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.math.MathUtils.{cos, sin}
import com.badlogic.gdx.math.Vector2

class Frog(var posit : Vector2) extends AnimatedObject(posit) {
  override var img: BitmapImage = new BitmapImage("data/images/frog_arrow-1.png")
  var r : Int = 30
  var direction : Float = 0 //degree
  var onLily : Boolean = true //rotation on
  var reached : Boolean = true //why? idk, I'll see if it's useless or not
  private var currentTime: Float = 0
  private val ANIMATION_LENGTH: Float = 10f
  var t : Float = 0

  def onGraphicsRender(g: GdxGraphics): Unit = {
    g.drawTransformedPicture(posit.x, posit.y, direction, 2, img)
    if(!onLily){
      img = new BitmapImage("data/images/jumping_frog.png")
      //t = currentTime/ANIMATION_LENGTH //wtf please kill me really

    }
    else{
      img = new BitmapImage("data/images/frog_arrow-1.png")
    }
  }
}
