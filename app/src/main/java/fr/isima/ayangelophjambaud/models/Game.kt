package fr.isima.ayangelophjambaud.models

import kotlinx.serialization.Serializable
import java.util.*

@Serializable
data class Game ( val date: Date,
                  val duration: Int,
                  val name: String,
                  val winnerCampKey: String,
                  val gameUUID: UUID,
                  val playerSize: Int,
                  val players: List<PlayerInfo>,
                  val winnerCamp: String,
                  val prettyEvents: List<PrettyEvent>)