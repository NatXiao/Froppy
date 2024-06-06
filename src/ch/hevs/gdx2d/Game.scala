package ch.hevs.gdx2d

import com.badlogic.gdx.math.MathUtils.{random, randomBoolean}
import com.badlogic.gdx.math.Vector2
import window.ScreenSelector

import scala.collection.mutable.ArrayBuffer
import scala.math.sin

class Game {
  private var score: Int = 0
  var life: Int = 5
  var nbeLilyPassed: Int = 0
  var nbLily = 2
  var distance: Int = 400
  val border : Int = 100

  var firstLily = new Lily(new Vector2(300, 200),0)
  var starter1 = new Lily(new Vector2(700, 500),1)
  var starter2 = new Lily(new Vector2(1100, 200),2)
  var lilys: ArrayBuffer[Lily] = ArrayBuffer(firstLily, starter1, starter2)
  var frog: Frog = new Frog(lilys.head.pos) //create it on the first lily

  def addLily(): Unit = {
    /*if (nbeLilyPassed >= 15) {
      distance = 500
    } else if (nbeLilyPassed >= 40) {
      distance = 600
    }*/
    var y: Int = random(border, ScreenSelector.SCREEN_HEIGHT-border)
    nbLily += 1
    if(randomBoolean(0.3f)){
      lilys.append(new SinkingLily(new Vector2(lilys.last.pos.x + distance, y),nbLily, randomBoolean()))
    }else{
      lilys.append(new Lily(new Vector2(lilys.last.pos.x + distance, y),nbLily, randomBoolean()))
    }

  }

  def onLily(pos: Vector2, angle: Float, lil: ArrayBuffer[Lily]): Int = {
    //distance center lily and the direction
    if(270f>angle && angle > 90f){
      return 0
    }
    var nbposLily : Int = lil.head.nbLily
    println(nbposLily)
    for(l <- lil.tail) { //wtf formula to detect if reached or not the water lily
      var angle2 = angle*math.Pi/180
      var vectPC : Vector2 = new Vector2(l.pos.x - pos.x, l.pos.y - pos.y)
      var norme_vectPC : Double = math.sqrt(vectPC.x*vectPC.x + vectPC.y*vectPC.y)
      var angle_alpha : Double = math.atan((l.pos.y-pos.y)/(l.pos.x - pos.x))//angle with beautiful trigo in radian
      var angle_between : Double = angle2 - angle_alpha
      var res : Double = norme_vectPC*math.abs(sin(angle_between))
      if (res <= l.r) {
        if ((l.nbLily - nbposLily) % 2 == 1){
          return 1
        }
        else {
          return 2
        }
      }
    }
    return 0
  }



  def jump(): Unit = { //if the center of the lily is in the frog's colliderbox, add score, else life-1

    println("distance = " + distance)
    println("nbelily = " + nbeLilyPassed)
    frog.onLily = false
    if(frog.pos.y < 0 || frog.pos.y> 1080){
      addLily()
      for (lil <- lilys) {
        lil.destinationX = lil.posi.x - distance
      }
      frog.destination = lilys.tail.head.posi.y
      lilys = lilys.drop(1)
    }else{

      var nblilyJumped: Int = onLily(frog.pos, frog.direction, lilys)
      if (nblilyJumped == 1) {
        nbeLilyPassed += 1
        score += 100
        addLily()
        for (lil <- lilys) {
          lil.destinationX = lil.posi.x - distance
        }
        frog.destination = lilys.tail.head.posi.y
        lilys = lilys.drop(1)
      }
      else if (nblilyJumped == 2) {
        nbeLilyPassed += 2
        score += 400
        addLily()
        addLily()
        for (lil <- lilys) {
          lil.destinationX = lil.posi.x - 2 * distance
        }
        frog.destination = lilys.tail.tail.head.posi.y
        lilys = lilys.drop(1)
        lilys = lilys.drop(1)
      }
      else {
        for (lil <- lilys) {
          lil.destinationX = lil.posi.x
        }
        if (!(270f > frog.direction && frog.direction > 90f)) {
          frog.destination = (sin(frog.direction * math.Pi / 180)*distance).toFloat
          life -= 1
        }
      }
    }
    for (lil <- lilys) {
      lil.mooving = true
    }
  }

  def getScore(): String = {
    return score.toString
  }
  def getLife() : String = {
    return "Life : " + life.toString
  }
}