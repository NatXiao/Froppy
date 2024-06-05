package window.screens

import ch.hevs.gdx2d.Game
import ch.hevs.gdx2d.components.screen_management.RenderingScreen
import ch.hevs.gdx2d.desktop.PortableApplication
import ch.hevs.gdx2d.lib.GdxGraphics
import ch.hevs.gdx2d.lib.utils.Logger
import com.badlogic.gdx.Input
import com.badlogic.gdx.graphics.Color

object ScreenSelector{
  def main(args : Array[String]): Unit = {
    new GameWindow(1920,1080)
  }
}

class GameWindow (width : Int, height : Int) extends PortableApplication(width, height)  {
  var game : Game =  _


  override def onInit(): Unit = {
    setTitle("Froppy")
    game = new Game
  }

  override def onGraphicRender(g: GdxGraphics): Unit = {
    g.clear(Color.BLUE)
    g.drawString(g.getScreenWidth-100, g.getScreenHeight-100,game.getScore())
    g.drawString(100, g.getScreenHeight-100, game.getLife())
    for (i <- game.lilys) {
      i.onGraphicsRender(g)
    }
    game.frog.onGraphicsRender(g)
    if (game.frog.onLily) {
      game.frog.direction = game.lilys.head.direction
    }
  }

  override def onKeyDown(keycode: Int): Unit = {
    super.onKeyDown(keycode)
    if (keycode == Input.Keys.SPACE) {
      game.jump()
    }
  }
}
