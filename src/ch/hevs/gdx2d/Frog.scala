package ch.hevs.gdx2d
import ch.hevs.gdx2d.components.bitmaps.BitmapImage
import ch.hevs.gdx2d.lib.GdxGraphics
import com.badlogic.gdx.Gdx
import com.badlogic.gdx.math.{Interpolation, Vector2}
import window.ScreenSelector

class Frog(var pos : Vector2) extends AnimatedObject(pos) {
  var fileName : String = "data/images/frog_arrow.png"
  var fileName_jump : String = "data/images/jumping_frog.png"
  var fileName_dead : String = "data/images/dead_frog.png"


  if (ScreenSelector.skin) {
    fileName = "data/images/pam_arrow.png"
    fileName_dead = "data/images/dead_pingouin.png"
    fileName_jump = "data/images/Jacquemet_jumping.png"
  }
  var img_jump = new BitmapImage(fileName_jump)
  var img_dead = new BitmapImage(fileName_dead)
  override val img: BitmapImage = new BitmapImage(fileName)
  val scale : Float = 1f
  var r : Int = 30

  override var direction : Int = 0 //degree
  var destination : Float = _

  var onLily : Boolean = true //rotation on
  var passed : Boolean = true

  var lily: Lily = null

  override def onGraphicsRender(g: GdxGraphics): Unit = {
    if(!onLily){ //jump
      currentTime += Gdx.graphics.getDeltaTime
      var animationTime: Float = currentTime / ANIMATION_LENGTH
      pos.y = Interpolation.linear.apply(state.y, destination, animationTime)
      g.drawTransformedPicture(pos.x, pos.y, direction, scale*2, img_jump)
      if(isAtDest(destination, pos.y, state.y)){
        onLily = true
        currentTime = 0f
      }
    }
    else if (!passed) { //death
      currentTime += Gdx.graphics.getDeltaTime
      var animationTimeDeath: Float = currentTime / ANIMATION_LENGTH
      var alpha = Interpolation.linear.apply(0.9f, 0, animationTimeDeath)
      g.drawAlphaPicture(pos.x, pos.y, direction, scale, alpha, img_dead)
      if(alpha <= 0){
        passed = true
        pos = new Vector2(state)
      }
    }
    else{ //merry go round!
      direction += 1
      currentTime = 0f

      if(lily != null)
        pos = lily.pos

      g.drawTransformedPicture(pos.x, pos.y, direction, scale, img)
    }
  }
}
