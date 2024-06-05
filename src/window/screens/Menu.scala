package window.screens

import ch.hevs.gdx2d.components.screen_management.RenderingScreen
import ch.hevs.gdx2d.lib.GdxGraphics
import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.scenes.scene2d.Stage
import com.badlogic.gdx.{Gdx, InputMultiplexer}

class Menu extends RenderingScreen {
  override def onInit(): Unit = {
    val stage = new Stage()

    val multiplexer = new InputMultiplexer()

    multiplexer.addProcessor(stage)
    multiplexer.addProcessor(Gdx.input.getInputProcessor())

    Gdx.input.setInputProcessor(multiplexer)
  }

  override def onGraphicRender(g: GdxGraphics): Unit = {
    g.clear(Color.DARK_GRAY)
    g.drawStringCentered(g.getScreenHeight / 2, "1 - Menu")
    g.drawFilledRectangle(g.getScreenWidth / 2, g.getScreenHeight / 10, 500f, 90f,0f, Color.CYAN)
    g.drawFilledRectangle(g.getScreenWidth / 2, (g.getScreenHeight / 10)*2, 500f, 90f,0f, Color.BLACK)
    g.drawFilledRectangle(g.getScreenWidth / 2, (g.getScreenHeight / 10)*4, 600f, 200f,0f, Color.CHARTREUSE)
  }

  override def dispose(): Unit = {
    super.dispose()
  }

  override def onKeyDown(keycode: Int): Unit = {
    super.onKeyDown(keycode)
    println("sboing Menu")
  }

  override def onClick(x: Int, y: Int, button: Int): Unit = {
    super.onClick(x, y, button)
    println("click Menu")
  }
}
