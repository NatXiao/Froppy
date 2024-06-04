package ch.hevs.gdx2d

import com.badlogic.gdx.math.MathUtils.random
import com.badlogic.gdx.math.{Interpolation, Vector2}

import scala.collection.mutable.ArrayBuffer
import scala.math.sin

class Game {
  var loose = false
  private var score: Int = 0
  private var life: Int = 5
  var nbeLilyPassed: Int = 0
  //var zoom : Double = 1
  var nbLily = 2

  var firstLily = new Lily(new Vector2(300, 200),0)
  var starter1 = new Lily(new Vector2(700, 200),1)
  var starter2 = new Lily(new Vector2(1100, 200),2)
  var lilys: ArrayBuffer[Lily] = ArrayBuffer(firstLily, starter1, starter2)

  def addLily(): Unit = {
    var y: Int = random(60, 1020)
    var distance: Int = 400 // TODO depends on nbeLilyPassed
    nbLily += 1

    if (lilys.length <= 2) {
      lilys.append(new Lily(new Vector2(lilys.last.pos.x + distance, (lilys.last.pos.y + y) % 1020), nbLily))
      nbLily += 1
    }
    lilys.append(new Lily(new Vector2(lilys.last.pos.x + distance, (lilys.last.pos.y + y)%1020),nbLily)) //create it after the last one
  }

  var frog: Frog = new Frog(lilys.head.pos) //create it on the first lily

  def onLily(pos: Vector2, angle: Float, lil: ArrayBuffer[Lily]): Int = {
    //distance center lily and the direction
    if(270f>angle && angle > 90f){
      println(angle + "you can't go there")
      return 0
    }
    var res : Double = 0
    var nbposLily : Int = lil.head.nbLily
    println(nbposLily)
    for(l <- lil.tail) { //wtf formula to detect if reached or not the water lily
      var angle2 = angle*math.Pi/180
      var vectPC : Vector2 = new Vector2(l.pos.x - pos.x, l.pos.y - pos.y)
      var norme_vectPC : Double = math.sqrt(vectPC.x*vectPC.x + vectPC.y*vectPC.y)
      var angle_alpha : Double = math.atan((l.pos.y-pos.y)/(l.pos.x - pos.x))//angle with beautiful trigo im radian
      var angle_between : Double = angle2 - angle_alpha
      var res : Double = norme_vectPC*math.abs(sin(angle_between))
      if (res <= l.r) {
        if ((l.nbLily - nbposLily) % 2 == 1){
          println(angle + "yeah, you jumped one lily!")
        return 1
        }
        else {
          println(angle + "yeah, you jumped two lily!")
          return 2
        }
      }
    }
    return 0
  }

  def jump(): Unit = { //if the center of the lily is in the frog's colliderbox, add score, else life-1
    println("jump")
    var nblilyJumped: Int = onLily(frog.pos, frog.direction, lilys)
    if (nblilyJumped == 1) {
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
    else if (nblilyJumped == 2) {
      addLily()
      addLily()
      nbeLilyPassed += 2
      score += 200
      for (lil <- lilys) {
        lil.pos.x = Interpolation.linear.apply(lil.pos.x - 800)
      }
      frog.pos.y = Interpolation.linear.apply(lilys.tail.tail.head.pos.y)
      frog.pos.x = Interpolation.linear.apply(lilys.tail.tail.head.pos.x)
      lilys = lilys.drop(1)
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