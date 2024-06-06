package window.screens

import ch.hevs.gdx2d.components.screen_management.RenderingScreen
import ch.hevs.gdx2d.lib.{GdxGraphics, ScreenManager}
import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.math.{Rectangle, Vector2}
import window.ScreenSelector

class Settings extends RenderingScreen {
  override def onInit(): Unit = {}

  private val bMenu : Rectangle = new Rectangle(1370f, 880f,400f,100f)

  override def onGraphicRender(g: GdxGraphics): Unit = {
    g.clear(Color.DARK_GRAY)
    g.drawStringCentered(g.getScreenHeight / 2, "4 - Settings")
    g.drawFilledRectangle(bMenu.x+(bMenu.width/2), bMenu.y+(bMenu.height/2), bMenu.width, bMenu.height,0f,Color.GOLD)
  }

  override def dispose(): Unit = {
    super.dispose()
  }

  override def onKeyDown(keycode: Int): Unit = {
    super.onKeyDown(keycode)
  }

  override def onClick(x: Int, y: Int, button: Int): Unit = {
    super.onClick(x, y, button)
    val posClick: Vector2 = new Vector2(x, y)
    if (bMenu.contains(posClick)) {
      ScreenSelector.s.transitionTo(0, ScreenManager.TransactionType.SMOOTH)
    }
  }
}