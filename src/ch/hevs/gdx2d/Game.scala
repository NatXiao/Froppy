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
  var nbLily = 0
  var distance: Int = 400
  val border : Int = 100

  //setup the game
  var firstLily = new StableLily(new Vector2(300, random(border, ScreenSelector.SCREEN_HEIGHT-border)),nbLily)
  var starter1 = new StableLily(new Vector2(firstLily.posi.x+distance, random(border, ScreenSelector.SCREEN_HEIGHT-border)),nbLily+1)
  var starter2 = new StableLily(new Vector2(starter1.posi.x+distance, random(border, ScreenSelector.SCREEN_HEIGHT-border)),nbLily+2)
  var lilys: ArrayBuffer[Lily] = ArrayBuffer(firstLily, starter1, starter2)
  var frog: Frog = new Frog(lilys.head.pos) //create it on the first lily

  nbLily = 2
  def addLily(): Unit = {
    if(nbeLilyPassed >= 40){ //last level : random distance
      distance = random(border*2, ScreenSelector.SCREEN_WIDTH/2-border)
    }
    if (nbeLilyPassed % 10 == 0) { // level up each 10 lilys passed
      distance += 100
    }
    var y: Int = random(border, ScreenSelector.SCREEN_HEIGHT-border)
    nbLily += 1
    if(randomBoolean(0.3f)){
      lilys.append(new MoovingLily(new Vector2(lilys.last.pos.x + distance, y),nbLily, randomBoolean()))
    }else{
      lilys.append(new StableLily(new Vector2(lilys.last.pos.x + distance, y),nbLily, randomBoolean()))
    }
  }

  def onLily(pos: Vector2, angle: Float, lil: ArrayBuffer[Lily]): Int = {//distance center lily and the direction
    if(270f>angle && angle > 90f){ //can't jump backward
      return 0
    }
    var nbposLily : Int = lil.head.nbLily
    for(l <- lil.tail) { //wtf formula to detect if reached or not the water lily
      var vectPC : Vector2 = new Vector2(l.pos.x - pos.x, l.pos.y - pos.y)
      var norme_vectPC : Double = math.sqrt(vectPC.x*vectPC.x + vectPC.y*vectPC.y)
      var angle_between : Double = angle*math.Pi/180 - math.atan((l.pos.y-pos.y)/(l.pos.x - pos.x))
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
    var jumpDistance: Float = lilys(nblilyJumped).pos.x - frog.pos.x
    frog.state = new Vector2(frog.pos)
    if (nblilyJumped != 0) {
      frog.onLily = false
      nbeLilyPassed += nblilyJumped
      score += 100 * (nblilyJumped * 1.4).round.toInt //add a bonus if more than 1 lily passed
      for (_ <- 0 until nblilyJumped) {
        addLily()
      }
      var jumpDistance: Float = lilys(nblilyJumped).pos.x - frog.pos.x

      frog.lily = lilys(nblilyJumped)

      for (lil <- lilys) {
        lil.destinationX = lil.pos.x - jumpDistance
      }
      frog.destination = lilys(nblilyJumped).pos.y
      for (_ <- 0 until nblilyJumped) {
        lilys = lilys.drop(1)
      }
      lilys.head.frogIsOn = true
    }
    else {
      if (!(270f > frog.direction && frog.direction > 90f)) {
        frog.onLily = false
        frog.destination = (sin(frog.direction * math.Pi / 180) * 2 * jumpDistance).toFloat
        life -= 1
        frog.passed = false
        frog.pos = new Vector2(lilys.head.pos)
        for (lil <- lilys) {
          lil.destinationX = lil.pos.x
        }
      }
    }
    for (lil <- lilys) {
      lil.state = new Vector2(lil.pos)
      lil.mooving = true
    }
  }

  def getScore: String = {
    return score.toString
  }
  def getLife: String = {
    return "Life : " + life.toString
  }
}