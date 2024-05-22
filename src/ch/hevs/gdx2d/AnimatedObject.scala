package ch.hevs.gdx2d

import ch.hevs.gdx2d.components.bitmaps.BitmapImage

abstract class AnimatedObject(var pos : Position) {
  var img : BitmapImage

}

class Position(x : Int, y : Int)
