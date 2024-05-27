package ch.hevs.gdx2d

import ch.hevs.gdx2d.components.bitmaps.BitmapImage
import com.badlogic.gdx.math.Vector2

class MovableObject(pos : Vector2) extends AnimatedObject(pos) {
  var img: BitmapImage = ???
  var collider = pos.x
}
