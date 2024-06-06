package window.screens

import ch.hevs.gdx2d.components.screen_management.RenderingScreen
import ch.hevs.gdx2d.lib.{GdxGraphics, ScreenManager}
import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.math.{Rectangle, Vector2}
import window.ScreenSelector

import java.io.{BufferedReader, FileNotFoundException, FileReader}
import scala.collection.mutable.ArrayBuffer

class LeaderBoard extends RenderingScreen {
  override def onInit(): Unit = {}

  private val bMenu : Rectangle = new Rectangle(1370f, 880f,400f,100f)

  override def onGraphicRender(g: GdxGraphics): Unit = {
    g.clear(Color.DARK_GRAY)
    //g.drawStringCentered(g.getScreenHeight / 2, "5 - LeaderBoard")
    g.drawFilledRectangle(bMenu.x+(bMenu.width/2), bMenu.y+(bMenu.height/2), bMenu.width,bMenu.height,0f,Color.GOLD)
    g.drawStringCentered(ScreenSelector.SCREEN_HEIGHT/2, getBestScores().mkString("\n"))
  }

  override def dispose(): Unit = {
    super.dispose()
  }

  override def onKeyDown(keycode: Int): Unit = {
    super.onKeyDown(keycode)
  }

  override def onClick(x: Int, y: Int, button : Int): Unit = {
    super.onClick(x,y,button)
    val posClick : Vector2 = new Vector2(x,y)
    if (bMenu.contains(posClick)){
      ScreenSelector.s.transitionTo(0,ScreenManager.TransactionType.SMOOTH)
    }
  }

  def getBestScores(): ArrayBuffer[Int] = {
    var arr : ArrayBuffer[Int] = ArrayBuffer.empty
    try {
      val inputReader = new BufferedReader(new FileReader("data/bestScore.txt"))
      while(inputReader.ready()){
        arr.append(inputReader.readLine().toInt)
      }
      arr = arr.sorted(Ordering[Int].reverse)

      inputReader.close()
    }catch {
      case e: FileNotFoundException =>
        println("File not found")
        e.printStackTrace()
      case e: Exception =>
        println(e.getMessage())
    }
    return arr.take(5)
  }
}