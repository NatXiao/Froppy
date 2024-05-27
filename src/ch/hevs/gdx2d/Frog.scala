package ch.hevs.gdx2d
import ch.hevs.gdx2d.components.bitmaps.BitmapImage
import ch.hevs.gdx2d.components.physics.primitives.PhysicsCircle
import com.badlogic.gdx.math.Vector2

class Frog(pos : Vector2) extends AnimatedObject(pos) {
  override var img: BitmapImage = ???
  var colliderBox : PhysicsCircle = new PhysicsCircle("frog", new Vector2(10, 10), 40)
}
