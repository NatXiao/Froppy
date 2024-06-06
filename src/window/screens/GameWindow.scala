package window.screens

import ch.hevs.gdx2d.Game
import ch.hevs.gdx2d.components.screen_management.RenderingScreen
import ch.hevs.gdx2d.lib.{GdxGraphics, ScreenManager}
import com.badlogic.gdx.{Gdx, Input}
import window.ScreenSelector

import java.io.{FileOutputStream, PrintWriter}


class GameWindow extends RenderingScreen  {
  var game : Game = _


  override def onInit(): Unit = {
    game = new Game
  }

  override def onGraphicRender(g: GdxGraphics): Unit = {
    g.clear()

    if(g.getShaderRenderer == null){
      g.setShader("data/shader/underwater.fp")
    }
    g.getShaderRenderer().setUniform("mouse", ScreenSelector.mouse)

    g.drawShader(ScreenSelector.time)
    ScreenSelector.time += Gdx.graphics.getDeltaTime

    for (i <- game.lilys) {
      i.onGraphicsRender(g)
    }
    game.frog.onGraphicsRender(g)
    if (game.frog.onLily) {
      game.frog.direction = game.lilys.head.direction
    }
    g.drawString(g.getScreenWidth - 100, g.getScreenHeight - 100, "Score : " + game.getScore())
    g.drawString(100, g.getScreenHeight - 100, game.getLife())
    g.drawFPS()
  }

  override def onKeyDown(keycode: Int): Unit = {
    super.onKeyDown(keycode)
    if (keycode == Input.Keys.SPACE) {
      game.jump()
    }
    if (game.life == 0) {
      ScreenSelector.s.transitionTo(2, ScreenManager.TransactionType.SMOOTH)
      try{
        val fs = new FileOutputStream("data/bestScore.txt", true)
        val pw = new PrintWriter(fs)
        pw.println(game.getScore())
        pw.close()
      }catch {
        case e: Exception =>
          println("File can't be written")
          e.printStackTrace()
      }
    }
  }

  override def onClick(x: Int, y: Int, button: Int): Unit = {
    super.onClick(x, y, button)
  }
}