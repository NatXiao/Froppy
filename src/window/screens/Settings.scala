package window.screens

import ch.hevs.gdx2d.components.screen_management.RenderingScreen
import ch.hevs.gdx2d.lib.GdxGraphics
import com.badlogic.gdx.graphics.Color

class Settings extends RenderingScreen {
  override def onInit(): Unit = {}

  override def onGraphicRender(g: GdxGraphics): Unit = {
    g.clear(Color.DARK_GRAY)
    g.drawStringCentered(g.getScreenHeight / 2, "4 - Settings")
    g.drawFilledRectangle(1570f, 930f, 400f,100f,0f,Color.GOLD)
  }

  override def dispose(): Unit = {
    super.dispose()
  }
}