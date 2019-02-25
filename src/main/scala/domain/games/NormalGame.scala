package domain.games

import domain.Players

class NormalGame(private val players: Players) extends Game(players) {
  def wonBy(): Option[String] = {
    if (isDeuce) {
      return None
    }

    if (isAdvantage) {
      return None
    }

    if (score.hasPlayerAtLeastPoints(players._1, 4)) {
      return Some(players._1)
    }
    if (score.hasPlayerAtLeastPoints(players._2, 4)) {
      return Some(players._2)
    }
    None
  }

  def isDeuce: Boolean =
    haveBothPlayersAtLeastPoints(3) && score.isTie

  def isAdvantage: Boolean =
    haveBothPlayersAtLeastPoints(3) && score.isPointsDifference(1)

  private def haveBothPlayersAtLeastPoints(points: Int) =
    score.hasPlayerAtLeastPoints(players._1, points) && score.hasPlayerAtLeastPoints(players._2, points)
}