package window.screens

import ch.hevs.gdx2d.components.screen_management.RenderingScreen
import ch.hevs.gdx2d.lib.{GdxGraphics, ScreenManager}
import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.math.{Rectangle, Vector2}
import com.badlogic.gdx.scenes.scene2d.Stage
import com.badlogic.gdx.{Gdx, InputMultiplexer}
import window.ScreenSelector

class Menu extends RenderingScreen {
  override def onInit(): Unit = {
    val stage = new Stage()

    val multiplexer = new InputMultiplexer()

    multiplexer.addProcessor(stage)
    multiplexer.addProcessor(Gdx.input.getInputProcessor())

    Gdx.input.setInputProcessor(multiplexer)
  }

  private val bNewGame : Rectangle = new Rectangle(610f, 350f,700f,200f)
  private val bSettings : Rectangle = new Rectangle(710f, 220f,500f,90f)
  private val bLeaderboard : Rectangle = new Rectangle(710f, 110f,500f,90f)


  override def onGraphicRender(g: GdxGraphics): Unit = {
    //g.clear(Color.DARK_GRAY)$
    g.setShader("data/shader/underwater.fp")
    g.clear()
    g.getShaderRenderer().setUniform("mouse", ScreenSelector.mouse)
    g.drawShader(ScreenSelector.time)
    ScreenSelector.time += Gdx.graphics.getDeltaTime



    g.drawStringCentered(g.getScreenHeight / 2, "1 - Menu")
    g.drawFilledRectangle(bNewGame.x+(bNewGame.width/2), bNewGame.y+(bNewGame.height/2), bNewGame.width,bNewGame.height,0f, Color.CYAN)
    g.drawFilledRectangle(bSettings.x+(bSettings.width/2), bSettings.y+(bSettings.height/2), bSettings.width,bSettings.height,0f, Color.BLACK)
    g.drawFilledRectangle(bLeaderboard.x+(bLeaderboard.width/2), bLeaderboard.y+(bLeaderboard.height/2), bLeaderboard.width,bLeaderboard.height,0f, Color.CHARTREUSE)
    g.drawString(bNewGame.x + 150, bNewGame.y + 130,"-New Game-", ScreenSelector.optimus80)
    g.drawString(bSettings.x + 170, bSettings.y + 60,"-Settings-", ScreenSelector.optimus40)
    g.drawString(bLeaderboard.x + 130, bLeaderboard.y + 60,"-Leaderboard-", ScreenSelector.optimus40)
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
    if (bNewGame.contains(posClick)) {
      ScreenSelector.s.transitionTo(1, ScreenManager.TransactionType.SMOOTH)
    }

    if (bSettings.contains(posClick)) {
      ScreenSelector.s.transitionTo(3, ScreenManager.TransactionType.SMOOTH)
    }

    if (bLeaderboard.contains(posClick)) {
      ScreenSelector.s.transitionTo(4, ScreenManager.TransactionType.SMOOTH)
    }

  }
}
