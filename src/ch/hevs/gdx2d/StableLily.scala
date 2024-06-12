package ch.hevs.gdx2d

import ch.hevs.gdx2d.components.bitmaps.BitmapImage
import ch.hevs.gdx2d.lib.GdxGraphics

import com.badlogic.gdx.math.Vector2
import window.ScreenSelector

class StableLily(var posi: Vector2, var nbeLily: Int, var rotDirection : Boolean = true, var powerUp : Boolean = false) extends Lily(posi, nbeLily, rotDirection){
  var fileName : String = "data/images/lily.png"
  if(ScreenSelector.skin){
    fileName = "data/images/ISC_lily.png"
  }
  override val img: BitmapImage = new BitmapImage(fileName)

  override def onGraphicsRender(g: GdxGraphics): Unit = {
    super.onGraphicsRender(g)
  }
}

