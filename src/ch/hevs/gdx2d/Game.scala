package ch.hevs.gdx2d

class Game {
  private var score : Int = 0
  var life : Int = 5
  var nbeLilyPassed : Int = 0
  //var zoom : Double = 1
  var loose : Boolean = false
  var frog : Frog = new Frog(new Position(0, 0))

  def jump(frog_pos : Position, lil_center : Position): Unit = {
    
  }
  def getScore() : Int = {
    return score
  }
}
