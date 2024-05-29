package window.screens

import ch.hevs.gdx2d.components.screen_management.RenderingScreen
import ch.hevs.gdx2d.lib.GdxGraphics
import com.badlogic.gdx.graphics.Color

class Menu extends RenderingScreen {
  override def onInit(): Unit = {}

  override def onGraphicRender(g: GdxGraphics): Unit = {
    g.clear(Color.DARK_GRAY)
    g.drawStringCentered(g.getScreenHeight / 2, "1 - Menu")
    g.drawFilledRectangle(g.getScreenWidth / 2, g.getScreenHeight / 10, 500f, 90f,0f, Color.GOLD)
    g.drawFilledRectangle(g.getScreenWidth / 2, (g.getScreenHeight / 10)*2, 500f, 90f,0f, Color.GOLD)
    g.drawFilledRectangle(g.getScreenWidth / 2, (g.getScreenHeight / 10)*4, 600f, 200f,0f, Color.GOLD)
  }

  override def dispose(): Unit = {
    super.dispose()
  }
}
