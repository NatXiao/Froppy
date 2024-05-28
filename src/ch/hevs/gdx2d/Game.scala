package ch.hevs.gdx2d

import com.badlogic.gdx.math.MathUtils.random
import com.badlogic.gdx.math.{Interpolation, Vector2}

import scala.collection.mutable.ArrayBuffer
import scala.math.{abs, sqrt}

class Game {
  var loose = false
  private var score: Int = 0
  var life: Int = 5
  var nbeLilyPassed: Int = 0
  //var zoom : Double = 1

  var lilys: ArrayBuffer[Lily] = ArrayBuffer.empty

  def addLily(): Unit = {
    var y: Int = random(60, 1020)
    var distance: Int = 30 // TODO depends on nbeLilyPassed
    lilys.append(new Lily(new Vector2(distance, lilys.last.pos.y + y))) //create it after the last one
  }

  addLily()
  addLily()

  var frog: Frog = new Frog(lilys.head.pos) //create it on the first lily

  def onLily(pos: Vector2, angle: Float, lil: ArrayBuffer[Lily]): Boolean = {
    //distance center lily and the direction
    var proj: Double = 0
    var n : Vector2 = new Vector2(1/pos.x, 1/pos.y)
    for (l <- lil) {
      var vect : Vector2 = new Vector2(l.pos.x-pos.x, l.pos.y-pos.y)
      proj = abs(n.x*vect.x + n.y*vect.y)/sqrt(n.x*n.x + n.y*n.y)
      //compare radius and the distance
      if (proj < l.r / 2) {
        return true
      }
    }
    return false
  }

  def jump(): Unit = { //if the center of the lily is in the frog's colliderbox, add score, else life-1
    addLily()
    for (i <- 0 to 100) { //the distance we allow the frog to jump (?)
      frog.pos.y = Interpolation.linear.apply(frog.pos.y)
      for (lil <- lilys) {
        lil.pos.x = Interpolation.linear.apply(lil.pos.x, frog.pos.x, 1f)
      }
      if (onLily(frog.pos, frog.direction, lilys)) {
        score += 100
      }
      else life -= 1
    } //animation
  }

  def getScore(): String = {
    return score.toString
  }
}