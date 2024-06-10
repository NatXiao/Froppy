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
  var frog: Frog = new Frog(new Vector2(lilys.head.pos)) //create it on the first lily

  def addLily(): Unit = {
    if (nbeLilyPassed >= 15) {
      distance = 500
    }
    if (nbeLilyPassed >= 40) {
      distance = 600
    }
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
    val nblilyJumped: Int = onLily(frog.pos, frog.direction, lilys)
    frog.state = new Vector2(frog.posit)
      if (nblilyJumped != 0) {
        frog.onLily = false
        nbeLilyPassed += nblilyJumped
        score += 100*nblilyJumped
        for(i <- 0 until nblilyJumped){
          addLily()
        }
        var jumpDistance : Float = (lilys(nblilyJumped).posi.x-frog.pos.x)
        println("jump distnace"+jumpDistance)
        for (lil <- lilys) {
          lil.destinationX = lil.posi.x - jumpDistance
        }
        frog.destination = lilys(nblilyJumped).posi.y
        for (_ <- 0 until nblilyJumped) {
          lilys = lilys.drop(1)
        }
      }
      else {
        if (!(270f > frog.direction && frog.direction > 90f)) {
          frog.onLily = false
          addLily()
          addLily()
          println(frog.direction)
          frog.destination = (sin(frog.direction * math.Pi / 180)*2*distance).toFloat
          life -= 1
          frog.passed = false
          lilys = lilys.dropRight(1)
          lilys = lilys.dropRight(1)
          frog.posit = new Vector2(lilys.head.posi)
        }
      }
    for (lil <- lilys) {
      lil.state = new Vector2(lil.posi)
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