package window.screens

import ch.hevs.gdx2d.components.screen_management.RenderingScreen
import ch.hevs.gdx2d.lib.{GdxGraphics, ScreenManager}
import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.math.{Rectangle, Vector2}
import window.ScreenSelector

class Settings extends RenderingScreen {
  override def onInit(): Unit = {}

  private val bMenu : Rectangle = new Rectangle(1370f, 880f,400f,100f)
  private val bChangeSkin : Rectangle = new Rectangle(610f, 350f,700f,200f)
  var click : Int = 0

  override def onGraphicRender(g: GdxGraphics): Unit = {
    g.clear(Color.DARK_GRAY)
    g.drawStringCentered(850,"Settings",ScreenSelector.FFF)
    if (click % 2 != 0) {
      g.drawString(bChangeSkin.x + 150, bChangeSkin.y + 130,"-HES Skin-", ScreenSelector.optimus80)
    }else{
      g.drawString(bChangeSkin.x + 150, bChangeSkin.y + 130,"-Classic Skin-", ScreenSelector.optimus80)
    }

    g.drawString(bMenu.x + (80), bMenu.y + 80,"-Menu-", ScreenSelector.optimus80)
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
    if (bChangeSkin.contains(posClick)) {
      click += 1
      if(click % 2 != 0){
        ScreenSelector.skin = !ScreenSelector.skin
      }
    }
  }
}