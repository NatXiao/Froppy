package ch.hevs.gdx2d

import ch.hevs.gdx2d.components.bitmaps.BitmapImage
import com.badlogic.gdx.math.MathUtils.random
import com.badlogic.gdx.math.{Interpolation, Vector2}

import scala.collection.mutable.ArrayBuffer
import scala.math.{abs, sin, sqrt, tan}

class Game {
  var loose = false
  private var score: Int = 0
  private var life: Int = 5
  var nbeLilyPassed: Int = 0 //TODO : challenging the player when reached "level"
  //var zoom : Double = 1

  var firstLily = new Lily(new Vector2(300, 200))
  var starter1 = new Lily(new Vector2(700, 200))
  var starter2 = new Lily(new Vector2(1100, 200))
  var lilys: ArrayBuffer[Lily] = ArrayBuffer(firstLily, starter1, starter2)

  def addLily(): Unit = {
    var y: Int = random(60, 1020)
    var distance: Int = 400 // depends on nbeLilyPassed
    lilys.append(new Lily(new Vector2(lilys.last.pos.x + distance, (lilys.last.pos.y + y)%1020))) //create it after the last one
  }

  var frog: Frog = new Frog(lilys.head.pos) //create it on the first lily

  def onLily(pos: Vector2, angle: Float, lil: ArrayBuffer[Lily]): Boolean = {
    var count = 0
    //distance center lily and the direction
    if(270f>angle && angle > 90f){
      println(angle + "you can't go there")
      return false
    }
    var res : Double = 0
    for(l <- lil) { //wtf formula to detect if reached or not the water lily
      var angle2 = angle*math.Pi/180
      var vectPC : Vector2 = new Vector2(l.pos.x - pos.x, l.pos.y - pos.y)
      var norme_vectPC : Double = math.sqrt(vectPC.x*vectPC.x + vectPC.y*vectPC.y)
      var angle_alpha : Double = math.atan((l.pos.y-pos.y)/(l.pos.x - pos.x))//angle with beautiful trigo im radian
      var angle_between : Double = angle2 - angle_alpha
      var res : Double = norme_vectPC*math.abs(sin(angle_between))
      if (res <= l.r) {
        println(angle + "yeah, you jumped!")
        return true
      }
    }
    return false
  }

  def jump(): Unit = { //if the center of the lily is in the frog's colliderbox, add score, else life-1
    frog.onLily = false
    println("jump")
    if (onLily(frog.pos, frog.direction, lilys)) {
      frog.reached = true
      //TODO add if first or second lily
        addLily()
        nbeLilyPassed += 1
        score += 100
        /*for (lil <- lilys) {
          lil.pos.x = Interpolation.linear.apply(lil.pos.x - 400)
        }*/
        //frog.pos.y = Interpolation.linear.apply(frog.pos.y,frog.pos.y*frog.direction, frog.t)
        //frog.pos.x = Interpolation.linear.apply(lilys.tail.head.pos.x)
        lilys = lilys.drop(1)
        frog.onLily = true
      }
      else {
      frog.reached = false
      if (270f > frog.direction && frog.direction > 90f) {
        //if backward, don't jump, don't loose life
        println(frog.direction + "you can't go there")
      }else {
        // TODO Interpolation animation?
        life -= 1
      }
    }
      if(life == 0){
        loose = !loose
      }
  }
  //Getters
  def getScore(): String = {
    return "Score : " + score.toString
  }
  def getLife() : String = {
    return "Life : " + life.toString
  }
}