package fr.isima.ayangelophjambaud.models

import kotlinx.serialization.Serializable
import java.util.*

@Serializable
data class PlayerInfo(val head: String,
                      val uuid: UUID,
                      val name: String,
                      val role: String,
                      val roleTranslation: String,
                      val deathTime: Int,
                      val killersPlayer: List<Player>,
                      val loversPlayer: List<Player>,
                      val nbKill: Int,
                      val infected: Int,
                      val solitary: Int,
                      val amnesiacLover: UUID,
                      val cursedLover: UUID,
                      val winner: Int)