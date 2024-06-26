package window.screens

import ch.hevs.gdx2d.components.screen_management.RenderingScreen
import ch.hevs.gdx2d.lib.{GdxGraphics, ScreenManager}
import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.math.{Rectangle, Vector2}
import window.ScreenSelector

class Loose extends RenderingScreen {
  override def onInit(): Unit = {}

  private var bLeaderboard : Rectangle = new Rectangle(80f, 80f,500f,100f)
  private var bMenu : Rectangle = new Rectangle(1370f, 80f,400f,100f)
  private var bReplay : Rectangle = new Rectangle(1370f, 880f,400f,100f)

  override def onGraphicRender(g: GdxGraphics): Unit = {
    g.clear(Color.BLUE)
    g.drawStringCentered(700,"Game over",ScreenSelector.FFF)

    g.drawString(bMenu.x + (80), bMenu.y + 80, "-Menu-", ScreenSelector.optimus80)
    g.drawString(bReplay.x + 70, bReplay.y + 80, "-Replay-", ScreenSelector.optimus80)
    g.drawString(bLeaderboard.x + 10, bLeaderboard.y + 80, "-Leaderboard-", ScreenSelector.optimus80)
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

    if (bReplay.contains(posClick)) {
      ScreenSelector.s.transitionTo(1, ScreenManager.TransactionType.SMOOTH)
    }

    if (bLeaderboard.contains(posClick)) {
      ScreenSelector.s.transitionTo(4, ScreenManager.TransactionType.SMOOTH)
    }
  }
}