package window

import ch.hevs.gdx2d.desktop.PortableApplication
import ch.hevs.gdx2d.lib.utils.Logger
import ch.hevs.gdx2d.lib.{GdxGraphics, ScreenManager}
import com.badlogic.gdx.{Gdx, Input}
import com.badlogic.gdx.files.FileHandle
import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.graphics.g2d.BitmapFont
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator.FreeTypeFontParameter
import com.badlogic.gdx.math.Vector2
import window.screens._

object ScreenSelector{
  var s = new ScreenManager

  var sprite : String = "ISC"

  var font : FileHandle = null
  var parameter : FreeTypeFontParameter = null
  var generator : FreeTypeFontGenerator = null
  var optimus80 : BitmapFont = null
  var optimus40 : BitmapFont = null
  var FFF : BitmapFont = null
  var FFFsmall : BitmapFont = null

  var time : Float = 0
  var mouse : Vector2 = new Vector2()

  val SCREEN_WIDTH : Int = 1920
  val SCREEN_HEIGHT : Int = 1080

  def main(args : Array[String]): Unit = {
    new ScreenSelector ( SCREEN_WIDTH,SCREEN_HEIGHT)
  }
}

class ScreenSelector (width : Int, height : Int) extends PortableApplication (width, height) {

  override def onInit(): Unit = {
    setTitle("Froppy")
    Logger.log("Game Launched, have fun playing Froppy !")
    ScreenSelector.s.registerScreen(classOf[Menu])
    ScreenSelector.s.registerScreen(classOf[GameWindow])
    ScreenSelector.s.registerScreen(classOf[Loose])
    ScreenSelector.s.registerScreen(classOf[Settings])
    ScreenSelector.s.registerScreen(classOf[LeaderBoard])

    ScreenSelector.font = Gdx.files.internal("data/font/OptimusPrinceps.ttf")
    var parameter = new FreeTypeFontGenerator.FreeTypeFontParameter
    var generator = new FreeTypeFontGenerator(ScreenSelector.font)

    parameter.size = generator.scaleForPixelHeight(80)
    parameter.color = Color.LIGHT_GRAY
    ScreenSelector.optimus80 = generator.generateFont(parameter)

    parameter.size = generator.scaleForPixelHeight(40)
    ScreenSelector.optimus40 = generator.generateFont(parameter)

    ScreenSelector.font = Gdx.files.internal("data/font/FFF_Tusj.ttf")
    parameter = new FreeTypeFontGenerator.FreeTypeFontParameter
    generator = new FreeTypeFontGenerator(ScreenSelector.font)

    parameter.size = generator.scaleForPixelHeight(200)
    parameter.color = Color.GREEN
    ScreenSelector.FFF = generator.generateFont(parameter)

    parameter.size = generator.scaleForPixelHeight(80)
    parameter.color = Color.LIGHT_GRAY
    ScreenSelector.FFFsmall = generator.generateFont(parameter)

    generator.dispose()
  }

  override def onGraphicRender(g: GdxGraphics): Unit = {
    ScreenSelector.s.render(g)
  }

  override def onClick(x: Int, y: Int, button: Int): Unit = {
    super.onClick(x,y,button)

    val activeScreen = ScreenSelector.s.getActiveScreen
    if (activeScreen != null){
      activeScreen.onClick(x,y,button)
    }
    /**
    // Delegate the click to the child class
    s.getActiveScreen.onClick(x, y, button)

    // Display the next screen without transition
    if (button == Input.Buttons.LEFT) s.activateNextScreen()
    **/
  }


  override def onKeyDown(keycode: Int): Unit = {
    super.onKeyDown(keycode)

    val activeScreen = ScreenSelector.s.getActiveScreen
    if(activeScreen != null){
      activeScreen.onKeyDown(keycode)
    }
    // Switch to next screen using all available transitions effects
    /*if (keycode == Input.Keys.SPACE) {
      print("jump")
    }*/

    /*  // Switch to the next transition effect
      transactionTypeId = (transactionTypeId + 1) % ScreenManager.TransactionType.values.length
    }**/
    if (keycode == Input.Keys.NUM_1) {
      ScreenSelector.s.transitionTo(0, ScreenManager.TransactionType.SMOOTH)


    } // ScreenSelector.s.activateScreen(0); Menu

    if (keycode == Input.Keys.NUM_2) {
      ScreenSelector.s.transitionTo(1, ScreenManager.TransactionType.SMOOTH)

    }// ScreenSelector.s.activateScreen(1);

    if (keycode == Input.Keys.NUM_3) {
      ScreenSelector.s.transitionTo(2, ScreenManager.TransactionType.SMOOTH)

    } // ScreenSelector.s.activateScreen(2);

    if (keycode == Input.Keys.NUM_4) {
      ScreenSelector.s.transitionTo(3, ScreenManager.TransactionType.SMOOTH)

    } // ScreenSelector.s.activateScreen(3);

    if (keycode == Input.Keys.NUM_5) {
      ScreenSelector.s.transitionTo(4, ScreenManager.TransactionType.SMOOTH)

    }// s.activateScreen(4);
  }

}