package ch.hevs.gdx2d

import com.badlogic.gdx.math.Vector2

class Game {
  private var score : Int = 0
  var life : Int = 5
  var nbeLilyPassed : Int = 0
  //var zoom : Double = 1

  var frog : Frog = new Frog(new Vector2(0,0))

  def jump(): Unit = {
    
  }
  def getScore() : Int = {
    return score
  }

}
