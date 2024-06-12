package ch.hevs.gdx2d

import ch.hevs.gdx2d.components.bitmaps.BitmapImage
import ch.hevs.gdx2d.lib.GdxGraphics
import com.badlogic.gdx.Gdx
import com.badlogic.gdx.math.{Interpolation, Vector2}
import window.ScreenSelector

class MoovingLily(var po: Vector2, var nbeLily: Int, var rotDirection : Boolean = true) extends Lily(po, nbeLily) {
  val ANIMATION_LENGTH_UP_OR_DOWN = 2f
  private val start : Int =  ScreenSelector.SCREEN_HEIGHT - 100
  private var currentTime2 : Float = 0f
  private var upOrDown : Int = 1
  private var destinationY : Int = 100

  var fileName: String = "data/images/lily.png"
  if (ScreenSelector.skin) {
    fileName = "data/images/ISC_lily.png"
  }
  override val img: BitmapImage = new BitmapImage(fileName)
  override def onGraphicsRender(g: GdxGraphics) = {
    super.onGraphicsRender(g)
    val animationTime : Float = computePercentage
    po.y = Interpolation.linear.apply(start, destinationY, animationTime)
    g.drawTransformedPicture(po.x, po.y, direction, 2, img)
  }

  private def computePercentage = {
    if (upOrDown == 1) currentTime2 += Gdx.graphics.getDeltaTime
    else currentTime2 -= Gdx.graphics.getDeltaTime
    if (currentTime2 >= ANIMATION_LENGTH_UP_OR_DOWN || currentTime2 <= 0) upOrDown *= -1
    currentTime2 / ANIMATION_LENGTH_UP_OR_DOWN
  }
}
