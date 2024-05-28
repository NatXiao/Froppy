package window

import ch.hevs.gdx2d.desktop.PortableApplication
import ch.hevs.gdx2d.lib.utils.Logger
import ch.hevs.gdx2d.lib.{GdxGraphics, ScreenManager}
import com.badlogic.gdx.Input

import window.screens.GameWindow
import window.screens.Menu
import window.screens.Loose
import window.screens.Settings

object ScreenSelector{
  def main(args : Array[String]): Unit = {
    new ScreenSelector ( 1920,1080)
  }
}

class ScreenSelector (width : Int, height : Int) extends PortableApplication (width, height) {

  private val s = new ScreenManager

  override def onInit(): Unit = {
    setTitle("Froppy")
    Logger.log("Game Launched, have fun playing Froppy !")
    s.registerScreen(classOf[Menu])
    s.registerScreen(classOf[GameWindow])
    s.registerScreen(classOf[Loose])
    s.registerScreen(classOf[Settings])
  }

  override def onGraphicRender(g: GdxGraphics): Unit = {
    s.render(g)
  }

  override def onClick(x: Int, y: Int, button: Int): Unit = {
    // Delegate the click to the child class
    s.getActiveScreen.onClick(x, y, button)

    // Display the next screen without transition
    if (button == Input.Buttons.LEFT) s.activateNextScreen()
  }


  override def onKeyDown(keycode: Int): Unit = {
    super.onKeyDown(keycode)


    // Switch to next screen using all available transitions effects
    /**if (keycode == Input.Keys.SPACE) {
      s.transitionToNext(ScreenManager.TransactionType.values())

      // Switch to the next transition effect
      transactionTypeId = (transactionTypeId + 1) % ScreenManager.TransactionType.values.length
    }**/
    if (keycode == Input.Keys.NUM_1) {
      s.transitionTo(0, ScreenManager.TransactionType.SLICE)

    } // s.activateScreen(0); Menu

    if (keycode == Input.Keys.NUM_2) {
      s.transitionTo(1, ScreenManager.TransactionType.SLIDE)

    }// s.activateScreen(1);

    if (keycode == Input.Keys.NUM_3) {
      s.transitionTo(2, ScreenManager.TransactionType.SMOOTH)

    } // s.activateScreen(2);

    if (keycode == Input.Keys.NUM_4) {
      s.transitionTo(3, ScreenManager.TransactionType.SMOOTH)

    } // s.activateScreen(3);
  }

}
