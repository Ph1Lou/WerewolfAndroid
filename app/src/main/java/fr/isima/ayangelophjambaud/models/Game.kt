package fr.isima.ayangelophjambaud.models

import java.util.*

data class Game (val date: Date,
                 val duration: Int,
                 val name: String,
                 val winnerCampKey: String,
                 val gameUUID: UUID,
                 val playersCount: Int,
                 val players: List<PlayerInfo>,
                 val winnerCamp: String,
                 val prettyEvents: List<PrettyEvent>)