package ch.hevs.gdx2d
import ch.hevs.gdx2d.components.bitmaps.BitmapImage
import ch.hevs.gdx2d.components.physics.primitives.PhysicsCircle
import com.badlogic.gdx.math.Vector2

class Frog(var posit : Vector2) extends AnimatedObject(posit) {
  override var img: BitmapImage = ???
  var r : Int = 40
  var direction : Float = 0f //degree
  var onLily : Boolean = true //rotation on

}
