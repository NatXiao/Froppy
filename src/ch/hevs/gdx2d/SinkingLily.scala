package ch.hevs.gdx2d
/**
class SinkingLily(var positi : Vector2) extends Lily(positi) {
  private val nbeTurnMAX : Int = 2
  var sunk : Boolean = false

  override def onGraphicsRender(g : GdxGraphics) = {
    super.onGraphicsRender(g) //let's see if it work later shall we
    var radius = 2
    var speed = 1
    g.drawTransformedPicture(posi.x, posi.y, direction, radius, img)
    // If reaching max or min size, invert the growing direction
    if (radius >= 10 || radius <= 1) speed *= -1
    // Modify the radius// Modify the radius
    radius += speed
  }
}
**/