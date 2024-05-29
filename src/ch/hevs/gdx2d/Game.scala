package ch.hevs.gdx2d

import com.badlogic.gdx.math.MathUtils.random
import com.badlogic.gdx.math.{Interpolation, Vector2}

import scala.collection.mutable.ArrayBuffer
import scala.math.{abs, sin, sqrt, tan}

class Game {
  var loose = false
  private var score: Int = 0
  private var life: Int = 5
  var nbeLilyPassed: Int = 0
  //var zoom : Double = 1

  var firstLily = new Lily(new Vector2(300, 200))
  var starter1 = new Lily(new Vector2(700, 200))
  var starter2 = new Lily(new Vector2(1100, 200))
  var lilys: ArrayBuffer[Lily] = ArrayBuffer(firstLily, starter1, starter2)

  def addLily(): Unit = {
    var y: Int = random(60, 1020)
    var distance: Int = 400 // TODO depends on nbeLilyPassed
    lilys.append(new Lily(new Vector2(lilys.last.pos.x + distance, (lilys.last.pos.y + y)%1020))) //create it after the last one
  }

  var frog: Frog = new Frog(lilys.head.pos) //create it on the first lily

  def onLily(pos: Vector2, angle: Float, lil: ArrayBuffer[Lily]): Boolean = {
    //distance center lily and the direction
    if(270f>angle && angle > 90f){
      println(angle + "you can't go there")
      return false
    }
    var res : Double = 0
    for(l <- lil) {
      var angle2 = angle*math.Pi/180

      var vectPC : Vector2 = new Vector2(l.pos.x - pos.x, l.pos.y - pos.y)
      var norme_vectPC : Double = math.sqrt(vectPC.x*vectPC.x + vectPC.y*vectPC.y)
      var oppos : Double = l.pos.y-pos.y
      var adj : Double = l.pos.x - pos.x
      var angle_alpha : Double = math.atan(oppos/adj)//radian
      var angle_between : Double = angle2 - angle_alpha
      var res : Double = norme_vectPC*math.abs(sin(angle_between))
      if (res <= l.r) {
        println(angle + "yeah, you jumped!")
        return true
      }
      println(angle + "so...")

    }
      //res = -(tan(angle2) * tan(angle2) * (l.pos.x-pos.x)*(l.pos.x-pos.x)) + (pos.y-l.pos.y)*(2*tan(angle2)*(pos.x-l.pos.x)-(pos.y-l.pos.y)-(pos.y-l.pos.y)) + l.r*l.r*(1+ tan(angle2)*tan(angle2))
      //println("res : " + res)
      //if (res >= 0) {
      //  return true
      //}
    //}

    return false
  }

  def jump(): Unit = { //if the center of the lily is in the frog's colliderbox, add score, else life-1
    println("jump")
    if (onLily(frog.pos, frog.direction, lilys)) {
        addLily()
        nbeLilyPassed += 1
        score += 100
        for (lil <- lilys) {
          lil.pos.x = Interpolation.linear.apply(lil.pos.x - 400)
        }
        frog.pos.y = Interpolation.linear.apply(lilys.tail.head.pos.y)
        frog.pos.x = Interpolation.linear.apply(lilys.tail.head.pos.x)
        lilys = lilys.drop(1)
      }
      else {
        life -= 1
      }
      if(life == 0){
        loose = !loose
      }
     //animation
  }


  def getScore(): String = {
    return "Score : " + score.toString
  }
  def getLife() : String = {
    return "Life : " + life.toString
  }
}