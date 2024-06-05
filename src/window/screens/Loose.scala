package window.screens

import ch.hevs.gdx2d.components.screen_management.RenderingScreen
import ch.hevs.gdx2d.lib.GdxGraphics
import com.badlogic.gdx.graphics.Color

class Loose extends RenderingScreen {
  override def onInit(): Unit = {}

  override def onGraphicRender(g: GdxGraphics): Unit = {
    g.clear(Color.DARK_GRAY)
    g.drawStringCentered(g.getScreenHeight / 2, "3 - Loose")
    g.drawFilledRectangle(1570f, 150f, 400f,100f,0f,Color.GOLD)
    g.drawFilledRectangle(350f, 150f, 400f,100f,0f,Color.GOLD)
    g.drawFilledRectangle(1570f, 930f, 400f,100f,0f,Color.GOLD)
  }

  override def dispose(): Unit = {
    super.dispose()
  }

  override def onKeyDown(keycode: Int): Unit = {
    super.onKeyDown(keycode)
    println("sboing Loose")
  }

  override def onClick(x: Int, y: Int, button: Int): Unit = {
    super.onClick(x, y, button)
    println("click Loose")
  }
}