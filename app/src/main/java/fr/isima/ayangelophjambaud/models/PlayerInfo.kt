package fr.isima.ayangelophjambaud.models

import java.util.*

data class PlayerInfo(val head: String,
                      val uuid: UUID,
                      val name: String,
                      val role: String,
                      val roleTranslation: String,
                      val deathTime: Int,
                      val killersPlayer: List<Player>,
                      val loversPlayer: List<Player>,
                      val nbKill: Int,
                      val infected: Boolean,
                      val solitary: Boolean,
                      val amnesiacLover: UUID,
                      val cursedLover: UUID,
                      val winner: Boolean)