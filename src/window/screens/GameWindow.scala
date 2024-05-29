package window.screens

import ch.hevs.gdx2d.Game
import ch.hevs.gdx2d.components.screen_management.RenderingScreen
import ch.hevs.gdx2d.lib.GdxGraphics
import ch.hevs.gdx2d.lib.utils.Logger
import com.badlogic.gdx.Input
import com.badlogic.gdx.graphics.Color


class GameWindow extends RenderingScreen {
  var game : Game =  new Game

  override def onInit(): Unit = {
  }

  override def onGraphicRender(g: GdxGraphics): Unit = {
    g.clear(Color.BLUE)
    g.drawStringCentered(g.getScreenHeight / 2, "2 - Game Window")
    g.drawString(g.getScreenWidth-100, g.getScreenHeight-100,game.getScore())
    g.drawString(100, g.getScreenHeight-100, game.getLife())
    for (i <- game.lilys) {
      i.onGraphicsRender(g)
    }
    game.frog.onGraphicsRender(g)
  }

  override def onKeyDown(keycode: Int): Unit = {
    super.onKeyDown(keycode)
    if (keycode == Input.Keys.SPACE) {
      game.jump()
    }
    Logger.log("jumped!")
  }

  override def dispose(): Unit = {
    super.dispose()
  }
}
